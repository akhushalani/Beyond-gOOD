package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class StringCell extends ValueCell {
  private String value;

  /**
   * Public constructor for when StringCell is null in worksheet.
   *
   * @param location coordinates for where cell is to be created in spreadsheet
   * @param value String value of cell
   */
  public StringCell(Coord location, String value) {
    super(location);
    this.value = value;
  }

  /**
   * Public constructor for when StringCell is represented by BlankCell
   * in worksheet, i.e. when cell has existing direct references.
   *
   * @param existingRefs ArrayList of direct references to existing BlankCell
   * @param location coordinates fro where cell is to be created in spreadsheet
   * @param value String value of cell
   */
  public StringCell(Coord location, ArrayList existingRefs, String value) {
    super(location, existingRefs);
    this.value = value;
  }
}
