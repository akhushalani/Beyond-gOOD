package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Concatenate extends AbstractFunction<StringValue> {
  public Concatenate(ArrayList<Formula> args) {
    super(args);
  }

  @Override
  public StringValue evaluateFunction(ArrayList<Formula> args, HashMap<Coord, Cell> worksheet) {
    StringBuilder concat = new StringBuilder();
    for (Formula arg : args) {
      if (arg.getValueType() != ValueType.NONE) {
        concat.append(arg.getPrintString(worksheet));
      }
    }
    return new StringValue(concat.toString());
  }
}
