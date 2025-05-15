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
import java.util.ArrayList;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.Locale;

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

    // Chart Creation Tests
    @Test
    public void testXYChartWithMultipleSeries() {
        XYChart chart = new XYChartBuilder().build();
        double[] xData = {1.0, 2.0, 3.0};
        double[] yData1 = {2.0, 4.0, 6.0};
        double[] yData2 = {1.0, 2.0, 3.0};
        
        XYSeries series1 = chart.addSeries("series1", xData, yData1);
        XYSeries series2 = chart.addSeries("series2", xData, yData2);
        
        assertEquals(2, chart.getSeriesMap().size());
        assertNotNull(series1);
        assertNotNull(series2);
    }

    @Test
    public void testCategoryChartWithStringCategories() {
        CategoryChart chart = new CategoryChartBuilder().build();
        List<String> categories = Arrays.asList("A", "B", "C");
        List<Number> values = Arrays.asList(1.0, 2.0, 3.0);
        
        CategorySeries series = chart.addSeries("test", categories, values);
        assertNotNull(series);
        assertEquals(categories.size(), series.getXData().size());
    }

    @Test
    public void testPieChartWithPercentages() {
        PieChart chart = new PieChartBuilder().build();
        chart.getStyler().setLabelsDistance(1.2);
        chart.addSeries("A", 30);
        chart.addSeries("B", 70);
        
        assertEquals(2, chart.getSeriesMap().size());
        assertEquals(1.2, chart.getStyler().getLabelsDistance(), 0.001);
    }

    // Styling Tests
    @Test
    public void testChartColorCustomization() {
        XYChart chart = new XYChartBuilder().build();
        Color customColor = new Color(50, 100, 150);
        chart.getStyler().setSeriesColors(new Color[]{customColor});
        
        double[] data = {1.0, 2.0};
        XYSeries series = chart.addSeries("test", data, data);
        series.setLineColor(customColor);
        
        assertEquals(customColor, series.getLineColor());
    }

    @Test
    public void testMarkerCustomization() {
        XYChart chart = new XYChartBuilder().build();
        double[] data = {1.0, 2.0};
        XYSeries series = chart.addSeries("test", data, data);
        series.setMarker(SeriesMarkers.DIAMOND);
        
        assertEquals(SeriesMarkers.DIAMOND, series.getMarker());
    }

    @Test
    public void testLineStyleCustomization() {
        XYChart chart = new XYChartBuilder().build();
        double[] data = {1.0, 2.0};
        XYSeries series = chart.addSeries("test", data, data);
        series.setLineStyle(SeriesLines.DASH_DASH);
        
        assertEquals(SeriesLines.DASH_DASH, series.getLineStyle());
    }

    // Theme Tests
    @Test
    public void testXChartTheme() {
        XYChart chart = new XYChartBuilder().theme(Styler.ChartTheme.XChart).build();
        assertTrue(chart.getStyler().getTheme() instanceof XChartTheme);
    }

    @Test
    public void testMatlabTheme() {
        XYChart chart = new XYChartBuilder().theme(Styler.ChartTheme.Matlab).build();
        assertTrue(chart.getStyler().getTheme() instanceof MatlabTheme);
    }

    // Legend Tests
    @Test
    public void testLegendPositioning() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        assertEquals(Styler.LegendPosition.InsideNE, chart.getStyler().getLegendPosition());
    }

    @Test
    public void testLegendLayout() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setLegendLayout(Styler.LegendLayout.Vertical);
        assertEquals(Styler.LegendLayout.Vertical, chart.getStyler().getLegendLayout());
    }

    // Axis Tests
    @Test
    public void testAxisTickMarkConfiguration() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setAxisTickMarkLength(15);
        assertEquals(15, chart.getStyler().getAxisTickMarkLength());
    }

    @Test
    public void testAxisTickLabelsConfiguration() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setAxisTickLabelsColor(Color.RED);
        assertEquals(Color.RED, chart.getStyler().getAxisTickLabelsColor());
    }

    // Grid Tests
    @Test
    public void testGridConfiguration() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setPlotGridLinesVisible(true);
        chart.getStyler().setPlotGridLinesColor(Color.GRAY);
        
        assertTrue(chart.getStyler().isPlotGridLinesVisible());
        assertEquals(Color.GRAY, chart.getStyler().getPlotGridLinesColor());
    }

    // Font Tests
    @Test
    public void testChartFontConfiguration() {
        XYChart chart = new XYChartBuilder().build();
        Font customFont = new Font("Arial", Font.BOLD, 14);
        chart.getStyler().setAxisTitleFont(customFont);
        
        assertEquals(customFont, chart.getStyler().getAxisTitleFont());
    }

    // Data Manipulation Tests
    @Test
    public void testXYChartDataUpdate() {
        XYChart chart = new XYChartBuilder().build();
        double[] xData = {1.0, 2.0};
        double[] yData = {1.0, 2.0};
        XYSeries series = chart.addSeries("test", xData, yData);
        
        // Create new data as List<Number>
        List<Number> newXData = Arrays.asList(2.0, 3.0);
        List<Number> newYData = Arrays.asList(2.0, 4.0);
        
        // Update the series
        chart.updateXYSeries("test", newXData, newYData, null);
        
        // Get the updated series and verify the data
        series = chart.getSeriesMap().get("test");
        assertNotNull(series, "Series should not be null after update");
        
        // Get the Y data and verify it matches
        double[] actualYData = series.getYData();
        assertNotNull(actualYData, "Y data should not be null");
        assertEquals(newYData.size(), actualYData.length, "Y data size should match");
        
        // Compare the values
        for (int i = 0; i < newYData.size(); i++) {
            assertEquals(
                newYData.get(i).doubleValue(),
                actualYData[i],
                0.000001,
                "Y data values should match at index " + i
            );
        }
    }

    @Test
    public void testCategoryChartDataUpdate() {
        CategoryChart chart = new CategoryChartBuilder().build();
        List<String> categories = Arrays.asList("A", "B");
        List<Number> values = Arrays.asList(1.0, 2.0);
        CategorySeries series = chart.addSeries("test", categories, values);
        
        List<Number> newValues = Arrays.asList(3.0, 4.0);
        series = chart.updateCategorySeries("test", categories, newValues, null);
        
        assertEquals(newValues, series.getYData());
    }

    // Error Bar Tests
    @Test
    public void testErrorBars() {
        XYChart chart = new XYChartBuilder().build();
        double[] xData = {1.0, 2.0};
        double[] yData = {1.0, 2.0};
        double[] errorBars = {0.1, 0.1};
        
        XYSeries series = chart.addSeries("test", xData, yData, errorBars);
        assertNotNull(series.getExtraValues());
    }

    // Decimal Formatting Tests
    @Test
    public void testDecimalFormatting() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setDecimalPattern("#.##");
        assertEquals("#.##", chart.getStyler().getDecimalPattern());
    }

    // Date Formatting Tests
    @Test
    public void testDateFormatting() {
        XYChart chart = new XYChartBuilder().build();
        String pattern = "yyyy-MM-dd";
        chart.getStyler().setDatePattern(pattern);
        assertEquals(pattern, chart.getStyler().getDatePattern());
    }

    // Marker Size Tests
    @Test
    public void testMarkerSize() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setMarkerSize(10);
        assertEquals(10, chart.getStyler().getMarkerSize());
    }

    // Chart Padding Tests
    @Test
    public void testChartPadding() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setChartPadding(20);
        assertEquals(20, chart.getStyler().getChartPadding());
    }

    // Plot Content Size Tests
    @Test
    public void testPlotContentSize() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setPlotContentSize(0.9);
        assertEquals(0.9, chart.getStyler().getPlotContentSize(), 0.001);
    }

    // Series Color Cycling Tests
    @Test
    public void testSeriesColorCycling() {
        XYChart chart = new XYChartBuilder().build();
        double[] data = {1.0, 2.0};
        XYSeries series1 = chart.addSeries("s1", data, data);
        XYSeries series2 = chart.addSeries("s2", data, data);
        
        series1.setLineColor(Color.RED);
        series2.setLineColor(Color.BLUE);
        
        assertNotEquals(series1.getLineColor(), series2.getLineColor());
    }

    // Axis Label Rotation Tests
    @Test
    public void testAxisLabelRotation() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setXAxisLabelRotation(45);
        assertEquals(45, chart.getStyler().getXAxisLabelRotation());
    }

    // Chart Title Tests
    @Test
    public void testChartTitleStyle() {
        XYChart chart = new XYChartBuilder().build();
        Font titleFont = new Font("Arial", Font.BOLD, 20);
        chart.getStyler().setChartTitleFont(titleFont);
        
        assertEquals(titleFont, chart.getStyler().getChartTitleFont());
    }

    // Series Line Width Tests
    @Test
    public void testSeriesLineWidth() {
        XYChart chart = new XYChartBuilder().build();
        double[] data = {1.0, 2.0};
        XYSeries series = chart.addSeries("test", data, data);
        series.setLineWidth(2.5f);
        
        assertEquals(2.5f, series.getLineWidth());
    }

    // Zero Line Tests
    @Test
    public void testZeroLineConfiguration() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setPlotGridLinesVisible(true);
        chart.getStyler().setPlotGridLinesColor(Color.LIGHT_GRAY);
        
        assertTrue(chart.getStyler().isPlotGridLinesVisible());
        assertEquals(Color.LIGHT_GRAY, chart.getStyler().getPlotGridLinesColor());
    }

    // Multiple Y-Axis Tests
    @Test
    public void testMultipleYAxis() {
        XYChart chart = new XYChartBuilder().build();
        double[] data = {1.0, 2.0};
        XYSeries series1 = chart.addSeries("s1", data, data);
        series1.setYAxisGroup(0);
        XYSeries series2 = chart.addSeries("s2", data, data);
        series2.setYAxisGroup(1);
        
        assertNotEquals(series1.getYAxisGroup(), series2.getYAxisGroup());
    }

    // Theme Color Tests
    @Test
    public void testThemeColors() {
        XYChart chart = new XYChartBuilder().theme(Styler.ChartTheme.GGPlot2).build();
        Color[] seriesColors = chart.getStyler().getSeriesColors();
        assertNotNull(seriesColors);
        assertTrue(seriesColors.length > 0);
    }

    // Chart Background Tests
    @Test
    public void testChartBackground() {
        XYChart chart = new XYChartBuilder().build();
        Color bgColor = new Color(240, 240, 240);
        chart.getStyler().setChartBackgroundColor(bgColor);
        assertEquals(bgColor, chart.getStyler().getChartBackgroundColor());
    }

    // Plot Background Tests
    @Test
    public void testPlotBackground() {
        XYChart chart = new XYChartBuilder().build();
        Color plotBgColor = new Color(250, 250, 250);
        chart.getStyler().setPlotBackgroundColor(plotBgColor);
        assertEquals(plotBgColor, chart.getStyler().getPlotBackgroundColor());
    }

    // Series Visibility Tests
    @Test
    public void testSeriesVisibility() {
        XYChart chart = new XYChartBuilder().build();
        double[] data = {1.0, 2.0};
        XYSeries series = chart.addSeries("test", data, data);
        series.setEnabled(false);
        
        assertFalse(series.isEnabled());
    }

    // Axis Title Alignment Tests
    @Test
    public void testAxisTitleAlignment() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setXAxisTitleVisible(true);
        chart.getStyler().setYAxisTitleVisible(true);
        
        assertTrue(chart.getStyler().isXAxisTitleVisible());
        assertTrue(chart.getStyler().isYAxisTitleVisible());
    }

    // Plot Border Tests
    @Test
    public void testPlotBorder() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setPlotBorderVisible(true);
        chart.getStyler().setPlotBorderColor(Color.BLACK);
        
        assertTrue(chart.getStyler().isPlotBorderVisible());
        assertEquals(Color.BLACK, chart.getStyler().getPlotBorderColor());
    }

    // Error Bar Color Tests
    @Test
    public void testErrorBarColor() {
        XYChart chart = new XYChartBuilder().build();
        Color errorBarColor = Color.RED;
        chart.getStyler().setErrorBarsColor(errorBarColor);
        assertEquals(errorBarColor, chart.getStyler().getErrorBarsColor());
    }

    // Axis Tick Mark Color Tests
    @Test
    public void testAxisTickMarkColor() {
        XYChart chart = new XYChartBuilder().build();
        Color tickMarkColor = Color.DARK_GRAY;
        chart.getStyler().setAxisTickMarksColor(tickMarkColor);
        assertEquals(tickMarkColor, chart.getStyler().getAxisTickMarksColor());
    }

    // Plot Grid Lines Style Tests
    @Test
    public void testPlotGridLinesStyle() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setPlotGridLinesStroke(new BasicStroke(1.5f));
        assertEquals(1.5f, chart.getStyler().getPlotGridLinesStroke().getLineWidth());
    }

    // Legend Border Tests
    @Test
    public void testLegendBorder() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setLegendBorderColor(Color.GRAY);
        assertEquals(Color.GRAY, chart.getStyler().getLegendBorderColor());
    }

    // Tool Tip Tests
    @Test
    public void testToolTips() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setToolTipsEnabled(true);
        assertTrue(chart.getStyler().isToolTipsEnabled());
    }

    // Series Render Style Tests
    @Test
    public void testSeriesRenderStyle() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Area);
        assertEquals(XYSeries.XYSeriesRenderStyle.Area, chart.getStyler().getDefaultSeriesRenderStyle());
    }

    // Y-Axis Group Tests
    @Test
    public void testYAxisGroup() {
        XYChart chart = new XYChartBuilder().build();
        double[] data = {1.0, 2.0};
        XYSeries series = chart.addSeries("test", data, data);
        series.setYAxisGroup(1);
        assertEquals(1, series.getYAxisGroup());
    }

    // Chart Overlay Tests
    @Test
    public void testChartOverlay() {
        XYChart chart = new XYChartBuilder().build();
        double[] data = {1.0, 2.0};
        XYSeries series1 = chart.addSeries("base", data, data);
        XYSeries series2 = chart.addSeries("overlay", data, data);
        
        assertNotNull(series1);
        assertNotNull(series2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    // Legend Background Tests
    @Test
    public void testLegendBackground() {
        XYChart chart = new XYChartBuilder().build();
        Color legendBgColor = new Color(245, 245, 245);
        chart.getStyler().setLegendBackgroundColor(legendBgColor);
        assertEquals(legendBgColor, chart.getStyler().getLegendBackgroundColor());
    }

    // Chart Padding Percentage Tests
    @Test
    public void testChartPaddingPercentage() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setPlotContentSize(.95);
        assertEquals(.95, chart.getStyler().getPlotContentSize(), 0.001);
    }

    // Y-Axis Decimal Pattern Tests
    @Test
    public void testYAxisDecimalPattern() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setYAxisDecimalPattern("#.###");
        assertEquals("#.###", chart.getStyler().getYAxisDecimalPattern());
    }

    // X-Axis Decimal Pattern Tests
    @Test
    public void testXAxisDecimalPattern() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setXAxisDecimalPattern("#.###");
        assertEquals("#.###", chart.getStyler().getXAxisDecimalPattern());
    }

    // Additional Parameter Variation Tests
    
    @Test
    public void testLargerChartDimensions() {
        XYChart chart = new XYChartBuilder()
            .width(1920)
            .height(1080)
            .build();
        assertEquals(1920, chart.getWidth());
        assertEquals(1080, chart.getHeight());
    }

    @Test
    public void testSmallChartDimensions() {
        XYChart chart = new XYChartBuilder()
            .width(200)
            .height(150)
            .build();
        assertEquals(200, chart.getWidth());
        assertEquals(150, chart.getHeight());
    }

    @Test
    public void testCustomMarkerSize() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setMarkerSize(15);
        assertEquals(15, chart.getStyler().getMarkerSize());
    }

    @Test
    public void testLargeDataSet() {
        XYChart chart = new XYChartBuilder().build();
        double[] xData = new double[1000];
        double[] yData = new double[1000];
        for (int i = 0; i < 1000; i++) {
            xData[i] = i;
            yData[i] = Math.sin(i * 0.1);
        }
        XYSeries series = chart.addSeries("large series", xData, yData);
        assertNotNull(series);
        assertEquals(1000, series.getXData().length);
    }

    @Test
    public void testMultipleColorStyles() {
        XYChart chart = new XYChartBuilder().build();
        Color[] colors = new Color[]{
            new Color(0, 50, 100),
            new Color(100, 50, 0),
            new Color(50, 100, 0)
        };
        chart.getStyler().setSeriesColors(colors);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testCustomChartPadding() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setChartPadding(50);
        assertEquals(50, chart.getStyler().getChartPadding());
    }

    @Test
    public void testAxisLabelRotationExtreme() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setXAxisLabelRotation(90);
        assertEquals(90, chart.getStyler().getXAxisLabelRotation());
    }

    @Test
    public void testCustomFontSizes() {
        XYChart chart = new XYChartBuilder().build();
        Font largeFont = new Font("Arial", Font.BOLD, 24);
        chart.getStyler().setAxisTitleFont(largeFont);
        assertEquals(24, chart.getStyler().getAxisTitleFont().getSize());
    }

    @Test
    public void testMultipleSeriesWithDifferentStyles() {
        XYChart chart = new XYChartBuilder().build();
        double[] data = {1.0, 2.0, 3.0};
        
        XYSeries series1 = chart.addSeries("series1", data, data);
        series1.setLineColor(Color.RED);
        series1.setLineWidth(2.0f);
        
        XYSeries series2 = chart.addSeries("series2", data, data);
        series2.setLineColor(Color.BLUE);
        series2.setLineWidth(3.0f);
        
        assertEquals(2.0f, series1.getLineWidth());
        assertEquals(3.0f, series2.getLineWidth());
    }

    @Test
    public void testChartWithLongTitle() {
        String longTitle = "This is a very long chart title that tests the handling of extended text in the title area of the chart";
        XYChart chart = new XYChartBuilder()
            .title(longTitle)
            .build();
        assertEquals(longTitle, chart.getTitle());
    }

    @Test
    public void testCustomAxisTitles() {
        String xTitle = "Custom X Axis Title (Units)";
        String yTitle = "Custom Y Axis Title (Units)";
        XYChart chart = new XYChartBuilder()
            .xAxisTitle(xTitle)
            .yAxisTitle(yTitle)
            .build();
        assertEquals(xTitle, chart.getXAxisTitle());
        assertEquals(yTitle, chart.getYAxisTitle());
    }

    @Test
    public void testDateFormatWithTime() {
        XYChart chart = new XYChartBuilder().build();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        chart.getStyler().setDatePattern(pattern);
        assertEquals(pattern, chart.getStyler().getDatePattern());
    }

    @Test
    public void testCustomDecimalPattern() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setDecimalPattern("#,###.####");
        assertEquals("#,###.####", chart.getStyler().getDecimalPattern());
    }

    @Test
    public void testSeriesWithNegativeValues() {
        XYChart chart = new XYChartBuilder().build();
        double[] xData = {1.0, 2.0, 3.0};
        double[] yData = {-1.0, -2.0, -3.0};
        XYSeries series = chart.addSeries("negative series", xData, yData);
        assertNotNull(series);
    }

    @Test
    public void testCustomLegendFont() {
        XYChart chart = new XYChartBuilder().build();
        Font legendFont = new Font("Courier", Font.ITALIC, 16);
        chart.getStyler().setLegendFont(legendFont);
        assertEquals(legendFont, chart.getStyler().getLegendFont());
    }

    @Test
    public void testAxisTickMarkLengthCustomization() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setAxisTickMarkLength(8);
        assertEquals(8, chart.getStyler().getAxisTickMarkLength());
    }

    @Test
    public void testPlotContentSizeExtreme() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setPlotContentSize(0.99);
        assertEquals(0.99, chart.getStyler().getPlotContentSize(), 0.001);
    }

    @Test
    public void testMultipleYAxisGroups() {
        XYChart chart = new XYChartBuilder().build();
        double[] data = {1.0, 2.0};
        XYSeries series1 = chart.addSeries("group0", data, data);
        XYSeries series2 = chart.addSeries("group1", data, data);
        XYSeries series3 = chart.addSeries("group2", data, data);
        
        series1.setYAxisGroup(0);
        series2.setYAxisGroup(1);
        series3.setYAxisGroup(2);
        
        assertEquals(0, series1.getYAxisGroup());
        assertEquals(1, series2.getYAxisGroup());
        assertEquals(2, series3.getYAxisGroup());
    }

    @Test
    public void testChartWithCustomBackground() {
        XYChart chart = new XYChartBuilder().build();
        Color customBg = new Color(240, 248, 255); // AliceBlue
        chart.getStyler().setChartBackgroundColor(customBg);
        assertEquals(customBg, chart.getStyler().getChartBackgroundColor());
    }

    @Test
    public void testAxisTickPadding() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setAxisTickPadding(10);
        assertEquals(10, chart.getStyler().getAxisTickPadding());
    }

    @Test
    public void testLegendSeriesLineLength() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setLegendSeriesLineLength(15);
        assertEquals(15, chart.getStyler().getLegendSeriesLineLength());
    }

    @Test
    public void testCustomPlotMargin() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setPlotMargin(20);
        assertEquals(20, chart.getStyler().getPlotMargin());
    }

    @Test
    public void testToolTipBorderColor() {
        XYChart chart = new XYChartBuilder().build();
        Color tooltipBorder = new Color(100, 100, 100);
        chart.getStyler().setToolTipBorderColor(tooltipBorder);
        assertEquals(tooltipBorder, chart.getStyler().getToolTipBorderColor());
    }

    @Test
    public void testAxisTickMarksVisible() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setAxisTicksMarksVisible(false);
        assertFalse(chart.getStyler().isAxisTicksMarksVisible());
    }

    @Test
    public void testCustomLocale() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setLocale(new Locale("fr", "FR"));
        assertEquals(new Locale("fr", "FR"), chart.getStyler().getLocale());
    }

    @Test
    public void testAxisTickLabelsFont() {
        XYChart chart = new XYChartBuilder().build();
        Font tickFont = new Font("SansSerif", Font.PLAIN, 11);
        chart.getStyler().setAxisTickLabelsFont(tickFont);
        assertEquals(tickFont, chart.getStyler().getAxisTickLabelsFont());
    }

    @Test
    public void testPlotGridLinesWidth() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setPlotGridLinesStroke(new BasicStroke(0.7f));
        assertEquals(0.7f, chart.getStyler().getPlotGridLinesStroke().getLineWidth());
    }

    @Test
    public void testAnnotationTextPanelPadding() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setAnnotationTextPanelPadding(10);
        assertEquals(10, chart.getStyler().getAnnotationTextPanelPadding());
    }

    @Test
    public void testPlotTicksMarksVisible() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setPlotTicksMarksVisible(false);
        assertFalse(chart.getStyler().isPlotTicksMarksVisible());
    }

    @Test
    public void testCustomAnnotationTextPanelFont() {
        XYChart chart = new XYChartBuilder().build();
        Font annotationFont = new Font("Dialog", Font.PLAIN, 12);
        chart.getStyler().setAnnotationTextPanelFont(annotationFont);
        assertEquals(annotationFont, chart.getStyler().getAnnotationTextPanelFont());
    }

    @Test
    public void testToolTipFont() {
        XYChart chart = new XYChartBuilder().build();
        Font tooltipFont = new Font("Verdana", Font.PLAIN, 13);
        chart.getStyler().setToolTipFont(tooltipFont);
        assertEquals(tooltipFont, chart.getStyler().getToolTipFont());
    }

    @Test
    public void testChartTitlePadding() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setChartTitlePadding(15);
        assertEquals(15, chart.getStyler().getChartTitlePadding());
    }

    @Test
    public void testLegendPadding() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setLegendPadding(8);
        assertEquals(8, chart.getStyler().getLegendPadding());
    }

    @Test
    public void testAxisTitlePadding() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setAxisTitlePadding(10);
        assertEquals(10, chart.getStyler().getAxisTitlePadding());
    }

    // Additional test cases
    
    @Test
    public void testEmptySeriesNameHandling() {
        XYChart chart = new XYChartBuilder().build();
        double[] data = {1.0, 2.0};
        
        assertThrows(IllegalArgumentException.class, () -> {
            chart.addSeries("", data, data);
        });
    }

    @Test
    public void testZeroLengthDataHandling() {
        XYChart chart = new XYChartBuilder().build();
        double[] emptyData = new double[0];
        
        assertThrows(IllegalArgumentException.class, () -> {
            chart.addSeries("empty", emptyData, emptyData);
        });
    }

    @Test
    public void testSinglePointData() {
        XYChart chart = new XYChartBuilder().build();
        double[] singlePoint = {1.0};
        XYSeries series = chart.addSeries("single", singlePoint, singlePoint);
        
        assertEquals(1, series.getXData().length);
        assertEquals(1.0, series.getXData()[0], 0.0001);
        assertEquals(1.0, series.getYData()[0], 0.0001);
    }

    @Test
    public void testMaximumAllowedFontSize() {
        XYChart chart = new XYChartBuilder().build();
        Font largeFont = new Font("Arial", Font.BOLD, 72);
        chart.getStyler().setAxisTitleFont(largeFont);
        assertEquals(72, chart.getStyler().getAxisTitleFont().getSize());
    }

    @Test
    public void testChartWithAllStylesDisabled() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setPlotGridLinesVisible(false);
        chart.getStyler().setPlotBorderVisible(false);
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAxisTicksMarksVisible(false);
        
        assertFalse(chart.getStyler().isPlotGridLinesVisible());
        assertFalse(chart.getStyler().isPlotBorderVisible());
        assertFalse(chart.getStyler().isLegendVisible());
        assertFalse(chart.getStyler().isAxisTicksMarksVisible());
    }

    @Test
    public void testMultipleSeriesWithSameName() {
        XYChart chart = new XYChartBuilder().build();
        double[] data1 = {1.0, 2.0};
        double[] data2 = {2.0, 3.0};
        
        XYSeries series1 = chart.addSeries("duplicate", data1, data1);
        assertThrows(IllegalArgumentException.class, () -> {
            chart.addSeries("duplicate", data2, data2);
        });
    }

    @Test
    public void testChartWithCustomDateFormatter() {
        XYChart chart = new XYChartBuilder().build();
        String customPattern = "MM/dd/yyyy HH:mm:ss.SSS";
        chart.getStyler().setDatePattern(customPattern);
        chart.getStyler().setLocale(Locale.US);
        
        assertEquals(customPattern, chart.getStyler().getDatePattern());
        assertEquals(Locale.US, chart.getStyler().getLocale());
    }

    @Test
    public void testSeriesWithMismatchedDataLengths() {
        XYChart chart = new XYChartBuilder().build();
        double[] xData = {1.0, 2.0, 3.0};
        double[] yData = {1.0, 2.0};
        
        assertThrows(IllegalArgumentException.class, () -> {
            chart.addSeries("mismatched", xData, yData);
        });
    }

    @Test
    public void testChartWithExtremeMargins() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setChartPadding(100);
        chart.getStyler().setPlotMargin(50);
        
        assertEquals(100, chart.getStyler().getChartPadding());
        assertEquals(50, chart.getStyler().getPlotMargin());
    }

    @Test
    public void testAxisDecimalPatternsIndependently() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setXAxisDecimalPattern("#.0");
        chart.getStyler().setYAxisDecimalPattern("#.00");
        
        assertEquals("#.0", chart.getStyler().getXAxisDecimalPattern());
        assertEquals("#.00", chart.getStyler().getYAxisDecimalPattern());
    }

    @Test
    public void testChartWithAllAxisTitlesEmpty() {
        XYChart chart = new XYChartBuilder()
            .xAxisTitle("")
            .yAxisTitle("")
            .title("")
            .build();
        
        assertEquals("", chart.getXAxisTitle());
        assertEquals("", chart.getYAxisTitle());
        assertEquals("", chart.getTitle());
    }

    @Test
    public void testSeriesVisibilityToggle() {
        XYChart chart = new XYChartBuilder().build();
        double[] data = {1.0, 2.0};
        XYSeries series = chart.addSeries("test", data, data);
        
        series.setEnabled(false);
        assertFalse(series.isEnabled());
        
        series.setEnabled(true);
        assertTrue(series.isEnabled());
    }
} 