package org.knowm.xchart.customtests;

import org.junit.jupiter.api.Test;
import org.knowm.xchart.*;
import org.knowm.xchart.style.*;
import org.knowm.xchart.style.colors.*;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.knowm.xchart.style.theme.*;
import java.awt.*;
import java.util.List;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class ChartAPITest {
    
    @Test
    public void testConsistentChartBuilderMethods() {
        XYChart xyChart = new XYChartBuilder()
            .width(800)
            .height(600)
            .title("Test")
            .xAxisTitle("X")
            .yAxisTitle("Y")
            .build();
        
        CategoryChart categoryChart = new CategoryChartBuilder()
            .width(800)
            .height(600)
            .title("Test")
            .xAxisTitle("X")
            .yAxisTitle("Y")
            .build();
        
        PieChart pieChart = new PieChartBuilder()
            .width(800)
            .height(600)
            .title("Test")
            .build();
        
        BubbleChart bubbleChart = new BubbleChartBuilder()
            .width(800)
            .height(600)
            .title("Test")
            .xAxisTitle("X")
            .yAxisTitle("Y")
            .build();
        
        assertEquals(800, xyChart.getWidth(), "XY chart width should be set");
        assertEquals(800, categoryChart.getWidth(), "Category chart width should be set");
        assertEquals(800, pieChart.getWidth(), "Pie chart width should be set");
        assertEquals(800, bubbleChart.getWidth(), "Bubble chart width should be set");
    }

    @Test
    public void testConsistentStylerAccess() {
        XYChart xyChart = new XYChartBuilder().build();
        CategoryChart categoryChart = new CategoryChartBuilder().build();
        PieChart pieChart = new PieChartBuilder().build();
        BubbleChart bubbleChart = new BubbleChartBuilder().build();
        
        assertNotNull(xyChart.getStyler(), "XY chart styler should be accessible");
        assertNotNull(categoryChart.getStyler(), "Category chart styler should be accessible");
        assertNotNull(pieChart.getStyler(), "Pie chart styler should be accessible");
        assertNotNull(bubbleChart.getStyler(), "Bubble chart styler should be accessible");
    }

    @Test
    public void testConsistentSeriesMapAccess() {
        XYChart xyChart = new XYChartBuilder().build();
        CategoryChart categoryChart = new CategoryChartBuilder().build();
        PieChart pieChart = new PieChartBuilder().build();
        BubbleChart bubbleChart = new BubbleChartBuilder().build();
        
        assertNotNull(xyChart.getSeriesMap(), "XY chart series map should be accessible");
        assertNotNull(categoryChart.getSeriesMap(), "Category chart series map should be accessible");
        assertNotNull(pieChart.getSeriesMap(), "Pie chart series map should be accessible");
        assertNotNull(bubbleChart.getSeriesMap(), "Bubble chart series map should be accessible");
    }

    @Test
    public void testConsistentTitleHandling() {
        String title = "Test Title";
        XYChart xyChart = new XYChartBuilder().title(title).build();
        CategoryChart categoryChart = new CategoryChartBuilder().title(title).build();
        PieChart pieChart = new PieChartBuilder().title(title).build();
        BubbleChart bubbleChart = new BubbleChartBuilder().title(title).build();
        
        assertEquals(title, xyChart.getTitle(), "XY chart title should match");
        assertEquals(title, categoryChart.getTitle(), "Category chart title should match");
        assertEquals(title, pieChart.getTitle(), "Pie chart title should match");
        assertEquals(title, bubbleChart.getTitle(), "Bubble chart title should match");
    }

    @Test
    public void testConsistentThemeApplication() {
        Styler.ChartTheme theme = Styler.ChartTheme.GGPlot2;
        XYChart xyChart = new XYChartBuilder().theme(theme).build();
        CategoryChart categoryChart = new CategoryChartBuilder().theme(theme).build();
        PieChart pieChart = new PieChartBuilder().theme(theme).build();
        BubbleChart bubbleChart = new BubbleChartBuilder().theme(theme).build();
        
        assertTrue(xyChart.getStyler().getTheme() instanceof GGPlot2Theme);
        assertTrue(categoryChart.getStyler().getTheme() instanceof GGPlot2Theme);
        assertTrue(pieChart.getStyler().getTheme() instanceof GGPlot2Theme);
        assertTrue(bubbleChart.getStyler().getTheme() instanceof GGPlot2Theme);
    }

    @Test
    public void testBoxPlotDataTypes() {
        BoxChart chart = new BoxChartBuilder().build();
        
        // Test with List<Number>
        List<Number> listData = Arrays.asList(1.0, 2.0, 3.0);
        chart.addSeries("list test", listData);
        assertNotNull(chart.getSeriesMap().get("list test"), "Box plot series with List should be added successfully");
        
        // Test with double[]
        double[] arrayData = new double[] {1.0, 2.0, 3.0};
        chart.addSeries("array test", arrayData);
        assertNotNull(chart.getSeriesMap().get("array test"), "Box plot series with array should be added successfully");
    }

    @Test
    public void testDialChartBasics() {
        DialChart chart = new DialChartBuilder().build();
        chart.addSeries("test", 0.42);
        
        assertNotNull(chart.getSeriesMap().get("test"), "Dial chart series should be added successfully");
        assertEquals(1, chart.getSeriesMap().size(), "Dial chart should have one series");
    }

    @Test
    public void testAxisTitleVisibility() {
        XYChart chart = new XYChartBuilder()
            .xAxisTitle("X")
            .yAxisTitle("Y")
            .build();
        
        assertTrue(chart.getStyler().isXAxisTitleVisible(), "X axis title should be visible");
        assertTrue(chart.getStyler().isYAxisTitleVisible(), "Y axis title should be visible");
    }

    @Test
    public void testLogarithmicAxisSupport() {
        XYChart chart = new XYChartBuilder().build();
        
        chart.getStyler().setXAxisLogarithmic(true);
        chart.getStyler().setYAxisLogarithmic(true);
        
        assertTrue(chart.getStyler().isXAxisLogarithmic(), "X axis should be logarithmic");
        assertTrue(chart.getStyler().isYAxisLogarithmic(), "Y axis should be logarithmic");
    }
} 