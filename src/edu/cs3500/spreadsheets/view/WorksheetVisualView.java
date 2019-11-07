package edu.cs3500.spreadsheets.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;

public class WorksheetVisualView implements WorksheetView {
  private Worksheet model;

  public WorksheetVisualView(Worksheet model) {
    this.model = model;
  }

  @Override
  public void updateModel(Worksheet model) {
    this.model = model;
  }

  @Override
  public void renderView() {
    JFrame frame = new JFrame();

    InfiniteScrollingTableModel tableModel = new InfiniteScrollingTableModel(model);

    RowHeaderTable table = new RowHeaderTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(table,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    tableModel.adjustToFrame(table.getColumnModel().getColumn(1).getWidth(),
            table.getRowHeight(), 1000, 1000);

    scrollPane.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {
      @Override
      public void adjustmentValueChanged(AdjustmentEvent e) {
        if (e.getValueIsAdjusting() &&
                e.getValue() == scrollPane.getHorizontalScrollBar().getMaximum()
                - scrollPane.getHorizontalScrollBar().getVisibleAmount()) {
          tableModel.fireScrollRight();
        } else if (e.getValueIsAdjusting() &&
                e.getValue() == scrollPane.getHorizontalScrollBar().getMinimum()) {
          tableModel.fireScrollLeft();
        }

        table.setFullyLeft(tableModel.fullyLeft());
      }
    });

    scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
      @Override
      public void adjustmentValueChanged(AdjustmentEvent e) {
        if (e.getValue() == scrollPane.getVerticalScrollBar().getMaximum()
                - scrollPane.getVerticalScrollBar().getVisibleAmount()) {
          tableModel.fireScrollDown();
        } else if (e.getValue() == scrollPane.getVerticalScrollBar().getMinimum()) {
          tableModel.fireScrollUp();
        }
      }
    });

    frame.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        tableModel.adjustToFrame(table.getColumnModel().getColumn(1).getWidth(),
                table.getRowHeight(), frame.getWidth(), frame.getHeight());
      }
    });

    frame.addMouseWheelListener(new MouseWheelListener() {
      @Override
      public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        if (notches < 0 && !e.isShiftDown()) {
          tableModel.fireScrollUp();
        } else if (notches > 0 && !e.isShiftDown()) {
          tableModel.fireScrollDown();
        } else if (notches < 0) {
          tableModel.fireScrollLeft();
        } else if (notches > 0) {
          tableModel.fireScrollRight();
        }
      }
    });

    table.setColumnSelectionAllowed(true);
    table.setRowSelectionAllowed(true);

    table.setIntercellSpacing(new Dimension(-1, -1));

    table.setBorder(BorderFactory.createEmptyBorder());
    table.getTableHeader().setBorder(BorderFactory.createEmptyBorder());

    table.setGridColor(Color.lightGray);

    DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
    headerRenderer.setHorizontalAlignment(JLabel.CENTER);
    table.getTableHeader().setDefaultRenderer(new HeaderRenderer(headerRenderer));

    TableCellRenderer cellRenderer = table.getDefaultRenderer(Object.class);
    table.setDefaultRenderer(Object.class, new RowHeaderTableCellRenderer(cellRenderer));

    frame.add(scrollPane);
    frame.setSize(1300, 600);

    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  @Override
  public Appendable getAppendable() {
    return new StringBuilder();
  }
}
