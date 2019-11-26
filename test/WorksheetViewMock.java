import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.event.DocumentListener;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.view.WorksheetView;

public class WorksheetViewMock implements WorksheetView {
  private WorksheetView delegate;
  public Appendable log;

  public WorksheetViewMock(WorksheetView delegate) {
    this.delegate = delegate;
    this.log = new StringBuilder();
  }

  @Override
  public void renderView() {
    try {
      this.log.append("renderView\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    delegate.renderView();
  }

  @Override
  public Appendable getAppendable() {
    try {
      this.log.append("getAppendable\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return delegate.getAppendable();
  }

  @Override
  public boolean cellsSelected() {
    try {
      this.log.append("cellsSelected\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return true;
  }

  @Override
  public Coord getFirstSelected() {
    try {
      this.log.append("getFirstSelected\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return new Coord(1, 1);
  }

  @Override
  public Coord getMinSelection() {
    try {
      this.log.append("getMinSelection\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return new Coord(1,1);
  }

  @Override
  public Coord getMaxSelection() {
    try {
      this.log.append("getMaxSelection\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return new Coord(1, 1);
  }

  @Override
  public String getEditText() {
    try {
      this.log.append("getEditText\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return "";
  }

  @Override
  public void setEditText(String editText) {
    try {
      this.log.append("setEditText: " + editText + "\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void notifyCellChanged(Coord coord) {
    try {
      this.log.append("notifyCellChanged: " + coord.toString() + "\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setListeners(ActionListener clicks, DocumentListener cellEdits, KeyListener keys) {
    try {
      this.log.append("setListeners\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setWindowTitle(String title) {
    try {
      this.log.append("setWindowTitle: " + title + "\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    delegate.setWindowTitle(title);
  }
}
