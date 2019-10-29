package edu.cs3500.spreadsheets.sexp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FunctionCheckSexpVisitor implements SexpVisitor<Boolean> {
  public static final List<String> functionList
          = Collections.unmodifiableList(Arrays.asList(
          "AND",
          "CONCAT",
          "DIV",
          ">",
          "<",
          "NOT",
          "OR",
          "PRODUCT",
          "SUB",
          "SUM"));

  public FunctionCheckSexpVisitor() {};

  @Override
  public Boolean visitSymbol(String s) {
    return functionList.contains(s);
  }

  @Override
  public Boolean visitString(String s) {
    return false;
  }

  @Override
  public Boolean visitSList(List<Sexp> l) {
    return false;
  }

  @Override
  public Boolean visitNumber(double d) {
    return false;
  }

  @Override
  public Boolean visitBoolean(boolean b) {
    return false;
  }
}
