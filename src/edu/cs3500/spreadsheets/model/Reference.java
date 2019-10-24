package edu.cs3500.spreadsheets.model;

import java.util.HashMap;

public class Reference implements Formula {
  private Coord refLocation;

  @Override
  public Formula evaluate(HashMap<Coord, Cell> spreadsheet) {
    return null;
  }
}
