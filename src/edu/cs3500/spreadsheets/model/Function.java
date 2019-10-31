package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Represents a Function type Formula that represents when the value of a cell in the worksheet
 *    begins with an = sign.
 * @param <T> represents the datatype of the Functions return value.
 */
public interface Function<T> extends Formula {

  /**
   * Evaluates a function to its simplest form for display or to another Formula in need of
   *    simplification.
   * @param args the arguments of a given Function.
   * @param worksheet the worksheet within which the Function and its arguments exist.
   * @return Generic T datatype that represents whatever the given Function returns.
   */
  T evaluateFunction(ArrayList<Formula> args, Worksheet worksheet, Coord cellLoc);

  ArrayList<Formula> getArguments();
}
