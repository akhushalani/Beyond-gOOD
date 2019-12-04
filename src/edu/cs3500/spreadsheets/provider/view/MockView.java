package edu.cs3500.spreadsheets.provider.view;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Mock View to test Controller.
 */
public class MockView implements WorksheetView {

  StringBuilder log;

  /**
   * Instantiates an instance of a mockView.
   * @param log the StringBuilder to test the output.
   */
  public MockView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void render() {
    // do nothing.
  }

  @Override
  public void selectCell(Point p) {
    // do nothing.
  }

  @Override
  public void setCellSelectionListener(MouseListener mouseEvent) {
    log.append("cell selected/n");
  }

  @Override
  public void setInputListener(ActionListener actionEvent) {
    log.append("input recorded/n");

  }

  @Override
  public String getInputBoxText() {
    return null;
  }

  @Override
  public void setInputField() {
    // do nothing.
  }

  @Override
  public Rectangle getSelected() {
    return null;
  }

  @Override
  public void refresh() {
    // do nothing.
  }

  @Override
  public void addRow() {
    // do nothing.
  }

  @Override
  public void addCol() {
    // do nothing.
  }

  @Override
  public ArrayList<ArrayList<Rectangle>> getRectangles() {
    return null;
  }
}
