package edu.cs3500.spreadsheets.view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.util.Rotation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.Color;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.GraphType;
import edu.cs3500.spreadsheets.model.WorksheetAdapter;

public class BeyondGoodGraph {
  private WorksheetAdapter model;
  private Coord start;
  private Coord end;
  private JFreeChart graph;
  private GraphType type;
  private JFrame frame;
  private ChartPanel chartPanel;

  public BeyondGoodGraph(WorksheetAdapter model, Coord start, Coord end, GraphType type) {
    this.model = model;
    this.start = start;
    this.end = end;
    createGraph(type);
    this.type = type;
    frame = new JFrame();
    frame.setAlwaysOnTop(true);
  }

  private void createGraph(GraphType type) {
    try {
      switch (type) {
        case PIE_CHART:
          createPieChart();
          break;
        case BAR_GRAPH:
          createBarGraph();
          break;
        case LINE_GRAPH:
          createLineGraph();
          break;
        case SCATTER_PLOT:
          createScatterPlot();
          break;
        default:
          // do nothing here
      }
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(frame, ex.getMessage());
    }
  }

  private void createPieChart() {
    if (end.col - start.col != 1) {
        throw new IllegalArgumentException("Data must be in two columns.");
      }
      DefaultPieDataset dataset = new DefaultPieDataset();
      for (int i = start.row; i <= end.row; i++) {
        Coord key = new Coord(start.col, i);
        Coord value = new Coord(end.col, i);
        try {
          dataset.setValue(model.getCellAt(key).evaluate(model.getModel(), true),
                  Double.parseDouble(model.getCellAt(value).evaluate(model.getModel(), true)));
        } catch (NumberFormatException | NullPointerException ex) {
        //throw new IllegalArgumentException("Values in right column must be numerical.");
      }
    }

    graph = ChartFactory.createPieChart("", dataset, true, true, false);
    PiePlot plot = (PiePlot) graph.getPlot();
    plot.setDirection(Rotation.CLOCKWISE);
    plot.setBackgroundAlpha(0.0f);
    plot.setLabelLinksVisible(false);
    plot.setLabelBackgroundPaint(Color.WHITE);
    plot.setLabelOutlinePaint(Color.WHITE);
    plot.setLabelShadowPaint(Color.WHITE);
    plot.setOutlinePaint(Color.WHITE);
    plot.setShadowPaint(Color.WHITE);
    plot.setCircular(true);
  }

  private void createBarGraph() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    if (end.col - start.col == 1) {
      for (int i = start.row; i <= end.row; i++) {
        Coord key = new Coord(start.col, i);
        Coord value = new Coord(end.col, i);
        try {
          dataset.addValue(Double.parseDouble(model.getCellAt(value).evaluate(model.getModel(), true)),
                  model.getCellAt(key).evaluate(model.getModel(), true), "");
        } catch (NumberFormatException | NullPointerException ex) {
          //throw new IllegalArgumentException("Values in right column must be numerical.");
        }
      }
    } else if (end.col - start.col > 1) {
      ArrayList<String> series = new ArrayList<>();
      for (int i = start.col + 1; i <= end.col; i++) {
        Coord seriesCoord = new Coord(i, start.row);
        if (model.getCellAt(seriesCoord) != null) {
          series.add(model.getCellAt(seriesCoord).evaluate(model.getModel(), true));
        }
      }

      for (int i = start.row + 1; i <= end.row; i++) {
        for (int j = 0; j < series.size(); j++) {
          Coord key = new Coord(start.col, i);
          Coord value = new Coord(start.col + j + 1, i);
          try {
            dataset.addValue(Double.parseDouble(model.getCellAt(value).evaluate(model.getModel(), true)),
                    series.get(j), model.getCellAt(key).evaluate(model.getModel(), true));
          } catch (NumberFormatException | NullPointerException ex) {
            //throw new IllegalArgumentException("Values in right column must be numerical.");
          }
        }
      }
    }

