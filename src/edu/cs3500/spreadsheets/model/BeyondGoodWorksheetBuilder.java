package edu.cs3500.spreadsheets.model;

import java.util.HashMap;

import edu.cs3500.spreadsheets.sexp.CellSexpVisitor;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;

public class BeyondGoodWorksheetBuilder
        implements WorksheetReader.WorksheetBuilder<BeyondGoodWorksheet> {
  private HashMap<Coord, Cell> worksheet;

  public BeyondGoodWorksheetBuilder() {
    this.worksheet = new HashMap<>();
  }

  public BeyondGoodWorksheetBuilder(HashMap<Coord, Cell> worksheet) {
    this.worksheet = worksheet;
  }

  @Override
  public WorksheetReader.WorksheetBuilder<BeyondGoodWorksheet>
  createCell(int col, int row, String contents) {
    Parser p = new Parser();
    Sexp sexpContents = p.parse(contents);
    Coord coord = new Coord(col, row);
    CellSexpVisitor cellVisitor = new CellSexpVisitor(coord);
    this.worksheet.put(coord, sexpContents.accept(cellVisitor));

    return new BeyondGoodWorksheetBuilder(this.worksheet);
  }

  @Override
  public BeyondGoodWorksheet createWorksheet() {
    return new BeyondGoodWorksheet(worksheet);
  }
}
