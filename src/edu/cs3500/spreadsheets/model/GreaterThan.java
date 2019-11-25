package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Represents the excel function GreaterThan, which returns a BooleanValue.
 */
public class GreaterThan extends AbstractFunction<BooleanValue> {

  /**
   * Represents a constructor for the GreaterThan function in excel, which takes in an ArrayList of
   *     Formulas.
   * @param args represents the arguments for the GreaterThan function represented as an ArrayList
   *     of Formulas.
   */
  public GreaterThan(ArrayList<Formula> args) {
    super(args);
  }

  @Override
  public BooleanValue evaluateFunction(ArrayList<Formula> args,
                                       Worksheet worksheet, Coord cellLoc) {
    if (args.size() != 2) {
      throw new IllegalArgumentException("Incorrect number of arguments.");
    } else if (anyNullArgs(worksheet, cellLoc)) {
      throw new IllegalArgumentException("Cell contains a blank reference.");
    } else if (args.get(0).evaluate(worksheet, cellLoc).getValueType() != ValueType.DOUBLE
            || args.get(1).evaluate(worksheet, cellLoc).getValueType() != ValueType.DOUBLE) {
      throw new IllegalArgumentException("Invalid arguments. Must use double arguments.");
    } else {
      DoubleValueVisitor doubleVisitor = new DoubleValueVisitor();
      return new BooleanValue(args.get(0).evaluate(worksheet, cellLoc).accept(doubleVisitor)
              > args.get(1).evaluate(worksheet, cellLoc).accept(doubleVisitor));
    }
  }
}
