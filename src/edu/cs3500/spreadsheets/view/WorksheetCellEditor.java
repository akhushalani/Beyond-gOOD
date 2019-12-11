package edu.cs3500.spreadsheets.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellRenderer;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetAdapter;

/**
 * A cell editor for a worksheet.
 */
public class WorksheetCellEditor extends DefaultCellEditor {
  private JTextField textField;
  private WorksheetAdapter model;
  private int width;

  /**
   * Public constructor for a WorksheetCellEditor.
   * @param textField a textfield for editing
   * @param model a worksheet model adapter
   */
  public WorksheetCellEditor(JTextField textField, WorksheetAdapter model) {
    super(textField);
    this.textField = textField;
    this.textField.getDocument().putProperty("owner", "cellEditor");
    this.model = model;
  }

  /**
   * Method for creating a worksheet cell editor.
   * @param model a worksheet model adaptor
   * @return a new worksheet cell editor
   */
  public static WorksheetCellEditor make(WorksheetAdapter model) {
    JTextField textField = new JTextField();

    return new WorksheetCellEditor(textField, model);
  }

  @Override
  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
                                               int row, int column) {
    String rawContents = "";
    if (model.getWorksheet().containsKey(new Coord(column + 1, row + 1))) {
      rawContents = model.getCellAt(new Coord(column + 1, row + 1)).getRawContents();
    }

    TableCellRenderer renderer = table.getCellRenderer(row, column);
    Component c = renderer.getTableCellRendererComponent(table, value,
            isSelected, true, row, column);
    textField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, c.getFont().getSize()));

    width = table.getCellRect(row, column, true).width;
    int i = column;
    while (textField.getFontMetrics(textField.getFont()).stringWidth(rawContents) > width) {
      i++;
      width += table.getCellRect(row, i, true).width;
    }
    textField.setSize(new Dimension(width, table.getCellRect(row, column, true).height));
    CompoundBorder b = BorderFactory.createCompoundBorder();
    b = BorderFactory.createCompoundBorder(b,
            BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0, 7, 224)));
    b = BorderFactory.createCompoundBorder(b,
            BorderFactory.createEmptyBorder(2, 2, 2, 2));
    textField.setBorder(b);


    return super.getTableCellEditorComponent(table, rawContents, isSelected, row, column);
  }

  public void setText(String text) {
    textField.setText(text);
  }

  public void addDocumentListener(DocumentListener dl) {
    this.textField.getDocument().addDocumentListener(dl);
  }

  public void selectAll() {
    this.textField.selectAll();
    System.out.println("Text selected");
  }

  public int getWidth() {
    return width;
  }
}
