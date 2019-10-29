package edu.cs3500.spreadsheets.model;

import java.util.HashMap;

import edu.cs3500.spreadsheets.sexp.CellSexpVisitor;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Represents a factory class for BeyondGoodWorksheets.
 */
public class BeyondGoodWorksheetBuilder
        implements WorksheetReader.WorksheetBuilder<BeyondGoodWorksheet> {
  private HashMap<Coord, Cell> worksheet;

  /**
   * Represents a constructor for the BeyondGoodWorkSheetBuilder that establishes a HashMap
   *     worksheet that represents the worksheet to be produced.
   */
  public BeyondGoodWorksheetBuilder() {
    this.worksheet = new HashMap<>();
  }

  /**
   * Represents a constructor for the BeyondGoodWorkSheetBuilder that takes in a worksheet as
   *      a HashMap establishes a HashMap worksheet that represents the worksheet to be produced.
   * @param worksheet the previously extant worksheet that the new BeyondGoodWorksheet is meant
   *      to me generated from.
   */
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
