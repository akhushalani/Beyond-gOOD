package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Cells which contain no value.
 */
public class BlankCell extends AbstractCell {

  /**
   * Public constructor for BlankCell.
   *
   * @param location represents location in spreadsheet
   */
  public BlankCell(Coord location) {
    super(location);
  }

  @Override
  public ArrayList<Coord> references() {
    return new ArrayList<>();
  }

  @Override
  public String evaluate(HashMap<Coord, Cell> worksheet) {
    return "";
  }

  @Override
  public String getEditString() {
    return "";
  }

  @Override
  public Formula getFormula() {
    return null;
  }
}