    graph = ChartFactory.createBarChart("", "", "", dataset,
            PlotOrientation.VERTICAL, true, true, false);
    CategoryPlot plot = (CategoryPlot) graph.getPlot();
    plot.setBackgroundAlpha(0.0f);
    plot.setOutlinePaint(Color.WHITE);
    ((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
  }

  private void createLineGraph() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    if (end.col - start.col == 1) {
      for (int i = start.row; i <= end.row; i++) {
        Coord key = new Coord(start.col, i);
        Coord value = new Coord(end.col, i);
        try {
          dataset.addValue(Double.parseDouble(model.getCellAt(value).evaluate(model.getModel(), true)),
                  "", model.getCellAt(key).evaluate(model.getModel(), true));
        } catch (NumberFormatException | NullPointerException ex) {
          //throw new IllegalArgumentException("Values in right column must be numerical.");
        }
      }
    } else if (end.col - start.col > 1) {
      ArrayList<String> series = new ArrayList<>();
      for (int i = start.col + 1; i <= end.col; i++) {
        Coord seriesCoord = new Coord(i, start.row);
        if (model.getCellAt(seriesCoord) != null) {
          series.add(model.getCellAt(seriesCoord).evaluate(model.getModel(), true));
        }
      }

      for (int i = start.row + 1; i <= end.row; i++) {
        for (int j = 0; j < series.size(); j++) {
          Coord key = new Coord(start.col, i);
          Coord value = new Coord(start.col + j + 1, i);
          try {
            dataset.addValue(Double.parseDouble(model.getCellAt(value).evaluate(model.getModel(), true)),
                    series.get(j), model.getCellAt(key).evaluate(model.getModel(), true));
          } catch (NumberFormatException | NullPointerException ex) {
            //throw new IllegalArgumentException("Values in right column must be numerical.");
          }
        }
      }
    }

    graph = ChartFactory.createLineChart("", "", "", dataset);
    CategoryPlot plot = (CategoryPlot) graph.getPlot();
    plot.setBackgroundAlpha(0.0f);
    plot.setOutlinePaint(Color.WHITE);
    plot.setDomainCrosshairPaint(Color.GRAY);
  }

  public void createScatterPlot() {
    if (end.col - start.col < 1) {
      throw new IllegalArgumentException("Data must be in two columns.");
    }
    ArrayList<String> series = new ArrayList<>();
    for (int i = start.col + 1; i <= end.col; i++) {
      Coord seriesCoord = new Coord(i, start.row);
      if (model.getCellAt(seriesCoord) != null) {
        series.add(model.getCellAt(seriesCoord).evaluate(model.getModel(), true));
      }
    }
    XYSeriesCollection dataset = new XYSeriesCollection();
    for (int i = 0; i < series.size(); i++) {
      XYSeries xySeries = new XYSeries(series.get(i));
      for (int j = start.row + 1; j < end.row; j++) {
        try {
          Coord keyCoord = new Coord(start.col, j);
          Coord valCoord = new Coord(start.col + i + 1, j);
          double key = Double.parseDouble(model.getCellAt(keyCoord)
                  .evaluate(model.getModel(), true));
          double value = Double.parseDouble(model.getCellAt(valCoord)
                  .evaluate(model.getModel(), true));
          xySeries.add(key, value);
        } catch (NumberFormatException | NullPointerException ex) {
          throw new IllegalArgumentException("All data must be numerical.");
        }
      }
      dataset.addSeries(xySeries);
    }

    graph = ChartFactory.createScatterPlot("", "", "", dataset);
    XYPlot plot = (XYPlot) graph.getPlot();
    plot.setBackgroundAlpha(0.0f);
    plot.setOutlinePaint(Color.WHITE);
  }

  public void updateGraph() {
    createGraph(type);
    if (graph != null) {
      chartPanel.setChart(graph);
    }
  }

  public void renderGraph() {
    if (graph != null) {
      chartPanel = new ChartPanel(graph);
      chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
      frame.setContentPane(chartPanel);
      frame.pack();
      frame.setVisible(true);
    }
  }

  public ArrayList<Coord> getRange() {
    ArrayList<Coord> range = new ArrayList<>();
    range.add(start);
    range.add(end);

    return range;
  }

  public void addWindowListener(WindowListener listener) {
    frame.addWindowListener(listener);
  }

  public GraphType getType() {
    return type;
  }

  public String getKey() {
    return start.toString() + ":" + end.toString() + "_" + type.name();
  }
}
