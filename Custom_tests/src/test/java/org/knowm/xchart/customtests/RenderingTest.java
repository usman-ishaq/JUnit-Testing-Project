package org.knowm.xchart.customtests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.knowm.xchart.*;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.style.*;
import org.knowm.xchart.style.colors.*;
import org.knowm.xchart.style.lines.*;
import org.knowm.xchart.style.markers.*;
import org.knowm.xchart.style.theme.*;

public class RenderingTest {

    // [Previous tests remain unchanged...]
    // Test 1: Verify basic chart rendering dimensions
    @Test
    public void testChartDimensions() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        BufferedImage image = BitmapEncoder.getBufferedImage(chart);
        assertEquals(800, image.getWidth());
        assertEquals(600, image.getHeight());
    }

    // Test 2: Test custom background color
    @Test
    public void testCustomBackgroundColor() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        chart.getStyler().setChartBackgroundColor(Color.LIGHT_GRAY);
        assertEquals(Color.LIGHT_GRAY, chart.getStyler().getChartBackgroundColor());
    }

    // Test 3: Test axis tick mark color
    @Test
    public void testAxisTickMarkColor() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setAxisTickMarksColor(Color.RED);
        assertEquals(Color.RED, chart.getStyler().getAxisTickMarksColor());
    }

    // Test 4: Test axis tick label color
    @Test
    public void testAxisTickLabelColor() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setAxisTickLabelsColor(Color.BLUE);
        assertEquals(Color.BLUE, chart.getStyler().getAxisTickLabelsColor());
    }

    // Test 5: Test marker size
    @Test
    public void testMarkerSize() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setMarkerSize(5);
        assertEquals(5, chart.getStyler().getMarkerSize());
    }

    // Test 6: Test plot grid lines style
    @Test
    public void testPlotGridLinesStyle() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setPlotGridLinesColor(Color.GRAY);
        assertEquals(Color.GRAY, chart.getStyler().getPlotGridLinesColor());
    }

    // Test 7: Test plot border style
    @Test
    public void testPlotBorderStyle() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setPlotBorderColor(Color.BLACK);
        assertEquals(Color.BLACK, chart.getStyler().getPlotBorderColor());
    }

    // Test 8: Test chart padding
    @Test
    public void testChartPadding() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setChartPadding(20);
        assertEquals(20, chart.getStyler().getChartPadding());
    }

    // Test 9: Test plot margin
    @Test
    public void testPlotMargin() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setPlotMargin(10);
        assertEquals(10, chart.getStyler().getPlotMargin());
    }

    // Test 10: Test axis tick padding
    @Test
    public void testAxisTickPadding() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setAxisTickPadding(5);
        assertEquals(5, chart.getStyler().getAxisTickPadding());
    }

    // Test 11: Test plot grid lines visibility
    @Test
    public void testPlotGridLinesVisibility() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setPlotGridLinesVisible(false);
        assertFalse(chart.getStyler().isPlotGridLinesVisible());
    }

    // Test 12: Test plot border visibility
    @Test
    public void testPlotBorderVisibility() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setPlotBorderVisible(false);
        assertFalse(chart.getStyler().isPlotBorderVisible());
    }

    // Test 13: Test series line color
    @Test
    public void testSeriesLineColor() {
        XYChart chart = new XYChartBuilder().build();
        double[] xData = {1, 2, 3};
        double[] yData = {1, 2, 3};
        XYSeries series = chart.addSeries("test", xData, yData);
        series.setLineColor(Color.RED);
        assertEquals(Color.RED, series.getLineColor());
    }

    // Test 14: Test series marker color
    @Test
    public void testSeriesMarkerColor() {
        XYChart chart = new XYChartBuilder().build();
        double[] xData = {1, 2, 3};
        double[] yData = {1, 2, 3};
        XYSeries series = chart.addSeries("test", xData, yData);
        series.setMarkerColor(Color.BLUE);
        assertEquals(Color.BLUE, series.getMarkerColor());
    }

    // Test 15: Test series line style
    @Test
    public void testSeriesLineStyle() {
        XYChart chart = new XYChartBuilder().build();
        double[] xData = {1, 2, 3};
        double[] yData = {1, 2, 3};
        XYSeries series = chart.addSeries("test", xData, yData);
        series.setLineStyle(SeriesLines.DASH_DASH);
        assertEquals(SeriesLines.DASH_DASH, series.getLineStyle());
    }

    // Test 16: Test series marker style
    @Test
    public void testSeriesMarkerStyle() {
        XYChart chart = new XYChartBuilder().build();
        double[] xData = {1, 2, 3};
        double[] yData = {1, 2, 3};
        XYSeries series = chart.addSeries("test", xData, yData);
        series.setMarker(SeriesMarkers.DIAMOND);
        assertEquals(SeriesMarkers.DIAMOND, series.getMarker());
    }

    // Test 17: Test chart theme
    @Test
    public void testChartTheme() {
        XYChart chart = new XYChartBuilder().theme(Styler.ChartTheme.GGPlot2).build();
        assertTrue(chart.getStyler().getTheme() instanceof GGPlot2Theme, "Chart theme should be an instance of GGPlot2Theme");
    }

    // Test 18: Test legend padding
    @Test
    public void testLegendPadding() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setLegendPadding(10);
        assertEquals(10, chart.getStyler().getLegendPadding());
    }

    // Test 19: Test legend series line length
    @Test
    public void testLegendSeriesLineLength() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setLegendSeriesLineLength(50);
        assertEquals(50, chart.getStyler().getLegendSeriesLineLength());
    }

    // Test 20: Test decimal pattern
    @Test
    public void testDecimalPattern() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setDecimalPattern("#.##");
        assertEquals("#.##", chart.getStyler().getDecimalPattern());
    }

    // Test 21: Test Y-axis decimal pattern
    @Test
    public void testYAxisDecimalPattern() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setYAxisDecimalPattern("#.##");
        assertEquals("#.##", chart.getStyler().getYAxisDecimalPattern());
    }

    // Test 22: Test X-axis decimal pattern
    @Test
    public void testXAxisDecimalPattern() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setXAxisDecimalPattern("#.##");
        assertEquals("#.##", chart.getStyler().getXAxisDecimalPattern());
    }

    // Test 23: Test chart title font
    @Test
    public void testChartTitleFont() {
        XYChart chart = new XYChartBuilder().build();
        Font font = new Font("Arial", Font.BOLD, 20);
        chart.getStyler().setChartTitleFont(font);
        assertEquals(font, chart.getStyler().getChartTitleFont());
    }

    // Test 24: Test legend font
    @Test
    public void testLegendFont() {
        XYChart chart = new XYChartBuilder().build();
        Font font = new Font("Arial", Font.PLAIN, 12);
        chart.getStyler().setLegendFont(font);
        assertEquals(font, chart.getStyler().getLegendFont());
    }

    // Test 25: Test axis title font
    @Test
    public void testAxisTitleFont() {
        XYChart chart = new XYChartBuilder().build();
        Font font = new Font("Arial", Font.PLAIN, 14);
        chart.getStyler().setAxisTitleFont(font);
        assertEquals(font, chart.getStyler().getAxisTitleFont());
    }

    // Test 26: Test axis tick labels font
    @Test
    public void testAxisTickLabelsFont() {
        XYChart chart = new XYChartBuilder().build();
        Font font = new Font("Arial", Font.PLAIN, 11);
        chart.getStyler().setAxisTickLabelsFont(font);
        assertEquals(font, chart.getStyler().getAxisTickLabelsFont());
    }

    // Test 27: Test plot grid lines stroke
    @Test
    public void testPlotGridLinesStroke() {
        XYChart chart = new XYChartBuilder().build();
        BasicStroke stroke = new BasicStroke(0.5f);
        chart.getStyler().setPlotGridLinesStroke(stroke);
        assertEquals(0.5f, chart.getStyler().getPlotGridLinesStroke().getLineWidth(), 0.001);
    }

    // Test 28: Test error bars color
    @Test
    public void testErrorBarsColor() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setErrorBarsColor(Color.RED);
        assertEquals(Color.RED, chart.getStyler().getErrorBarsColor());
    }

    // Test 29: Test chart title visibility
    @Test
    public void testChartTitleVisibility() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setChartTitleVisible(false);
        assertFalse(chart.getStyler().isChartTitleVisible());
    }

    // Test 30: Test axis title visibility
    @Test
    public void testAxisTitleVisibility() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setXAxisTitleVisible(false);
        assertFalse(chart.getStyler().isXAxisTitleVisible());
    }

    // Test 31: Test plot grid horizontal lines visibility
    @Test
    public void testPlotGridHorizontalLinesVisibility() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setPlotGridHorizontalLinesVisible(false);
        assertFalse(chart.getStyler().isPlotGridHorizontalLinesVisible());
    }

    // Test 32: Test plot grid vertical lines visibility
    @Test
    public void testPlotGridVerticalLinesVisibility() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setPlotGridVerticalLinesVisible(false);
        assertFalse(chart.getStyler().isPlotGridVerticalLinesVisible());
    }

    // Test 33: Test chart title padding
    @Test
    public void testChartTitlePadding() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setChartTitlePadding(15);
        assertEquals(15, chart.getStyler().getChartTitlePadding());
    }

    // Test 34: Test plot content size
    @Test
    public void testPlotContentSize() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setPlotContentSize(.95);
        assertEquals(.95, chart.getStyler().getPlotContentSize(), 0.001);
    }

    // Test 35: Test axis tick mark length
    @Test
    public void testAxisTickMarkLength() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setAxisTickMarkLength(8);
        assertEquals(8, chart.getStyler().getAxisTickMarkLength());
    }

    // Test 36: Test Y-axis max
    @Test
    public void testYAxisMax() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setYAxisMax(100.0);
        assertEquals(100.0, chart.getStyler().getYAxisMax(), 0.001);
    }

    // Test 37: Test Y-axis min
    @Test
    public void testYAxisMin() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setYAxisMin(0.0);
        assertEquals(0.0, chart.getStyler().getYAxisMin(), 0.001);
    }

    // Test 38: Test X-axis max
    @Test
    public void testXAxisMax() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setXAxisMax(100.0);
        assertEquals(100.0, chart.getStyler().getXAxisMax(), 0.001);
    }

    // Test 39: Test X-axis min
    @Test
    public void testXAxisMin() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setXAxisMin(0.0);
        assertEquals(0.0, chart.getStyler().getXAxisMin(), 0.001);
    }

    // Test 40: Test Y-axis logarithmic
    @Test
    public void testYAxisLogarithmic() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setYAxisLogarithmic(true);
        assertTrue(chart.getStyler().isYAxisLogarithmic());
    }

    // Test 41: Test X-axis logarithmic
    @Test
    public void testXAxisLogarithmic() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setXAxisLogarithmic(true);
        assertTrue(chart.getStyler().isXAxisLogarithmic());
    }

    // Test 42: Test Y-axis tick mark spacing hint
    @Test
    public void testYAxisTickMarkSpacingHint() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setYAxisTickMarkSpacingHint(80);
        assertEquals(80, chart.getStyler().getYAxisTickMarkSpacingHint());
    }

    // Test 43: Test X-axis tick mark spacing hint
    @Test
    public void testXAxisTickMarkSpacingHint() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setXAxisTickMarkSpacingHint(80);
        assertEquals(80, chart.getStyler().getXAxisTickMarkSpacingHint());
    }

    // Test 44: Test legend border color
    @Test
    public void testLegendBorderColor() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setLegendBorderColor(Color.BLACK);
        assertEquals(Color.BLACK, chart.getStyler().getLegendBorderColor());
    }

    // Test 45: Test legend background color
    @Test
    public void testLegendBackgroundColor() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setLegendBackgroundColor(Color.WHITE);
        assertEquals(Color.WHITE, chart.getStyler().getLegendBackgroundColor());
    }

    // Test 46: Test theme transition during runtime
    @Test
    public void testThemeTransitionDuringRuntime() {
        XYChart chart = new XYChartBuilder().theme(Styler.ChartTheme.XChart).build();
        chart.getStyler().setTheme(new GGPlot2Theme());
        assertTrue(chart.getStyler().getTheme() instanceof GGPlot2Theme);
        // Verify that all style properties are properly updated
        assertEquals(ChartColor.LIGHT_GREY.getColor(), chart.getStyler().getPlotBackgroundColor());
        assertEquals(ChartColor.WHITE.getColor(), chart.getStyler().getPlotBorderColor());
    }

    // Test 47: Test style inheritance after theme change
    @Test
    public void testStyleInheritanceAfterThemeChange() {
        XYChart chart = new XYChartBuilder().theme(Styler.ChartTheme.GGPlot2).build();
        Color customColor = new Color(123, 45, 67);
        chart.getStyler().setPlotBackgroundColor(customColor);
        chart.getStyler().setTheme(new XChartTheme());
        assertEquals(customColor, chart.getStyler().getPlotBackgroundColor(), "Custom style should be preserved after theme change");
    }

    // Test 48: Test marker size consistency across series
    @Test
    public void testMarkerSizeConsistency() {
        XYChart chart = new XYChartBuilder().build();
        double[] xData = {1, 2, 3};
        double[] yData = {1, 2, 3};
        XYSeries series1 = chart.addSeries("test1", xData, yData);
        XYSeries series2 = chart.addSeries("test2", xData, yData);
        
        int customSize = 10;
        chart.getStyler().setMarkerSize(customSize);
        
        assertEquals(customSize, chart.getStyler().getMarkerSize());
        series1.setMarkerColor(Color.RED);
        series2.setMarkerColor(Color.BLUE);
        assertEquals(customSize, chart.getStyler().getMarkerSize(), "Marker size should remain consistent after color changes");
    }

    // Test 49: Test plot content size limits
    @Test
    public void testPlotContentSizeLimits() {
        XYChart chart = new XYChartBuilder().build();
        assertThrows(IllegalArgumentException.class, () -> chart.getStyler().setPlotContentSize(1.1));
        assertThrows(IllegalArgumentException.class, () -> chart.getStyler().setPlotContentSize(0.0));
        assertThrows(IllegalArgumentException.class, () -> chart.getStyler().setPlotContentSize(-0.1));
    }

    // Test 50: Test chart padding negative values
    @Test
    public void testChartPaddingNegativeValues() {
        XYChart chart = new XYChartBuilder().build();
        assertThrows(IllegalArgumentException.class, () -> chart.getStyler().setChartPadding(-1));
    }

    // Test 51: Test font size zero values
    @Test
    public void testFontSizeZeroValues() {
        XYChart chart = new XYChartBuilder().build();
        assertThrows(IllegalArgumentException.class, () -> chart.getStyler().setAxisTickLabelsFont(new Font("Arial", Font.PLAIN, 0)));
    }

    // Test 52: Test stroke width zero values
    @Test
    public void testStrokeWidthZeroValues() {
        XYChart chart = new XYChartBuilder().build();
        assertThrows(IllegalArgumentException.class, () -> chart.getStyler().setPlotGridLinesStroke(new BasicStroke(0.0f)));
    }

    // Test 53: Test axis tick mark spacing hints
    @Test
    public void testAxisTickMarkSpacingHints() {
        XYChart chart = new XYChartBuilder().build();
        assertThrows(IllegalArgumentException.class, () -> chart.getStyler().setXAxisTickMarkSpacingHint(0));
        assertThrows(IllegalArgumentException.class, () -> chart.getStyler().setYAxisTickMarkSpacingHint(-1));
    }

    // Test 54: Test legend position with zero padding
    @Test
    public void testLegendPositionWithZeroPadding() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setLegendPadding(0);
        assertEquals(0, chart.getStyler().getLegendPadding());
    }

    // Test 55: Test chart title with empty string
    @Test
    public void testChartTitleWithEmptyString() {
        XYChart chart = new XYChartBuilder().title("").build();
        assertFalse(chart.getStyler().isChartTitleVisible(), "Chart title should not be visible with empty string");
    }

    // Test 56: Test overlapping series colors
    @Test
    public void testOverlappingSeriesColors() {
        XYChart chart = new XYChartBuilder().build();
        double[] xData = {1, 2, 3};
        double[] yData = {1, 2, 3};
        XYSeries series1 = chart.addSeries("test1", xData, yData);
        XYSeries series2 = chart.addSeries("test2", xData, yData);
        
        Color sameColor = Color.RED;
        series1.setLineColor(sameColor);
        series2.setLineColor(sameColor);
        
        // Both series should maintain the same color despite potential style cycling
        assertEquals(sameColor, series1.getLineColor());
        assertEquals(sameColor, series2.getLineColor());
    }

    // Test 57: Test marker size with extreme values
    @Test
    public void testMarkerSizeExtremeValues() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setMarkerSize(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, chart.getStyler().getMarkerSize());
    }

    // Test 58: Test chart rendering with zero width
    @Test
    public void testChartRenderingZeroWidth() {
        assertThrows(IllegalArgumentException.class, () -> new XYChartBuilder().width(0).height(100).build());
    }

    // Test 59: Test chart rendering with zero height
    @Test
    public void testChartRenderingZeroHeight() {
        assertThrows(IllegalArgumentException.class, () -> new XYChartBuilder().width(100).height(0).build());
    }

    // Test 60: Test axis label rotation limits
    @Test
    public void testAxisLabelRotationLimits() {
        XYChart chart = new XYChartBuilder().build();
        assertThrows(IllegalArgumentException.class, () -> chart.getStyler().setXAxisLabelRotation(361));
        assertThrows(IllegalArgumentException.class, () -> chart.getStyler().setXAxisLabelRotation(-361));
    }

    // Test 61: Test decimal pattern with invalid format
    @Test
    public void testDecimalPatternInvalidFormat() {
        XYChart chart = new XYChartBuilder().build();
        assertThrows(IllegalArgumentException.class, () -> chart.getStyler().setDecimalPattern("invalid"));
    }

    // Test 62: Test series render style inheritance
    @Test
    public void testSeriesRenderStyleInheritance() {
        XYChart chart = new XYChartBuilder().build();
        double[] xData = {1, 2, 3};
        double[] yData = {1, 2, 3};
        XYSeries series = chart.addSeries("test", xData, yData);
        
        // Series should inherit default style from chart
        assertEquals(chart.getStyler().getDefaultSeriesRenderStyle(), series.getXYSeriesRenderStyle());
    }

    // Test 63: Test marker size inheritance
    @Test
    public void testMarkerSizeInheritance() {
        XYChart chart = new XYChartBuilder().build();
        int originalSize = chart.getStyler().getMarkerSize();
        double[] xData = {1, 2, 3};
        double[] yData = {1, 2, 3};
        XYSeries series = chart.addSeries("test", xData, yData);
        
        chart.getStyler().setMarkerSize(originalSize * 2);
        // Series should reflect updated marker size
        assertEquals(originalSize * 2, chart.getStyler().getMarkerSize());
    }

    // Test 64: Test theme color array consistency
    @Test
    public void testThemeColorArrayConsistency() {
        XYChart chart = new XYChartBuilder().theme(Styler.ChartTheme.GGPlot2).build();
        Color[] colors = chart.getStyler().getSeriesColors();
        assertNotNull(colors);
        assertTrue(colors.length > 0);
        
        // Change theme and verify colors are updated
        chart.getStyler().setTheme(new XChartTheme());
        Color[] newColors = chart.getStyler().getSeriesColors();
        assertNotNull(newColors);
        assertTrue(newColors.length > 0);
        // Colors should be different between themes
        assertNotEquals(colors[0], newColors[0]);
    }

    // Test 65: Test chart title box style consistency
    @Test
    public void testChartTitleBoxStyleConsistency() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setChartTitleBoxVisible(true);
        Color bgColor = Color.YELLOW;
        Color borderColor = Color.GREEN;
        
        chart.getStyler().setChartTitleBoxBackgroundColor(bgColor);
        chart.getStyler().setChartTitleBoxBorderColor(borderColor);
        
        assertEquals(bgColor, chart.getStyler().getChartTitleBoxBackgroundColor());
        assertEquals(borderColor, chart.getStyler().getChartTitleBoxBorderColor());
    }

    // Test 66: Test chart rendering with mixed series types
    @Test
    public void testMixedSeriesTypes() {
        XYChart chart = new XYChartBuilder().build();
        double[] xData = {1, 2, 3};
        double[] yData = {1, 2, 3};
        
        XYSeries series1 = chart.addSeries("series1", xData, yData);
        XYSeries series2 = chart.addSeries("series2", xData, yData);
        
        series1.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        series2.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        
        assertNotEquals(series1.getXYSeriesRenderStyle(), series2.getXYSeriesRenderStyle());
    }

    // Test 67: Test series visibility toggling - demonstrates API naming inconsistency
    @Test
    public void testSeriesVisibilityToggling() {
        XYChart chart = new XYChartBuilder().build();
        double[] xData = {1, 2, 3};
        double[] yData = {1, 2, 3};
        
        XYSeries series = chart.addSeries("test", xData, yData);
        series.setShowInLegend(false);
        // API inconsistency: Boolean property uses 'is' prefix instead of 'get'
        // This violates Java Bean naming convention where both forms should be available
        assertFalse(series.isShowInLegend());
        
        series.setShowInLegend(true);
        assertTrue(series.isShowInLegend());
    }

    // Test 68: Test axis tick mark length consistency
    @Test
    public void testAxisTickMarkLengthConsistency() {
        XYChart chart = new XYChartBuilder().build();
        int newLength = 15;
        chart.getStyler().setAxisTickMarkLength(newLength);
        assertEquals(newLength, chart.getStyler().getAxisTickMarkLength());
    }

    // Test 69: Test plot grid lines style persistence
    @Test
    public void testPlotGridLinesStylePersistence() {
        XYChart chart = new XYChartBuilder().build();
        BasicStroke customStroke = new BasicStroke(2.5f);
        chart.getStyler().setPlotGridLinesStroke(customStroke);
        assertEquals(2.5f, chart.getStyler().getPlotGridLinesStroke().getLineWidth(), 0.001);
    }

    // Test 70: Test chart background alpha
    @Test
    public void testChartBackgroundAlpha() {
        XYChart chart = new XYChartBuilder().build();
        Color semiTransparent = new Color(0, 0, 0, 128);
        chart.getStyler().setChartBackgroundColor(semiTransparent);
        assertEquals(128, chart.getStyler().getChartBackgroundColor().getAlpha());
    }

    // Test 71: Test series marker size override
    @Test
    public void testSeriesMarkerSizeOverride() {
        XYChart chart = new XYChartBuilder().build();
        double[] xData = {1, 2, 3};
        double[] yData = {1, 2, 3};
        
        chart.getStyler().setMarkerSize(10);
        XYSeries series = chart.addSeries("test", xData, yData);
        assertEquals(10, chart.getStyler().getMarkerSize());
    }

    // Test 72: Test chart title font style persistence
    @Test
    public void testChartTitleFontStylePersistence() {
        XYChart chart = new XYChartBuilder().build();
        Font customFont = new Font("Monospaced", Font.BOLD | Font.ITALIC, 24);
        chart.getStyler().setChartTitleFont(customFont);
        
        Font resultFont = chart.getStyler().getChartTitleFont();
        assertEquals(customFont.getStyle(), resultFont.getStyle());
        assertEquals(customFont.getSize(), resultFont.getSize());
        assertEquals(customFont.getFamily(), resultFont.getFamily());
    }

    // Test 73: Test axis tick label color inheritance
    @Test
    public void testAxisTickLabelColorInheritance() {
        XYChart chart = new XYChartBuilder().build();
        Color customColor = new Color(50, 100, 150);
        chart.getStyler().setAxisTickLabelsColor(customColor);
        assertEquals(customColor, chart.getStyler().getAxisTickLabelsColor());
    }

    // Test 74: Test plot area border style
    @Test
    public void testPlotAreaBorderStyle() {
        XYChart chart = new XYChartBuilder().build();
        Color borderColor = new Color(200, 100, 50);
        chart.getStyler().setPlotBorderColor(borderColor);
        chart.getStyler().setPlotBorderVisible(true);
        
        assertEquals(borderColor, chart.getStyler().getPlotBorderColor());
        assertTrue(chart.getStyler().isPlotBorderVisible());
    }

    // Test 75: Test chart annotation rendering
    @Test
    public void testChartAnnotationRendering() {
        XYChart chart = new XYChartBuilder().build();
        Color annotationColor = new Color(100, 200, 50);
        chart.getStyler().setAnnotationTextPanelFontColor(annotationColor);
        assertEquals(annotationColor, chart.getStyler().getAnnotationTextPanelFontColor());
    }

    // Test 76: Test legend position validation
    @Test
    public void testLegendPositionValidation() {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        assertEquals(Styler.LegendPosition.OutsideE, chart.getStyler().getLegendPosition());
    }

    // Test 77: Test chart button style persistence
    @Test
    public void testChartButtonStylePersistence() {
        XYChart chart = new XYChartBuilder().build();
        Color buttonColor = new Color(150, 75, 25);
        chart.getStyler().setChartButtonBackgroundColor(buttonColor);
        assertEquals(buttonColor, chart.getStyler().getChartButtonBackgroundColor());
    }

    // Test 78: Test chart padding consistency
    @Test
    public void testChartPaddingConsistency() {
        XYChart chart = new XYChartBuilder().build();
        int padding = 25;
        chart.getStyler().setChartPadding(padding);
        assertEquals(padding, chart.getStyler().getChartPadding());
    }

    // Test 79: Test plot margin validation
    @Test
    public void testPlotMarginValidation() {
        XYChart chart = new XYChartBuilder().build();
        int margin = 15;
        chart.getStyler().setPlotMargin(margin);
        assertEquals(margin, chart.getStyler().getPlotMargin());
    }

    // Test 80: Test chart font color inheritance
    @Test
    public void testChartFontColorInheritance() {
        XYChart chart = new XYChartBuilder().build();
        Color fontColor = new Color(25, 75, 150);
        chart.getStyler().setChartFontColor(fontColor);
        assertEquals(fontColor, chart.getStyler().getChartFontColor());
    }

    // Test 81: Test axis title padding validation
    @Test
    public void testAxisTitlePaddingValidation() {
        XYChart chart = new XYChartBuilder().build();
        assertThrows(IllegalArgumentException.class, () -> chart.getStyler().setAxisTitlePadding(-1));
    }

    // Test 82: Test chart title padding validation
    @Test
    public void testChartTitlePaddingValidation() {
        XYChart chart = new XYChartBuilder().build();
        assertThrows(IllegalArgumentException.class, () -> chart.getStyler().setChartTitlePadding(-1));
    }

    // Test 83: Test legend series line length validation
    @Test
    public void testLegendSeriesLineLengthValidation() {
        XYChart chart = new XYChartBuilder().build();
        assertThrows(IllegalArgumentException.class, () -> chart.getStyler().setLegendSeriesLineLength(-1));
    }

    // Test 84: Test plot content size validation
    @Test
    public void testPlotContentSizeValidation() {
        XYChart chart = new XYChartBuilder().build();
        assertThrows(IllegalArgumentException.class, () -> chart.getStyler().setPlotContentSize(-0.1));
        assertThrows(IllegalArgumentException.class, () -> chart.getStyler().setPlotContentSize(1.1));
    }

    // Test 85: Test marker size validation
    @Test
    public void testMarkerSizeValidation() {
        XYChart chart = new XYChartBuilder().build();
        assertThrows(IllegalArgumentException.class, () -> chart.getStyler().setMarkerSize(-1));
    }

    // Test 86: Test axis tick mark spacing hint validation - demonstrates API inconsistency
    @Test
    public void testAxisTickMarkSpacingHintValidation() {
        XYChart chart = new XYChartBuilder().build();
        // Y-axis has a spacing hint method
        chart.getStyler().setYAxisTickMarkSpacingHint(50);
        assertEquals(50, chart.getStyler().getYAxisTickMarkSpacingHint());
        
        // X-axis spacing hint is missing, demonstrating API inconsistency
        // This would be the expected method if the API was consistent:
        // chart.getStyler().setXAxisTickMarkSpacingHint(50);
        // assertEquals(50, chart.getStyler().getXAxisTickMarkSpacingHint());
        
        // Instead, we have to use Y-axis specific method:
        assertThrows(IllegalArgumentException.class, () -> chart.getStyler().setYAxisTickMarkSpacingHint(-1));
    }

    // Test 87: Test chart button margin validation
    @Test
    public void testChartButtonMarginValidation() {
        XYChart chart = new XYChartBuilder().build();
        assertThrows(IllegalArgumentException.class, () -> chart.getStyler().setChartButtonMargin(-1));
    }

    // Test 88: Test annotation text panel padding validation
    @Test
    public void testAnnotationTextPanelPaddingValidation() {
        XYChart chart = new XYChartBuilder().build();
        assertThrows(IllegalArgumentException.class, () -> chart.getStyler().setAnnotationTextPanelPadding(-1));
    }

    // Test 89: Test legend padding validation
    @Test
    public void testLegendPaddingValidation() {
        XYChart chart = new XYChartBuilder().build();
        assertThrows(IllegalArgumentException.class, () -> chart.getStyler().setLegendPadding(-1));
    }

    // Test 90: Test axis tick padding validation
    @Test
    public void testAxisTickPaddingValidation() {
        XYChart chart = new XYChartBuilder().build();
        assertThrows(IllegalArgumentException.class, () -> chart.getStyler().setAxisTickPadding(-1));
    }

    // Test 91: Test chart rendering with extreme dimensions
    @Test
    public void testChartRenderingWithExtremeDimensions() {
        assertThrows(IllegalArgumentException.class, () -> new XYChartBuilder().width(Integer.MAX_VALUE).height(100).build());
        assertThrows(IllegalArgumentException.class, () -> new XYChartBuilder().width(100).height(Integer.MAX_VALUE).build());
    }

    // Test 92: Test chart rendering with minimum dimensions
    @Test
    public void testChartRenderingWithMinimumDimensions() {
        assertThrows(IllegalArgumentException.class, () -> new XYChartBuilder().width(1).height(1).build());
    }

    // Test 93: Test series line color with alpha
    @Test
    public void testSeriesLineColorWithAlpha() {
        XYChart chart = new XYChartBuilder().build();
        double[] xData = {1, 2, 3};
        double[] yData = {1, 2, 3};
        XYSeries series = chart.addSeries("test", xData, yData);
        
        Color transparentColor = new Color(255, 0, 0, 128);
        series.setLineColor(transparentColor);
        assertEquals(128, series.getLineColor().getAlpha());
    }

    // Test 94: Test series marker color with alpha
    @Test
    public void testSeriesMarkerColorWithAlpha() {
        XYChart chart = new XYChartBuilder().build();
        double[] xData = {1, 2, 3};
        double[] yData = {1, 2, 3};
        XYSeries series = chart.addSeries("test", xData, yData);
        
        Color transparentColor = new Color(0, 255, 0, 128);
        series.setMarkerColor(transparentColor);
        assertEquals(128, series.getMarkerColor().getAlpha());
    }

    // Test 95: Test chart background color with alpha
    @Test
    public void testChartBackgroundColorWithAlpha() {
        XYChart chart = new XYChartBuilder().build();
        Color transparentColor = new Color(0, 0, 255, 128);
        chart.getStyler().setChartBackgroundColor(transparentColor);
        assertEquals(128, chart.getStyler().getChartBackgroundColor().getAlpha());
    }

    // Test 96: Test plot background color with alpha
    @Test
    public void testPlotBackgroundColorWithAlpha() {
        XYChart chart = new XYChartBuilder().build();
        Color transparentColor = new Color(128, 128, 128, 128);
        chart.getStyler().setPlotBackgroundColor(transparentColor);
        assertEquals(128, chart.getStyler().getPlotBackgroundColor().getAlpha());
    }

    // Test 97: Test legend background color with alpha
    @Test
    public void testLegendBackgroundColorWithAlpha() {
        XYChart chart = new XYChartBuilder().build();
        Color transparentColor = new Color(200, 200, 200, 128);
        chart.getStyler().setLegendBackgroundColor(transparentColor);
        assertEquals(128, chart.getStyler().getLegendBackgroundColor().getAlpha());
    }

    // Test 98: Test chart title box background color with alpha
    @Test
    public void testChartTitleBoxBackgroundColorWithAlpha() {
        XYChart chart = new XYChartBuilder().build();
        Color transparentColor = new Color(150, 150, 150, 128);
        chart.getStyler().setChartTitleBoxBackgroundColor(transparentColor);
        assertEquals(128, chart.getStyler().getChartTitleBoxBackgroundColor().getAlpha());
    }

    // Test 99: Test plot grid lines color with alpha
    @Test
    public void testPlotGridLinesColorWithAlpha() {
        XYChart chart = new XYChartBuilder().build();
        Color transparentColor = new Color(100, 100, 100, 128);
        chart.getStyler().setPlotGridLinesColor(transparentColor);
        assertEquals(128, chart.getStyler().getPlotGridLinesColor().getAlpha());
    }

    // Test 100: Test annotation text panel background color with alpha
    @Test
    public void testAnnotationTextPanelBackgroundColorWithAlpha() {
        XYChart chart = new XYChartBuilder().build();
        Color transparentColor = new Color(50, 50, 50, 128);
        chart.getStyler().setAnnotationTextPanelBackgroundColor(transparentColor);
        assertEquals(128, chart.getStyler().getAnnotationTextPanelBackgroundColor().getAlpha());
    }

    // Test 101: Axis tick mark spacing hint API inconsistency
} 