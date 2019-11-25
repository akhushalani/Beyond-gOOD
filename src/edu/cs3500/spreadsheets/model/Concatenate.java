package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 *  Represents the excel function Concatenate, which returns a StringValue.
 */
public class Concatenate extends AbstractFunction<StringValue> {

  /**
   * Represents a constructor for the Concatenate function in excel, which takes in an ArrayList of
   *     Formulas.
   * @param args represents the arguments for the Concatenate function represented as an ArrayList
   *     of Formulas.
   */
  public Concatenate(ArrayList<Formula> args) {
    super(args);
  }

  @Override
  public StringValue evaluateFunction(ArrayList<Formula> args, Worksheet worksheet, Coord cellLoc) {
    StringBuilder concat = new StringBuilder();
    for (Formula arg : args) {
      if (arg.evaluate(worksheet, cellLoc) != null
              && arg.evaluate(worksheet, cellLoc).getValueType() == ValueType.STRING) {
        StringValueVisitor stringVisitor = new StringValueVisitor();
        concat.append(arg.evaluate(worksheet, cellLoc).accept(stringVisitor));
      }
    }
    return new StringValue(concat.toString());
  }
}
