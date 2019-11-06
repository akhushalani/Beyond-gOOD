package edu.cs3500.spreadsheets.view;

import java.io.IOException;

import edu.cs3500.spreadsheets.model.Worksheet;

/**
 * An interface representing a view of a Worksheet.
 */
public interface WorksheetView {
  /**
   * Updates the model that the view represents.
   * @param worksheet the model to update to
   */
  void updateModel(Worksheet worksheet);

  /**
   * Renders the view of the model.
   * @throws IOException if view cannot append to appendable
   */
  void renderView() throws IOException;

  /**
   * Returns the appendable form of the view.
   * @return Appendable format of view.
   */
  Appendable getAppendable();
}
