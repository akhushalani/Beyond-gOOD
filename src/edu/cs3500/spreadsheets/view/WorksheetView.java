package edu.cs3500.spreadsheets.view;

import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JScrollBar;
import javax.swing.event.CellEditorListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.TableColumnModelListener;

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
  void setListeners(ActionListener clicks, CellEditorListener cellEdits, DocumentListener docEdits,
                    FocusListener focus, KeyListener keys, MouseInputAdapter rowResize,
                    TableColumnModelListener colResize, AdjustmentListener horizScroll,
                    AdjustmentListener vertScroll, ComponentAdapter resizeListener);

  /**
   * Sets the title of the window containing the view.
   * @param title the title to set
   */
  void setWindowTitle(String title);

  /**
   * Gets the title of the window containing the view.
   * @return the window title
   */
  String getWindowTitle();

  /**
   * Checks if a cell is being directly edited through its own cell editor.
   * @return whether a cell is being edited
   */
  boolean isEditing();

  /**
   * Opens the cell editor for a given cell.
   * @param coord the location of the cell to start the editor for
   */
  void startEditing(Coord coord);

  /**
   * Closes the active cell editor.
   */
  void stopEditing();

  /**
   * Sets the text of the active cell editor.
   * @param text the text to set the cell editor to
   */
  void setCellEditorText(String text);

  /**
   * Gets the fixed row header table in the worksheet view.
   * @return the row header JTable
   */
  RowHeaderTable getRowHeader();

  /**
   * Gets the main data table in the worksheet view.
   * @return the main JTable
   */
  RowHeaderTable getTable();

  /**
   * Gets the minimum row height of the table.
   * @return the minimum row height
   */
  int getMinRowHeight();

  /**
   * Gets the initial column width of the columns in the view.
   * @return the default column width
   */
  int getDefaultColumnWidth();

  /**
   * Resizes all of the cells in the view as specified by the model.
   */
  void resizeCells();

  /**
   * Gets the horizontal scroll bar in the view.
   * @return the horizontal JScrollBar
   */
  JScrollBar getHorizontalScrollBar();

  /**
   * Gets the vertical scroll bar in the view.
   * @return the vertical JScrollBar
   */
  JScrollBar getVerticalScrollBar();

  /**
   * To be called when the horizontal scroll bar is scrolled all the way to the right.
   */
  void fireScrollRight();

  /**
   * To be called when the vertical scroll bar is scrolled all the way down.
   */
  void fireScrollDown();

  /**
   * Adjusts the number of cells displayed to the frame.
   * @param colWidth the minimum column width
   * @param rowHeight the minimum row height
   */
  void adjustToFrame(int colWidth, int rowHeight);

  /**
   * Determines whether the table is wide enough to fill the frame.
   * @return whether the table width is sufficient for the frame
   */
  boolean tableWidthSufficient();

  /**
   * Adds a graph to the view.
   * @param graph the graph object to add
   */
  void addGraph(BeyondGoodGraph graph);

  /**
   * Removes a graph from the view.
   * @param graph the graph object to remove
   */
  void removeGraph(BeyondGoodGraph graph);

  /**
   * Gets the map of graphs in the view.
   * @return a map of graph keys to graph objects
   */
  HashMap<String, BeyondGoodGraph> getGraphs();

  /**
   * Updates a graph in the view.
   * @param key the key of the graph to update
   */
  void updateGraph(String key);
}
