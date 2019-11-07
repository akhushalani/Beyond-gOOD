package edu.cs3500.spreadsheets.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

class CellBorder extends EmptyBorder {

  public boolean isHeaderCell = false;

  protected CellBorder(int top, int left, int bottom, int right) {
    super(top, left, bottom, right);
  }

  @Override public boolean isBorderOpaque() {
    return true;
  }

  @Override public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
    Graphics2D border = (Graphics2D) g.create();
    border.translate(x, y);
    border.setPaint(Color.lightGray);
    if (!isHeaderCell) {
      border.drawLine(0, 0, w - 1, 0);
    }
    border.dispose();
  }
}