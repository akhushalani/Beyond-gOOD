package edu.cs3500.spreadsheets.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentListener;

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
    return null;
  }

  @Override
  public void setEditText(String editText) {

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
  public void setListeners(ActionListener clicks, DocumentListener cellEdits, KeyListener keys) {

  }

  @Override
  public void setWindowTitle(String title) {
    this.title = title;
    setTitle(title);
  }
}