package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Not extends AbstractFunction<BooleanValue> {
  public Not(ArrayList<Formula> args) {
    super(args);
  }

  @Override
  public BooleanValue evaluateFunction(ArrayList<Formula> args, HashMap<Coord, Cell> worksheet) {
    if (args.size() != 1) {
      throw new IllegalArgumentException("Incorrect number of arguments.");
    } else if (args.get(0).getValueType() != ValueType.BOOLEAN) {
      throw new IllegalArgumentException("Invalid argument. Must use boolean argument.");
    } else {
      return new BooleanValue(!((BooleanValue) args.get(0).evaluate(worksheet)).getValue());
    }
  }
}
