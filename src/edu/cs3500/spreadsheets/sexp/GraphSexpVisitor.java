package edu.cs3500.spreadsheets.sexp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.GraphType;

/**
 * A visitor that accumulates a map of graphs for the model.
 */
public class GraphSexpVisitor
        implements SexpVisitor<HashMap<ArrayList<Coord>, ArrayList<GraphType>>> {
  private HashMap<ArrayList<Coord>, ArrayList<GraphType>> graphs;

  /**
   * Public constructor for the GraphSexpVisitor class.
   * @param graphs the accumulated map of graphs to add to
   */
  public GraphSexpVisitor(HashMap<ArrayList<Coord>, ArrayList<GraphType>> graphs) {
    this.graphs = graphs;
  }

  @Override
  public HashMap<ArrayList<Coord>, ArrayList<GraphType>> visitBoolean(boolean b) {
    return graphs;
  }

  @Override
  public HashMap<ArrayList<Coord>, ArrayList<GraphType>> visitNumber(double d) {
    return graphs;
  }

  @Override
  public HashMap<ArrayList<Coord>, ArrayList<GraphType>> visitSList(List<Sexp> l) {
    CoordSexpVisitor coordVisitor = new CoordSexpVisitor();
    GraphTypeSexpVisitor typeVisitor = new GraphTypeSexpVisitor();

    Coord start = l.get(0).accept(coordVisitor);
    Coord end = l.get(1).accept(coordVisitor);
    GraphType type = l.get(2).accept(typeVisitor);

    ArrayList range = new ArrayList();
    range.add(start);
    range.add(end);

    if (graphs.containsKey(range) && !graphs.get(range).contains(type)) {
      graphs.get(range).add(type);
    } else {
      ArrayList<GraphType> types = new ArrayList<>();
      types.add(type);
      graphs.put(range, types);
    }

    return graphs;
  }

  @Override
  public HashMap<ArrayList<Coord>, ArrayList<GraphType>> visitSymbol(String s) {
    return graphs;
  }

  @Override
  public HashMap<ArrayList<Coord>, ArrayList<GraphType>> visitString(String s) {
    return graphs;
  }
}
