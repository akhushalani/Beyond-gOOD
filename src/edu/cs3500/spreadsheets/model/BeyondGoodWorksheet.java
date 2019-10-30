package edu.cs3500.spreadsheets.model;

import java.util.HashMap;

/**
 * Represents an implementation of the Worksheet interface meant for use in our BeyondGOOD
 *    implementation of Excel, which represents a WorkSheet in Excel.
 */
public class BeyondGoodWorksheet implements Worksheet {
  private HashMap<Coord, Cell> worksheet;

  /**
   * Represents the default Constructor for a BeyondGoodWorkSheet, which establishes a HashMap of
   *     Coords to Cells.
   */
  public BeyondGoodWorksheet() {
    worksheet = new HashMap<>();
  }

  /**
   * Represents a constructor for BeyondGoodWorkSheet that takes in an already existent worksheet
   *    as a HashMap and returns a new WorkSheet using said input.
   * @param worksheet represents a previously existing worksheet as a HashMap of Coords to Cells.
   */
  public BeyondGoodWorksheet(HashMap<Coord, Cell> worksheet) {
    this.worksheet = worksheet;
  }

  @Override
  public Cell getCellAt(Coord coord) {
    return worksheet.get(coord);
  }

  @Override
  public void setCell(Coord coord, Cell cell) {
    if (!cyclicReference(coord, cell)) {
      worksheet.put(coord, cell);
    } else {
      throw new IllegalArgumentException("Cell contains a cylic reference.");
    }
  }

  /**
   * Takes in a location of a cell as a Coord and that cell as a Cell and returns a boolean value
   *      that represents whether or not that cell references itself.
   * @param location the location of the given cell in the WorkSheet.
   * @param cell The cell whose references are to be iterated through.
   * @return a boolean value whether the two Cells reference one another.
   */
  private boolean cyclicReference(Coord location, Cell cell) {
    boolean cyclic = false;
    for (Coord ref : cell.references()) {
      cyclic = cyclic || referenceBetween(location, ref);
    }
    // Isn't this redundant?
    return cell.cyclicReference(location) || cyclic;
  }

  /**
   * Evaluates two Coords for whether or not the Cells at those locations reference one another.
   * @param loc1 The coordinates of the first cell as a Coord.
   * @param loc2 The coordinates of the second cell as a Coord.
   * @return A boolean value whether the two Cells reference one another, either directly or
   *    indirectly.
   */
  private boolean referenceBetween(Coord loc1, Coord loc2) {
    boolean cyclic = false;
    for (Coord ref : getCellAt(loc2).references()) {
      cyclic = cyclic || referenceBetween(loc1, ref);
    }
    return cyclic;
  }
}