package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * A visitor object which visits a Formula and retrieves a List of all of the coords
 * referenced within it.
 */
public class ReferenceFormulaVisitor implements FormulaVisitor<ArrayList<Coord>> {
  private ArrayList<Coord> refList;
  private Worksheet worksheet;
  private Coord cellLoc;

  /**
   * Constructor for creating ReferenceFormulaVisitor object.
   * @param worksheet the worksheet holding the formula being referenced
   * @param cellLoc the location of the cell holding the formula
   */
  public ReferenceFormulaVisitor(Worksheet worksheet, Coord cellLoc) {
    this.refList = new ArrayList<>();
    this.worksheet = worksheet;
    this.cellLoc = cellLoc;
  }

  @Override
  public ArrayList<Coord> visitStringValue(StringValue s) {
    return this.refList;
  }

  @Override
  public ArrayList<Coord> visitReference(Reference r) {
    this.refList.add(parseCoord(r.getPrintString(worksheet, cellLoc, false)));

    return null;
  }

  @Override
  public ArrayList<Coord> visitFunction(Function f) {
    ArrayList<Coord> innerRefs = new ArrayList<>();
    ReferenceFormulaVisitor innerRefVisitor = new ReferenceFormulaVisitor(worksheet, cellLoc);
    for (Object arg : f.getArguments()) {
      Formula fArg = (Formula) arg;
      innerRefs.addAll(fArg.accept(innerRefVisitor));
    }
    this.refList.addAll(innerRefs);

    return this.refList;
  }

  @Override
  public ArrayList<Coord> visitDoubleValue(DoubleValue d) {
    return this.refList;
  }

  @Override
  public ArrayList<Coord> visitBooleanValue(BooleanValue b) {
    return this.refList;
  }

  private Coord parseCoord(String s) {
    int firstNum = 0;
    int lastLetter = 0;

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        firstNum = i;
        break;
      }
    }

    for (int j = s.length() - 1; j >= 0; j--) {
      char d = s.charAt(j);
      if (Character.isLetter(d)) {
        lastLetter = j;
        break;
      }
    }

    int col = Coord.colNameToIndex(s.substring(0, lastLetter + 1));
    int row = Integer.parseInt(s.substring(firstNum));

    return new Coord(col, row);
  }
}
