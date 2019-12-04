package edu.cs3500.spreadsheets.provider.model;

public class ProviderCellAdapter implements Cell {
  private edu.cs3500.spreadsheets.model.Cell delegate;
  private edu.cs3500.spreadsheets.model.Worksheet clientSheet;

  public ProviderCellAdapter(edu.cs3500.spreadsheets.model.Cell delegate,
                             edu.cs3500.spreadsheets.model.Worksheet clientSheet) {
    this.delegate = delegate;
    this.clientSheet = clientSheet;
  }


  @Override
  public Content getContents() {
    return new ProviderContentAdapter(delegate.getFormula(), delegate.getRawContents(),
            clientSheet);
  }
}
