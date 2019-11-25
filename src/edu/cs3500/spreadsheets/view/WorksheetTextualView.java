package edu.cs3500.spreadsheets.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Map;

import javax.swing.event.DocumentListener;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetAdapter;

/**
 * A textual view of a worksheet model.
 */
public class WorksheetTextualView implements WorksheetView {
  private WorksheetAdapter model;
  private Appendable ap;

  /**
   * Public constructor for the WorksheetTextualView class.
   *
   * @param model the model the view is representing
   * @param ap the appendable to which the view will append its output
   */
  public WorksheetTextualView(WorksheetAdapter model, Appendable ap) {
    this.model = model;
    this.ap = ap;
  }

  @Override
  public void renderView() {
    for (Map.Entry<Coord, Cell> entry : model.getWorksheet().entrySet()) {
      try {
        if (model.getCellAt(entry.getKey()).evaluate(model.getModel(), false).length() > 7
                && model.getCellAt(entry.getKey()).evaluate(model.getModel(), false).substring(0, 7)
                .equals("ERROR: ")) {
          ap.append("# " + model.getCellAt(entry.getKey()).evaluate(model.getModel(), false) + "\n");
          ap.append(entry.getKey().toString() + " "
                  + model.getCellAt(entry.getKey()).getRawContents() + "\n");
        } else {
          ap.append(entry.getKey().toString() + " "
                  + model.getCellAt(entry.getKey()).evaluate(model.getModel(), false) + "\n");
        }
      } catch (IOException ex) {
        // Shouldn't ever happen, all Strings being appended will be valid
        throw new IllegalArgumentException("Invalid IO passed to Appendable");
      }
    }
  }

  @Override
  public Appendable getAppendable() {
    return this.ap;
  }

  @Override
  public boolean cellsSelected() {
    return false;
  }

  @Override
  public Coord getFirstSelected() {
    return null;
  }

  @Override
  public Coord getMinSelection() {
    return null;
  }

  @Override
  public Coord getMaxSelection() {
    return null;
  }

  @Override
  public String getEditText() {
    return null;
  }

  @Override
  public void setEditText(String editText) {

  }

  @Override
  public void notifyCellChanged(Coord coord) {

  }

  @Override
  public void setListeners(ActionListener clicks, DocumentListener cellEdits, KeyListener keys) {

  }

  @Override
  public void setWindowTitle(String title) {

  }
}
