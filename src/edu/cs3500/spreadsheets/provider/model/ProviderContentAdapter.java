package edu.cs3500.spreadsheets.provider.model;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.ValueType;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * A class that adapts the providers Content interface to our Formula implementation
 * through a delegate.
 */
public class ProviderContentAdapter implements Content {
  private Formula delegate;
  private String rawContents;
  private edu.cs3500.spreadsheets.model.Worksheet clientSheet;

  /**
   * A public constructor for a ProviderContentAdapter that takes in a Formula that represents
   *        our implementation of a cells contents, a String that is the raw "contents" and
   *        Worksheet that is a representation of our worksheet.
   * @param delegate an instance of our Formula representation.
   * @param rawContents the raw contents that corresponds to the represented Content.
   * @param clientSheet an instance of our Worksheet.
   */
  public ProviderContentAdapter(Formula delegate, String rawContents,
                                edu.cs3500.spreadsheets.model.Worksheet clientSheet) {
    this.delegate = delegate;
    this.rawContents = rawContents;
    this.clientSheet = clientSheet;
  }


  @Override
  public AValue evaluate(Worksheet w, Coord c) {
    if (this.delegate.evaluate(clientSheet, c).getValueType() == ValueType.BOOLEAN) {
      return new BooleanValue(
              ((edu.cs3500.spreadsheets.model.BooleanValue)
                      this.delegate.evaluate(clientSheet, c)).getValue());
    } else if (this.delegate.evaluate(clientSheet, c).getValueType() == ValueType.STRING) {
      return new StringValue(
              ((edu.cs3500.spreadsheets.model.StringValue)
                      this.delegate.evaluate(clientSheet, c)).getValue());
    } else if (this.delegate.evaluate(clientSheet, c).getValueType() == ValueType.DOUBLE) {
      return new DoubleValue(
              ((edu.cs3500.spreadsheets.model.DoubleValue)
                      this.delegate.evaluate(clientSheet, c)).getValue());
    } else {
      return (new ProviderContentAdapter(this.delegate.evaluate(clientSheet, c), rawContents,
              clientSheet)).evaluate(w, c);
    }
  }

  @Override
  public Sexp getVal() {
    return Parser.parse(this.rawContents);
  }

  @Override
  public String toString() {
    return rawContents;
  }
}
