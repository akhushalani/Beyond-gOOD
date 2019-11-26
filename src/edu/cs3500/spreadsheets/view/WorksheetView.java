package edu.cs3500.spreadsheets.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.event.CellEditorListener;
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

  /**
   * Checks if any cells are selected in the worksheet.
   * @return if any cells are selected
   */
  boolean cellsSelected();

  /**
   * Gets the first selected cell in the selection.
   * @return the Coord of the first selected cell
   */
  Coord getFirstSelected();

  /**
   * Gets the top left cell in the selection.
   * @return the Coord of the top left selected cell
   */
  Coord getMinSelection();

  /**
   * Gets the bottom right cell in the selection.
   * @return the Coord of the bottom right cell
   */
  Coord getMaxSelection();

  /**
   * Gets the text that has been typed in the cell edit field.
   * @return the String in the edit field
   */
  String getEditText();

  /**
   * Sets the text visible in the cell edit field.
   * @param editText the String to put in the edit field
   */
  void setEditText(String editText);

  /**
   * Notifies the view that a cell's data has been changed.
   * @param coord the location of the changed cell
   */
  void notifyCellChanged(Coord coord);

  /**
   * Adds the feature listeners to the view.
   * @param clicks the listener for button actions
   * @param cellEdits the listener for changes to an individual cell editor
   * @param docEdits the listener for changes to the edit field text
   * @param keys the keyboard listener
   */
  void setListeners(ActionListener clicks, CellEditorListener cellEdits,
                    DocumentListener docEdits, KeyListener keys);

  /**
   * Sets the title of the window containing the view.
   * @param title the title to set
   */
  void setWindowTitle(String title);

  /**
   * Checks if a cell is being directly edited through its own cell editor.
   * @return whether a cell is being edited
   */
  boolean isEditing();
}
