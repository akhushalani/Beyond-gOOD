package edu.cs3500.spreadsheets.provider.model;

import java.util.Objects;

import edu.cs3500.spreadsheets.provider.model.BooleanValue;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Class that extends AValue to represent a value that can be within a Coord - a double.
 */
public class DoubleValue extends AValue {


  public DoubleValue(Double d) {
    super(d);
  }

  @Override
  public DoubleValue multiply(AValue v) {
    try {
      return new DoubleValue(this.d * v.d);
    } catch (Exception e) {
      return new DoubleValue(0.0);
    }
  }

  @Override
  public DoubleValue add(AValue v) {
    try {
      return new DoubleValue(this.d + v.d);
    } catch (Exception e) {
      return new DoubleValue(this.d + 0);
    }
  }

  @Override
  public BooleanValue lessThan(AValue v) throws IllegalArgumentException {
    if (v.d != null) {
      return new BooleanValue(this.d < v.d);
    } else {
      throw new IllegalArgumentException("Second argument is not a number.");
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

    DoubleValue doubleValue = (DoubleValue) o;

    return d.equals(doubleValue.d);
  }

  @Override
  public int hashCode() {
    return Objects.hash(d);
  }

  @Override
  public String toString() {
    return this.d.toString();
  }

  @Override
  public Sexp getVal() {
    return new SNumber(this.d);
  }

}
