package edu.cs3500.spreadsheets.model;

/**
 * A visitor that visits a formula and returns its value if it is a BooleanValue.
 */
public class BooleanValueVisitor implements FormulaVisitor<Boolean> {

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
