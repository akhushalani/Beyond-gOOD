package edu.cs3500.spreadsheets.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import edu.cs3500.spreadsheets.model.WorksheetAdapter;

public class AdvancedWorksheetEditorVisualView extends JFrame implements WorksheetView {
  private WorksheetAdapter model;

  /**
   * Public constructor for the WorksheetEditorVisualView class.
   *
   * @param model the model being represented by the view
   */
  public AdvancedWorksheetEditorVisualView(WorksheetAdapter model) {
    this.model = model;
  }

  @Override
  public void renderView() {
    WorksheetPanel worksheetPanel = new WorksheetPanel(model, this, true);

    Color bg = new Color(50, 50, 50);

    JPanel toolbars = new JPanel(new GridLayout(0, 1));
    WorksheetAttributeBar attributeBar = new WorksheetAttributeBar(bg);
    WorksheetEditBar editBar = new WorksheetEditBar(bg);
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

    add(toolbars, BorderLayout.PAGE_START);
    add(worksheetPanel.getScrollPane(), BorderLayout.CENTER);
    setSize(1300, 600);

    setVisible(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  @Override
  public Appendable getAppendable() {
    return new StringBuilder();
  }
}
