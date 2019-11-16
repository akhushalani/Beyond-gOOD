package edu.cs3500.spreadsheets.view;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetAdapter;

/**
 * A model for a JTable that implements infinite scrolling in both the horizontal
 * and vertical directions for a worksheet.
 */
public class InfiniteScrollingTableModel extends DefaultTableModel {
  private WorksheetAdapter worksheet;
  private int rowCount;
  private int colCount;
  private ArrayList<Coord> selected;
  private Coord firstSelection;

  /**
   * Public constructor for the InfiniteScrollingTableModel class.
   *
   * @param worksheet the worksheet being displayed in the table
   */
  public InfiniteScrollingTableModel(WorksheetAdapter worksheet) {
    this.worksheet = worksheet;
    colCount = this.worksheet.getWidth() + 1;
    rowCount = this.worksheet.getHeight();
    selected = new ArrayList<>();
    firstSelection = null;
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

  /**
   * Adds a coordinate to the list of those selected in the table.
   *
   * @param coord the coordinate that is selected
   */
  public void addSelected(Coord coord) {
    if (!selected.contains(coord)) {
      selected.add(coord);
      fireTableCellUpdated(coord.row - 1, coord.col - 1);
    }
  }

  /**
   * Gets the list of coordinates selected in the table.
   *
   * @return the list of coordinates
   */
  public ArrayList<Coord> getSelected() {
    return selected;
  }

  /**
   * Clears the list of selected coordinates in the table.
   */
  public void clearSelected() {
    ArrayList<Coord> selectedCopy = (ArrayList<Coord>) selected.clone();
    for (int i = 0; i < selectedCopy.size(); i++) {
      Coord c = selectedCopy.get(i);
      selected.remove(c);
      fireTableCellUpdated(c.row - 1, c.col - 1);
    }
    selected.clear();
  }

  public int minSelectionCol() {
    if (selected.size() == 0) {
      return -1;
    } else {
      int min = selected.get(0).col;
      if (selected.size() > 1) {
        for (int i = 0; i < selected.size(); i++) {
          min = Math.min(min, selected.get(i).col);
        }
      }

      return min - 1;
    }
  }

  public int maxSelectionCol() {
    if (selected.size() == 0) {
      return -1;
    } else {
      int max = selected.get(0).col;
      if (selected.size() > 1) {
        for (int i = 0; i < selected.size(); i++) {
          max = Math.max(max, selected.get(i).col);
        }
      }

      return max - 1;
    }
  }

  public int minSelectionRow() {
    if (selected.size() == 0) {
      return -1;
    } else {
      int min = selected.get(0).row;
      if (selected.size() > 1) {
        for (int i = 0; i < selected.size(); i++) {
          min = Math.min(min, selected.get(i).row);
        }
      }

      return min - 1;
    }
  }

  public int maxSelectionRow() {
    if (selected.size() == 0) {
      return -1;
    } else {
      int max = selected.get(0).row;
      if (selected.size() > 1) {
        for (int i = 0; i < selected.size(); i++) {
          max = Math.max(max, selected.get(i).row);
        }
      }

      return max - 1;
    }
  }

  public void setFirstSelection(Coord firstSelection) {
    this.firstSelection = firstSelection;
  }

  public Coord getFirstSelection() {
    return firstSelection;
  }

  public void select(JTable table) {
    table.getSelectionModel().setSelectionInterval(minSelectionRow(), maxSelectionRow());
    table.getColumnModel().getSelectionModel()
            .setSelectionInterval(minSelectionCol(), maxSelectionCol());
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    HashMap<Coord, Cell> data = worksheet.getWorksheet();

    Coord coord = new Coord(columnIndex + 1, rowIndex + 1);
    if (data.containsKey(coord)) {
      if (selected.contains(coord) && selected.size() == 1) {
        return worksheet.getCellAt(coord).getRawContents();
      } else {
        return worksheet.getCellAt(coord).evaluate(worksheet.getModel());
      }
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

  public void fireScrollLeft() {
    int newColumnCount = getColumnCount() - 1;
    setColumnCount(Math.max(getColumnCount(), newColumnCount));
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

  public void fireScrollUp() {
    int newRowCount = getRowCount() - 1;
    setRowCount(Math.max(getRowCount(), newRowCount));
    fireTableStructureChanged();
  }

  @Override
  public void setValueAt(Object aValue, int row, int column) {
    super.setValueAt(aValue, row, column);
  }
}
