package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Represents the excel function Not, which returns a BooleanValue.
 */
public class Not extends AbstractFunction<BooleanValue> {

  /**
   * Represents a constructor for the Not function in excel, which takes in an ArrayList of
   *     Formulas.
   * @param args represents the arguments for the Not function represented as an ArrayList
   *     of Formulas.
   */
  public Not(ArrayList<Formula> args) {
    super(args);
  }

  @Override
  public BooleanValue evaluateFunction(ArrayList<Formula> args,
                                       Worksheet worksheet, Coord cellLoc) {
    if (args.size() != 1) {
      throw new IllegalArgumentException("Incorrect number of arguments.");
    } else if (args.get(0).evaluate(worksheet, cellLoc).getValueType() != ValueType.BOOLEAN) {
      throw new IllegalArgumentException("Invalid argument. Must use boolean argument.");
    } else {
      BooleanValueVisitor booleanVisitor = new BooleanValueVisitor();
      return new BooleanValue(!args.get(0).evaluate(worksheet, cellLoc).accept(booleanVisitor));
    }
  }
}
