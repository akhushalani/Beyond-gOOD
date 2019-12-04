package edu.cs3500.spreadsheets.provider.model;

import java.util.Objects;

import edu.cs3500.spreadsheets.sexp.SBoolean;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Class that extends AValue to represent a value that can be within a Coord - a boolean.
 */
public class BooleanValue extends AValue {

  public BooleanValue(boolean b) {
    super(b);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    BooleanValue boolValue = (BooleanValue) o;

    return b.equals(boolValue.b);
  }

  @Override
  public int hashCode() {
    return Objects.hash(b);
  }

  @Override
  public String toString() {
    return this.b.toString();
  }


  @Override
  public Sexp getVal() {
    return new SBoolean(this.b);
  }

}
