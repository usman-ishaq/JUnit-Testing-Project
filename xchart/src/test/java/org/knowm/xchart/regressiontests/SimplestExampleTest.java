package org.knowm.xchart.regressiontests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import org.junit.jupiter.api.Disabled;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XYChart;

// Disabled because I don't have example chart pngs from all OSes
@Disabled
public class SimplestExampleTest {

  @Disabled
  public void testSimplestExampleStaysTheSame() throws Exception {
    // given
    double[] xData = new double[] {0.0, 1.0, 2.0};
    double[] yData = new double[] {2.0, 1.0, 0.0};

    // when
    XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
    chart.getStyler().setChartTitleFont(arial());
    chart.getStyler().setAxisTickLabelsFont(arial());
    chart.getStyler().setLegendFont(arial());
    chart.getStyler().setAxisTitleFont(arial());
    DigestOutputStream output =
        new DigestOutputStream(new ByteArrayOutputStream(), MessageDigest.getInstance(digestType));
    BitmapEncoder.saveBitmap(chart, output, BitmapEncoder.BitmapFormat.PNG);
    output.close();

    // test
    assertImagesEquals("simplestExample.png", output);
  }

  static final String digestType = "md5";

  private Font arial() {
    return new Font("Arial", Font.PLAIN, 14);
  }

  public void assertImagesEquals(String expectedFileName, DigestOutputStream actual)
      throws Exception {
    String path =
        "/expectedChartRenderings/"
            + System.getProperty("os.name").replaceAll(" ", "")
            + "/"
            + expectedFileName;
    byte[] expectedBytes = Files.readAllBytes(Paths.get(getClass().getResource(path).toURI()));
    byte[] expectedDigest = MessageDigest.getInstance(digestType).digest(expectedBytes);
    byte[] actualDigest = actual.getMessageDigest().digest();

    assertArrayEquals(expectedDigest, actualDigest);
  }
}
