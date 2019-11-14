package edu.cs3500.spreadsheets.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;

/**
 * A class for rendering the header cells in a JTable.
 */
public class HeaderRenderer implements TableCellRenderer {
  private final TableCellRenderer renderer;

  /**
   * Public constructor for HeaderRenderer.
   *
   * @param renderer the defalt renderer in the table
   */
  public HeaderRenderer(TableCellRenderer renderer) {
    this.renderer = renderer;
  }

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                 boolean hasFocus, int row, int column) {
    JComponent component = (JComponent) renderer.getTableCellRendererComponent(table, value,
            isSelected, hasFocus, row, column);
    component.setBorder(new LineBorder(Color.lightGray));
    component.setBackground(Color.gray);
    component.setForeground(Color.white);
    component.setFont(component.getFont().deriveFont(Font.BOLD));
    return component;
  }
}
