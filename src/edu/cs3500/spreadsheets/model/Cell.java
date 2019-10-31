package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

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

  /**
   * Returns the Formula within the given cell.
   * @return the Formula that the given cell contains as a Formula.
   */
  Formula getFormula();

  /**
   * Gets the name of the cell coordinates for output.
   *
   * @return the cell name
   */
  String getCellName();

  /**
   * Evaluates the given Cell to a String representation value.
   * @param worksheet the worksheet within which the Cell and its references exist.
   * @return the String representation of the cell value
   */
  String evaluate(Worksheet worksheet);

  /**
   * Evaluates whether or not the given cell has a cyclic reference.
   * @param location the location of the cell in the worksheet.
   * @return boolean value whether or not the Cell has a cyclic reference.
   */
  boolean cyclicReference(Coord location);
}
