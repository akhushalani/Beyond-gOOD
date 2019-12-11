package edu.cs3500.spreadsheets.sexp;

import java.util.List;

/**
 * A visitor for returning an integer from an SNumber.
 */
public class IntegerSexpVisitor implements SexpVisitor<Integer> {
  @Override
  public Integer visitBoolean(boolean b) {
    return -1;
  }

  @Override
  public Integer visitNumber(double d) {
    return (int) d;
  }

  @Override
  public Integer visitSList(List<Sexp> l) {
    return -1;
  }

  @Override
  public Integer visitSymbol(String s) {
    return -1;
  }

  @Override
  public Integer visitString(String s) {
    return -1;
  }
}
