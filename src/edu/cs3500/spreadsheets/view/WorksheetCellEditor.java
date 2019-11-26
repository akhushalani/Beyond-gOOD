package edu.cs3500.spreadsheets.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetAdapter;

/**
 * A cell editor for a worksheet.
 */
public class WorksheetCellEditor extends DefaultCellEditor {
  private JTextField textField;
  private WorksheetAdapter model;

  /**
   * Public constructor for a WorksheetCellEditor.
   * @param textField a textfield for editing
   * @param model a worksheet model adapter
   */
  public WorksheetCellEditor(JTextField textField, WorksheetAdapter model) {
    super(textField);
    this.textField = textField;
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
  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    textField.setSize(new Dimension(table.getCellRect(row, column, true).width,
            table.getCellRect(row, column, true).height));
    TableCellRenderer renderer = table.getCellRenderer(row, column);
    Component c = renderer.getTableCellRendererComponent(table, value,
            isSelected, true, row, column);
    textField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, c.getFont().getSize()));
    textField.setBorder(((JComponent) c).getBorder());

    String rawContents = "";
    if (model.getWorksheet().containsKey(new Coord(column + 1, row + 1))) {
      rawContents = model.getCellAt(new Coord(column + 1, row + 1)).getRawContents();
    }
    return super.getTableCellEditorComponent(table, rawContents, isSelected, row, column);
  }
}
