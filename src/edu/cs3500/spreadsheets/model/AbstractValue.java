package edu.cs3500.spreadsheets.model;

import java.util.HashMap;

/**
 * Formulas that evaluate to values directly.
 * @param <T> The datatype for the value that the formula evaluates to.
 */
public abstract class AbstractValue<T> implements Formula {
  protected T value;

  /**
   * Gets the value of the Formula.
   *
   * @return value of formula
   */
  public final T getValue() {
    return value;
  }

  @Override
  public String getPrintString() {
    return value + "";
  }

  @Override
  public Formula evaluate(HashMap<Coord, Cell> spreadsheet) {
    return this;
  }
}