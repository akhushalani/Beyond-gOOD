package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public interface Function<T> extends Formula {
  T evaluateFunction(ArrayList<Formula> args);
}
