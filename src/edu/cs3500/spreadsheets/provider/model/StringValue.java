package edu.cs3500.spreadsheets.provider.model;

import java.util.Objects;

import edu.cs3500.spreadsheets.sexp.SString;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Class that extends AValue to represent a value that can be within a Coord - a String.
 */
public class StringValue extends AValue {

  public StringValue(String s) {
    super(s);
  }

  @Override
  public StringValue concatString(AValue v) {
    if (v.s != null) {
      return new StringValue(this.s + " " + v.s);
    } else {
      throw new IllegalArgumentException("Second argument is not a string.");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    StringValue strValue = (StringValue) o;

    return s.equals(strValue.s);
  }

  @Override
  public int hashCode() {
    return Objects.hash(s);
  }

  @Override
  public String toString() {
    return this.s;
  }

  @Override
  public Sexp getVal() {
    return new SString(this.s);
  }

}
