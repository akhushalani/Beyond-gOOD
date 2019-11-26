package edu.cs3500.spreadsheets.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetAdapter;

/**
 * A JPanel for viewing a worksheet.
 */
public class WorksheetPanel extends JPanel {
  private JScrollPane scrollPane;
  private JTextField editField;
  private JTextField coordDisplay;
  private InfiniteScrollingTableModel tableModel;
  private RowHeaderTable table;

  /**
   * A public constructor for a WorksheetPanel.
   * @param model a worksheet model adapter
   * @param frame the frame in which the panel is displayed
   * @param editable whether the worksheet is editable
   */
  public WorksheetPanel(WorksheetAdapter model, JFrame frame, boolean editable) {
    this.editField = null;
    this.coordDisplay = null;

    tableModel = new InfiniteScrollingTableModel(model);

    table = new RowHeaderTable(tableModel, false, editable);
    scrollPane = new JScrollPane(table,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    table.setColumnSelectionAllowed(true);
    table.setRowSelectionAllowed(true);

    table.setIntercellSpacing(new Dimension(-1, -1));

    Color bg = new Color(50, 50, 50);
    scrollPane.setBorder(BorderFactory.createEmptyBorder());
    table.setBorder(BorderFactory.createEmptyBorder());
    table.getTableHeader().setBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, bg));
    scrollPane.setBackground(bg);
    scrollPane.getVerticalScrollBar()
            .setBorder(BorderFactory.createMatteBorder(-1, 0, -60, 0, Color.lightGray));
    scrollPane.getHorizontalScrollBar()
            .setBorder(BorderFactory.createMatteBorder(0, -1, 0, -60, Color.lightGray));

    table.setGridColor(Color.lightGray);
    table.setSelectionBackground(new Color(222, 222, 222));
    table.setSelectionForeground(Color.BLACK);

    DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
    headerRenderer.setHorizontalAlignment(JLabel.CENTER);
    table.getTableHeader().setDefaultRenderer(new HeaderRenderer(headerRenderer, tableModel));

    TableCellRenderer cellRenderer = table.getDefaultRenderer(Object.class);
    table.setDefaultRenderer(Object.class, new RowHeaderTableCellRenderer(cellRenderer));

    ScrollableRowHeaderTable scrollableTable = new ScrollableRowHeaderTable(scrollPane);

    scrollableTable.getRowHeader().getTableHeader()
            .setDefaultRenderer(new HeaderRenderer(headerRenderer, tableModel));
    scrollableTable.getRowHeader().setGridColor(Color.lightGray);
    scrollableTable.getRowHeader().setIntercellSpacing(new Dimension(-1, -1));

    ((InfiniteScrollingTableModel) scrollableTable.getTable().getModel())
            .adjustToFrame(table.getColumnModel().getColumn(1).getWidth(),
                    table.getRowHeight(), 1000, 1000);

    frame.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        tableModel.adjustToFrame(table.getColumnModel().getColumn(1).getWidth(),
                table.getRowHeight(), frame.getWidth(), frame.getHeight());
      }
    });

    ListSelectionListener selectionListener = new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        int[] selectedRows = table.getSelectedRows();
        int[] selectedColumns = table.getSelectedColumns();
        InfiniteScrollingTableModel scrollingTableModel
                = (InfiniteScrollingTableModel) scrollableTable.getTable().getModel();

        if (selectedRows.length > 0 && selectedColumns.length > 0) {
          scrollingTableModel.clearSelected();
          for (int selectedRow : selectedRows) {
            for (int selectedColumn : selectedColumns) {
              Coord c = new Coord(selectedColumn + 1, selectedRow + 1);
              scrollingTableModel.addSelected(c);
            }

            String coordDisplayText = (scrollingTableModel.maxSelectionRow()
                    - scrollingTableModel.minSelectionRow() + 1) + "R x "
                    + (scrollingTableModel.maxSelectionCol()
                    - scrollingTableModel.minSelectionCol() + 1) + "C";

            if (selectedRows.length == 1 && selectedColumns.length == 1) {
              Coord c = new Coord(selectedColumns[0] + 1, selectedRows[0] + 1);

              if (editField != null) {
                String editFieldText = "";

                if (model.getWorksheet().containsKey(c)) {
                  editFieldText += model.getCellAt(c).getRawContents();
                }
                editField.setText(editFieldText);
              }

              if (coordDisplay != null) {
                coordDisplayText = c.toString();
              }

              tableModel.setFirstSelection(c);
            }

            if (coordDisplay != null) {
              coordDisplay.setText(coordDisplayText);
            }
          }
        } else {
          ((InfiniteScrollingTableModel) scrollableTable.getTable().getModel()).clearSelected();
          if (editField != null) {
            editField.setText("");
          }

          if (coordDisplay != null) {
            coordDisplay.setText("");
          }
        }

        scrollableTable.getTable().getTableHeader().repaint();
        scrollableTable.getRowHeader().repaint();
      }
    };

    scrollPane.getHorizontalScrollBar().addAdjustmentListener(e -> {
      int horizExtent = scrollPane.getHorizontalScrollBar().getModel().getExtent();
      if (e.getValueIsAdjusting()
              && e.getValue() == scrollPane.getHorizontalScrollBar().getMaximum() - horizExtent) {
        tableModel.fireScrollRight();
      }
    });

    scrollPane.getVerticalScrollBar().addAdjustmentListener(e -> {
      int vertExtent = scrollPane.getVerticalScrollBar().getModel().getExtent();
      if (e.getValueIsAdjusting()
              && e.getValue() == scrollPane.getVerticalScrollBar().getMaximum() - vertExtent) {
        tableModel.fireScrollDown();
      }
    });

    frame.addMouseWheelListener(e -> {
      int notches = e.getWheelRotation();
      if (notches > 0 && !e.isShiftDown()) {
        tableModel.fireScrollDown();
      } else if (notches > 0) {
        tableModel.fireScrollRight();
      }
    });

    scrollableTable.getTable().setDefaultEditor(Object.class, WorksheetCellEditor.make(model));

    scrollableTable.getTable().getColumnModel().getSelectionModel()
            .addListSelectionListener(selectionListener);
    scrollableTable.getTable().getSelectionModel().addListSelectionListener(selectionListener);
  }

  /**
   * Gets the scroll pane containing the table.
   * @return the scroll pane
   */
  public JScrollPane getScrollPane() {
    return scrollPane;
  }

  /**
   * Attaches an edit field to the worksheet panel.
   * @param editField the edit field to attach
   */
  public void attachEditField(JTextField editField) {
    this.editField = editField;
  }

  /**
   * Attaches a coord display to the worksheet panel.
   * @param coordDisplay the coord display to attach
   */
  public void attachCoordDisplay(JTextField coordDisplay) {
    this.coordDisplay = coordDisplay;
  }

  /**
   * Gets the table model for the worksheet table.
   * @return the table model
   */
  public InfiniteScrollingTableModel getTableModel() {
    return tableModel;
  }

  /**
   * Gets the worksheet table.
   * @return the worksheet table
   */
  public JTable getTable() {
    return table;
  }
}
