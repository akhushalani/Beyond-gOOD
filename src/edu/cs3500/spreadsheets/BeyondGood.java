package edu.cs3500.spreadsheets;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import edu.cs3500.spreadsheets.model.BeyondGoodWorksheet;
import edu.cs3500.spreadsheets.model.BeyondGoodWorksheetBuilder;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.WorksheetTextualView;
import edu.cs3500.spreadsheets.view.WorksheetVisualView;

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
    BeyondGoodWorksheet worksheet = null;
    if (args.length > 4) {
      outputString.append("Too many arguments were specified.\n");
    } else if (args.length > 0) {
      if (args.length == 1) {
        if (args[0].equals("-gui")) {
          WorksheetVisualView view = new WorksheetVisualView(new BeyondGoodWorksheet());
          view.renderView();
        } else {
          outputString.append("Invalid argument.\n");
        }
      } else if (args.length > 2 && args[0].equals("-in")) {
        try {
          // When doing this how do we get individual cell formation errors?
          worksheet = WorksheetReader.read(new BeyondGoodWorksheetBuilder(),
                  new FileReader(args[1]));
        } catch (FileNotFoundException e) {
          outputString.append("Insufficient arguments, file specified does not "
                  + "exist.\n");
        }

        if (args[2].equals("-eval")) {
          if (args.length > 3) {
            Coord cellCoord = null;
            String cellToEval = args[3];
            if (validReference(cellToEval)) {
              cellCoord = parseCoord(cellToEval);
            }
            else {
              outputString.append("Error in cell " + cellToEval + ": Cell is not a valid cell "
                      + "reference.\n");
            }
            outputString.append(worksheet.getCellAt(cellCoord).evaluate(worksheet));
          }
        } else if (args[2].equals("-save")) {
          if (args.length > 3) {
            try {
              PrintWriter pw = new PrintWriter(args[3]);
              WorksheetTextualView view = new WorksheetTextualView(worksheet, pw);
              view.renderView();
              pw = (PrintWriter) view.getAppendable();
              pw.close();
            } catch (FileNotFoundException ex) {
              // will never run, creating a new file with String name
              throw new IllegalArgumentException("Invalid file name");
            }
          } else {
            outputString.append("Insufficient arguments, no output file name specified.\n");
          }
        } else if (args[2].equals("-gui")) {
          if (args.length == 3) {
            WorksheetVisualView view = new WorksheetVisualView(worksheet);
            view.renderView();
          } else {
            outputString.append("Too many arguments were specified.\n");
          }
        }
      } else {
        outputString.append("Insufficient arguments, no file name specified.\n");
      }
    }

    System.out.print(outputString.toString());
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

  // Returns a boolean value that represents whether a given String contains valid Cell
  // Reference(s).
  private static boolean validReference(String s) {
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
}
