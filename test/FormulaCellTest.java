import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.model.BeyondGoodWorksheet;
import edu.cs3500.spreadsheets.model.BooleanValue;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.FormulaCell;
import edu.cs3500.spreadsheets.model.LessThan;
import edu.cs3500.spreadsheets.model.Product;
import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.StringValue;
import edu.cs3500.spreadsheets.model.Sum;
import edu.cs3500.spreadsheets.model.Worksheet;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the FormulaCell class.
 */
public class FormulaCellTest {
  private Worksheet ws;
  private Coord coord11;
  private Coord coord12;
  private List<Coord> loCoord1;
  private List<Coord> loCoord2;
  private List<Coord> loCoord3;
  private List<Cell> loCell1;
  private List<Cell> loCell2;
  private List<Cell> loCell3;
  private Cell cell11;
  private Cell cell12;
  private Cell cell13;
  private ArrayList<Cell> masterCellList;
  private ArrayList<Formula> masterFormulaList;
  private ArrayList<Coord> masterCoordList;
  private ArrayList<String> los;


  private void initVars() {
    ws = new BeyondGoodWorksheet();
    loCoord1 = new ArrayList<>();
    loCoord2 = new ArrayList<>();
    loCoord3 = new ArrayList<>();
    loCell1 = new ArrayList<>();
    loCell2 = new ArrayList<>();
    loCell3 = new ArrayList<>();

    Cell cell1;
    Cell cell2;
    Cell cell3;
    Cell cell4;
    Cell cell5;
    Cell cell6;
    Cell cell7;
    Cell cell8;
    Cell cell9;
    Cell cell10;
    Formula form14;
    Cell cell14;
    Formula form15;
    Cell cell15;
    Formula form16;
    Cell cell16;
    Formula form17;
    Cell cell17;
    Formula form18;
    Cell cell18;
    Formula form19;
    Cell cell19;
    String s1;
    String s2;
    String s3;
    String s4;
    String s5;
    String s6;
    String s7;
    String s8;
    String s9;
    String s10;
    String s11;
    String s12;
    String s13;
    Coord coord13;
    Coord coord14;
    Coord coord15;
    Coord coord16;
    Coord coord17;
    Coord coord18;
    Coord coord19;
    Formula form1;
    Formula form2;
    Formula form3;
    Formula form4;
    Formula form5;
    Formula form6;
    Formula form7;
    Formula form8;
    Formula form9;
    Formula form10;
    Formula form11;
    Formula form12;
    Formula form13;
    ArrayList<Formula> lessThanArgs;
    ArrayList<Formula> prodArgs;
    ArrayList<Formula> sumArgs;
    List<Formula> lof1;
    List<Formula> lof2;
    List<Formula> lof3;
    
    lof1 = new ArrayList<>();
    lof2 = new ArrayList<>();
    lof3 = new ArrayList<>();

    Coord coord1;
    Coord coord2;
    Coord coord3;
    Coord coord4;
    Coord coord5;
    Coord coord6;
    Coord coord7;
    Coord coord8;
    Coord coord9;
    Coord coord10;
    
    coord1 = new Coord(1, 1);
    coord2 = new Coord(2, 1);
    coord3 = new Coord(1, 2);
    coord4 = new Coord(7, 1);
    coord5 = new Coord(1, 20);
    coord6 = new Coord(20, 20);
    coord7 = new Coord(99, 109);
    coord8 = new Coord(40, 10);
    coord9 = new Coord(1, 9);
    coord10 = new Coord(1000, 2);
    coord11 = new Coord(5, 5);
    coord12 = new Coord(9, 9);
    coord13 = new Coord(19, 19);
    loCoord1.add(coord1);
    loCoord1.add(coord2);
    loCoord1.add(coord3);
    loCoord1.add(coord4);
    loCoord1.add(coord5);
    loCoord2.add(coord6);
    loCoord2.add(coord7);
    loCoord3.add(coord8);
    loCoord3.add(coord9);
    loCoord3.add(coord10);

    masterCoordList = new ArrayList<>();
    masterCellList = new ArrayList<>();
    masterFormulaList = new ArrayList<>();

    masterCoordList.add(coord1);
    masterCoordList.add(coord2);
    masterCoordList.add(coord3);
    masterCoordList.add(coord4);
    masterCoordList.add(coord5);
    masterCoordList.add(coord6);
    masterCoordList.add(coord7);
    masterCoordList.add(coord8);
    masterCoordList.add(coord9);
    masterCoordList.add(coord10);
    masterCoordList.add(coord11);
    masterCoordList.add(coord12);
    masterCoordList.add(coord13);

    form1 = new DoubleValue(1.0);
    form2 = new DoubleValue(20.0);
    form3 = new DoubleValue(-1.0);
    form4 = new DoubleValue(-7.0);
    form5 = new DoubleValue(0.0);
    form6 = new BooleanValue(true);
    form7 = new BooleanValue(false);
    form8 = new StringValue("");
    form9 = new StringValue("Kees");
    form10 = new StringValue("Abhinav");
    sumArgs = new ArrayList<>();
    sumArgs.add(form1);
    sumArgs.add(form2);
    form11 = new Sum(sumArgs);
    prodArgs = new ArrayList<>();
    prodArgs.add(form3);
    prodArgs.add(form4);
    form12 = new Product(prodArgs);
    lessThanArgs = new ArrayList<>();
    lessThanArgs.add(form5);
    lessThanArgs.add(form1);
    form13 = new LessThan(lessThanArgs);

    lof1.add(form1);
    lof1.add(form2);
    lof1.add(form3);
    lof1.add(form4);
    lof1.add(form5);

    lof2.add(form6);
    lof2.add(form7);

    lof3.add(form8);
    lof3.add(form9);
    lof3.add(form10);

    masterFormulaList.add(form1);
    masterFormulaList.add(form2);
    masterFormulaList.add(form3);
    masterFormulaList.add(form4);
    masterFormulaList.add(form5);
    masterFormulaList.add(form6);
    masterFormulaList.add(form7);
    masterFormulaList.add(form8);
    masterFormulaList.add(form9);
    masterFormulaList.add(form10);
    masterFormulaList.add(form11);
    masterFormulaList.add(form12);
    masterFormulaList.add(form13);

    los = new ArrayList<>();
    s1 = "yuh";
    s2 = "yah";
    s3 = "yeet";
    s4 = "a7j";
    s5 = "536435ng";
    s6 = "akfglrg";
    s7 = "sSSSFnfj";
    s8 = "[][][]";
    s9 = "data";
    s10 = "youknowhowitbe";
    s11 = "mamasaymamasa";
    s12 = "runitback";
    s13 = "butter";

    los.add(s1);
    los.add(s2);
    los.add(s3);
    los.add(s4);
    los.add(s5);
    los.add(s6);
    los.add(s7);
    los.add(s8);
    los.add(s9);
    los.add(s10);
    los.add(s11);
    los.add(s12);
    los.add(s13);

    cell1 = new FormulaCell(coord1, form1, s1);
    cell2 = new FormulaCell(coord2, form2, s2);
    cell3 = new FormulaCell(coord3, form3, s3);
    cell4 = new FormulaCell(coord4, form4, s4);
    cell5 = new FormulaCell(coord5, form5, s5);
    cell6 = new FormulaCell(coord6, form6, s6);
    cell7 = new FormulaCell(coord7, form7, s7);
    cell8 = new FormulaCell(coord8, form8, s8);
    cell9 = new FormulaCell(coord9, form9, s9);
    cell10 = new FormulaCell(coord10, form10, s10);
    cell11 = new FormulaCell(coord11, form11, s11);
    cell12 = new FormulaCell(coord12, form12, s12);
    cell13 = new FormulaCell(coord13, form13, s13);

    loCell1.add(cell1);
    loCell1.add(cell2);
    loCell1.add(cell3);
    loCell1.add(cell4);
    loCell1.add(cell5);

    loCell2.add(cell6);
    loCell2.add(cell7);

    loCell3.add(cell8);
    loCell3.add(cell9);
    loCell3.add(cell10);

    masterCellList.add(cell1);
    masterCellList.add(cell2);
    masterCellList.add(cell3);
    masterCellList.add(cell4);
    masterCellList.add(cell5);
    masterCellList.add(cell6);
    masterCellList.add(cell7);
    masterCellList.add(cell8);
    masterCellList.add(cell9);
    masterCellList.add(cell10);
    masterCellList.add(cell11);
    masterCellList.add(cell12);
    masterCellList.add(cell13);
    String mtString = "";
    coord14 = new Coord(3, 3);
    form14 = new Reference(coord14);
    cell14 = new FormulaCell(coord14, form14, mtString);
    coord15 = new Coord(79, 97);
    form15 = new Reference(coord15);
    coord16 = new Coord(49, 90);
    cell15 = new FormulaCell(coord16, form15, mtString);
    form16 = new Reference(coord16);
    cell16 = new FormulaCell(coord15, form16, mtString);

    coord17 = new Coord(700, 700);
    coord18 = new Coord(700, 701);
    coord19 = new Coord(700, 702);
    form17 = new Reference(coord17);
    form18 = new Reference(coord18);
    form19 = new DoubleValue(7);
    cell17 = new FormulaCell(coord17, form17, mtString);
    cell18 = new FormulaCell(coord18, form18, mtString);
    cell19 = new FormulaCell(coord19, form19, mtString);
  }

