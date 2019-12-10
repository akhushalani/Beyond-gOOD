package edu.cs3500.spreadsheets.view;

import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JScrollBar;
import javax.swing.event.CellEditorListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.TableColumnModelListener;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.GraphType;
import edu.cs3500.spreadsheets.model.WorksheetAdapter;

/**
 * A textual view of a worksheet model.
 */
public class WorksheetTextualView implements WorksheetView {
  private WorksheetAdapter model;
  private Appendable ap;

  /**
   * Public constructor for the WorksheetTextualView class.
   *
   * @param model the model the view is representing
   * @param ap the appendable to which the view will append its output
   */
  public WorksheetTextualView(WorksheetAdapter model, Appendable ap) {
    this.model = model;
    this.ap = ap;
  }

  @Override
  public void renderView() {
    renderData();

    renderSizes();

    renderGraphs();
  }

  private void renderData() {
    for (Map.Entry<Coord, Cell> entry : model.getWorksheet().entrySet()) {
      try {
        if (model.getCellAt(entry.getKey()).evaluate(model.getModel(), false).length() > 7
                && model.getCellAt(entry.getKey()).evaluate(model.getModel(), false).substring(0, 7)
                .equals("ERROR: ")) {
          ap.append("# " + model.getCellAt(entry.getKey())
                  .evaluate(model.getModel(), false) + "\n");
          ap.append(entry.getKey().toString() + " "
                  + model.getCellAt(entry.getKey()).getRawContents() + "\n");
        } else {
          ap.append(entry.getKey().toString() + " "
                  + model.getCellAt(entry.getKey()).evaluate(model.getModel(), false) + "\n");
        }
      } catch (IOException ex) {
        // Shouldn't ever happen, all Strings being appended will be valid
        throw new IllegalArgumentException("Invalid IO passed to Appendable");
      }
    }
  }

  private void renderSizes() {
    try {
      for (Map.Entry<Integer, Double> colEntry : model.getColumnSizes().entrySet()) {
        ap.append("~~(" + colEntry.getKey() + " " + colEntry.getValue() + ")\n");
      }

      for (Map.Entry<Integer, Double> rowEntry : model.getColumnSizes().entrySet()) {
        ap.append("~(" + rowEntry.getKey() + " " + rowEntry.getValue() + ")\n");
      }
    } catch (IOException ex) {
      // Shouldn't happen, all column and row sizes should be written in proper format
      throw new IllegalArgumentException("Invalid row/column size IO passed to Appendable");
    }
  }

  private void renderGraphs() {
    try {
      for (Map.Entry<ArrayList<Coord>, ArrayList<GraphType>> graphEntry
              : model.getGraphs().entrySet()) {
        for (GraphType type : graphEntry.getValue()) {
          ap.append("@@(" + graphEntry.getKey().get(0).toString() + " "
                  + graphEntry.getKey().get(1).toString() + " " + type.name() + ")\n");
        }
      }
    } catch (IOException ex) {
      // Shouldn't happen, all graphs should be properly written
      throw new IllegalArgumentException("Invalid row/column size IO passed to Appendable");
    }
  }

  @Override
  public Appendable getAppendable() {
    return this.ap;
  }

  @Override
  public boolean cellsSelected() {
    return false;
  }

  @Override
  public Coord getFirstSelected() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Coord getMinSelection() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Coord getMaxSelection() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getEditText() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setEditText(String editText) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void notifyCellChanged(Coord coord) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setListeners(ActionListener clicks, CellEditorListener cellEdits,
                           DocumentListener docEdits, FocusListener focus, KeyListener keys,
                           MouseInputAdapter rowResize, TableColumnModelListener colResize,
                           AdjustmentListener horizScroll, AdjustmentListener vertScroll,
                           ComponentAdapter resizeListener) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setWindowTitle(String title) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getWindowTitle() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isEditing() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void startEditing(Coord coord) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void stopEditing() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setCellEditorText(String text) {
    throw new UnsupportedOperationException();
  }

  @Override
  public RowHeaderTable getRowHeader() {
    throw new UnsupportedOperationException();
  }

  @Override
  public RowHeaderTable getTable() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getMinRowHeight() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getDefaultColumnWidth() {
    return 0;
  }

  @Override
  public void resizeCells() {
    throw new UnsupportedOperationException();
  }

  @Override
  public JScrollBar getHorizontalScrollBar() {
    throw new UnsupportedOperationException();
  }

  @Override
  public JScrollBar getVerticalScrollBar() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void fireScrollRight() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void fireScrollDown() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void adjustToFrame(int colWidth, int rowHeight) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean tableWidthSufficient() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void addGraph(BeyondGoodGraph graph) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void removeGraph(BeyondGoodGraph graph) {
    throw new UnsupportedOperationException();
  }

  @Override
  public HashMap<String, BeyondGoodGraph> getGraphs() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void updateGraph(String key) {
    throw new UnsupportedOperationException();
  }
}
