package edu.cs3500.spreadsheets.sexp;

import java.util.HashMap;
import java.util.List;

public class SizingSexpVisitor implements SexpVisitor<HashMap<Integer, Double>> {
  private HashMap<Integer, Double> sizes;
  private boolean row;

  public SizingSexpVisitor(HashMap<Integer, Double> sizes, boolean row) {
    this.sizes = sizes;
    this.row = row;
  }

  @Override
  public HashMap<Integer, Double> visitBoolean(boolean b) {
    return sizes;
  }

  @Override
  public HashMap<Integer, Double> visitNumber(double d) {
    return sizes;
  }

  @Override
  public HashMap<Integer, Double> visitSList(List<Sexp> l) {
    if (l.size() != 2) {
      return sizes;
    }

    int index = l.get(0).accept(new IntegerSexpVisitor());
    double size = l.get(1).accept(new DoubleSexpVisitor(row));
    if (index == -1 || size == -1.0) {
      return sizes;
    } else {
      sizes.put(index, size);
      return sizes;
    }
  }

  @Override
  public HashMap<Integer, Double> visitSymbol(String s) {
    return sizes;
  }

  @Override
  public HashMap<Integer, Double> visitString(String s) {
    return sizes;
  }
}
