package edu.cs3500.spreadsheets.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.event.CellEditorListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumnModel;

import edu.cs3500.spreadsheets.model.CellAttribute;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.GraphType;
import edu.cs3500.spreadsheets.model.WorksheetAdapter;

/**
 * An editable worksheet visual view.
 */
public class WorksheetEditorVisualView extends JFrame implements WorksheetView {
  private WorksheetAdapter model;
  private WorksheetPanel worksheetPanel;
  private WorksheetAttributeBar attributeBar;
  private WorksheetEditBar editBar;
  private WorksheetMenuBar menuBar;
  private String title;
  private HashMap<String, BeyondGoodGraph> graphs;

  /**
   * Public constructor for the WorksheetEditorVisualView class.
   *
   * @param model the model being represented by the view
   */
  public WorksheetEditorVisualView(WorksheetAdapter model, String title) {
    this.model = model;
    this.title = title;
    this.graphs = new HashMap<>();
    for (Map.Entry<ArrayList<Coord>, ArrayList<GraphType>> graphEntry
            : this.model.getGraphs().entrySet()) {
      for (GraphType type : graphEntry.getValue()) {
        String key = graphEntry.getKey().get(0).toString() + ":"
                + graphEntry.getKey().get(1).toString() + "_" + type.name();
        BeyondGoodGraph graph = new BeyondGoodGraph(this.model, graphEntry.getKey().get(0),
                graphEntry.getKey().get(1), type);
        graphs.put(key, graph);
      }
    }
  }

  @Override
  public void renderView() {
    worksheetPanel = new WorksheetPanel(model, this, true);

    Color bg = new Color(50, 50, 50);

    JPanel toolbars = new JPanel(new GridLayout(0, 1));
    attributeBar = new WorksheetAttributeBar(bg);
    editBar = new WorksheetEditBar(bg);
    toolbars.add(attributeBar);
    toolbars.add(editBar);

    attributeBar.getBoldButton().addActionListener(e ->
            worksheetPanel.getTableModel().toggleAttribute(CellAttribute.BOLD));

    attributeBar.getItalicButton().addActionListener(e ->
            worksheetPanel.getTableModel().toggleAttribute(CellAttribute.ITALIC));

    attributeBar.getUnderlineButton().addActionListener(e ->
            worksheetPanel.getTableModel().toggleAttribute(CellAttribute.UNDERLINE));

    attributeBar.getLeftButton().addActionListener(e ->
            worksheetPanel.getTableModel().toggleAttribute(CellAttribute.LEFT));

    attributeBar.getCenterButton().addActionListener(e ->
            worksheetPanel.getTableModel().toggleAttribute(CellAttribute.CENTER));

    attributeBar.getRightButton().addActionListener(e ->
            worksheetPanel.getTableModel().toggleAttribute(CellAttribute.RIGHT));

    attributeBar.getTextColorButton().addActionListener(e -> {
      Color oldColor = model
              .getAttributeSet(worksheetPanel.getTableModel().getFirstSelection()).getTextColor();
      Color color = JColorChooser.showDialog(attributeBar, "Choose text color", oldColor);
      worksheetPanel.getTableModel().setColor(CellAttribute.TEXT_COLOR, color);
    });

    worksheetPanel.attachCoordDisplay(editBar.getCoordDisplay());
    worksheetPanel.attachEditField(editBar.getEditField());

    menuBar = new WorksheetMenuBar(bg);

    add(toolbars, BorderLayout.PAGE_START);
    add(worksheetPanel.getScrollPane(), BorderLayout.CENTER);
    setSize(1300, 600);
    setJMenuBar(menuBar);
    setTitle(title);

    setVisible(true);
  }

  @Override
  public Appendable getAppendable() {
    return new StringBuilder();
  }

  @Override
  public String getEditText() {
    return editBar.getEditField().getText();
  }

