Model Structure:
- Interface Worksheet represents a worksheet like those that appear in Excel.
- Class BeyondGoodWorksheet represents a BeyondGood implementation of the Worksheet
        interface that will hold our data. Possesses a field worksheet that is a
        hashmap of Coords (a class that holds two int values that are x and y coordinates
        of a cell within the sheet) to Cells.
        DESIGN CHOICE: We chose a hashmap for representing our spreadsheet because
                       we felt that it was the best way to be able to access our cells
                       by their coordinates in the sheet without having to store
                       blank values that would waste storage space.
- Interface Cell represents a Cell within our worksheet that contains some kind
        of contents.
- Class FormulaCell represents an implementation of the Cell interface with some kind
        of evaluable formula in it. A FormulaCell has four fields:
            1. directRefs, which is an ArrayList<Coord> that represents the cells
                that reference the FormulaCell in question
            2. location, which is a Coord representing the location of the FormulaCell
                within the hashmap.
            3. formula, which is a Formula representing the contents of the FormulaCell
            4. rawContents which is a String representing the textual raw contents
                of the FormulaCell.
- Interface Formula represents the contents of a Cell in the worksheet.
- AbstractClass AbstractValue represents a Formula that is just a value, such as a Double,
        Boolean, or String. It has the field value, which is a generic type value
        that is the value contents of the abstract value.
- Classes StringValue, BooleanValue, and DoubleValue are all classes that extend
        AbstractValue and represent String values, boolean values, and double values as
        they are represented independently in the spreadsheet, respectively.
- Class Reference implements the Formula interface and represents a cell contents
        that is a reference to another cell in the spreadsheet. It has field
        refLocation that is a Coord representing the cell in the spreadsheet
        that this Reference is a reference to.
- Interface Function extends the Formula Interface and represents a Cell contents
        that is a Formula, such as 7 - 3, this AND that, or 10 * 2.
- AbstractClass AbstractFunction extends the Function interface and represents
        a function that has a list of arguments for the function
        that are themselves Formulas. Possesses a field args that is an
        ArrayList<Formula> of said arguments.
- Classes Subtract, SquareRoot, Or, And, Concatenate, GreaterThan, LessThan,
        Not, Product, and Sum all extend the AbstractFunction class and represent
        the corresponding functions for which they are named.
- Class CellSexpVisitor implements the SexpVisitor interface. It takes a Formula that is
        being parsed and creates a new FormulaCell at the required location. It has
        three fields:
            1. location, which is a Coord representing where the new FormulaCell is
                meant to go in the spreadsheet.
            2. rawContents, which is a String representing the raw textual contents
                of the cell.
            3. worksheet, which is a hashmap of Coords to Cells which represents the
                worksheet within which the new FormulaCell is to be placed.
- Class FunctionNameVisitor implements the SexpVisitor interface and serves to determine
        whether an Sexp that is the first in a list of Sexps is a String, and if
        so returns the String format of it.
- Class FunctionCheckVisitor implements the SexpVisitor interface and checks whether a
        Symbol Sexp is a valid function. Contains a public static final field
        that is a List of Strings that represent the possible valid functions.
- Class FunctionArgsSexpVisitor implements the SexpVisitor interface and serves to
        create an ArrayList<Formula> that represents the arguments to a given function.
- Class ReferenceSexpVisitor implements SexpVisitor interface and handles cell references
            when represented either as symbols (single references or cell block references)
            or as Lists of SExpressions (a function with arguments that can be any kind of
            SExp themselves) and appends them to an ArrayList of references.
- Class BeyondGoodWorkSheetBuilder is a factory class for BeyondGoodWorksheets.
- Interface WorksheetView represents the View aspect of the Model-View-Controller
        design pattern as it pertains to our worksheet.
- Class WorksheetVisualView implements the WorksheetView interface and represents the
        GUI version of the WorksheetView. It is implemented using the JTable feature of
        the Java Swing library. It has one field, model, a Worksheet that represents
        the worksheet to be rendered in the textualView.

- VisualView Implementation Details:
        A JFrame is established, and then a new InfiniteScrollingTableModel object is
        created by passing in the given Worksheet model. This new object is a
        DefaultTableModel with the added feature that it is infinitely scrollable on both
        axes largely via the adjustToFrame method, which adjusts what is visible in the sheet,
        and the clearSelected method, which allows for what is not displayed to be dynamically
        cleared. A RowHeaderTable is then created by passing in the TableModel to constructor.
        This creates a JTable object with row headers. This object is then passed into a
        ScrollPane which makes the view scrollable. An adjustment listener has been added to
        the horizontal and vertical scrollbar that allows for infinite scrolling by firing a
        Scroll Right and Scroll Down when the maximum x or y value on the sheet has been reached
        (essentially extending the x and y values infinitely). A mousewheel listener has also
        been added to allow for infinite scrolling via the same mechanism when using the mousewheel.
        A listener has also been added that listens for the window getting resized, and if it is it
        adjusts the size of the worksheet to fit that frame. Other details, such as border size,
        contents, and color have been edited from the JTable defaults as well.

- Class WorksheetTextualView implements the WorksheetView interface and represents the
        textual view version of the WorksheetView, not unlike the the pyramidsolitaire
        textual view of assignment 3. It has two fields:
            1. model, a Worksheet that represents the worksheet to be rendered in
                the textualView
            2. ap, the Appendable to which the textual view is to be appended
                to.
        It works by iterating through the hashmap from the Worksheet field and
        appending the Coord.toString(), followed by the Cell.toString(), to the
        appendable. Errors are reported as comments next to the raw contents of the
        cell.
- Class HeaderRenderer implements the TableCellRenderer interface of JTable. Its purpose
        is to allow for the rendering of header cells in JTable. It has a private final field
        renderer that is the default TableCellRenderer for the table.
- Class InfiniteScrollingTableModel extends the DefaultTableModel of JTable but possesses the
        added feature that it is infinitely scrollable on both axes. It takes in a Worksheet
        worksheet that is to be rendered.
- Class RowHeaderTable extends JTable and represents a JTable with Row Headers. It has the boolean
        field headerTable that represents whether the Table should render Row Headers.
- Class RowHeaderTableCellRenderer implements TableCellRenderer and represents a class for
        producing cells in a JTable with RowHeader cells. It has a private final field renderer
        that is the default TableCellRenderer.
- Class RowHeaderTableModel extends DefaultTableModel and represents a JTable Model with Row
        Headers. It has a field dataModel that is the original TableModel.
- Class ScrollableRowHeaderTable implements ChangeListener and PropertyChangeListener, and
        represents a Table that is scrollable and has Row Headers. Its constructor takes in a
        JScrollPane scrollpane that contains the table.

Assignment 7:
Changes to model:
- Added a method to the Worksheet interface that returns all the references to a cell at a given
        Coord.
Changes to view:
-


