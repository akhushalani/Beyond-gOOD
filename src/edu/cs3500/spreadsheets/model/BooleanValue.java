package edu.cs3500.spreadsheets.model;

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
}
