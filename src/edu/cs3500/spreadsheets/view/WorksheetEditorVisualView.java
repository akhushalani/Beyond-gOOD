package edu.cs3500.spreadsheets.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
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
    WorksheetEditBar editBar = new WorksheetEditBar(bg);

    worksheetPanel.addCoordDisplay(editBar.getCoordDisplay());
    worksheetPanel.addEditField(editBar.getEditField());

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
