package edu.cs3500.spreadsheets.model;

public class BooleanValueVisitor implements FormulaVisitor<Boolean> {
  public BooleanValueVisitor() {}

  @Override
  public Boolean visitStringValue(StringValue s) {
    return null;
  }

  @Override
  public Boolean visitReference(Reference r) {
    return null;
  }

  @Override
  public Boolean visitFunction(Function f) {
    return null;
  }

  @Override
  public Boolean visitDoubleValue(DoubleValue d) {
    return null;
  }

  @Override
  public Boolean visitBooleanValue(BooleanValue b) {
    return b.getValue();
  }
}
