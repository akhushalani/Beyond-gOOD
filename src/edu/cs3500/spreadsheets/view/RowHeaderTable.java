package edu.cs3500.spreadsheets.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class RowHeaderTable extends JTable {
  public RowHeaderTable(Object[][] rowData, Object[] colData) {
    super(rowData, colData);
  }

  @Override
  public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
    if (column == 0) {
      return this.getTableHeader().getDefaultRenderer()
              .getTableCellRendererComponent(this, this.getValueAt(row, column),
                      false, false, row, column);
    } else {
      return super.prepareRenderer(renderer, row, column);
    }
  }

  @Override
  public boolean isCellEditable(int rowIndex, int vColIndex) {
    return false;
  }
}
