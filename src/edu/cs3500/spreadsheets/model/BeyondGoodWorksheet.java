package edu.cs3500.spreadsheets.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Represents an implementation of the Worksheet interface meant for use in our BeyondGOOD
 *    implementation of Excel, which represents a WorkSheet in Excel.
 */
public class BeyondGoodWorksheet implements Worksheet {
  private HashMap<Coord, Cell> worksheet;
  private HashMap<Coord, Formula> calculatedReferences;
  private ArrayList<Coord> cyclicCoords;
  private HashMap<Coord, CellAttributes> attributes;

  /**
   * Represents the default Constructor for a BeyondGoodWorkSheet, which establishes a HashMap of
   *     Coords to Cells.
   */
  public BeyondGoodWorksheet() {
    this.worksheet = new HashMap<>();
    this.calculatedReferences = new HashMap<>();
    this.cyclicCoords = new ArrayList<>();
    this.attributes = new HashMap<>();
  }

  /**
   * Represents a constructor for BeyondGoodWorkSheet that takes in an already existent worksheet
   *    as a HashMap and returns a new WorkSheet using said input.
   * @param     worksheet represents a previously existing worksheet as a HashMap of Coords to
   *                      Cells.
   */
  public BeyondGoodWorksheet(HashMap<Coord, Cell> worksheet) {
    this.worksheet = worksheet;
    this.calculatedReferences = new HashMap<>();
    this.cyclicCoords = new ArrayList<>();
    this.attributes = new HashMap<>();
  }

  @Override
  public Cell getCellAt(Coord coord) {
    return worksheet.get(coord);
  }

  @Override
  public void setCell(Coord coord, Cell cell) {
    removeCyclicReference(coord);
    if (cell == null) {
      worksheet.remove(coord);
    } else if (!cyclicReference(coord, cell)) {
      worksheet.put(coord, cell);
    } else {
      throw new IllegalArgumentException("Error in cell " + coord.toString()
              + ": Cell contains a cyclic reference.");
    }
  }

  @Override
  public HashMap<Coord, Cell> getWorksheet() {
    HashMap<Coord, Cell> wsToBeReturned = new HashMap<>();
    for (Coord c : this.worksheet.keySet()) {
      wsToBeReturned.put(c, this.worksheet.get(c));
    }
    return wsToBeReturned;
  }

  @Override
  public Formula getCalculatedReference(Coord coord) {
    return calculatedReferences.get(coord);
  }

  @Override
  public boolean hasCalculatedReference(Coord coord) {
    return calculatedReferences.containsKey(coord);
  }

  @Override
  public void addCalculatedReference(Coord coord, Formula formula) {
    this.calculatedReferences.put(coord, formula);
  }

  @Override
  public void clearCalculatedReferences() {
    this.calculatedReferences.clear();
  }

  @Override
  public boolean containsCyclicReference(Coord coord) {
    return cyclicCoords.contains(coord);
  }

  @Override
  public void addCyclicReference(Coord coord) {
    this.cyclicCoords.add(coord);
  }

  public void removeCyclicReference(Coord coord) {
    if (this.cyclicCoords.contains(coord)) {
      this.cyclicCoords.remove(coord);
    }
  }

  /**
   * Takes in a location of a cell as a Coord and that cell as a Cell and returns a boolean value
   *      that represents whether or not that cell references itself.
   * @param     location the location of the given cell in the WorkSheet.
   * @param     cell The cell whose references are to be iterated through.
   * @return    a boolean value whether the two Cells reference one another.
   */
  private boolean cyclicReference(Coord location, Cell cell) {
    boolean cyclic = false;
    for (Coord ref : cell.references()) {
      cyclic = cyclic || referenceBetween(location, ref);
    }
    // Isn't this redundant?
    return cell.cyclicReference(location) || cyclic;
  }

  /**
   * Evaluates two Coords for whether or not the Cells at those locations reference one another.
   * @param     loc1 The coordinates of the first cell as a Coord.
   * @param     loc2 The coordinates of the second cell as a Coord.
   * @return    a boolean value whether the two Cells reference one another, either directly or
   *            indirectly.
   */
  private boolean referenceBetween(Coord loc1, Coord loc2) {
    boolean cyclic = false;
    for (Coord ref : getCellAt(loc2).references()) {
      cyclic = cyclic || referenceBetween(loc1, ref);
    }
    return cyclic;
  }

