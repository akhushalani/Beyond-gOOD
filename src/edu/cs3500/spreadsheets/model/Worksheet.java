package edu.cs3500.spreadsheets.model;

import java.util.HashMap;

/**
 * An interface representing worksheets which consist of Cells at various Coords.
 */
public interface Worksheet {

  /**
   * Takes in a coordinate and returns the Cell in the worksheet at that coordinate.
   * @param coord represents the coordinates of a Cell in the worksheet.
   * @return the Cell at the given Coord.
   */
  Cell getCellAt(Coord coord);

  /**
   * Takes in a coordinate and a cell and sets the value at that coordinate in the worksheet to be
   *      that cell.
   * @param coord a Coord representing the coordinates within the worksheet where the user
   *      wants a given cell to be.
   * @param cell a Cell that the user wants to be input into the worksheet.
   */
  void setCell(Coord coord, Cell cell);

  /**
   * Retrieves the worksheet from the Worksheet as a HashMap.
   */
  HashMap<Coord, Cell> getWorksheet();

  /**
   * Gets an already calculated reference in the model.
   * @param coord location of the reference to get
   * @return the formula of the cell at that location
   */
  Formula getCalculatedReference(Coord coord);

  /**
   * Determines whether the model has already calculated a given reference.
   * @param coord location at which to check
   * @return whether or not model has calculated the reference
   */
  boolean hasCalculatedReference(Coord coord);

  /**
   * Adds a calculated reference to the list of calculated references in the model.
   * @param coord location at which reference was calculated
   * @param formula calculated reference
   */
  void addCalculatedReference(Coord coord, Formula formula);

  /**
   * Clears the list of calculated references.
   */
  void clearCalculatedReferences();
}
