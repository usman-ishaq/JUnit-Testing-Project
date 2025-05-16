package org.knowm.xchart.customtests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.knowm.xchart.*;
import org.knowm.xchart.style.*;
import org.knowm.xchart.style.colors.*;
import org.knowm.xchart.style.lines.*;
import org.knowm.xchart.style.markers.*;
import org.knowm.xchart.style.theme.*;
import java.awt.*;
import java.util.*;
import java.text.SimpleDateFormat;
import static org.junit.jupiter.api.Assertions.*;

public class ChartCustomizationTest {
    
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
    public void testChartTitleCustomization() {
        xyChart.setTitle("Custom Title");
        xyChart.getStyler().setChartTitleFont(new Font("Arial", Font.BOLD, 24));
        
        assertEquals("Custom Title", xyChart.getTitle());
        assertEquals(24, xyChart.getStyler().getChartTitleFont().getSize());
    }
    
    @Test
    public void testChartBackgroundCustomization() {
        Color customColor = new Color(240, 240, 240);
        xyChart.getStyler().setChartBackgroundColor(customColor);
        xyChart.getStyler().setPlotBackgroundColor(Color.WHITE);
        
        assertEquals(customColor, xyChart.getStyler().getChartBackgroundColor());
        assertEquals(Color.WHITE, xyChart.getStyler().getPlotBackgroundColor());
    }
    
    @Test
    public void testAxisCustomization() {
        xyChart.getStyler().setXAxisTitleVisible(true);
        xyChart.getStyler().setYAxisTitleVisible(true);
        xyChart.getStyler().setAxisTicksLineVisible(true);
        
        assertTrue(xyChart.getStyler().isXAxisTitleVisible());
        assertTrue(xyChart.getStyler().isYAxisTitleVisible());
        assertTrue(xyChart.getStyler().isAxisTicksLineVisible());
    }
    
    @Test
    public void testLegendCustomization() {
        xyChart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        xyChart.getStyler().setLegendBackgroundColor(Color.WHITE);
        xyChart.getStyler().setLegendBorderColor(Color.BLACK);
        
        assertEquals(Styler.LegendPosition.OutsideE, xyChart.getStyler().getLegendPosition());
        assertEquals(Color.WHITE, xyChart.getStyler().getLegendBackgroundColor());
        assertEquals(Color.BLACK, xyChart.getStyler().getLegendBorderColor());
    }
    
    @Test
    public void testGridLinesCustomization() {
        xyChart.getStyler().setPlotGridLinesVisible(true);
        xyChart.getStyler().setPlotGridLinesColor(Color.LIGHT_GRAY);
        xyChart.getStyler().setPlotGridLinesStroke(new BasicStroke(0.5f));
        
        assertTrue(xyChart.getStyler().isPlotGridLinesVisible());
        assertEquals(Color.LIGHT_GRAY, xyChart.getStyler().getPlotGridLinesColor());
    }
    
    @Test
    public void testMarkerCustomization() {
        double[] xData = {1.0, 2.0, 3.0};
        double[] yData = {2.0, 4.0, 6.0};
        XYSeries series = xyChart.addSeries("test", xData, yData);
        series.setMarker(SeriesMarkers.DIAMOND);
        series.setMarkerColor(Color.RED);
        
        assertEquals(SeriesMarkers.DIAMOND, series.getMarker());
        assertEquals(Color.RED, series.getMarkerColor());
    }
    
    @Test
    public void testLineStyleCustomization() {
        double[] xData = {1.0, 2.0, 3.0};
        double[] yData = {2.0, 4.0, 6.0};
        XYSeries series = xyChart.addSeries("test", xData, yData);
        series.setLineStyle(SeriesLines.DASH_DASH);
        series.setLineColor(Color.BLUE);
        
        assertEquals(SeriesLines.DASH_DASH, series.getLineStyle());
        assertEquals(Color.BLUE, series.getLineColor());
    }
    
    @Test
    public void testDateAxisCustomization() {
        xyChart.getStyler().setDatePattern("yyyy-MM-dd");
        xyChart.getStyler().setXAxisTickMarkSpacingHint(100);
        
        assertEquals("yyyy-MM-dd", xyChart.getStyler().getDatePattern());
        assertEquals(100, xyChart.getStyler().getXAxisTickMarkSpacingHint());
    }
    
