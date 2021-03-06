import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.CellAttribute;
import edu.cs3500.spreadsheets.model.CellAttributes;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.GraphType;
import edu.cs3500.spreadsheets.model.Worksheet;

/**
 * Represents an instance of a Worksheet that has no true functionality and is only used for
 *      testing.
 */
public class WorksheetModelMock implements Worksheet {
  private Worksheet delegate;
  public StringBuilder log;

  /**
   * A basic constructor for the mock model of the Worksheet, which instantiates an empty log.
   * @param delegate a non-mock Worksheet that any true functionality of the mock
   *                 will be drawn from.
   */
  public WorksheetModelMock(Worksheet delegate) {
    this.delegate = delegate;
    this.log = new StringBuilder();
  }

  @Override
  public Cell getCellAt(Coord coord) {
    this.log.append("getCellAt\n");
    return delegate.getCellAt(coord);
  }

  @Override
  public void setCell(Coord coord, Cell cell) {
    this.log.append("setCell\n");
  }

  @Override
  public HashMap<Coord, Cell> getWorksheet() {
    this.log.append("getWorksheet\n");
    return delegate.getWorksheet();
  }

  @Override
  public Formula getCalculatedReference(Coord coord) {
    this.log.append("getCalculatedReference\n");
    return null;
  }

  @Override
  public boolean hasCalculatedReference(Coord coord) {
    this.log.append("hasCalculatedReference\n");
    return false;
  }

  @Override
  public void addCalculatedReference(Coord coord, Formula formula) {
    this.log.append("addCalculatedReference\n");
  }

  @Override
  public void clearCalculatedReferences() {
    this.log.append("clearCalculatedReference\n");
  }

  @Override
  public int getHeight() {
    this.log.append("getHeight\n");
    return 0;
  }

  @Override
  public int getWidth() {
    this.log.append("getWidth\n");
    return 0;
  }

  @Override
  public ArrayList<Coord> referTo(Coord coord) {
    this.log.append("referTo\n");
    return null;
  }

  @Override
  public boolean containsCyclicReference(Coord coord) {
    this.log.append("containsCyclicReference\n");
    return false;
  }

  @Override
  public void addCyclicReference(Coord coord) {
    this.log.append("addCyclicReference\n");
  }

  @Override
  public void removeCyclicReference(Coord coord) {
    this.log.append("removeCyclicReference\n");
  }

  @Override
  public void setAttributes(Coord coord, CellAttributes attributeSet) {
    this.log.append("setAttributes\n");
  }

  @Override
  public void resizeRow(int row, double size) {
    this.log.append("resizeRow\n");
  }

  @Override
  public void resizeColumn(int column, double size) {
    this.log.append("resizeColumn\n");
  }

  @Override
  public HashMap<Integer, Double> getRowSizes() {
    this.log.append("getRowSizes\n");
    return delegate.getRowSizes();
  }

  @Override
  public HashMap<Integer, Double> getColumnSizes() {
    this.log.append("getColumnSizes\n");
    return delegate.getColumnSizes();
  }

  @Override
  public void addGraph(Coord start, Coord end, GraphType graph) {
    this.log.append("addGraph\n");
  }

  @Override
  public void removeGraph(ArrayList<Coord> range, GraphType graph) {
    this.log.append("removeGraph\n");
  }

  @Override
  public ArrayList<String> getGraphsToUpdate() {
    this.log.append("getGraphsToUpdate\n");
    return delegate.getGraphsToUpdate();
  }

  @Override
  public void clearGraphsToUpdate() {
    this.log.append("clearGraphsToUpdate\n");
  }

  @Override
  public HashMap<ArrayList<Coord>, ArrayList<GraphType>> getGraphs() {
    this.log.append("getGraphs\n");
    return delegate.getGraphs();
  }

  @Override
  public void toggleAttribute(CellAttribute attribute, Coord coord) {
    this.log.append("toggleAttribute\n");
  }

  @Override
  public void setColor(CellAttribute attribute, Color color, Coord coord) {
    this.log.append("setColor\n");
  }

  @Override
  public CellAttributes getAttributeSet(Coord coord) {
    this.log.append("getAttributeSet\n");
    return delegate.getAttributeSet(coord);
  }

  @Override
  public HashMap<Coord, CellAttributes> getAttributes() {
    this.log.append("getAttributes\n");
    return delegate.getAttributes();
  }
}
