package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents an abstract implementation of the Function interface containing the common elements
 *      between its subclasses, namely its ArrayList of Formula arguments.
 * @param <T> represents the return type of a Function.
 */
public abstract class AbstractFunction<T extends Formula> implements Function<T> {
  protected ArrayList<Formula> args;

  /**
   * Represents the constructor for the AbstractFunction class, which establishes the ArrayList of
   *    Functions arguments.
   * @param args represents the given arguments to a Function.
   */
  protected AbstractFunction(ArrayList<Formula> args) {
    this.args = args;
  }

  @Override
  public Formula evaluate(HashMap<Coord, Cell> worksheet) {
    return evaluateFunction(args, worksheet);
  }

  @Override
  public String getPrintString(HashMap<Coord, Cell> worksheet) {
    return evaluateFunction(args, worksheet).getPrintString(worksheet);
  }

  @Override
  public ValueType getValueType() {
    return ValueType.NONE;
  }
}
