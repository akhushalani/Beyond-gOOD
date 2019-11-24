package edu.cs3500.spreadsheets.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JToolBar;

public class WorksheetEditBar extends JToolBar {
  private JTextField coordDisplay;
  private JTextField editField;

  public WorksheetEditBar(Color bg) {
    super();
    setBackground(bg);
    setFloatable(false);

    coordDisplay = new JTextField();
    coordDisplay.setBackground(new Color(71, 71, 71));
    coordDisplay.setForeground(Color.WHITE);
    coordDisplay.setMaximumSize(new Dimension(50, coordDisplay.getPreferredSize().height));
    coordDisplay.setColumns(7);
    coordDisplay.setEditable(false);
    add(coordDisplay);

    editField = new JTextField();
    editField.setBackground(new Color(71, 71, 71));
    editField.setForeground(Color.WHITE);
    add(editField);
  }

  public JTextField getCoordDisplay() {
    return coordDisplay;
  }

  public JTextField getEditField() {
    return editField;
  }
}
