package edu.cs3500.spreadsheets.provider.view;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Represents a textual view of the Spreadsheet.
 */
public interface WorksheetView {
  int CELL_HEIGHT = 20;
  int CELL_WIDTH = 60;
  int ROW_LABEL_WIDTH_INITIAL = 20;

  /**
   * Renders the model in some visual way.
   */
  void render();

  /**
   * Updates selected cell to be filled.
   */
  void selectCell(Point p);

  /**
   * Sets mouse listener component for cell selection.
   * @param mouseEvent  the mouse event
   */
  void setCellSelectionListener(MouseListener mouseEvent);

  /**
   * Sets action listener component for button clicking.
   * @param actionEvent the button clicking event
   */
  void setInputListener(ActionListener actionEvent);


  /**
   * Returns any input that the user entered into the input box in the form of a String.
   *
   * @return  String representing input
   */
  String getInputBoxText();

  /**
   * Sets input field with whatever is in the selected cell.
   */
  void setInputField();

  /**
   * Gets the rectangle representing the selected cell.
   * @return rectangle representing selected cell
   */
  Rectangle getSelected();

  /**
   * Refreshes the view of the spreadsheet.
   */
  void refresh();

  /**
   * Adds a row to the view of the worksheet.
   */
  void addRow();

  /**
   * Adds a column to the view of the worksheet.
   */
  void addCol();

  /**
   * Returns a defensive copy of the worksheet represented as an arraylist.
   *
   * @return the copy
   */
  ArrayList<ArrayList<Rectangle>> getRectangles();
}
