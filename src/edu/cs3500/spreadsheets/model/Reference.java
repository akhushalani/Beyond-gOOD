package edu.cs3500.spreadsheets.model;

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
  public Formula evaluate(Worksheet worksheet) {
    if (worksheet.getWorksheet().get(refLocation).getFormula() == null) {
      return null;
    } else if (!worksheet.hasCalculatedReference(refLocation)) {
      worksheet.addCalculatedReference(refLocation,
              worksheet.getWorksheet().get(refLocation).getFormula().evaluate(worksheet));
      return worksheet.getCalculatedReference(refLocation);
    } else {
      return worksheet.getCalculatedReference(refLocation);
    }
  }

  @Override
  public String getPrintString(Worksheet worksheet) {
    return refLocation.toString();
  }

  @Override
  public ValueType getValueType() {
    return ValueType.NONE;
  }

  @Override
  public <R> R accept(FormulaVisitor<R> visitor) {
    return visitor.visitReference(this);
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
