import org.junit.Test;

import edu.cs3500.spreadsheets.model.BeyondGoodWorksheetBuilder;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;

import static org.junit.Assert.assertEquals;

public class FunctionTest {
  private Worksheet worksheet;

  public void initWorksheet() {
    worksheet = new BeyondGoodWorksheetBuilder()
            .createCell(1, 1, "true")
            .createCell(1, 2, "false")
            .createCell(1, 3, "-2")
            .createCell(1, 4, "0")
            .createCell(1, 5, "1")
            .createCell(1, 6, "\"Hello, \"")
            .createCell(1, 7, "\"World. \"")
            // AND tests
            .createCell(2, 1, "=(AND true false)")
            .createCell(2, 2, "=(AND true true)")
            .createCell(2, 3, "=(AND false false)")
            .createCell(2, 4, "=(AND A1 A2)")
            .createCell(2, 5, "=(AND true A1)")
            .createCell(2, 6, "=(AND false -2)")
            // CONCAT tests
            .createCell(3, 1, "=(CONCAT \"Hello, \" \"World. \")")
            .createCell(3, 2, "=(CONCAT A6 A7)")
            .createCell(3, 3, "=(CONCAT \"Hello, \" \"World. \" A6 A7)")
            .createCell(3, 4, "=(CONCAT \"Hello, \" 7)")
            // > tests
            .createCell(4, 1, "=(> 1 0)")
            .createCell(4, 2, "=(> 0 -2)")
            .createCell(4, 3, "=(> -2 1)")
            .createCell(4, 4, "=(> A5 A3)")
            .createCell(4, 5, "=(> A3 A5)")
            .createCell(4, 6, "=(> 1 true)")
            .createCell(4, 7, "=(> 2 0 -1)")
            // < tests
            .createCell(5, 1, "=(< 1 0)")
            .createCell(5, 2, "=(< 0 -2)")
            .createCell(5, 3, "=(< -2 1)")
            .createCell(5, 4, "=(< A5 A3)")
            .createCell(5, 5, "=(< A3 A5)")
            .createCell(5, 6, "=(< 1 true)")
            .createCell(5, 7, "=(< 2 0 -1)")
            // NOT tests
            .createCell(6, 1, "=(NOT true)")
            .createCell(6, 2, "=(NOT false)")
            .createCell(6, 3, "=(NOT A1)")
            .createCell(6, 4, "=(NOT A2)")
            .createCell(6, 5, "=(NOT 3)")
            .createCell(6, 6, "=(NOT true false)")
            // OR tests
            .createCell(7, 1, "=(OR true false)")
            .createCell(7, 2, "=(OR true true)")
            .createCell(7, 3, "=(OR false false)")
            .createCell(7, 4, "=(OR A1 A2)")
            .createCell(7, 5, "=(OR true true true)")
            .createCell(7, 6, "=(OR true 7)")
            // PRODUCT tests
            .createCell(8, 1, "=(PRODUCT 1 2)")
            .createCell(8, 2, "=(PRODUCT 1 2 3)")
            .createCell(8, 3, "=(PRODUCT -1 2)")
            .createCell(8, 4, "=(PRODUCT 2 0)")
            .createCell(8, 5, "=(PRODUCT A3 A5)")
            .createCell(8, 6, "=(PRODUCT A3 A4 A5)")
            .createCell(8, 7, "=(PRODUCT A3:A5)")
            .createCell(8, 8, "=(PRODUCT A3 0 A5)")
            .createCell(8, 9, "=(PRODUCT 1 true)")
            // SQRT tests
            .createCell(9, 1, "=(SQRT 1)")
            .createCell(9, 2, "=(SQRT 0)")
            .createCell(9, 3, "=(SQRT A4)")
            .createCell(9, 4, "=(SQRT 1 0)")
            .createCell(9, 5, "=(SQRT -4)")
            .createCell(9, 6, "=(SQRT true)")
            // SUB tests
            .createCell(10, 1, "=(SUB 4 2)")
            .createCell(10, 2, "=(SUB 4 -2)")
            .createCell(10, 3, "=(SUB 4 0)")
            .createCell(10, 4, "=(SUB -2 4)")
            .createCell(10, 5, "=(SUB A5 A4)")
            .createCell(10, 6, "=(SUB 4 2 1)")
            // SUM tests
            .createCell(11, 1, "=(SUM 1)")
            .createCell(11, 2, "=(SUM 1 2 3)")
            .createCell(11, 3, "=(SUM 1 2 0)")
            .createCell(11, 4, "=(SUM A3 A4 A5)")
            .createCell(11, 5, "=(SUM 7 true)")
            .createWorksheet();
  }

