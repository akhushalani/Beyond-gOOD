package edu.cs3500.spreadsheets.provider.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * JPanel for the Panel that represents the Column headers of the Spreadsheet.
 */
public class ColumnPanel extends javax.swing.JPanel {

  private static int ORIGIN = 0;
  private static int CELL_HEIGHT = 20;
  private static int CELL_WIDTH = 60;
  private static int CENTER_HEADER_X = 35;
  private static int CENTER_HEADER_Y = 15;


  public ColumnPanel() {
    // this class needs no non-final fields.
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g;

    // set color to gray
    g2d.setColor(new Color(29,111,66));

    // top bar
    g2d.fillRect(ORIGIN, ORIGIN, this.getWidth(), CELL_HEIGHT);

    // set color to black
    g2d.setColor(Color.BLACK);

    // draw column header labels
    int colCounter = 1;
    for (int i = 0; i < this.getWidth() / CELL_WIDTH; i++) {
      int x = (i * CELL_WIDTH); //incrementing based on the width of the cells
      int y = ORIGIN;
      Coord c = new Coord(1, colCounter);
      String letter = c.colIndexToName(colCounter - 1);
      g2d.drawString(letter, x - CENTER_HEADER_X, y + CENTER_HEADER_Y);
      colCounter++;
    }

  }


}
