package edu.cs3500.spreadsheets.provider.model;

/**
 * Public interface representing an operation that can be contained within a Coord in a Worksheet,
 * and can act on other Coords as well.
 */
public interface Function {

  /**
   * Evaluates a function to retrieve the AValue of its result.
   *
   * @return the AValue representation of the result of evaluating this Function.
   */
  AValue evaluate();
}
