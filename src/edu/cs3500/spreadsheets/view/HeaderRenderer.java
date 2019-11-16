package edu.cs3500.spreadsheets.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * A class for rendering the header cells in a JTable.
 */
public class HeaderRenderer implements TableCellRenderer {
  private final TableCellRenderer renderer;
  private InfiniteScrollingTableModel tableModel;

  /**
   * Public constructor for HeaderRenderer.
   *
   * @param renderer the default renderer in the table
   */
  public HeaderRenderer(TableCellRenderer renderer,
                        InfiniteScrollingTableModel tableModel) {
    this.renderer = renderer;
    this.tableModel = tableModel;
  }

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                 boolean hasFocus, int row, int column) {
    JComponent component = (JComponent) renderer.getTableCellRendererComponent(table, value,
            isSelected, hasFocus, row, column);
    Color bg = new Color(50, 50, 50);
    if (column == 0 && row != -1) {
      component.setBorder(BorderFactory.createCompoundBorder(
              BorderFactory.createMatteBorder(0, 0, 2, 2, Color.LIGHT_GRAY),
              BorderFactory.createMatteBorder(2, 0, 2, 0, bg)));
    } else {
      component.setBorder(BorderFactory.createCompoundBorder(
              BorderFactory.createMatteBorder(0, 0, 1, 1, Color.LIGHT_GRAY),
              BorderFactory.createMatteBorder(2, 0, 2, 0, bg)));
    }

    Color selectColor = new Color(0, 7, 224);
    if ((row == -1
            && column <= tableModel.maxSelectionCol()
            && column >= tableModel.minSelectionCol())) {
      component.setBackground(Color.GRAY);
      component.setBorder(
              BorderFactory.createCompoundBorder(
                      BorderFactory.createMatteBorder(0, 0, 2, 0, selectColor),
                      BorderFactory.createMatteBorder(0, 1, 0, 1, Color.GRAY)));
    } else if (column == 0
            && row != -1
            && row <= tableModel.maxSelectionRow()
            && row >= tableModel.minSelectionRow()) {
      component.setBackground(Color.GRAY);
      component.setBorder(
              BorderFactory.createCompoundBorder(
                      BorderFactory.createMatteBorder(0, 0, 0, 3, selectColor),
                      BorderFactory.createMatteBorder(0, 1, 2, 0, Color.GRAY)));
    } else if (column == 0 && row != -1 && row == tableModel.minSelectionRow() - 1) {
      component.setBorder(BorderFactory.createCompoundBorder(
              BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY),
              BorderFactory.createCompoundBorder(
              BorderFactory.createMatteBorder(0, 0, 0, 2, Color.LIGHT_GRAY),
              BorderFactory.createMatteBorder(2, 0, 2, 0, bg))));
    } else if (row == -1 && column == tableModel.minSelectionCol() - 1) {
      component.setBorder(BorderFactory.createCompoundBorder(
              BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY),
              BorderFactory.createCompoundBorder(
              BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
              BorderFactory.createMatteBorder(2, 0, 2, 0, bg))));
    } else {
      component.setBackground(bg);
    }

    component.setForeground(Color.white);
    return component;
  }
}
