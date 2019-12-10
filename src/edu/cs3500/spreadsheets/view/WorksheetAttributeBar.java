package edu.cs3500.spreadsheets.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A Toolbar for editing the attributes of a cell.
 * FOR ASSIGNMENT 9, NOT ASSIGNMENT 7.
 */
public class WorksheetAttributeBar extends JToolBar {
  private JButton boldButton;
  private JButton italicButton;
  private JButton underlineButton;
  private JButton leftButton;
  private JButton centerButton;
  private JButton rightButton;
  private JButton textColorButton;
  private JButton pieChartButton;
  private JButton barGraphButton;
  private JButton lineGraphButton;
  private JButton scatterPlotButton;

  /**
   * Public constructor for the WorksheetAttributeBar class.
   * @param bg the background color of the toolbar
   */
  public WorksheetAttributeBar(Color bg) {
    super();
    setBackground(bg);
    setFloatable(false);

    Insets margins = new Insets(0, 1, 0, 1);

    boldButton = new JButton("B");
    boldButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD,
            (int) Math.floor(boldButton.getFont().getSize() * 1.5)));
    boldButton.setBackground(bg);
    boldButton.setForeground(Color.WHITE);
    boldButton.setOpaque(true);
    boldButton.setBorder(BorderFactory.createEmptyBorder(2, 7, 2, 7));
    boldButton.setAlignmentY(JLabel.CENTER_ALIGNMENT);
    boldButton.setRolloverEnabled(true);
    boldButton.getModel().addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        if (boldButton.getModel().isRollover()) {
          boldButton.setBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true),
                  BorderFactory.createEmptyBorder(1, 6, 1, 6)));
          boldButton.setBackground(Color.DARK_GRAY);
        } else {
          boldButton.setBorder(BorderFactory.createEmptyBorder(2, 7, 2, 7));
          boldButton.setBackground(bg);
        }
      }
    });
    boldButton.setMargin(margins);
    add(boldButton);

    italicButton = new JButton("I");
    italicButton.setFont(new Font(Font.SERIF, Font.ITALIC,
            (int) Math.floor(italicButton.getFont().getSize() * 1.60)));
    italicButton.setBackground(bg);
    italicButton.setForeground(Color.WHITE);
    italicButton.setOpaque(true);
    italicButton.setBorder(BorderFactory.createEmptyBorder(6, 10, 1, 10));
    italicButton.setAlignmentY(JLabel.CENTER_ALIGNMENT);
    italicButton.setRolloverEnabled(true);
    italicButton.getModel().addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        if (italicButton.getModel().isRollover()) {
          italicButton.setBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true),
                  BorderFactory.createEmptyBorder(5, 9, 0, 9)));
          italicButton.setBackground(Color.DARK_GRAY);
        } else {
          italicButton.setBorder(BorderFactory.createEmptyBorder(6, 10, 1, 10));
          italicButton.setBackground(bg);
        }
      }
    });
    italicButton.setMargin(margins);
    add(italicButton);

    underlineButton = new JButton("U");
    underlineButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,
            (int) Math.floor(underlineButton.getFont().getSize() * 1.4)));
    Map attributes = underlineButton.getFont().getAttributes();
    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
    underlineButton.setFont(underlineButton.getFont().deriveFont(attributes));
    underlineButton.setBackground(bg);
    underlineButton.setForeground(Color.WHITE);
    underlineButton.setOpaque(true);
    underlineButton.setBorder(BorderFactory.createEmptyBorder(2, 7, 2, 7));
    underlineButton.setAlignmentY(JLabel.CENTER_ALIGNMENT);
    underlineButton.setRolloverEnabled(true);
    underlineButton.getModel().addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        if (underlineButton.getModel().isRollover()) {
          underlineButton.setBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true),
                  BorderFactory.createEmptyBorder(1, 6, 1, 6)));
          underlineButton.setBackground(Color.DARK_GRAY);
        } else {
          underlineButton.setBorder(BorderFactory.createEmptyBorder(2, 7, 2, 7));
          underlineButton.setBackground(bg);
        }
      }
    });
    underlineButton.setMargin(margins);
    add(underlineButton);

    addSeparator();

    leftButton = new JButton();
    leftButton.setIcon(new ImageIcon("resources/icons/left_align.png"));
    leftButton.setBackground(bg);
    leftButton.setOpaque(true);
    leftButton.setBorder(BorderFactory.createEmptyBorder(4, 7, 3, 7));
    leftButton.setAlignmentY(JLabel.CENTER_ALIGNMENT);
    leftButton.setRolloverEnabled(true);
    leftButton.getModel().addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        if (leftButton.getModel().isRollover()) {
          leftButton.setBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true),
                  BorderFactory.createEmptyBorder(3, 6, 2, 6)));
          leftButton.setBackground(Color.DARK_GRAY);
        } else {
          leftButton.setBorder(BorderFactory.createEmptyBorder(4, 7, 3, 7));
          leftButton.setBackground(bg);
        }
      }
    });
    leftButton.setMargin(margins);
    add(leftButton);

    centerButton = new JButton();
    centerButton.setIcon(new ImageIcon("resources/icons/center_align.png"));
    centerButton.setBackground(bg);
    centerButton.setOpaque(true);
    centerButton.setBorder(BorderFactory.createEmptyBorder(4, 7, 3, 7));
    centerButton.setAlignmentY(JLabel.CENTER_ALIGNMENT);
    centerButton.setRolloverEnabled(true);
    centerButton.getModel().addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        if (centerButton.getModel().isRollover()) {
          centerButton.setBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true),
                  BorderFactory.createEmptyBorder(3, 6, 2, 6)));
          centerButton.setBackground(Color.DARK_GRAY);
        } else {
          centerButton.setBorder(BorderFactory.createEmptyBorder(4, 7, 3, 7));
          centerButton.setBackground(bg);
        }
      }
    });
    centerButton.setMargin(margins);
    add(centerButton);

    rightButton = new JButton();
    rightButton.setIcon(new ImageIcon("resources/icons/right_align.png"));
    rightButton.setBackground(bg);
    rightButton.setOpaque(true);
    rightButton.setBorder(BorderFactory.createEmptyBorder(4, 7, 3, 7));
    rightButton.setAlignmentY(JLabel.CENTER_ALIGNMENT);
    rightButton.setRolloverEnabled(true);
    rightButton.getModel().addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        if (rightButton.getModel().isRollover()) {
          rightButton.setBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true),
                  BorderFactory.createEmptyBorder(3, 6, 2, 6)));
          rightButton.setBackground(Color.DARK_GRAY);
        } else {
          rightButton.setBorder(BorderFactory.createEmptyBorder(4, 7, 3, 7));
          rightButton.setBackground(bg);
        }
      }
    });
    rightButton.setMargin(margins);
    add(rightButton);

    addSeparator();

    textColorButton = new JButton("T");
    textColorButton.setFont(new Font(Font.SERIF, Font.PLAIN,
            (int) Math.floor(textColorButton.getFont().getSize() * 1.65)));
    textColorButton.setBackground(bg);
    textColorButton.setForeground(Color.WHITE);
    textColorButton.setOpaque(true);
    textColorButton.setBorder(BorderFactory.createEmptyBorder(6, 10, 1, 10));
    textColorButton.setAlignmentY(JLabel.CENTER_ALIGNMENT);
    textColorButton.setRolloverEnabled(true);
    textColorButton.getModel().addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        if (textColorButton.getModel().isRollover()) {
          textColorButton.setBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true),
                  BorderFactory.createEmptyBorder(5, 9, 0, 9)));
          textColorButton.setBackground(Color.DARK_GRAY);
        } else {
          textColorButton.setBorder(BorderFactory.createEmptyBorder(6, 10, 1, 10));
          textColorButton.setBackground(bg);
        }
      }
    });
    textColorButton.setMargin(margins);
    add(textColorButton);

    addSeparator();

    pieChartButton = new JButton();
    pieChartButton.setIcon(new ImageIcon("resources/icons/pie_chart.png"));
    pieChartButton.setBackground(bg);
    pieChartButton.setOpaque(true);
    pieChartButton.setBorder(BorderFactory.createEmptyBorder(4, 7, 3, 7));
    pieChartButton.setAlignmentY(JLabel.CENTER_ALIGNMENT);
    pieChartButton.setRolloverEnabled(true);
    pieChartButton.getModel().addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        if (pieChartButton.getModel().isRollover()) {
          pieChartButton.setBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true),
                  BorderFactory.createEmptyBorder(3, 6, 2, 6)));
          pieChartButton.setBackground(Color.DARK_GRAY);
        } else {
          pieChartButton.setBorder(BorderFactory.createEmptyBorder(4, 7, 3, 7));
          pieChartButton.setBackground(bg);
        }
      }
    });
    pieChartButton.setMargin(margins);
    pieChartButton.setActionCommand("Pie Chart");
    add(pieChartButton);

    barGraphButton = new JButton();
    barGraphButton.setIcon(new ImageIcon("resources/icons/bar_graph.png"));
    barGraphButton.setBackground(bg);
    barGraphButton.setOpaque(true);
    barGraphButton.setBorder(BorderFactory.createEmptyBorder(4, 7, 3, 7));
    barGraphButton.setAlignmentY(JLabel.CENTER_ALIGNMENT);
    barGraphButton.setRolloverEnabled(true);
    barGraphButton.getModel().addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        if (barGraphButton.getModel().isRollover()) {
          barGraphButton.setBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true),
                  BorderFactory.createEmptyBorder(3, 6, 2, 6)));
          barGraphButton.setBackground(Color.DARK_GRAY);
        } else {
          barGraphButton.setBorder(BorderFactory.createEmptyBorder(4, 7, 3, 7));
          barGraphButton.setBackground(bg);
        }
      }
    });
    barGraphButton.setMargin(margins);
    barGraphButton.setActionCommand("Bar Graph");
    add(barGraphButton);

    lineGraphButton = new JButton();
    lineGraphButton.setIcon(new ImageIcon("resources/icons/line_graph.png"));
    lineGraphButton.setBackground(bg);
    lineGraphButton.setOpaque(true);
    lineGraphButton.setBorder(BorderFactory.createEmptyBorder(4, 7, 3, 7));
    lineGraphButton.setAlignmentY(JLabel.CENTER_ALIGNMENT);
    lineGraphButton.setRolloverEnabled(true);
    lineGraphButton.getModel().addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        if (lineGraphButton.getModel().isRollover()) {
          lineGraphButton.setBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true),
                  BorderFactory.createEmptyBorder(3, 6, 2, 6)));
          lineGraphButton.setBackground(Color.DARK_GRAY);
        } else {
          lineGraphButton.setBorder(BorderFactory.createEmptyBorder(4, 7, 3, 7));
          lineGraphButton.setBackground(bg);
        }
      }
    });
    lineGraphButton.setMargin(margins);
    lineGraphButton.setActionCommand("Line Graph");
    add(lineGraphButton);

    scatterPlotButton = new JButton();
    scatterPlotButton.setIcon(new ImageIcon("resources/icons/scatter_plot.png"));
    scatterPlotButton.setBackground(bg);
    scatterPlotButton.setOpaque(true);
    scatterPlotButton.setBorder(BorderFactory.createEmptyBorder(4, 7, 3, 7));
    scatterPlotButton.setAlignmentY(JLabel.CENTER_ALIGNMENT);
    scatterPlotButton.setRolloverEnabled(true);
    scatterPlotButton.getModel().addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        if (scatterPlotButton.getModel().isRollover()) {
          scatterPlotButton.setBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true),
                  BorderFactory.createEmptyBorder(3, 6, 2, 6)));
          scatterPlotButton.setBackground(Color.DARK_GRAY);
        } else {
          scatterPlotButton.setBorder(BorderFactory.createEmptyBorder(4, 7, 3, 7));
          scatterPlotButton.setBackground(bg);
        }
      }
    });
    scatterPlotButton.setMargin(margins);
    scatterPlotButton.setActionCommand("Scatter Plot");
    add(scatterPlotButton);
  }

  /**
   * Gets the bold button in the toolbar.
   * @return the bold button
   */
  public JButton getBoldButton() {
    return boldButton;
  }

  /**
   * Gets the italic button in the toolbar.
   * @return the italic button
   */
  public JButton getItalicButton() {
    return italicButton;
  }

  /**
   * Gets the underline button in the toolbar.
   * @return the underline button
   */
  public JButton getUnderlineButton() {
    return underlineButton;
  }

  /**
   * Gets the left align button in the toolbar.
   * @return the left align button
   */
  public JButton getLeftButton() {
    return leftButton;
  }

  /**
   * Gets the center align button in the toolbar.
   * @return the center align button
   */
  public JButton getCenterButton() {
    return centerButton;
  }

  /**
   * Gets the right align button in the toolbar.
   * @return the right align button
   */
  public JButton getRightButton() {
    return rightButton;
  }

  /**
   * Gets the text color button in the toolbar.
   * @return the text color button
   */
  public JButton getTextColorButton() {
    return textColorButton;
  }

  /**
   * Gets the pie chart button in the toolbar.
   * @return the pie chart button
   */
  public JButton getPieChartButton() {
    return pieChartButton;
  }

  /**
   * Gets the bar graph button in the toolbar.
   * @return the bar graph button
   */
  public JButton getBarGraphButton() {
    return barGraphButton;
  }

  /**
   * Gets the line graph button in the toolbar.
   * @return the line graph button
   */
  public JButton getLineGraphButton() {
    return lineGraphButton;
  }

  /**
   * Gets the scatter plot button in the toolbar.
   * @return the scatter plot button
   */
  public JButton getScatterPlotButton() {
    return scatterPlotButton;
  }
}
