package edu.cs3500.spreadsheets.provider.model;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.ValueType;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;

public class ProviderContentAdapter implements Content {
  private Formula delegate;
  private String rawContents;
  private edu.cs3500.spreadsheets.model.Worksheet clientSheet;

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
