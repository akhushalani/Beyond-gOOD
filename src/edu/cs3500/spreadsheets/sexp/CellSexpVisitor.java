package edu.cs3500.spreadsheets.sexp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import edu.cs3500.spreadsheets.model.And;
import edu.cs3500.spreadsheets.model.BooleanValue;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Concatenate;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Divide;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.FormulaCell;
import edu.cs3500.spreadsheets.model.GreaterThan;
import edu.cs3500.spreadsheets.model.LessThan;
import edu.cs3500.spreadsheets.model.Not;
import edu.cs3500.spreadsheets.model.Or;
import edu.cs3500.spreadsheets.model.Product;
import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.StringValue;
import edu.cs3500.spreadsheets.model.Subtract;
import edu.cs3500.spreadsheets.model.Sum;

public class CellSexpVisitor implements SexpVisitor<Cell> {
  private Coord location;

  public CellSexpVisitor(Coord location) {
    this.location = location;
  }

  @Override
  public Cell visitBoolean(boolean b) {
    return new FormulaCell(location, new BooleanValue(b));
  }

  @Override
  public Cell visitNumber(double d) {
    return new FormulaCell(location, new DoubleValue(d));
  }

  @Override
  public Cell visitSList(List<Sexp> l) {
    FunctionCheckSexpVisitor functionChecker = new FunctionCheckSexpVisitor();
    if (l.get(0).accept(functionChecker)) {
      ArrayList<Formula> functionArgs = new ArrayList<>();
      FunctionArgsSexpVisitor argsVisitor = new FunctionArgsSexpVisitor();
      for (int i = 0; i < l.size(); i++) {
        if (i != l.size() - 1) {
          l.get(i).accept(argsVisitor);
        } else {
          functionArgs = l.get(i).accept(argsVisitor);
        }
      }

      FunctionNameVisitor nameVisitor = new FunctionNameVisitor();
      switch (l.get(0).accept(nameVisitor)) {
        case "AND":
          return new FormulaCell(location, new And(functionArgs));
        case "CONCAT":
          return new FormulaCell(location, new Concatenate(functionArgs));
        case "DIV":
          return new FormulaCell(location, new Divide(functionArgs));
        case ">":
          return new FormulaCell(location, new GreaterThan(functionArgs));
        case "<":
          return new FormulaCell(location, new LessThan(functionArgs));
        case "NOT":
          return new FormulaCell(location, new Not(functionArgs));
        case "OR":
          return new FormulaCell(location, new Or(functionArgs));
        case "PRODUCT":
          return new FormulaCell(location, new Product(functionArgs));
        case "SUB":
          return new FormulaCell(location, new Subtract(functionArgs));
        case "SUM":
          return new FormulaCell(location, new Sum(functionArgs));
        default:
          // will never run
          throw new IllegalArgumentException("Should not run.");
      }
    } else {
      throw new IllegalArgumentException("Too many arguments. Only enter one expression per cell.");
    }
  }

  @Override
  public Cell visitString(String s) {
    return new FormulaCell(location, new StringValue(s));
  }

  @Override
  public Cell visitSymbol(String s) {
    if (validReference(s)) {
      return new FormulaCell(location, new Reference(parseCoord(s)));
    } else {
      throw new IllegalArgumentException("Invalid input. " +
              "Input must be a boolean, number, String, or formula.");
    }
  }

  private boolean validReference(String s) {
    Pattern p = Pattern.compile("[^a-zA-Z0-9]");
    boolean validChars = !p.matcher(s).find();

    if (!validChars) {
      return false;
    }

    int firstNum = 0;
    int lastLetter = 1;

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        firstNum = i;
        break;
      }
    }

    for (int j = s.length() - 1; j > 0; j--) {
      char d = s.charAt(j);
      if (Character.isLetter(d)) {
        lastLetter = j;
        break;
      }
    }

    return firstNum > lastLetter;
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

    for (int j = s.length() - 1; j > 0; j--) {
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
