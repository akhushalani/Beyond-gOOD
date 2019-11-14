package edu.cs3500.spreadsheets.view;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;

/**
 * A model for a JTable that implements infinite scrolling in both the horizontal
 * and vertical directions for a worksheet.
 */
public class InfiniteScrollingTableModel extends DefaultTableModel {
  private Worksheet worksheet;
  private int rowCount;
  private int colCount;

  /**
   * Public constructor for the InfiniteScrollingTableModel class.
   *
   * @param worksheet the worksheet being displayed in the table
   */
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

  /**
   * Adjusts the tables' number of cells to fill the frame it is in.
   *
   * @param colWidth width of a table column
   * @param rowHeight height of a table row
   * @param frameWidth width of the frame containing the table
   * @param frameHeight height of the frame containing the table
   */
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

  /**
   * To be called when the horizontal scrollbar in the view scrolls all the way to the right.
   */
  public void fireScrollRight() {
    int newColumnCount = getColumnCount() + 1;
    setColumnCount(newColumnCount);
    fireTableStructureChanged();
  }

  /**
   * To be called when the vertical scrollbar in the view scrolls all the way down.
   */
  public void fireScrollDown() {
    int newRowCount = getRowCount() + 1;
    setRowCount(newRowCount);
    fireTableStructureChanged();
  }
}
