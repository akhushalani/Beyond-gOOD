package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;

import edu.cs3500.spreadsheets.sexp.CellSexpVisitor;
import edu.cs3500.spreadsheets.sexp.GraphSexpVisitor;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SizingSexpVisitor;
import edu.cs3500.spreadsheets.sexp.ValueCellSexpVisitor;

/**
 * Represents a factory class for BeyondGoodWorksheets.
 */
public class BeyondGoodWorksheetBuilder
        implements WorksheetReader.WorksheetBuilder<BeyondGoodWorksheet> {
  private HashMap<Coord, Cell> worksheet;
  private HashMap<Integer, Double> colSizes;
  private HashMap<Integer, Double> rowSizes;
  private HashMap<ArrayList<Coord>, ArrayList<GraphType>> graphs;

  /**
   * Represents a constructor for the BeyondGoodWorkSheetBuilder that establishes a HashMap
   *     worksheet that represents the worksheet to be produced.
   */
  public BeyondGoodWorksheetBuilder() {
    this.worksheet = new HashMap<>();
    this.colSizes = new HashMap<>();
    this.rowSizes = new HashMap<>();
    this.graphs = new HashMap<>();
  }

  /**
   * Represents a constructor for the BeyondGoodWorkSheetBuilder that takes in a worksheet as
   *      a HashMap establishes a HashMap worksheet that represents the worksheet to be produced.
   * @param worksheet the previously extant worksheet that the new BeyondGoodWorksheet is meant
   *      to me generated from.
   */
  public BeyondGoodWorksheetBuilder(HashMap<Coord, Cell> worksheet,
                                    HashMap<Integer, Double> colSizes,
                                    HashMap<Integer, Double> rowSizes,
                                    HashMap<ArrayList<Coord>, ArrayList<GraphType>> graphs) {
    this.worksheet = worksheet;
    this.colSizes = colSizes;
    this.rowSizes = rowSizes;
    this.graphs = graphs;
  }

  @Override
  public WorksheetReader.WorksheetBuilder<BeyondGoodWorksheet>
      createCell(int col, int row, String contents) {
    if (contents == null) {
      throw new IllegalArgumentException("Contents cannot be null.");
    } else if (contents.substring(0, 1).equals("=")) {
      Sexp sexpContents = Parser.parse(contents.substring(1));
      Coord coord = new Coord(col, row);
      CellSexpVisitor cellVisitor = new CellSexpVisitor(coord, contents, createWorksheet());
      this.worksheet.put(coord, sexpContents.accept(cellVisitor));
    } else {
      Sexp sexpContents = Parser.parse(contents);
      Coord coord = new Coord(col, row);
      ValueCellSexpVisitor valueCellVisitor
              = new ValueCellSexpVisitor(coord, contents, createWorksheet());
      this.worksheet.put(coord, sexpContents.accept(valueCellVisitor));
    }

    return new BeyondGoodWorksheetBuilder(this.worksheet, this.colSizes,
            this.rowSizes, this.graphs);
  }

  @Override
  public WorksheetReader.WorksheetBuilder<BeyondGoodWorksheet> addColumnSize(String contents) {
    if (contents == null) {
      throw new IllegalArgumentException("Contents cannot be null.");
    } else {
      Sexp sexpContents = Parser.parse(contents);
      SizingSexpVisitor colVisitor = new SizingSexpVisitor(this.colSizes, false);
      this.colSizes = sexpContents.accept(colVisitor);
    }

    return new BeyondGoodWorksheetBuilder(this.worksheet, this.colSizes,
            this.rowSizes, this.graphs);
  }

  @Override
  public WorksheetReader.WorksheetBuilder<BeyondGoodWorksheet> addRowSize(String contents) {
    if (contents == null) {
      throw new IllegalArgumentException("Contents cannot be null.");
    } else {
      Sexp sexpContents = Parser.parse(contents);
      SizingSexpVisitor rowVisitor = new SizingSexpVisitor(this.rowSizes, true);
      this.rowSizes = sexpContents.accept(rowVisitor);
    }

    return new BeyondGoodWorksheetBuilder(this.worksheet, this.colSizes,
            this.rowSizes, this.graphs);
  }

  @Override
  public WorksheetReader.WorksheetBuilder<BeyondGoodWorksheet> addGraph(String contents) {
    if (contents == null) {
      throw new IllegalArgumentException("Contents cannot be null.");
    } else {
      Sexp sexpContents = Parser.parse(contents);
      GraphSexpVisitor graphVisitor = new GraphSexpVisitor(this.graphs);
      this.graphs = sexpContents.accept(graphVisitor);
    }

    return new BeyondGoodWorksheetBuilder(this.worksheet, this.colSizes,
            this.rowSizes, this.graphs);
  }

  @Override
  public BeyondGoodWorksheet createWorksheet() {
    return new BeyondGoodWorksheet(worksheet, colSizes, rowSizes, graphs);
  }
}
