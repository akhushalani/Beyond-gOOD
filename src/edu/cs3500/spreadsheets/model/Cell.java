package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * A broad interface for handling cells in a spreadsheet of any type.
 */
public interface Cell {
  /**
   * Check which cells the current Cell is directly or indirectly referenced by.
   *
   * @return ArrayList of Coords that reference this cell.
   */
  ArrayList referencedBy();

  /**
   * Gets the name of the cell coordinates for output.
   *
   * @return the cell name
   */
  String getCellName();
}
