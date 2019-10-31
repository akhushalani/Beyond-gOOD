package edu.cs3500.spreadsheets;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import edu.cs3500.spreadsheets.model.BeyondGoodWorksheet;
import edu.cs3500.spreadsheets.model.BeyondGoodWorksheetBuilder;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.sexp.CellSexpVisitor;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * The main class for our program.
 */
public class BeyondGood {

  /**
   * The main entry point.
   *
   * @param args any command-line arguments.
   */
  public static void main(String[] args) {
    StringBuilder outputString = new StringBuilder();
    /*
      TODO: For now, look in the args array to obtain a filename and a cell name,
      - read the file and build a model from it, 
      - evaluate all the cells, and
      - report any errors, or print the evaluated value of the requested cell.
    */
    BeyondGoodWorksheet ws = null;
    if (args.length > 0 && args[0].equals("-in")) {
      if (args.length > 1) {
        try {
          // When doing this how do we get individual cell formation errors?
          ws = WorksheetReader.read(new BeyondGoodWorksheetBuilder(), new FileReader(args[1]));
        } catch (FileNotFoundException e) {
          outputString.append("Insufficient arguments, file specified does not " +
                  "exist.");
        }
      }
      else {
        outputString.append("Insufficient arguments, no file name specified.");
      }
      if (args.length > 2 && args[2].equals("-eval")) {
        if (args.length > 3) {
          Coord cellCoord = null;
          String cellToEval = args[4];
          if (validReference(cellToEval)) {
            cellCoord = parseCoord(cellToEval);
          }
          else {
            outputString.append("Error in cell " + cellToEval + ": Cell is not a valid cell " +
                    "reference.");
          }
          outputString.append(ws.getCellAt(cellCoord).evaluate(ws.getWorksheet()));
        }
      }
      else {
        outputString.append("Insufficient arguments, no cell name specified.");
      }
      if (args.length > 4) {
        outputString.append("Too many arguments were specified.");
      }
    }
  }

  // Takes a Cell Reference as a String and returns its Coord equivalent.
  private static Coord parseCoord(String s) {
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

    // Returns a boolean value that represents whether a given String contains valid Cell
    // Reference(s).
    private static boolean validReference(String s){
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
  }
