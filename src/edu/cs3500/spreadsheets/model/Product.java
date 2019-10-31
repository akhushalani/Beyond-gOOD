package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

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
  public DoubleValue evaluateFunction(ArrayList<Formula> args, Worksheet worksheet) {
    double product = 1.0;
    DoubleValueVisitor doubleVisitor = new DoubleValueVisitor();
    for (Formula arg : args) {
      if (arg.evaluate(worksheet).getValueType() == ValueType.DOUBLE) {
        product *= arg.evaluate(worksheet).accept(doubleVisitor);
      }
    }

    return new DoubleValue(product);
  }
}
