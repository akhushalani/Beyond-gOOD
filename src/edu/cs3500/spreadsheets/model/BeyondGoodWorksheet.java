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
    worksheet.put(coord, cell);
  }
}
