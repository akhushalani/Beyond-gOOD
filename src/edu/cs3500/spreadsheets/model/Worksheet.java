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

  Formula getCalculatedReference(Coord coord);

  boolean hasCalculatedReference(Coord coord);

  void addCalculatedReference(Coord coord, Formula formula);

  void clearCalculatedReferences();
}
