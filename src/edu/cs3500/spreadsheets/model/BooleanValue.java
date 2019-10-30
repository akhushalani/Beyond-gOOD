package edu.cs3500.spreadsheets.model;

import java.util.Objects;

/**
 * Formulas that evaluate to values that are booleans. These formulas extend the AbstractValue
 * abstract class and are a type of Formula.
 */
public class BooleanValue extends AbstractValue<Boolean> {
  /**
   * Public constructor for BooleanValue.
   *
   * @param value boolean value
   */
  public BooleanValue(boolean value) {
    this.value = value;
  }

  @Override
  public ValueType getValueType() {
    return ValueType.BOOLEAN;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof BooleanValue)) {
      return false;
    }

    BooleanValue that = (BooleanValue) obj;
    return this.value == that.getValue();
  }
}
