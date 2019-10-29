package edu.cs3500.spreadsheets.model;

import java.util.HashMap;

public class BeyondGoodWorksheet implements Worksheet {
  private HashMap<Coord, Cell> worksheet;

  public BeyondGoodWorksheet() {
    worksheet = new HashMap<>();
  }

  @Override
  public Cell getCellAt(Coord coord) {
    return worksheet.get(coord);
  }

  @Override
  public void setCell(Coord coord, Cell cell) {
    if (!cyclicReference(coord, cell)) {
      worksheet.put(coord, cell);
    } else {
      throw new IllegalArgumentException("Cell contains a cylic reference.");
    }
  }

  private boolean cyclicReference(Coord location, Cell cell) {
    boolean cyclic = false;
    for (Coord ref : cell.references()) {
      cyclic = cyclic || referenceBetween(location, ref);
    }
    return cell.cyclicReference(location) || cyclic;
  }

  private boolean referenceBetween(Coord loc1, Coord loc2) {
    boolean cyclic = false;
    for (Coord ref : getCellAt(loc2).references()) {
      cyclic = cyclic || referenceBetween(loc1, ref);
    }
    return cyclic;
  }
}
