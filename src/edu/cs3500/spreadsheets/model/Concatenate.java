package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;

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