  @Override
  public void setEditText(String editText) {
    editBar.getEditField().setText(editText);
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
  public boolean cellsSelected() {
    return worksheetPanel.getTableModel().minSelectionCol() != -1;
  }

  @Override
  public void notifyCellChanged(Coord coord) {
    worksheetPanel.getTableModel().fireTableCellUpdated(coord.row - 1, coord.col - 1);
    worksheetPanel.getTableModel().cellUpdated(coord);
  }

  @Override
  public void setListeners(ActionListener clicks, CellEditorListener cellEdits,
                           DocumentListener docEdits, FocusListener focus, KeyListener keys,
                           MouseInputAdapter rowResize, TableColumnModelListener colResize,
                           AdjustmentListener horizScroll, AdjustmentListener vertScroll,
                           ComponentAdapter resizeListener) {
    attributeBar.getPieChartButton().addActionListener(clicks);
    attributeBar.getBarGraphButton().addActionListener(clicks);
    attributeBar.getLineGraphButton().addActionListener(clicks);
    attributeBar.getScatterPlotButton().addActionListener(clicks);
    editBar.getConfirmButton().addActionListener(clicks);
    editBar.getRejectButton().addActionListener(clicks);
    editBar.getEditField().addActionListener(clicks);
    editBar.getEditField().setActionCommand("Confirm");
    editBar.getEditField().addFocusListener(focus);
    editBar.getEditField().getDocument().addDocumentListener(docEdits);
    editBar.getEditField().getDocument().putProperty("owner", "editField");
    menuBar.getNewFile().addActionListener(clicks);
    menuBar.getSave().addActionListener(clicks);
    menuBar.getOpen().addActionListener(clicks);
    worksheetPanel.getTable().addKeyListener(keys);
    worksheetPanel.getTable().getColumnModel().addColumnModelListener(colResize);
    worksheetPanel.getRowHeader().addMouseListener(rowResize);
    worksheetPanel.getRowHeader().addMouseMotionListener(rowResize);
    worksheetPanel.getCellEditor().addDocumentListener(docEdits);
    worksheetPanel.getCellEditor().addCellEditorListener(cellEdits);
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
    return worksheetPanel.getTable().isEditing();
  }

  @Override
  public void startEditing(Coord coord) {
    worksheetPanel.getTable().editCellAt(coord.row - 1, coord.col - 1);
    ((WorksheetCellEditor) worksheetPanel.getTable().getCellEditor()).selectAll();
  }

  @Override
  public void stopEditing() {
    worksheetPanel.getCellEditor().stopCellEditing();
  }

  @Override
  public void setCellEditorText(String text) {
    worksheetPanel.getCellEditor().setText(text);
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
    TableColumnModel tcm = worksheetPanel.getTable().getColumnModel();
    for (Map.Entry<Integer, Double> colEntry : model.getColumnSizes().entrySet()) {
      if (colEntry.getKey() <= worksheetPanel.getTable().getColumnCount()) {
        tcm.getColumn(colEntry.getKey() - 1).setPreferredWidth(
                (int) (worksheetPanel.getDefaultColumnWidth() * colEntry.getValue()));
      }
    }

    for (Map.Entry<Integer, Double> rowEntry : model.getRowSizes().entrySet()) {
      if (rowEntry.getKey() <= worksheetPanel.getTable().getRowCount()) {
        worksheetPanel.getTable().setRowHeight(rowEntry.getKey() - 1,
                (int) (worksheetPanel.getMinRowHeight() * rowEntry.getValue()));
        worksheetPanel.getRowHeader().setRowHeight(rowEntry.getKey() - 1,
                (int) (worksheetPanel.getMinRowHeight() * rowEntry.getValue()));
      }
    }
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
    return worksheetPanel.getTable().getWidth() > getWidth();
  }

  @Override
  public void addGraph(BeyondGoodGraph graph) {
    graphs.put(graph.getKey(), graph);
  }

  @Override
  public void removeGraph(BeyondGoodGraph graph) {
    graphs.remove(graph.getKey());
  }

  @Override
  public HashMap<String, BeyondGoodGraph> getGraphs() {
    return graphs;
  }

  @Override
  public void updateGraph(String key) {
    if (graphs.containsKey(key)) {
      graphs.get(key).updateGraph();
    }
  }
}
