package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Represents the excel function Or, which returns a BooleanValue.
 */
public class Or extends AbstractFunction<BooleanValue> {

  /**
   * Represents a constructor for the Or function in excel, which takes in an ArrayList of
   *     Formulas.
   * @param args represents the arguments for the Or function represented as an ArrayList
   *     of Formulas.
   */
  public Or(ArrayList<Formula> args) {
    super(args);
  }

  @Override
  public BooleanValue evaluateFunction(ArrayList<Formula> args, Worksheet worksheet, Coord cellLoc) {
    if (args.size() != 2) {
      throw new IllegalArgumentException("Incorrect number of arguments.");
    } else if (args.get(0).evaluate(worksheet, cellLoc).getValueType() != ValueType.BOOLEAN
            || args.get(1).evaluate(worksheet, cellLoc).getValueType() != ValueType.BOOLEAN) {
      throw new IllegalArgumentException("Invalid arguments. Must use boolean arguments.");
    } else {
      BooleanValueVisitor booleanVisitor = new BooleanValueVisitor();
      return new BooleanValue(args.get(0).evaluate(worksheet, cellLoc).accept(booleanVisitor)
              || args.get(1).evaluate(worksheet, cellLoc).accept(booleanVisitor));
    }
  }
}
