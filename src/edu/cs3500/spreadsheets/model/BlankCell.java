package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Cells which contain no value. These cells are a type of ValueCell.
 */
public class BlankCell extends AbstractCell {

  /**
   * Public constructor for BlankCell.
   *
   * @param location represents location in spreadsheet
   */
  public BlankCell(Coord location) {
    super(location);
  }

  /**
   * Public constructor for when cell is represented by BlankCell
   * in worksheet, or when cell has existing direct references.
   *
   * @param existingRefs ArrayList of direct references to existing BlankCell
   * @param location coordinates for where cell is to be created in spreadsheet
   */
  protected BlankCell(Coord location, ArrayList<Coord> existingRefs) {
    super(location, existingRefs);
  }
}