    @Test
    public void testDecimalPatternCustomization() {
        xyChart.getStyler().setDecimalPattern("#.##");
        
        assertEquals("#.##", xyChart.getStyler().getDecimalPattern());
    }
    
    @Test
    public void testChartPaddingCustomization() {
        xyChart.getStyler().setChartPadding(20);
        
        assertEquals(20, xyChart.getStyler().getChartPadding());
    }
    
    @Test
    public void testPlotContentSizeCustomization() {
        xyChart.getStyler().setPlotContentSize(.95);
        
        assertEquals(.95, xyChart.getStyler().getPlotContentSize(), 0.001);
    }
    
    @Test
    public void testSeriesColorCycleCustomization() {
        Color[] customColors = new Color[] {Color.RED, Color.BLUE, Color.GREEN};
        xyChart.getStyler().setSeriesColors(customColors);
        
        assertArrayEquals(customColors, xyChart.getStyler().getSeriesColors());
    }
    
    @Test
    public void testMarkerSizeCustomization() {
        xyChart.getStyler().setMarkerSize(10);
        
        assertEquals(10, xyChart.getStyler().getMarkerSize());
    }
    
    @Test
    public void testLegendSeriesLineLengthCustomization() {
        xyChart.getStyler().setLegendSeriesLineLength(50);
        
        assertEquals(50, xyChart.getStyler().getLegendSeriesLineLength());
    }
    
    @Test
    public void testAxisTickLabelsCustomization() {
        xyChart.getStyler().setAxisTickLabelsFont(new Font("Arial", Font.PLAIN, 12));
        xyChart.getStyler().setAxisTickLabelsColor(Color.BLACK);
        
        assertEquals(12, xyChart.getStyler().getAxisTickLabelsFont().getSize());
        assertEquals(Color.BLACK, xyChart.getStyler().getAxisTickLabelsColor());
    }
    
    @Test
    public void testAxisTickMarkCustomization() {
        xyChart.getStyler().setAxisTickMarkLength(10);
        xyChart.getStyler().setAxisTickMarksColor(Color.BLACK);
        
        assertEquals(10, xyChart.getStyler().getAxisTickMarkLength());
        assertEquals(Color.BLACK, xyChart.getStyler().getAxisTickMarksColor());
    }
    
    @Test
    public void testPlotBorderCustomization() {
        xyChart.getStyler().setPlotBorderVisible(true);
        xyChart.getStyler().setPlotBorderColor(Color.BLACK);
        
        assertTrue(xyChart.getStyler().isPlotBorderVisible());
        assertEquals(Color.BLACK, xyChart.getStyler().getPlotBorderColor());
    }
    
    @Test
    public void testAxisTitleCustomization() {
        xyChart.getStyler().setAxisTitleFont(new Font("Arial", Font.BOLD, 14));
        xyChart.getStyler().setAxisTitlePadding(10);
        
        assertEquals(14, xyChart.getStyler().getAxisTitleFont().getSize());
        assertEquals(10, xyChart.getStyler().getAxisTitlePadding());
    }
    
    @Test
    public void testLegendFontCustomization() {
        xyChart.getStyler().setLegendFont(new Font("Arial", Font.PLAIN, 12));
        xyChart.getStyler().setLegendPadding(10);
        
        assertEquals(12, xyChart.getStyler().getLegendFont().getSize());
        assertEquals(10, xyChart.getStyler().getLegendPadding());
    }
    
    @Test
    public void testXYChartWithMultipleSeries() {
        double[] xData = {1.0, 2.0, 3.0};
        double[] yData1 = {2.0, 4.0, 6.0};
        double[] yData2 = {1.0, 2.0, 3.0};
        
        XYSeries series1 = xyChart.addSeries("series1", xData, yData1);
        XYSeries series2 = xyChart.addSeries("series2", xData, yData2);
        
        assertEquals(2, xyChart.getSeriesMap().size());
        assertNotNull(series1);
        assertNotNull(series2);
    }
    
    @Test
    public void testCategoryChartWithStringCategories() {
        java.util.List<String> categories = Arrays.asList("A", "B", "C");
        java.util.List<Number> values = Arrays.asList(1.0, 2.0, 3.0);
        
        CategorySeries series = categoryChart.addSeries("test", categories, values);
        assertNotNull(series);
        assertEquals(categories.size(), series.getXData().size());
    }
    
