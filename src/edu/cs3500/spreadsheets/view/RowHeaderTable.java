package edu.cs3500.spreadsheets.view;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 * A table that has a row header.
 */
public class RowHeaderTable extends JTable {
  private boolean headerTable;
  private boolean editable;
  private TableModel tableModel;

  /**
   * Public constructor for the RowHeaderTableClass.
   *
   * @param tableModel the default model of the table
   * @param headerTable is the table the row header table
   */
  public RowHeaderTable(TableModel tableModel, boolean headerTable, boolean editable) {
    super(tableModel);
    this.headerTable = headerTable;
    this.editable = editable;
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
  public String getToolTipText(MouseEvent event) {
    String toolTipText = null;
    int row = rowAtPoint( event.getPoint() );
    int col = columnAtPoint( event.getPoint() );

    Rectangle bounds = getCellRect(row, col, false);

    Object value = getValueAt(row, col);
    Component comp = prepareRenderer(getCellRenderer(row, col), row, col);
    if (comp.getPreferredSize().width > bounds.width) {
      toolTipText = value == null ? null : value.toString();
    }

    return toolTipText;
  }

  @Override
  public boolean isCellEditable(int rowIndex, int vColIndex) {
    //return false;

    if (tableModel == null) {
      return editable;
    } else {
      InfiniteScrollingTableModel model = (InfiniteScrollingTableModel) tableModel;
      if (model.minSelectionCol() == -1 || model.minSelectionRow() == -1) {
        return editable;
      } else {
        return editable
                && rowIndex == model.getFirstSelection().row - 1
                && vColIndex == model.getFirstSelection().col - 1;
      }
    }

  }
}
