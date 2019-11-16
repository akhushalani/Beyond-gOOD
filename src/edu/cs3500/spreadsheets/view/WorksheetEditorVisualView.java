package edu.cs3500.spreadsheets.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import edu.cs3500.spreadsheets.model.WorksheetAdapter;

public class WorksheetEditorVisualView implements WorksheetView {
  private WorksheetAdapter model;

  /**
   * Public constructor for the WorksheetEditorVisualView class.
   *
   * @param model the model being represented by the view
   */
  public WorksheetEditorVisualView(WorksheetAdapter model) {
    this.model = model;
  }

  @Override
  public void renderView() {
    JFrame frame = new JFrame();

    WorksheetPanel worksheetPanel = new WorksheetPanel(model, frame, true);

    Color bg = new Color(50, 50, 50);
    JToolBar editBar = new JToolBar();
    editBar.setBackground(bg);
    editBar.setFloatable(false);

    JTextField coordDisplay = new JTextField();
    coordDisplay.setBackground(new Color(71, 71, 71));
    coordDisplay.setForeground(Color.WHITE);
    coordDisplay.setMaximumSize(new Dimension(50, coordDisplay.getPreferredSize().height));
    coordDisplay.setColumns(7);
    coordDisplay.setEditable(false);
    editBar.add(coordDisplay);

    JTextField editField = new JTextField();
    editField.setBackground(new Color(71, 71, 71));
    editField.setForeground(Color.WHITE);
    editBar.add(editField);

    worksheetPanel.addCoordDisplay(coordDisplay);
    worksheetPanel.addEditField(editField);

    frame.add(editBar, BorderLayout.PAGE_START);
    frame.add(worksheetPanel.getScrollPane());
    frame.setSize(1300, 600);

    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  @Override
  public Appendable getAppendable() {
    return new StringBuilder();
  }
}
