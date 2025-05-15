package org.knowm.xchart.customtests;

import org.junit.jupiter.api.Test;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import static org.junit.jupiter.api.Assertions.*;

public class BasicChartTest {
    
    @Test
    public void testBasicChartCreation() {
        // Create Chart
        XYChart chart = new XYChartBuilder()
            .width(800)
            .height(600)
            .title("Basic Chart Test")
            .xAxisTitle("X")
            .yAxisTitle("Y")
            .build();
        
        assertNotNull(chart, "Chart should be created successfully");
        assertEquals("Basic Chart Test", chart.getTitle(), "Chart title should match");
        assertEquals("X", chart.getXAxisTitle(), "X axis title should match");
        assertEquals("Y", chart.getYAxisTitle(), "Y axis title should match");
    }
} 