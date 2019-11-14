package edu.cs3500.spreadsheets.view;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 * A table that has a row header.
 */
public class RowHeaderTable extends JTable {
  private boolean headerTable;

  /**
   * Public constructor for the RowHeaderTableClass.
   *
   * @param tableModel the default model of the table
   * @param headerTable is the table the row header table
   */
  public RowHeaderTable(TableModel tableModel, boolean headerTable) {
    super(tableModel);
    this.headerTable = headerTable;
  }

  @Override
  public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
    if (headerTable) {
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
