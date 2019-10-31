package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class SquareRoot extends AbstractFunction<DoubleValue> {
  public SquareRoot(ArrayList<Formula> args) {
    super(args);
  }

  @Override
  public DoubleValue evaluateFunction(ArrayList<Formula> args, Worksheet worksheet) {
    if (args.size() != 1) {
      throw new IllegalArgumentException("Incorrect number of arguments.");
    } else if (args.get(0).evaluate(worksheet).getValueType() != ValueType.DOUBLE) {
      throw new IllegalArgumentException("Invalid argument. Must use double argument.");
    } else {
      DoubleValueVisitor doubleVisitor = new DoubleValueVisitor();
      return new DoubleValue(Math.sqrt(args.get(0).evaluate(worksheet).accept(doubleVisitor)));
    }
  }
}
