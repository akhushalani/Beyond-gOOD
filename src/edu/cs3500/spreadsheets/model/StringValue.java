package edu.cs3500.spreadsheets.model;

/**
 * Formulas that evaluate to values that are Strings. These formulas extend the AbstractValue
 * abstract class and are a type of Formula.
 */
public class StringValue extends AbstractValue<String> {

  /**
   * Public constructor for StringValue.
   *
   * @param value String value
   */
  public StringValue(String value) {
    this.value = value;
  }
}
