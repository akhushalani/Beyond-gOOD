package edu.cs3500.spreadsheets.model;

import java.util.Objects;

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
}