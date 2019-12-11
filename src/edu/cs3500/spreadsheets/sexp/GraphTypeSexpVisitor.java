package edu.cs3500.spreadsheets.sexp;

import java.util.List;

import edu.cs3500.spreadsheets.model.GraphType;

/**
 * A visitor to return a GraphType from an SSymbol.
 */
public class GraphTypeSexpVisitor implements SexpVisitor<GraphType> {

  @Override
  public GraphType visitBoolean(boolean b) {
    // shouldn't happen
    throw new IllegalArgumentException("Invalid type");
  }

  @Override
  public GraphType visitNumber(double d) {
    // shouldn't happen
    throw new IllegalArgumentException("Invalid type");
  }

  @Override
  public GraphType visitSList(List<Sexp> l) {
    // shouldn't happen
    throw new IllegalArgumentException("Invalid type");
  }

  @Override
  public GraphType visitSymbol(String s) {
    return GraphType.valueOf(s);
  }

  @Override
  public GraphType visitString(String s) {
    // shouldn't happen
    throw new IllegalArgumentException("Invalid type");
  }
}
