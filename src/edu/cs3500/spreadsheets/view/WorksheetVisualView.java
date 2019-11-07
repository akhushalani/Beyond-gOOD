package edu.cs3500.spreadsheets.view;


import java.awt.Color;
import java.awt.Dimension;
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

    String[][] data = new String[model.getHeight()][model.getWidth() + 1];
    for (int i = 0; i < model.getHeight(); i++) {
      for (int j = 0; j <= model.getWidth(); j++) {
        if (j == 0) {
          data[i][j] = String.valueOf(i + 1);
        } else {
          data[i][j] = "";
        }
      }
    }

    String[] colNames = new String[model.getWidth() + 1];
    for (int i = 0; i <= model.getWidth(); i++) {
      if (i == 0) {
        colNames[i] = "";
      } else {
        colNames[i] = Coord.colIndexToName(i);
      }
    }

    RowHeaderTable table = new RowHeaderTable(data, colNames);
    JScrollPane scrollPane = new JScrollPane(table,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    table.setColumnSelectionAllowed(true);
    table.setRowSelectionAllowed(true);

    table.setIntercellSpacing(new Dimension(-1, -1));
    table.setBorder(BorderFactory.createEmptyBorder());
    table.getTableHeader().setBorder(BorderFactory.createEmptyBorder());

    table.setGridColor(Color.lightGray);

    DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
    headerRenderer.setHorizontalAlignment(JLabel.CENTER);
    table.getTableHeader().setDefaultRenderer(new HeaderRenderer(headerRenderer));

    TableCellRenderer cellRenderer = table.getDefaultRenderer(String.class);
    table.setDefaultRenderer(String.class, new RowHeaderTableCellRenderer(cellRenderer));

    for (Map.Entry<Coord, Cell> entry : model.getWorksheet().entrySet()) {
      table.getModel().setValueAt(model.getCellAt(entry.getKey()).evaluate(model),
              entry.getKey().row - 1,
              entry.getKey().col);
    }

    frame.add(scrollPane);
    frame.setSize(1000, 150);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  @Override
  public Appendable getAppendable() {
    return new StringBuilder();
  }
}
