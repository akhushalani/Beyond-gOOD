package edu.cs3500.spreadsheets.provider.view;

import java.io.PrintWriter;
import java.util.ArrayList;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.provider.model.Worksheet;

/**
 * Represents the textual view of the given Worksheet.
 */
public class WorksheetTextualView {

  private final Worksheet w;
  private PrintWriter pw;

  public WorksheetTextualView(Worksheet w, PrintWriter pw) {
    this.w = w;
    this.pw = pw;
  }

  /**
   * Renders the Textual view of this Worksheet.
   */
  public void render() {
    ArrayList<Coord> coords = w.nonEmptyCoords();

    for (int i = 0; i < coords.size(); i++) {
      Coord c = coords.get(i);

      // this part appends the coordinate in string form with its contents
      String s = c.toString() + " " + w.getContents(c).toString();

      try {
        pw.append(s + "\n");
      } catch (Exception e) {
        throw new IllegalStateException("Cannot append to output file.");
      }
    }

    pw.close();
  }
}