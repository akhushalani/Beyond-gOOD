package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the excel function Divide, which returns a DoubleValue.
 */
public class Divide extends AbstractFunction<DoubleValue> {

  /**
   * Represents a constructor for the Divide function in excel, which takes in an ArrayList of
   *     Formulas.
   * @param args represents the arguments for the Divide function represented as an ArrayList
   *     of Formulas.
   */
  public Divide(ArrayList<Formula> args) {
    super(args);
  }

  @Override
  public DoubleValue evaluateFunction(ArrayList<Formula> args, HashMap<Coord, Cell> worksheet) {
    if (args.size() != 2) {
      throw new IllegalArgumentException("Incorrect number of arguments.");
    } else if (args.get(0).getValueType() != ValueType.DOUBLE
            || args.get(1).getValueType() != ValueType.DOUBLE) {
      throw new IllegalArgumentException("Invalid arguments. Must use double arguments.");
    } else {
      return new DoubleValue(((DoubleValue) args.get(0).evaluate(worksheet)).getValue()
              / ((DoubleValue) args.get(0).evaluate(worksheet)).getValue());
    }
  }
}
