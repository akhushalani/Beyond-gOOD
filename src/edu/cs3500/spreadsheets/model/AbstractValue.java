package edu.cs3500.spreadsheets.model;

public abstract class AbstractValue<ValueType> implements Formula {
  protected ValueType value;

  /**
   * Gets the value of the Formula.
   *
   * @return value of formula
   */
  public final ValueType getValue() {
    return value;
  }

  @Override
  public Formula evaluate() {
    return this;
  }
}