package edu.cs3500.spreadsheets.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class WorksheetMenuBar extends JMenuBar {
  private JMenuItem save;
  private JMenuItem open;
  private Color bg;

  public WorksheetMenuBar(Color bg) {
    super();

    this.bg = bg;

    JMenu fileMenu = new JMenu("File");
    fileMenu.setForeground(Color.WHITE);
    fileMenu.setOpaque(false);
    add(fileMenu);

    save = new JMenuItem("Save");
    save.setActionCommand("Save");
    fileMenu.add(save);

    open = new JMenuItem("Open");
    open.setActionCommand("Open");
    fileMenu.add(open);

    setForeground(Color.WHITE);
    setOpaque(false);
  }

  public JMenuItem getSave() {
    return save;
  }

  public JMenuItem getOpen() {
    return open;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(bg);
    g2d.fillRect(0, 0, getWidth(), getHeight() + 1);
  }
}
