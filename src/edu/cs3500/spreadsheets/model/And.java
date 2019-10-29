package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;

public class And extends AbstractFunction<BooleanValue> {
  public And(ArrayList<Formula> args) {
    super(args);
  }

  @Override
  public BooleanValue evaluateFunction(ArrayList<Formula> args, HashMap<Coord, Cell> worksheet) {
    if (args.size() != 2) {
      throw new IllegalArgumentException("Incorrect number of arguments.");
    } else if (args.get(0).getValueType() != ValueType.BOOLEAN
            || args.get(1).getValueType() != ValueType.BOOLEAN) {
      throw new IllegalArgumentException("Invalid arguments. Must use boolean arguments.");
    } else {
      return new BooleanValue(((BooleanValue) args.get(0).evaluate(worksheet)).getValue()
              && ((BooleanValue) args.get(0).evaluate(worksheet)).getValue());
    }
  }
}
