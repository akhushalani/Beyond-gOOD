package edu.cs3500.spreadsheets.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public final class CellAttributes {
  private boolean bold;
  private boolean italic;
  private boolean underline;
  private int alignment;
  private Color bgColor;
  private Color textColor;

  public static final int LEFT = 0;
  public static final int CENTER = 1;
  public static final int RIGHT = 2;

  public CellAttributes() {
    bold = false;
    italic = false;
    underline = false;

    alignment = LEFT;

    bgColor = Color.WHITE;
    textColor = Color.BLACK;
  }

  public void toggleBold() {
    bold = !bold;
  }

  public void toggleItalic() {
    italic = !italic;
  }

  public void toggleUnderline() {
    underline = !underline;
  }

  public void setAlignment(int alignment) {
    if (alignment == LEFT || alignment == CENTER || alignment == RIGHT) {
      this.alignment = alignment;
    }
  }

  public void setBackgroundColor(Color color) {
    bgColor = color;
  }

  public void setTextColor(Color color) {
    textColor = color;
  }

  public Color getTextColor() {
    return textColor;
  }

  public JComponent apply(JComponent component) {
    if (bold) {
      component.setFont(component.getFont().deriveFont(component.getFont().getStyle() + Font.BOLD));
    }

    if (italic) {
      component.setFont(component.getFont().deriveFont(component.getFont().getStyle() + Font.ITALIC));
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

    //component.setBackground(bgColor);
    component.setForeground(textColor);

    return component;
  }

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
    }
  }
}
