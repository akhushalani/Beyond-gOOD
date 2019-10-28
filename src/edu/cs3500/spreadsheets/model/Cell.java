package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A broad interface for handling cells in a spreadsheet of any type.
 */
public interface Cell {
  /**
   * Check which cells the current Cell is directly or indirectly referencing.
   *
   * @return ArrayList of Coords that this cell references.
   */
  ArrayList<Coord> references();

  Formula getFormula();

  /**
   * Gets the name of the cell coordinates for output.
   *
   * @return the cell name
   */
  String getCellName();

  String getEditString();

  String evaluate(HashMap<Coord, Cell> worksheet);
}
