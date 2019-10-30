package edu.cs3500.spreadsheets.model;

/**
 * Formulas that evaluate to values that are Strings. These formulas extend the AbstractValue
 * abstract class and are a type of Formula.
 */
public class StringValue extends AbstractValue<String> {

  /**
   * Public constructor for StringValue that takes in a String that will represent the value of the
   *    String value.
   *
   * @param value represents the String that will be the value of this StringValue.
   */
  public StringValue(String value) {
    this.value = value;
  }

  @Override
  public ValueType getValueType() {
    return ValueType.STRING;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof StringValue)) {
      return false;
    }

    StringValue that = (StringValue) obj;
    return this.value.equals(that.getValue());
  }
}
