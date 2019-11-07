package edu.cs3500.spreadsheets.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;

public class RowHeaderTableCellRenderer implements TableCellRenderer {
  private final TableCellRenderer renderer;

  public RowHeaderTableCellRenderer(TableCellRenderer renderer) {
    this.renderer = renderer;
  }

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                 boolean hasFocus, int row, int column) {
    JComponent component = (JComponent) renderer.getTableCellRendererComponent(table, value,
            isSelected, hasFocus, row, column);
    return component;
  }
}
