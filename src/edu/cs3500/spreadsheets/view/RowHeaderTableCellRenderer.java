package edu.cs3500.spreadsheets.view;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
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
    component.setBorder(BorderFactory.createEmptyBorder(0,4,0,4));
    return component;
  }
}
