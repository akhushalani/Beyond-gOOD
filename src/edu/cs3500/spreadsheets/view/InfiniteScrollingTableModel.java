package edu.cs3500.spreadsheets.view;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;

public class InfiniteScrollingTableModel extends DefaultTableModel {
  private Worksheet worksheet;
  private int rowCount;
  private int colCount;

  public InfiniteScrollingTableModel(Worksheet worksheet) {
    this.worksheet = worksheet;
    colCount = this.worksheet.getWidth() + 1;
    rowCount = this.worksheet.getHeight();
  }

  @Override
  public int getRowCount() {
    return rowCount;
  }

  @Override
  public int getColumnCount() {
    return colCount;
  }

  @Override
  public void setRowCount(int rowCount) {
    this.rowCount = rowCount;
  }

  @Override
  public void setColumnCount(int colCount) {
    this.colCount = colCount;
  }

  public void adjustToFrame(int colWidth, int rowHeight, int frameWidth, int frameHeight) {
    int newColCount = (frameWidth / colWidth) + 1;
    if (newColCount > worksheet.getWidth() + 1) {
      setColumnCount(newColCount);
    } else {
      setColumnCount(worksheet.getWidth() + 1);
    }

    int newRowCount = (frameHeight / rowHeight) + 1;
    if (newRowCount > worksheet.getHeight()) {
      setRowCount(newRowCount);
    } else {
      setRowCount(worksheet.getHeight());
    }
    fireTableStructureChanged();
  }

  @Override
  public String getColumnName(int columnIndex) {
    return Coord.colIndexToName(columnIndex + 1);
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    HashMap<Coord, Cell> data = worksheet.getWorksheet();

    if (data.containsKey(new Coord(columnIndex + 1, rowIndex + 1))) {
      return worksheet.getCellAt(new Coord(columnIndex + 1, rowIndex + 1)).evaluate(worksheet);
    } else {
      return "";
    }
  }

  public void fireScrollRight() {
    int newColumnCount = getColumnCount() + 1;
    setColumnCount(newColumnCount);
    fireTableStructureChanged();
  }

  public void fireScrollDown() {
    int newRowCount = getRowCount() + 1;
    setRowCount(newRowCount);
    fireTableStructureChanged();
  }
}
