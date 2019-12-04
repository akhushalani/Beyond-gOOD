package edu.cs3500.spreadsheets.provider.controller;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.provider.model.Worksheet;
import edu.cs3500.spreadsheets.provider.view.WorksheetView;

/**
 *
 */
public class ProviderWorksheetController implements Controller, ActionListener, MouseListener {
  private WorksheetView view;
  private Worksheet model;

  /**
   *
   * @param model a Worksheet model
   * @param view a Worksheet view
   */
  public ProviderWorksheetController(Worksheet model, WorksheetView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void goController() {
    view.setInputListener(this);
    view.setCellSelectionListener(this);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "addRow":
        view.addRow();
        view.refresh();
        break;
      case "addColumn":
        view.addCol();
        view.refresh();
        break;
      case "accept":
        model.updateCell(getSelectedCoord(view.getSelected()), view.getInputBoxText());
        view.refresh();
        break;
      case "decline":
        view.setInputField();
        break;
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    view.selectCell(e.getPoint());
    view.refresh();
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // do nothing
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // do nothing
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // do nothing
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // do nothing
  }

  private Coord getSelectedCoord(Rectangle selected) {
    int row = -1;
    int col = -1;
    for (int i = 0; i < view.getRectangles().size(); i++) {
      for (int j = 0; j < view.getRectangles().get(i).size(); j++) {
        if (selected.equals(view.getRectangles().get(i).get(j))) {
          row = i + 1;
          col = j + 1;
        }
      }
    }

    if (row == -1 || col == -1) {
      throw new IllegalArgumentException("Selected cell not found.");
    }
    return new Coord(col, row);
  }
}
