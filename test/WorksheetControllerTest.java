import org.junit.Test;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.cs3500.spreadsheets.controller.WorksheetController;
import edu.cs3500.spreadsheets.model.BeyondGoodWorksheetBuilder;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetAdapter;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.WorksheetVisualView;

import static org.junit.Assert.assertEquals;


/**
 * A class for testing the functions and wiring of the WorksheetController.
 */
public class WorksheetControllerTest {
  Worksheet model1;
  WorksheetModelMock mockmodel;
  WorksheetAdapter adapter;
  WorksheetVisualView view;
  WorksheetViewMock mockview;
  WorksheetController wc;
  ActionEvent confirm;
  ActionEvent reject;
  ActionEvent save;
  ActionEvent open;
  StringBuilder log1;
  StringBuilder log2;
  StringBuilder log3;
  StringBuilder log4;
  StringBuilder log5;
  StringBuilder log6;
  StringBuilder log7;
  StringBuilder log8;

  // Initializes the variables for tests in this file.
  private void initVars() throws FileNotFoundException {
    this.model1 = WorksheetReader.read(new BeyondGoodWorksheetBuilder(),
            new FileReader("resources/test1.gOOD"));
    this.mockmodel = new WorksheetModelMock(this.model1);
    this.adapter = new WorksheetAdapter(mockmodel);
    this.view = new WorksheetVisualView(adapter, "test1");
    this.mockview = new WorksheetViewMock(view);
    this.wc = new WorksheetController(mockmodel, mockview);
    this.confirm = new ActionEvent(view,
            ActionEvent.ACTION_PERFORMED, "Confirm Button");
    this.reject = new ActionEvent(view, ActionEvent.ACTION_PERFORMED,
            "Reject Button");
    this.save = new ActionEvent(view, ActionEvent.ACTION_PERFORMED,
            "Save");
    this.open = new ActionEvent(view, ActionEvent.ACTION_PERFORMED,
            "Open");
    this.log1 = new StringBuilder();
    this.log1.append("setListeners\n"
            + "cellsSelected\n"
            + "getFirstSelected\n"
            + "getEditText\n"
            + "getFirstSelected\n"
            + "notifyCellChanged: A1\n");
    this.log2 = new StringBuilder();
    this.log2.append("setListeners\n"
            + "cellsSelected\n"
            + "getFirstSelected\n"
            + "setEditText: 3\n");
    this.log3 = new StringBuilder();
    this.log3.append("setListeners\n");
    this.log4 = new StringBuilder();
    this.log4.append("setListeners\n");
    this.log5 = new StringBuilder();
    this.log5.append("setListeners\n"
            + "cellsSelected\n"
            + "getMinSelection\n"
            + "getMaxSelection\n"
            + "getMinSelection\n"
            + "getMaxSelection\n"
            + "notifyCellChanged: A1\n");
    this.log6 = new StringBuilder();
    this.log6.append("referTo\n"
            + "setCell\n");
    this.log7 = new StringBuilder();
    this.log7.append("getWorksheet\n"
            + "getCellAt\n");
    this.log8 = new StringBuilder();
    this.log8.append("setCell\n");
  }

  @Test
  public void confirmCell() throws FileNotFoundException {
    this.initVars();
    wc.confirmCell();
    // Have all the functions in the view been called correctly?
    assertEquals(log1.toString(), mockview.log.toString());
    // Have all the functions in the model been called correctly?
    assertEquals(log6.toString(), mockmodel.log.toString());
  }

  @Test
  public void rejectCell() throws FileNotFoundException {
    this.initVars();
    wc.rejectCell();
    // Have all the functions in the view been called correctly?
    assertEquals(log2.toString(), mockview.log.toString());
    // Have all the functions in the model been called correctly?
    assertEquals(log7.toString(), mockmodel.log.toString());
  }

  @Test
  public void save() throws FileNotFoundException {
    this.initVars();
    wc.save();
    // Have all the functions in the view been called correctly?
    assertEquals(log3.toString(), mockview.log.toString());
  }

  @Test
  public void open() throws FileNotFoundException {
    this.initVars();
    wc.open();
    // Have all the functions in the view been called correctly?
    assertEquals(log4.toString(), mockview.log.toString());
  }

  @Test
  public void deleteCells() throws FileNotFoundException {
    this.initVars();
    wc.deleteCells();
    // Have all the functions in the view been called correctly?
    assertEquals(log5.toString(), mockview.log.toString());
    // Have all the functions in the model been called correctly?
    assertEquals(log8.toString(), mockmodel.log.toString());
  }
}