  @Test
  public void evaluate() {
    this.initVars();
    for (int i = 0; i < loCell1.size(); i++) {
      ws.setCell(loCoord1.get(i), loCell1.get(i));
      //assertEquals(loCell1.get(i).evaluate(ws));
      assertEquals(loCell1.get(i).getFormula().getPrintString(ws, loCoord1.get(i)),
              loCell1.get(i).evaluate(ws));
    }
    for (int i = 0; i < loCell2.size(); i++) {
      ws.setCell(loCoord2.get(i), loCell2.get(i));
      assertEquals(loCell2.get(i).getFormula().getPrintString(ws, loCoord2.get(i)),
              loCell2.get(i).evaluate(ws));
    }
    for (int i = 0; i < loCell3.size(); i++) {
      ws.setCell(loCoord3.get(i), loCell3.get(i));
      assertEquals(loCell3.get(i).getFormula().getPrintString(ws, loCoord3.get(i)),
              loCell3.get(i).evaluate(ws));
    }
    ws.setCell(coord11, cell11);
    assertEquals(cell11.getFormula().getPrintString(ws, coord11), cell11.evaluate(ws));

    ws.setCell(coord12, cell12);
    assertEquals(cell12.getFormula().getPrintString(ws, coord11), cell12.evaluate(ws));

    ws.setCell(coord11, cell13);
    assertEquals(cell13.getFormula().getPrintString(ws, coord11), cell13.evaluate(ws));


  }

  @Test
  public void getFormula() {
    this.initVars();
    for (int i = 0; i < masterCellList.size(); i++) {
      assertEquals(masterFormulaList.get(i), masterCellList.get(i).getFormula());
    }
  }

  @Test
  public void getCellName() {
    this.initVars();
    for (int i = 0; i < masterCellList.size(); i++) {
      assertEquals(masterCoordList.get(i).toString(), masterCellList.get(i).getCellName());
    }
  }

  @Test
  public void getRawContents() {
    this.initVars();
    for (int i = 0; i < masterCellList.size(); i++) {
      assertEquals(los.get(i), masterCellList.get(i).getRawContents());
    }
  }
}