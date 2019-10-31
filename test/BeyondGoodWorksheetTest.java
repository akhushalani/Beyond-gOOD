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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Test class for the BeyondGoodWorksheet class.
 */
public class BeyondGoodWorksheetTest {
  private Worksheet ws;
  private Formula form1;
  private List<Formula> lof;
  private List<Coord> loCoord;
  private List<Cell> loCell;
  private Coord coord1;


  private void initVars() {
    ws = new BeyondGoodWorksheet();
    loCoord = new ArrayList<>();
    loCell = new ArrayList<>();
    lof = new ArrayList<>();
    Coord coord2;
    Coord coord3;
    Coord coord4;
    Coord coord5;
    Coord coord6;
    Coord coord7;
    Coord coord8;
    Coord coord9;
    Coord coord10;
    Coord coord11;
    Coord coord12;
    Coord coord13;
    Coord coord14;
    Coord coord15;
    Coord coord16;
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
    Cell cell11;
    Cell cell12;
    Cell cell13;
    Formula form14;
    Cell cell14;
    Formula form15;
    Cell cell15;
    Formula form16;
    Cell cell16;
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
    ArrayList<String> los;
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
    loCoord.add(coord1);
    loCoord.add(coord2);
    loCoord.add(coord3);
    loCoord.add(coord4);
    loCoord.add(coord5);
    loCoord.add(coord6);
    loCoord.add(coord7);
    loCoord.add(coord8);
    loCoord.add(coord9);
    loCoord.add(coord10);
    loCoord.add(coord11);
    loCoord.add(coord12);
    loCoord.add(coord13);
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
    lof = new ArrayList<>();
    lof.add(form1);
    lof.add(form2);
    lof.add(form3);
    lof.add(form4);
    lof.add(form5);
    lof.add(form6);
    lof.add(form7);
    lof.add(form8);
    lof.add(form9);
    lof.add(form10);
    lof.add(form11);
    lof.add(form12);
    lof.add(form13);
    String mtString = "";

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
    loCell.add(cell1);
    loCell.add(cell2);
    loCell.add(cell3);
    loCell.add(cell4);
    loCell.add(cell5);
    loCell.add(cell6);
    loCell.add(cell7);
    loCell.add(cell8);
    loCell.add(cell9);
    loCell.add(cell10);
    loCell.add(cell11);
    loCell.add(cell12);
    loCell.add(cell13);
    coord14 = new Coord(3, 3);
    form14 = new Reference(coord14);
    cell14 = new FormulaCell(coord14, form14, mtString);
    coord15 = new Coord(79, 97);
    form15 = new Reference(coord15);
    coord16 = new Coord(49, 90);
    cell15 = new FormulaCell(coord16, form15, mtString);
    form16 = new Reference(coord16);
    cell16 = new FormulaCell(coord15, form16, mtString);
  }

  @Test
  public void testBlankCell() {
    this.initVars();
    assertNull(ws.getCellAt(new Coord(924, 999)));
  }

  @Test
  public void getCellAt() {
    this.initVars();
    assertNull( ws.getCellAt(coord1));
    for (int i = 0; i < loCell.size(); i++) {
      ws.setCell(loCoord.get(i), loCell.get(i));
      assertEquals(loCell.get(i), ws.getCellAt(loCoord.get(i)));
      assertEquals(lof.get(i), loCell.get(i).getFormula());
    }
  }

  @Test
  public void setCell() {
    this.initVars();
    for (int i = 0; i < loCell.size(); i++) {
      ws.setCell(loCoord.get(i), loCell.get(i));
      assertEquals(loCell.get(i), ws.getCellAt(loCoord.get(i)));
      assertEquals(lof.get(i), loCell.get(i).getFormula());
    }
  }

  @Test
  public void getWorksheet() {
    this.initVars();
    assertTrue(ws.getWorksheet().isEmpty());
    for (int i = 0; i < loCell.size(); i++) {
      ws.setCell(loCoord.get(i), loCell.get(i));
    }
    for (int i = 0; i < loCell.size(); i++) {
      assertEquals(ws.getCellAt(loCoord.get(i)), ws.getWorksheet().get(loCoord.get(i)));
    }
  }

  @Test
  public void getCalculatedReference() {
    this.initVars();
    this.ws.addCalculatedReference(coord1, form1);
    assertEquals(form1, this.ws.getCalculatedReference(coord1));
  }

  @Test
  public void hasCalculatedReference() {
    this.initVars();
    this.ws.addCalculatedReference(coord1, form1);
    assertTrue(this.ws.hasCalculatedReference(coord1));
  }

  @Test
  public void addCalculatedReference() {
    this.initVars();
    this.ws.addCalculatedReference(coord1, form1);
    assertTrue(this.ws.hasCalculatedReference(coord1));
  }

  @Test
  public void clearCalculatedReferences() {
    this.initVars();
    for (int i = 0; i < loCell.size(); i++) {
      this.ws.addCalculatedReference(loCoord.get(i), lof.get(i));
    }
    this.ws.clearCalculatedReferences();
    for (int i = 0; i < loCell.size(); i++) {
      assertFalse(this.ws.hasCalculatedReference(loCoord.get(i)));
    }
  }
}