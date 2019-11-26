package edu.cs3500.spreadsheets.controller;

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
}
