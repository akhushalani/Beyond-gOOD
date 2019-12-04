package edu.cs3500.spreadsheets.provider.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.cs3500.spreadsheets.BeyondGood;
import edu.cs3500.spreadsheets.model.BeyondGoodWorksheetBuilder;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.sexp.CellSexpVisitor;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.ReferenceSexpVisitor;

import static edu.cs3500.spreadsheets.BeyondGood.validReference;

/**
 * Represents an adapter class for interpreting their worksheet interface in terms of our worksheet
 *        interface.
 */
public class ProviderWorksheetAdapter implements Worksheet {
  private edu.cs3500.spreadsheets.model.Worksheet delegate;

  public ProviderWorksheetAdapter(edu.cs3500.spreadsheets.model.Worksheet delegate) {
    this.delegate = delegate;
  }

  @Override
  public HashMap<Coord, Cell> evaluateAllCells() {
    HashMap<Coord, Cell> evaluatedWorksheet = new HashMap<>();
    for (Map.Entry<Coord, edu.cs3500.spreadsheets.model.Cell> entry
            : delegate.getWorksheet().entrySet()) {
      evaluatedWorksheet.put(entry.getKey(),
              new ProviderCellAdapter(entry.getValue(), this.delegate));
    }
    return evaluatedWorksheet;
  }

  @Override
  public Coord createCoord(String c) throws IllegalArgumentException {
    return BeyondGood.parseCoord(c);
  }

  @Override
  public ArrayList<Coord> createCoordList(String s) throws IllegalArgumentException {
    ArrayList<Coord> refList = new ArrayList<>();
    int colonIndex = s.indexOf(":");
    String firstRef = s.substring(0, colonIndex);
    String secondRef = s.substring(colonIndex + 1);
    if (BeyondGood.validReference(firstRef) && BeyondGood.validReference(secondRef)) {
      Coord firstCoord = BeyondGood.parseCoord(firstRef);
      Coord secondCoord = BeyondGood.parseCoord(secondRef);
      if (secondCoord.col >= firstCoord.col && secondCoord.row >= firstCoord.row) {
        for (int i = firstCoord.col; i <= secondCoord.col; i++) {
          for (int j = firstCoord.row; j <= secondCoord.row; i++) {
            refList.add(new Coord(i, j));
          }
        }
      }
    }
    return refList;
  }

  @Override
  public boolean isReference(String s) {
    return validReference(s);
  }

  @Override
  public boolean isRange(String s) throws IllegalArgumentException {
    if (!s.contains(":")) {
      return false;
    }
    int colonIndex = s.indexOf(":");
    String firstRef = s.substring(0, colonIndex);
    String secondRef = s.substring(colonIndex + 1);
    return BeyondGood.validReference(firstRef) && BeyondGood.validReference(secondRef);
  }

  @Override
  public <T> WorksheetReader.WorksheetBuilder<T> builder() {
    return (WorksheetReader.WorksheetBuilder<T>)
            new BeyondGoodWorksheetBuilder(this.delegate.getWorksheet());
  }

  @Override
  public Content getContents(Coord coordinate) throws IllegalArgumentException {
    return new ProviderContentAdapter(delegate.getCellAt(coordinate).getFormula(),
            delegate.getCellAt(coordinate).getRawContents(), delegate);
  }

  @Override
  public boolean isCyclic(Coord initial, Content content) {
    ReferenceSexpVisitor refVisitor = new ReferenceSexpVisitor();
    ArrayList<Coord> refs = content.getVal().accept(refVisitor);
    if (refs.isEmpty()) {
      return false;
    } else {
      for (Coord c : refs) {
        if (c.equals(initial)) {
          return true;
        } else {
          ArrayList<Coord> subReferences = delegate.getCellAt(c).references();
          for (Coord x : subReferences) {
            subReferences = gatherReferences(x, subReferences);
          }
          return subReferences.contains(initial);
        }
      }
    }
    return false;
  }

  private ArrayList<Coord> gatherReferences(Coord c, ArrayList<Coord> refs) {
    for (Coord x : delegate.getCellAt(c).references()) {
      refs.add(x);
      refs = gatherReferences(x, refs);
    }
    return refs;
  }

  @Override
  public ArrayList<Coord> nonEmptyCoords() {
    ArrayList<Coord> coords = new ArrayList<>();
    for (Map.Entry<Coord, edu.cs3500.spreadsheets.model.Cell> entry
            : delegate.getWorksheet().entrySet()) {
      coords.add(entry.getKey());
    }

    return coords;
  }

  @Override
  public void updateCell(Coord c, String s) {
    CellSexpVisitor cellVisitor = new CellSexpVisitor(c, s, delegate);
    edu.cs3500.spreadsheets.model.Cell cell = Parser.parse(s).accept(cellVisitor);
    delegate.setCell(c, cell);
  }

  @Override
  public HashMap<Coord, Cell> getWorksheet() {
    return evaluateAllCells();
  }
}
