package edu.cs3500.spreadsheets.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.DocumentListener;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetAdapter;

public class AdvancedWorksheetEditorVisualView extends JFrame implements WorksheetView {
  private WorksheetAdapter model;
  private WorksheetPanel worksheetPanel;
  private WorksheetAttributeBar attributeBar;
  private WorksheetEditBar editBar;
  private WorksheetMenuBar menuBar;
  private String title;

  /**
   * Public constructor for the WorksheetEditorVisualView class.
   *
   * @param model the model being represented by the view
   */
  public AdvancedWorksheetEditorVisualView(WorksheetAdapter model, String title) {
    this.model = model;
    this.title = title;
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
      Color oldColor = worksheetPanel.getTableModel().getAttributes()
              .get(worksheetPanel.getTableModel().getFirstSelection()).getTextColor();
      Color color = JColorChooser.showDialog(attributeBar, "Choose text color", oldColor);
      worksheetPanel.getTableModel().setColor(CellAttribute.TEXT_COLOR, color);
    });

    worksheetPanel.addCoordDisplay(editBar.getCoordDisplay());
    worksheetPanel.addEditField(editBar.getEditField());

    menuBar = new WorksheetMenuBar(bg);

    add(toolbars, BorderLayout.PAGE_START);
    add(worksheetPanel.getScrollPane(), BorderLayout.CENTER);
    setSize(1300, 600);
    setJMenuBar(menuBar);
    setTitle(title);

    setVisible(true);
    //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
  public void setListeners(ActionListener clicks, DocumentListener cellEdits, KeyListener keys) {
    editBar.getConfirmButton().addActionListener(clicks);
    editBar.getEditField().addActionListener(clicks);
    editBar.getEditField().getDocument().addDocumentListener(cellEdits);
    menuBar.getSave().addActionListener(clicks);
    menuBar.getOpen().addActionListener(clicks);
    worksheetPanel.addKeyListener(keys);
  }

  @Override
  public void setWindowTitle(String title) {
    this.title = title;
    setTitle(title);
  }
}
