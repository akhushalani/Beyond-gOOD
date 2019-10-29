package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Product extends AbstractFunction<DoubleValue> {
  public Product(ArrayList<Formula> args) {
    super(args);
  }

  @Override
  public DoubleValue evaluateFunction(ArrayList<Formula> args, HashMap<Coord, Cell> worksheet) {
    double product = 0.0;
    for (Formula arg : args) {
      if (arg.getValueType() == ValueType.NONE) {
        product *= ((DoubleValue) arg.evaluate(worksheet)).getValue();
      }
    }

    return new DoubleValue(product);
  }
}
