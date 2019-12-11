package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.GraphType;

/**
 * The features of a worksheet editor.
 */
public interface Features {

  /**
   * Confirms the formula entered into the edit field and edits the cell.
   */
  void confirmCell();

  /**
   * Rejects the formula entered into the edit field.
   */
  void rejectCell();

  /**
   * Creates a new worksheet file.
   */
  void newFile();

  /**
   * Saves the current worksheet to a new file.
   */
  void save();

  /**
   * Opens a worksheet.
   */
  void open();

  /**
   * Deletes the selected cells.
   */
  void deleteCells();

  /**
   * Creates a graph of a given type.
   * @param type the type of graph to create
   */
  void createGraph(GraphType type);
}
