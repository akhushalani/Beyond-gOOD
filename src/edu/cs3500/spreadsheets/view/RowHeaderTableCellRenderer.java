package edu.cs3500.spreadsheets.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.table.TableCellRenderer;

/**
 * A class for rendering cells in a RowHeaderTable.
 */
public class RowHeaderTableCellRenderer implements TableCellRenderer {
  private final TableCellRenderer renderer;

  /**
   * Public constructor for the RowHeaderTableCellRendererClass.
   *
   * @param renderer default TableCellRenderer in the class
   */
  public RowHeaderTableCellRenderer(TableCellRenderer renderer) {
    this.renderer = renderer;
  }

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                 boolean hasFocus, int row, int column) {
    JComponent component = (JComponent) renderer.getTableCellRendererComponent(table, value,
            isSelected, hasFocus, row, column);
    if (isSelected || hasFocus) {
      CompoundBorder b = BorderFactory.createCompoundBorder();
      InfiniteScrollingTableModel tableModel = (InfiniteScrollingTableModel) table.getModel();
      int top = 4;
      int left = 4;
      int bottom = 4;
      int right = 4;
      Color selectColor = new Color(0, 7, 224);

      if (row == tableModel.minSelectionRow()) {
        b = BorderFactory.createCompoundBorder(b,
                BorderFactory.createMatteBorder(2, 0, 0, 0, selectColor));
        top -= 2;
      }

      if (column == tableModel.minSelectionCol()) {
        b = BorderFactory.createCompoundBorder(b,
                BorderFactory.createMatteBorder(0, 2, 0, 0, selectColor));
        left -= 2;
      }

      if (row == tableModel.maxSelectionRow()) {
        b = BorderFactory.createCompoundBorder(b,
                BorderFactory.createMatteBorder(0, 0, 2, 0, selectColor));
        bottom -= 2;
      }

      if (column == tableModel.maxSelectionCol()) {
        b = BorderFactory.createCompoundBorder(b,
                BorderFactory.createMatteBorder(0, 0, 0, 2, selectColor));
        right -= 2;
      }

      if (row == tableModel.minSelectionRow()) {
        b = BorderFactory.createCompoundBorder(b,
                BorderFactory.createMatteBorder(1, 0, 0, 0, Color.WHITE));
        top -= 1;
      }

      if (column == tableModel.minSelectionCol()) {
        b = BorderFactory.createCompoundBorder(b,
                BorderFactory.createMatteBorder(0, 1, 0, 0, Color.WHITE));
        left -= 1;
      }

      if (row == tableModel.maxSelectionRow()) {
        b = BorderFactory.createCompoundBorder(b,
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        bottom -= 1;
      }

      if (column == tableModel.maxSelectionCol()) {
        b = BorderFactory.createCompoundBorder(b,
                BorderFactory.createMatteBorder(0, 0, 0, 1, Color.WHITE));
        right -= 1;
      }

      if (tableModel.maxSelectionCol() != tableModel.minSelectionCol()) {
        if (column != tableModel.maxSelectionCol()) {
          b = BorderFactory.createCompoundBorder(b,
                  BorderFactory.createMatteBorder(0, 0, 0, 2, Color.LIGHT_GRAY));
          right -= 1;
        }
      }

      if (tableModel.maxSelectionRow() != tableModel.minSelectionRow()) {
        if (row != tableModel.maxSelectionRow()) {
          b = BorderFactory.createCompoundBorder(b,
                  BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));
          bottom -= 1;
        }
      }

      b = BorderFactory.createCompoundBorder(b,
              BorderFactory.createEmptyBorder(top, left, bottom, right));

      component.setBorder(b);

      if (row == tableModel.getFirstSelection().row - 1
              && column == tableModel.getFirstSelection().col - 1) {
        component.setBackground(Color.WHITE);
      }
    } else {
      component.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    }
    return component;
  }
}
