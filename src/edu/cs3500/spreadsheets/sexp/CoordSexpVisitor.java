package edu.cs3500.spreadsheets.sexp;

import java.util.List;

import edu.cs3500.spreadsheets.BeyondGood;
import edu.cs3500.spreadsheets.model.Coord;

/**
 * A visitor for getting a Coord from an SSymbol.
 */
public class CoordSexpVisitor implements SexpVisitor<Coord> {
  @Override
  public Coord visitBoolean(boolean b) {
    // shouldn't happen
    throw new IllegalArgumentException("Invalid type");
  }

  @Override
  public Coord visitNumber(double d) {
    // shouldn't happen
    throw new IllegalArgumentException("Invalid type");
  }

  @Override
  public Coord visitSList(List<Sexp> l) {
    // shouldn't happen
    throw new IllegalArgumentException("Invalid type");
  }

  @Override
  public Coord visitSymbol(String s) {
    return BeyondGood.parseCoord(s);
  }

  @Override
  public Coord visitString(String s) {
    // shouldn't happen
    throw new IllegalArgumentException("Invalid type");
  }
}
