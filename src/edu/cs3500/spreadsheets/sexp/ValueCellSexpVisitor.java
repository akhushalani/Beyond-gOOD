package edu.cs3500.spreadsheets.sexp;

import java.util.List;

import edu.cs3500.spreadsheets.model.BooleanValue;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.FormulaCell;
import edu.cs3500.spreadsheets.model.StringValue;

public class ValueCellSexpVisitor implements SexpVisitor<Cell> {
  private Coord location;

  public ValueCellSexpVisitor(Coord location) {
    this.location = location;
  }

  @Override
  public Cell visitSymbol(String s) {
    throw new IllegalArgumentException("Cell must be a value, reference, or function.");
  }

  @Override
  public Cell visitString(String s) {
    return new FormulaCell(location, new StringValue(s));
  }

  @Override
  public Cell visitSList(List<Sexp> l) {
    throw new IllegalArgumentException("Cell must be a value, reference, or function.");
  }

  @Override
  public Cell visitNumber(double d) {
    return new FormulaCell(location, new DoubleValue(d));
  }

  @Override
  public Cell visitBoolean(boolean b) {
    return new FormulaCell(location, new BooleanValue(b));
  }
}
