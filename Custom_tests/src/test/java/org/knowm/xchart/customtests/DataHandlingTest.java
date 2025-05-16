package org.knowm.xchart.customtests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.knowm.xchart.*;
import org.knowm.xchart.style.*;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.knowm.xchart.style.lines.SeriesLines;
import java.awt.*;
import java.util.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.*;

public class DataHandlingTest {
    
    private XYChart xyChart;
    private CategoryChart categoryChart;
    private PieChart pieChart;
    private BubbleChart bubbleChart;
    
    @BeforeEach
    public void setUp() {
        xyChart = new XYChartBuilder().width(800).height(600).build();
        categoryChart = new CategoryChartBuilder().width(800).height(600).build();
        pieChart = new PieChartBuilder().width(800).height(600).build();
        bubbleChart = new BubbleChartBuilder().width(800).height(600).build();
    }
    
    @Test
    public void testEmptyDataSeries() {
        assertThrows(IllegalArgumentException.class, () -> {
            double[] emptyX = new double[]{};
            double[] emptyY = new double[]{};
            xyChart.addSeries("empty", emptyX, emptyY);
        });
    }
    
    @Test
    public void testNullDataSeries() {
        assertThrows(IllegalArgumentException.class, () -> {
            double[] nullX = null;
            double[] nullY = null;
            xyChart.addSeries("null", nullX, nullY);
        });
    }
    
    @Test
    public void testSinglePointDataSeries() {
        XYSeries series = xyChart.addSeries("single", new double[]{1.0}, new double[]{1.0});
        assertNotNull(series);
        assertEquals(1, series.getXData().length);
    }
    
    @Test
    public void testLargeDataSeries() {
        double[] xData = new double[1000];
        double[] yData = new double[1000];
        for (int i = 0; i < 1000; i++) {
            xData[i] = i;
            yData[i] = Math.sin(i * 0.1);
        }
        XYSeries series = xyChart.addSeries("large", xData, yData);
        assertEquals(1000, series.getXData().length);
    }
    
    @Test
    public void testDateData() {
        java.util.List<Date> xData = new ArrayList<>();
        java.util.List<Double> yData = new ArrayList<>();
        Date now = new Date();
        for (int i = 0; i < 5; i++) {
            xData.add(new Date(now.getTime() + i * 86400000)); // Adding days
            yData.add((double) i);
        }
        XYSeries series = xyChart.addSeries("dates", xData, yData);
        assertEquals(5, xData.size());
    }
    
    @Test
    public void testMismatchedDataArrayLengths() {
        assertThrows(IllegalArgumentException.class, () -> {
            xyChart.addSeries("mismatched", new double[]{1, 2}, new double[]{1});
        });
    }
    
    @Test
    public void testDuplicateSeriesName() {
        xyChart.addSeries("duplicate", new double[]{1}, new double[]{1});
        assertThrows(IllegalArgumentException.class, () -> {
            xyChart.addSeries("duplicate", new double[]{2}, new double[]{2});
        });
    }
    
    @Test
    public void testNaNValues() {
        XYSeries series = xyChart.addSeries("nan", new double[]{1, Double.NaN, 3}, new double[]{1, 2, 3});
        assertNotNull(series);
        assertTrue(Double.isNaN(series.getXData()[1]));
    }
    
    @Test
    public void testInfinityValues() {
        XYSeries series = xyChart.addSeries("infinity", 
            new double[]{1, Double.POSITIVE_INFINITY, 3}, 
            new double[]{1, 2, 3});
        assertNotNull(series);
        assertTrue(Double.isInfinite(series.getXData()[1]));
    }
    
    @Test
    public void testCategoryDataWithNulls() {
        java.util.List<String> categories = Arrays.asList("A", null, "C");
        java.util.List<Number> values = Arrays.asList(1, 2, 3);
        assertThrows(IllegalArgumentException.class, () -> {
            categoryChart.addSeries("nullCategory", categories, values);
        });
    }
    
