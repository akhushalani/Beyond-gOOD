package edu.cs3500.spreadsheets.sexp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import edu.cs3500.spreadsheets.model.And;
import edu.cs3500.spreadsheets.model.BooleanValue;
import edu.cs3500.spreadsheets.model.Concatenate;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Divide;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.GreaterThan;
import edu.cs3500.spreadsheets.model.LessThan;
import edu.cs3500.spreadsheets.model.Not;
import edu.cs3500.spreadsheets.model.Or;
import edu.cs3500.spreadsheets.model.Product;
import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.SquareRoot;
import edu.cs3500.spreadsheets.model.StringValue;
import edu.cs3500.spreadsheets.model.Subtract;
import edu.cs3500.spreadsheets.model.Sum;

/**
 * A visitor that represents an s-expression for an argument to a function
 * and returns an ArrayList of Formula arguments.
 */
public class FunctionArgsSexpVisitor implements SexpVisitor<ArrayList<Formula>> {
  private ArrayList<Formula> args;

  public FunctionArgsSexpVisitor() {
    this.args = new ArrayList<>();
  }

  @Override
  public ArrayList<Formula> visitBoolean(boolean b) {
    this.args.add(new BooleanValue(b));
    return this.args;
  }

  @Override
  public ArrayList<Formula> visitNumber(double d) {
    this.args.add(new DoubleValue(d));
    return this.args;
  }

  @Override
  public ArrayList<Formula> visitSList(List<Sexp> l) {
    FunctionCheckSexpVisitor functionChecker = new FunctionCheckSexpVisitor();
    if (l.get(0).accept(functionChecker) && l.size() > 1) {
      ArrayList<Formula> innerArgs = new ArrayList<>();
      FunctionArgsSexpVisitor argsVisitor = new FunctionArgsSexpVisitor();
      for (int i = 1; i < l.size(); i++) {
        if (i != l.size() - 1) {
          l.get(i).accept(argsVisitor);
        } else {
          innerArgs = l.get(i).accept(argsVisitor);
        }
      }

      FunctionNameVisitor nameVisitor = new FunctionNameVisitor();
      switch (l.get(0).accept(nameVisitor)) {
        case "AND":
          this.args.add(new And(innerArgs));
          break;
        case "CONCAT":
          this.args.add(new Concatenate(innerArgs));
          break;
        case "DIV":
          this.args.add(new Divide(innerArgs));
          break;
        case ">":
          this.args.add(new GreaterThan(innerArgs));
          break;
        case "<":
          this.args.add(new LessThan(innerArgs));
          break;
        case "NOT":
          this.args.add(new Not(innerArgs));
          break;
        case "OR":
          this.args.add(new Or(innerArgs));
          break;
        case "PRODUCT":
          this.args.add(new Product(innerArgs));
          break;
        case "SQRT":
          this.args.add(new SquareRoot(innerArgs));
          break;
        case "SUB":
          this.args.add(new Subtract(innerArgs));
          break;
        case "SUM":
          this.args.add(new Sum(innerArgs));
          break;
        default:
          // will never run
          throw new IllegalArgumentException("Should not run.");
      }
    } else if (l.size() == 1) {
      FunctionNameVisitor nameVisitor = new FunctionNameVisitor();
      String funcName = l.get(0).accept(nameVisitor);
      throw new IllegalArgumentException("Function " + funcName
              + "is being called with no arguments.");
    } else {
      throw new IllegalArgumentException("Too many arguments. Only enter one expression per cell.");
    }

    return this.args;
  }

  @Override
  public ArrayList<Formula> visitString(String s) {
    this.args.add(new StringValue(s));
    return this.args;
  }

  @Override
  public ArrayList<Formula> visitSymbol(String s) {
    if (!s.contains(":")) {
      if (validReference(s)) {
        this.args.add(new Reference(parseCoord(s)));
      } else {
        throw new IllegalArgumentException("Invalid input. " +
                "Input must be a boolean, number, String, or formula.");
      }
    } else {
      int colonIndex = s.indexOf(":");
      String firstRef = s.substring(0, colonIndex);
      String secondRef = s.substring(colonIndex + 1);
      if (validReference(firstRef) && validReference(secondRef)) {
        Coord firstCoord = parseCoord(firstRef);
        Coord secondCoord = parseCoord(secondRef);
        if (secondCoord.col >= firstCoord.col && secondCoord.row >= firstCoord.row) {
          for (int i = firstCoord.col; i <= secondCoord.col; i++) {
            for (int j = firstCoord.row; j <= secondCoord.row; j++) {
              this.args.add(new Reference(new Coord(i, j)));
            }
          }
        } else {
          throw new IllegalArgumentException("Coordinates are in the wrong order.");
        }
      }
    }

    return this.args;
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

    for (int j = s.length() - 1; j >= 0; j--) {
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

    for (int j = s.length() - 1; j >= 0; j--) {
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
