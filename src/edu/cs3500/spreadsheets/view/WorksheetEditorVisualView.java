package edu.cs3500.spreadsheets.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetAdapter;

public class WorksheetEditorVisualView extends JFrame implements WorksheetView {
  private WorksheetAdapter model;
  private WorksheetPanel worksheetPanel;
  private WorksheetEditBar editBar;
  private WorksheetMenuBar menuBar;
  private String title;

  /**
   * Public constructor for the WorksheetEditorVisualView class.
   *
   * @param model the model being represented by the view
   */
  public WorksheetEditorVisualView(WorksheetAdapter model, String title) {
    this.model = model;
    this.title = title;
  }

  @Override
  public void renderView() {
    worksheetPanel = new WorksheetPanel(model, this, true);

    Color bg = new Color(50, 50, 50);
    editBar = new WorksheetEditBar(bg);

    editBar.getConfirmButton().getModel().addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        if (editBar.getConfirmButton().getModel().isRollover()
                && worksheetPanel.getTableModel().minSelectionCol() != -1) {
          editBar.getConfirmButton().setBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true),
                  BorderFactory.createEmptyBorder(0, 6, 1, 6)));
          editBar.getConfirmButton().setBackground(Color.DARK_GRAY);
        } else {
          editBar.getConfirmButton().setBorder(BorderFactory.createEmptyBorder(1, 7, 2, 7));
          editBar.getConfirmButton().setBackground(bg);
        }
      }
    });
    editBar.getConfirmButton().setActionCommand("Confirm Button");
    editBar.getEditField().setActionCommand("Confirm Button");

    editBar.getRejectButton().getModel().addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        if (editBar.getRejectButton().getModel().isRollover()
                && worksheetPanel.getTableModel().minSelectionCol() != -1) {
          editBar.getRejectButton().setBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true),
                  BorderFactory.createEmptyBorder(0, 6, 1, 6)));
          editBar.getRejectButton().setBackground(Color.DARK_GRAY);
        } else {
          editBar.getRejectButton().setBorder(BorderFactory.createEmptyBorder(1, 7, 2, 7));
          editBar.getRejectButton().setBackground(bg);
        }
      }
    });
    editBar.getRejectButton().setActionCommand("Reject Button");

    worksheetPanel.addCoordDisplay(editBar.getCoordDisplay());
    worksheetPanel.addEditField(editBar.getEditField());

    menuBar = new WorksheetMenuBar(bg);

    add(editBar, BorderLayout.PAGE_START);
    add(worksheetPanel.getScrollPane());
    setJMenuBar(menuBar);
    setSize(1300, 600);
    setTitle(title);

    setVisible(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  @Override
  public void setListeners(ActionListener clicks, DocumentListener cellEdits, KeyListener keys) {
    editBar.getConfirmButton().addActionListener(clicks);
    editBar.getRejectButton().addActionListener(clicks);
    editBar.getEditField().addActionListener(clicks);
    editBar.getEditField().getDocument().addDocumentListener(cellEdits);
    menuBar.getSave().addActionListener(clicks);
    menuBar.getOpen().addActionListener(clicks);
    this.addKeyListener(keys);
  }

  @Override
  public String getEditText() {
    return editBar.getEditField().getText();
  }

  @Override
  public void setEditText(String editText) {
    if (editBar != null) {
      editBar.getEditField().setText(editText);
    }
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
  public Appendable getAppendable() {
    return new StringBuilder();
  }

  @Override
  public boolean cellsSelected() {
    return worksheetPanel.getTableModel().minSelectionCol() != -1;
  }

  @Override
  public void notifyCellChanged(Coord coord) {
    worksheetPanel.getTableModel().fireTableDataChanged();
    worksheetPanel.getTableModel().cellUpdated(coord);
  }

  @Override
  public void setWindowTitle(String title) {
    this.title = title;
    setTitle(title);
  }
}
