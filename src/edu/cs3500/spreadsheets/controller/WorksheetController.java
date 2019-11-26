package edu.cs3500.spreadsheets.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import edu.cs3500.spreadsheets.model.BeyondGoodWorksheetBuilder;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetAdapter;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.sexp.CellSexpVisitor;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.ValueCellSexpVisitor;
import edu.cs3500.spreadsheets.view.AdvancedWorksheetEditorVisualView;
import edu.cs3500.spreadsheets.view.WorksheetTextualView;
import edu.cs3500.spreadsheets.view.WorksheetView;

/**
 * A controller for a worksheet.
 */
public class WorksheetController implements ActionListener, DocumentListener, KeyListener {
  private Worksheet model;
  private WorksheetView view;

  /**
   * Public constructor for a WorksheetController.
   * @param model a worksheet model
   * @param view a worksheet view
   */
  public WorksheetController(Worksheet model, WorksheetView view) {
    this.model = model;
    this.view = view;
    this.view.setListeners(this, this, this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Confirm Button":
        if (view.cellsSelected()) {
          Coord sel = view.getFirstSelected();
          ArrayList<Coord> refs = model.referTo(sel);
          if (view.getEditText().equals("")) {
            model.setCell(view.getFirstSelected(), null);
          } else {
            String contents = view.getEditText();
            Parser p = new Parser();
            if (contents.substring(0, 1).equals("=")) {
              Sexp sexpContents = p.parse(contents.substring(1));
              CellSexpVisitor cellVisitor = new CellSexpVisitor(sel, contents, model);
              model.setCell(sel, sexpContents.accept(cellVisitor));
            } else {
              Sexp sexpContents = p.parse(contents);
              ValueCellSexpVisitor valueCellVisitor
                      = new ValueCellSexpVisitor(sel, contents, model);
              model.setCell(sel, sexpContents.accept(valueCellVisitor));
            }
          }
          view.notifyCellChanged(sel);
          /*for (Coord ref : refs) {
            view.notifyCellChanged(ref);
          }*/
        }
        break;
      case "Reject Button":
        if (view.cellsSelected()) {
          Coord sel = view.getFirstSelected();
          if (model.getWorksheet().containsKey(sel)) {
            view.setEditText(model.getCellAt(sel).getRawContents());
          }
        }
        break;
      case "Save":
        JFileChooser save = new JFileChooser();
        int saveVal = save.showSaveDialog(null);
        if (saveVal == JFileChooser.APPROVE_OPTION) {
          String filename = save.getSelectedFile().getName();
          String directory = save.getCurrentDirectory().toString();
          String savePath = "";
          if (!(filename.length() > 5
                  && filename.substring(filename.length() - 5).toLowerCase().equals(".good"))) {
            filename += ".gOOD";
          }
          savePath += directory + "/" + filename;

          try {
            PrintWriter pw = new PrintWriter(savePath);
            WorksheetTextualView view = new WorksheetTextualView(
                    new WorksheetAdapter(model), pw);
            view.renderView();
            pw = (PrintWriter) view.getAppendable();
            pw.close();
            view.setWindowTitle(filename);
          } catch (FileNotFoundException ex) {
            // will never run, creating a new file with String name
            throw new IllegalArgumentException("Invalid file name");
          }
        }
        break;
      case "Open":
        JFileChooser open = new JFileChooser();
        int openVal = open.showOpenDialog(null);
        if (openVal == JFileChooser.APPROVE_OPTION) {
          String filename = open.getSelectedFile().getName();
          String directory = open.getCurrentDirectory().toString();
          String openPath = directory + "/" + filename;
          try {
            Worksheet worksheet = WorksheetReader.read(new BeyondGoodWorksheetBuilder(),
                    new FileReader(openPath));
            AdvancedWorksheetEditorVisualView view
                    = new AdvancedWorksheetEditorVisualView(new WorksheetAdapter(worksheet), filename);
            view.renderView();
            WorksheetController controller = new WorksheetController(worksheet, view);
          } catch (FileNotFoundException fnfe) {
            System.out.println("Insufficient arguments, file specified does not exist");
          }
        }
        break;
    }
  }

  @Override
  public void insertUpdate(DocumentEvent e) {
    documentUpdated(e);
  }

  @Override
  public void removeUpdate(DocumentEvent e) {
    documentUpdated(e);
  }

  @Override
  public void changedUpdate(DocumentEvent e) {
    // won't be called on a plain text document
  }

  // Should be run when the document is changed.
  private void documentUpdated(DocumentEvent e) {
    Runnable doUpdate = () -> {
      view.setEditText(e.getDocument().toString());
    };

    //SwingUtilities.invokeLater(doUpdate);
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {
      System.out.println("Delete pressed");
      if (view.cellsSelected()) {
        int minCol = view.getMinSelection().col;
        int maxCol = view.getMaxSelection().col;
        int minRow = view.getMinSelection().row;
        int maxRow = view.getMaxSelection().row;
        for (int i = minCol; i <= maxCol; i++) {
          for (int j = minRow; j <= maxRow; j++) {
            Coord c = new Coord(i, j);
            model.setCell(c, null);
            view.notifyCellChanged(c);
          }
        }
      }
    }
  }
}
