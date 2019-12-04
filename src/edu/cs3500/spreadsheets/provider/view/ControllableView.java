package edu.cs3500.spreadsheets.provider.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import edu.cs3500.spreadsheets.provider.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.provider.model.Worksheet;

/**
 * A view that can communicate with the Controller.
 */
public class ControllableView extends JFrame implements WorksheetView {

  private SelectPanel selectPanel;
  private ColumnPanel columnPanel;
  private RowPanel rowPanel;
  JScrollPane scrollPane;
  SpreadsheetView spreadsheetView;
  Worksheet ws;
  private JPanel buttonPanel;
  private JPanel addPanel;
  private JTextField input;
  private JButton accept;
  private JButton decline;
  private JButton addRow;
  private JButton addColumn;
  private static final int INITIAL_SIZE = 500;

  /**
   * Constructs an instance of this View.
   * @param ws the worksheet that the view represents.
   */
  public ControllableView(Worksheet ws) {
    this.ws = ws;
    this.spreadsheetView = new SpreadsheetView(ws);


    ArrayList<Coord> allCoords;
    allCoords = ws.nonEmptyCoords();

    // initialize spreadsheet setting features
    int maxRow = 0;
    int maxCol = 0;
    for (Coord c : allCoords) {
      if (c.row > maxRow) {
        maxRow = c.row;
      }
      if (c.col > maxCol) {
        maxCol = c.col;
      }
    }

    int width = maxCol * CELL_WIDTH;
    int height = maxRow * CELL_HEIGHT;

    if (width < INITIAL_SIZE) {
      width = INITIAL_SIZE;
    }
    if (height < INITIAL_SIZE) {
      height = INITIAL_SIZE;
    }

    this.setTitle("Spreadsheet");
    this.setSize(width, height);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    // create the scrolling functionality
    selectPanel = new SelectPanel(ws);
    selectPanel.setPreferredSize(new Dimension(width, height));
    scrollPane = new JScrollPane(selectPanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    columnPanel = new ColumnPanel();
    rowPanel = new RowPanel();
    columnPanel.setPreferredSize(new Dimension(width, CELL_HEIGHT));
    rowPanel.setPreferredSize(new Dimension(ROW_LABEL_WIDTH_INITIAL, height));
    JScrollBar horizontal = scrollPane.getHorizontalScrollBar();
    JScrollBar vertical = scrollPane.getVerticalScrollBar();
    vertical.setValue(vertical.getMaximum());
    horizontal.setValue(horizontal.getMaximum());
    scrollPane.setColumnHeaderView(columnPanel);
    scrollPane.setRowHeaderView(rowPanel);

    // handle accept and decline buttons, and text-box for input
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    accept = new JButton("✓");
    decline = new JButton("✘");
    input = new JTextField(15);
    buttonPanel.add(accept);
    buttonPanel.add(decline);
    buttonPanel.add(input);

    // handle add rows and columns
    addPanel = new JPanel();
    addPanel.setLayout(new FlowLayout());
    addRow = new JButton("New Row");
    addColumn = new JButton("New Column");
    addPanel.add(addRow);
    addPanel.add(addColumn);

    // add all component to view properly layered
    this.add(scrollPane, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.NORTH);
    this.add(addPanel, BorderLayout.SOUTH);

    this.pack();
    this.render();
  }

  @Override
  public void render() {
    this.setVisible(true);
  }


  public void setCellSelectionListener(MouseListener mouseEvent) {
    selectPanel.addMouseListener(mouseEvent);
  }

  /**
   * Sets the action listeners and commands for this View.
   * @param actionEvent the event to add to this View.
   */
  public void setInputListener(ActionListener actionEvent) {
    accept.addActionListener(actionEvent);
    accept.setActionCommand("accept");
    decline.addActionListener(actionEvent);
    decline.setActionCommand("decline");
    addRow.addActionListener(actionEvent);
    addRow.setActionCommand("addRow");
    addColumn.addActionListener(actionEvent);
    addColumn.setActionCommand("addColumn");
  }

  public String getInputBoxText() {
    return this.input.getText();
  }

  @Override
  public void setInputField() {
    Rectangle r = selectPanel.getSelected();
    int col = (r.x / CELL_WIDTH) + 1;
    int row = (r.y / CELL_HEIGHT) + 1;
    HashMap<Coord, Cell> wscopy = ws.getWorksheet();

    Coord c = new Coord(col, row);
    if (wscopy.containsKey(c)) {
      String text = wscopy.get(c).getContents().toString();
      input.setText(text);
    }
  }

  @Override
  public Rectangle getSelected() {
    return selectPanel.getSelected();
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  public void selectCell(Point p) {
    this.selectPanel.setSelected(p);
    this.setInputField();
  }

  @Override
  public void addRow() {
    selectPanel.addRow();
    selectPanel.setPreferredSize(selectPanel.getPreferredSize());
    scrollPane.setViewportView(selectPanel);
    rowPanel.setPreferredSize(new Dimension(ROW_LABEL_WIDTH_INITIAL, selectPanel.getHeight()));
  }

  @Override
  public void addCol() {
    selectPanel.addCol();
    selectPanel.setPreferredSize(selectPanel.getPreferredSize());
    this.setPreferredSize(selectPanel.getPreferredSize());
    scrollPane.setViewportView(selectPanel);
    columnPanel.setPreferredSize(new Dimension(selectPanel.getWidth(), CELL_HEIGHT));
  }

  @Override
  public ArrayList<ArrayList<Rectangle>> getRectangles() {
    return selectPanel.getRectangles();
  }
}

