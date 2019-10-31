package edu.cs3500.spreadsheets.model;

public interface FormulaVisitor<R> {
  R visitDoubleValue(DoubleValue d);

  R visitBooleanValue(BooleanValue b);

  R visitStringValue(StringValue s);

  R visitReference(Reference r);

  R visitFunction(Function f);
}
