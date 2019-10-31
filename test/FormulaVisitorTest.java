import org.junit.Test;

import java.util.ArrayList;

import edu.cs3500.spreadsheets.model.BooleanValue;
import edu.cs3500.spreadsheets.model.BooleanValueVisitor;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.DoubleValueVisitor;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.StringValue;
import edu.cs3500.spreadsheets.model.StringValueVisitor;
import edu.cs3500.spreadsheets.model.Sum;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Class for testing all implementations of the FormulaVisitor interface.
 */
public class FormulaVisitorTest {
  private BooleanValue trueVal;
  private BooleanValue falseVal;
  private DoubleValue twoVal;
  private StringValue helloVal;
  private Reference ref;
  private Sum sum;

  private void initVars() {
    trueVal = new BooleanValue(true);
    falseVal = new BooleanValue(false);

    twoVal = new DoubleValue(2.0);

    helloVal = new StringValue("Hello");

    ref = new Reference(new Coord(1, 1));

    ArrayList<Formula> args = new ArrayList<>();
    args.add(falseVal);
    args.add(twoVal);
    sum = new Sum(args);
  }

  @Test
  public void testBooleanValueVisitor() {
    initVars();
    BooleanValueVisitor booleanVisitor = new BooleanValueVisitor();
    assertEquals(true, trueVal.accept(booleanVisitor));
    assertEquals(false, falseVal.accept(booleanVisitor));
    assertNull(twoVal.accept(booleanVisitor));
    assertNull(helloVal.accept(booleanVisitor));
    assertNull(ref.accept(booleanVisitor));
    assertNull(sum.accept(booleanVisitor));
  }

  @Test
  public void testDoubleValueVisitor() {
    initVars();
    DoubleValueVisitor doubleVisitor = new DoubleValueVisitor();
    assertTrue(0.0 == trueVal.accept(doubleVisitor));
    assertTrue(0.0 == falseVal.accept(doubleVisitor));
    assertTrue(2.0 == twoVal.accept(doubleVisitor));
    assertTrue(0.0 == helloVal.accept(doubleVisitor));
    assertTrue(0.0 == ref.accept(doubleVisitor));
    assertTrue(0.0 == sum.accept(doubleVisitor));
  }

  @Test
  public void testStringValueVisitor() {
    initVars();
    StringValueVisitor stringVisitor = new StringValueVisitor();
    assertEquals("", trueVal.accept(stringVisitor));
    assertEquals("", falseVal.accept(stringVisitor));
    assertEquals("", twoVal.accept(stringVisitor));
    assertEquals("Hello", helloVal.accept(stringVisitor));
    assertEquals("", ref.accept(stringVisitor));
    assertEquals("", sum.accept(stringVisitor));
  }
}
