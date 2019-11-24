package edu.cs3500.spreadsheets.model;

import java.util.Objects;

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
  public String getPrintString(Worksheet worksheet, Coord cellLoc, boolean clean) {
    if (clean) {
      return this.getValue();
    } else {
      String printString = this.getValue()
              .replace("\\", "\\\\")
              .replace("\"", "\\\"")
              .replace("'", "\\'");
      return "\"" + printString + "\"";
    }
  }

  @Override
  public Formula evaluate(Worksheet worksheet, Coord cellLoc) {
    return new StringValue(this.value);
  }

  @Override
  public <R> R accept(FormulaVisitor<R> visitor) {
    return visitor.visitStringValue(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof StringValue)) {
      return false;
    }

    StringValue that = (StringValue) obj;
    return this.value.equals(that.getValue());
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
