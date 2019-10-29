package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;

public interface Function<T> extends Formula {
  T evaluateFunction(ArrayList<Formula> args, HashMap<Coord, Cell> worksheet);
}
