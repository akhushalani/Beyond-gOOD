package edu.cs3500.spreadsheets.provider.model;

import edu.cs3500.spreadsheets.provider.model.BooleanValue;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.provider.model.DoubleValue;
import edu.cs3500.spreadsheets.provider.model.StringValue;
import edu.cs3500.spreadsheets.provider.model.Worksheet;
import edu.cs3500.spreadsheets.sexp.Sexp;


/**
 * A class representing a possible Content that can be contained within a Coord within a Worksheet.
 */
public abstract class AValue implements Content {
  protected String s;
  protected Double d;
  protected Boolean b;


  public AValue(String s) {
    this.s = s;
  }

  public AValue(Double d) {
    this.d = d;
  }

  public AValue(Boolean b) {
    this.b = b;
  }


  @Override
  public AValue evaluate(Worksheet w, Coord c) {
    return this;
  }

  /**
   * Multiplies this AValue by the given AValue, return a DoubleValue of 0.0 if first value, is not
   * a DoubleValue (and thus cannot be multiplied).
   *
   * @param v is the given AValue to multiply by this AValue by
   * @return the DoubleValue representing the two AValues multiplied together
   */
  protected DoubleValue multiply(AValue v) {
    return new DoubleValue(0.0);
  }

  /**
   * Adds this AValue by the given AValue, return a DoubleValue of 0.0 if first value is not, a
   * DoubleValue (and thus cannot be added).
   *
   * @param v is the given AValue to add to this AValue by
   * @return the DoubleValue representing the two AValues added together
   */
  protected DoubleValue add(AValue v) {
    return new DoubleValue(0.0);
  }

  /**
   * Checks to see if this AValue is less than the given AValue.
   *
   * @param v is the given AValue that this AValue will be compared against.
   * @return BooleanValue of true if this AValue is less than the given one.
   * @throws IllegalArgumentException if this AValue is not a double / number.
   */
  protected BooleanValue lessThan(AValue v) throws IllegalArgumentException {
    throw new IllegalArgumentException("First argument is not a number.");
  }

  /**
   * Concatenates this AValue to the given AValue.
   *
   * @param v is the given AValue to concatenate this AValue to.
   * @return the StringValue representing the two AValues concatenated together.
   * @throws IllegalArgumentException if this AValue is not a String.
   */
  protected StringValue concatString(AValue v) throws IllegalArgumentException {
    throw new IllegalArgumentException("First argument is not a string.");
  }

  /**
   * Retrieves the SExp that represents this AValue.
   *
   * @return the corresponding Sexp.
   */
  public abstract Sexp getVal();
}
