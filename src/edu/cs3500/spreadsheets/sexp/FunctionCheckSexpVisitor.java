package edu.cs3500.spreadsheets.sexp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A visitor that visits an s-expression and checks if it is a valid
 * function name as an SSymbol.
 */
public class FunctionCheckSexpVisitor implements SexpVisitor<Boolean> {
  public static final List<String> FUNCTION_LIST
          = Collections.unmodifiableList(Arrays.asList(
          "AND",
          "CONCAT",
          "DIV",
          ">",
          "<",
          "NOT",
          "OR",
          "PRODUCT",
          "SQRT",
          "SUB",
          "SUM"));

  @Override
  public Boolean visitSymbol(String s) {
    return FUNCTION_LIST.contains(s);
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
