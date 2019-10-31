package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Represents the excel function Sum, which returns a DoubleValue.
 */
public class Sum extends AbstractFunction<DoubleValue> {

  /**
   * Represents a constructor for the Sum function in excel, which takes in an ArrayList of
   *     Formulas.
   * @param args represents the arguments for the Sum function represented as an ArrayList
   *     of Formulas.
   */
  public Sum(ArrayList<Formula> args) {
    super(args);
  }

  @Override
  public DoubleValue evaluateFunction(ArrayList<Formula> args, Worksheet worksheet) {
    double sum = 0.0;
    for (Formula arg : args) {
      if (arg.evaluate(worksheet).getValueType() == ValueType.DOUBLE) {
        DoubleValueVisitor doubleVisitor = new DoubleValueVisitor();
        sum += arg.evaluate(worksheet).accept(doubleVisitor);
      }
    }

    return new DoubleValue(sum);
  }
}
