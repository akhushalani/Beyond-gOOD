package edu.cs3500.spreadsheets.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * A menu bar for a worksheet visual view.
 */
public class WorksheetMenuBar extends JMenuBar {
  private JMenuItem newFile;
  private JMenuItem save;
  private JMenuItem open;
  private Color bg;

  /**
   * A public constructor for a WorksheetMenuBar.
   * @param bg
   */
  public WorksheetMenuBar(Color bg) {
    super();

    this.bg = bg;

    JMenu fileMenu = new JMenu("File");
    fileMenu.setForeground(Color.WHITE);
    fileMenu.setOpaque(false);
    add(fileMenu);

    newFile = new JMenuItem("New");
    newFile.setActionCommand("New File");
    newFile.setAccelerator(KeyStroke.getKeyStroke('N',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
    fileMenu.add(newFile);

    save = new JMenuItem("Save");
    save.setActionCommand("Save");
    save.setAccelerator(KeyStroke.getKeyStroke('S',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
    fileMenu.add(save);

    open = new JMenuItem("Open");
    open.setActionCommand("Open");
    open.setAccelerator(KeyStroke.getKeyStroke('O',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
    fileMenu.add(open);

    setForeground(Color.WHITE);
    setOpaque(false);
  }

  /**
   * Gets the new file menu item from the toolbar.
   * @return the new file menu item
   */
  public JMenuItem getNewFile() {
    return newFile;
  }

  /**
   * Gets the save menu item from the toolbar.
   * @return the save menu item
   */
  public JMenuItem getSave() {
    return save;
  }

  /**
   * Gets the open menu item from the toolbar.
   * @return the open menu item
   */
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
