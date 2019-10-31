package edu.cs3500.spreadsheets.sexp;

import java.util.List;

public class FunctionNameVisitor implements SexpVisitor<String> {
  public FunctionNameVisitor() {};

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
