package org.knowm.xchart.customtests;

import org.junit.jupiter.api.Test;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.XYStyler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.knowm.xchart.XYSeries;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ThemeStyleTest {

    @Test
    public void testChartBackgroundColor() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(255, 255, 255);
        chart.getStyler().setChartBackgroundColor(color);
        assertEquals(color, chart.getStyler().getChartBackgroundColor());
    }

    @Test
    public void testChartFontColor() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(0, 0, 0);
        chart.getStyler().setChartFontColor(color);
        assertEquals(color, chart.getStyler().getChartFontColor());
    }

    @Test
    public void testPlotBackgroundColor() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(240, 240, 240);
        chart.getStyler().setPlotBackgroundColor(color);
        assertEquals(color, chart.getStyler().getPlotBackgroundColor());
    }

    @Test
    public void testAxisTickLabelsColor() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(50, 50, 50);
        chart.getStyler().setAxisTickLabelsColor(color);
        assertEquals(color, chart.getStyler().getAxisTickLabelsColor());
    }

    @Test
    public void testLegendBackgroundColor() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(200, 200, 200);
        chart.getStyler().setLegendBackgroundColor(color);
        assertEquals(color, chart.getStyler().getLegendBackgroundColor());
    }

    @Test
    public void testGridLinesColor() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(220, 220, 220);
        chart.getStyler().setPlotGridLinesColor(color);
        assertEquals(color, chart.getStyler().getPlotGridLinesColor());
    }

    @Test
    public void testMarkerSize() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int size = 10;
        chart.getStyler().setMarkerSize(size);
        assertEquals(size, chart.getStyler().getMarkerSize());
    }

    @Test
    public void testAxisTickMarkLength() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int length = 8;
        chart.getStyler().setAxisTickMarkLength(length);
        assertEquals(length, chart.getStyler().getAxisTickMarkLength());
    }

    @Test
    public void testPlotMargin() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int margin = 5;
        chart.getStyler().setPlotMargin(margin);
        assertEquals(margin, chart.getStyler().getPlotMargin());
    }

    @Test
    public void testLegendSeriesLineLength() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int length = 15;
        chart.getStyler().setLegendSeriesLineLength(length);
        assertEquals(length, chart.getStyler().getLegendSeriesLineLength());
    }

    @Test
    public void testAxisTickPadding() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int padding = 4;
        chart.getStyler().setAxisTickPadding(padding);
        assertEquals(padding, chart.getStyler().getAxisTickPadding());
    }

    @Test
    public void testPlotContentSize() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        double size = 0.95;
        chart.getStyler().setPlotContentSize(size);
        assertEquals(size, chart.getStyler().getPlotContentSize(), 0.001);
    }

    @Test
    public void testLegendVisible() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        chart.getStyler().setLegendVisible(false);
        assertFalse(chart.getStyler().isLegendVisible());
    }

    @Test
    public void testPlotGridLinesVisible() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        chart.getStyler().setPlotGridLinesVisible(false);
        assertFalse(chart.getStyler().isPlotGridLinesVisible());
    }

    @Test
    public void testDefaultSeriesRenderStyle() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        XYSeries.XYSeriesRenderStyle style = XYSeries.XYSeriesRenderStyle.Line;
        chart.getStyler().setDefaultSeriesRenderStyle(style);
        assertEquals(style, chart.getStyler().getDefaultSeriesRenderStyle());
    }

    @Test
    public void testMarker() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        XYSeries series = chart.addSeries("test", new double[]{1,2}, new double[]{1,2});
        series.setMarker(SeriesMarkers.CIRCLE);
        assertEquals(SeriesMarkers.CIRCLE, series.getMarker());
    }

    @Test
    public void testSeriesColors() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color[] colors = new Color[]{Color.RED, Color.BLUE, Color.GREEN};
        chart.getStyler().setSeriesColors(colors);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testDecimalPattern() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        String pattern = "#.##";
        chart.getStyler().setDecimalPattern(pattern);
        assertEquals(pattern, chart.getStyler().getDecimalPattern());
    }

    @Test
    public void testLocale() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        chart.getStyler().setLocale(java.util.Locale.US);
        assertEquals(java.util.Locale.US, chart.getStyler().getLocale());
    }

    @Test
    public void testDatePattern() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        String pattern = "yyyy-MM-dd";
        chart.getStyler().setDatePattern(pattern);
        assertEquals(pattern, chart.getStyler().getDatePattern());
    }

    @Test
    public void testLegendPosition() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Styler.LegendPosition position = Styler.LegendPosition.OutsideE;
        chart.getStyler().setLegendPosition(position);
        assertEquals(position, chart.getStyler().getLegendPosition());
    }

    @Test
    public void testLegendLayout() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Styler.LegendLayout layout = Styler.LegendLayout.Horizontal;
        chart.getStyler().setLegendLayout(layout);
        assertEquals(layout, chart.getStyler().getLegendLayout());
    }

    @Test
    public void testToolTipsEnabled() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        chart.getStyler().setToolTipsEnabled(true);
        assertTrue(chart.getStyler().isToolTipsEnabled());
    }

    @Test
    public void testToolTipBackgroundColor() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(230, 230, 230);
        chart.getStyler().setToolTipBackgroundColor(color);
        assertEquals(color, chart.getStyler().getToolTipBackgroundColor());
    }

    @Test
    public void testToolTipBorderColor() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(200, 200, 200);
        chart.getStyler().setToolTipBorderColor(color);
        assertEquals(color, chart.getStyler().getToolTipBorderColor());
    }

    @Test
    public void testToolTipHighlightColor() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(255, 255, 0);
        chart.getStyler().setToolTipHighlightColor(color);
        assertEquals(color, chart.getStyler().getToolTipHighlightColor());
    }

    @Test
    public void testChartBackgroundColorWithAlpha() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(255, 255, 255, 128);
        chart.getStyler().setChartBackgroundColor(color);
        assertEquals(color, chart.getStyler().getChartBackgroundColor());
    }

    @Test
    public void testChartFontColorDark() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(20, 20, 20);
        chart.getStyler().setChartFontColor(color);
        assertEquals(color, chart.getStyler().getChartFontColor());
    }

    @Test
    public void testPlotBackgroundColorDark() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(40, 40, 40);
        chart.getStyler().setPlotBackgroundColor(color);
        assertEquals(color, chart.getStyler().getPlotBackgroundColor());
    }

    @Test
    public void testAxisTickLabelsColorLight() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(200, 200, 200);
        chart.getStyler().setAxisTickLabelsColor(color);
        assertEquals(color, chart.getStyler().getAxisTickLabelsColor());
    }

    @Test
    public void testLegendBackgroundColorTransparent() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(200, 200, 200, 128);
        chart.getStyler().setLegendBackgroundColor(color);
        assertEquals(color, chart.getStyler().getLegendBackgroundColor());
    }

    @Test
    public void testGridLinesColorLight() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(240, 240, 240);
        chart.getStyler().setPlotGridLinesColor(color);
        assertEquals(color, chart.getStyler().getPlotGridLinesColor());
    }

    @Test
    public void testLargeMarkerSize() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int size = 20;
        chart.getStyler().setMarkerSize(size);
        assertEquals(size, chart.getStyler().getMarkerSize());
    }

    @Test
    public void testSmallMarkerSize() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int size = 5;
        chart.getStyler().setMarkerSize(size);
        assertEquals(size, chart.getStyler().getMarkerSize());
    }

    @Test
    public void testLongAxisTickMarkLength() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int length = 16;
        chart.getStyler().setAxisTickMarkLength(length);
        assertEquals(length, chart.getStyler().getAxisTickMarkLength());
    }

    @Test
    public void testShortAxisTickMarkLength() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int length = 4;
        chart.getStyler().setAxisTickMarkLength(length);
        assertEquals(length, chart.getStyler().getAxisTickMarkLength());
    }

    @Test
    public void testLargePlotMargin() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int margin = 20;
        chart.getStyler().setPlotMargin(margin);
        assertEquals(margin, chart.getStyler().getPlotMargin());
    }

    @Test
    public void testSmallPlotMargin() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int margin = 2;
        chart.getStyler().setPlotMargin(margin);
        assertEquals(margin, chart.getStyler().getPlotMargin());
    }

    @Test
    public void testLongLegendSeriesLineLength() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int length = 30;
        chart.getStyler().setLegendSeriesLineLength(length);
        assertEquals(length, chart.getStyler().getLegendSeriesLineLength());
    }

    @Test
    public void testShortLegendSeriesLineLength() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int length = 8;
        chart.getStyler().setLegendSeriesLineLength(length);
        assertEquals(length, chart.getStyler().getLegendSeriesLineLength());
    }

    @Test
    public void testLargeAxisTickPadding() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int padding = 10;
        chart.getStyler().setAxisTickPadding(padding);
        assertEquals(padding, chart.getStyler().getAxisTickPadding());
    }

    @Test
    public void testSmallAxisTickPadding() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int padding = 2;
        chart.getStyler().setAxisTickPadding(padding);
        assertEquals(padding, chart.getStyler().getAxisTickPadding());
    }

    @Test
    public void testMinPlotContentSize() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        double size = 0.5;
        chart.getStyler().setPlotContentSize(size);
        assertEquals(size, chart.getStyler().getPlotContentSize(), 0.001);
    }

    @Test
    public void testMaxPlotContentSize() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        double size = 0.99;
        chart.getStyler().setPlotContentSize(size);
        assertEquals(size, chart.getStyler().getPlotContentSize(), 0.001);
    }

    @Test
    public void testDefaultSeriesRenderStyleScatter() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        XYSeries.XYSeriesRenderStyle style = XYSeries.XYSeriesRenderStyle.Scatter;
        chart.getStyler().setDefaultSeriesRenderStyle(style);
        assertEquals(style, chart.getStyler().getDefaultSeriesRenderStyle());
    }

    @Test
    public void testDefaultSeriesRenderStyleArea() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        XYSeries.XYSeriesRenderStyle style = XYSeries.XYSeriesRenderStyle.Area;
        chart.getStyler().setDefaultSeriesRenderStyle(style);
        assertEquals(style, chart.getStyler().getDefaultSeriesRenderStyle());
    }

    @Test
    public void testMarkerSquare() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        XYSeries series = chart.addSeries("test", new double[]{1,2}, new double[]{1,2});
        series.setMarker(SeriesMarkers.SQUARE);
        assertEquals(SeriesMarkers.SQUARE, series.getMarker());
    }

    @Test
    public void testMarkerDiamond() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        XYSeries series = chart.addSeries("test", new double[]{1,2}, new double[]{1,2});
        series.setMarker(SeriesMarkers.DIAMOND);
        assertEquals(SeriesMarkers.DIAMOND, series.getMarker());
    }

    @Test
    public void testSeriesColorsCustomPalette() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color[] colors = new Color[]{
            new Color(255, 0, 0),
            new Color(0, 255, 0),
            new Color(0, 0, 255),
            new Color(255, 255, 0),
            new Color(255, 0, 255)
        };
        chart.getStyler().setSeriesColors(colors);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testDecimalPatternPrecise() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        String pattern = "#.####";
        chart.getStyler().setDecimalPattern(pattern);
        assertEquals(pattern, chart.getStyler().getDecimalPattern());
    }

    @Test
    public void testDecimalPatternSimple() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        String pattern = "#";
        chart.getStyler().setDecimalPattern(pattern);
        assertEquals(pattern, chart.getStyler().getDecimalPattern());
    }

    @Test
    public void testLocaleFrench() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        chart.getStyler().setLocale(java.util.Locale.FRANCE);
        assertEquals(java.util.Locale.FRANCE, chart.getStyler().getLocale());
    }

    @Test
    public void testLocaleGerman() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        chart.getStyler().setLocale(java.util.Locale.GERMANY);
        assertEquals(java.util.Locale.GERMANY, chart.getStyler().getLocale());
    }

    @Test
    public void testDatePatternWithTime() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        chart.getStyler().setDatePattern(pattern);
        assertEquals(pattern, chart.getStyler().getDatePattern());
    }

    @Test
    public void testDatePatternShort() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        String pattern = "MM/dd/yy";
        chart.getStyler().setDatePattern(pattern);
        assertEquals(pattern, chart.getStyler().getDatePattern());
    }

    @Test
    public void testLegendPositionOutsideS() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Styler.LegendPosition position = Styler.LegendPosition.OutsideS;
        chart.getStyler().setLegendPosition(position);
        assertEquals(position, chart.getStyler().getLegendPosition());
    }

    @Test
    public void testLegendPositionInsideNW() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Styler.LegendPosition position = Styler.LegendPosition.InsideNW;
        chart.getStyler().setLegendPosition(position);
        assertEquals(position, chart.getStyler().getLegendPosition());
    }

    @Test
    public void testLegendLayoutVertical() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Styler.LegendLayout layout = Styler.LegendLayout.Vertical;
        chart.getStyler().setLegendLayout(layout);
        assertEquals(layout, chart.getStyler().getLegendLayout());
    }

    @Test
    public void testToolTipBackgroundColorDark() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(50, 50, 50);
        chart.getStyler().setToolTipBackgroundColor(color);
        assertEquals(color, chart.getStyler().getToolTipBackgroundColor());
    }

    @Test
    public void testToolTipBorderColorLight() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(240, 240, 240);
        chart.getStyler().setToolTipBorderColor(color);
        assertEquals(color, chart.getStyler().getToolTipBorderColor());
    }

    @Test
    public void testToolTipHighlightColorWithAlpha() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(255, 255, 0, 128);
        chart.getStyler().setToolTipHighlightColor(color);
        assertEquals(color, chart.getStyler().getToolTipHighlightColor());
    }

    @Test
    public void testDefaultSeriesRenderStyleStep() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        XYSeries.XYSeriesRenderStyle style = XYSeries.XYSeriesRenderStyle.Step;
        chart.getStyler().setDefaultSeriesRenderStyle(style);
        assertEquals(style, chart.getStyler().getDefaultSeriesRenderStyle());
    }

    @Test
    public void testMarkerTriangleUp() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        XYSeries series = chart.addSeries("test", new double[]{1,2}, new double[]{1,2});
        series.setMarker(SeriesMarkers.TRIANGLE_UP);
        assertEquals(SeriesMarkers.TRIANGLE_UP, series.getMarker());
    }

    @Test
    public void testMarkerTriangleDown() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        XYSeries series = chart.addSeries("test", new double[]{1,2}, new double[]{1,2});
        series.setMarker(SeriesMarkers.TRIANGLE_DOWN);
        assertEquals(SeriesMarkers.TRIANGLE_DOWN, series.getMarker());
    }

    @Test
    public void testLegendPositionInsideSE() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Styler.LegendPosition position = Styler.LegendPosition.InsideSE;
        chart.getStyler().setLegendPosition(position);
        assertEquals(position, chart.getStyler().getLegendPosition());
    }

    @Test
    public void testMarkerCross() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        XYSeries series = chart.addSeries("test", new double[]{1,2}, new double[]{1,2});
        series.setMarker(SeriesMarkers.CROSS);
        assertEquals(SeriesMarkers.CROSS, series.getMarker());
    }

    @Test
    public void testDatePatternWithMillis() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
        chart.getStyler().setDatePattern(pattern);
        assertEquals(pattern, chart.getStyler().getDatePattern());
    }

    @Test
    public void testLocaleJapanese() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        chart.getStyler().setLocale(java.util.Locale.JAPAN);
        assertEquals(java.util.Locale.JAPAN, chart.getStyler().getLocale());
    }

    @Test
    public void testDecimalPatternWithGrouping() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        String pattern = "#,###.##";
        chart.getStyler().setDecimalPattern(pattern);
        assertEquals(pattern, chart.getStyler().getDecimalPattern());
    }

    @Test
    public void testSeriesColorsGrayscale() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color[] colors = new Color[]{
            new Color(50, 50, 50),
            new Color(100, 100, 100),
            new Color(150, 150, 150),
            new Color(200, 200, 200)
        };
        chart.getStyler().setSeriesColors(colors);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testMarkerNone() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        XYSeries series = chart.addSeries("test", new double[]{1,2}, new double[]{1,2});
        series.setMarker(SeriesMarkers.NONE);
        assertEquals(SeriesMarkers.NONE, series.getMarker());
    }

    @Test
    public void testToolTipBackgroundColorTransparent() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(230, 230, 230, 128);
        chart.getStyler().setToolTipBackgroundColor(color);
        assertEquals(color, chart.getStyler().getToolTipBackgroundColor());
    }

    @Test
    public void testToolTipBorderColorTransparent() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(200, 200, 200, 128);
        chart.getStyler().setToolTipBorderColor(color);
        assertEquals(color, chart.getStyler().getToolTipBorderColor());
    }

    @Test
    public void testToolTipHighlightColorRed() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(255, 0, 0, 128);
        chart.getStyler().setToolTipHighlightColor(color);
        assertEquals(color, chart.getStyler().getToolTipHighlightColor());
    }

    @Test
    public void testChartBackgroundColorRGBA() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(128, 128, 128, 64);
        chart.getStyler().setChartBackgroundColor(color);
        assertEquals(color, chart.getStyler().getChartBackgroundColor());
    }

    @Test
    public void testChartFontColorBlue() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(0, 0, 255);
        chart.getStyler().setChartFontColor(color);
        assertEquals(color, chart.getStyler().getChartFontColor());
    }

    @Test
    public void testPlotBackgroundColorSemiTransparent() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(200, 200, 200, 180);
        chart.getStyler().setPlotBackgroundColor(color);
        assertEquals(color, chart.getStyler().getPlotBackgroundColor());
    }

    @Test
    public void testExtraLargeMarkerSize() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int size = 30;
        chart.getStyler().setMarkerSize(size);
        assertEquals(size, chart.getStyler().getMarkerSize());
    }

    @Test
    public void testExtraSmallMarkerSize() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int size = 2;
        chart.getStyler().setMarkerSize(size);
        assertEquals(size, chart.getStyler().getMarkerSize());
    }

    @Test
    public void testExtraLongAxisTickMarkLength() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int length = 24;
        chart.getStyler().setAxisTickMarkLength(length);
        assertEquals(length, chart.getStyler().getAxisTickMarkLength());
    }

    @Test
    public void testMinimalAxisTickMarkLength() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int length = 1;
        chart.getStyler().setAxisTickMarkLength(length);
        assertEquals(length, chart.getStyler().getAxisTickMarkLength());
    }

    @Test
    public void testExtraLargePlotMargin() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int margin = 50;
        chart.getStyler().setPlotMargin(margin);
        assertEquals(margin, chart.getStyler().getPlotMargin());
    }

    @Test
    public void testMinimalPlotMargin() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int margin = 0;
        chart.getStyler().setPlotMargin(margin);
        assertEquals(margin, chart.getStyler().getPlotMargin());
    }

    @Test
    public void testExtraLongLegendSeriesLineLength() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int length = 50;
        chart.getStyler().setLegendSeriesLineLength(length);
        assertEquals(length, chart.getStyler().getLegendSeriesLineLength());
    }

    @Test
    public void testMinimalLegendSeriesLineLength() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int length = 1;
        chart.getStyler().setLegendSeriesLineLength(length);
        assertEquals(length, chart.getStyler().getLegendSeriesLineLength());
    }

    @Test
    public void testExtraLargeAxisTickPadding() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int padding = 20;
        chart.getStyler().setAxisTickPadding(padding);
        assertEquals(padding, chart.getStyler().getAxisTickPadding());
    }

    @Test
    public void testMinimalAxisTickPadding() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        int padding = 0;
        chart.getStyler().setAxisTickPadding(padding);
        assertEquals(padding, chart.getStyler().getAxisTickPadding());
    }

    @Test
    public void testMinimalPlotContentSize() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        double size = 0.1;
        chart.getStyler().setPlotContentSize(size);
        assertEquals(size, chart.getStyler().getPlotContentSize(), 0.001);
    }

    @Test
    public void testSeriesColorsRainbow() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color[] colors = new Color[]{
            new Color(255, 0, 0),    // Red
            new Color(255, 127, 0),  // Orange
            new Color(255, 255, 0),  // Yellow
            new Color(0, 255, 0),    // Green
            new Color(0, 0, 255),    // Blue
            new Color(75, 0, 130),   // Indigo
            new Color(148, 0, 211)   // Violet
        };
        chart.getStyler().setSeriesColors(colors);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testDecimalPatternScientific() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        String pattern = "0.###E0";
        chart.getStyler().setDecimalPattern(pattern);
        assertEquals(pattern, chart.getStyler().getDecimalPattern());
    }

    @Test
    public void testLocaleChina() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        chart.getStyler().setLocale(java.util.Locale.CHINA);
        assertEquals(java.util.Locale.CHINA, chart.getStyler().getLocale());
    }

    @Test
    public void testDatePatternCustom() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        String pattern = "EEEE, MMMM dd, yyyy HH:mm:ss z";
        chart.getStyler().setDatePattern(pattern);
        assertEquals(pattern, chart.getStyler().getDatePattern());
    }

    @Test
    public void testLegendPositionInsideN() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Styler.LegendPosition position = Styler.LegendPosition.InsideN;
        chart.getStyler().setLegendPosition(position);
        assertEquals(position, chart.getStyler().getLegendPosition());
    }

    @Test
    public void testMarkerPlus() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        XYSeries series = chart.addSeries("test", new double[]{1,2}, new double[]{1,2});
        series.setMarker(SeriesMarkers.PLUS);
        assertEquals(SeriesMarkers.PLUS, series.getMarker());
    }

    @Test
    public void testToolTipBackgroundColorYellow() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(255, 255, 0, 180);
        chart.getStyler().setToolTipBackgroundColor(color);
        assertEquals(color, chart.getStyler().getToolTipBackgroundColor());
    }

    @Test
    public void testToolTipBorderColorBlue() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(0, 0, 255, 180);
        chart.getStyler().setToolTipBorderColor(color);
        assertEquals(color, chart.getStyler().getToolTipBorderColor());
    }

    @Test
    public void testToolTipHighlightColorGreen() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color color = new Color(0, 255, 0, 180);
        chart.getStyler().setToolTipHighlightColor(color);
        assertEquals(color, chart.getStyler().getToolTipHighlightColor());
    }

    @Test
    public void testSeriesColorsMonochromatic() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Color[] colors = new Color[]{
            new Color(0, 0, 255),      // Full blue
            new Color(51, 51, 255),    // Light blue 1
            new Color(102, 102, 255),  // Light blue 2
            new Color(153, 153, 255),  // Light blue 3
            new Color(204, 204, 255)   // Light blue 4
        };
        chart.getStyler().setSeriesColors(colors);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testMarkerOval() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        XYSeries series = chart.addSeries("test", new double[]{1,2}, new double[]{1,2});
        series.setMarker(SeriesMarkers.OVAL);
        assertEquals(SeriesMarkers.OVAL, series.getMarker());
    }

    @Test
    public void testLegendPositionInsideS() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        Styler.LegendPosition position = Styler.LegendPosition.InsideS;
        chart.getStyler().setLegendPosition(position);
        assertEquals(position, chart.getStyler().getLegendPosition());
    }
}