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
  public Formula evaluate(Worksheet worksheet, Coord cellLoc) {
    return new BooleanValue(this.value);
  }

  @Override
  public String getPrintString(Worksheet worksheet, Coord cellLoc) {
    if (this.value) {
      return "true";
    } else {
      return "false";
    }
  }

  @Override
  public <R> R accept(FormulaVisitor<R> visitor) {
    return visitor.visitBooleanValue(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof BooleanValue)) {
      return false;
    }

    BooleanValue that = (BooleanValue) obj;
    return this.value == that.getValue();
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
