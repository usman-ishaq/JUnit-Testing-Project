package org.knowm.xchart.customtests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.SeriesMarkers;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class DataSeriesTest {
    
    private XYChart xyChart;
    private CategoryChart categoryChart;
    private PieChart pieChart;
    
    @BeforeEach
    public void setUp() {
        xyChart = new XYChartBuilder().width(800).height(600).build();
        categoryChart = new CategoryChartBuilder().width(800).height(600).build();
        pieChart = new PieChartBuilder().width(800).height(600).build();
    }
    
    @Test
    public void testXYSeriesAddition() {
        double[] xData = {1.0, 2.0, 3.0};
        double[] yData = {2.0, 4.0, 6.0};
        
        XYSeries series = xyChart.addSeries("test series", xData, yData);
        
        assertNotNull(series, "Series should be created successfully");
        assertEquals("test series", series.getName(), "Series name should match");
        assertArrayEquals(xData, series.getXData(), "X data should match");
        assertArrayEquals(yData, series.getYData(), "Y data should match");
    }
    
    @Test
    public void testMultipleSeriesAddition() {
        double[] xData1 = {1.0, 2.0, 3.0};
        double[] yData1 = {2.0, 4.0, 6.0};
        double[] xData2 = {1.0, 2.0, 3.0};
        double[] yData2 = {1.0, 2.0, 3.0};
        
        XYSeries series1 = xyChart.addSeries("series 1", xData1, yData1);
        XYSeries series2 = xyChart.addSeries("series 2", xData2, yData2);
        
        assertEquals(2, xyChart.getSeriesMap().size(), "Chart should have two series");
        assertTrue(xyChart.getSeriesMap().containsKey("series 1"), "Chart should contain series 1");
        assertTrue(xyChart.getSeriesMap().containsKey("series 2"), "Chart should contain series 2");
    }
    
    @Test
    public void testCategorySeriesAddition() {
        List<String> categories = Arrays.asList("A", "B", "C");
        List<Number> values = Arrays.asList(1.0, 2.0, 3.0);
        
        CategorySeries series = categoryChart.addSeries("test category", categories, values);
        
        assertNotNull(series, "Category series should be created successfully");
        assertEquals("test category", series.getName(), "Series name should match");
        assertEquals(3, categories.size(), "Categories size should match");
        assertEquals(3, values.size(), "Values size should match");
    }
    
    @Test
    public void testPieSeriesAddition() {
        pieChart.addSeries("slice 1", 30.0);
        pieChart.addSeries("slice 2", 70.0);
        
        Map<String, PieSeries> seriesMap = pieChart.getSeriesMap();
        assertEquals(2, seriesMap.size(), "Pie chart should have two slices");
        assertTrue(seriesMap.containsKey("slice 1"), "Pie chart should contain slice 1");
        assertTrue(seriesMap.containsKey("slice 2"), "Pie chart should contain slice 2");
    }
    
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000})
    public void testLargeDataSeries(int size) {
        double[] xData = new double[size];
        double[] yData = new double[size];
        for (int i = 0; i < size; i++) {
            xData[i] = i;
            yData[i] = Math.sin(i);
        }
        
        XYSeries series = xyChart.addSeries("large series", xData, yData);
        assertEquals(size, series.getXData().length, "Series size should match input size");
    }
    
    @Test
    public void testSeriesCustomization() {
        double[] xData = {1.0, 2.0, 3.0};
        double[] yData = {2.0, 4.0, 6.0};
        
        XYSeries series = xyChart.addSeries("custom series", xData, yData);
        series.setMarker(SeriesMarkers.DIAMOND);
        series.setLineWidth(2.5f);
        
        assertEquals(SeriesMarkers.DIAMOND, series.getMarker(), "Series marker should match");
        assertEquals(2.5f, series.getLineWidth(), "Series line width should match");
    }
    
    @Test
    public void testSeriesRemoval() {
        double[] xData = {1.0, 2.0, 3.0};
        double[] yData = {2.0, 4.0, 6.0};
        
        xyChart.addSeries("series to remove", xData, yData);
        assertEquals(1, xyChart.getSeriesMap().size(), "Chart should have one series");
        
        xyChart.removeSeries("series to remove");
        assertEquals(0, xyChart.getSeriesMap().size(), "Chart should have no series after removal");
    }
    
    @Test
    public void testNullDataHandling() {
        // X data can be null, it will be auto-generated
        double[] yData = {1.0, 2.0, 3.0};
        XYSeries series = xyChart.addSeries("auto x series", null, yData);
        assertNotNull(series, "Series should be created with auto-generated X data");
        assertEquals(yData.length, series.getXData().length, "X data should be auto-generated with same length as Y data");
        
        // Y data cannot be null
        assertThrows(IllegalArgumentException.class, () -> {
            xyChart.addSeries("null y series", new double[]{1.0, 2.0, 3.0}, null);
        }, "Should throw exception for null y data");
    }
} 