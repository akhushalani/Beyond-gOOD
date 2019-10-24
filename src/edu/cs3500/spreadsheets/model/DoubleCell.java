package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Cells which contain a double value. These cells are a type of ValueCell.
 */
public class DoubleCell extends ValueCell {
  private double value;

  /**
   * Public constructor for when DoubleCell is null in worksheet.
   *
   * @param location coordinates fro where cell is to be created in spreadsheet
   * @param value double value of cell
   */
  public DoubleCell(Coord location, double value) {
    super(location);
    this.value = value;
  }

  /**
   * Public constructor for when DoubleCell is represented by BlankCell
   * in worksheet, i.e. when cell has existing direct references.
   *
   * @param existingRefs ArrayList of direct references to existing BlankCell
   * @param location coordinates fro where cell is to be created in spreadsheet
   * @param value double value of cell
   */
  public DoubleCell(Coord location, ArrayList existingRefs, double value) {
    super(location, existingRefs);
    this.value = value;
  }
}
