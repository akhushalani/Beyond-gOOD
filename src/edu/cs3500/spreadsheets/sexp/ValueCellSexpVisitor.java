package edu.cs3500.spreadsheets.sexp;

import java.util.List;

import edu.cs3500.spreadsheets.model.BooleanValue;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.FormulaCell;
import edu.cs3500.spreadsheets.model.StringValue;
import edu.cs3500.spreadsheets.model.Worksheet;

/**
 * A visitor that visits an s-expression and returns a Cell containing a value.
 */
public class ValueCellSexpVisitor implements SexpVisitor<Cell> {
  private Coord location;
  private String rawContents;
  private Worksheet worksheet;

  public ValueCellSexpVisitor(Coord location, String rawContents, Worksheet worksheet) {
    this.location = location;
    this.rawContents = rawContents;
    this.worksheet = worksheet;
  }

  @Override
  public Cell visitSymbol(String s) {
    throw new IllegalArgumentException("Cell must be a value, reference, or function.");
  }

  @Override
  public Cell visitString(String s) {
    return new FormulaCell(location, new StringValue(s), rawContents, worksheet);
  }

  @Override
  public Cell visitSList(List<Sexp> l) {
    throw new IllegalArgumentException("Cell must be a value, reference, or function.");
  }

  @Override
  public Cell visitNumber(double d) {
    return new FormulaCell(location, new DoubleValue(d), rawContents, worksheet);
  }

  @Override
  public Cell visitBoolean(boolean b) {
    return new FormulaCell(location, new BooleanValue(b), rawContents, worksheet);
  }
}
