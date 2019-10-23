package edu.cs3500.spreadsheets.model;

/**
 * An interface for formulas that can represent the contents of a Cell and be evaluated.
 */
public interface Formula {
  /**
   * Checks whether a formula is able to be evaluated, i.e. can be the contents of a FormulaCell.
   *
   * @return whether formula is evaluable.
   */
  boolean evaluable();

  /**
   * Evaluates a formula to retrieve final form for cell or further evaluation.
   *
   * @return an evaluated Formula.
   */
  Formula evaluate();
}
