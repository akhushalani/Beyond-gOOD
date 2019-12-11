package edu.cs3500.spreadsheets.sexp;

import java.util.List;

/**
 * A visitor for getting a double from an SNumber.
 */
public class DoubleSexpVisitor implements SexpVisitor<Double> {
  private boolean row;

  /**
   * A public constructor for a DoubleSexpVisitor.
   * @param row whether the double is for a row size.
   */
  public DoubleSexpVisitor(boolean row) {
    this.row = row;
  }

  @Override
  public Double visitBoolean(boolean b) {
    return -1.0;
  }

  @Override
  public Double visitNumber(double d) {
    if (row && d < 1.0) {
      return 1.0;
    } else {
      return d;
    }
  }

  @Override
  public Double visitSList(List<Sexp> l) {
    return -1.0;
  }

  @Override
  public Double visitSymbol(String s) {
    return -1.0;
  }

  @Override
  public Double visitString(String s) {
    return -1.0;
  }
}
