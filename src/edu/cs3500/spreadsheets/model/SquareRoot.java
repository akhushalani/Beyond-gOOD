package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Represents the function Square Root, which returns a DoubleValue.
 */
public class SquareRoot extends AbstractFunction<DoubleValue> {
  public SquareRoot(ArrayList<Formula> args) {
    super(args);
  }

  @Override
  public DoubleValue evaluateFunction(ArrayList<Formula> args, Worksheet worksheet, Coord cellLoc) {
    if (args.size() != 1) {
      throw new IllegalArgumentException("Incorrect number of arguments.");
    } else if (anyNullArgs(worksheet, cellLoc)) {
      throw new IllegalArgumentException("Cell contains a blank reference.");
    } else if (args.get(0).evaluate(worksheet, cellLoc).getValueType() != ValueType.DOUBLE) {
      throw new IllegalArgumentException("Invalid argument. Must use double argument.");
    } else {
      DoubleValueVisitor doubleVisitor = new DoubleValueVisitor();
      if (args.get(0).evaluate(worksheet, cellLoc).accept(doubleVisitor) >= 0) {
        return new DoubleValue(Math.sqrt(
                args.get(0).evaluate(worksheet, cellLoc).accept(doubleVisitor)));
      } else {
        throw new IllegalArgumentException("Argument cannot be negative.");
      }
    }
  }
}
