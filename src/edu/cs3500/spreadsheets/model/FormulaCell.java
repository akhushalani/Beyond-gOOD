package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Cells which contain a formula to be evaluated.
 */
public class FormulaCell extends AbstractCell {
  private Formula formula;

  /**
   * Public constructor for when cell is null in worksheet.
   *
   * @param location coordinates for where cell is to be created in spreadsheet
   * @param formula formula to be evaluated in cell
   */
  protected FormulaCell(Coord location, Formula formula) {
    super(location);
    this.formula = formula;
  }

  /**
   * Public constructor for when cell is represented by BlankCell
   * in worksheet, i.e. when cell has existing direct references.
   *
   * @param existingRefs ArrayList of direct references to existing BlankCell
   * @param location coordinates fro where cell is to be created in spreadsheet
   * @param formula formula to be evaluated in cell
   */
  protected FormulaCell(Coord location, ArrayList<Coord> existingRefs, Formula formula) {
    super(location, existingRefs);
    this.formula = formula;
  }
}
