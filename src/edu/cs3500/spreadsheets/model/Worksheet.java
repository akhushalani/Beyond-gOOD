package edu.cs3500.spreadsheets.model;

/**
 * An interface representing worksheets which consist of Cells at various Coords.
 */
public interface Worksheet {
  Cell getCellAt(Coord coord);

  void setCell(Coord coord, Cell cell);
}
