import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JScrollBar;
import javax.swing.event.CellEditorListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.TableColumnModelListener;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.view.BeyondGoodGraph;
import edu.cs3500.spreadsheets.view.RowHeaderTable;
import edu.cs3500.spreadsheets.view.WorksheetView;

/**
 * Represents an instance of a WorksheetView that has no true functionality and is only used for
 *      testing.
 */
public class WorksheetViewMock implements WorksheetView {
  private WorksheetView delegate;
  public Appendable log;

  /**
   * A basic constructor for the mock view of the Worksheet, which instantiates an empty log.
   * @param delegate a non-mock Worksheet that any true functionality of the mock
   *                 will be drawn from.
   */
  public WorksheetViewMock(WorksheetView delegate) {
    this.delegate = delegate;
    this.log = new StringBuilder();
  }

  @Override
  public void renderView() {
    try {
      this.log.append("renderView\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    delegate.renderView();
  }

  @Override
  public Appendable getAppendable() {
    try {
      this.log.append("getAppendable\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return delegate.getAppendable();
  }

  @Override
  public boolean cellsSelected() {
    try {
      this.log.append("cellsSelected\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return true;
  }

  @Override
  public Coord getFirstSelected() {
    try {
      this.log.append("getFirstSelected\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return new Coord(1, 1);
  }

  @Override
  public Coord getMinSelection() {
    try {
      this.log.append("getMinSelection\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return new Coord(1,1);
  }

  @Override
  public Coord getMaxSelection() {
    try {
      this.log.append("getMaxSelection\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return new Coord(1, 1);
  }

  @Override
  public String getEditText() {
    try {
      this.log.append("getEditText\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return "";
  }

  @Override
  public void setEditText(String editText) {
    try {
      this.log.append("setEditText: " + editText + "\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void notifyCellChanged(Coord coord) {
    try {
      this.log.append("notifyCellChanged: " + coord.toString() + "\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setListeners(ActionListener clicks, CellEditorListener cellEdits,
                           DocumentListener docEdits, FocusListener focus, KeyListener keys,
                           MouseInputAdapter rowResize, TableColumnModelListener colResize,
                           AdjustmentListener horizScroll, AdjustmentListener vertScroll,
                           ComponentAdapter resizeListener) {
    try {
      this.log.append("setListeners\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setWindowTitle(String title) {
    try {
      this.log.append("setWindowTitle: " + title + "\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    delegate.setWindowTitle(title);
  }

  @Override
  public boolean isEditing() {
    return delegate.isEditing();
  }

  @Override
  public void startEditing(Coord coord) {
    try {
      this.log.append("startEditing\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void stopEditing() {
    try {
      this.log.append("stopEditing\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setCellEditorText(String text) {
    try {
      this.log.append("setCellEditorText\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public RowHeaderTable getRowHeader() {
    try {
      this.log.append("getRowHeader\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    return delegate.getRowHeader();
  }

  @Override
  public RowHeaderTable getTable() {
    try {
      this.log.append("getTable\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    return delegate.getTable();
  }

  @Override
  public int getMinRowHeight() {
    try {
      this.log.append("getMinRowHeight\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    return delegate.getMinRowHeight();
  }

  @Override
  public int getDefaultColumnWidth() {
    try {
      this.log.append("getDefaultColumnWidth\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    return delegate.getDefaultColumnWidth();
  }

  @Override
  public void resizeCells() {
    try {
      this.log.append("resizeCells\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public JScrollBar getHorizontalScrollBar() {
    try {
      this.log.append("getHorizontalScrollBar\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    return delegate.getHorizontalScrollBar();
  }

  @Override
  public JScrollBar getVerticalScrollBar() {
    try {
      this.log.append("getVerticalScrollBar\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    return delegate.getVerticalScrollBar();
  }

  @Override
  public void fireScrollRight() {
    try {
      this.log.append("fireScrollRight\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void fireScrollDown() {
    try {
      this.log.append("fireScrollDown\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void adjustToFrame(int colWidth, int rowHeight) {
    try {
      this.log.append("adjustToFrame\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean tableWidthSufficient() {
    try {
      this.log.append("tableWidthSufficient\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    return delegate.tableWidthSufficient();
  }

  @Override
  public void addGraph(BeyondGoodGraph graph) {
    try {
      this.log.append("addGraph\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void removeGraph(BeyondGoodGraph graph) {
    try {
      this.log.append("removeGraph\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public HashMap<String, BeyondGoodGraph> getGraphs() {
    try {
      this.log.append("getGraphs\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    return delegate.getGraphs();
  }

  @Override
  public void updateGraph(String key) {
    try {
      this.log.append("updateGraphs\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getWindowTitle() {
    try {
      this.log.append("getWindowTitle\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    return delegate.getWindowTitle();
  }
}
