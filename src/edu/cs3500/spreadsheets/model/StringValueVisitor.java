package edu.cs3500.spreadsheets.model;

public class StringValueVisitor implements FormulaVisitor<String> {

  @Override
  public String visitBooleanValue(BooleanValue b) {
    return "";
  }

  @Override
  public String visitDoubleValue(DoubleValue d) {
    return "";
  }

  @Override
  public String visitFunction(Function f) {
    return "";
  }

  @Override
  public String visitReference(Reference r) {
    return "";
  }

  @Override
  public String visitStringValue(StringValue s) {
    return s.getValue();
  }
}
