package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class BooleanCell extends ValueCell {
  private boolean value;

  /**
   * Public constructor for when BooleanCell is null in worksheet.
   *
   * @param location coordinates fro where cell is to be created in spreadsheet
   * @param value boolean value of cell
   */
  public BooleanCell(Coord location, boolean value) {
    super(location);
    this.value = value;
  }

  /**
   * Public constructor for when BooleanCell is represented by BlankCell
   * in worksheet, i.e. when cell has existing direct references.
   *
   * @param existingRefs ArrayList of direct references to existing BlankCell
   * @param location coordinates fro where cell is to be created in spreadsheet
   * @param value boolean value of cell
   */
  public BooleanCell(Coord location, ArrayList existingRefs, boolean value) {
    super(location, existingRefs);
    this.value = value;
  }
}
