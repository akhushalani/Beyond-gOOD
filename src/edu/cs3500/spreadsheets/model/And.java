package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Represents the excel function And, which returns a BooleanValue.
 */
public class And extends AbstractFunction<BooleanValue> {

  /**
   * Represents a constructor for the And function in excel, which takes in an ArrayList of
   *     Formulas.
   * @param args represents the arguments for the And function represented as an ArrayList of
   *     Formulas.
   */
  public And(ArrayList<Formula> args) {
    super(args);
  }

  @Override
  public BooleanValue evaluateFunction(ArrayList<Formula> args, Worksheet worksheet) {
    if (args.size() != 2) {
      throw new IllegalArgumentException("Incorrect number of arguments.");
    } else if (args.get(0).evaluate(worksheet).getValueType() != ValueType.BOOLEAN
            || args.get(1).evaluate(worksheet).getValueType() != ValueType.BOOLEAN) {
      throw new IllegalArgumentException("Invalid arguments. Must use boolean arguments.");
    } else {
      BooleanValueVisitor booleanVisitor = new BooleanValueVisitor();
      return new BooleanValue(args.get(0).evaluate(worksheet).accept(booleanVisitor)
              && args.get(1).evaluate(worksheet).accept(booleanVisitor));
    }
  }
}
