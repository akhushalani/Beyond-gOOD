package edu.cs3500.spreadsheets.provider.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.provider.model.Worksheet;

/**
 * Draws the spreadsheet based on which cell is selected.
 */
public class SelectPanel extends javax.swing.JPanel {

  private static int CELL_HEIGHT = 20;
  private static int CELL_WIDTH = 60;
  private static int CELL_MAX = 7;
  private static final int INITIAL_SIZE = 500;
  private ArrayList<ArrayList<Rectangle>> cells = new ArrayList<>();
  private Rectangle selected;
  private Worksheet ws;

  /**
   * Instantiates an instance of SelectPanel using the given worksheet.
   *
   * @param ws the Worksheet the panel should organize and display.
   */
  public SelectPanel(Worksheet ws) {
    super();
    this.ws = ws;


    ArrayList<Coord> allCoords;
    allCoords = ws.nonEmptyCoords();

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
    this.setPreferredSize(new Dimension(width, height));

    // instantiate rectangles
    int maxColCells = maxCol;
    int maxRowCells = maxRow;

    if (maxRowCells < INITIAL_SIZE / CELL_WIDTH) {
      maxRowCells = INITIAL_SIZE / CELL_WIDTH;
    }

    if (maxColCells < INITIAL_SIZE / CELL_HEIGHT) {
      maxColCells = INITIAL_SIZE / CELL_HEIGHT;
    }
    for (int c = 0; c < maxRowCells; c++) {
      ArrayList<Rectangle> row = new ArrayList<>();
      for (int r = 0; r < maxColCells; r++) {
        int x = r * CELL_WIDTH;
        int y = c * CELL_HEIGHT;

        Rectangle rect = new Rectangle(x, y, CELL_WIDTH, CELL_HEIGHT);
        row.add(rect);
      }
      cells.add(row);
    }

    selected = cells.get(0).get(0);

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;


    // set color to black
    g2d.setColor(Color.BLACK);

    // draw rectangles
    int count = 0;
    for (int c = 0; c < cells.size(); c++) {
      for (int r = 0; r < cells.get(c).size(); r++) {
        Rectangle rect = cells.get(c).get(r);
        g2d.drawRect(rect.x, rect.y, rect.width, rect.height);
        count++;
      }
    }

    //set color to LIGHT_GRAY
    g2d.setColor(Color.LIGHT_GRAY);

    // fill selected rectangle
    g2d.fill(selected);

    // set color to black
    g2d.setColor(Color.BLACK);

    // draw contents
    ArrayList<Coord> coordList = this.ws.nonEmptyCoords();

    for (Coord c : coordList) {
      int row = c.row;
      int col = c.col;
      String con;

      try {
        con = ws.getContents(c).evaluate(ws, c).toString();
      } catch (Exception e) {
        con = "hello";
      }

      int x = col * CELL_WIDTH - CELL_WIDTH;
      int y = (row - 1) * CELL_HEIGHT + 15;

      if (con.length() > CELL_MAX) {
        con = con.substring(0, CELL_MAX);
      }

      g2d.drawString(con, x, y);
    }
  }

  /**
   * Returns the currently selected cell.
   *
   * @return the rectangle representing the currently selected cell.
   */
  public Rectangle getSelected() {
    return selected;
  }

  /**
   * Sets the currently selected cell to be the one containing the given point.
   *
   * @param p the point on the window where the mouse was clicked.
   */
  public void setSelected(Point p) {
    int x = p.x;
    int y = p.y;

    int row = (x / CELL_WIDTH) + 1;
    int height = (y / CELL_HEIGHT) + 1;

    Rectangle r = cells.get(height - 1).get(row - 1);
    if (r.contains(x, y)) {
      selected = r;
    }
  }

  /**
   * Adds a new row to the spreadsheet.
   */
  public void addRow() {
    int size = cells.get(0).size();
    ArrayList<Rectangle> newRow = new ArrayList<>();
    int y = cells.size() * CELL_HEIGHT;
    for (int i = 0; i < size; i++) {
      int x = i * CELL_WIDTH;
      Rectangle rect = new Rectangle(x, y, CELL_WIDTH, CELL_HEIGHT);
      newRow.add(rect);
    }

    cells.add(newRow);
  }

  /**
   * Adds a new column to the spreadsheet.
   */
  public void addCol() {
    int size = cells.size();
    int x = cells.get(0).size() * CELL_WIDTH;

    for (int i = 0; i < size; i++) {
      int y = i * CELL_HEIGHT;
      Rectangle rect = new Rectangle(x, y, CELL_WIDTH, CELL_HEIGHT);
      cells.get(i).add(rect);
    }


  }

  @Override
  public Dimension getPreferredSize() {
    int maxRow = 0;
    int maxCol = 0;
    if (cells.get(0).size() > maxCol) {
      maxCol = cells.get(0).size();
    }

    if (cells.size() > maxRow) {
      maxRow = cells.size();
    }

    int width = maxCol * CELL_WIDTH;
    int height = maxRow * CELL_HEIGHT;

    if (width < INITIAL_SIZE) {
      width = INITIAL_SIZE;
    }

    if (height < INITIAL_SIZE) {
      height = INITIAL_SIZE;
    }

    return new Dimension(width, height);
  }

  /**
   * Returns a defensive copy of the worksheet represented as an arraylist.
   *
   * @return the copy
   */
  public ArrayList<ArrayList<Rectangle>> getRectangles() {
    ArrayList<ArrayList<Rectangle>> cellsCopy = new ArrayList<>();
    for (int i = 0; i < cells.size(); i++) {
      cellsCopy.add(cells.get(i));
    }
    return cellsCopy;
  }

}
