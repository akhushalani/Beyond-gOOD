package edu.cs3500.spreadsheets.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.event.DocumentListener;

import edu.cs3500.spreadsheets.model.Coord;

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

  boolean cellsSelected();

  Coord getFirstSelected();

  Coord getMinSelection();

  Coord getMaxSelection();

  String getEditText();

  void setEditText(String editText);

  void notifyCellChanged(Coord coord);

  void setListeners(ActionListener clicks, DocumentListener cellEdits, KeyListener keys);

  void setWindowTitle(String title);
}
