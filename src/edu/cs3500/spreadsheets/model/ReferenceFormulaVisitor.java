package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

import edu.cs3500.spreadsheets.sexp.ReferenceSexpVisitor;

public class ReferenceFormulaVisitor implements FormulaVisitor<ArrayList<Coord>> {
  private ArrayList<Coord> refList;
  private Worksheet worksheet;

  public ReferenceFormulaVisitor(Worksheet worksheet) {
    this.refList = new ArrayList<>();
    this.worksheet = worksheet;
  }

  @Override
  public ArrayList<Coord> visitStringValue(StringValue s) {
    return this.refList;
  }

  @Override
  public ArrayList<Coord> visitReference(Reference r) {
    this.refList.add(parseCoord(r.getPrintString(worksheet)));

    return null;
  }

  @Override
  public ArrayList<Coord> visitFunction(Function f) {
    ArrayList<Coord> innerRefs = new ArrayList<>();
    ReferenceFormulaVisitor innerRefVisitor = new ReferenceFormulaVisitor(worksheet);
    for (Object arg : f.getArguments()) {
      Formula fArg = (Formula) arg;
      innerRefs.addAll(fArg.accept(innerRefVisitor));
    }
    this.refList.addAll(innerRefs);

    return this.refList;
  }

  @Override
  public ArrayList<Coord> visitDoubleValue(DoubleValue d) {
    return this.refList;
  }

  @Override
  public ArrayList<Coord> visitBooleanValue(BooleanValue b) {
    return this.refList;
  }

  private Coord parseCoord(String s) {
    int firstNum = 0;
    int lastLetter = 0;

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        firstNum = i;
        break;
      }
    }

    for (int j = s.length() - 1; j >= 0; j--) {
      char d = s.charAt(j);
      if (Character.isLetter(d)) {
        lastLetter = j;
        break;
      }
    }

    int col = Coord.colNameToIndex(s.substring(0, lastLetter + 1));
    int row = Integer.parseInt(s.substring(firstNum));

    return new Coord(col, row);
  }
}
