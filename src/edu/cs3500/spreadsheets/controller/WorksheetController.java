package edu.cs3500.spreadsheets.controller;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Collections;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumn;
import javax.swing.text.BadLocationException;

import edu.cs3500.spreadsheets.model.BeyondGoodWorksheet;
import edu.cs3500.spreadsheets.model.BeyondGoodWorksheetBuilder;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.GraphType;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetAdapter;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.sexp.CellSexpVisitor;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.ValueCellSexpVisitor;
import edu.cs3500.spreadsheets.view.BeyondGoodGraph;
import edu.cs3500.spreadsheets.view.WorksheetEditorVisualView;
import edu.cs3500.spreadsheets.view.WorksheetTextualView;
import edu.cs3500.spreadsheets.view.WorksheetView;

/**
 * A controller for a worksheet.
 */
public class WorksheetController implements ActionListener, CellEditorListener, DocumentListener,
        FocusListener, KeyListener, Features {
  private Worksheet model;
  private WorksheetView view;
  private Coord currentSelection;
  private boolean editFieldHasFocus;

  /**
   * Public constructor for a WorksheetController.
   * @param model a worksheet model
   * @param view a worksheet view
   */
  public WorksheetController(Worksheet model, WorksheetView view) {
    this.model = model;
    this.view = view;
    this.view.setListeners(this, this, this, this, this,
            new RowHeightListener(), new ColumnWidthListener(), new HorizontalScrollListener(),
            new VerticalScrollListener(), new FrameResizeListener());
    this.currentSelection = null;
    this.editFieldHasFocus = false;
    this.view.adjustToFrame(view.getDefaultColumnWidth(), view.getMinRowHeight());
    this.view.resizeCells();

    for (BeyondGoodGraph graph : this.view.getGraphs().values()) {
      graph.renderGraph();
      graph.addWindowListener(new GraphManager(graph));
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Confirm":
        confirmCell();
        view.stopEditing();
        break;
      case "Reject":
        rejectCell();
        break;
      case "New File":
        newFile();
        break;
      case "Save":
        save();
        break;
      case "Open":
        open();
        break;
      case "Pie Chart":
        createGraph(GraphType.PIE_CHART);
        break;
      case "Bar Graph":
        createGraph(GraphType.BAR_GRAPH);
        break;
      case "Line Graph":
        createGraph(GraphType.LINE_GRAPH);
        break;
      case "Scatter Plot":
        createGraph(GraphType.SCATTER_PLOT);
        break;
      default:
        // do nothing here
    }
  }

  @Override
  public void insertUpdate(DocumentEvent e) {
    if (e.getDocument().getProperty("owner").equals("cellEditor") && !editFieldHasFocus) {
      cellEditorDocumentUpdated(e);
    } else if (e.getDocument().getProperty("owner").equals("editField")) {
      editFieldDocumentUpdated(e);
    }
  }

  @Override
  public void removeUpdate(DocumentEvent e) {
    if (e.getDocument().getProperty("owner").equals("cellEditor") && !editFieldHasFocus) {
      cellEditorDocumentUpdated(e);
    } else if (e.getDocument().getProperty("owner").equals("editField")) {
      editFieldDocumentUpdated(e);
    }
  }

  @Override
  public void changedUpdate(DocumentEvent e) {
    // won't be called on a plain text document
  }

  // Should be run when the document is changed.
  private void editFieldDocumentUpdated(DocumentEvent e) {
    if (editFieldHasFocus) {
      if (!view.isEditing() && view.getFirstSelected() != null) {
        view.startEditing(view.getFirstSelected());
        currentSelection = view.getFirstSelected();
      }
      Runnable doUpdate = () -> {
        view.setCellEditorText(view.getEditText());
      };

      SwingUtilities.invokeLater(doUpdate);
    }
  }

  private void cellEditorDocumentUpdated(DocumentEvent e) {
    if (!editFieldHasFocus) {
      Runnable doUpdate = () -> {
        try {
          view.setEditText(e.getDocument().getText(0, e.getDocument().getLength()));
        } catch (BadLocationException ex) {
          ex.printStackTrace();
        }
      };

      SwingUtilities.invokeLater(doUpdate);
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // don't have to check for keys being typed
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // don't have to check for keys being released
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if ((e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE)
            && !view.isEditing()) {
      deleteCells();
    }
  }

  @Override
  public void confirmCell() {
    if (view.cellsSelected()) {
      Coord sel = view.getFirstSelected();
      if (view.getEditText().equals("")) {
        model.setCell(view.getFirstSelected(), null);
      } else {
        String contents = view.getEditText();
        if (contents.substring(0, 1).equals("=")) {
          Sexp sexpContents = Parser.parse(contents.substring(1));
          CellSexpVisitor cellVisitor = new CellSexpVisitor(sel, contents, model);
          model.setCell(sel, sexpContents.accept(cellVisitor));
        } else {
          Sexp sexpContents = Parser.parse(contents);
          ValueCellSexpVisitor valueCellVisitor
                  = new ValueCellSexpVisitor(sel, contents, model);
          model.setCell(sel, sexpContents.accept(valueCellVisitor));
        }
      }
      view.notifyCellChanged(sel);

      for (String graph : model.getGraphsToUpdate()) {
        view.updateGraph(graph);
      }
      model.clearGraphsToUpdate();
    }
  }

  @Override
  public void rejectCell() {
    if (view.cellsSelected()) {
      Coord sel = view.getFirstSelected();
      if (model.getWorksheet().containsKey(sel)) {
        view.setEditText(model.getCellAt(sel).getRawContents());
      }
    }
  }

  @Override
  public void save() {
    JFileChooser save = new JFileChooser();
    if (view.getWindowTitle() != "BeyondGood") {
      save.setSelectedFile(
              new File(view.getWindowTitle().substring(0, view.getWindowTitle().length() - 5)));
    }
    int saveVal = save.showSaveDialog(null);
    if (saveVal == JFileChooser.APPROVE_OPTION) {
      String filename = save.getSelectedFile().getName();
      String directory = save.getCurrentDirectory().toString();
      String savePath = "";
      if (!(filename.length() > 5
              && filename.substring(filename.length() - 5).toLowerCase().equals(".good"))) {
        filename += ".gOOD";
      }
      savePath += directory + "/" + filename;

      try {
        PrintWriter pw = new PrintWriter(savePath);
        WorksheetTextualView view = new WorksheetTextualView(
                new WorksheetAdapter(model), pw);
        view.renderView();
        pw = (PrintWriter) view.getAppendable();
        pw.close();
        this.view.setWindowTitle(filename);
      } catch (FileNotFoundException ex) {
        // will never run, creating a new file with String name
        throw new IllegalArgumentException("Invalid file name");
      }
    }
  }

  @Override
  public void newFile() {
    BeyondGoodWorksheet model = new BeyondGoodWorksheet();
    WorksheetEditorVisualView view
            = new WorksheetEditorVisualView(new WorksheetAdapter(model), "BeyondGood");
    view.renderView();
    WorksheetController controller = new WorksheetController(model, view);
  }

  @Override
  public void open() {
    JFileChooser open = new JFileChooser();
    int openVal = open.showOpenDialog(null);
    if (openVal == JFileChooser.APPROVE_OPTION) {
      String filename = open.getSelectedFile().getName();
      String directory = open.getCurrentDirectory().toString();
      String openPath = directory + "/" + filename;
      try {
        Worksheet worksheet = WorksheetReader.read(new BeyondGoodWorksheetBuilder(),
                new FileReader(openPath));
        WorksheetEditorVisualView view
                = new WorksheetEditorVisualView(new WorksheetAdapter(worksheet), filename);
        view.renderView();
        WorksheetController controller = new WorksheetController(worksheet, view);
      } catch (FileNotFoundException fnfe) {
        System.out.println("Insufficient arguments, file specified does not exist");
      }
    }
  }

  @Override
  public void deleteCells() {
    if (view.cellsSelected()) {
      int minCol = view.getMinSelection().col;
      int maxCol = view.getMaxSelection().col;
      int minRow = view.getMinSelection().row;
      int maxRow = view.getMaxSelection().row;
      for (int i = minCol; i <= maxCol; i++) {
        for (int j = minRow; j <= maxRow; j++) {
          Coord c = new Coord(i, j);
          model.setCell(c, null);
          view.notifyCellChanged(c);
        }
      }
    }
  }

  public void createGraph(GraphType type) {
    WorksheetAdapter modelAdapter = new WorksheetAdapter(model);
    BeyondGoodGraph graph = new BeyondGoodGraph(modelAdapter,
            view.getMinSelection(), view.getMaxSelection(), type);
    model.addGraph(view.getMinSelection(), view.getMaxSelection(), type);
    view.addGraph(graph);
    graph.renderGraph();
    graph.addWindowListener(new GraphManager(graph));
  }

  @Override
  public void editingStopped(ChangeEvent e) {
    confirmCell();
  }

  @Override
  public void editingCanceled(ChangeEvent e) {
    rejectCell();
  }

  @Override
  public void focusGained(FocusEvent e) {
    if (view.getFirstSelected() != null && !editFieldHasFocus) {
      view.startEditing(view.getFirstSelected());
      currentSelection = view.getFirstSelected();
    }
    editFieldHasFocus = true;
  }

  @Override
  public void focusLost(FocusEvent e) {
    if (view.getFirstSelected() != null && view.getFirstSelected().equals(currentSelection)
            && editFieldHasFocus) {
      view.stopEditing();
    }
    this.editFieldHasFocus = false;
  }

  private class ColumnWidthListener implements TableColumnModelListener {

    @Override
    public void columnAdded(TableColumnModelEvent e) {

    }

    @Override
    public void columnRemoved(TableColumnModelEvent e) {

    }

    @Override
    public void columnMoved(TableColumnModelEvent e) {

    }

    @Override
    public void columnMarginChanged(ChangeEvent e) {
      TableColumn col = view.getTable().getTableHeader().getResizingColumn();
      if (model != null && col != null) {
        model.resizeColumn(col.getModelIndex() + 1,
                col.getWidth() / (double) view.getDefaultColumnWidth());
      }
    }

    @Override
    public void columnSelectionChanged(ListSelectionEvent e) {

    }
  }

  public final class RowHeightListener extends MouseInputAdapter {
    private Cursor resizeCursor = Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);

    private int mouseYDelta;
    private int row;
    private Cursor swap = resizeCursor;

    private int getResizingRow(Point p) {
      return getResizingRow(p, view.getRowHeader().rowAtPoint(p));
    }

    private int getResizingRow(Point p, int currentRow) {
      if (currentRow == -1) {
        return -1;
      }

      int currentCol = view.getRowHeader().columnAtPoint(p);
      if (currentCol == -1) {
        return -1;
      }

      Rectangle cellRect = view.getRowHeader().getCellRect(currentRow, currentCol, true);
      cellRect.grow(0, -4);
      if (cellRect.contains(p)) {
        return -1;
      }

      int midY = cellRect.y + cellRect.height / 2;
      int index;
      if (p.y < midY) {
        index = currentRow - 1;
      } else {
        index = currentRow;
      }

      return index;
    }

    private void swapCursor() {
      Cursor current = view.getRowHeader().getCursor();
      view.getRowHeader().setCursor(swap);
      swap = current;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      int mouseY = e.getY();

      if (row >= 0) {
        int newHeight = mouseY - mouseYDelta;
        if (newHeight > 0) {
          newHeight = Math.max(newHeight, view.getMinRowHeight());
          view.getRowHeader().setRowHeight(row, newHeight);
          view.getTable().setRowHeight(row, newHeight);
          model.resizeRow(row + 1, newHeight / (double) view.getMinRowHeight());
          view.getRowHeader()
                  .setPreferredScrollableViewportSize(view.getRowHeader().getSize());
        }
      }
    }

    @Override
    public void mousePressed(MouseEvent e) {
      Point p = e.getPoint();

      row = getResizingRow(p);
      mouseYDelta = p.y - view.getRowHeader().getRowHeight(row);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      if ((view.getRowHeader().getCursor() == resizeCursor)
              != (getResizingRow(e.getPoint()) >= 0)) {
        swapCursor();
      }
    }
  }

  private class HorizontalScrollListener implements AdjustmentListener {
    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
      int horizExtent = view.getHorizontalScrollBar().getModel().getExtent();
      if (e.getValueIsAdjusting()
              && e.getValue() == view.getHorizontalScrollBar().getMaximum() - horizExtent) {
        view.fireScrollRight();
        view.resizeCells();
      }
    }
  }

  private class VerticalScrollListener implements AdjustmentListener {
    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
      int vertExtent = view.getVerticalScrollBar().getModel().getExtent();
      if (e.getValueIsAdjusting()
              && e.getValue() == view.getVerticalScrollBar().getMaximum() - vertExtent) {
        view.fireScrollDown();
        view.resizeCells();
        //System.out.println("# of rows: " + view.getTable().getRowCount());
      }
    }
  }

  private class FrameResizeListener extends ComponentAdapter {
    @Override
    public void componentResized(ComponentEvent e) {
      if (!model.getColumnSizes().isEmpty()) {
        double minColWidth = Collections.min(model.getColumnSizes().values());
        view.adjustToFrame((int) (view.getDefaultColumnWidth() * minColWidth),
                view.getMinRowHeight());
      } else {
        view.adjustToFrame(view.getDefaultColumnWidth(), view.getMinRowHeight());
      }
      view.resizeCells();
    }
  }

  private class GraphManager implements WindowListener {
    private BeyondGoodGraph graph;

    public GraphManager(BeyondGoodGraph graph) {
      this.graph = graph;
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
      model.removeGraph(graph.getRange(), graph.getType());
      view.removeGraph(graph);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
  }
}