  @Test
  public void testAnd() {
    initWorksheet();
    assertEquals(worksheet.getCellAt(new Coord(2, 1)).evaluate(worksheet), "false");
    assertEquals(worksheet.getCellAt(new Coord(2, 2)).evaluate(worksheet), "true");
    assertEquals(worksheet.getCellAt(new Coord(2, 3)).evaluate(worksheet), "false");
    assertEquals(worksheet.getCellAt(new Coord(2, 4)).evaluate(worksheet), "false");
    assertEquals(worksheet.getCellAt(new Coord(2, 5)).evaluate(worksheet), "true");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAndInvalidArgs() {
    initWorksheet();
    worksheet.getCellAt(new Coord(2, 6)).evaluate(worksheet);
  }

  @Test
  public void testConcat() {
    initWorksheet();
    assertEquals(worksheet.getCellAt(new Coord(3, 1)).evaluate(worksheet),
            "\"Hello, World. \"");
    assertEquals(worksheet.getCellAt(new Coord(3, 2)).evaluate(worksheet),
            "\"Hello, World. \"");
    assertEquals(worksheet.getCellAt(new Coord(3, 3)).evaluate(worksheet),
            "\"Hello, World. Hello, World. \"");
    assertEquals(worksheet.getCellAt(new Coord(3, 4)).evaluate(worksheet),
            "\"Hello, \"");
  }

  @Test
  public void testGreaterThan() {
    initWorksheet();
    assertEquals(worksheet.getCellAt(new Coord(4, 1)).evaluate(worksheet), "true");
    assertEquals(worksheet.getCellAt(new Coord(4, 2)).evaluate(worksheet), "true");
    assertEquals(worksheet.getCellAt(new Coord(4, 3)).evaluate(worksheet), "false");
    assertEquals(worksheet.getCellAt(new Coord(4, 4)).evaluate(worksheet), "true");
    assertEquals(worksheet.getCellAt(new Coord(4, 5)).evaluate(worksheet), "false");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreaterThanInvalidArgs1() {
    initWorksheet();
    worksheet.getCellAt(new Coord(4, 6)).evaluate(worksheet);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreaterThanInvalidArgs2() {
    initWorksheet();
    worksheet.getCellAt(new Coord(4, 7)).evaluate(worksheet);
  }

  @Test
  public void testLessThan() {
    initWorksheet();
    assertEquals(worksheet.getCellAt(new Coord(5, 1)).evaluate(worksheet), "false");
    assertEquals(worksheet.getCellAt(new Coord(5, 2)).evaluate(worksheet), "false");
    assertEquals(worksheet.getCellAt(new Coord(5, 3)).evaluate(worksheet), "true");
    assertEquals(worksheet.getCellAt(new Coord(5, 4)).evaluate(worksheet), "false");
    assertEquals(worksheet.getCellAt(new Coord(5, 5)).evaluate(worksheet), "true");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLessThanInvalidArgs1() {
    initWorksheet();
    worksheet.getCellAt(new Coord(5, 6)).evaluate(worksheet);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLessThanInvalidArgs2() {
    initWorksheet();
    worksheet.getCellAt(new Coord(5, 7)).evaluate(worksheet);
  }

  @Test
  public void testNot() {
    initWorksheet();
    assertEquals(worksheet.getCellAt(new Coord(6, 1)).evaluate(worksheet), "false");
    assertEquals(worksheet.getCellAt(new Coord(6, 2)).evaluate(worksheet), "true");
    assertEquals(worksheet.getCellAt(new Coord(6, 3)).evaluate(worksheet), "false");
    assertEquals(worksheet.getCellAt(new Coord(6, 4)).evaluate(worksheet), "true");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotInvalidArgs1() {
    initWorksheet();
    worksheet.getCellAt(new Coord(6, 5)).evaluate(worksheet);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotInvalidArgs2() {
    initWorksheet();
    worksheet.getCellAt(new Coord(6, 6)).evaluate(worksheet);
  }

  @Test
  public void testOr() {
    initWorksheet();
    assertEquals(worksheet.getCellAt(new Coord(7, 1)).evaluate(worksheet), "true");
    assertEquals(worksheet.getCellAt(new Coord(7, 2)).evaluate(worksheet), "true");
    assertEquals(worksheet.getCellAt(new Coord(7, 3)).evaluate(worksheet), "false");
    assertEquals(worksheet.getCellAt(new Coord(7, 4)).evaluate(worksheet), "true");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOrInvalidArgs1() {
    initWorksheet();
    worksheet.getCellAt(new Coord(7, 5)).evaluate(worksheet);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOrInvalidArgs2() {
    initWorksheet();
    worksheet.getCellAt(new Coord(7, 6)).evaluate(worksheet);
  }

  @Test
  public void testProduct() {
    initWorksheet();
    assertEquals(worksheet.getCellAt(new Coord(8, 1)).evaluate(worksheet), "2.000000");
    assertEquals(worksheet.getCellAt(new Coord(8, 2)).evaluate(worksheet), "6.000000");
    assertEquals(worksheet.getCellAt(new Coord(8, 3)).evaluate(worksheet), "-2.000000");
    assertEquals(worksheet.getCellAt(new Coord(8, 4)).evaluate(worksheet), "0.000000");
    assertEquals(worksheet.getCellAt(new Coord(8, 5)).evaluate(worksheet), "-2.000000");
    assertEquals(worksheet.getCellAt(new Coord(8, 6)).evaluate(worksheet), "0.000000");
    assertEquals(worksheet.getCellAt(new Coord(8, 7)).evaluate(worksheet), "0.000000");
    assertEquals(worksheet.getCellAt(new Coord(8, 8)).evaluate(worksheet), "0.000000");
    assertEquals(worksheet.getCellAt(new Coord(8, 9)).evaluate(worksheet), "1.000000");
  }

  @Test
  public void testSqrt() {
    initWorksheet();
    assertEquals(worksheet.getCellAt(new Coord(9, 1)).evaluate(worksheet), "1.000000");
    assertEquals(worksheet.getCellAt(new Coord(9, 2)).evaluate(worksheet), "0.000000");
    assertEquals(worksheet.getCellAt(new Coord(9, 3)).evaluate(worksheet), "0.000000");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSqrtInvalidArgs1() {
    initWorksheet();
    worksheet.getCellAt(new Coord(9, 4)).evaluate(worksheet);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSqrtInvalidArgs2() {
    initWorksheet();
    worksheet.getCellAt(new Coord(9, 5)).evaluate(worksheet);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSqrtInvalidArgs3() {
    initWorksheet();
    worksheet.getCellAt(new Coord(9, 6)).evaluate(worksheet);
  }

  @Test
  public void testSub() {
    initWorksheet();
    assertEquals(worksheet.getCellAt(new Coord(10, 1)).evaluate(worksheet), "2.000000");
    assertEquals(worksheet.getCellAt(new Coord(10, 2)).evaluate(worksheet), "6.000000");
    assertEquals(worksheet.getCellAt(new Coord(10, 3)).evaluate(worksheet), "4.000000");
    assertEquals(worksheet.getCellAt(new Coord(10, 4)).evaluate(worksheet), "-6.000000");
    assertEquals(worksheet.getCellAt(new Coord(10, 5)).evaluate(worksheet), "1.000000");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSubInvalidArgs1() {
    initWorksheet();
    worksheet.getCellAt(new Coord(10, 6)).evaluate(worksheet);
  }

  @Test
  public void testSum() {
    initWorksheet();
    assertEquals(worksheet.getCellAt(new Coord(11, 1)).evaluate(worksheet), "1.000000");
    assertEquals(worksheet.getCellAt(new Coord(11, 2)).evaluate(worksheet), "6.000000");
    assertEquals(worksheet.getCellAt(new Coord(11, 3)).evaluate(worksheet), "3.000000");
    assertEquals(worksheet.getCellAt(new Coord(11, 4)).evaluate(worksheet), "-1.000000");
    assertEquals(worksheet.getCellAt(new Coord(11, 5)).evaluate(worksheet), "7.000000");
  }
}
