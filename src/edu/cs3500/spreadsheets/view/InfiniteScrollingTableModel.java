package edu.cs3500.spreadsheets.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.CellAttribute;
import edu.cs3500.spreadsheets.model.CellAttributes;
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

    for (Map.Entry<Coord, Cell> entry : worksheet.getWorksheet().entrySet()) {
      worksheet.setAttributes(entry.getKey(), new CellAttributes());
      try {
        Double value = Double.parseDouble(worksheet.getCellAt(entry.getKey())
                .evaluate(worksheet.getModel(), false));
        worksheet.getAttributeSet(entry.getKey()).setAlignment(CellAttributes.RIGHT);
      } catch (NumberFormatException | NullPointerException ignored) {

      }
    }
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
   * Toggles an attribute for the selected cells.
   * @param attribute the attribute to toggle.
   */
  public void toggleAttribute(CellAttribute attribute) {
    worksheet.toggleAttribute(attribute, firstSelection);

    for (Coord selection : selected) {
      if (worksheet.getAttributeSet(selection) == null) {
        worksheet.setAttributes(selection, new CellAttributes());
      }
      worksheet.getAttributeSet(selection)
              .useAttributes(worksheet.getAttributeSet(firstSelection), attribute);
      fireTableCellUpdated(selection.row - 1, selection.col - 1);
    }
  }

  /**
   * Sets the value of a color attribute for the selected cells.
   * @param attribute the color attribute to set
   * @param color the color value to assign to the cells
   */
  public void setColor(CellAttribute attribute, Color color) {
    for (Coord selection : selected) {
      worksheet.getAttributeSet(selection).setTextColor(color);
      fireTableCellUpdated(selection.row - 1, selection.col - 1);
    }
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

  /**
   * Gets the leftmost selected column index.
   * @return the minimum selected column index
   */
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

  /**
   * Gets the rightmost selected column index.
   * @return the maximum selected column index
   */
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

  /**
   * Gets the top selected row index.
   * @return the minimum selected row index
   */
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

  /**
   * Gets the bottom selected row index.
   * @return the maximum selected row index
   */
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

  /**
   * Sets which cell in the current selection was selected first.
   * @param firstSelection the location of the first selection
   */
  public void setFirstSelection(Coord firstSelection) {
    this.firstSelection = firstSelection;
  }

  /**
   * Gets the cell that was selected first in the current selection.
   * @return the location of the first selection
   */
  public Coord getFirstSelection() {
    return firstSelection;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    HashMap<Coord, Cell> data = worksheet.getWorksheet();

    Coord coord = new Coord(columnIndex + 1, rowIndex + 1);
    if (data.containsKey(coord)) {
      return worksheet.getCellAt(coord).evaluate(worksheet.getModel(), true);
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

  @Override
  public void setValueAt(Object aValue, int row, int column) {
    // disallows cell editing from the editor
  }

  /**
   * Notifies the table model that a cell's data has been updated.
   * @param c the location of the updated cell
   */
  public void cellUpdated(Coord c) {
    if (!worksheet.getAttributes().containsKey(c)) {
      worksheet.setAttributes(c, new CellAttributes());
    }
    try {
      Double value = Double.parseDouble(worksheet.getCellAt(c)
              .evaluate(worksheet.getModel(), false));
      worksheet.getAttributeSet(c).setAlignment(CellAttributes.RIGHT);
    } catch (NumberFormatException | NullPointerException ignored) {

    }
  }
}
