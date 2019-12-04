package edu.cs3500.spreadsheets.provider.model;

import java.util.ArrayList;
import java.util.HashMap;

import edu.cs3500.spreadsheets.provider.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetReader;

/**
 * Public interface representing a Worksheet that a user can create and the different methods that,
 * can act on any of these Worksheets.
 */
public interface Worksheet {

  /**
   * Updates each Cell by evaluating all of the Cells within the Worksheet.
   * @return HashMap<{@link Coord}, {@link Cell}> all the cells in original
   *          hashmap with its contents evaluated
   */
  HashMap<Coord, Cell> evaluateAllCells();

  /**
   * Creates a coordinate from the given String.
   *
   * @param c is the String representing the given coordinate
   * @return the Coord
   * @throws IllegalArgumentException if the given String doesn't represent a valid Coord
   */
  Coord createCoord(String c) throws IllegalArgumentException;

  /**
   * Creates an ArrayList of Coords of all of the Coords in the reference (represented as a
   * String).
   *
   * @param s is the reference represented as a Coord
   * @return the ArrayList of all of the Coords
   * @throws IllegalArgumentException if the given String doesn't represent a valid range reference
   */
  ArrayList<Coord> createCoordList(String s) throws IllegalArgumentException;

  /**
   * Determines whether the given String is a valid single reference to a Cell.
   *
   * @param s the String representing a single Cell reference
   * @return true if the String is a valid single reference to a Cell
   */
  boolean isReference(String s);

  /**
   * Determines whether the String is a valid reference to a group of Cells.
   *
   * @param s is the String representing the reference to a group of Cells
   * @return true if the String is a valid reference to a group of Cells
   * @throws IllegalArgumentException if any Cells contain a cyclic reference
   */
  boolean isRange(String s) throws IllegalArgumentException;

  /**
   * Builds a builder for the Worksheet.
   *
   * @return WorksheetBuilder instance of that Worksheet's builder
   */
  <T> WorksheetReader.WorksheetBuilder<T> builder();

  /**
   * Returns the Content within the given Coordinate.
   *
   * @param coordinate is the Coord for the Content that should be returned
   * @return the Content that is within the given coordinate
   * @throws IllegalArgumentException if the given Coord contains cyclic references or the given
   *                                  Coord is not in this Worksheet
   */
  Content getContents(Coord coordinate) throws IllegalArgumentException;

  /**
   * Determines if the given Coord contains a cyclic reference, or any references to itself. (or any
   * cells that are referenced again).
   *
   * @param initial is the Coord represented as a String to be compared against
   * @param content is the given Content that must be checked
   * @return true if the given Coord contains a cyclic reference
   */
  boolean isCyclic(Coord initial, Content content);

  // TODO: changes
  /**
   * Returns a list of Coords representing non-empty cells in this worksheet.
   *
   * @return an ArrayList of Coords representing this worksheet's non-empty cells.
   */
  ArrayList<Coord> nonEmptyCoords();

  /**
   * For overwriting within each class that implements this interface.
   *
   * @param o the Object to be compared to.
   * @return true if the two objects are equal
   */
  boolean equals(Object o);

  /**
   * Retrieves the hashcode for this worksheet.
   *
   * @return the corresponding hashcode
   */
  int hashCode();

  /**
   * updates the cell at the given coordinates with the given string.
   * @param c the cell the update
   * @param s the cell's new contents to update the cell with
   */
  void updateCell(Coord c, String s);

  /**
   * Gets a defensive copy of this Worksheet's hashmap.
   * @return a defensive copy of this worksheet
   */
  HashMap<Coord, Cell> getWorksheet();


}
