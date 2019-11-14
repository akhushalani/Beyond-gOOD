package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Worksheet;

/**
 * An interface representing a view of a Worksheet.
 */
public interface WorksheetView {
  /**
   * Updates the model that the view represents.
   * @param model the model to update to
   */
  void updateModel(Worksheet model);

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
