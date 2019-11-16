package edu.cs3500.spreadsheets.view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import edu.cs3500.spreadsheets.model.WorksheetAdapter;

/**
 * A visual view of a worksheet model.
 */
public class WorksheetVisualView implements WorksheetView {
  private WorksheetAdapter model;

  /**
   * Public constructor for the WorksheetVisualView class.
   *
   * @param model the model being represented by the view
   */
  public WorksheetVisualView(WorksheetAdapter model) {
    this.model = model;
  }

  @Override
  public void renderView() {
    JFrame frame = new JFrame();

    WorksheetPanel worksheetPanel = new WorksheetPanel(model, frame, false);

    frame.add(worksheetPanel.getScrollPane());
    frame.pack();
    frame.setSize(1300, 600);

    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  @Override
  public Appendable getAppendable() {
    return new StringBuilder();
  }
}