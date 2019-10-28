package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class Sum extends AbstractFunction<DoubleValue> {

  public Sum(ArrayList<Formula> args) {
    super(args);
  }

  @Override
  public DoubleValue evaluateFunction(ArrayList<Formula> args) {
    double sum = 0.0;
    for (Formula arg : args) {
      if (arg instanceof DoubleValue) {
        sum += ((DoubleValue) arg).getValue();
      } else {
        throw new IllegalArgumentException("Invalid argument type. " +
                "All arguments should be doubles.");
      }
    }

    return new DoubleValue(sum);
  }
}
