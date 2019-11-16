package edu.cs3500.spreadsheets.view;

import java.io.IOException;
import java.util.Map;

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
        if (model.getCellAt(entry.getKey()).evaluate(model.getModel()).length() > 7
                && model.getCellAt(entry.getKey()).evaluate(model.getModel()).substring(0, 7)
                .equals("ERROR: ")) {
          ap.append("# " + model.getCellAt(entry.getKey()).evaluate(model.getModel()) + "\n");
          ap.append(entry.getKey().toString() + " "
                  + model.getCellAt(entry.getKey()).getRawContents() + "\n");
        } else {
          ap.append(entry.getKey().toString() + " "
                  + model.getCellAt(entry.getKey()).evaluate(model.getModel()) + "\n");
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
}
