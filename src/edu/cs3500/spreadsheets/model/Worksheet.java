package edu.cs3500.spreadsheets.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * An interface representing worksheets which consist of Cells at various Coords.
 */
public interface Worksheet {

  /**
   * Takes in a coordinate and returns the Cell in the worksheet at that coordinate.
   * @param coord represents the coordinates of a Cell in the worksheet.
   * @return the Cell at the given Coord.
   */
  Cell getCellAt(Coord coord);

  /**
   * Takes in a coordinate and a cell and sets the value at that coordinate in the worksheet to be
   *      that cell.
   * @param coord a Coord representing the coordinates within the worksheet where the user
   *      wants a given cell to be.
   * @param cell a Cell that the user wants to be input into the worksheet.
   */
  void setCell(Coord coord, Cell cell);

  /**
   * Retrieves the worksheet from the Worksheet as a HashMap.
   */
  HashMap<Coord, Cell> getWorksheet();

  /**
   * Gets an already calculated reference in the model.
   * @param coord location of the reference to get
   * @return the formula of the cell at that location
   */
  Formula getCalculatedReference(Coord coord);

  /**
   * Determines whether the model has already calculated a given reference.
   * @param coord location at which to check
   * @return whether or not model has calculated the reference
   */
  boolean hasCalculatedReference(Coord coord);

  /**
   * Adds a calculated reference to the list of calculated references in the model.
   * @param coord location at which reference was calculated
   * @param formula calculated reference
   */
  void addCalculatedReference(Coord coord, Formula formula);

  /**
   * Clears the list of calculated references.
   */
  void clearCalculatedReferences();

  /**
   * Gets the height of the worksheet.
   * @return the y-coordinate of the furthest down cell.
   */
  int getHeight();

  /**
   * Gets the width of the worksheet.
   * @return the x-coordinate of the furthest right cell.
   */
  int getWidth();

  /**
   * Gets all the cells that refer directly or indirectly to a given cell.
   * @param coord the cell to check for references to
   * @return the list of referring cells
   */
  ArrayList<Coord> referTo(Coord coord);

  /**
   * Checks if the worksheet contains a calculated cyclic reference in a given cell.
   * @param coord the cell to check
   * @return whether the cell contains a calculated cyclic reference
   */
  boolean containsCyclicReference(Coord coord);

  /**
   * Adds a cell to the list of calculated cyclic references.
   * @param coord the cell which contains the reference
   */
  void addCyclicReference(Coord coord);

  /**
   * Removes a cell from the list of calculated cyclic references.
   * @param coord the cell to remove
   */
  void removeCyclicReference(Coord coord);

  /**
   * Toggles an attribute for the selected cells.
   * @param attribute the attribute to toggle.
   */
  void toggleAttribute(CellAttribute attribute, Coord coord);

  /**
   * Sets the value of a color attribute for the selected cells.
   * @param attribute the color attribute to set
   * @param color the color value to assign to the cells
   */
  void setColor(CellAttribute attribute, Color color, Coord coord);

  /**
   * Gets the attributes of a cell in the worksheet.
   * @return a map from the coordinates in the worksheet to their attributes
   */
  CellAttributes getAttributeSet(Coord coord);

  /**
   * Gets the attributes of the cells in the worksheet.
   * @return a map from the coordinates in the worksheet to their attributes
   */
  HashMap<Coord, CellAttributes> getAttributes();

  /**
   * Sets the attributes of a cell in the worksheet.
   */
  void setAttributes(Coord coord, CellAttributes attributeSet);

  /**
   * Resizes a row.
   * @param row the row being resized
   * @param size the ratio the row has been resized by compared to the default row height.
   */
  void resizeRow(int row, double size);

  /**
   * Resizes a column.
   * @param column the column being resized
   * @param size the ratio the column has been resized by compared to the default column width.
   */
  void resizeColumn(int column, double size);

  /**
   * Gets the row sizes in a worksheet.
   * @return a map of row indexes to their size ratios
   */
  HashMap<Integer, Double> getRowSizes();

  /**
   * Gets the column sizes in a worksheet.
   * @return a map of column indexes to their size ratios
   */
  HashMap<Integer, Double> getColumnSizes();

  /**
   * Adds a graph to the worksheet.
   * @param start the top left Coord of the data being graphed
   * @param end the bottom right Coord of the data being graphed
   * @param graph the type of graph being added
   */
  void addGraph(Coord start, Coord end, GraphType graph);

  /**
   * Removes a graph from the worksheet.
   * @param range a list of the top left and bottom right Coords of the graph
   * @param graph the graph type
   */
  void removeGraph(ArrayList<Coord> range, GraphType graph);

  /**
   * Gets all the graphs that should be updated after a data change.
   * @return the list of graph keys to be updated
   */
  ArrayList<String> getGraphsToUpdate();

  /**
   * Clears the list of graphs to update.
   */
  void clearGraphsToUpdate();

  /**
   * Gets the map of graphs in the worksheet.
   * @return a map of Coord ranges to lists of graph types found at those ranges
   */
  HashMap<ArrayList<Coord>, ArrayList<GraphType>> getGraphs();
}