    @Test
    public void testPieChartWithValues() {
        pieChart.addSeries("A", 30);
        pieChart.addSeries("B", 70);
        
        assertEquals(2, pieChart.getSeriesMap().size());
    }
    
    @Test
    public void testXYChartSeriesVisibility() {
        double[] data = {1.0, 2.0};
        XYSeries series = xyChart.addSeries("test", data, data);
        series.setEnabled(false);
        
        assertFalse(series.isEnabled());
    }
    
    @Test
    public void testLegendFont() {
        Font customFont = new Font("Arial", Font.PLAIN, 14);
        xyChart.getStyler().setLegendFont(customFont);
        
        assertEquals(customFont, xyChart.getStyler().getLegendFont());
    }
    
    @Test
    public void testAxisTickMarkLength() {
        xyChart.getStyler().setAxisTickMarkLength(10);
        
        assertEquals(10, xyChart.getStyler().getAxisTickMarkLength());
    }
    
    @Test
    public void testSeriesColors() {
        Color[] colors = new Color[]{Color.RED, Color.BLUE, Color.GREEN};
        xyChart.getStyler().setSeriesColors(colors);
        
        assertArrayEquals(colors, xyChart.getStyler().getSeriesColors());
    }
    
    @Test
    public void testChartTheme() {
        xyChart.getStyler().setTheme(new MatlabTheme());
        
        assertTrue(xyChart.getStyler().getTheme() instanceof MatlabTheme);
    }
    
    @Test
    public void testAxisLogarithmic() {
        xyChart.getStyler().setXAxisLogarithmic(true);
        xyChart.getStyler().setYAxisLogarithmic(true);
        
        assertTrue(xyChart.getStyler().isXAxisLogarithmic());
        assertTrue(xyChart.getStyler().isYAxisLogarithmic());
    }
    
    @Test
    public void testDatePattern() {
        String pattern = "yyyy-MM-dd";
        xyChart.getStyler().setDatePattern(pattern);
        
        assertEquals(pattern, xyChart.getStyler().getDatePattern());
    }
    
    @Test
    public void testLocaleCustomization() {
        Locale locale = Locale.US;
        xyChart.getStyler().setLocale(locale);
        
        assertEquals(locale, xyChart.getStyler().getLocale());
    }
    
    @Test
    public void testLegendLayout() {
        xyChart.getStyler().setLegendLayout(Styler.LegendLayout.Vertical);
        
        assertEquals(Styler.LegendLayout.Vertical, xyChart.getStyler().getLegendLayout());
    }
    
    @Test
    public void testAxisTickLabelsColor() {
        Color color = Color.BLUE;
        xyChart.getStyler().setAxisTickLabelsColor(color);
        
        assertEquals(color, xyChart.getStyler().getAxisTickLabelsColor());
    }
    
    @Test
    public void testPlotGridLinesVisible() {
        xyChart.getStyler().setPlotGridLinesVisible(false);
        
        assertFalse(xyChart.getStyler().isPlotGridLinesVisible());
    }
    
    @Test
    public void testAxisTitleVisible() {
        xyChart.getStyler().setXAxisTitleVisible(false);
        xyChart.getStyler().setYAxisTitleVisible(false);
        
        assertFalse(xyChart.getStyler().isXAxisTitleVisible());
        assertFalse(xyChart.getStyler().isYAxisTitleVisible());
    }
    
    @Test
    public void testMarkerSize() {
        xyChart.getStyler().setMarkerSize(8);
        
        assertEquals(8, xyChart.getStyler().getMarkerSize());
    }
    
    @Test
    public void testChartTitleVisible() {
        xyChart.getStyler().setChartTitleVisible(false);
        
        assertFalse(xyChart.getStyler().isChartTitleVisible());
    }
    
    @Test
    public void testAxisTicksVisibility() {
        xyChart.getStyler().setAxisTicksMarksVisible(false);
        
        assertFalse(xyChart.getStyler().isAxisTicksMarksVisible());
    }
    
    @Test
    public void testLegendVisible() {
        xyChart.getStyler().setLegendVisible(false);
        
        assertFalse(xyChart.getStyler().isLegendVisible());
    }
    
    @Test
    public void testPlotBorderVisible() {
        xyChart.getStyler().setPlotBorderVisible(false);
        
        assertFalse(xyChart.getStyler().isPlotBorderVisible());
    }
    
