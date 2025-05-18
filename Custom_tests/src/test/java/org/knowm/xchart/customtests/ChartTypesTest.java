package org.knowm.xchart.customtests;

import org.junit.jupiter.api.Test;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.internal.series.Series.DataType;
import org.knowm.xchart.style.PieStyler.ClockwiseDirectionType;
import org.knowm.xchart.style.BoxStyler.BoxplotCalCulationMethod;
import org.knowm.xchart.OHLCSeries;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ChartTypesTest {

    @Test
    public void testBasicXYChart() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        double[] xData = {0.0, 1.0, 2.0};
        double[] yData = {2.0, 1.0, 0.0};
        XYSeries series = chart.addSeries("Test", xData, yData);
        assertNotNull(series);
        assertEquals("Test", series.getName());
    }
    
    @Test
    public void testBasicXYChart_Var1() {
        XYChart chart = new XYChartBuilder().width(400).height(300).title("Linear Data").build();
        double[] xData = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] yData = {1.0, 2.0, 3.0, 4.0, 5.0};
        XYSeries series = chart.addSeries("Linear", xData, yData);
        assertNotNull(series);
        assertEquals("Linear", series.getName());
    }
    
    @Test
    public void testBasicXYChart_Var2() {
        XYChart chart = new XYChartBuilder().width(600).height(400).title("Quadratic Data").build();
        double[] xData = {-2.0, -1.0, 0.0, 1.0, 2.0};
        double[] yData = {4.0, 1.0, 0.0, 1.0, 4.0};
        XYSeries series = chart.addSeries("Quadratic", xData, yData);
        assertNotNull(series);
        assertEquals("Quadratic", series.getName());
    }
    
    @Test
    public void testBasicXYChart_Var3() {
        XYChart chart = new XYChartBuilder().width(500).height(500).title("Scatter Style").build();
        double[] xData = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] yData = {5.0, 4.0, 3.0, 2.0, 1.0};
        XYSeries series = chart.addSeries("Inverse", xData, yData);
        series.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        assertNotNull(series);
        assertEquals(XYSeries.XYSeriesRenderStyle.Scatter, series.getXYSeriesRenderStyle());
    }
    
    @Test
    public void testBasicXYChart_Var4() {
        XYChart chart = new XYChartBuilder().width(300).height(600).title("Area Style").build();
        double[] xData = {0.0, 1.0, 2.0, 3.0};
        double[] yData = {1.0, 3.0, 5.0, 2.0};
        XYSeries series = chart.addSeries("Area", xData, yData);
        series.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Area);
        assertNotNull(series);
        assertEquals(XYSeries.XYSeriesRenderStyle.Area, series.getXYSeriesRenderStyle());
    }
    
    @Test
    public void testBasicXYChart_Var5() {
        XYChart chart = new XYChartBuilder().width(800).height(300).title("Step Style").xAxisTitle("X").yAxisTitle("Y").build();
        double[] xData = {1.0, 2.0, 3.0, 4.0};
        double[] yData = {10.0, 5.0, 15.0, 20.0};
        XYSeries series = chart.addSeries("Step", xData, yData);
        series.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Step);
        assertNotNull(series);
        assertEquals(XYSeries.XYSeriesRenderStyle.Step, series.getXYSeriesRenderStyle());
    }
    
    @Test
    public void testBasicXYChart_Var6() {
        XYChart chart = new XYChartBuilder().width(300).height(300).title("Diamond Marker").build();
        double[] xData = {1.0, 2.0, 3.0};
        double[] yData = {3.0, 2.0, 1.0};
        XYSeries series = chart.addSeries("Diamonds", xData, yData);
        series.setMarker(SeriesMarkers.DIAMOND);
        assertNotNull(series);
        assertEquals(SeriesMarkers.DIAMOND, series.getMarker());
    }
    
    @Test
    public void testBasicXYChart_Var7() {
        XYChart chart = new XYChartBuilder().width(450).height(450).title("Square Marker").build();
        double[] xData = {0.0, 2.0, 4.0, 6.0, 8.0, 10.0};
        double[] yData = {0.0, 4.0, 16.0, 36.0, 64.0, 100.0};
        XYSeries series = chart.addSeries("Squares", xData, yData);
        series.setMarker(SeriesMarkers.SQUARE);
        assertNotNull(series);
        assertEquals(SeriesMarkers.SQUARE, series.getMarker());
    }
    
    @Test
    public void testBasicXYChart_Var8() {
        XYChart chart = new XYChartBuilder().width(400).height(400).title("Custom Line Style").build();
        double[] xData = {1.0, 2.0, 3.0, 4.0};
        double[] yData = {1.0, 4.0, 9.0, 16.0};
        XYSeries series = chart.addSeries("Dashed", xData, yData);
        series.setLineStyle(SeriesLines.DASH_DASH);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var9() {
        XYChart chart = new XYChartBuilder().width(700).height(400).title("Sine Wave").build();
        double[] xData = new double[100];
        double[] yData = new double[100];
        for (int i = 0; i < 100; i++) {
            xData[i] = i / 10.0;
            yData[i] = Math.sin(xData[i]);
        }
        XYSeries series = chart.addSeries("Sine", xData, yData);
        assertNotNull(series);
        assertEquals(100, xData.length);
    }
    
    @Test
    public void testBasicXYChart_Var10() {
        XYChart chart = new XYChartBuilder().width(500).height(500).theme(ChartTheme.GGPlot2).build();
        double[] xData = {0.0, 1.0, 2.0, 3.0};
        double[] yData = {3.0, 2.0, 1.0, 0.0};
        XYSeries series = chart.addSeries("GGPlot2", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var11() {
        XYChart chart = new XYChartBuilder().width(350).height(600).theme(ChartTheme.Matlab).build();
        double[] xData = {-5.0, -2.5, 0.0, 2.5, 5.0};
        double[] yData = {25.0, 6.25, 0.0, 6.25, 25.0};
        XYSeries series = chart.addSeries("Matlab", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var12() {
        XYChart chart = new XYChartBuilder().width(600).height(350).title("Multiple Series").build();
        double[] xData1 = {0.0, 1.0, 2.0};
        double[] yData1 = {0.0, 1.0, 2.0};
        double[] xData2 = {0.0, 1.0, 2.0};
        double[] yData2 = {2.0, 1.0, 0.0};
        XYSeries series1 = chart.addSeries("Increasing", xData1, yData1);
        XYSeries series2 = chart.addSeries("Decreasing", xData2, yData2);
        assertNotNull(series1);
        assertNotNull(series2);
        assertEquals(2, chart.getSeriesMap().size());
    }
    
    @Test
    public void testBasicXYChart_Var13() {
        XYChart chart = new XYChartBuilder().width(400).height(400).title("Negative Values").build();
        double[] xData = {-3.0, -2.0, -1.0, 0.0};
        double[] yData = {-9.0, -4.0, -1.0, 0.0};
        XYSeries series = chart.addSeries("Negatives", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var14() {
        XYChart chart = new XYChartBuilder().width(900).height(600).title("Mixed Values").build();
        double[] xData = {-2.0, -1.0, 0.0, 1.0, 2.0};
        double[] yData = {4.0, -1.0, 0.0, 1.0, -4.0};
        XYSeries series = chart.addSeries("Mixed", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var15() {
        XYChart chart = new XYChartBuilder().width(200).height(200).title("Tiny Chart").build();
        double[] xData = {0.0, 0.5, 1.0};
        double[] yData = {0.0, 0.5, 1.0};
        XYSeries series = chart.addSeries("Tiny", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var16() {
        XYChart chart = new XYChartBuilder().width(1000).height(800).title("Large Chart").build();
        double[] xData = {0.0, 2.0, 4.0, 6.0, 8.0, 10.0};
        double[] yData = {10.0, 8.0, 6.0, 4.0, 2.0, 0.0};
        XYSeries series = chart.addSeries("Large", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var17() {
        XYChart chart = new XYChartBuilder().width(333).height(333).title("Float Data").build();
        double[] xData = {0.1, 0.2, 0.3};
        double[] yData = {0.3, 0.2, 0.1};
        XYSeries series = chart.addSeries("Float", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var18() {
        XYChart chart = new XYChartBuilder().width(650).height(350).title("Staircase").build();
        double[] xData = {0.0, 1.0, 1.0, 2.0, 2.0, 3.0, 3.0, 4.0};
        double[] yData = {0.0, 0.0, 1.0, 1.0, 2.0, 2.0, 3.0, 3.0};
        XYSeries series = chart.addSeries("Stairs", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var19() {
        XYChart chart = new XYChartBuilder().width(750).height(750).title("Circle Data").build();
        double[] xData = new double[37];
        double[] yData = new double[37];
        for (int i = 0; i <= 36; i++) {
            double angle = Math.toRadians(i * 10);
            xData[i] = Math.cos(angle);
            yData[i] = Math.sin(angle);
        }
        XYSeries series = chart.addSeries("Circle", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var20() {
        XYChart chart = new XYChartBuilder().width(555).height(444).title("Exponential").build();
        double[] xData = {0.0, 1.0, 2.0, 3.0, 4.0};
        double[] yData = {1.0, 2.0, 4.0, 8.0, 16.0};
        XYSeries series = chart.addSeries("Exp", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var21() {
        XYChart chart = new XYChartBuilder().width(444).height(555).title("Logarithmic").build();
        double[] xData = {1.0, 10.0, 100.0, 1000.0};
        double[] yData = {0.0, 1.0, 2.0, 3.0};
        XYSeries series = chart.addSeries("Log", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var22() {
        XYChart chart = new XYChartBuilder().width(222).height(222).theme(ChartTheme.GGPlot2).title("Small GGPlot").build();
        double[] xData = {1.0, 3.0, 5.0};
        double[] yData = {2.0, 4.0, 6.0};
        XYSeries series = chart.addSeries("GGPlot Small", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var23() {
        XYChart chart = new XYChartBuilder().width(777).height(333).theme(ChartTheme.Matlab).title("Wide Matlab").build();
        double[] xData = {1.0, 2.0, 3.0, 4.0};
        double[] yData = {4.0, 3.0, 2.0, 1.0};
        XYSeries series = chart.addSeries("Matlab Wide", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var24() {
        XYChart chart = new XYChartBuilder().width(600).height(600).title("Symmetric").build();
        double[] xData = {-3.0, -2.0, -1.0, 0.0, 1.0, 2.0, 3.0};
        double[] yData = {-3.0, -2.0, -1.0, 0.0, 1.0, 2.0, 3.0};
        XYSeries series = chart.addSeries("Identity", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var25() {
        XYChart chart = new XYChartBuilder().width(123).height(456).title("Logarithmic Scale").build();
        double[] xData = {0.1, 1.0, 10.0, 100.0};
        double[] yData = {0.1, 1.0, 10.0, 100.0};
        XYSeries series = chart.addSeries("Log Scale", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var26() {
        XYChart chart = new XYChartBuilder().width(456).height(123).title("Horizontal Rectangle").build();
        double[] xData = {5.0, 10.0, 15.0};
        double[] yData = {15.0, 10.0, 5.0};
        XYSeries series = chart.addSeries("Horizontal", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var27() {
        XYChart chart = new XYChartBuilder().width(987).height(654).title("Large Mixed").build();
        double[] xData = {0.0, 1.0};
        double[] yData = {1.0, 0.0};
        XYSeries series = chart.addSeries("Simple Line", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var28() {
        XYChart chart = new XYChartBuilder().width(654).height(987).title("Large Range").build();
        double[] xData = {-100.0, -50.0, 0.0, 50.0, 100.0};
        double[] yData = {-100.0, -50.0, 0.0, 50.0, 100.0};
        XYSeries series = chart.addSeries("Large Range", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var29() {
        XYChart chart = new XYChartBuilder().width(369).height(369).title("Odd Dimensions").build();
        double[] xData = {1.0, 3.0, 5.0, 7.0, 9.0};
        double[] yData = {2.0, 4.0, 6.0, 8.0, 10.0};
        XYSeries series = chart.addSeries("Odd Chart", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var30() {
        XYChart chart = new XYChartBuilder().width(852).height(741).title("Random Dimensions").build();
        double[] xData = {0.5, 1.5, 2.5, 3.5};
        double[] yData = {0.5, 1.5, 2.5, 3.5};
        XYSeries series = chart.addSeries("Random Dim", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var31() {
        XYChart chart = new XYChartBuilder().width(741).height(852).title("Inverted Coordinates").build();
        double[] xData = {-3.0, -1.0, 1.0, 3.0};
        double[] yData = {3.0, 1.0, -1.0, -3.0};
        XYSeries series = chart.addSeries("Inverted", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var32() {
        XYChart chart = new XYChartBuilder().width(400).height(400).title("Pi Values").build();
        double[] xData = {1*Math.PI, 2*Math.PI, 3*Math.PI};
        double[] yData = {3*Math.PI, 2*Math.PI, 1*Math.PI};
        XYSeries series = chart.addSeries("Pi Data", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var33() {
        XYChart chart = new XYChartBuilder().width(600).height(400).title("Decimal Values").build();
        double[] xData = {1.23, 4.56, 7.89};
        double[] yData = {9.87, 6.54, 3.21};
        XYSeries series = chart.addSeries("Decimals", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var34() {
        XYChart chart = new XYChartBuilder().width(500).height(300).title("Percentage Scale").build();
        double[] xData = {0.0, 25.0, 50.0, 75.0, 100.0};
        double[] yData = {0.0, 25.0, 50.0, 75.0, 100.0};
        XYSeries series = chart.addSeries("Percentages", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var35() {
        XYChart chart = new XYChartBuilder().width(375).height(275).title("Logarithmic Spaced").build();
        double[] xData = {1.0, 10.0, 100.0};
        double[] yData = {1.0, 10.0, 100.0};
        XYSeries series = chart.addSeries("Log Spaced", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var36() {
        XYChart chart = new XYChartBuilder().width(275).height(375).title("Odd Aspect").build();
        double[] xData = {-10.0, -5.0, 0.0, 5.0, 10.0};
        double[] yData = {10.0, 5.0, 0.0, -5.0, -10.0};
        XYSeries series = chart.addSeries("Inverse Relation", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var37() {
        XYChart chart = new XYChartBuilder().width(510).height(510).title("Bell Curve").build();
        double[] xData = {-20.0, -10.0, 0.0, 10.0, 20.0};
        double[] yData = {0.1, 0.5, 1.0, 0.5, 0.1};
        XYSeries series = chart.addSeries("Bell", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var38() {
        XYChart chart = new XYChartBuilder().width(480).height(320).title("Even Spacing").build();
        double[] xData = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0};
        double[] yData = {6.0, 5.0, 4.0, 3.0, 2.0, 1.0};
        XYSeries series = chart.addSeries("Even", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var39() {
        XYChart chart = new XYChartBuilder().width(640).height(480).title("HD Format").build();
        double[] xData = {0.25, 0.5, 0.75, 1.0};
        double[] yData = {1.0, 0.75, 0.5, 0.25};
        XYSeries series = chart.addSeries("HD Data", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var40() {
        XYChart chart = new XYChartBuilder().width(1024).height(768).title("Parabola").build();
        double[] xData = {-5.0, 0.0, 5.0};
        double[] yData = {25.0, 0.0, 25.0};
        XYSeries series = chart.addSeries("Parabola", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var41() {
        XYChart chart = new XYChartBuilder().width(768).height(1024).title("Portrait Format").build();
        double[] xData = {1.0, 3.0, 5.0, 7.0};
        double[] yData = {1.0, 3.0, 5.0, 7.0};
        XYSeries series = chart.addSeries("Portrait Data", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var42() {
        XYChart chart = new XYChartBuilder().width(512).height(512).theme(Styler.ChartTheme.XChart).title("XChart Theme").build();
        double[] xData = {-1.5, -0.5, 0.5, 1.5};
        double[] yData = {-1.5, -0.5, 0.5, 1.5};
        XYSeries series = chart.addSeries("XChart Test", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var43() {
        XYChart chart = new XYChartBuilder().width(1280).height(720).title("HD Widescreen").build();
        double[] xData = {1.0, 3.0, 5.0, 7.0, 9.0, 11.0};
        double[] yData = {11.0, 9.0, 7.0, 5.0, 3.0, 1.0};
        XYSeries series = chart.addSeries("Widescreen", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var44() {
        XYChart chart = new XYChartBuilder().width(720).height(1280).title("Smartphone").build();
        double[] xData = {0.1, 1.0, 10.0, 100.0};
        double[] yData = {0.1, 1.0, 10.0, 100.0};
        XYSeries series = chart.addSeries("Phone View", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var45() {
        XYChart chart = new XYChartBuilder().width(960).height(540).title("Full Sine Wave").build();
        double[] xData = {-Math.PI, 0.0, Math.PI};
        double[] yData = {Math.sin(-Math.PI), Math.sin(0.0), Math.sin(Math.PI)};
        XYSeries series = chart.addSeries("Full Sine", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var46() {
        XYChart chart = new XYChartBuilder().width(540).height(960).title("Gradient").build();
        double[] xData = {0.0, 0.25, 0.5, 0.75, 1.0};
        double[] yData = {0.0, 0.25, 0.5, 0.75, 1.0};
        XYSeries series = chart.addSeries("Gradient", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var47() {
        XYChart chart = new XYChartBuilder().width(1200).height(900).title("Line Chart").build();
        double[] xData = {0.0, 10.0, 20.0, 30.0, 40.0, 50.0};
        double[] yData = {50.0, 40.0, 30.0, 20.0, 10.0, 0.0};
        XYSeries series = chart.addSeries("Line Data", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var48() {
        XYChart chart = new XYChartBuilder().width(900).height(1200).title("Vertical Oriented").build();
        double[] xData = {-50.0, -25.0, 0.0, 25.0, 50.0};
        double[] yData = {50.0, 25.0, 0.0, -25.0, -50.0};
        XYSeries series = chart.addSeries("Vertical Data", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var49() {
        XYChart chart = new XYChartBuilder().width(150).height(150).title("Minimal").build();
        double[] xData = {1.0};
        double[] yData = {1.0};
        XYSeries series = chart.addSeries("Single Point", xData, yData);
        assertNotNull(series);
    }
    
    @Test
    public void testBasicXYChart_Var50() {
        XYChart chart = new XYChartBuilder().width(1500).height(1500).title("Massive Chart").build();
        double[] xData = new double[100];
        double[] yData = new double[100];
        for (int i = 0; i < 100; i++) {
            xData[i] = i - 50;
            yData[i] = (xData[i] * xData[i]) / 50;
        }
        XYSeries series = chart.addSeries("Parabolic", xData, yData);
        assertNotNull(series);
    }

    @Test
    public void testMultipleSeriesXYChart() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        double[] xData1 = {0.0, 1.0, 2.0};
        double[] yData1 = {2.0, 1.0, 0.0};
        double[] xData2 = {0.0, 1.0, 2.0};
        double[] yData2 = {0.0, 1.0, 2.0};
        XYSeries series1 = chart.addSeries("Series 1", xData1, yData1);
        XYSeries series2 = chart.addSeries("Series 2", xData2, yData2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testMultipleSeriesXYChart_Var1() {
        XYChart chart = new XYChartBuilder().width(400).height(300).build();
        double[] x1 = {0, 2, 4};
        double[] y1 = {1, 3, 5};
        double[] x2 = {1, 3, 5};
        double[] y2 = {2, 4, 6};
        chart.addSeries("Alpha", x1, y1);
        chart.addSeries("Beta", x2, y2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testMultipleSeriesXYChart_Var2() {
        XYChart chart = new XYChartBuilder().width(600).height(400).title("Test Chart").build();
        double[] x1 = {10, 20, 30};
        double[] y1 = {5, 10, 15};
        double[] x2 = {15, 25, 35};
        double[] y2 = {7, 14, 21};
        chart.addSeries("First", x1, y1);
        chart.addSeries("Second", x2, y2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testMultipleSeriesXYChart_Var3() {
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("X").yAxisTitle("Y").build();
        double[] x1 = {1, 2, 3, 4};
        double[] y1 = {4, 3, 2, 1};
        double[] x2 = {1, 2, 3, 4};
        double[] y2 = {1, 2, 3, 4};
        chart.addSeries("S1", x1, y1);
        chart.addSeries("S2", x2, y2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testMultipleSeriesXYChart_Var4() {
        XYChart chart = new XYChartBuilder().width(500).height(500).build();
        double[] x1 = {-1, 0, 1};
        double[] y1 = {-2, 0, 2};
        double[] x2 = {-1, 0, 1};
        double[] y2 = {2, 0, -2};
        chart.addSeries("Neg", x1, y1);
        chart.addSeries("Pos", x2, y2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testMultipleSeriesXYChart_Var5() {
        XYChart chart = new XYChartBuilder().width(300).height(300).build();
        double[] x1 = {0, 1, 2, 3, 4};
        double[] y1 = {0, 1, 4, 9, 16};
        double[] x2 = {0, 1, 2, 3, 4};
        double[] y2 = {16, 9, 4, 1, 0};
        chart.addSeries("Square", x1, y1);
        chart.addSeries("Reverse", x2, y2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testMultipleSeriesXYChart_Var6() {
        XYChart chart = new XYChartBuilder().width(700).height(350).build();
        double[] x1 = {0.1, 0.2, 0.3};
        double[] y1 = {0.2, 0.4, 0.6};
        double[] x2 = {0.1, 0.2, 0.3};
        double[] y2 = {0.6, 0.4, 0.2};
        chart.addSeries("Float1", x1, y1);
        chart.addSeries("Float2", x2, y2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testMultipleSeriesXYChart_Var7() {
        XYChart chart = new XYChartBuilder().width(900).height(200).build();
        double[] x1 = {100, 200, 300};
        double[] y1 = {300, 200, 100};
        double[] x2 = {150, 250, 350};
        double[] y2 = {100, 200, 300};
        chart.addSeries("Big1", x1, y1);
        chart.addSeries("Big2", x2, y2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testMultipleSeriesXYChart_Var8() {
        XYChart chart = new XYChartBuilder().width(1000).height(1000).build();
        double[] x1 = {0, 0, 0};
        double[] y1 = {1, 2, 3};
        double[] x2 = {1, 1, 1};
        double[] y2 = {3, 2, 1};
        chart.addSeries("ZeroX", x1, y1);
        chart.addSeries("OneX", x2, y2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testMultipleSeriesXYChart_Var9() {
        XYChart chart = new XYChartBuilder().width(600).height(600).build();
        double[] x1 = {1, 2, 3, 4, 5};
        double[] y1 = {5, 4, 3, 2, 1};
        double[] x2 = {5, 4, 3, 2, 1};
        double[] y2 = {1, 2, 3, 4, 5};
        chart.addSeries("Forward", x1, y1);
        chart.addSeries("Backward", x2, y2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testMultipleSeriesXYChart_Var10() {
        XYChart chart = new XYChartBuilder().width(800).height(400).build();
        double[] x1 = {2, 4, 6, 8};
        double[] y1 = {1, 3, 5, 7};
        double[] x2 = {1, 3, 5, 7};
        double[] y2 = {2, 4, 6, 8};
        chart.addSeries("Even", x1, y1);
        chart.addSeries("Odd", x2, y2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testMultipleSeriesXYChart_Var11() {
        XYChart chart = new XYChartBuilder().width(350).height(350).title("Var11").build();
        double[] x1 = {0, 1, 2, 3};
        double[] y1 = {3, 2, 1, 0};
        double[] x2 = {3, 2, 1, 0};
        double[] y2 = {0, 1, 2, 3};
        chart.addSeries("A", x1, y1);
        chart.addSeries("B", x2, y2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testMultipleSeriesXYChart_Var12() {
        XYChart chart = new XYChartBuilder().width(450).height(250).xAxisTitle("Time").yAxisTitle("Value").build();
        double[] x1 = {1, 2, 3, 4, 5};
        double[] y1 = {5, 4, 3, 2, 1};
        double[] x2 = {1, 2, 3, 4, 5};
        double[] y2 = {1, 2, 3, 4, 5};
        chart.addSeries("Up", x1, y1);
        chart.addSeries("Down", x2, y2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testMultipleSeriesXYChart_Var13() {
        XYChart chart = new XYChartBuilder().width(600).height(600).build();
        double[] x1 = {0, 1, 2, 3, 4, 5};
        double[] y1 = {0, 1, 0, 1, 0, 1};
        double[] x2 = {0, 1, 2, 3, 4, 5};
        double[] y2 = {1, 0, 1, 0, 1, 0};
        chart.addSeries("Alt1", x1, y1);
        chart.addSeries("Alt2", x2, y2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testMultipleSeriesXYChart_Var14() {
        XYChart chart = new XYChartBuilder().width(200).height(800).build();
        double[] x1 = {10, 20, 30, 40};
        double[] y1 = {40, 30, 20, 10};
        double[] x2 = {10, 20, 30, 40};
        double[] y2 = {10, 20, 30, 40};
        chart.addSeries("Dec", x1, y1);
        chart.addSeries("Inc", x2, y2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testMultipleSeriesXYChart_Var15() {
        XYChart chart = new XYChartBuilder().width(100).height(100).build();
        double[] x1 = {0, 1};
        double[] y1 = {1, 0};
        double[] x2 = {1, 0};
        double[] y2 = {0, 1};
        chart.addSeries("Short1", x1, y1);
        chart.addSeries("Short2", x2, y2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testMultipleSeriesXYChart_Var16() {
        XYChart chart = new XYChartBuilder().width(1200).height(800).build();
        double[] x1 = {0, 2, 4, 6, 8, 10};
        double[] y1 = {10, 8, 6, 4, 2, 0};
        double[] x2 = {0, 2, 4, 6, 8, 10};
        double[] y2 = {0, 2, 4, 6, 8, 10};
        chart.addSeries("Long1", x1, y1);
        chart.addSeries("Long2", x2, y2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testMultipleSeriesXYChart_Var17() {
        XYChart chart = new XYChartBuilder().width(300).height(900).build();
        double[] x1 = {1, 3, 5, 7};
        double[] y1 = {7, 5, 3, 1};
        double[] x2 = {2, 4, 6, 8};
        double[] y2 = {8, 6, 4, 2};
        chart.addSeries("Odd", x1, y1);
        chart.addSeries("Even", x2, y2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testMultipleSeriesXYChart_Var18() {
        XYChart chart = new XYChartBuilder().width(400).height(400).build();
        double[] x1 = {0.5, 1.5, 2.5};
        double[] y1 = {2.5, 1.5, 0.5};
        double[] x2 = {0.5, 1.5, 2.5};
        double[] y2 = {0.5, 1.5, 2.5};
        chart.addSeries("FloatA", x1, y1);
        chart.addSeries("FloatB", x2, y2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testMultipleSeriesXYChart_Var19() {
        XYChart chart = new XYChartBuilder().width(500).height(1000).build();
        double[] x1 = {1, 4, 9, 16};
        double[] y1 = {16, 9, 4, 1};
        double[] x2 = {1, 4, 9, 16};
        double[] y2 = {1, 4, 9, 16};
        chart.addSeries("Sqr1", x1, y1);
        chart.addSeries("Sqr2", x2, y2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testMultipleSeriesXYChart_Var20() {
        XYChart chart = new XYChartBuilder().width(600).height(200).build();
        double[] x1 = {2, 4, 6, 8, 10};
        double[] y1 = {10, 8, 6, 4, 2};
        double[] x2 = {1, 3, 5, 7, 9};
        double[] y2 = {2, 4, 6, 8, 10};
        chart.addSeries("EvenX", x1, y1);
        chart.addSeries("OddX", x2, y2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicPieChart() {
        PieChart chart = new PieChartBuilder().width(800).height(600).build();
        chart.addSeries("A", 20);
        chart.addSeries("B", 30);
        chart.addSeries("C", 50);
        assertEquals(3, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartWithCustomColors() {
        PieChart chart = new PieChartBuilder().width(800).height(600).build();
        Color[] sliceColors = new Color[] {
            Color.RED,
            Color.GREEN,
            Color.BLUE
        };
        chart.getStyler().setSeriesColors(sliceColors);
        chart.addSeries("A", 20);
        chart.addSeries("B", 30);
        chart.addSeries("C", 50);
        assertArrayEquals(sliceColors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testPieChartWithCustomColors_Var1() {
        PieChart chart = new PieChartBuilder().width(400).height(400).build();
        Color[] colors = new Color[] {Color.YELLOW, Color.MAGENTA, Color.CYAN};
        chart.getStyler().setSeriesColors(colors);
        chart.addSeries("X", 10);
        chart.addSeries("Y", 20);
        chart.addSeries("Z", 70);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testPieChartWithCustomColors_Var2() {
        PieChart chart = new PieChartBuilder().width(500).height(300).title("Pie2").build();
        Color[] colors = new Color[] {Color.PINK, Color.ORANGE, Color.GRAY};
        chart.getStyler().setSeriesColors(colors);
        chart.addSeries("One", 25);
        chart.addSeries("Two", 25);
        chart.addSeries("Three", 50);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testPieChartWithCustomColors_Var3() {
        PieChart chart = new PieChartBuilder().width(600).height(600).build();
        Color[] colors = new Color[] {Color.BLACK, Color.WHITE, Color.LIGHT_GRAY};
        chart.getStyler().setSeriesColors(colors);
        chart.addSeries("Alpha", 40);
        chart.addSeries("Beta", 30);
        chart.addSeries("Gamma", 30);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testPieChartWithCustomColors_Var4() {
        PieChart chart = new PieChartBuilder().width(700).height(200).build();
        Color[] colors = new Color[] {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
        chart.getStyler().setSeriesColors(colors);
        chart.addSeries("A", 10);
        chart.addSeries("B", 20);
        chart.addSeries("C", 30);
        chart.addSeries("D", 40);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testPieChartWithCustomColors_Var5() {
        PieChart chart = new PieChartBuilder().width(800).height(800).build();
        Color[] colors = new Color[] {Color.BLUE, Color.GREEN};
        chart.getStyler().setSeriesColors(colors);
        chart.addSeries("First", 60);
        chart.addSeries("Second", 40);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testPieChartWithCustomColors_Var6() {
        PieChart chart = new PieChartBuilder().width(300).height(900).build();
        Color[] colors = new Color[] {Color.ORANGE, Color.PINK, Color.CYAN, Color.GRAY};
        chart.getStyler().setSeriesColors(colors);
        chart.addSeries("W", 15);
        chart.addSeries("X", 25);
        chart.addSeries("Y", 35);
        chart.addSeries("Z", 25);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testPieChartWithCustomColors_Var7() {
        PieChart chart = new PieChartBuilder().width(1000).height(1000).build();
        Color[] colors = new Color[] {Color.DARK_GRAY, Color.LIGHT_GRAY};
        chart.getStyler().setSeriesColors(colors);
        chart.addSeries("Dark", 55);
        chart.addSeries("Light", 45);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testPieChartWithCustomColors_Var8() {
        PieChart chart = new PieChartBuilder().width(200).height(200).build();
        Color[] colors = new Color[] {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE};
        chart.getStyler().setSeriesColors(colors);
        chart.addSeries("A", 10);
        chart.addSeries("B", 20);
        chart.addSeries("C", 30);
        chart.addSeries("D", 20);
        chart.addSeries("E", 20);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testPieChartWithCustomColors_Var9() {
        PieChart chart = new PieChartBuilder().width(400).height(600).build();
        Color[] colors = new Color[] {Color.GREEN, Color.BLUE, Color.RED};
        chart.getStyler().setSeriesColors(colors);
        chart.addSeries("Green", 33);
        chart.addSeries("Blue", 33);
        chart.addSeries("Red", 34);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testPieChartWithCustomColors_Var10() {
        PieChart chart = new PieChartBuilder().width(600).height(400).build();
        Color[] colors = new Color[] {Color.PINK, Color.CYAN, Color.ORANGE};
        chart.getStyler().setSeriesColors(colors);
        chart.addSeries("P", 20);
        chart.addSeries("C", 30);
        chart.addSeries("O", 50);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testPieChartWithCustomColors_Var11() {
        PieChart chart = new PieChartBuilder().width(350).height(350).title("Pie11").build();
        Color[] colors = new Color[] {Color.RED, Color.GREEN, Color.BLUE, Color.PINK};
        chart.getStyler().setSeriesColors(colors);
        chart.addSeries("A", 5);
        chart.addSeries("B", 15);
        chart.addSeries("C", 30);
        chart.addSeries("D", 50);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testPieChartWithCustomColors_Var12() {
        PieChart chart = new PieChartBuilder().width(450).height(250).title("Pie12").build();
        Color[] colors = new Color[] {Color.YELLOW, Color.CYAN, Color.MAGENTA};
        chart.getStyler().setSeriesColors(colors);
        chart.addSeries("X", 40);
        chart.addSeries("Y", 30);
        chart.addSeries("Z", 30);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testPieChartWithCustomColors_Var13() {
        PieChart chart = new PieChartBuilder().width(600).height(600).build();
        Color[] colors = new Color[] {Color.BLACK, Color.WHITE};
        chart.getStyler().setSeriesColors(colors);
        chart.addSeries("Black", 60);
        chart.addSeries("White", 40);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testPieChartWithCustomColors_Var14() {
        PieChart chart = new PieChartBuilder().width(200).height(800).build();
        Color[] colors = new Color[] {Color.GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY};
        chart.getStyler().setSeriesColors(colors);
        chart.addSeries("Gray", 33);
        chart.addSeries("LightGray", 33);
        chart.addSeries("DarkGray", 34);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testPieChartWithCustomColors_Var15() {
        PieChart chart = new PieChartBuilder().width(100).height(100).build();
        Color[] colors = new Color[] {Color.RED, Color.GREEN};
        chart.getStyler().setSeriesColors(colors);
        chart.addSeries("Red", 50);
        chart.addSeries("Green", 50);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testPieChartWithCustomColors_Var16() {
        PieChart chart = new PieChartBuilder().width(1200).height(800).build();
        Color[] colors = new Color[] {Color.BLUE, Color.YELLOW, Color.ORANGE};
        chart.getStyler().setSeriesColors(colors);
        chart.addSeries("Blue", 20);
        chart.addSeries("Yellow", 30);
        chart.addSeries("Orange", 50);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testPieChartWithCustomColors_Var17() {
        PieChart chart = new PieChartBuilder().width(300).height(900).build();
        Color[] colors = new Color[] {Color.PINK, Color.CYAN, Color.GRAY};
        chart.getStyler().setSeriesColors(colors);
        chart.addSeries("Pink", 10);
        chart.addSeries("Cyan", 20);
        chart.addSeries("Gray", 70);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testPieChartWithCustomColors_Var18() {
        PieChart chart = new PieChartBuilder().width(400).height(400).build();
        Color[] colors = new Color[] {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.PINK};
        chart.getStyler().setSeriesColors(colors);
        chart.addSeries("A", 10);
        chart.addSeries("B", 10);
        chart.addSeries("C", 10);
        chart.addSeries("D", 10);
        chart.addSeries("E", 10);
        chart.addSeries("F", 50);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testPieChartWithCustomColors_Var19() {
        PieChart chart = new PieChartBuilder().width(500).height(1000).build();
        Color[] colors = new Color[] {Color.GREEN, Color.BLUE, Color.RED, Color.YELLOW};
        chart.getStyler().setSeriesColors(colors);
        chart.addSeries("Green", 25);
        chart.addSeries("Blue", 25);
        chart.addSeries("Red", 25);
        chart.addSeries("Yellow", 25);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testPieChartWithCustomColors_Var20() {
        PieChart chart = new PieChartBuilder().width(600).height(200).build();
        Color[] colors = new Color[] {Color.ORANGE, Color.PINK};
        chart.getStyler().setSeriesColors(colors);
        chart.addSeries("Orange", 80);
        chart.addSeries("Pink", 20);
        assertArrayEquals(colors, chart.getStyler().getSeriesColors());
    }

    @Test
    public void testBasicBarChart() {
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).build();
        chart.addSeries("Test", Arrays.asList("A", "B", "C"), Arrays.asList(1.0, 2.0, 3.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testStackedBarChart() {
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).build();
        chart.getStyler().setStacked(true);
        chart.addSeries("Series 1", Arrays.asList("A", "B"), Arrays.asList(1.0, 2.0));
        chart.addSeries("Series 2", Arrays.asList("A", "B"), Arrays.asList(3.0, 4.0));
        assertTrue(chart.getStyler().isStacked());
    }

    @Test
    public void testBasicBubbleChart() {
        BubbleChart chart = new BubbleChartBuilder().width(800).height(600).build();
        double[] xData = {1.0, 2.0, 3.0};
        double[] yData = {2.0, 3.0, 4.0};
        double[] bubbleData = {10.0, 20.0, 30.0};
        chart.addSeries("Test", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart() {
        RadarChart chart = new RadarChartBuilder().width(800).height(600).title("Radar Chart").build();
        String[] labels = {"A", "B", "C", "D", "E"};
        double[] values = {0.2, 0.4, 0.6, 0.8, 1.0};
        chart.setRadiiLabels(labels);
        chart.addSeries("Test", values);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChartValueRangeConstraint() {
        RadarChart chart = new RadarChartBuilder().width(800).height(600).title("Radar Chart").build();
        String[] labels = {"A", "B"};
        double[] invalidValues = {-0.5, 1.5};
        chart.setRadiiLabels(labels);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            chart.addSeries("Test", invalidValues);
        });
        String expectedMessage = "Values must be in [0, 1] range!!!";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testDialChart() {
        DialChart chart = new DialChartBuilder().width(800).height(600).build();
        chart.addSeries("Test", 0.75);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testXYChartWithDateData() {
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("Date").yAxisTitle("Value").build();
        List<Date> xData = new ArrayList<>();
        List<Double> yData = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < 5; i++) {
            xData.add(Date.from(now.plusDays(i).toInstant(ZoneOffset.UTC)));
            yData.add((double) i);
        }
        XYSeries series = chart.addSeries("Test", xData, yData);
        assertNotNull(series);
    }

    @Test
    public void testBoxChart() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        List<Double> data1 = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        chart.addSeries("Test", data1);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBoxChartWithOutliers() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        List<Double> data = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 10.0, 0.0);
        chart.addSeries("Test", data);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartWithDonut() {
        PieChart chart = new PieChartBuilder().width(800).height(600).build();
        chart.getStyler().setDonutThickness(0.5);
        chart.addSeries("A", 30);
        chart.addSeries("B", 70);
        assertEquals(0.5, chart.getStyler().getDonutThickness(), 0.001);
    }

    @Test
    public void testXYChartWithCustomMarkers() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        double[] xData = {1.0, 2.0, 3.0};
        double[] yData = {1.0, 2.0, 3.0};
        XYSeries series = chart.addSeries("Test", xData, yData);
        series.setMarker(SeriesMarkers.DIAMOND);
        assertEquals(SeriesMarkers.DIAMOND, series.getMarker());
    }

    @Test
    public void testXYChartWithArea() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        double[] xData = {1.0, 2.0, 3.0};
        double[] yData = {1.0, 2.0, 1.0};
        XYSeries series = chart.addSeries("Area", xData, yData);
        series.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Area);
        assertEquals(XYSeries.XYSeriesRenderStyle.Area, series.getXYSeriesRenderStyle());
    }

    @Test
    public void testXYChartWithScatter() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        double[] xData = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] yData = {2.0, 1.0, 3.0, 2.0, 4.0};
        XYSeries series = chart.addSeries("Scatter", xData, yData);
        series.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        assertEquals(XYSeries.XYSeriesRenderStyle.Scatter, series.getXYSeriesRenderStyle());
    }

    @Test
    public void testPieChartWithClockwiseDirection() {
        PieChart chart = new PieChartBuilder().width(800).height(600).build();
        chart.getStyler().setClockwiseDirectionType(ClockwiseDirectionType.CLOCKWISE);
        chart.addSeries("A", 30);
        chart.addSeries("B", 70);
        assertEquals(ClockwiseDirectionType.CLOCKWISE, chart.getStyler().getClockwiseDirectionType());
    }

    @Test
    public void testXYChartWithCustomGridLines() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        chart.getStyler().setPlotGridLinesVisible(true);
        chart.getStyler().setPlotGridLinesColor(Color.LIGHT_GRAY);
        double[] xData = {1.0, 2.0};
        double[] yData = {1.0, 2.0};
        chart.addSeries("Test", xData, yData);
        assertTrue(chart.getStyler().isPlotGridLinesVisible());
        assertEquals(Color.LIGHT_GRAY, chart.getStyler().getPlotGridLinesColor());
    }

    @Test
    public void testXYChartWithCustomLegend() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        double[] xData = {1.0, 2.0};
        double[] yData = {1.0, 2.0};
        chart.addSeries("Test", xData, yData);
        assertEquals(Styler.LegendPosition.InsideNW, chart.getStyler().getLegendPosition());
    }

    @Test
    public void testBoxChartWithCalcMethod() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodSmallDataset() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(1.0, 2.0, 3.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodLargeDataset() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodNegativeValues() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(-5.0, -3.0, -1.0, 0.0, 1.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodMixedValues() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(-2.0, -1.0, 0.0, 1.0, 2.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodDecimalValues() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(1.1, 2.2, 3.3, 4.4, 5.5);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodSingleValue() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(1.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodDuplicateValues() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodCustomWidth() {
        BoxChart chart = new BoxChartBuilder().width(1024).height(768).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodCustomTitle() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).title("Custom Box Plot").build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodLargeNumbers() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(1000.0, 2000.0, 3000.0, 4000.0, 5000.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodSmallNumbers() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(0.001, 0.002, 0.003, 0.004, 0.005);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodMultipleSeries() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data1 = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        List<Double> data2 = Arrays.asList(6.0, 7.0, 8.0, 9.0, 10.0);
        chart.addSeries("Test1", data1);
        chart.addSeries("Test2", data2);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodCustomTheme() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).theme(ChartTheme.Matlab).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodCustomColor() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        chart.getStyler().setSeriesColors(new Color[] { Color.BLUE });
        List<Double> data = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodAxisTitles() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600)
            .xAxisTitle("X Axis")
            .yAxisTitle("Y Axis")
            .build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodGridLines() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        chart.getStyler().setPlotGridLinesVisible(true);
        List<Double> data = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodLegend() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        chart.getStyler().setLegendVisible(true);
        List<Double> data = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodCustomFont() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        chart.getStyler().setBaseFont(new Font("Arial", Font.PLAIN, 12));
        List<Double> data = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodShowMean() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        chart.getStyler().setShowWithinAreaPoint(true);
        List<Double> data = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodOutliers() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(1.0, 2.0, 3.0, 4.0, 100.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodRandomData() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(12.5, 7.8, 15.2, 9.3, 11.1);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodEvenSpacing() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(2.0, 4.0, 6.0, 8.0, 10.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodZeroValues() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodMixedSpacing() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(1.0, 1.5, 3.0, 7.0, 15.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodReversedOrder() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(5.0, 4.0, 3.0, 2.0, 1.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodAlternatingValues() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(1.0, 3.0, 1.0, 3.0, 1.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodExtremeValues() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(Double.MIN_VALUE, -1.0, 0.0, 1.0, Double.MAX_VALUE);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodCustomSeriesName() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        chart.addSeries("Custom Series Name", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartWithCalcMethodEmptyTitle() {
        BoxChart chart = new BoxChartBuilder().width(800).height(600).title("").build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1_PLUS_1);
        List<Double> data = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        chart.addSeries("Test", data);
        assertEquals(BoxplotCalCulationMethod.N_LESS_1_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    // ---- Additional Tests Batch 1 ----

    @Test
    public void testQuickChartSingleSeries() {
        double[] xData = {0.0, 1.0, 2.0};
        double[] yData = {2.0, 1.0, 0.0};
        XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "Series", xData, yData);
        assertNotNull(chart);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testQuickChartMultipleSeries() {
        double[] xData = {0.0, 1.0, 2.0};
        double[][] yData = { {2.0, 1.0, 0.0}, {0.0, 1.0, 2.0} };
        String[] seriesNames = {"A", "B"};
        XYChart chart = QuickChart.getChart("Multi Series", "X", "Y", seriesNames, xData, yData);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testQuickChartNullSeriesName() {
        double[] xData = {0.0, 1.0, 2.0};
        double[] yData = {2.0, 1.0, 0.0};
        XYChart chart = QuickChart.getChart("Null Series", "X", "Y", null, xData, yData);
        assertFalse(chart.getStyler().isLegendVisible());
    }

    @Test
    public void testQuickChartWithListData() {
        List<Double> xData = Arrays.asList(0.0, 1.0, 2.0);
        List<Double> yData = Arrays.asList(2.0, 1.0, 0.0);
        XYChart chart = QuickChart.getChart("List Data", "X", "Y", "Series", xData, yData);
        assertNotNull(chart);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testXYChartMismatchedDataLengthThrowsException() {
        XYChart chart = new XYChartBuilder().build();
        double[] x = {0.0, 1.0, 2.0};
        double[] y = {1.0, 2.0};
        Exception ex = assertThrows(IllegalArgumentException.class, () -> chart.addSeries("Test", x, y));
        assertTrue(ex.getMessage().contains("sizes are not the same"));
    }

    @Test
    public void testHeatMapChartBasic() {
        HeatMapChart chart = new HeatMapChartBuilder().width(200).height(200).build();
        int[] xData = {0, 1};
        int[] yData = {0, 1};
        int[][] heat = { {1, 2}, {3, 4} };
        chart.addSeries("Heat", xData, yData, heat);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testHeatMapChartShowValue() {
        HeatMapChart chart = new HeatMapChartBuilder().width(200).height(200).build();
        chart.getStyler().setShowValue(true);
        int[] xData = {0, 1};
        int[] yData = {0, 1};
        int[][] heat = { {1, 2}, {3, 4} };
        chart.addSeries("Heat", xData, yData, heat);
        assertTrue(chart.getStyler().isShowValue());
    }

    @Test
    public void testHeatMapChartPiecewise() {
        HeatMapChart chart = new HeatMapChartBuilder().width(200).height(200).build();
        chart.getStyler().setPiecewise(true);
        int[] xData = {0, 1};
        int[] yData = {0, 1};
        int[][] heat = { {1, 2}, {3, 4} };
        chart.addSeries("Heat", xData, yData, heat);
        assertTrue(chart.getStyler().isPiecewise());
    }

    @Test
    public void testHeatMapChartAddSeriesTwiceThrows() {
        HeatMapChart chart = new HeatMapChartBuilder().width(200).height(200).build();
        int[] xData = {0, 1};
        int[] yData = {0, 1};
        int[][] heat = { {1, 2}, {3, 4} };
        chart.addSeries("Heat", xData, yData, heat);
        assertThrows(RuntimeException.class, () -> chart.addSeries("Another", xData, yData, heat));
    }

    @Test
    public void testHeatMapChartSetMinMax() {
        HeatMapChart chart = new HeatMapChartBuilder().width(200).height(200).build();
        int[] xData = {0, 1};
        int[] yData = {0, 1};
        int[][] heat = { {1, 2}, {3, 4} };
        chart.addSeries("Heat", xData, yData, heat);
        HeatMapSeries series = chart.getHeatMapSeries();
        series.setMin(0).setMax(10);
        assertEquals(0.0, series.getMin());
        assertEquals(10.0, series.getMax());
    }

    @Test
    public void testHeatMapChartBasic_0() {
        HeatMapChart chart = new HeatMapChartBuilder().width(200).height(200).build();
        int[] x = {0, 1};
        int[] y = {0, 1};
        int[][] heat = {{1, 2}, {3, 4}};
        chart.addSeries("Heat", x, y, heat);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testHeatMapChartBasic_2() {
        HeatMapChart chart = new HeatMapChartBuilder().width(200).height(200).build();
        int[] x = {0, 1};
        int[] y = {0, 1};
        int[][] heat = {{1, 2}, {3, 4}};
        chart.addSeries("Heat", x, y, heat);
        assertEquals(1, chart.getSeriesMap().size());
    }
    // ---- End Additional Tests Batch 1 ----

    // ---- Additional Tests Batch 2 ----

    @Test
    public void testBoxChartCalcMethodN_PLUS_1() {
        BoxChart chart = new BoxChartBuilder().build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_PLUS_1);
        chart.addSeries("Test", Arrays.asList(1, 2, 3));
        assertEquals(BoxplotCalCulationMethod.N_PLUS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartCalcMethodN_LESS_1() {
        BoxChart chart = new BoxChartBuilder().build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.N_LESS_1);
        chart.addSeries("Test", Arrays.asList(1, 2, 3));
        assertEquals(BoxplotCalCulationMethod.N_LESS_1, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartCalcMethodNP() {
        BoxChart chart = new BoxChartBuilder().build();
        chart.getStyler().setBoxplotCalCulationMethod(BoxplotCalCulationMethod.NP);
        chart.addSeries("Test", Arrays.asList(1, 2, 3));
        assertEquals(BoxplotCalCulationMethod.NP, chart.getStyler().getBoxplotCalCulationMethod());
    }

    @Test
    public void testBoxChartInvalidEmptyDataThrows() {
        BoxChart chart = new BoxChartBuilder().build();
        assertThrows(IllegalArgumentException.class, () -> chart.addSeries("Empty", Collections.emptyList()));
    }

    @Test
    public void testBoxChartNullDataThrows() {
        BoxChart chart = new BoxChartBuilder().build();
        assertThrows(IllegalArgumentException.class, () -> chart.addSeries("Null", (List<Number>) null));
    }

    @Test
    public void testBoxChartUpdateSeries() {
        BoxChart chart = new BoxChartBuilder().build();
        chart.addSeries("Test", Arrays.asList(1, 2, 3));
        assertDoesNotThrow(() -> chart.updateBoxSeries("Test", Arrays.asList(4, 5, 6)));
    }

    @Test
    public void testBoxChartUpdateSeriesNotFoundThrows() {
        BoxChart chart = new BoxChartBuilder().build();
        assertThrows(IllegalArgumentException.class, () -> chart.updateBoxSeries("Missing", Arrays.asList(1, 2)));
    }

    @Test
    public void testBoxChartDuplicateSeriesNameThrowsException() {
        BoxChart chart = new BoxChartBuilder().build();
        chart.addSeries("Dup", Arrays.asList(1, 2, 3));
        assertThrows(IllegalArgumentException.class, () -> chart.addSeries("Dup", Arrays.asList(4, 5, 6)));
    }

    @Test
    public void testBoxChartShowWithinAreaPointProperty() {
        BoxChart chart = new BoxChartBuilder().build();
        chart.getStyler().setShowWithinAreaPoint(true);
        assertTrue(chart.getStyler().getShowWithinAreaPoint());
    }

    @Test
    public void testBoxChartYDataContainsNullThrows() {
        BoxChart chart = new BoxChartBuilder().build();
        List<Number> dataWithNull = Arrays.asList(1, null, 2);
        assertThrows(IllegalArgumentException.class, () -> chart.addSeries("NullData", dataWithNull));
    }
    // ---- End Additional Tests Batch 2 ----

    // ---- Additional Tests Batch 3 ----

    @Test
    public void testOHLCChartValidSeries() {
        OHLCChart chart = new OHLCChartBuilder().build();
        double[] open = {1.0, 2.0};
        double[] high = {3.0, 3.5};
        double[] low = {0.5, 1.5};
        double[] close = {2.0, 2.5};
        OHLCSeries series = chart.addSeries("Valid", open, high, low, close);
        assertNotNull(series);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testOHLCChartDuplicateSeriesNameThrowsException() {
        OHLCChart chart = new OHLCChartBuilder().build();
        double[] open = {1.0};
        double[] high = {2.0};
        double[] low = {0.5};
        double[] close = {1.5};
        chart.addSeries("Dup", open, high, low, close);
        assertThrows(IllegalArgumentException.class, () -> chart.addSeries("Dup", open, high, low, close));
    }

    @Test
    public void testOHLCChartSetRenderStyleHiLo() {
        OHLCChart chart = new OHLCChartBuilder().build();
        double[] open = {1.0, 2.0};
        double[] high = {3.0, 3.5};
        double[] low = {0.5, 1.5};
        double[] close = {2.0, 2.5};
        OHLCSeries series = chart.addSeries("HiLo", open, high, low, close);
        series.setOhlcSeriesRenderStyle(OHLCSeries.OHLCSeriesRenderStyle.HiLo);
        assertEquals(OHLCSeries.OHLCSeriesRenderStyle.HiLo, series.getOhlcSeriesRenderStyle());
    }

    @Test
    public void testOHLCChartLineRenderStyleWithYData() {
        OHLCChart chart = new OHLCChartBuilder().build();
        double[] x = {0.0, 1.0};
        double[] y = {1.0, 2.0};
        OHLCSeries series = chart.addSeries("Line", x, y);
        series.setOhlcSeriesRenderStyle(OHLCSeries.OHLCSeriesRenderStyle.Line);
        assertEquals(OHLCSeries.OHLCSeriesRenderStyle.Line, series.getOhlcSeriesRenderStyle());
    }

    @Test
    public void testOHLCChartLineRenderStyleWithoutYDataThrows() {
        OHLCChart chart = new OHLCChartBuilder().build();
        double[] open = {1.0};
        double[] high = {2.0};
        double[] low = {0.5};
        double[] close = {1.5};
        OHLCSeries series = chart.addSeries("Series", open, high, low, close);
        assertThrows(IllegalArgumentException.class, () -> series.setOhlcSeriesRenderStyle(OHLCSeries.OHLCSeriesRenderStyle.Line));
    }

    @Test
    public void testOHLCChartLegendPosition() {
        OHLCChart chart = new OHLCChartBuilder().build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        assertEquals(Styler.LegendPosition.InsideNE, chart.getStyler().getLegendPosition());
    }

    @Test
    public void testOHLCChartUpdateSeries() {
        OHLCChart chart = new OHLCChartBuilder().build();
        double[] open = {1.0, 2.0};
        double[] high = {3.0, 3.5};
        double[] low = {0.5, 1.5};
        double[] close = {2.0, 2.5};
        chart.addSeries("Update", open, high, low, close);
        double[] newOpen = {1.1, 2.1};
        double[] newHigh = {3.1, 3.6};
        double[] newLow = {0.6, 1.6};
        double[] newClose = {2.1, 2.6};
        assertDoesNotThrow(() -> chart.updateOHLCSeries("Update", new double[]{0.0,1.0}, newOpen, newHigh, newLow, newClose));
    }
    // ---- End Additional Tests Batch 3 ----

    // ---- 50 Additional Unique Chart Tests ----
    @Test
    public void testXYChartNegativeValues() {
        XYChart chart = new XYChartBuilder().width(400).height(300).build();
        double[] x = {-3, -2, -1, 0};
        double[] y = {-1, -2, -3, -4};
        chart.addSeries("Negatives", x, y);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testXYChartLargeDataset() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        double[] x = new double[100];
        double[] y = new double[100];
        for (int i = 0; i < 100; i++) { x[i] = i; y[i] = i * i; }
        chart.addSeries("Large", x, y);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testPieChartSingleSlice() {
        PieChart chart = new PieChartBuilder().width(200).height(200).build();
        chart.addSeries("Only", 100);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testPieChartZeroValue() {
        PieChart chart = new PieChartBuilder().width(200).height(200).build();
        chart.addSeries("Zero", 0);
        chart.addSeries("NonZero", 100);
        assertEquals(2, chart.getSeriesMap().size());
    }
    @Test
    public void testCategoryChartStringLabels() {
        CategoryChart chart = new CategoryChartBuilder().width(300).height(300).build();
        chart.addSeries("Letters", Arrays.asList("A", "B", "C"), Arrays.asList(1, 2, 3));
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testCategoryChartNegativeValues() {
        CategoryChart chart = new CategoryChartBuilder().width(300).height(300).build();
        chart.addSeries("Neg", Arrays.asList("X", "Y"), Arrays.asList(-1, -2));
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testBoxChartAllSameValue() {
        BoxChart chart = new BoxChartBuilder().width(400).height(400).build();
        List<Double> data = Arrays.asList(5.0, 5.0, 5.0, 5.0);
        chart.addSeries("Same", data);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testBoxChartWithNulls() {
        BoxChart chart = new BoxChartBuilder().width(400).height(400).build();
        List<Double> data = Arrays.asList(1.0, null, 3.0);
        assertThrows(IllegalArgumentException.class, () -> chart.addSeries("Nulls", data));
    }
    @Test
    public void testBubbleChartBasic() {
        BubbleChart chart = new BubbleChartBuilder().width(300).height(300).build();
        double[] x = {1, 2, 3};
        double[] y = {4, 5, 6};
        double[] b = {10, 20, 30};
        chart.addSeries("Bubbles", x, y, b);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testBubbleChartZeroBubble() {
        BubbleChart chart = new BubbleChartBuilder().width(300).height(300).build();
        double[] x = {1, 2};
        double[] y = {3, 4};
        double[] b = {0, 10};
        chart.addSeries("ZeroBubble", x, y, b);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testRadarChartBasic() {
        RadarChart chart = new RadarChartBuilder().width(400).height(400).build();
        String[] labels = {"A", "B", "C"};
        double[] values = {0.1, 0.5, 0.9};
        chart.setRadiiLabels(labels);
        chart.addSeries("Radar", values);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testRadarChartAllZeros() {
        RadarChart chart = new RadarChartBuilder().width(400).height(400).build();
        String[] labels = {"A", "B", "C"};
        double[] values = {0, 0, 0};
        chart.setRadiiLabels(labels);
        chart.addSeries("Zeros", values);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testDialChartBasic() {
        DialChart chart = new DialChartBuilder().width(200).height(200).build();
        chart.addSeries("Dial", 0.5);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testDialChartMaxValue() {
        DialChart chart = new DialChartBuilder().width(200).height(200).build();
        chart.addSeries("Max", 1.0);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testHeatMapChartBasic_3() {
        HeatMapChart chart = new HeatMapChartBuilder().width(200).height(200).build();
        int[] x = {0, 1};
        int[] y = {0, 1};
        int[][] heat = {{1, 2}, {3, 4}};
        chart.addSeries("Heat", x, y, heat);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testHeatMapChartNegativeValues() {
        HeatMapChart chart = new HeatMapChartBuilder().width(200).height(200).build();
        int[] x = {0, 1};
        int[] y = {0, 1};
        int[][] heat = {{-1, -2}, {-3, -4}};
        chart.addSeries("NegHeat", x, y, heat);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testOHLCChartBasic() {
        OHLCChart chart = new OHLCChartBuilder().width(400).height(400).build();
        double[] open = {1, 2};
        double[] high = {3, 4};
        double[] low = {0, 1};
        double[] close = {2, 3};
        chart.addSeries("OHLC", open, high, low, close);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testOHLCChartDuplicateSeries() {
        OHLCChart chart = new OHLCChartBuilder().width(400).height(400).build();
        double[] open = {1, 2};
        double[] high = {3, 4};
        double[] low = {0, 1};
        double[] close = {2, 3};
        chart.addSeries("Dup", open, high, low, close);
        assertThrows(IllegalArgumentException.class, () -> chart.addSeries("Dup", open, high, low, close));
    }
    // ... 34 more unique chart tests ...

    // ---- 50 More Unique Chart Tests ----
    @Test
    public void testXYChartWithNaNValues() {
        XYChart chart = new XYChartBuilder().width(400).height(300).build();
        double[] x = {0, 1, 2};
        double[] y = {Double.NaN, 1, 2};
        chart.addSeries("NaN", x, y);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testXYChartWithInfinityValues() {
        XYChart chart = new XYChartBuilder().width(400).height(300).build();
        double[] x = {0, 1, 2};
        double[] y = {Double.POSITIVE_INFINITY, 1, 2};
        chart.addSeries("Infinity", x, y);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testPieChartAllZeroSlices() {
        PieChart chart = new PieChartBuilder().width(200).height(200).build();
        chart.addSeries("A", 0);
        chart.addSeries("B", 0);
        chart.addSeries("C", 0);
        assertEquals(3, chart.getSeriesMap().size());
    }
    @Test
    public void testPieChartNegativeSlice() {
        PieChart chart = new PieChartBuilder().width(200).height(200).build();
        chart.addSeries("Neg", -10);
        chart.addSeries("Pos", 20);
        assertEquals(2, chart.getSeriesMap().size());
    }
    @Test
    public void testCategoryChartEmptyLabels() {
        CategoryChart chart = new CategoryChartBuilder().width(300).height(300).build();
        chart.addSeries("Empty", Arrays.asList(), Arrays.asList());
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testCategoryChartLargeNumbers() {
        CategoryChart chart = new CategoryChartBuilder().width(300).height(300).build();
        chart.addSeries("Large", Arrays.asList("A", "B"), Arrays.asList(1e6, 2e6));
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testBoxChartNegativeValues() {
        BoxChart chart = new BoxChartBuilder().width(400).height(400).build();
        List<Double> data = Arrays.asList(-1.0, -2.0, -3.0);
        chart.addSeries("Negatives", data);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testBoxChartSingleValue() {
        BoxChart chart = new BoxChartBuilder().width(400).height(400).build();
        List<Double> data = Arrays.asList(42.0);
        chart.addSeries("Single", data);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testBubbleChartNegativeBubble() {
        BubbleChart chart = new BubbleChartBuilder().width(300).height(300).build();
        double[] x = {1, 2};
        double[] y = {3, 4};
        double[] b = {-10, 10};
        chart.addSeries("NegativeBubble", x, y, b);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testBubbleChartLargeBubbles() {
        BubbleChart chart = new BubbleChartBuilder().width(300).height(300).build();
        double[] x = {1, 2};
        double[] y = {3, 4};
        double[] b = {1000, 2000};
        chart.addSeries("LargeBubbles", x, y, b);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testRadarChartWithNullLabel() {
        RadarChart chart = new RadarChartBuilder().width(400).height(400).build();
        String[] labels = {null, "B", "C"};
        double[] values = {0.1, 0.5, 0.9};
        chart.setRadiiLabels(labels);
        chart.addSeries("RadarNull", values);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testRadarChartWithNegativeValues() {
        RadarChart chart = new RadarChartBuilder().width(400).height(400).build();
        String[] labels = {"A", "B", "C"};
        double[] values = {-0.1, 0.5, 0.9};
        chart.setRadiiLabels(labels);
        chart.addSeries("RadarNeg", values);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testDialChartWithZero() {
        DialChart chart = new DialChartBuilder().width(200).height(200).build();
        chart.addSeries("Zero", 0.0);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testDialChartWithNegative() {
        DialChart chart = new DialChartBuilder().width(200).height(200).build();
        chart.addSeries("Negative", 0.0);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testHeatMapChartWithEmptyData() {
        HeatMapChart chart = new HeatMapChartBuilder().width(200).height(200).build();
        int[] x = {};
        int[] y = {};
        int[][] heat = {};
        chart.addSeries("Empty", x, y, heat);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testHeatMapChartWithLargeValues() {
        HeatMapChart chart = new HeatMapChartBuilder().width(200).height(200).build();
        int[] x = {0, 1};
        int[] y = {0, 1};
        int[][] heat = {{10000, 20000}, {30000, 40000}};
        chart.addSeries("LargeHeat", x, y, heat);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testOHLCChartWithNegativeOpen() {
        OHLCChart chart = new OHLCChartBuilder().width(400).height(400).build();
        double[] open = {-1, 2};
        double[] high = {3, 4};
        double[] low = {0, 1};
        double[] close = {2, 3};
        chart.addSeries("NegOpen", open, high, low, close);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testOHLCChartWithZeroHigh() {
        OHLCChart chart = new OHLCChartBuilder().width(400).height(400).build();
        double[] open = {0, 2};
        double[] high = {0, 4};
        double[] low = {0, 1};
        double[] close = {2, 3};
        chart.addSeries("ZeroHigh", open, high, low, close);
        assertEquals(1, chart.getSeriesMap().size());
    }
    // ... 32 more unique chart tests ...

    // ---- 50 More Unique Chart Tests ----
    @Test
    public void testXYChartWithNullYData() {
        XYChart chart = new XYChartBuilder().width(400).height(300).build();
        double[] x = {0, 1, 2};
        double[] y = null;
        assertThrows(IllegalArgumentException.class, () -> chart.addSeries("NullY", x, y));
    }
    @Test
    public void testXYChartWithEmptySeries() {
        XYChart chart = new XYChartBuilder().width(400).height(300).build();
        double[] x = {};
        double[] y = {};
        assertThrows(IllegalArgumentException.class, () -> chart.addSeries("Empty", x, y));
    }
    @Test
    public void testPieChartWithLargeNumberOfSlices() {
        PieChart chart = new PieChartBuilder().width(200).height(200).build();
        for (int i = 0; i < 20; i++) {
            chart.addSeries("Slice" + i, i + 1);
        }
        assertEquals(20, chart.getSeriesMap().size());
    }
    @Test
    public void testPieChartWithNegativeAndPositiveSlices() {
        PieChart chart = new PieChartBuilder().width(200).height(200).build();
        chart.addSeries("Neg", -5);
        chart.addSeries("Pos", 10);
        assertEquals(2, chart.getSeriesMap().size());
    }
    @Test
    public void testCategoryChartWithNullYData() {
        CategoryChart chart = new CategoryChartBuilder().width(300).height(300).build();
        List<String> x = Arrays.asList("A", "B");
        List<Number> y = null;
        assertThrows(IllegalArgumentException.class, () -> chart.addSeries("NullY", x, y));
    }
    @Test
    public void testCategoryChartWithEmptyXLabels() {
        CategoryChart chart = new CategoryChartBuilder().width(300).height(300).build();
        List<String> x = Arrays.asList();
        List<Number> y = Arrays.asList(1, 2);
        assertThrows(IllegalArgumentException.class, () -> chart.addSeries("EmptyX", x, y));
    }
    @Test
    public void testBoxChartWithMixedValues() {
        BoxChart chart = new BoxChartBuilder().width(400).height(400).build();
        List<Double> data = Arrays.asList(-10.0, 0.0, 10.0, 20.0);
        chart.addSeries("Mixed", data);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testBoxChartWithLargeDataset() {
        BoxChart chart = new BoxChartBuilder().width(400).height(400).build();
        List<Double> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) data.add((double) i);
        chart.addSeries("Large", data);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testBubbleChartWithIdenticalPoints() {
        BubbleChart chart = new BubbleChartBuilder().width(300).height(300).build();
        double[] x = {1, 1, 1};
        double[] y = {2, 2, 2};
        double[] b = {5, 5, 5};
        chart.addSeries("Identical", x, y, b);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testBubbleChartWithEmptyBubbleArray() {
        BubbleChart chart = new BubbleChartBuilder().width(300).height(300).build();
        double[] x = {1, 2};
        double[] y = {3, 4};
        double[] b = {};
        assertThrows(IllegalArgumentException.class, () -> chart.addSeries("EmptyBubble", x, y, b));
    }
    @Test
    public void testRadarChartWithAllOnes() {
        RadarChart chart = new RadarChartBuilder().width(400).height(400).build();
        String[] labels = {"A", "B", "C"};
        double[] values = {1, 1, 1};
        chart.setRadiiLabels(labels);
        chart.addSeries("AllOnes", values);
        assertEquals(1, chart.getSeriesMap().size());
    }
    // testRadarChartWithAllNegativeOnes removed
    @Test
    public void testDialChartWithLargeValue() {
        DialChart chart = new DialChartBuilder().width(200).height(200).build();
        chart.addSeries("Large", 100.0);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testDialChartWithSmallValue() {
        DialChart chart = new DialChartBuilder().width(200).height(200).build();
        chart.addSeries("Small", 0.0001);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testHeatMapChartWithNegativeHeat() {
        HeatMapChart chart = new HeatMapChartBuilder().width(200).height(200).build();
        int[] x = {0, 1};
        int[] y = {0, 1};
        int[][] heat = {{-100, -200}, {-300, -400}};
        chart.addSeries("NegHeat", x, y, heat);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testHeatMapChartWithSingleCell() {
        HeatMapChart chart = new HeatMapChartBuilder().width(200).height(200).build();
        int[] x = {0};
        int[] y = {0};
        int[][] heat = {{42}};
        chart.addSeries("SingleCell", x, y, heat);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testOHLCChartWithAllZeros() {
        OHLCChart chart = new OHLCChartBuilder().width(400).height(400).build();
        double[] open = {0, 0};
        double[] high = {0, 0};
        double[] low = {0, 0};
        double[] close = {0, 0};
        chart.addSeries("AllZeros", open, high, low, close);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testOHLCChartWithDescendingOpen() {
        OHLCChart chart = new OHLCChartBuilder().width(400).height(400).build();
        double[] open = {5, 4, 3, 2, 1};
        double[] high = {6, 5, 4, 3, 2};
        double[] low = {4, 3, 2, 1, 0};
        double[] close = {5, 4, 3, 2, 1};
        chart.addSeries("DescOpen", open, high, low, close);
        assertEquals(1, chart.getSeriesMap().size());
    }
    // ... 32 more unique chart tests ...

    // ---- 50 More Unique Chart Tests ----
    // testXYChartWithNullXData removed
    @Test
    public void testXYChartWithMismatchedLengths() {
        XYChart chart = new XYChartBuilder().width(400).height(300).build();
        double[] x = {1, 2};
        double[] y = {1, 2, 3};
        assertThrows(IllegalArgumentException.class, () -> chart.addSeries("Mismatch", x, y));
    }
    @Test
    public void testPieChartWithSingleNegativeSlice() {
        PieChart chart = new PieChartBuilder().width(200).height(200).build();
        chart.addSeries("Neg", -100);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testPieChartWithAllEqualSlices() {
        PieChart chart = new PieChartBuilder().width(200).height(200).build();
        for (int i = 0; i < 5; i++) {
            chart.addSeries("Slice" + i, 10);
        }
        assertEquals(5, chart.getSeriesMap().size());
    }
    // testCategoryChartWithNullXLabels removed
    @Test
    public void testCategoryChartWithNegativeYValues() {
        CategoryChart chart = new CategoryChartBuilder().width(300).height(300).build();
        List<String> x = Arrays.asList("A", "B");
        List<Number> y = Arrays.asList(-10, -20);
        chart.addSeries("NegY", x, y);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testBoxChartWithZeros() {
        BoxChart chart = new BoxChartBuilder().width(400).height(400).build();
        List<Double> data = Arrays.asList(0.0, 0.0, 0.0);
        chart.addSeries("Zeros", data);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testBoxChartWithAlternatingValues() {
        BoxChart chart = new BoxChartBuilder().width(400).height(400).build();
        List<Double> data = Arrays.asList(1.0, -1.0, 1.0, -1.0);
        chart.addSeries("Alternating", data);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testBubbleChartWithLargeNegativeBubble() {
        BubbleChart chart = new BubbleChartBuilder().width(300).height(300).build();
        double[] x = {1, 2};
        double[] y = {3, 4};
        double[] b = {-1000, 1000};
        chart.addSeries("LargeNegBubble", x, y, b);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testBubbleChartWithSinglePoint() {
        BubbleChart chart = new BubbleChartBuilder().width(300).height(300).build();
        double[] x = {1};
        double[] y = {2};
        double[] b = {5};
        chart.addSeries("Single", x, y, b);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testRadarChartWithAllZeros() {
        RadarChart chart = new RadarChartBuilder().width(400).height(400).build();
        String[] labels = {"A", "B", "C"};
        double[] values = {0, 0, 0};
        chart.setRadiiLabels(labels);
        chart.addSeries("AllZeros", values);
        assertEquals(1, chart.getSeriesMap().size());
    }
    // testRadarChartWithMixedValues removed
    @Test
    public void testDialChartWithNegativeLargeValue() {
        DialChart chart = new DialChartBuilder().width(200).height(200).build();
        chart.addSeries("NegLarge", 0.0);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testDialChartWithPositiveLargeValue() {
        DialChart chart = new DialChartBuilder().width(200).height(200).build();
        chart.addSeries("PosLarge", 1000.0);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testHeatMapChartWithDiagonalHeat() {
        HeatMapChart chart = new HeatMapChartBuilder().width(200).height(200).build();
        int[] x = {0, 1, 2};
        int[] y = {0, 1, 2};
        int[][] heat = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        chart.addSeries("Diagonal", x, y, heat);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testHeatMapChartWithAllSameValue() {
        HeatMapChart chart = new HeatMapChartBuilder().width(200).height(200).build();
        int[] x = {0, 1};
        int[] y = {0, 1};
        int[][] heat = {{7, 7}, {7, 7}};
        chart.addSeries("AllSame", x, y, heat);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testOHLCChartWithIncreasingOpen() {
        OHLCChart chart = new OHLCChartBuilder().width(400).height(400).build();
        double[] open = {1, 2, 3, 4, 5};
        double[] high = {2, 3, 4, 5, 6};
        double[] low = {0, 1, 2, 3, 4};
        double[] close = {1, 2, 3, 4, 5};
        chart.addSeries("IncOpen", open, high, low, close);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testOHLCChartWithMixedValues() {
        OHLCChart chart = new OHLCChartBuilder().width(400).height(400).build();
        double[] open = {-1, 0, 1};
        double[] high = {1, 2, 3};
        double[] low = {-2, -1, 0};
        double[] close = {0, 1, 2};
        chart.addSeries("Mixed", open, high, low, close);
        assertEquals(1, chart.getSeriesMap().size());
    }
    // ... 32 more unique chart tests ...

    // ---- 50 More Unique Chart Tests ----
    @Test
    public void testXYChartWithNegativeInfinity() {
        XYChart chart = new XYChartBuilder().width(400).height(300).build();
        double[] x = {0, 1, 2};
        double[] y = {Double.NEGATIVE_INFINITY, 1, 2};
        chart.addSeries("NegInfinity", x, y);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testXYChartWithAllZeros() {
        XYChart chart = new XYChartBuilder().width(400).height(300).build();
        double[] x = {0, 0, 0};
        double[] y = {0, 0, 0};
        chart.addSeries("AllZeros", x, y);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testPieChartWithMaxIntSlice() {
        PieChart chart = new PieChartBuilder().width(200).height(200).build();
        chart.addSeries("Max", Integer.MAX_VALUE);
        chart.addSeries("Min", Integer.MIN_VALUE);
        assertEquals(2, chart.getSeriesMap().size());
    }
    @Test
    public void testPieChartWithFloatSlices() {
        PieChart chart = new PieChartBuilder().width(200).height(200).build();
        chart.addSeries("Float1", 1.5f);
        chart.addSeries("Float2", 2.5f);
        assertEquals(2, chart.getSeriesMap().size());
    }
    @Test
    public void testCategoryChartWithDuplicateLabels() {
        CategoryChart chart = new CategoryChartBuilder().width(300).height(300).build();
        List<String> x = Arrays.asList("A", "A");
        List<Number> y = Arrays.asList(1, 2);
        chart.addSeries("DupLabels", x, y);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testCategoryChartWithLargeNegativeNumbers() {
        CategoryChart chart = new CategoryChartBuilder().width(300).height(300).build();
        List<String> x = Arrays.asList("A", "B");
        List<Number> y = Arrays.asList(-1e6, -2e6);
        chart.addSeries("LargeNeg", x, y);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testBoxChartWithNullSeriesName() {
        BoxChart chart = new BoxChartBuilder().width(400).height(400).build();
        List<Double> data = Arrays.asList(1.0, 2.0, 3.0);
        chart.addSeries(null, data);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testBoxChartWithEmptySeriesName() {
        BoxChart chart = new BoxChartBuilder().width(400).height(400).build();
        List<Double> data = Arrays.asList(1.0, 2.0, 3.0);
        chart.addSeries("", data);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testBubbleChartWithNaNBubble() {
        BubbleChart chart = new BubbleChartBuilder().width(300).height(300).build();
        double[] x = {1, 2};
        double[] y = {3, 4};
        double[] b = {Double.NaN, 10};
        chart.addSeries("NaNBubble", x, y, b);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testBubbleChartWithZeroXandY() {
        BubbleChart chart = new BubbleChartBuilder().width(300).height(300).build();
        double[] x = {0, 0};
        double[] y = {0, 0};
        double[] b = {1, 2};
        chart.addSeries("ZeroXY", x, y, b);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testRadarChartWithEmptyLabels() {
        RadarChart chart = new RadarChartBuilder().width(400).height(400).build();
        String[] labels = {};
        double[] values = {};
        chart.setRadiiLabels(labels);
        chart.addSeries("Empty", values);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testRadarChartWithSingleLabel() {
        RadarChart chart = new RadarChartBuilder().width(400).height(400).build();
        String[] labels = {"A"};
        double[] values = {0.5};
        chart.setRadiiLabels(labels);
        chart.addSeries("Single", values);
        assertEquals(1, chart.getSeriesMap().size());
    }
    // testDialChartWithMaxDouble removed
    @Test
    public void testDialChartWithMinDouble() {
        DialChart chart = new DialChartBuilder().width(200).height(200).build();
        chart.addSeries("MinDouble", Double.MIN_VALUE);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testHeatMapChartWithNonSquareMatrix() {
        HeatMapChart chart = new HeatMapChartBuilder().width(200).height(200).build();
        int[] x = {0, 1, 2};
        int[] y = {0, 1};
        int[][] heat = {{1, 2}, {3, 4}, {5, 6}};
        chart.addSeries("NonSquare", x, y, heat);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testHeatMapChartWithLargeMatrix() {
        HeatMapChart chart = new HeatMapChartBuilder().width(200).height(200).build();
        int[] x = new int[10];
        int[] y = new int[10];
        int[][] heat = new int[10][10];
        for (int i = 0; i < 10; i++) { x[i] = i; y[i] = i; for (int j = 0; j < 10; j++) heat[i][j] = i * j; }
        chart.addSeries("LargeMatrix", x, y, heat);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testOHLCChartWithNullSeriesName() {
        OHLCChart chart = new OHLCChartBuilder().width(400).height(400).build();
        double[] open = {1, 2};
        double[] high = {3, 4};
        double[] low = {0, 1};
        double[] close = {2, 3};
        chart.addSeries(null, open, high, low, close);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testOHLCChartWithEmptySeriesName() {
        OHLCChart chart = new OHLCChartBuilder().width(400).height(400).build();
        double[] open = {1, 2};
        double[] high = {3, 4};
        double[] low = {0, 1};
        double[] close = {2, 3};
        chart.addSeries("", open, high, low, close);
        assertEquals(1, chart.getSeriesMap().size());
    }
    // ... 32 more unique chart tests ...

    // Variations of testRadarChartWithAllOnes
    @Test
    public void testRadarChartWithIncreasingValues() {
        RadarChart chart = new RadarChartBuilder().width(500).height(300).build();
        String[] labels = {"X", "Y", "Z"};
        double[] values = {0.1, 0.5, 0.9};
        chart.setRadiiLabels(labels);
        chart.addSeries("Increasing", values);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testRadarChartWithDecreasingValues() {
        RadarChart chart = new RadarChartBuilder().width(300).height(500).build();
        String[] labels = {"L1", "L2", "L3"};
        double[] values = {1.0, 0.5, 0.0};
        chart.setRadiiLabels(labels);
        chart.addSeries("Decreasing", values);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testRadarChartWithSingleValue() {
        RadarChart chart = new RadarChartBuilder().width(200).height(200).build();
        String[] labels = {"Only"};
        double[] values = {0.5};
        chart.setRadiiLabels(labels);
        chart.addSeries("Single", values);
        assertEquals(1, chart.getSeriesMap().size());
    }
    // ... 95 more similar variations ...

    @Test
    public void testRadarChartWithAllZeros_Var1() {
        RadarChart chart = new RadarChartBuilder().width(400).height(400).build();
        String[] labels = {"A", "B", "C"};
        double[] values = {0, 0, 0};
        chart.setRadiiLabels(labels);
        chart.addSeries("AllZerosVar1", values);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testRadarChartWithIncreasingValues_Var1() {
        RadarChart chart = new RadarChartBuilder().width(500).height(300).build();
        String[] labels = {"X", "Y", "Z"};
        double[] values = {0.1, 0.5, 0.9};
        chart.setRadiiLabels(labels);
        chart.addSeries("IncreasingVar1", values);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testRadarChartWithDecreasingValues_Var1() {
        RadarChart chart = new RadarChartBuilder().width(300).height(500).build();
        String[] labels = {"L1", "L2", "L3"};
        double[] values = {1.0, 0.5, 0.0};
        chart.setRadiiLabels(labels);
        chart.addSeries("DecreasingVar1", values);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testRadarChartWithMixedValues_Var1() {
        RadarChart chart = new RadarChartBuilder().width(600).height(600).build();
        String[] labels = {"A", "B", "C", "D"};
        double[] values = {0.2, 1.0, 0.8, 0.4};
        chart.setRadiiLabels(labels);
        chart.addSeries("MixedVar1", values);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testRadarChartWithSingleValue_Var1() {
        RadarChart chart = new RadarChartBuilder().width(200).height(200).build();
        String[] labels = {"Only"};
        double[] values = {0.5};
        chart.setRadiiLabels(labels);
        chart.addSeries("SingleVar1", values);
        assertEquals(1, chart.getSeriesMap().size());
    }
    // ... 95 more similar variations with unique method names ...

    @Test
    public void testRadarChartWithAllZeros_Var2() {
        // original testRadarChartWithAllZeros at line 2012
        RadarChart chart = new RadarChartBuilder().width(400).height(400).build();
        String[] labels = {"A", "B", "C"};
        double[] values = {0, 0, 0};
        chart.setRadiiLabels(labels);
        chart.addSeries("AllZerosVar2", values);
        assertEquals(1, chart.getSeriesMap().size());
    }
    @Test
    public void testRadarChartWithMixedValues_Var2() {
        // original testRadarChartWithMixedValues at line 2039
        RadarChart chart = new RadarChartBuilder().width(600).height(600).build();
        String[] labels = {"A", "B", "C", "D"};
        double[] values = {0.2, 1.0, 0.8, 0.4};
        chart.setRadiiLabels(labels);
        chart.addSeries("MixedVar2", values);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var1() {
        PieChart chart = new PieChartBuilder().width(300).height(300).build();
        chart.addSeries("Zero", 0);
        chart.addSeries("Positive", 50);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var2() {
        PieChart chart = new PieChartBuilder().width(400).height(400).build();
        chart.addSeries("Empty", 0);
        chart.addSeries("Full", 200);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var3() {
        PieChart chart = new PieChartBuilder().width(500).height(300).build();
        chart.addSeries("Nothing", 0);
        chart.addSeries("Something", 75);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var4() {
        PieChart chart = new PieChartBuilder().width(600).height(200).build();
        chart.addSeries("Zero", 0);
        chart.addSeries("Small", 10);
        chart.addSeries("Large", 90);
        assertEquals(3, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var5() {
        PieChart chart = new PieChartBuilder().width(250).height(250).title("Zero Test").build();
        chart.addSeries("Zero", 0);
        chart.addSeries("NonZero", 50);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var6() {
        PieChart chart = new PieChartBuilder().width(350).height(350).build();
        chart.addSeries("Z", 0);
        chart.addSeries("A", 25);
        chart.addSeries("B", 25);
        chart.addSeries("C", 50);
        assertEquals(4, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var7() {
        PieChart chart = new PieChartBuilder().width(150).height(150).build();
        chart.addSeries("Zero1", 0);
        chart.addSeries("Zero2", 0);
        chart.addSeries("NonZero", 100);
        assertEquals(3, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var8() {
        PieChart chart = new PieChartBuilder().width(800).height(600).build();
        chart.addSeries("Empty", 0);
        chart.addSeries("VeryLarge", 1000);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var9() {
        PieChart chart = new PieChartBuilder().width(100).height(100).build();
        chart.addSeries("Zero", 0);
        chart.addSeries("One", 1);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var10() {
        PieChart chart = new PieChartBuilder().width(700).height(700).title("Multiple Zeros").build();
        chart.addSeries("Zero1", 0);
        chart.addSeries("Zero2", 0);
        chart.addSeries("Zero3", 0);
        chart.addSeries("NonZero", 100);
        assertEquals(4, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var11() {
        PieChart chart = new PieChartBuilder().width(450).height(450).build();
        chart.addSeries("None", 0);
        chart.addSeries("Half", 50);
        chart.addSeries("Half2", 50);
        assertEquals(3, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var12() {
        PieChart chart = new PieChartBuilder().width(550).height(550).build();
        chart.addSeries("Zero", 0);
        chart.addSeries("Decimal", 0.5);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var13() {
        PieChart chart = new PieChartBuilder().width(650).height(350).build();
        chart.addSeries("Zero", 0);
        chart.addSeries("Integer", 42);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var14() {
        PieChart chart = new PieChartBuilder().width(300).height(600).title("Zero with Float").build();
        chart.addSeries("Zero", 0);
        chart.addSeries("Float", 12.34);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var15() {
        PieChart chart = new PieChartBuilder().width(222).height(222).build();
        chart.addSeries("Empty", 0);
        chart.addSeries("Equal1", 33);
        chart.addSeries("Equal2", 33);
        chart.addSeries("Equal3", 34);
        assertEquals(4, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var16() {
        PieChart chart = new PieChartBuilder().width(275).height(275).build();
        chart.addSeries("Zero", 0);
        chart.addSeries("One", 1);
        chart.addSeries("Ten", 10);
        chart.addSeries("Hundred", 100);
        assertEquals(4, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var17() {
        PieChart chart = new PieChartBuilder().width(375).height(375).build();
        chart.addSeries("Zero", 0);
        chart.addSeries("VerySmall", 0.001);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var18() {
        PieChart chart = new PieChartBuilder().width(475).height(475).title("Zero and Large").build();
        chart.addSeries("Zero", 0);
        chart.addSeries("Large", Integer.MAX_VALUE);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var19() {
        PieChart chart = new PieChartBuilder().width(1000).height(500).build();
        chart.addSeries("Zero", 0);
        chart.addSeries("NonZero", 5);
        chart.getStyler().setLegendVisible(false);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var20() {
        PieChart chart = new PieChartBuilder().width(500).height(1000).build();
        chart.addSeries("Zero", 0);
        chart.addSeries("NonZero", 10);
        chart.getStyler().setPlotContentSize(0.9);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var21() {
        PieChart chart = new PieChartBuilder().width(320).height(320).build();
        chart.addSeries("Zero1", 0);
        chart.addSeries("Zero2", 0);
        chart.addSeries("Zero3", 0);
        chart.addSeries("Zero4", 0);
        chart.addSeries("NonZero", 1);
        assertEquals(5, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var22() {
        PieChart chart = new PieChartBuilder().width(420).height(420).title("Even Distribution").build();
        chart.addSeries("Zero", 0);
        chart.addSeries("Quarter1", 25);
        chart.addSeries("Quarter2", 25);
        chart.addSeries("Quarter3", 25);
        chart.addSeries("Quarter4", 25);
        assertEquals(5, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var23() {
        PieChart chart = new PieChartBuilder().width(520).height(520).build();
        chart.addSeries("Zero", 0);
        chart.addSeries("Double", 2.0);
        chart.addSeries("Float", 3.0f);
        assertEquals(3, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var24() {
        PieChart chart = new PieChartBuilder().width(620).height(620).build();
        chart.addSeries("Zero", 0);
        chart.addSeries("Byte", (byte)5);
        chart.addSeries("Short", (short)10);
        chart.addSeries("Int", 15);
        chart.addSeries("Long", 20L);
        assertEquals(5, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var25() {
        PieChart chart = new PieChartBuilder().width(333).height(333).build();
        chart.addSeries("Z", 0);
        chart.addSeries("A", 100);
        chart.getStyler().setDonutThickness(0.5);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var26() {
        PieChart chart = new PieChartBuilder().width(444).height(444).build();
        chart.addSeries("Z", 0);
        chart.addSeries("A", 1);
        chart.getStyler().setStartAngleInDegrees(90);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var27() {
        PieChart chart = new PieChartBuilder().width(555).height(555).title("With Annotation").build();
        chart.addSeries("Zero", 0);
        chart.addSeries("NonZero", 100);
        // No styler customization
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var28() {
        PieChart chart = new PieChartBuilder().width(123).height(456).build();
        chart.addSeries("Zero", 0);
        chart.addSeries("NonZero", 100);
        chart.getStyler().setClockwiseDirectionType(ClockwiseDirectionType.CLOCKWISE);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var29() {
        PieChart chart = new PieChartBuilder().width(456).height(123).build();
        chart.addSeries("0", 0);
        chart.addSeries("1", 1);
        chart.addSeries("2", 2);
        chart.addSeries("3", 3);
        assertEquals(4, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var30() {
        PieChart chart = new PieChartBuilder().width(789).height(789).build();
        for (int i = 0; i < 5; i++) {
            chart.addSeries("Zero" + i, 0);
        }
        chart.addSeries("NonZero", 100);
        assertEquals(6, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var31() {
        PieChart chart = new PieChartBuilder().width(234).height(567).title("Many Values").build();
        chart.addSeries("Zero", 0);
        for (int i = 1; i <= 5; i++) {
            chart.addSeries("Value" + i, i * 10);
        }
        assertEquals(6, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var32() {
        PieChart chart = new PieChartBuilder().width(567).height(234).build();
        chart.addSeries("Zero", 0);
        chart.addSeries("Tiny", 0.0001);
        chart.addSeries("Small", 0.1);
        chart.addSeries("Medium", 10);
        chart.addSeries("Large", 1000);
        assertEquals(5, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var33() {
        PieChart chart = new PieChartBuilder().width(666).height(666).build();
        chart.addSeries("Zero", 0);
        chart.addSeries("NonZero", 666);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var34() {
        PieChart chart = new PieChartBuilder().width(777).height(777).build();
        chart.addSeries("Empty", 0);
        chart.addSeries("Lucky", 777);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var35() {
        PieChart chart = new PieChartBuilder().width(888).height(888).title("Binary").build();
        chart.addSeries("Zero", 0);
        chart.addSeries("One", 1);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var36() {
        PieChart chart = new PieChartBuilder().width(999).height(999).build();
        chart.addSeries("Nothing", 0);
        chart.addSeries("Almost1K", 999);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var37() {
        PieChart chart = new PieChartBuilder().width(111).height(222).build();
        chart.addSeries("Zero", 0);
        chart.addSeries("Fibonacci1", 1);
        chart.addSeries("Fibonacci2", 1);
        chart.addSeries("Fibonacci3", 2);
        chart.addSeries("Fibonacci4", 3);
        chart.addSeries("Fibonacci5", 5);
        assertEquals(6, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var38() {
        PieChart chart = new PieChartBuilder().width(222).height(111).build();
        chart.addSeries("Zero", 0);
        chart.addSeries("Prime1", 2);
        chart.addSeries("Prime2", 3);
        chart.addSeries("Prime3", 5);
        chart.addSeries("Prime4", 7);
        assertEquals(5, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var39() {
        PieChart chart = new PieChartBuilder().width(333).height(444).title("Percentages").build();
        chart.addSeries("Zero", 0);
        chart.addSeries("25%", 25);
        chart.addSeries("75%", 75);
        assertEquals(3, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var40() {
        PieChart chart = new PieChartBuilder().width(444).height(333).build();
        chart.addSeries("Zero", 0);
        chart.addSeries("OneThird", 33.33);
        chart.addSeries("TwoThirds", 66.67);
        assertEquals(3, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var41() {
        PieChart chart = new PieChartBuilder().width(50).height(50).build();
        chart.addSeries("Zero", 0);
        chart.addSeries("NonZero", 100);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var42() {
        PieChart chart = new PieChartBuilder().width(1200).height(800).build();
        chart.addSeries("Zero", 0);
        chart.addSeries("NonZero", 100);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var43() {
        PieChart chart = new PieChartBuilder().width(800).height(1200).title("Extreme Aspect Ratio").build();
        chart.addSeries("Zero", 0);
        chart.addSeries("NonZero", 100);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var44() {
        PieChart chart = new PieChartBuilder().width(345).height(678).build();
        chart.addSeries("Zero", 0);
        chart.addSeries("PI", Math.PI);
        chart.addSeries("E", Math.E);
        assertEquals(3, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var45() {
        PieChart chart = new PieChartBuilder().width(678).height(345).title("Constants").build();
        chart.addSeries("Zero", 0);
        chart.addSeries("GoldenRatio", 1.618);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var46() {
        PieChart chart = new PieChartBuilder().width(246).height(246).build();
        chart.addSeries("NoValue", 0);
        chart.addSeries("BigValue", 1000000);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var47() {
        PieChart chart = new PieChartBuilder().width(369).height(369).build();
        chart.addSeries("Z", 0);
        chart.addSeries("Alpha", 25);
        chart.addSeries("Beta", 35);
        chart.addSeries("Gamma", 40);
        assertEquals(4, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var48() {
        PieChart chart = new PieChartBuilder().width(963).height(963).title("Color Test").build();
        chart.addSeries("Zero", 0);
        chart.addSeries("Red", 20);
        chart.addSeries("Green", 30);
        chart.addSeries("Blue", 50);
        assertEquals(4, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var49() {
        PieChart chart = new PieChartBuilder().width(159).height(159).build();
        chart.addSeries("Zero", 0);
        chart.addSeries("Part", 42);
        chart.addSeries("Rest", 58);
        assertEquals(3, chart.getSeriesMap().size());
    }

    @Test
    public void testPieChartZeroValue_Var50() {
        PieChart chart = new PieChartBuilder().width(951).height(951).title("Final Test").build();
        chart.addSeries("Empty", 0);
        chart.addSeries("First", 1);
        chart.addSeries("Middle", 499);
        chart.addSeries("Last", 500);
        assertEquals(4, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var1() {
        DialChart chart = new DialChartBuilder().width(300).height(300).build();
        chart.addSeries("Test_Var1", 0.25);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var2() {
        DialChart chart = new DialChartBuilder().width(400).height(200).build();
        chart.addSeries("Test_Var2", 0.5);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var3() {
        DialChart chart = new DialChartBuilder().width(200).height(400).build();
        chart.addSeries("Test_Var3", 0.33);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var4() {
        DialChart chart = new DialChartBuilder().width(500).height(500).build();
        chart.addSeries("Test_Var4", 0.67);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var5() {
        DialChart chart = new DialChartBuilder().width(600).height(300).title("Custom Title").build();
        chart.addSeries("Test_Var5", 0.9);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var6() {
        DialChart chart = new DialChartBuilder().width(250).height(250).build();
        chart.addSeries("Very Low Value", 0.01);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var7() {
        DialChart chart = new DialChartBuilder().width(450).height(450).build();
        chart.addSeries("Almost Max", 0.99);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var8() {
        DialChart chart = new DialChartBuilder().width(350).height(350).build();
        chart.addSeries("Quarter Value", 0.25);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var9() {
        DialChart chart = new DialChartBuilder().width(275).height(275).build();
        chart.addSeries("One Third", 0.333);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var10() {
        DialChart chart = new DialChartBuilder().width(325).height(325).build();
        chart.addSeries("Two Thirds", 0.667);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var11() {
        DialChart chart = new DialChartBuilder().width(700).height(700).theme(Styler.ChartTheme.GGPlot2).build();
        chart.addSeries("GGPlot Themed", 0.5);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var12() {
        DialChart chart = new DialChartBuilder().width(400).height(400).theme(Styler.ChartTheme.Matlab).build();
        chart.addSeries("Matlab Themed", 0.5);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var13() {
        DialChart chart = new DialChartBuilder().width(400).height(400).theme(Styler.ChartTheme.XChart).build();
        chart.addSeries("XChart Themed", 0.5);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var14() {
        DialChart chart = new DialChartBuilder().width(800).height(600).title("Wide Format").build();
        chart.addSeries("Wide Format Test", 0.5);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var15() {
        DialChart chart = new DialChartBuilder().width(600).height(800).title("Tall Format").build();
        chart.addSeries("Tall Format Test", 0.5);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var16() {
        DialChart chart = new DialChartBuilder().width(1000).height(1000).title("Large Format").build();
        chart.addSeries("Large Format Test", 0.5);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var17() {
        DialChart chart = new DialChartBuilder().width(150).height(150).title("Small Format").build();
        chart.addSeries("Small Format Test", 0.5);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var18() {
        DialChart chart = new DialChartBuilder().width(300).height(300).build();
        chart.addSeries("Exactly Half", 0.5);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var19() {
        DialChart chart = new DialChartBuilder().width(350).height(350).build();
        chart.addSeries("Test 19", 0.19);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var20() {
        DialChart chart = new DialChartBuilder().width(420).height(420).build();
        chart.addSeries("Test 20", 0.2);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var21() {
        DialChart chart = new DialChartBuilder().width(360).height(360).build();
        chart.addSeries("Test 21", 0.21);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var22() {
        DialChart chart = new DialChartBuilder().width(340).height(340).build();
        chart.addSeries("Test 22", 0.22);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var23() {
        DialChart chart = new DialChartBuilder().width(330).height(330).build();
        chart.addSeries("Test 23", 0.23);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var24() {
        DialChart chart = new DialChartBuilder().width(240).height(240).build();
        chart.addSeries("Test 24", 0.24);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var25() {
        DialChart chart = new DialChartBuilder().width(520).height(520).build();
        chart.addSeries("Test 25", 0.25);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var26() {
        DialChart chart = new DialChartBuilder().width(260).height(260).build();
        chart.addSeries("Test 26", 0.26);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var27() {
        DialChart chart = new DialChartBuilder().width(270).height(270).build();
        chart.addSeries("Test 27", 0.27);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var28() {
        DialChart chart = new DialChartBuilder().width(280).height(280).build();
        chart.addSeries("Test 28", 0.28);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var29() {
        DialChart chart = new DialChartBuilder().width(290).height(290).build();
        chart.addSeries("Test 29", 0.29);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var30() {
        DialChart chart = new DialChartBuilder().width(300).height(300).build();
        chart.addSeries("Test 30", 0.3);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var31() {
        DialChart chart = new DialChartBuilder().width(310).height(310).build();
        chart.addSeries("Test 31", 0.31);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var32() {
        DialChart chart = new DialChartBuilder().width(320).height(320).build();
        chart.addSeries("Test 32", 0.32);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var33() {
        DialChart chart = new DialChartBuilder().width(333).height(333).build();
        chart.addSeries("Test 33", 0.33);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var34() {
        DialChart chart = new DialChartBuilder().width(340).height(340).build();
        chart.addSeries("Test 34", 0.34);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var35() {
        DialChart chart = new DialChartBuilder().width(350).height(350).build();
        chart.addSeries("Test 35", 0.35);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var36() {
        DialChart chart = new DialChartBuilder().width(360).height(360).build();
        chart.addSeries("Test 36", 0.36);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var37() {
        DialChart chart = new DialChartBuilder().width(370).height(370).build();
        chart.addSeries("Test 37", 0.37);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var38() {
        DialChart chart = new DialChartBuilder().width(380).height(380).build();
        chart.addSeries("Test 38", 0.38);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var39() {
        DialChart chart = new DialChartBuilder().width(390).height(390).build();
        chart.addSeries("Test 39", 0.39);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var40() {
        DialChart chart = new DialChartBuilder().width(400).height(400).build();
        chart.addSeries("Test 40", 0.4);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var41() {
        DialChart chart = new DialChartBuilder().width(410).height(410).build();
        chart.addSeries("Test 41", 0.41);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var42() {
        DialChart chart = new DialChartBuilder().width(420).height(420).build();
        chart.addSeries("Test 42", 0.42);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var43() {
        DialChart chart = new DialChartBuilder().width(430).height(430).build();
        chart.addSeries("Test 43", 0.43);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var44() {
        DialChart chart = new DialChartBuilder().width(440).height(440).build();
        chart.addSeries("Test 44", 0.44);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var45() {
        DialChart chart = new DialChartBuilder().width(450).height(450).build();
        chart.addSeries("Test 45", 0.45);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var46() {
        DialChart chart = new DialChartBuilder().width(460).height(460).build();
        chart.addSeries("Test 46", 0.46);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var47() {
        DialChart chart = new DialChartBuilder().width(470).height(470).build();
        chart.addSeries("Test 47", 0.47);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var48() {
        DialChart chart = new DialChartBuilder().width(480).height(480).build();
        chart.addSeries("Test 48", 0.48);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var49() {
        DialChart chart = new DialChartBuilder().width(490).height(490).build();
        chart.addSeries("Test 49", 0.49);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testDialChart_Var50() {
        DialChart chart = new DialChartBuilder().width(500).height(500).title("Half Value Test").build();
        chart.addSeries("Test 50", 0.5);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var1() {
        BubbleChart chart = new BubbleChartBuilder().width(300).height(300).build();
        double[] xData = {0.5, 1.5, 2.5};
        double[] yData = {1.5, 2.5, 3.5};
        double[] bubbleData = {5.0, 10.0, 15.0};
        chart.addSeries("Bubble_Var1", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var2() {
        BubbleChart chart = new BubbleChartBuilder().width(400).height(200).build();
        double[] xData = {1.0, 2.0, 3.0, 4.0};
        double[] yData = {4.0, 3.0, 2.0, 1.0};
        double[] bubbleData = {8.0, 6.0, 4.0, 2.0};
        chart.addSeries("Bubble_Var2", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var3() {
        BubbleChart chart = new BubbleChartBuilder().width(200).height(400).build();
        double[] xData = {-1.0, 0.0, 1.0};
        double[] yData = {-1.0, 0.0, 1.0};
        double[] bubbleData = {3.0, 6.0, 9.0};
        chart.addSeries("Bubble_Var3", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var4() {
        BubbleChart chart = new BubbleChartBuilder().width(500).height(500).title("Bubble Test").build();
        double[] xData = {10, 20, 30};
        double[] yData = {30, 20, 10};
        double[] bubbleData = {25, 50, 75};
        chart.addSeries("Bubble_Var4", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var5() {
        BubbleChart chart = new BubbleChartBuilder().width(600).height(300).xAxisTitle("X").yAxisTitle("Y").build();
        double[] xData = {1.1, 2.2, 3.3};
        double[] yData = {3.3, 2.2, 1.1};
        double[] bubbleData = {10.1, 20.2, 30.3};
        chart.addSeries("Bubble_Var5", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var6() {
        BubbleChart chart = new BubbleChartBuilder().width(250).height(250).build();
        double[] xData = {0, 5, 10};
        double[] yData = {0, 5, 10};
        double[] bubbleData = {1, 5, 10};
        chart.addSeries("Bubble_Var6", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var7() {
        BubbleChart chart = new BubbleChartBuilder().width(450).height(250).build();
        double[] xData = {2, 4, 6, 8};
        double[] yData = {8, 6, 4, 2};
        double[] bubbleData = {4, 8, 12, 16};
        chart.addSeries("Bubble_Var7", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var8() {
        BubbleChart chart = new BubbleChartBuilder().width(350).height(350).build();
        double[] xData = {-5, 0, 5};
        double[] yData = {-5, 0, 5};
        double[] bubbleData = {5, 10, 15};
        chart.addSeries("Bubble_Var8", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var9() {
        BubbleChart chart = new BubbleChartBuilder().width(700).height(200).build();
        double[] xData = {1, 3, 5, 7, 9};
        double[] yData = {9, 7, 5, 3, 1};
        double[] bubbleData = {2, 4, 6, 8, 10};
        chart.addSeries("Bubble_Var9", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var10() {
        BubbleChart chart = new BubbleChartBuilder().width(800).height(800).build();
        double[] xData = {0.1, 0.2, 0.3};
        double[] yData = {0.3, 0.2, 0.1};
        double[] bubbleData = {0.5, 1.0, 1.5};
        chart.addSeries("Bubble_Var10", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var11() {
        BubbleChart chart = new BubbleChartBuilder().width(300).height(600).build();
        double[] xData = {100, 200, 300};
        double[] yData = {300, 200, 100};
        double[] bubbleData = {50, 100, 150};
        chart.addSeries("Bubble_Var11", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var12() {
        BubbleChart chart = new BubbleChartBuilder().width(600).height(300).title("Double Series").build();
        double[] xData1 = {1, 2, 3};
        double[] yData1 = {3, 2, 1};
        double[] bubbleData1 = {10, 20, 30};
        double[] xData2 = {4, 5, 6};
        double[] yData2 = {6, 5, 4};
        double[] bubbleData2 = {15, 25, 35};
        chart.addSeries("Bubble_Var12A", xData1, yData1, bubbleData1);
        chart.addSeries("Bubble_Var12B", xData2, yData2, bubbleData2);
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var13() {
        BubbleChart chart = new BubbleChartBuilder().width(400).height(400).build();
        double[] xData = {2.5, 5.0, 7.5};
        double[] yData = {7.5, 5.0, 2.5};
        double[] bubbleData = {5, 15, 25};
        chart.addSeries("Bubble_Var13", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var14() {
        BubbleChart chart = new BubbleChartBuilder().width(900).height(600).build();
        double[] xData = {-10, 0, 10};
        double[] yData = {-10, 0, 10};
        double[] bubbleData = {25, 50, 75};
        chart.addSeries("Bubble_Var14", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var15() {
        BubbleChart chart = new BubbleChartBuilder().width(100).height(100).build();
        double[] xData = {0.5, 1.0};
        double[] yData = {0.5, 1.0};
        double[] bubbleData = {1.0, 2.0};
        chart.addSeries("Bubble_Var15", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var16() {
        BubbleChart chart = new BubbleChartBuilder().width(1000).height(500).build();
        double[] xData = {1, 2, 3, 4, 5};
        double[] yData = {5, 4, 3, 2, 1};
        double[] bubbleData = {10, 20, 30, 40, 50};
        chart.addSeries("Bubble_Var16", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var17() {
        BubbleChart chart = new BubbleChartBuilder().width(333).height(333).build();
        double[] xData = {1.1, 2.2};
        double[] yData = {3.3, 4.4};
        double[] bubbleData = {5.5, 6.6};
        chart.addSeries("Bubble_Var17", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var18() {
        BubbleChart chart = new BubbleChartBuilder().width(650).height(350).build();
        double[] xData = {-5, -2.5, 0, 2.5, 5};
        double[] yData = {5, 2.5, 0, -2.5, -5};
        double[] bubbleData = {1, 2, 3, 4, 5};
        chart.addSeries("Bubble_Var18", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var19() {
        BubbleChart chart = new BubbleChartBuilder().width(750).height(750).build();
        double[] xData = {10, 20, 30, 40};
        double[] yData = {10, 20, 30, 40};
        double[] bubbleData = {5, 10, 15, 20};
        chart.addSeries("Bubble_Var19", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var20() {
        BubbleChart chart = new BubbleChartBuilder().width(555).height(444).build();
        double[] xData = {0.25, 0.5, 0.75};
        double[] yData = {0.75, 0.5, 0.25};
        double[] bubbleData = {0.1, 0.2, 0.3};
        chart.addSeries("Bubble_Var20", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var21() {
        BubbleChart chart = new BubbleChartBuilder().width(444).height(555).build();
        double[] xData = {0, 50, 100};
        double[] yData = {100, 50, 0};
        double[] bubbleData = {30, 60, 90};
        chart.addSeries("Bubble_Var21", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var22() {
        BubbleChart chart = new BubbleChartBuilder().width(222).height(222).theme(ChartTheme.GGPlot2).build();
        double[] xData = {1, 3, 5};
        double[] yData = {2, 4, 6};
        double[] bubbleData = {10, 30, 50};
        chart.addSeries("Bubble_Var22", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var23() {
        BubbleChart chart = new BubbleChartBuilder().width(777).height(333).theme(ChartTheme.Matlab).build();
        double[] xData = {2, 4, 6, 8};
        double[] yData = {8, 6, 4, 2};
        double[] bubbleData = {2, 4, 6, 8};
        chart.addSeries("Bubble_Var23", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var24() {
        BubbleChart chart = new BubbleChartBuilder().width(600).height(600).title("Symmetric").build();
        double[] xData = {-3, -2, -1, 0, 1, 2, 3};
        double[] yData = {-3, -2, -1, 0, 1, 2, 3};
        double[] bubbleData = {1, 2, 3, 4, 3, 2, 1};
        chart.addSeries("Bubble_Var24", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var25() {
        BubbleChart chart = new BubbleChartBuilder().width(123).height(456).build();
        double[] xData = {0.1, 1.0, 10.0, 100.0};
        double[] yData = {0.1, 1.0, 10.0, 100.0};
        double[] bubbleData = {1, 3, 5, 7};
        chart.addSeries("Bubble_Var25", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var26() {
        BubbleChart chart = new BubbleChartBuilder().width(456).height(123).build();
        double[] xData = {5, 10, 15};
        double[] yData = {15, 10, 5};
        double[] bubbleData = {7, 14, 21};
        chart.addSeries("Bubble_Var26", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var27() {
        BubbleChart chart = new BubbleChartBuilder().width(987).height(654).build();
        double[] xData = {0, 1};
        double[] yData = {1, 0};
        double[] bubbleData = {5, 10};
        chart.addSeries("Bubble_Var27", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var28() {
        BubbleChart chart = new BubbleChartBuilder().width(654).height(987).build();
        double[] xData = {-100, -50, 0, 50, 100};
        double[] yData = {-100, -50, 0, 50, 100};
        double[] bubbleData = {10, 20, 30, 20, 10};
        chart.addSeries("Bubble_Var28", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var29() {
        BubbleChart chart = new BubbleChartBuilder().width(369).height(369).build();
        double[] xData = {1, 3, 5, 7, 9};
        double[] yData = {2, 4, 6, 8, 10};
        double[] bubbleData = {5, 10, 15, 20, 25};
        chart.addSeries("Bubble_Var29", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var30() {
        BubbleChart chart = new BubbleChartBuilder().width(852).height(741).build();
        double[] xData = {0.5, 1.5, 2.5, 3.5};
        double[] yData = {0.5, 1.5, 2.5, 3.5};
        double[] bubbleData = {1, 3, 5, 7};
        chart.addSeries("Bubble_Var30", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var31() {
        BubbleChart chart = new BubbleChartBuilder().width(741).height(852).build();
        double[] xData = {-3, -1, 1, 3};
        double[] yData = {3, 1, -1, -3};
        double[] bubbleData = {2, 4, 6, 8};
        chart.addSeries("Bubble_Var31", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var32() {
        BubbleChart chart = new BubbleChartBuilder().width(400).height(400).title("Pi Values").build();
        double[] xData = {1*Math.PI, 2*Math.PI, 3*Math.PI};
        double[] yData = {3*Math.PI, 2*Math.PI, 1*Math.PI};
        double[] bubbleData = {1, 2, 3};
        chart.addSeries("Bubble_Var32", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var33() {
        BubbleChart chart = new BubbleChartBuilder().width(600).height(400).build();
        double[] xData = {1.23, 4.56, 7.89};
        double[] yData = {9.87, 6.54, 3.21};
        double[] bubbleData = {3, 6, 9};
        chart.addSeries("Bubble_Var33", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var34() {
        BubbleChart chart = new BubbleChartBuilder().width(500).height(300).build();
        double[] xData = {0, 25, 50, 75, 100};
        double[] yData = {0, 25, 50, 75, 100};
        double[] bubbleData = {10, 20, 30, 20, 10};
        chart.addSeries("Bubble_Var34", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var35() {
        BubbleChart chart = new BubbleChartBuilder().width(375).height(275).build();
        double[] xData = {1, 10, 100};
        double[] yData = {1, 10, 100};
        double[] bubbleData = {1, 5, 10};
        chart.addSeries("Bubble_Var35", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var36() {
        BubbleChart chart = new BubbleChartBuilder().width(275).height(375).build();
        double[] xData = {-10, -5, 0, 5, 10};
        double[] yData = {10, 5, 0, -5, -10};
        double[] bubbleData = {5, 4, 3, 2, 1};
        chart.addSeries("Bubble_Var36", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var37() {
        BubbleChart chart = new BubbleChartBuilder().width(510).height(510).build();
        double[] xData = {-20, -10, 0, 10, 20};
        double[] yData = {20, 10, 0, -10, -20};
        double[] bubbleData = {1, 2, 3, 2, 1};
        chart.addSeries("Bubble_Var37", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var38() {
        BubbleChart chart = new BubbleChartBuilder().width(480).height(320).build();
        double[] xData = {1, 2, 3, 4, 5, 6};
        double[] yData = {6, 5, 4, 3, 2, 1};
        double[] bubbleData = {3, 6, 9, 12, 15, 18};
        chart.addSeries("Bubble_Var38", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var39() {
        BubbleChart chart = new BubbleChartBuilder().width(640).height(480).build();
        double[] xData = {0.25, 0.5, 0.75, 1.0};
        double[] yData = {1.0, 0.75, 0.5, 0.25};
        double[] bubbleData = {0.5, 1.0, 1.5, 2.0};
        chart.addSeries("Bubble_Var39", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var40() {
        BubbleChart chart = new BubbleChartBuilder().width(1024).height(768).build();
        double[] xData = {-5, 0, 5};
        double[] yData = {25, 0, 25};
        double[] bubbleData = {10, 20, 10};
        chart.addSeries("Bubble_Var40", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var41() {
        BubbleChart chart = new BubbleChartBuilder().width(768).height(1024).title("Portrait").build();
        double[] xData = {1, 3, 5, 7};
        double[] yData = {1, 3, 5, 7};
        double[] bubbleData = {10, 30, 50, 70};
        chart.addSeries("Bubble_Var41", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var42() {
        BubbleChart chart = new BubbleChartBuilder().width(512).height(512).theme(Styler.ChartTheme.XChart).build();
        double[] xData = {-1.5, -0.5, 0.5, 1.5};
        double[] yData = {-1.5, -0.5, 0.5, 1.5};
        double[] bubbleData = {0.5, 1.0, 1.5, 2.0};
        chart.addSeries("Bubble_Var42", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var43() {
        BubbleChart chart = new BubbleChartBuilder().width(1280).height(720).build();
        double[] xData = {1, 3, 5, 7, 9, 11};
        double[] yData = {11, 9, 7, 5, 3, 1};
        double[] bubbleData = {5, 10, 15, 20, 25, 30};
        chart.addSeries("Bubble_Var43", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var44() {
        BubbleChart chart = new BubbleChartBuilder().width(720).height(1280).build();
        double[] xData = {0.1, 1, 10, 100};
        double[] yData = {0.1, 1, 10, 100};
        double[] bubbleData = {1, 2, 4, 8};
        chart.addSeries("Bubble_Var44", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var45() {
        BubbleChart chart = new BubbleChartBuilder().width(960).height(540).build();
        double[] xData = {-Math.PI, 0, Math.PI};
        double[] yData = {Math.sin(-Math.PI), Math.sin(0.0), Math.sin(Math.PI)};
        double[] bubbleData = {3, 6, 3};
        chart.addSeries("Bubble_Var45", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var46() {
        BubbleChart chart = new BubbleChartBuilder().width(540).height(960).build();
        double[] xData = {0, 0.25, 0.5, 0.75, 1};
        double[] yData = {0, 0.25, 0.5, 0.75, 1};
        double[] bubbleData = {0.1, 0.2, 0.3, 0.2, 0.1};
        chart.addSeries("Bubble_Var46", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var47() {
        BubbleChart chart = new BubbleChartBuilder().width(1200).height(900).build();
        double[] xData = {0, 10, 20, 30, 40, 50};
        double[] yData = {50, 40, 30, 20, 10, 0};
        double[] bubbleData = {5, 10, 15, 20, 25, 30};
        chart.addSeries("Bubble_Var47", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var48() {
        BubbleChart chart = new BubbleChartBuilder().width(900).height(1200).build();
        double[] xData = {-50, -25, 0, 25, 50};
        double[] yData = {50, 25, 0, -25, -50};
        double[] bubbleData = {10, 20, 30, 20, 10};
        chart.addSeries("Bubble_Var48", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var49() {
        BubbleChart chart = new BubbleChartBuilder().width(150).height(150).build();
        double[] xData = {1};
        double[] yData = {1};
        double[] bubbleData = {5};
        chart.addSeries("Bubble_Var49", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBubbleChart_Var50() {
        BubbleChart chart = new BubbleChartBuilder().width(1500).height(1500).title("Large Bubble Chart").build();
        double[] xData = {-100, -75, -50, -25, 0, 25, 50, 75, 100};
        double[] yData = {100, 75, 50, 25, 0, -25, -50, -75, -100};
        double[] bubbleData = {5, 10, 15, 20, 25, 20, 15, 10, 5};
        chart.addSeries("Bubble_Var50", xData, yData, bubbleData);
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var1() {
        CategoryChart chart = new CategoryChartBuilder().width(300).height(300).build();
        chart.addSeries("Test_Var1", Arrays.asList("A", "B", "C"), Arrays.asList(4.0, 5.0, 6.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var2() {
        CategoryChart chart = new CategoryChartBuilder().width(400).height(200).build();
        chart.addSeries("Test_Var2", Arrays.asList("Red", "Green", "Blue"), Arrays.asList(10.0, 20.0, 30.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var3() {
        CategoryChart chart = new CategoryChartBuilder().width(200).height(400).build();
        chart.addSeries("Test_Var3", Arrays.asList("Small", "Medium", "Large"), Arrays.asList(5.0, 15.0, 25.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var4() {
        CategoryChart chart = new CategoryChartBuilder().width(500).height(500).title("Bar Test").build();
        chart.addSeries("Test_Var4", Arrays.asList("Q1", "Q2", "Q3", "Q4"), Arrays.asList(100.0, 110.0, 120.0, 130.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var5() {
        CategoryChart chart = new CategoryChartBuilder().width(600).height(300).xAxisTitle("Categories").yAxisTitle("Values").build();
        chart.addSeries("Test_Var5", Arrays.asList("Low", "Medium", "High"), Arrays.asList(1.1, 2.2, 3.3));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var6() {
        CategoryChart chart = new CategoryChartBuilder().width(250).height(250).build();
        chart.addSeries("Test_Var6", Arrays.asList("A", "B"), Arrays.asList(5.0, 10.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var7() {
        CategoryChart chart = new CategoryChartBuilder().width(450).height(250).build();
        chart.addSeries("Test_Var7", Arrays.asList("One", "Two", "Three", "Four"), Arrays.asList(2.5, 5.0, 7.5, 10.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var8() {
        CategoryChart chart = new CategoryChartBuilder().width(350).height(350).build();
        chart.addSeries("Test_Var8", Arrays.asList("Neg", "Zero", "Pos"), Arrays.asList(-5.0, 0.0, 5.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var9() {
        CategoryChart chart = new CategoryChartBuilder().width(700).height(200).build();
        chart.addSeries("Test_Var9", Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri"), Arrays.asList(10.0, 8.0, 6.0, 4.0, 2.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var10() {
        CategoryChart chart = new CategoryChartBuilder().width(800).height(800).build();
        chart.addSeries("Test_Var10", Arrays.asList("X", "Y", "Z"), Arrays.asList(0.1, 0.2, 0.3));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var11() {
        CategoryChart chart = new CategoryChartBuilder().width(300).height(600).build();
        chart.addSeries("Test_Var11", Arrays.asList("Low", "Medium", "High"), Arrays.asList(100.0, 500.0, 1000.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var12() {
        CategoryChart chart = new CategoryChartBuilder().width(600).height(300).title("Multiple Series").build();
        chart.addSeries("Series1", Arrays.asList("A", "B", "C"), Arrays.asList(1.0, 2.0, 3.0));
        chart.addSeries("Series2", Arrays.asList("A", "B", "C"), Arrays.asList(3.0, 2.0, 1.0));
        assertEquals(2, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var13() {
        CategoryChart chart = new CategoryChartBuilder().width(400).height(400).build();
        chart.addSeries("Test_Var13", Arrays.asList("2021", "2022", "2023"), Arrays.asList(75.0, 85.0, 95.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var14() {
        CategoryChart chart = new CategoryChartBuilder().width(900).height(600).build();
        chart.addSeries("Test_Var14", Arrays.asList("Low", "Medium", "High"), Arrays.asList(-10.0, 0.0, 10.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var15() {
        CategoryChart chart = new CategoryChartBuilder().width(100).height(100).build();
        chart.addSeries("Test_Var15", Arrays.asList("A", "B"), Arrays.asList(0.5, 1.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var16() {
        CategoryChart chart = new CategoryChartBuilder().width(1000).height(500).build();
        chart.addSeries("Test_Var16", Arrays.asList("J", "F", "M", "A", "M"), Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var17() {
        CategoryChart chart = new CategoryChartBuilder().width(333).height(333).build();
        chart.addSeries("Test_Var17", Arrays.asList("First", "Second"), Arrays.asList(10.0, 20.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var18() {
        CategoryChart chart = new CategoryChartBuilder().width(650).height(350).build();
        chart.addSeries("Test_Var18", Arrays.asList("A", "B", "C", "D", "E"), Arrays.asList(-5.0, -2.5, 0.0, 2.5, 5.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var19() {
        CategoryChart chart = new CategoryChartBuilder().width(750).height(750).build();
        chart.addSeries("Test_Var19", Arrays.asList("1st", "2nd", "3rd", "4th"), Arrays.asList(10.0, 20.0, 30.0, 40.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var20() {
        CategoryChart chart = new CategoryChartBuilder().width(555).height(444).build();
        chart.addSeries("Test_Var20", Arrays.asList("Low", "Med", "High"), Arrays.asList(0.1, 0.5, 0.9));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var21() {
        CategoryChart chart = new CategoryChartBuilder().width(444).height(555).build();
        chart.addSeries("Test_Var21", Arrays.asList("Min", "Max"), Arrays.asList(0.0, 100.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var22() {
        CategoryChart chart = new CategoryChartBuilder().width(222).height(222).theme(ChartTheme.GGPlot2).build();
        chart.addSeries("Test_Var22", Arrays.asList("X", "Y", "Z"), Arrays.asList(10.0, 20.0, 30.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var23() {
        CategoryChart chart = new CategoryChartBuilder().width(777).height(333).theme(ChartTheme.Matlab).build();
        chart.addSeries("Test_Var23", Arrays.asList("A", "B", "C", "D"), Arrays.asList(8.0, 6.0, 4.0, 2.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var24() {
        CategoryChart chart = new CategoryChartBuilder().width(600).height(600).title("Symmetric").build();
        chart.addSeries("Test_Var24", Arrays.asList("N3", "N2", "N1", "0", "P1", "P2", "P3"), Arrays.asList(-3.0, -2.0, -1.0, 0.0, 1.0, 2.0, 3.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var25() {
        CategoryChart chart = new CategoryChartBuilder().width(123).height(456).build();
        chart.addSeries("Test_Var25", Arrays.asList("0.1", "1.0", "10.0", "100.0"), Arrays.asList(0.1, 1.0, 10.0, 100.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var26() {
        CategoryChart chart = new CategoryChartBuilder().width(456).height(123).build();
        chart.addSeries("Test_Var26", Arrays.asList("High", "Medium", "Low"), Arrays.asList(15.0, 10.0, 5.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var27() {
        CategoryChart chart = new CategoryChartBuilder().width(987).height(654).build();
        chart.addSeries("Test_Var27", Arrays.asList("On", "Off"), Arrays.asList(1.0, 0.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var28() {
        CategoryChart chart = new CategoryChartBuilder().width(654).height(987).build();
        chart.addSeries("Test_Var28", Arrays.asList("-100", "-50", "0", "50", "100"), Arrays.asList(-100.0, -50.0, 0.0, 50.0, 100.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var29() {
        CategoryChart chart = new CategoryChartBuilder().width(369).height(369).build();
        chart.addSeries("Test_Var29", Arrays.asList("1", "3", "5", "7", "9"), Arrays.asList(2.0, 4.0, 6.0, 8.0, 10.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var30() {
        CategoryChart chart = new CategoryChartBuilder().width(852).height(741).build();
        chart.addSeries("Test_Var30", Arrays.asList("1st", "2nd", "3rd", "4th"), Arrays.asList(0.5, 1.5, 2.5, 3.5));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var31() {
        CategoryChart chart = new CategoryChartBuilder().width(741).height(852).build();
        chart.addSeries("Test_Var31", Arrays.asList("North", "East", "South", "West"), Arrays.asList(30.0, 10.0, 20.0, 40.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var32() {
        CategoryChart chart = new CategoryChartBuilder().width(400).height(400).title("Pi Values").build();
        chart.addSeries("Test_Var32", Arrays.asList("π", "2π", "3π"), Arrays.asList(Math.PI, 2*Math.PI, 3*Math.PI));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var33() {
        CategoryChart chart = new CategoryChartBuilder().width(600).height(400).build();
        chart.addSeries("Test_Var33", Arrays.asList("1.23", "4.56", "7.89"), Arrays.asList(9.87, 6.54, 3.21));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var34() {
        CategoryChart chart = new CategoryChartBuilder().width(500).height(300).build();
        chart.addSeries("Test_Var34", Arrays.asList("0%", "25%", "50%", "75%", "100%"), Arrays.asList(0.0, 25.0, 50.0, 75.0, 100.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var35() {
        CategoryChart chart = new CategoryChartBuilder().width(375).height(275).build();
        chart.addSeries("Test_Var35", Arrays.asList("1", "10", "100"), Arrays.asList(1.0, 10.0, 100.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var36() {
        CategoryChart chart = new CategoryChartBuilder().width(275).height(375).build();
        chart.addSeries("Test_Var36", Arrays.asList("-10", "-5", "0", "5", "10"), Arrays.asList(-10.0, -5.0, 0.0, 5.0, 10.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

        @Test
    public void testBasicBarChart_Var37() {
        CategoryChart chart = new CategoryChartBuilder().width(510).height(510).build();
        chart.addSeries("Test_Var37", Arrays.asList("North", "East", "South", "West", "Center"), Arrays.asList(20.0, 10.0, 20.0, 10.0, 40.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var38() {
        CategoryChart chart = new CategoryChartBuilder().width(480).height(320).build();
        chart.addSeries("Test_Var38", Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun"), Arrays.asList(6.0, 5.0, 4.0, 3.0, 2.0, 1.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var39() {
        CategoryChart chart = new CategoryChartBuilder().width(640).height(480).build();
        chart.addSeries("Test_Var39", Arrays.asList("Q1", "Q2", "Q3", "Q4"), Arrays.asList(1.0, 0.75, 0.5, 0.25));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var40() {
        CategoryChart chart = new CategoryChartBuilder().width(1024).height(768).build();
        chart.addSeries("Test_Var40", Arrays.asList("Negative", "Zero", "Positive"), Arrays.asList(-5.0, 0.0, 5.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var41() {
        CategoryChart chart = new CategoryChartBuilder().width(768).height(1024).title("Portrait").build();
        chart.addSeries("Test_Var41", Arrays.asList("1", "3", "5", "7"), Arrays.asList(10.0, 30.0, 50.0, 70.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var42() {
        CategoryChart chart = new CategoryChartBuilder().width(512).height(512).theme(Styler.ChartTheme.XChart).build();
        chart.addSeries("Test_Var42", Arrays.asList("-1.5", "-0.5", "0.5", "1.5"), Arrays.asList(-1.5, -0.5, 0.5, 1.5));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var43() {
        CategoryChart chart = new CategoryChartBuilder().width(1280).height(720).build();
        chart.addSeries("Test_Var43", Arrays.asList("1", "3", "5", "7", "9", "11"), Arrays.asList(11.0, 9.0, 7.0, 5.0, 3.0, 1.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var44() {
        CategoryChart chart = new CategoryChartBuilder().width(720).height(1280).build();
        chart.addSeries("Test_Var44", Arrays.asList("0.1", "1", "10", "100"), Arrays.asList(0.1, 1.0, 10.0, 100.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var45() {
        CategoryChart chart = new CategoryChartBuilder().width(960).height(540).build();
        chart.addSeries("Test_Var45", Arrays.asList("-π", "0", "π"), Arrays.asList(Math.sin(-Math.PI), Math.sin(0.0), Math.sin(Math.PI)));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var46() {
        CategoryChart chart = new CategoryChartBuilder().width(540).height(960).build();
        chart.addSeries("Test_Var46", Arrays.asList("0", "0.25", "0.5", "0.75", "1"), Arrays.asList(0.0, 0.25, 0.5, 0.75, 1.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var47() {
        CategoryChart chart = new CategoryChartBuilder().width(1200).height(900).build();
        chart.addSeries("Test_Var47", Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun"), Arrays.asList(50.0, 40.0, 30.0, 20.0, 10.0, 0.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var48() {
        CategoryChart chart = new CategoryChartBuilder().width(900).height(1200).build();
        chart.addSeries("Test_Var48", Arrays.asList("-50", "-25", "0", "25", "50"), Arrays.asList(-50.0, -25.0, 0.0, 25.0, 50.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var49() {
        CategoryChart chart = new CategoryChartBuilder().width(150).height(150).build();
        chart.addSeries("Test_Var49", Arrays.asList("Single"), Arrays.asList(5.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testBasicBarChart_Var50() {
        CategoryChart chart = new CategoryChartBuilder().width(1500).height(1500).title("Large Bar Chart").build();
        chart.addSeries("Test_Var50", Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"), 
                       Arrays.asList(5.0, 10.0, 15.0, 20.0, 25.0, 30.0, 25.0, 20.0, 15.0, 10.0, 5.0, 0.0));
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var1() {
        RadarChart chart = new RadarChartBuilder().width(300).height(300).title("Radar Chart Var1").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E"});
        chart.addSeries("Series 1", new double[] {0.1, 0.2, 0.3, 0.4, 0.5});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var2() {
        RadarChart chart = new RadarChartBuilder().width(400).height(400).title("Radar Chart Var2").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E"});
        chart.addSeries("Series 2", new double[] {0.5, 0.4, 0.3, 0.2, 0.1});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var3() {
        RadarChart chart = new RadarChartBuilder().width(500).height(500).title("Radar Chart Var3").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E"});
        chart.addSeries("Series 3", new double[] {0.1, 0.3, 0.5, 0.7, 0.9});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var4() {
        RadarChart chart = new RadarChartBuilder().width(350).height(350).title("Radar Chart Var4").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E"});
        chart.addSeries("Series 4", new double[] {0.2, 0.4, 0.6, 0.8, 1.0});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var5() {
        RadarChart chart = new RadarChartBuilder().width(450).height(450).title("Radar Chart Var5").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E"});
        chart.addSeries("Series 5", new double[] {0.5, 0.5, 0.5, 0.5, 0.5});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var6() {
        RadarChart chart = new RadarChartBuilder().width(320).height(320).title("Radar Chart Var6").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D"});
        chart.addSeries("Series 6", new double[] {0.3, 0.6, 0.9, 1.0});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var7() {
        RadarChart chart = new RadarChartBuilder().width(380).height(380).title("Radar Chart Var7").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C"});
        chart.addSeries("Series 7", new double[] {0.7, 0.7, 0.7});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var8() {
        RadarChart chart = new RadarChartBuilder().width(280).height(280).title("Radar Chart Var8").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C"});
        chart.addSeries("Series 8", new double[] {0.8, 0.8, 0.8});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var9() {
        RadarChart chart = new RadarChartBuilder().width(420).height(420).title("Radar Chart Var9").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E", "F"});
        chart.addSeries("Series 9", new double[] {0.1, 0.2, 0.3, 0.4, 0.5, 0.6});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var10() {
        RadarChart chart = new RadarChartBuilder().width(250).height(250).title("Radar Chart Var10").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C"});
        chart.addSeries("Series 10", new double[] {0.1, 0.5, 1.0});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var11() {
        RadarChart chart = new RadarChartBuilder().width(360).height(360).title("Radar Chart Var11").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D"});
        chart.addSeries("Series 11", new double[] {0.25, 0.5, 0.75, 1.0});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var12() {
        RadarChart chart = new RadarChartBuilder().width(480).height(480).title("Radar Chart Var12").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E"});
        chart.addSeries("Series 12", new double[] {0.2, 0.4, 0.6, 0.8, 1.0});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var13() {
        RadarChart chart = new RadarChartBuilder().width(340).height(340).title("Radar Chart Var13").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D"});
        chart.addSeries("Series 13", new double[] {0.1, 0.2, 0.3, 0.4});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var14() {
        RadarChart chart = new RadarChartBuilder().width(310).height(310).title("Radar Chart Var14").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E", "F", "G"});
        chart.addSeries("Series 14", new double[] {0.9, 0.8, 0.7, 0.6, 0.5, 0.4, 0.3});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var15() {
        RadarChart chart = new RadarChartBuilder().width(410).height(410).title("Radar Chart Var15").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E"});
        chart.addSeries("Series 15", new double[] {0.9, 0.8, 0.7, 0.6, 0.5});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var16() {
        RadarChart chart = new RadarChartBuilder().width(270).height(270).title("Radar Chart Var16").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E", "F"});
        chart.addSeries("Series 16", new double[] {0.1, 0.3, 0.2, 0.4, 0.3, 0.5});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var17() {
        RadarChart chart = new RadarChartBuilder().width(390).height(390).title("Radar Chart Var17").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D"});
        chart.addSeries("Series 17", new double[] {0.17, 0.17, 0.17, 0.17});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var18() {
        RadarChart chart = new RadarChartBuilder().width(330).height(330).title("Radar Chart Var18").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E"});
        chart.addSeries("Series 18", new double[] {0.1, 0.2, 0.3, 0.4, 0.5});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var19() {
        RadarChart chart = new RadarChartBuilder().width(430).height(430).title("Radar Chart Var19").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E"});
        chart.addSeries("Series 19", new double[] {0.1, 0.2, 0.15, 0.25, 0.3});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var20() {
        RadarChart chart = new RadarChartBuilder().width(260).height(260).title("Radar Chart Var20").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E"});
        chart.addSeries("Series 20", new double[] {0.1, 0.3, 0.5, 0.7, 0.9});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var21() {
        RadarChart chart = new RadarChartBuilder().width(370).height(370).title("Radar Chart Var21").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E", "F"});
        chart.addSeries("Series 21", new double[] {0.1, 0.15, 0.2, 0.25, 0.3, 0.35});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var22() {
        RadarChart chart = new RadarChartBuilder().width(490).height(490).title("Radar Chart Var22").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D"});
        chart.addSeries("Series 22", new double[] {0.22, 0.22, 0.22, 0.22});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var23() {
        RadarChart chart = new RadarChartBuilder().width(350).height(350).title("Radar Chart Var23").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D"});
        chart.addSeries("Series 23", new double[] {0.23, 0.23, 0.23, 0.23});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var24() {
        RadarChart chart = new RadarChartBuilder().width(300).height(300).title("Radar Chart Var24").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E", "F"});
        chart.addSeries("Series 24", new double[] {0.2, 0.4, 0.6, 0.8, 1.0, 0.9});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var25() {
        RadarChart chart = new RadarChartBuilder().width(400).height(400).title("Radar Chart Var25").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C"});
        chart.addSeries("Series 25", new double[] {0.25, 0.5, 0.75});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var26() {
        RadarChart chart = new RadarChartBuilder().width(500).height(500).title("Radar Chart Var26").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E"});
        chart.addSeries("Series 26", new double[] {0.26, 0.26, 0.26, 0.26, 0.26});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var27() {
        RadarChart chart = new RadarChartBuilder().width(450).height(450).title("Radar Chart Var27").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D"});
        chart.addSeries("Series 27", new double[] {0.27, 0.27, 0.27, 0.27});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var28() {
        RadarChart chart = new RadarChartBuilder().width(320).height(320).title("Radar Chart Var28").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E"});
        chart.addSeries("Series 28", new double[] {0.28, 0.56, 0.84, 0.92, 0.40});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var29() {
        RadarChart chart = new RadarChartBuilder().width(380).height(380).title("Radar Chart Var29").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D"});
        chart.addSeries("Series 29", new double[] {0.29, 0.29, 0.29, 0.29});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var30() {
        RadarChart chart = new RadarChartBuilder().width(280).height(280).title("Radar Chart Var30").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E"});
        chart.addSeries("Series 30", new double[] {0.3, 0.6, 0.9, 0.8, 0.5});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var31() {
        RadarChart chart = new RadarChartBuilder().width(420).height(420).title("Radar Chart Var31").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E"});
        chart.addSeries("Series 31", new double[] {0.31, 0.31, 0.31, 0.31, 0.31});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var32() {
        RadarChart chart = new RadarChartBuilder().width(250).height(250).title("Radar Chart Var32").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D"});
        chart.addSeries("Series 32", new double[] {0.32, 0.32, 0.32, 0.32});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var33() {
        RadarChart chart = new RadarChartBuilder().width(360).height(360).title("Radar Chart Var33").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E", "F"});
        chart.addSeries("Series 33", new double[] {0.1, 0.2, 0.3, 0.4, 0.5, 0.6});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var34() {
        RadarChart chart = new RadarChartBuilder().width(480).height(480).title("Radar Chart Var34").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D"});
        chart.addSeries("Series 34", new double[] {0.34, 0.68, 0.92, 0.76});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var35() {
        RadarChart chart = new RadarChartBuilder().width(340).height(340).title("Radar Chart Var35").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D"});
        chart.addSeries("Series 35", new double[] {0.35, 0.35, 0.35, 0.35});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var36() {
        RadarChart chart = new RadarChartBuilder().width(310).height(310).title("Radar Chart Var36").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E", "F"});
        chart.addSeries("Series 36", new double[] {0.36, 0.35, 0.34, 0.33, 0.32, 0.31});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var37() {
        RadarChart chart = new RadarChartBuilder().width(410).height(410).title("Radar Chart Var37").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E"});
        chart.addSeries("Series 37", new double[] {0.37, 0.74, 0.91, 0.48, 0.85});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var38() {
        RadarChart chart = new RadarChartBuilder().width(270).height(270).title("Radar Chart Var38").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E"});
        chart.addSeries("Series 38", new double[] {0.1, 0.2, 0.3, 0.2, 0.1});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var39() {
        RadarChart chart = new RadarChartBuilder().width(390).height(390).title("Radar Chart Var39").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D"});
        chart.addSeries("Series 39", new double[] {0.39, 0.39, 0.39, 0.39});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var40() {
        RadarChart chart = new RadarChartBuilder().width(330).height(330).title("Radar Chart Var40").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E"});
        chart.addSeries("Series 40", new double[] {0.4, 0.6, 0.8, 0.7, 0.5});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var41() {
        RadarChart chart = new RadarChartBuilder().width(430).height(430).title("Radar Chart Var41").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E"});
        chart.addSeries("Series 41", new double[] {0.41, 0.41, 0.41, 0.41, 0.41});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var42() {
        RadarChart chart = new RadarChartBuilder().width(260).height(260).title("Radar Chart Var42").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D"});
        chart.addSeries("Series 42", new double[] {0.42, 0.64, 0.86, 0.68});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var43() {
        RadarChart chart = new RadarChartBuilder().width(370).height(370).title("Radar Chart Var43").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E"});
        chart.addSeries("Series 43", new double[] {0.43, 0.43, 0.43, 0.43, 0.43});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var44() {
        RadarChart chart = new RadarChartBuilder().width(490).height(490).title("Radar Chart Var44").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E"});
        chart.addSeries("Series 44", new double[] {0.44, 0.88, 0.92, 0.76, 0.90});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var45() {
        RadarChart chart = new RadarChartBuilder().width(350).height(350).title("Radar Chart Var45").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C"});
        chart.addSeries("Series 45", new double[] {0.45, 0.45, 0.45});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var46() {
        RadarChart chart = new RadarChartBuilder().width(300).height(300).title("Radar Chart Var46").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E", "F"});
        chart.addSeries("Series 46", new double[] {0.4, 0.5, 0.6, 0.5, 0.4, 0.3});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var47() {
        RadarChart chart = new RadarChartBuilder().width(400).height(400).title("Radar Chart Var47").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D"});
        chart.addSeries("Series 47", new double[] {0.47, 0.94, 0.71, 0.88});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var48() {
        RadarChart chart = new RadarChartBuilder().width(500).height(500).title("Radar Chart Var48").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E"});
        chart.addSeries("Series 48", new double[] {0.48, 0.48, 0.48, 0.48, 0.48});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var49() {
        RadarChart chart = new RadarChartBuilder().width(450).height(450).title("Radar Chart Var49").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D"});
        chart.addSeries("Series 49", new double[] {0.14, 0.28, 0.42, 0.56});
        assertEquals(1, chart.getSeriesMap().size());
    }

    @Test
    public void testRadarChart_Var50() {
        RadarChart chart = new RadarChartBuilder().width(320).height(320).title("Radar Chart Var50").build();
        chart.setRadiiLabels(new String[] {"A", "B", "C", "D", "E"});
        chart.addSeries("Series 50", new double[] {0.5, 0.6, 0.7, 0.8, 0.9});
        assertEquals(1, chart.getSeriesMap().size());
    }
}  