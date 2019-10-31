package edu.cs3500.spreadsheets.model;

/**
 * A visitor that visits a Formula and returns a value of a given type.
 *
 * @param <R>   the return type of the visitor
 */
public interface FormulaVisitor<R> {
  /**
   * Visits a DoubleValue.
   *
   * @param d the value
   * @return desired result
   */
  R visitDoubleValue(DoubleValue d);

  /**
   * Visits a BooleanValue.
   *
   * @param b the value
   * @return desired result
   */
  R visitBooleanValue(BooleanValue b);

  /**
   * Visits a StringValue.
   *
   * @param s the value
   * @return desired result
   */
  R visitStringValue(StringValue s);

  /**
   * Visits a Reference.
   *
   * @param r the reference
   * @return desired result
   */
  R visitReference(Reference r);

  /**
   * Visits a Function.
   *
   * @param f the function
   * @return desired result
   */
  R visitFunction(Function f);
}