  @Override
  public int getHeight() {
    int height = 0;
    for (Map.Entry<Coord, Cell> entry : worksheet.entrySet()) {
      if (entry.getKey().row > height) {
        height = entry.getKey().row;
      }
    }

    return height;
  }

  @Override
  public int getWidth() {
    int width = 0;
    for (Map.Entry<Coord, Cell> entry : worksheet.entrySet()) {
      if (entry.getKey().col > width) {
        width = entry.getKey().col;
      }
    }

    return width;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof BeyondGoodWorksheet)) {
      return false;
    }

    BeyondGoodWorksheet that = (BeyondGoodWorksheet) obj;
    Set<Coord> worksheetKeys = worksheet.keySet();
    if (worksheetKeys.size() != that.worksheet.size()) {
      return false;
    }

    boolean same = true;
    for (Coord key : worksheetKeys) {
      same = same && getCellAt(key).evaluate(this, false)
              .equals(that.getCellAt(key).evaluate(that, false));
    }

    return same;
  }

  @Override
  public int hashCode() {
    return Objects.hash(worksheet);
  }

  @Override
  public ArrayList<Coord> referTo(Coord coord) {
    ArrayList<Coord> refs = new ArrayList<>(directlyReferTo(coord, new ArrayList<>()));
    int refsSize;
    do {
      refsSize = refs.size();
      for (Coord ref : refs) {
        refs.addAll(directlyReferTo(ref, refs));
      }
    } while (refsSize != refs.size());

    return refs;
  }

  // Gets all the cells that directly reference a given cell that aren't in existing refs already.
  private ArrayList<Coord> directlyReferTo(Coord coord, ArrayList<Coord> existingRefs) {
    ArrayList<Coord> refs = new ArrayList<>();
    for (Map.Entry<Coord, Cell> cellEntry : worksheet.entrySet()) {
      if (cellEntry.getValue().references().contains(coord)
              && !existingRefs.contains(cellEntry.getKey())) {
        refs.add(cellEntry.getKey());
      }
    }

    return refs;
  }

  @Override
  public void toggleAttribute(CellAttribute attribute, Coord coord) {
    switch (attribute) {
      case BOLD:
        if (attributes.get(coord) == null) {
          attributes.put(coord, new CellAttributes());
        }
        attributes.get(coord).toggleBold();
        break;
      case ITALIC:
        if (attributes.get(coord) == null) {
          attributes.put(coord, new CellAttributes());
        }
        attributes.get(coord).toggleItalic();
        break;
      case UNDERLINE:
        if (attributes.get(coord) == null) {
          attributes.put(coord, new CellAttributes());
        }
        attributes.get(coord).toggleUnderline();
        break;
      case LEFT:
        if (attributes.get(coord) == null) {
          attributes.put(coord, new CellAttributes());
        }
        attributes.get(coord).setAlignment(CellAttributes.LEFT);
        break;
      case CENTER:
        if (attributes.get(coord) == null) {
          attributes.put(coord, new CellAttributes());
        }
        attributes.get(coord).setAlignment(CellAttributes.CENTER);
        break;
      case RIGHT:
        if (attributes.get(coord) == null) {
          attributes.put(coord, new CellAttributes());
        }
        attributes.get(coord).setAlignment(CellAttributes.RIGHT);
        break;
    }
  }

  @Override
  public void setColor(CellAttribute attribute, Color color, Coord coord) {
    if (attribute == CellAttribute.TEXT_COLOR) {
      attributes.get(coord).setTextColor(color);
    }
  }

  @Override
  public CellAttributes getAttributeSet(Coord coord) {
    return attributes.get(coord);
  }

 @Override
  public HashMap<Coord, CellAttributes> getAttributes() {
    return attributes;
  }

  @Override
  public void setAttributes(Coord coord, CellAttributes attributeSet) {
    attributes.put(coord, attributeSet);
  }
}
