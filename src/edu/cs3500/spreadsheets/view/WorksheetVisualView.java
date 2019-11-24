package edu.cs3500.spreadsheets.view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import edu.cs3500.spreadsheets.model.WorksheetAdapter;

/**
 * A visual view of a worksheet model.
 */
public class WorksheetVisualView extends JFrame implements WorksheetView {
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
    WorksheetPanel worksheetPanel = new WorksheetPanel(model, this, false);

    add(worksheetPanel.getScrollPane());
    pack();
    setSize(1300, 600);

    setVisible(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  @Override
  public Appendable getAppendable() {
    return new StringBuilder();
  }
}