package org.knowm.xchart.demo.charts.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.demo.charts.ExampleChart;

/**
 * Year scale
 *
 * <p>Demonstrates the following:
 *
 * <ul>
 *   <li>Rotated X-Axis labels
 *   <li>Setting a custom date formatter String
 *   <li>Smooth series
 */
public class DateChart08 implements ExampleChart<XYChart> {

  public static void main(String[] args) {

    ExampleChart<XYChart> exampleChart = new DateChart08();
    XYChart chart = exampleChart.getChart();
    new SwingWrapper<XYChart>(chart).displayChart();
  }

  @Override
  public XYChart getChart() {

    // Create Chart
    XYChart chart =
        new XYChartBuilder().width(800).height(600).title(getClass().getSimpleName()).build();

    // Customize Chart
    chart.getStyler().setLegendVisible(false);
    chart.getStyler().setXAxisLabelRotation(60);
    chart.getStyler().setDatePattern("yyyy-MM-dd");

    // Series
    List<Date> xData = new ArrayList<>();
    List<Double> yData = new ArrayList<>();

    Random random = new Random();

    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date date = null;
    for (int i = 1; i <= 14; i++) {
      try {
        date = sdf.parse("" + (2001 + i) + "-" + random.nextInt(12) + "-" + random.nextInt(28));
      } catch (ParseException e) {
        e.printStackTrace();
      }
      xData.add(date);
      yData.add(Math.random() * i);
    }

    chart.addSeries("blah", xData, yData).setSmooth(true);

    return chart;
  }

  @Override
  public String getExampleChartName() {

    return getClass().getSimpleName() + " - Rotated Tick Labels";
  }
}
