package edu.cs3500.spreadsheets.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;

/**
 * A class that represents a scrollable table with a fixed row header column.
 */
public class ScrollableRowHeaderTable implements ChangeListener, PropertyChangeListener {
  private RowHeaderTable table;
  private RowHeaderTable rowHeader;
  private JScrollPane scrollPane;
  private int minRowHeight;

  /**
   * Public constructor for the ScrollableRowHeaderTable class.
   *
   * @param scrollPane the JScrollPane containing the table
   */
  public ScrollableRowHeaderTable(JScrollPane scrollPane) {
    this.scrollPane = scrollPane;

    table = (RowHeaderTable) scrollPane.getViewport().getView();
    table.addPropertyChangeListener(this);
    table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    table.getTableHeader().setReorderingAllowed(false);
    table.setRowHeight((int) (table.getRowHeight() * 1.2));
    minRowHeight = table.getRowHeight();

    RowHeaderTableModel headerModel = new RowHeaderTableModel(table.getModel());
    rowHeader = new RowHeaderTable(headerModel, true, false);
    rowHeader.setModel(headerModel);
    rowHeader.setSelectionModel(new NullSelectionModel());
    rowHeader.setFocusable(false);
    rowHeader.getColumnModel().getColumn(0)
            .setPreferredWidth(rowHeader.getColumnModel().getColumn(0).getWidth() / 2);
    rowHeader.getTableHeader().setReorderingAllowed(false);
    rowHeader.setRowHeight(table.getRowHeight());

    rowHeader.setPreferredScrollableViewportSize(rowHeader.getPreferredSize());
    this.scrollPane.setRowHeaderView(rowHeader);
    this.scrollPane.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, rowHeader.getTableHeader());

    this.scrollPane.getRowHeader().addChangeListener(this);
  }

  /**
   * Gets the row header table in the scrollable table.
   *
   * @return the row header JTable
   */
  public RowHeaderTable getRowHeader() {
    return rowHeader;
  }

  /**
   * Gets the main data table in the scrollable table.
   *
   * @return the data JTable
   */
  public RowHeaderTable getTable() {
    return table;
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    JViewport viewport = (JViewport) e.getSource();
    scrollPane.getVerticalScrollBar().setValue(viewport.getViewPosition().y);
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("selectionModel")) {
      rowHeader.setSelectionModel(table.getSelectionModel());
    }

    if (evt.getPropertyName().equals("model")) {
      rowHeader.setModel(table.getModel());
    }
  }

  public int getMinRowHeight() {
    return minRowHeight;
  }

  public static class NullSelectionModel implements ListSelectionModel {
    public NullSelectionModel() {}

    @Override
    public void setSelectionInterval(int index0, int index1) {

    }

    @Override
    public void addSelectionInterval(int index0, int index1) {

    }

    @Override
    public void removeSelectionInterval(int index0, int index1) {

    }

    @Override
    public int getMinSelectionIndex() {
      return -1;
    }

    @Override
    public int getMaxSelectionIndex() {
      return -1;
    }

    @Override
    public boolean isSelectedIndex(int index) {
      return false;
    }

    @Override
    public int getAnchorSelectionIndex() {
      return -1;
    }

    @Override
    public void setAnchorSelectionIndex(int index) {

    }

    @Override
    public int getLeadSelectionIndex() {
      return -1;
    }

    @Override
    public void setLeadSelectionIndex(int index) {

    }

    @Override
    public void clearSelection() {

    }

    @Override
    public boolean isSelectionEmpty() {
      return true;
    }

    @Override
    public void insertIndexInterval(int index, int length, boolean before) {

    }

    @Override
    public void removeIndexInterval(int index0, int index1) {

    }

    @Override
    public void setValueIsAdjusting(boolean valueIsAdjusting) {

    }

    @Override
    public boolean getValueIsAdjusting() {
      return false;
    }

    @Override
    public void setSelectionMode(int selectionMode) {

    }

    @Override
    public int getSelectionMode() {
      return SINGLE_SELECTION;
    }

    @Override
    public void addListSelectionListener(ListSelectionListener x) {

    }

    @Override
    public void removeListSelectionListener(ListSelectionListener x) {

    }
  }
}
