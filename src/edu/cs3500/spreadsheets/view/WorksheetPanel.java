package edu.cs3500.spreadsheets.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentAdapter;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
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
  private ScrollableRowHeaderTable scrollableTable;
  private WorksheetCellEditor cellEditor;
  private int defaultColumnWidth;
  private JFrame frame;

  /**
   * A public constructor for a WorksheetPanel.
   * @param model a worksheet model adapter
   * @param frame the frame in which the panel is displayed
   * @param editable whether the worksheet is editable
   */
  public WorksheetPanel(WorksheetAdapter model, JFrame frame, boolean editable) {
    this.editField = null;
    this.coordDisplay = null;
    this.frame = frame;

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

    defaultColumnWidth = table.getColumnModel().getColumn(0).getWidth();

    TableCellRenderer cellRenderer = table.getDefaultRenderer(Object.class);
    table.setDefaultRenderer(Object.class, new RowHeaderTableCellRenderer(cellRenderer, model));

    scrollableTable = new ScrollableRowHeaderTable(scrollPane);

    scrollableTable.getRowHeader().getTableHeader()
            .setDefaultRenderer(new HeaderRenderer(headerRenderer, tableModel));
    scrollableTable.getRowHeader().setGridColor(Color.lightGray);
    scrollableTable.getRowHeader().setIntercellSpacing(new Dimension(-1, -1));

    if (!model.getWorksheet().isEmpty()) {
      ((InfiniteScrollingTableModel) scrollableTable.getTable().getModel())
              .adjustToFrame(table.getColumnModel().getColumn(1).getWidth(),
                      table.getRowHeight(), 1000, 1000);
    } else {
      ((InfiniteScrollingTableModel) scrollableTable.getTable().getModel())
              .adjustToFrame(30,
                      table.getRowHeight(), 1000, 1000);
    }

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

    cellEditor = WorksheetCellEditor.make(model);
    scrollableTable.getTable().setDefaultEditor(Object.class, cellEditor);

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
  public RowHeaderTable getTable() {
    return table;
  }

  public RowHeaderTable getRowHeader() {
    return scrollableTable.getRowHeader();
  }

  public int getMinRowHeight() {
    return scrollableTable.getMinRowHeight();
  }

  /**
   * Gets the worksheet cell editor component.
   * @return the cell editor
   */
  public WorksheetCellEditor getCellEditor() {
    return cellEditor;
  }

  public int getDefaultColumnWidth() {
    return defaultColumnWidth;
  }

  public void addScrollListeners(AdjustmentListener horizListener,
                                 AdjustmentListener vertListener) {
    scrollPane.getHorizontalScrollBar().addAdjustmentListener(horizListener);
    scrollPane.getVerticalScrollBar().addAdjustmentListener(vertListener);
  }

  public void addResizeListener(ComponentAdapter resizeListener) {
    frame.addComponentListener(resizeListener);
  }

  public JScrollBar getHorizontalScrollBar() {
    return scrollPane.getHorizontalScrollBar();
  }

  public JScrollBar getVerticalScrollBar() {
    return scrollPane.getVerticalScrollBar();
  }

  public int getFrameWidth() {
    return frame.getWidth();
  }

  public int getFrameHeight() {
    return frame.getHeight();
  }
}
