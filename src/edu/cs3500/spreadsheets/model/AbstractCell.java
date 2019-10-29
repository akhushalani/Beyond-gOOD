package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Abstract implementation of Cell. Contains common fields and methods for its extending classes.
 */
public abstract class AbstractCell implements Cell {
  protected ArrayList<Coord> directRefs;
  protected Coord location;

  /**
   * Public constructor for when cell is null in worksheet.
   *
   * @param location coordinates for where cell is to be created in spreadsheet
   */
  protected AbstractCell(Coord location) {
    this.location = location;
    this.directRefs = new ArrayList<>();
  }

  /**
   * Public constructor for when cell is represented by BlankCell
   * in worksheet, i.e. when cell contains existing direct references.
   *
   * @param existingRefs ArrayList of direct references
   * @param location coordinates fro where cell is to be created in spreadsheet
   */
  protected AbstractCell(Coord location, ArrayList<Coord> existingRefs) {
    this.location = location;
    this.directRefs = existingRefs;
  }

  @Override
  public ArrayList<Coord> references() {
    return directRefs;
  }

  @Override
  public String getCellName() {
    return location.toString();
  }

  @Override
  public boolean cyclicReference(Coord location) {
    return directRefs.contains(location);
  }
}
