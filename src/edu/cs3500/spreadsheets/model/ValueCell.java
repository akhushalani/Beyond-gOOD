package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Abstract implementation of AbstractCell that represents cells that can hold a value.
 */
public abstract class ValueCell extends AbstractCell {
  /**
   * Public constructor for when cell is null in worksheet.
   *
   * @param location coordinates for where cell is to be created in spreadsheet
   */
  protected ValueCell(Coord location) {
    super(location);
  }

  /**
   * Public constructor for when cell is represented by BlankCell
   * in worksheet, i.e. when cell has existing direct references.
   *
   * @param existingRefs ArrayList of direct references to existing BlankCell
   * @param location coordinates fro where cell is to be created in spreadsheet
   */
  protected ValueCell(Coord location, ArrayList<Coord> existingRefs) {
    super(location, existingRefs);
  }
}
