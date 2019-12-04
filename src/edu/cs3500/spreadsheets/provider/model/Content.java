package edu.cs3500.spreadsheets.provider.model;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Public interface representing anything that can be contained within a Coord.
 */
public interface Content {

  /**
   * Retrieves the desired AValue of the given Content.
   *
   * @param w the worksheet that is being dealt with
   * @param c the Coord to retrieve the AValue from
   * @return the AValue that has been evaluated
   */
  AValue evaluate(Worksheet w, Coord c);

  /**
   * For overwriting within each class that implements this interface.
   *
   * @param o the Object to be compared to.
   * @return true if the two objects are equal
   */
  boolean equals(Object o);


  int hashCode();

  String toString();

  /**
   * Returns the SExp representation of the this Content.
   *
   * @return the SExp that corresponds with this Content
   */
  Sexp getVal();

}
