package edu.cs3500.spreadsheets.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FormulaCellTest {
  Worksheet ws;
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
  Coord coord11;
  Coord coord12;
  Coord coord13;
  Coord coord14;
  Coord coord15;
  Coord coord16;
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
  List<Coord> loCoord1;
  List<Coord> loCoord2;
  List<Coord> loCoord3;
  List<Cell> loCell1;
  List<Cell> loCell2;
  List<Cell> loCell3;
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


  public void initVars() {
    ws = new BeyondGoodWorksheet();
    loCoord1 = new ArrayList<>();
    loCoord2 = new ArrayList<>();
    loCoord3 = new ArrayList<>();
    loCell1 = new ArrayList<>();
    loCell2 = new ArrayList<>();
    loCell3 = new ArrayList<>();
    lof1 = new ArrayList<>();
    lof2 = new ArrayList<>();
    lof3 = new ArrayList<>();

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

    String mtString = "";
    cell1 = new FormulaCell(coord1, form1, mtString);
    cell2 = new FormulaCell(coord2, form2, mtString);
    cell3 = new FormulaCell(coord3, form3, mtString);
    cell4 = new FormulaCell(coord4, form4, mtString);
    cell5 = new FormulaCell(coord5, form5, mtString);
    cell6 = new FormulaCell(coord6, form6, mtString);
    cell7 = new FormulaCell(coord7, form7, mtString);
    cell8 = new FormulaCell(coord8, form8, mtString);
    cell9 = new FormulaCell(coord9, form9, mtString);
    cell10 = new FormulaCell(coord10, form10, mtString);
    cell11 = new FormulaCell(coord11, form11, mtString);
    cell12 = new FormulaCell(coord12, form12, mtString);
    cell13 = new FormulaCell(coord13, form13, mtString);

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
  public void evaluate() {
    for (int i = 0; i < loCell1.size(); i++) {
      ws.setCell(loCoord1.get(i), loCell1.get(i));
      //assertEquals(loCell1.get(i).evaluate(ws));
    }
  }

  @Test
  public void getFormula() {
  }

  @Test
  public void references() {
  }

  @Test
  public void getCellName() {
  }

  @Test
  public void getRawContents() {
  }

  @Test
  public void cyclicReference() {
  }

  @Test
  public void testEquals() {
  }

  @Test
  public void testHashCode() {
  }
}