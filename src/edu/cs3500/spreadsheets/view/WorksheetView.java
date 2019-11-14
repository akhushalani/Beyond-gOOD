package edu.cs3500.spreadsheets.view;

/**
 * An interface representing a view of a Worksheet.
 */
public interface WorksheetView {
  /**
   * Renders the view of the model.
   */
  void renderView();

  /**
   * Returns the appendable form of the view.
   * @return Appendable format of view.
   */
  Appendable getAppendable();
}
