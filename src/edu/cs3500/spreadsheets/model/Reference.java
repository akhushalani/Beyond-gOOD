package edu.cs3500.spreadsheets.model;

import java.util.HashMap;

public class Reference implements Formula {
  private Coord refLocation;

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
  public String getPrintString() {
    return refLocation.toString();
  }
}
