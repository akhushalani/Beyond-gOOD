package edu.cs3500.spreadsheets.provider.model;

/**
 * A class that adapts the providers Cell to our Cell implementation through a delegate.
 */
public class ProviderCellAdapter implements Cell {
  private edu.cs3500.spreadsheets.model.Cell delegate;
  private edu.cs3500.spreadsheets.model.Worksheet clientSheet;

  /**
   * A public constructor for a ProviderCellAdapter that takes in a delegate and a clientSheet.
   * @param delegate an instance of our Cell implementation
   * @param clientSheet an instance of our implementation of a worksheet.
   */
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