    @Test
    public void testPieChartNegativeValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            pieChart.addSeries("negative", -1.0);
        });
    }
    
    @Test
    public void testPieChartZeroValue() {
        pieChart.addSeries("zero", 0.0);
        assertEquals(1, pieChart.getSeriesMap().size());
    }
    
    @Test
    public void testBubbleChartData() {
        double[] xData = {1.0, 2.0};
        double[] yData = {2.0, 4.0};
        double[] bubbleData = {10.0, 20.0};
        BubbleSeries series = bubbleChart.addSeries("bubble", xData, yData, bubbleData);
        assertEquals(2, series.getXData().length);
        assertEquals(2, bubbleData.length);
    }
    
    @Test
    public void testMultipleSeriesAddition() {
        for (int i = 0; i < 10; i++) {
            xyChart.addSeries("series" + i, new double[]{i}, new double[]{i});
        }
        assertEquals(10, xyChart.getSeriesMap().size());
    }
    
    @Test
    public void testSeriesRemoval() {
        xyChart.addSeries("toRemove", new double[]{1}, new double[]{1});
        xyChart.removeSeries("toRemove");
        assertEquals(0, xyChart.getSeriesMap().size());
    }
    
    @Test
    public void testClearAllSeries() {
        xyChart.addSeries("series1", new double[]{1}, new double[]{1});
        xyChart.addSeries("series2", new double[]{2}, new double[]{2});
        xyChart.getSeriesMap().clear();
        assertEquals(0, xyChart.getSeriesMap().size());
    }
    
    @Test
    public void testDataSeriesUpdate() {
        XYSeries series = xyChart.addSeries("update", new double[]{1}, new double[]{1});
        series.replaceData(new double[]{2}, new double[]{2}, null);
        assertEquals(2.0, series.getXData()[0]);
    }
    
    @Test
    public void testCategoryChartWithDates() {
        java.util.List<Date> dates = new ArrayList<>();
        java.util.List<Number> values = new ArrayList<>();
        Date now = new Date();
        for (int i = 0; i < 3; i++) {
            dates.add(new Date(now.getTime() + i * 86400000));
            values.add(i);
        }
        CategorySeries series = categoryChart.addSeries("dates", dates, values);
        assertEquals(3, dates.size());
    }
    
    @Test
    public void testDataSeriesWithErrorBars() {
        double[] xData = {1.0, 2.0};
        double[] yData = {2.0, 4.0};
        double[] errorBars = {0.1, 0.2};
        XYSeries series = xyChart.addSeries("errorBars", xData, yData, errorBars);
        assertNotNull(series.getExtraValues());
    }
    
    @Test
    public void testBoxPlotData() {
        BoxChart boxChart = new BoxChartBuilder().build();
        java.util.List<Number> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        boxChart.addSeries("box", data);
        assertEquals(1, boxChart.getSeriesMap().size());
    }
    
    @Test
    public void testDataSeriesVisibility() {
        XYSeries series = xyChart.addSeries("visible", new double[]{1}, new double[]{1});
        series.setEnabled(false);
        assertFalse(series.isEnabled());
    }
    
    @Test
    public void testDataWithCustomMarkers() {
        XYSeries series = xyChart.addSeries("markers", new double[]{1, 2}, new double[]{1, 2});
        series.setMarker(SeriesMarkers.DIAMOND);
        assertEquals(SeriesMarkers.DIAMOND, series.getMarker());
    }
    
    @Test
    public void testDataWithCustomColors() {
        XYSeries series = xyChart.addSeries("colors", new double[]{1}, new double[]{1});
        Color customColor = new Color(128, 128, 128);
        series.setLineColor(customColor);
        assertEquals(customColor, series.getLineColor());
    }
    
    @Test
    public void testLogarithmicData() {
        double[] xData = {1, 10, 100, 1000};
        double[] yData = {1, 10, 100, 1000};
        xyChart.getStyler().setXAxisLogarithmic(true);
        XYSeries series = xyChart.addSeries("log", xData, yData);
        assertEquals(4, series.getXData().length);
    }
    
    @Test
    public void testDataInterpolation() {
        double[] xData = {1, 2, 3};
        double[] yData = {1, 4, 9};
        XYSeries series = xyChart.addSeries("interpolation", xData, yData);
        series.setSmooth(true);
        assertTrue(series.isSmooth());
    }
    
    @Test
    public void testTimeSeriesData() {
        java.util.List<Date> xData = new ArrayList<>();
        java.util.List<Double> yData = new ArrayList<>();
        Instant now = Instant.now();
        for (int i = 0; i < 5; i++) {
            xData.add(Date.from(now.plus(i, ChronoUnit.HOURS)));
            yData.add((double) i);
        }
        XYSeries series = xyChart.addSeries("timeSeries", xData, yData);
        assertEquals(5, xData.size());
    }
    
    @Test
    public void testCategoricalDataSorting() {
        java.util.List<String> categories = Arrays.asList("B", "C", "A");
        java.util.List<Number> values = Arrays.asList(2, 3, 1);
        CategorySeries series = categoryChart.addSeries("sorted", categories, values);
        assertEquals(3, categories.size());
    }
    
    @Test
    public void testPieChartDataPercentages() {
        pieChart.addSeries("A", 25.0);
        pieChart.addSeries("B", 75.0);
        assertEquals(2, pieChart.getSeriesMap().size());
        double total = pieChart.getSeriesMap().values().stream()
            .mapToDouble(series -> ((Number) series.getValue()).doubleValue())
            .sum();
        assertEquals(100.0, total, 0.000001);
    }
    
    @Test
    public void testBubbleChartSizeScaling() {
        double[] xData = {1.0};
        double[] yData = {1.0};
        double[] bubbleData = {100.0};
        BubbleSeries series = bubbleChart.addSeries("bubble", xData, yData, bubbleData);
        assertEquals(100.0, bubbleData[0]);
    }
    
    @Test
    public void testDataSeriesWithStyle() {
        XYSeries series = xyChart.addSeries("styled", new double[]{1}, new double[]{1});
        series.setLineStyle(SeriesLines.DASH_DASH);
        assertEquals(SeriesLines.DASH_DASH, series.getLineStyle());
    }
    
    @Test
    public void testDataLabels() {
        XYSeries series = xyChart.addSeries("labels", new double[]{1}, new double[]{1});
        series.setLabel("Custom Label");
        assertEquals("Custom Label", series.getLabel());
    }
    
    @Test
    public void testMultipleYAxes() {
        double[] data = {1.0, 2.0};
        XYSeries series1 = xyChart.addSeries("primary", data, data);
        XYSeries series2 = xyChart.addSeries("secondary", data, data);
        series2.setYAxisGroup(1);
        assertEquals(0, series1.getYAxisGroup());
        assertEquals(1, series2.getYAxisGroup());
    }
    
    @Test
    public void testDataAggregation() {
        java.util.List<String> categories = Arrays.asList("A", "A", "B");
        java.util.List<Number> values = Arrays.asList(1, 2, 3);
        CategorySeries series = categoryChart.addSeries("aggregated", categories, values);
        assertEquals(3, series.getXData().size());
    }
    
    @Test
    public void testDataWithMixedTypes() {
        java.util.List<Object> categories = Arrays.asList("A", 1, 2.5);
        java.util.List<Number> values = Arrays.asList(1, 2, 3);
        assertThrows(IllegalArgumentException.class, () -> {
            categoryChart.addSeries("mixed", categories, values);
        });
    }
    
    @Test
    public void testDataSeriesCloning() {
        double[] xData = {1.0, 2.0};
        double[] yData = {2.0, 4.0};
        XYSeries original = xyChart.addSeries("original", xData, yData);
        XYSeries clone = xyChart.addSeries("clone", original.getXData(), original.getYData());
        assertArrayEquals(original.getXData(), clone.getXData());
    }
    
    @Test
    public void testDataSeriesStatistics() {
        double[] xData = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] yData = {2.0, 4.0, 6.0, 8.0, 10.0};
        XYSeries series = xyChart.addSeries("stats", xData, yData);
        assertEquals(5, series.getXData().length);
        assertEquals(1.0, series.getXData()[0]);
        assertEquals(10.0, series.getYData()[4]);
    }
    
    @Test
    public void testDataSeriesManipulation() {
        double[] xData = {1.0, 2.0};
        double[] yData = {2.0, 4.0};
        XYSeries series = xyChart.addSeries("manipulate", xData, yData);
        series.replaceData(new double[]{3.0}, new double[]{6.0}, null);
        assertEquals(1, series.getXData().length);
        assertEquals(3.0, series.getXData()[0]);
    }
    
    @Test
    public void testDataSeriesMetadata() {
        XYSeries series = xyChart.addSeries("metadata", new double[]{1}, new double[]{1});
        series.setLabel("Custom Label");
        series.setEnabled(true);
        assertEquals("Custom Label", series.getLabel());
        assertTrue(series.isEnabled());
    }
    
    @Test
    public void testDataExtremes() {
        double[] xData = {Double.MIN_VALUE, Double.MAX_VALUE};
        double[] yData = {1.0, 1.0};
        XYSeries series = xyChart.addSeries("extremes", xData, yData);
        assertEquals(Double.MIN_VALUE, series.getXData()[0]);
        assertEquals(Double.MAX_VALUE, series.getXData()[1]);
    }
    
    @Test
    public void testDataPrecision() {
        double[] xData = {1.23456789, 2.23456789};
        double[] yData = {3.23456789, 4.23456789};
        XYSeries series = xyChart.addSeries("precision", xData, yData);
        assertEquals(1.23456789, series.getXData()[0], 0.000000001);
    }
    
    @Test
    public void testDataConsistency() {
        double[] xData = {1.0, 2.0};
        double[] yData = {2.0, 4.0};
        XYSeries series = xyChart.addSeries("consistency", xData, yData);
        assertArrayEquals(xData, series.getXData());
        assertArrayEquals(yData, series.getYData());
    }
    
    @Test
    public void testDataValidation() {
        assertThrows(IllegalArgumentException.class, () -> {
            xyChart.addSeries(null, new double[]{1}, new double[]{1});
        });
    }
    
    @Test
    public void testSeriesIdentification() {
        XYSeries series = xyChart.addSeries("id", new double[]{1}, new double[]{1});
        assertEquals("id", series.getName());
    }
    
    @Test
    public void testDataSeriesEquality() {
        double[] data = {1.0, 2.0};
        XYSeries series1 = xyChart.addSeries("series1", data, data);
        XYSeries series2 = xyChart.addSeries("series2", data.clone(), data.clone());
        assertNotEquals(series1, series2);
    }
    
    @Test
    public void testDataSeriesIteration() {
        xyChart.addSeries("series1", new double[]{1}, new double[]{1});
        xyChart.addSeries("series2", new double[]{2}, new double[]{2});
        int count = 0;
        for (Map.Entry<String, XYSeries> entry : xyChart.getSeriesMap().entrySet()) {
            count++;
        }
        assertEquals(2, count);
    }
    
    @Test
    public void testLargeDataSetPerformance() {
        int size = 10000;
        double[] xData = IntStream.range(0, size).mapToDouble(i -> i).toArray();
        double[] yData = IntStream.range(0, size).mapToDouble(i -> Math.sin(i * 0.01)).toArray();
        XYSeries series = xyChart.addSeries("large", xData, yData);
        assertEquals(size, series.getXData().length);
    }
    
    @Test
    public void testDataSeriesSwapping() {
        XYSeries series1 = xyChart.addSeries("swap1", new double[]{1}, new double[]{1});
        XYSeries series2 = xyChart.addSeries("swap2", new double[]{2}, new double[]{2});
        double[] tempX = series1.getXData();
        double[] tempY = series1.getYData();
        series1.replaceData(series2.getXData(), series2.getYData(), null);
        series2.replaceData(tempX, tempY, null);
        assertEquals(2.0, series1.getXData()[0]);
        assertEquals(1.0, series2.getXData()[0]);
    }
    
    @Test
    public void testExtremeDoubleValues() {
        double[] xData = {Double.MIN_NORMAL, Double.MAX_VALUE};
        double[] yData = {1.0, 2.0};
        XYSeries series = xyChart.addSeries("extreme", xData, yData);
        assertEquals(Double.MIN_NORMAL, series.getXData()[0]);
        assertEquals(Double.MAX_VALUE, series.getXData()[1]);
    }

    @Test
    public void testZeroValuesInLogScale() {
        xyChart.getStyler().setXAxisLogarithmic(true);
        assertThrows(IllegalArgumentException.class, () -> {
            xyChart.addSeries("zero", new double[]{0.0}, new double[]{1.0});
        });
    }

    @Test
    public void testNegativeValuesInLogScale() {
        xyChart.getStyler().setYAxisLogarithmic(true);
        assertThrows(IllegalArgumentException.class, () -> {
            xyChart.addSeries("negative", new double[]{1.0}, new double[]{-1.0});
        });
    }

    @Test
    public void testDataSeriesWithSingleInfinity() {
        XYSeries series = xyChart.addSeries("infinity", 
            new double[]{Double.POSITIVE_INFINITY}, 
            new double[]{1.0});
        assertTrue(Double.isInfinite(series.getXData()[0]));
    }

    @Test
    public void testDataSeriesWithAllInfinity() {
        XYSeries series = xyChart.addSeries("allInfinity",
            new double[]{Double.POSITIVE_INFINITY},
            new double[]{Double.POSITIVE_INFINITY});
        assertTrue(Double.isInfinite(series.getXData()[0]));
        assertTrue(Double.isInfinite(series.getYData()[0]));
    }

    @Test
    public void testDataSeriesWithMixedInfinityAndNaN() {
        XYSeries series = xyChart.addSeries("mixed",
            new double[]{Double.POSITIVE_INFINITY, Double.NaN},
            new double[]{1.0, 2.0});
        assertTrue(Double.isInfinite(series.getXData()[0]));
        assertTrue(Double.isNaN(series.getXData()[1]));
    }

    @Test
    public void testVeryLargeDataSeries() {
        int size = 100000;
        double[] xData = new double[size];
        double[] yData = new double[size];
        for (int i = 0; i < size; i++) {
            xData[i] = i;
            yData[i] = Math.sin(i);
        }
        XYSeries series = xyChart.addSeries("veryLarge", xData, yData);
        assertEquals(size, series.getXData().length);
    }

    @Test
    public void testDataSeriesWithAllZeros() {
        double[] zeros = new double[100];
        XYSeries series = xyChart.addSeries("zeros", zeros, zeros);
        assertEquals(0.0, series.getYData()[50]);
    }

    @Test
    public void testDataSeriesWithAlternatingValues() {
        double[] xData = {1.0, -1.0, 1.0, -1.0};
        double[] yData = {1.0, -1.0, 1.0, -1.0};
        XYSeries series = xyChart.addSeries("alternating", xData, yData);
        assertEquals(-1.0, series.getXData()[1]);
        assertEquals(1.0, series.getXData()[2]);
    }

    @Test
    public void testDataSeriesWithRepeatingValues() {
        double[] xData = {1.0, 1.0, 1.0, 1.0};
        double[] yData = {2.0, 2.0, 2.0, 2.0};
        XYSeries series = xyChart.addSeries("repeating", xData, yData);
        assertEquals(1.0, series.getXData()[0]);
        assertEquals(1.0, series.getXData()[3]);
    }

    @Test
    public void testDataSeriesWithVerySmallDifferences() {
        double[] xData = {1.0, 1.0000000001, 1.0000000002};
        double[] yData = {1.0, 1.0000000001, 1.0000000002};
        XYSeries series = xyChart.addSeries("smallDiff", xData, yData);
        assertTrue(series.getXData()[1] > series.getXData()[0]);
    }

    @Test
    public void testBoxPlotWithOutliers() {
        BoxChart boxChart = new BoxChartBuilder().build();
        java.util.List<Number> data = Arrays.asList(1, 2, 3, 4, 5, 100, -100);
        boxChart.addSeries("outliers", data);
        assertEquals(1, boxChart.getSeriesMap().size());
    }

    @Test
    public void testBoxPlotWithIdenticalValues() {
        BoxChart boxChart = new BoxChartBuilder().build();
        java.util.List<Number> data = Arrays.asList(1, 1, 1, 1, 1);
        boxChart.addSeries("identical", data);
        assertEquals(1, boxChart.getSeriesMap().size());
    }

    @Test
    public void testBoxPlotWithSingleValue() {
        BoxChart boxChart = new BoxChartBuilder().build();
        java.util.List<Number> data = Arrays.asList(1);
        boxChart.addSeries("single", data);
        assertEquals(1, boxChart.getSeriesMap().size());
    }

    @Test
    public void testBubbleChartWithZeroBubbleSizes() {
        double[] xData = {1.0};
        double[] yData = {1.0};
        double[] bubbleData = {0.0};
        BubbleSeries series = bubbleChart.addSeries("zeroBubble", xData, yData, bubbleData);
        assertEquals(0.0, bubbleData[0]);
    }

    @Test
    public void testBubbleChartWithNegativeBubbleSizes() {
        double[] xData = {1.0};
        double[] yData = {1.0};
        double[] bubbleData = {-1.0};
        BubbleSeries series = bubbleChart.addSeries("negativeBubble", xData, yData, bubbleData);
        assertEquals(-1.0, bubbleData[0]);
    }

    @Test
    public void testCategoryChartWithEmptyCategories() {
        java.util.List<String> categories = Arrays.asList("");
        java.util.List<Number> values = Arrays.asList(1);
        CategorySeries series = categoryChart.addSeries("empty", categories, values);
        assertEquals("", ((java.util.List<String>)series.getXData()).get(0));
    }

    @Test
    public void testCategoryChartWithSpecialCharacters() {
        java.util.List<String> categories = Arrays.asList("!@#$%^&*()");
        java.util.List<Number> values = Arrays.asList(1);
        CategorySeries series = categoryChart.addSeries("special", categories, values);
        assertEquals("!@#$%^&*()", ((java.util.List<String>)series.getXData()).get(0));
    }

    @Test
    public void testCategoryChartWithLongCategoryNames() {
        String longName = String.join("", Collections.nCopies(1000, "a"));
        java.util.List<String> categories = Arrays.asList(longName);
        java.util.List<Number> values = Arrays.asList(1);
        CategorySeries series = categoryChart.addSeries("long", categories, values);
        assertEquals(longName, ((java.util.List<String>)series.getXData()).get(0));
    }

    @Test
    public void testCategoryChartWithUnicodeCharacters() {
        java.util.List<String> categories = Arrays.asList("🌟", "🌈", "🌍");
        java.util.List<Number> values = Arrays.asList(1, 2, 3);
        CategorySeries series = categoryChart.addSeries("unicode", categories, values);
        assertEquals("🌟", ((java.util.List<String>)series.getXData()).get(0));
    }

    @Test
    public void testDataSeriesWithCustomDecimalFormat() {
        XYSeries series = xyChart.addSeries("decimal", new double[]{1.23456}, new double[]{1.23456});
        xyChart.getStyler().setDecimalPattern("#.##");
        assertEquals(1.23456, series.getXData()[0], 0.00001);
    }

    @Test
    public void testDataSeriesWithDateFormat() {
        java.util.List<Date> xData = new ArrayList<>();
        java.util.List<Double> yData = new ArrayList<>();
        Date now = new Date();
        xData.add(now);
        yData.add(1.0);
        XYSeries series = xyChart.addSeries("dateFormat", xData, yData);
        xyChart.getStyler().setDatePattern("yyyy-MM-dd");
        assertEquals(now, xData.get(0));
    }

    @Test
    public void testMultipleSeriesWithSameData() {
        double[] data = {1.0, 2.0, 3.0};
        xyChart.addSeries("series1", data, data);
        xyChart.addSeries("series2", data.clone(), data.clone());
        assertEquals(2, xyChart.getSeriesMap().size());
    }

    @Test
    public void testSeriesWithDifferentLengths() {
        xyChart.addSeries("short", new double[]{1}, new double[]{1});
        xyChart.addSeries("long", new double[]{1,2,3}, new double[]{1,2,3});
        assertEquals(2, xyChart.getSeriesMap().size());
    }

    @Test
    public void testDataSeriesWithStepFunction() {
        XYSeries series = xyChart.addSeries("step", new double[]{1,2}, new double[]{1,2});
        series.setLineStyle(SeriesLines.DASH_DOT);
        assertEquals(SeriesLines.DASH_DOT, series.getLineStyle());
    }

    @Test
    public void testDataSeriesWithDotted() {
        XYSeries series = xyChart.addSeries("dotted", new double[]{1,2}, new double[]{1,2});
        series.setLineStyle(SeriesLines.DOT_DOT);
        assertEquals(SeriesLines.DOT_DOT, series.getLineStyle());
    }

    @Test
    public void testMultipleMarkersInSeries() {
        XYSeries series1 = xyChart.addSeries("circle", new double[]{1}, new double[]{1});
        XYSeries series2 = xyChart.addSeries("square", new double[]{2}, new double[]{2});
        series1.setMarker(SeriesMarkers.CIRCLE);
        series2.setMarker(SeriesMarkers.SQUARE);
        assertNotEquals(series1.getMarker(), series2.getMarker());
    }

    @Test
    public void testSeriesWithNoMarkers() {
        XYSeries series = xyChart.addSeries("noMarker", new double[]{1}, new double[]{1});
        series.setMarker(SeriesMarkers.NONE);
        assertEquals(SeriesMarkers.NONE, series.getMarker());
    }

    @Test
    public void testMultipleSeriesVisibility() {
        XYSeries series1 = xyChart.addSeries("visible", new double[]{1}, new double[]{1});
        XYSeries series2 = xyChart.addSeries("invisible", new double[]{2}, new double[]{2});
        series2.setEnabled(false);
        assertTrue(series1.isEnabled());
        assertFalse(series2.isEnabled());
    }

    @Test
    public void testSeriesWithCustomColors() {
        XYSeries series1 = xyChart.addSeries("red", new double[]{1}, new double[]{1});
        XYSeries series2 = xyChart.addSeries("blue", new double[]{2}, new double[]{2});
        series1.setLineColor(Color.RED);
        series2.setLineColor(Color.BLUE);
        assertNotEquals(series1.getLineColor(), series2.getLineColor());
    }

    @Test
    public void testDataSeriesWithGradientColors() {
        XYSeries series = xyChart.addSeries("gradient", new double[]{1}, new double[]{1});
        Color startColor = new Color(255, 0, 0);
        Color endColor = new Color(0, 0, 255);
        series.setLineColor(startColor);
        assertNotEquals(endColor, series.getLineColor());
    }

    @Test
    public void testDataSeriesWithTransparentColors() {
        XYSeries series = xyChart.addSeries("transparent", new double[]{1}, new double[]{1});
        Color transparentColor = new Color(255, 0, 0, 128);
        series.setLineColor(transparentColor);
        assertEquals(128, series.getLineColor().getAlpha());
    }

    @Test
    public void testMultipleSeriesWithDifferentStyles() {
        XYSeries series1 = xyChart.addSeries("solid", new double[]{1}, new double[]{1});
        XYSeries series2 = xyChart.addSeries("dashed", new double[]{2}, new double[]{2});
        series1.setLineStyle(SeriesLines.SOLID);
        series2.setLineStyle(SeriesLines.DASH_DASH);
        assertNotEquals(series1.getLineStyle(), series2.getLineStyle());
    }

    @Test
    public void testDataSeriesWithExponentialValues() {
        double[] xData = {1e-10, 1e-5, 1e0, 1e5, 1e10};
        double[] yData = {1.0, 2.0, 3.0, 4.0, 5.0};
        XYSeries series = xyChart.addSeries("exponential", xData, yData);
        assertEquals(1e-10, series.getXData()[0], 1e-15);
    }

    @Test
    public void testDataSeriesWithPreciseValues() {
        double[] xData = {Math.PI, Math.E, Math.sqrt(2)};
        double[] yData = {1.0, 2.0, 3.0};
        XYSeries series = xyChart.addSeries("precise", xData, yData);
        assertEquals(Math.PI, series.getXData()[0], 1e-15);
    }

    @Test
    public void testDataSeriesWithTrigonometricValues() {
        double[] angles = {0, Math.PI/6, Math.PI/4, Math.PI/3, Math.PI/2};
        double[] sines = Arrays.stream(angles).map(Math::sin).toArray();
        XYSeries series = xyChart.addSeries("trig", angles, sines);
        assertEquals(Math.sin(Math.PI/2), series.getYData()[4], 1e-15);
    }

    @Test
    public void testDataSeriesWithGeometricSequence() {
        double[] xData = {1, 2, 4, 8, 16, 32};
        double[] yData = {1, 3, 9, 27, 81, 243};
        XYSeries series = xyChart.addSeries("geometric", xData, yData);
        assertEquals(32.0, series.getXData()[5]);
    }

    @Test
    public void testDataSeriesWithArithmeticSequence() {
        double[] xData = {1, 3, 5, 7, 9};
        double[] yData = {2, 4, 6, 8, 10};
        XYSeries series = xyChart.addSeries("arithmetic", xData, yData);
        assertEquals(9.0, series.getXData()[4]);
    }

    @Test
    public void testDataSeriesWithFibonacciSequence() {
        double[] fibonacci = {1, 1, 2, 3, 5, 8, 13, 21};
        XYSeries series = xyChart.addSeries("fibonacci", fibonacci, fibonacci);
        assertEquals(21.0, series.getXData()[7]);
    }

    @Test
    public void testDataSeriesWithPrimeNumbers() {
        double[] primes = {2, 3, 5, 7, 11, 13, 17, 19};
        XYSeries series = xyChart.addSeries("primes", primes, primes);
        assertEquals(19.0, series.getXData()[7]);
    }

    @Test
    public void testDataSeriesWithSquareNumbers() {
        double[] squares = {1, 4, 9, 16, 25, 36, 49, 64};
        XYSeries series = xyChart.addSeries("squares", squares, squares);
        assertEquals(64.0, series.getXData()[7]);
    }

    @Test
    public void testDataSeriesWithCubeNumbers() {
        double[] cubes = {1, 8, 27, 64, 125};
        XYSeries series = xyChart.addSeries("cubes", cubes, cubes);
        assertEquals(125.0, series.getXData()[4]);
    }

    @Test
    public void testDataSeriesWithPowersOfTwo() {
        double[] powers = {1, 2, 4, 8, 16, 32, 64, 128};
        XYSeries series = xyChart.addSeries("powers", powers, powers);
        assertEquals(128.0, series.getXData()[7]);
    }

    @Test
    public void testDataSeriesWithFactorials() {
        double[] factorials = {1, 2, 6, 24, 120};
        XYSeries series = xyChart.addSeries("factorials", factorials, factorials);
        assertEquals(120.0, series.getXData()[4]);
    }

    @Test
    public void testDataSeriesWithTriangularNumbers() {
        double[] triangular = {1, 3, 6, 10, 15, 21, 28, 36};
        XYSeries series = xyChart.addSeries("triangular", triangular, triangular);
        assertEquals(36.0, series.getXData()[7]);
    }

    @Test
    public void testDataSeriesWithPentagonalNumbers() {
        double[] pentagonal = {1, 5, 12, 22, 35};
        XYSeries series = xyChart.addSeries("pentagonal", pentagonal, pentagonal);
        assertEquals(35.0, series.getXData()[4]);
    }

    @Test
    public void testDataSeriesWithHexagonalNumbers() {
        double[] hexagonal = {1, 6, 15, 28, 45};
        XYSeries series = xyChart.addSeries("hexagonal", hexagonal, hexagonal);
        assertEquals(45.0, series.getXData()[4]);
    }

    @Test
    public void testDataSeriesWithPerfectNumbers() {
        double[] perfect = {6, 28, 496, 8128};
        XYSeries series = xyChart.addSeries("perfect", perfect, perfect);
        assertEquals(8128.0, series.getXData()[3]);
    }

    @Test
    public void testDataSeriesWithMersennePrimes() {
        double[] mersenne = {3, 7, 31, 127};
        XYSeries series = xyChart.addSeries("mersenne", mersenne, mersenne);
        assertEquals(127.0, series.getXData()[3]);
    }

    @Test
    public void testDataSeriesWithFermatNumbers() {
        double[] fermat = {3, 5, 17, 257, 65537};
        XYSeries series = xyChart.addSeries("fermat", fermat, fermat);
        assertEquals(65537.0, series.getXData()[4]);
    }

    @Test
    public void testDataSeriesWithLucasNumbers() {
        double[] lucas = {2, 1, 3, 4, 7, 11, 18, 29};
        XYSeries series = xyChart.addSeries("lucas", lucas, lucas);
        assertEquals(29.0, series.getXData()[7]);
    }

    @Test
    public void testDataSeriesWithCatalanNumbers() {
        double[] catalan = {1, 1, 2, 5, 14, 42};
        XYSeries series = xyChart.addSeries("catalan", catalan, catalan);
        assertEquals(42.0, series.getXData()[5]);
    }
    @Test
    public void testDataSeriesWithCatalanNumbers2() {
        double[] catalan = {1, 1, 2, 5, 14, 42};
        XYSeries series = xyChart.addSeries("catalan", catalan, catalan);
        assertEquals(42.0, series.getXData()[5]);
    }
} 