    @Test
    public void testToolTipsEnabled() {
        xyChart.getStyler().setToolTipsEnabled(true);
        
        assertTrue(xyChart.getStyler().isToolTipsEnabled());
    }
    
    @Test
    public void testXAxisTickMarkSpacingHint() {
        xyChart.getStyler().setXAxisTickMarkSpacingHint(100);
        
        assertEquals(100, xyChart.getStyler().getXAxisTickMarkSpacingHint());
    }
    
    @Test
    public void testYAxisTickMarkSpacingHint() {
        xyChart.getStyler().setYAxisTickMarkSpacingHint(100);
        
        assertEquals(100, xyChart.getStyler().getYAxisTickMarkSpacingHint());
    }
    
    @Test
    public void testPlotMargin() {
        xyChart.getStyler().setPlotMargin(20);
        
        assertEquals(20, xyChart.getStyler().getPlotMargin());
    }
    
    @Test
    public void testAxisTitlePadding() {
        xyChart.getStyler().setAxisTitlePadding(10);
        
        assertEquals(10, xyChart.getStyler().getAxisTitlePadding());
    }
    
    @Test
    public void testXYSeriesRenderStyle() {
        xyChart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Area);
        
        assertEquals(XYSeries.XYSeriesRenderStyle.Area, xyChart.getStyler().getDefaultSeriesRenderStyle());
    }
    
    @Test
    public void testTimeZone() {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        xyChart.getStyler().setTimezone(timeZone);
        
        assertEquals(timeZone, xyChart.getStyler().getTimezone());
    }
    
    @Test
    public void testLegendSeriesLineLength() {
        xyChart.getStyler().setLegendSeriesLineLength(50);
        
        assertEquals(50, xyChart.getStyler().getLegendSeriesLineLength());
    }
    
    @Test
    public void testErrorBarsColor() {
        Color color = Color.RED;
        xyChart.getStyler().setErrorBarsColor(color);
        
        assertEquals(color, xyChart.getStyler().getErrorBarsColor());
    }
    
    @Test
    public void testAxisTickMarksColor() {
        Color color = Color.BLACK;
        xyChart.getStyler().setAxisTickMarksColor(color);
        
        assertEquals(color, xyChart.getStyler().getAxisTickMarksColor());
    }
    
    @Test
    public void testPlotGridLinesColor() {
        Color color = Color.GRAY;
        xyChart.getStyler().setPlotGridLinesColor(color);
        
        assertEquals(color, xyChart.getStyler().getPlotGridLinesColor());
    }
    
    @Test
    public void testLegendBorderColor() {
        Color color = Color.BLACK;
        xyChart.getStyler().setLegendBorderColor(color);
        
        assertEquals(color, xyChart.getStyler().getLegendBorderColor());
    }
    
    @Test
    public void testLegendBackgroundColor() {
        Color color = Color.WHITE;
        xyChart.getStyler().setLegendBackgroundColor(color);
        
        assertEquals(color, xyChart.getStyler().getLegendBackgroundColor());
    }
    
    @Test
    public void testPlotBackgroundColor() {
        Color color = Color.WHITE;
        xyChart.getStyler().setPlotBackgroundColor(color);
        
        assertEquals(color, xyChart.getStyler().getPlotBackgroundColor());
    }
    
    @Test
    public void testChartBackgroundColor() {
        Color color = new Color(240, 240, 240);
        xyChart.getStyler().setChartBackgroundColor(color);
        
        assertEquals(color, xyChart.getStyler().getChartBackgroundColor());
    }
    
    @Test
    public void testPlotBorderColor() {
        Color color = Color.BLACK;
        xyChart.getStyler().setPlotBorderColor(color);
        
        assertEquals(color, xyChart.getStyler().getPlotBorderColor());
    }
    
    @Test
    public void testChartFontColor() {
        Color color = Color.BLACK;
        xyChart.getStyler().setChartFontColor(color);
        
        assertEquals(color, xyChart.getStyler().getChartFontColor());
    }
    
    @Test
    public void testAxisTickLabelsFont() {
        Font font = new Font("Arial", Font.PLAIN, 12);
        xyChart.getStyler().setAxisTickLabelsFont(font);
        
        assertEquals(font, xyChart.getStyler().getAxisTickLabelsFont());
    }
    
    @Test
    public void testAxisTitleFont() {
        Font font = new Font("Arial", Font.BOLD, 14);
        xyChart.getStyler().setAxisTitleFont(font);
        
        assertEquals(font, xyChart.getStyler().getAxisTitleFont());
    }
    
    @Test
    public void testChartTitleFont() {
        Font font = new Font("Arial", Font.BOLD, 16);
        xyChart.getStyler().setChartTitleFont(font);
        
        assertEquals(font, xyChart.getStyler().getChartTitleFont());
    }
    
    @Test
    public void testAnnotationDistance() {
        pieChart.getStyler().setLabelsDistance(1.2);
        
        assertEquals(1.2, pieChart.getStyler().getLabelsDistance(), 0.001);
    }
    
    @Test
    public void testPieStartAngle() {
        pieChart.getStyler().setStartAngleInDegrees(90);
        
        assertEquals(90, pieChart.getStyler().getStartAngleInDegrees());
    }
    
    @Test
    public void testDonutThickness() {
        pieChart.getStyler().setDonutThickness(0.5);
        
        assertEquals(0.5, pieChart.getStyler().getDonutThickness(), 0.001);
    }
    
    @Test
    public void testCategorySeriesRenderStyle() {
        categoryChart.getStyler().setDefaultSeriesRenderStyle(CategorySeries.CategorySeriesRenderStyle.Bar);
        
        assertEquals(CategorySeries.CategorySeriesRenderStyle.Bar, categoryChart.getStyler().getDefaultSeriesRenderStyle());
    }
    
    @Test
    public void testAvailableSpaceFill() {
        categoryChart.getStyler().setAvailableSpaceFill(.8);
        
        assertEquals(.8, categoryChart.getStyler().getAvailableSpaceFill(), 0.001);
    }
    
    @Test
    public void testOverlapped() {
        categoryChart.getStyler().setOverlapped(true);
        
        assertTrue(categoryChart.getStyler().isOverlapped());
    }
    
    @Test
    public void testStacked() {
        categoryChart.getStyler().setStacked(true);
        
        assertTrue(categoryChart.getStyler().isStacked());
    }
    
    @Test
    public void testYAxisLogarithmic() {
        categoryChart.getStyler().setYAxisLogarithmic(true);
        
        assertTrue(categoryChart.getStyler().isYAxisLogarithmic());
    }
    
    @Test
    public void testBubbleChartWithData() {
        BubbleChart bubbleChart = new BubbleChartBuilder().width(800).height(600).build();
        double[] xData = {1.0, 2.0};
        double[] yData = {2.0, 4.0};
        double[] bubbleData = {10.0, 20.0};
        
        BubbleSeries series = bubbleChart.addSeries("test", xData, yData, bubbleData);
        
        assertNotNull(series);
        assertEquals(2, series.getXData().length);
    }
    
    @Test
    public void testDialChartWithValue() {
        DialChart dialChart = new DialChartBuilder().width(800).height(600).build();
        dialChart.addSeries("test", 0.75);
        
        assertEquals(1, dialChart.getSeriesMap().size());
    }
    
    @Test
    public void testBoxChartWithData() {
        BoxChart boxChart = new BoxChartBuilder().width(800).height(600).build();
        java.util.List<Number> data = Arrays.asList(1, 2, 3, 4, 5);
        boxChart.addSeries("test", data);
        
        assertEquals(1, boxChart.getSeriesMap().size());
    }
    
    @Test
    public void testChartTitleCustomizationWithLargeFont() {
        xyChart.setTitle("Large Title");
        xyChart.getStyler().setChartTitleFont(new Font("Arial", Font.BOLD, 36));
        
        assertEquals("Large Title", xyChart.getTitle());
        assertEquals(36, xyChart.getStyler().getChartTitleFont().getSize());
    }
    
    @Test
    public void testChartBackgroundCustomizationWithLightGray() {
        Color customColor = new Color(245, 245, 245);
        xyChart.getStyler().setChartBackgroundColor(customColor);
        xyChart.getStyler().setPlotBackgroundColor(Color.WHITE);
        
        assertEquals(customColor, xyChart.getStyler().getChartBackgroundColor());
        assertEquals(Color.WHITE, xyChart.getStyler().getPlotBackgroundColor());
    }
    
    @Test
    public void testAxisCustomizationWithThickerTicks() {
        xyChart.getStyler().setAxisTickMarkLength(15);
        xyChart.getStyler().setAxisTicksLineVisible(true);
        
        assertEquals(15, xyChart.getStyler().getAxisTickMarkLength());
        assertTrue(xyChart.getStyler().isAxisTicksLineVisible());
    }
    
    @Test
    public void testLegendCustomizationOutsideNorth() {
        xyChart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        xyChart.getStyler().setLegendBackgroundColor(new Color(240, 240, 240));
        
        assertEquals(Styler.LegendPosition.OutsideE, xyChart.getStyler().getLegendPosition());
        assertEquals(new Color(240, 240, 240), xyChart.getStyler().getLegendBackgroundColor());
    }
    
    @Test
    public void testGridLinesCustomizationWithDarkGray() {
        xyChart.getStyler().setPlotGridLinesVisible(true);
        xyChart.getStyler().setPlotGridLinesColor(new Color(80, 80, 80));
        
        assertTrue(xyChart.getStyler().isPlotGridLinesVisible());
        assertEquals(new Color(80, 80, 80), xyChart.getStyler().getPlotGridLinesColor());
    }
    
    @Test
    public void testMarkerCustomizationWithLargeSize() {
        double[] xData = {1.0, 2.0, 3.0};
        double[] yData = {2.0, 4.0, 6.0};
        XYSeries series = xyChart.addSeries("test", xData, yData);
        series.setMarker(SeriesMarkers.CIRCLE);
        xyChart.getStyler().setMarkerSize(15);
        
        assertEquals(SeriesMarkers.CIRCLE, series.getMarker());
        assertEquals(15, xyChart.getStyler().getMarkerSize());
    }
    
    @Test
    public void testLineStyleCustomizationWithThickLine() {
        double[] xData = {1.0, 2.0, 3.0};
        double[] yData = {2.0, 4.0, 6.0};
        XYSeries series = xyChart.addSeries("test", xData, yData);
        series.setLineStyle(SeriesLines.SOLID);
        series.setLineColor(new Color(0, 100, 200));
        
        assertEquals(SeriesLines.SOLID, series.getLineStyle());
        assertEquals(new Color(0, 100, 200), series.getLineColor());
    }
    
    @Test
    public void testDateAxisCustomizationWithFullFormat() {
        xyChart.getStyler().setDatePattern("yyyy-MM-dd HH:mm:ss");
        xyChart.getStyler().setXAxisTickMarkSpacingHint(120);
        
        assertEquals("yyyy-MM-dd HH:mm:ss", xyChart.getStyler().getDatePattern());
        assertEquals(120, xyChart.getStyler().getXAxisTickMarkSpacingHint());
    }
    
    @Test
    public void testDecimalPatternCustomizationWithPrecision() {
        xyChart.getStyler().setDecimalPattern("#.####");
        
        assertEquals("#.####", xyChart.getStyler().getDecimalPattern());
    }
    
    @Test
    public void testChartPaddingCustomizationLarge() {
        xyChart.getStyler().setChartPadding(40);
        
        assertEquals(40, xyChart.getStyler().getChartPadding());
    }
    
    @Test
    public void testPlotContentSizeCustomizationSmall() {
        xyChart.getStyler().setPlotContentSize(.75);
        
        assertEquals(.75, xyChart.getStyler().getPlotContentSize(), 0.001);
    }
    
    @Test
    public void testSeriesColorCycleCustomizationBrightColors() {
        Color[] customColors = new Color[] {
            new Color(255, 100, 100),
            new Color(100, 255, 100),
            new Color(100, 100, 255)
        };
        xyChart.getStyler().setSeriesColors(customColors);
        
        assertArrayEquals(customColors, xyChart.getStyler().getSeriesColors());
    }
    
    @Test
    public void testMarkerSizeCustomizationSmall() {
        xyChart.getStyler().setMarkerSize(5);
        
        assertEquals(5, xyChart.getStyler().getMarkerSize());
    }
    
    @Test
    public void testLegendSeriesLineLengthCustomizationLong() {
        xyChart.getStyler().setLegendSeriesLineLength(100);
        
        assertEquals(100, xyChart.getStyler().getLegendSeriesLineLength());
    }
    
    @Test
    public void testAxisTickLabelsCustomizationLarge() {
        Font largeFont = new Font("Arial", Font.PLAIN, 16);
        xyChart.getStyler().setAxisTickLabelsFont(largeFont);
        
        assertEquals(16, xyChart.getStyler().getAxisTickLabelsFont().getSize());
    }
    
    @Test
    public void testAxisTickMarkCustomizationLong() {
        xyChart.getStyler().setAxisTickMarkLength(15);
        xyChart.getStyler().setAxisTickMarksColor(new Color(50, 50, 50));
        
        assertEquals(15, xyChart.getStyler().getAxisTickMarkLength());
        assertEquals(new Color(50, 50, 50), xyChart.getStyler().getAxisTickMarksColor());
    }
    
    @Test
    public void testPlotBorderCustomizationThick() {
        xyChart.getStyler().setPlotBorderVisible(true);
        xyChart.getStyler().setPlotBorderColor(new Color(40, 40, 40));
        
        assertTrue(xyChart.getStyler().isPlotBorderVisible());
        assertEquals(new Color(40, 40, 40), xyChart.getStyler().getPlotBorderColor());
    }
    
    @Test
    public void testAxisTitleCustomizationLarge() {
        Font largeFont = new Font("Arial", Font.BOLD, 18);
        xyChart.getStyler().setAxisTitleFont(largeFont);
        xyChart.getStyler().setAxisTitlePadding(15);
        
        assertEquals(18, xyChart.getStyler().getAxisTitleFont().getSize());
        assertEquals(15, xyChart.getStyler().getAxisTitlePadding());
    }
    
    @Test
    public void testLegendFontCustomizationLarge() {
        Font largeFont = new Font("Arial", Font.PLAIN, 16);
        xyChart.getStyler().setLegendFont(largeFont);
        xyChart.getStyler().setLegendPadding(15);
        
        assertEquals(16, xyChart.getStyler().getLegendFont().getSize());
        assertEquals(15, xyChart.getStyler().getLegendPadding());
    }
    
    @Test
    public void testPieChartWithCustomStartAngle() {
        pieChart.getStyler().setStartAngleInDegrees(45);
        
        assertEquals(45, pieChart.getStyler().getStartAngleInDegrees());
    }
    
    @Test
    public void testPieChartWithThinDonut() {
        pieChart.getStyler().setDonutThickness(0.3);
        
        assertEquals(0.3, pieChart.getStyler().getDonutThickness(), 0.001);
    }
    
    @Test
    public void testCategoryChartWithCustomBarWidth() {
        categoryChart.getStyler().setAvailableSpaceFill(0.9);
        
        assertEquals(0.9, categoryChart.getStyler().getAvailableSpaceFill(), 0.001);
    }
    
    @Test
    public void testXYChartWithAreaStyle() {
        xyChart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Area);
        
        assertEquals(XYSeries.XYSeriesRenderStyle.Area, xyChart.getStyler().getDefaultSeriesRenderStyle());
    }
    
    @Test
    public void testCategoryChartWithStackedBars() {
        categoryChart.getStyler().setStacked(true);
        
        assertTrue(categoryChart.getStyler().isStacked());
    }
    
    @Test
    public void testChartWithCustomLocale() {
        xyChart.getStyler().setLocale(Locale.FRANCE);
        
        assertEquals(Locale.FRANCE, xyChart.getStyler().getLocale());
    }
    
    @Test
    public void testChartWithCustomTimeZone() {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+2");
        xyChart.getStyler().setTimezone(timeZone);
        
        assertEquals(timeZone, xyChart.getStyler().getTimezone());
    }
    
    @Test
    public void testDialChartWithCustomValue() {
        DialChart dialChart = new DialChartBuilder().width(800).height(600).build();
        dialChart.addSeries("test", 0.25);
        
        assertEquals(1, dialChart.getSeriesMap().size());
    }
    
    @Test
    public void testBoxChartWithLargeDataSet() {
        BoxChart boxChart = new BoxChartBuilder().width(800).height(600).build();
        java.util.List<Number> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        boxChart.addSeries("test", data);
        
        assertEquals(1, boxChart.getSeriesMap().size());
    }
    
    @Test
    public void testCategoryChartWithOverlappedBars() {
        categoryChart.getStyler().setOverlapped(true);
        
        assertTrue(categoryChart.getStyler().isOverlapped());
    }
    
    @Test
    public void testXYChartWithLogarithmicAxes() {
        xyChart.getStyler().setXAxisLogarithmic(true);
        xyChart.getStyler().setYAxisLogarithmic(true);
        
        assertTrue(xyChart.getStyler().isXAxisLogarithmic());
        assertTrue(xyChart.getStyler().isYAxisLogarithmic());
    }
} 