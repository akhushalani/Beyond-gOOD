package edu.cs3500.spreadsheets.model;

import java.util.Objects;

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

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof DoubleValue)) {
      return false;
    }

    DoubleValue that = (DoubleValue) obj;
    return Math.abs(this.value - that.getValue()) <= 0.000001;
  }
}
