package edu.cs3500.spreadsheets.provider.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * JPanel for the Panel that represents the Row headers of the Spreadsheet.
 */
public class RowPanel extends javax.swing.JPanel {

  private static int ORIGIN = 0;
  private static int CELL_HEIGHT = 20;
  private static int ROW_LABEL_WIDTH_INITIAL = 20;

  public RowPanel() {
    // this class needs no non-final fields.
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g;

    // set color to gray
    g2d.setColor(new Color(29,111,66));

    // side bar
    g2d.fillRect(ORIGIN, ORIGIN, ROW_LABEL_WIDTH_INITIAL, this.getHeight());

    // set color to black
    g2d.setColor(Color.BLACK);

    // draw row header labels
    int rowCounter = 0;
    for (int i = 0; i < this.getHeight() / CELL_HEIGHT; i++) {
      int x = 0;
      int y = (i * CELL_HEIGHT) - 5;
      g2d.drawString(Integer.toString(rowCounter), x, y);
      rowCounter++;
    }

  }

}
