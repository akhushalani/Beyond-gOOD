package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the excel function Product, which returns a DoubleValue.
 */
public class Product extends AbstractFunction<DoubleValue> {

  /**
   * Represents a constructor for the Product function in excel, which takes in an ArrayList of
   *     Formulas.
   * @param args represents the arguments for the Product function represented as an ArrayList
   *     of Formulas.
   */
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