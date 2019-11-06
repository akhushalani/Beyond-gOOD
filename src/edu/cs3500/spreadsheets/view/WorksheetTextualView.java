package edu.cs3500.spreadsheets.view;

import java.io.IOException;
import java.util.Map;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;

public class WorksheetTextualView implements WorksheetView {
  private Worksheet model;
  private Appendable ap;

  public WorksheetTextualView(Worksheet model, Appendable ap) {
    this.model = model;
    this.ap = ap;
  }

  @Override
  public void updateModel(Worksheet model) {
    this.model = model;
  }

  @Override
  public void renderView() throws IOException {
    for (Map.Entry<Coord, Cell> entry : model.getWorksheet().entrySet()) {
      ap.append(entry.getKey().toString() + " "
              + model.getCellAt(entry.getKey()).evaluate(model) +"\n");
    }
  }

  @Override
  public Appendable getAppendable() {
    return this.ap;
  }
}
