package edu.cs3500.spreadsheets.model;

import java.util.HashMap;

public final class WorksheetAdapter {
  private final Worksheet worksheet;

  public WorksheetAdapter(Worksheet worksheet) {
    this.worksheet = worksheet;
  }

  /**
   * Takes in a coordinate and returns the Cell in the worksheet at that coordinate.
   * @param coord represents the coordinates of a Cell in the worksheet.
   * @return the Cell at the given Coord.
   */
  public Cell getCellAt(Coord coord) {
    return worksheet.getCellAt(coord);
  }

  /**
   * Retrieves the worksheet from the Worksheet as a HashMap.
   */
  public HashMap<Coord, Cell> getWorksheet() {
    return worksheet.getWorksheet();
  }

  /**
   * Gets the height of the worksheet.
   * @return the y-coordinate of the furthest down cell.
   */
  public int getHeight() {
    return worksheet.getHeight();
  }

  /**
   * Gets the width of the worksheet.
   * @return the x-coordinate of the furthest right cell.
   */
  public int getWidth() {
    return worksheet.getWidth();
  }

  public Worksheet getModel() {
    return this.worksheet;
  }
}
