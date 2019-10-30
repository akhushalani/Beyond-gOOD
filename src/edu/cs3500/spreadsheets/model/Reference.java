package edu.cs3500.spreadsheets.model;

import java.util.HashMap;
import java.util.Objects;

/**
 * Represents a type of formula that is a reference to another Cell in a worksheet.
 */
public class Reference implements Formula {
  private Coord refLocation;

  /**
   * The constructor for a Reference that takes in a refLocation as a Coord, with that
   *    Coord representing the Cell to which the Reference should Reference.
   * @param refLocation
   */
  public Reference(Coord refLocation) {
    this.refLocation = refLocation;
  }

  @Override
  public Formula evaluate(HashMap<Coord, Cell> spreadsheet) {
    if (spreadsheet.get(refLocation).getFormula() == null) {
      return null;
    } else {
      return spreadsheet.get(refLocation).getFormula().evaluate(spreadsheet);
    }
  }

  @Override
  public String getPrintString(HashMap<Coord, Cell> worksheet) {
    return refLocation.toString();
  }

  @Override
  public ValueType getValueType() {
    return ValueType.NONE;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Reference)) {
      return false;
    }

    Reference that = (Reference) obj;
    return this.refLocation.equals(that.refLocation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(refLocation);
  }
}
