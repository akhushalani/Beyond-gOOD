package edu.cs3500.spreadsheets.provider.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.provider.model.Worksheet;

/**
 * JPanel representing the view of the contents of a Spreadsheet.
 */
public class SpreadsheetPanel extends javax.swing.JPanel {

  private static int ROW_LABEL_WIDTH = 20;
  private static int CELL_HEIGHT = 20;
  private static int CELL_WIDTH = 60;
  private static int ORIGIN = 0;
  private static int CELL_MAX = 7;

  private Worksheet w;

  public SpreadsheetPanel(Worksheet w) {
    this.w = w;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D)g;

    // set color to gray
    g2d.setColor(new Color(29,111,66));

    // set color to black
    g2d.setColor(Color.BLACK);

    // draw horizontal lines
    for (int i = 1; i < this.getHeight() / CELL_HEIGHT; i++) {
      int x = 0;
      int y = i * CELL_HEIGHT;
      g2d.drawLine(x, y, this.getWidth(), y);

    }

    // draw vertical lines
    for (int i = 1; i < this.getWidth() / CELL_WIDTH; i++) {
      int x = (i * CELL_WIDTH); //incrementing based on the width of the cells
      int y = ORIGIN;
      g2d.drawLine(x, y, x, this.getHeight());
    }

    // draw contents
    ArrayList<Coord> coordList = this.w.nonEmptyCoords();

    for (Coord c : coordList) {
      int row = c.row;
      int col = c.col;
      String con = w.getContents(c).toString();

      int x = col * CELL_WIDTH - CELL_WIDTH;
      int y = (row - 1) * CELL_HEIGHT + 15;

      if (con.length() > CELL_MAX) {
        con = con.substring(0, CELL_MAX);
      }

      g2d.drawString(con, x, y);
    }

  }


}
