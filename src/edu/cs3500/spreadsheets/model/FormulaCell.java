package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

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
  public FormulaCell(Coord location, Formula formula) {
    super(location);
    this.formula = formula;
  }

  /**
   * Public constructor for when cell is represented by Cell
   * in worksheet, i.e. when cell has existing direct references.
   *
   * @param existingRefs ArrayList of direct references to existing Cell
   * @param location coordinates fro where cell is to be created in spreadsheet
   * @param formula formula to be evaluated in cell
   */
  public FormulaCell(Coord location, ArrayList<Coord> existingRefs, Formula formula) {
    super(location, existingRefs);
    this.formula = formula;
  }

  @Override
  public String evaluate(HashMap<Coord, Cell> worksheet) {
    return this.formula.evaluate(worksheet).getPrintString(worksheet);
  }

  @Override
  public Formula getFormula() {
    return this.formula;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof FormulaCell)) {
      return false;
    }

    FormulaCell that = (FormulaCell) obj;
    return this.formula.equals(that.formula);
  }

  @Override
  public int hashCode() {
    return Objects.hash(formula);
  }
}
