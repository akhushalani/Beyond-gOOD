package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public abstract class ValueCell implements Cell {
  protected ArrayList<Coord> directRefs;
  protected Coord location;

  /**
   * Public constructor for when cell is null in worksheet.
   *
   * @param location coordinates fro where cell is to be created in spreadsheet
   */
  protected ValueCell(Coord location) {
    this.directRefs = new ArrayList<>();
    this.location = location;
  }

  /**
   * Public constructor for when cell is represented by BlankCell
   * in worksheet, i.e. when cell has existing direct references.
   *
   * @param existingRefs ArrayList of direct references to existing BlankCell
   * @param location coordinates fro where cell is to be created in spreadsheet
   */
  protected ValueCell(Coord location, ArrayList<Coord> existingRefs) {
    this.directRefs = existingRefs;
    this.location = location;
  }

  @Override
  public ArrayList referencedBy() {
    return directRefs;
  }

  @Override
  public String getCellName() {
    return location.toString();
  }
}
