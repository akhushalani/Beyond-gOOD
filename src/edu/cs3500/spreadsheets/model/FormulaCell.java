package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Cells which contain a formula to be evaluated.
 */
public class FormulaCell implements Cell {
  private ArrayList<Coord> directRefs;
  private Coord location;
  private Formula formula;
  private String rawContents;
  private boolean cyclic;

  /**
   * Public constructor for when cell is null in worksheet.
   *
   * @param location coordinates for where cell is to be created in spreadsheet
   * @param formula formula to be evaluated in cell
   */
  public FormulaCell(Coord location, Formula formula, String rawContents) {
    this.location = location;
    this.directRefs = new ArrayList<>();
    this.formula = formula;
    this.rawContents = rawContents;
    this.cyclic = false;
  }

  /**
   * Public constructor for when cell is created from builder to check for cyclic references.
   *
   * @param location coordinates for where cell is to be created in spreadsheet
   * @param formula formula to be evaluated in cell
   */
  public FormulaCell(Coord location, Formula formula, String rawContents, Worksheet worksheet) {
    this.location = location;
    ReferenceFormulaVisitor refVisitor = new ReferenceFormulaVisitor(worksheet, this.location);
    this.directRefs = formula.accept(refVisitor);
    this.formula = formula;
    this.rawContents = rawContents;
    this.cyclic = false;
  }

  /**
   * Public constructor for when cell is represented by Cell
   * in worksheet, i.e. when cell has existing direct references.
   *
   * @param existingRefs ArrayList of direct references to existing Cell
   * @param location coordinates fro where cell is to be created in spreadsheet
   * @param formula formula to be evaluated in cell
   */
  public FormulaCell(Coord location, ArrayList<Coord> existingRefs, Formula formula,
                     String rawContents) {
    this.location = location;
    if (existingRefs.contains(this.location)) {
      this.cyclic = true;
      this.directRefs = new ArrayList<>();
    } else {
      this.cyclic = false;
      this.directRefs = existingRefs;
    }
    this.formula = formula;
    this.rawContents = rawContents;
  }

  @Override
  public String evaluate(Worksheet worksheet, boolean clean) {
    worksheet.clearCalculatedReferences();
    String output = "";
    if (cyclic) {
      output += "ERROR: Cell contains a cyclic reference.";
    } else {
      try {
        output += this.formula.evaluate(worksheet, this.location)
                .getPrintString(worksheet, this.location, clean);
      } catch (IllegalArgumentException ex) {
        int colonIndex = ex.toString().indexOf(":");
        String errorMsg = ex.toString().substring(colonIndex);
        //output += "Error in cell " + this.location.toString() + errorMsg;
        output += "ERROR" + errorMsg;
      }
    }
    return output;
  }

  @Override
  public Formula getFormula() {
    return this.formula;
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
  public String getRawContents() {
    return this.rawContents;
  }

  @Override
  public boolean cyclicReference(Coord location) {
    return directRefs.contains(location);
  }

  @Override
  public boolean directCyclicReference() {
    return cyclic;
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
