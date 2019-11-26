package edu.cs3500.spreadsheets.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * An object which stores the attributes of a cell.
 * FOR ASSIGNMENT 9, NOT ASSIGNMENT 7.
 */
public final class CellAttributes {
  private boolean bold;
  private boolean italic;
  private boolean underline;
  private int alignment;
  private Color textColor;

  public static final int LEFT = 0;
  public static final int CENTER = 1;
  public static final int RIGHT = 2;

  /**
   * Public constructor for CellAttributes with default values for a new cell.
   */
  public CellAttributes() {
    bold = false;
    italic = false;
    underline = false;

    alignment = LEFT;

    textColor = Color.BLACK;
  }

  /**
   * Toggles the bold attribute of a cell.
   */
  public void toggleBold() {
    bold = !bold;
  }

  /**
   * Toggles the italic attribute of a cell.
   */
  public void toggleItalic() {
    italic = !italic;
  }

  /**
   * Toggles the underline attribute of a cell.
   */
  public void toggleUnderline() {
    underline = !underline;
  }

  /**
   * Sets the text alignment of the cell.
   * @param alignment the alignment value to be applied
   */
  public void setAlignment(int alignment) {
    if (alignment == LEFT || alignment == CENTER || alignment == RIGHT) {
      this.alignment = alignment;
    }
  }

  /**
   * Sets the color of the text in the cell.
   * @param color the color to make the text
   */
  public void setTextColor(Color color) {
    textColor = color;
  }

  /**
   * Gets the color of the text in the cell.
   * @return the text color
   */
  public Color getTextColor() {
    return textColor;
  }

  /**
   * Applies the cell attributes to a component.
   * @param component the component to apply the attributes to
   * @return the component with the attributes applied
   */
  public JComponent apply(JComponent component) {
    if (bold) {
      component.setFont(component.getFont().deriveFont(component.getFont().getStyle() + Font.BOLD));
    }

    if (italic) {
      component.setFont(component.getFont()
              .deriveFont(component.getFont().getStyle() + Font.ITALIC));
    }

    if (underline) {
      Map fontAttributes = component.getFont().getAttributes();
      fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
      component.setFont(component.getFont().deriveFont(fontAttributes));
    }

    if (alignment == LEFT) {
      ((JLabel) component).setHorizontalAlignment(SwingConstants.LEFT);
    } else if (alignment == CENTER) {
      ((JLabel) component).setHorizontalAlignment(SwingConstants.CENTER);
    } else {
      ((JLabel) component).setHorizontalAlignment(SwingConstants.RIGHT);
    }

    component.setForeground(textColor);

    return component;
  }

  /**
   * Applies a given attribute from another attribute set.
   * @param attributeSet the attributes from which to get the value
   * @param attribute the attribute to apply
   */
  public void useAttributes(CellAttributes attributeSet, CellAttribute attribute) {
    switch (attribute) {
      case BOLD:
        bold = attributeSet.bold;
        break;
      case ITALIC:
        italic = attributeSet.italic;
        break;
      case UNDERLINE:
        underline = attributeSet.underline;
        break;
      case LEFT:
      case CENTER:
      case RIGHT:
        alignment = attributeSet.alignment;
        break;
      default:
        // do nothing here
    }
  }
}
