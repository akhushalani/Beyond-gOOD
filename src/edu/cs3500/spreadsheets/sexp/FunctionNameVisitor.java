package edu.cs3500.spreadsheets.sexp;

import java.util.List;

/**
 * A visitor to be called on an s-expression that is the first in an SList
 * and gets the String format of it.
 */
public class FunctionNameVisitor implements SexpVisitor<String> {
  @Override
  public String visitSymbol(String s) {
    return s;
  }

  @Override
  public String visitString(String s) {
    return null;
  }

  @Override
  public String visitSList(List<Sexp> l) {
    return null;
  }

  @Override
  public String visitNumber(double d) {
    return null;
  }

  @Override
  public String visitBoolean(boolean b) {
    return null;
  }
}
