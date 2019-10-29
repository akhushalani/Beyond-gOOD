package edu.cs3500.spreadsheets.model;

/**
 * Formulas that evaluate to values that are doubles. These formulas extend the AbstractValue
 * abstract class and are a type of Formula.
 */
public class DoubleValue extends AbstractValue<Double> {
  /**
   * Public constructor for DoubleValue.
   *
   * @param value double value
   */
  public DoubleValue(double value) {
    this.value = value;
  }

  @Override
  public ValueType getValueType() {
    return ValueType.DOUBLE;
  }
}
