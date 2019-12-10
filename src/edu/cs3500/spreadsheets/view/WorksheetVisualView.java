package edu.cs3500.spreadsheets.view;

import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.WindowConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.TableColumnModelListener;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetAdapter;

/**
 * A visual view of a worksheet model.
 */
public class WorksheetVisualView extends JFrame implements WorksheetView {
  private WorksheetAdapter model;
  private WorksheetPanel worksheetPanel;
  private String title;

  /**
   * Public constructor for the WorksheetVisualView class.
   *
   * @param model the model being represented by the view
   */
  public WorksheetVisualView(WorksheetAdapter model, String title) {
    this.model = model;
    this.title = title;
  }

  @Override
  public void renderView() {
    worksheetPanel = new WorksheetPanel(model, this, false);

    add(worksheetPanel.getScrollPane());
    pack();
    setSize(1300, 600);
    setTitle(title);

    setVisible(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  @Override
  public Appendable getAppendable() {
    return new StringBuilder();
  }

  @Override
  public boolean cellsSelected() {
    return worksheetPanel.getTableModel().minSelectionCol() != -1;
  }

  @Override
  public void notifyCellChanged(Coord coord) {
    worksheetPanel.getTableModel().fireTableCellUpdated(coord.row - 1, coord.col - 1);
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
  public Coord getFirstSelected() {
    return worksheetPanel.getTableModel().getFirstSelection();
  }

  @Override
  public Coord getMinSelection() {
    return new Coord(worksheetPanel.getTableModel().minSelectionCol() + 1,
            worksheetPanel.getTableModel().minSelectionRow() + 1);
  }

  @Override
  public Coord getMaxSelection() {
    return new Coord(worksheetPanel.getTableModel().maxSelectionCol() + 1,
            worksheetPanel.getTableModel().maxSelectionRow() + 1);
  }

  @Override
  public void setListeners(ActionListener clicks, CellEditorListener cellEdits,
                           DocumentListener docEdits, FocusListener focus, KeyListener keys,
                           MouseInputAdapter rowResize, TableColumnModelListener colResize,
                           AdjustmentListener horizScroll, AdjustmentListener vertScroll,
                           ComponentAdapter resizeListener) {
    worksheetPanel.addScrollListeners(horizScroll, vertScroll);
    worksheetPanel.addResizeListener(resizeListener);
  }

  @Override
  public void setWindowTitle(String title) {
    this.title = title;
    setTitle(title);
  }

  @Override
  public String getWindowTitle() {
    return title;
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
    return worksheetPanel.getRowHeader();
  }

  @Override
  public RowHeaderTable getTable() {
    return worksheetPanel.getTable();
  }

  @Override
  public int getMinRowHeight() {
    return worksheetPanel.getMinRowHeight();
  }

  @Override
  public int getDefaultColumnWidth() {
    return worksheetPanel.getDefaultColumnWidth();
  }

  @Override
  public void resizeCells() {
    throw new UnsupportedOperationException();
  }

  @Override
  public JScrollBar getHorizontalScrollBar() {
    return worksheetPanel.getHorizontalScrollBar();
  }

  @Override
  public JScrollBar getVerticalScrollBar() {
    return worksheetPanel.getVerticalScrollBar();
  }

  @Override
  public void fireScrollRight() {
    ((InfiniteScrollingTableModel) worksheetPanel.getTable().getModel()).fireScrollRight();
  }

  @Override
  public void fireScrollDown() {
    ((InfiniteScrollingTableModel) worksheetPanel.getTable().getModel()).fireScrollDown();
  }

  @Override
  public void adjustToFrame(int colWidth, int rowHeight) {
    ((InfiniteScrollingTableModel) worksheetPanel.getTable().getModel())
            .adjustToFrame(colWidth, rowHeight,
                    worksheetPanel.getFrameWidth(), worksheetPanel.getFrameHeight());
  }

  @Override
  public boolean tableWidthSufficient() {
    return true;
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