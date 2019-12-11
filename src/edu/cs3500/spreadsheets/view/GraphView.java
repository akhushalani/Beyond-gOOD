package edu.cs3500.spreadsheets.view;

import java.awt.event.WindowListener;
import java.util.ArrayList;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.GraphType;

/**
 * A view that displays a graph of data that can be updated.
 */
public interface GraphView {
  /**
   * Displays the graph to the user.
   */
  void renderGraph();

  /**
   * Updates the graph to display the current data
   */
  void updateGraph();

  /**
   * Gets the Coord range of the data displayed in the graph.
   * @return an ArrayList of the start and end Coords
   */
  ArrayList<Coord> getRange();

  /**
   * Adds a WindowListener to the graph's frame.
   * @param listener the WindowListener being added
   */
  void addWindowListener(WindowListener listener);

  /**
   * Gets the type of graph that the object represents.
   * @return the graph type
   */
  GraphType getType();

  /**
   * Returns a unique key for the graph.
   * @return a unique key
   */
  String getKey();
}
