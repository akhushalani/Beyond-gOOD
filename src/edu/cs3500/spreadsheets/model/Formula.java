package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * An interface for formulas that can represent the contents of a Cell and be evaluated.
 */
public interface Formula {

  /**
   * Evaluates a formula to retrieve final form for cell or further evaluation.
   *
   * @return a Formula, either in its final form or in need of further evaluation, as a Formula.
   */
  Formula evaluate(Worksheet worksheet);

  /**
   * Converts a formula to a simplified String representation of the Formula.
   * @param worksheet the worksheet within which the Formula in question exists.
   * @return a simplified String representation of the Formula.
   */
  String getPrintString(Worksheet worksheet);

  /**
   * Retrieves the ValueType of a Formula.
   * @return a ValueType representing the ValueType of a given Formula.
   */
  ValueType getValueType();

  <R> R accept(FormulaVisitor<R> visitor);
}
