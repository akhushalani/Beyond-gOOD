package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class BlankCell implements Cell {
  private ArrayList<Coord> directRefs;
  private Coord location;

  /**
   * Public constructor for BlankCell.
   *
   * @param location represents location in spreadsheet
   */
  public BlankCell(Coord location) {
    this.location = location;
    this.directRefs = new ArrayList<>();
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
