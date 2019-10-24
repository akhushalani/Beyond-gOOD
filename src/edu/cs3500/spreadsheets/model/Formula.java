package edu.cs3500.spreadsheets.model;

/**
 * An interface for formulas that can represent the contents of a Cell and be evaluated.
 */
public interface Formula {
  /**
   * Evaluates a formula to retrieve final form for cell or further evaluation.
   *
   * @return
   */
  Formula evaluate();
}
