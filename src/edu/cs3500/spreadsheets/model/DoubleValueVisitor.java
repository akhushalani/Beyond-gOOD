package edu.cs3500.spreadsheets.model;

/**
 * A visitor that visits a formula and returns its value if it is a DoubleValue.
 */
public class DoubleValueVisitor implements FormulaVisitor<Double> {

  @Override
  public Double visitBooleanValue(BooleanValue b) {
    return 0.0;
  }

  @Override
  public Double visitDoubleValue(DoubleValue d) {
    return d.getValue();
  }

  @Override
  public Double visitFunction(Function f) {
    return 0.0;
  }

  @Override
  public Double visitReference(Reference r) {
    return 0.0;
  }

  @Override
  public Double visitStringValue(StringValue s) {
    return 0.0;
  }
}
