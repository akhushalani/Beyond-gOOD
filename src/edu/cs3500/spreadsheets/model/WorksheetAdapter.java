package edu.cs3500.spreadsheets.model;

import java.awt.Color;
import java.util.HashMap;

/**
 * An immutable adapter class for passing the worksheet model to views.
 */
public final class WorksheetAdapter {
  private final Worksheet worksheet;

  /**
   * Public constructor for the WorksheetAdapter.
   * @param worksheet the model being wrapped
   */
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

  /**
   * Toggles an attribute for the selected cells.
   * @param attribute the attribute to toggle.
   */
  public void toggleAttribute(CellAttribute attribute, Coord coord) {
    worksheet.toggleAttribute(attribute, coord);
  }

  /**
   * Sets the value of a color attribute for the selected cells.
   * @param attribute the color attribute to set
   * @param color the color value to assign to the cells
   */
  public void setColor(CellAttribute attribute, Color color, Coord coord) {
    worksheet.setColor(attribute, color, coord);
  }

  /**
   * Gets the attributes of a cell in the worksheet.
   * @return a map from the coordinates in the worksheet to their attributes
   */
  public CellAttributes getAttributeSet(Coord coord) {
    return worksheet.getAttributeSet(coord);
  }

  /**
   * Gets the attributes of the cells in the worksheet.
   * @return a map from the coordinates in the worksheet to their attributes
   */
  public HashMap<Coord, CellAttributes> getAttributes() {
    return worksheet.getAttributes();
  }

  /**
   * Sets the attributes of a cell in the worksheet.
   */
  public void setAttributes(Coord coord, CellAttributes attributeSet) {
    worksheet.setAttributes(coord, attributeSet);
  }

  public Worksheet getModel() {
    return this.worksheet;
  }
}
