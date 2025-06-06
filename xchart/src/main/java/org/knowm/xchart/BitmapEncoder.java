package org.knowm.xchart;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.FileImageOutputStream;
import org.knowm.xchart.internal.chartpart.Chart;

/** A helper class with static methods for saving Charts as bitmaps */
public final class BitmapEncoder {

  /** Constructor - Private constructor to prevent instantiation */
  private BitmapEncoder() {}

  /**
   * Only adds the extension of the BitmapFormat to the filename if the filename doesn't already
   * have it.
   *
   * @param fileName
   * @param bitmapFormat
   * @return filename (if extension already exists), otherwise;: filename + "." + extension
   */
  public static String addFileExtension(String fileName, BitmapFormat bitmapFormat) {

    final String newFileExtension = "." + bitmapFormat.toString().toLowerCase();
    final String fileNameWithFileExtension;
    if (fileName.length() < newFileExtension.length()
        || !fileName
            .substring(fileName.length() - newFileExtension.length())
            .equalsIgnoreCase(newFileExtension)) {
      fileNameWithFileExtension = fileName + newFileExtension;
    } else {
      // This is to ensure the lower-case for the extension
      fileNameWithFileExtension =
          fileName.substring(0, fileName.length() - newFileExtension.length()) + newFileExtension;
    }
    return fileNameWithFileExtension;
  }

  /**
   * Save a Chart as an image file
   *
   * @param chart
   * @param fileName
   * @param bitmapFormat
   * @throws IOException
   */
  public static <T extends Chart<?, ?>> void saveBitmap(
      T chart, String fileName, BitmapFormat bitmapFormat) throws IOException {

    try (OutputStream out = new FileOutputStream(addFileExtension(fileName, bitmapFormat))) {
      saveBitmap(chart, out, bitmapFormat);
    }
  }

  /**
   * Write a Chart into a given stream. Does not close the target stream automatically at the end of
   * the operation
   *
   * @param chart
   * @param targetStream
   * @param bitmapFormat
   * @throws IOException
   */
  public static <T extends Chart<?, ?>> void saveBitmap(
      T chart, OutputStream targetStream, BitmapFormat bitmapFormat) throws IOException {

    BufferedImage bufferedImage = getBufferedImage(chart);
    ImageIO.write(bufferedImage, bitmapFormat.toString().toLowerCase(), targetStream);
  }

  /**
   * Save list of Charts as an image file. Function assumes that all charts are the same size
   * (width, height). Number of charts should equal rows multiplied by cols.
   *
   * @param charts
   * @param rows number of rows
   * @param cols number of columns
   * @param fileName
   * @param bitmapFormat
   * @throws IOException
   */
  public static <T extends Chart<?, ?>> void saveBitmap(
      List<T> charts,
      Integer rows,
      Integer cols,
      String fileName,
      BitmapEncoder.BitmapFormat bitmapFormat)
      throws IOException {

    try (OutputStream out = new FileOutputStream(addFileExtension(fileName, bitmapFormat))) {
      saveBitmap(charts, rows, cols, out, bitmapFormat);
    }
  }

  /**
   * Save list of Charts into a given stream. Does not close the target stream automatically at the
   * end of the operation. Function assumes that all charts are the same size (width, height).
   * Number of charts should equal rows multiplied by cols.
   *
   * @param charts
   * @param rows number of rows
   * @param cols number of columns
   * @param targetStream
   * @param bitmapFormat
   * @throws IOException
   */
  public static <T extends Chart<?, ?>> void saveBitmap(
      List<T> charts,
      Integer rows,
      Integer cols,
      OutputStream targetStream,
      BitmapEncoder.BitmapFormat bitmapFormat)
      throws IOException {

    List<BufferedImage> chartImages = new LinkedList<>();
    for (T c : charts) chartImages.add(getBufferedImage(c));

    BufferedImage bufferedImage = mergeImages(chartImages, rows, cols);
    ImageIO.write(bufferedImage, bitmapFormat.toString().toLowerCase(), targetStream);
  }

  /**
   * Save a chart as a PNG with a custom DPI. The default DPI is 72, which is fine for displaying
   * charts on a computer monitor, but for printing charts, a DPI of around 300 is much better.
   *
   * @param chart
   * @param fileName
   * @param DPI
   * @throws IOException
   */
  public static <T extends Chart<?, ?>> void saveBitmapWithDPI(
      T chart, String fileName, BitmapFormat bitmapFormat, int DPI) throws IOException {

    double scaleFactor = DPI / 72.0;

    BufferedImage bufferedImage =
        new BufferedImage(
            (int) (chart.getWidth() * scaleFactor),
            (int) (chart.getHeight() * scaleFactor),
            BufferedImage.TYPE_INT_RGB);

    Graphics2D graphics2D = bufferedImage.createGraphics();

    AffineTransform at = graphics2D.getTransform();
    at.scale(scaleFactor, scaleFactor);
    graphics2D.setTransform(at);

    chart.paint(graphics2D, chart.getWidth(), chart.getHeight());
    Iterator<ImageWriter> writers =
        ImageIO.getImageWritersByFormatName(bitmapFormat.toString().toLowerCase());
    if (writers.hasNext()) {
      ImageWriter writer = writers.next();
      // instantiate an ImageWriteParam object with default compression options
      ImageWriteParam iwp = writer.getDefaultWriteParam();

      ImageTypeSpecifier typeSpecifier =
          ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_RGB);
      IIOMetadata metadata = writer.getDefaultImageMetadata(typeSpecifier, iwp);
      if (metadata.isReadOnly() || !metadata.isStandardMetadataFormatSupported()) {
        throw new IllegalArgumentException(
            "It is not possible to set the DPI on a bitmap with "
                + bitmapFormat
                + " format!! Try another format.");
      }

      setDPI(metadata, DPI);

      File file = new File(addFileExtension(fileName, bitmapFormat));

      try (FileImageOutputStream output = new FileImageOutputStream(file)) {
        writer.setOutput(output);
        IIOImage image = new IIOImage(bufferedImage, null, metadata);
        writer.write(null, image, iwp);
        writer.dispose();
      }
    }
  }

  /**
   * Sets the metadata correctly
   *
   * @param metadata
   * @param DPI
   * @throws IIOInvalidTreeException
   */
  private static void setDPI(IIOMetadata metadata, int DPI) throws IIOInvalidTreeException {

    // for PNG, it's dots per millimeter
    double dotsPerMilli = 1.0 * DPI / 10 / 2.54;

    IIOMetadataNode horiz = new IIOMetadataNode("HorizontalPixelSize");
    horiz.setAttribute("value", Double.toString(dotsPerMilli));

    IIOMetadataNode vert = new IIOMetadataNode("VerticalPixelSize");
    vert.setAttribute("value", Double.toString(dotsPerMilli));

    IIOMetadataNode dim = new IIOMetadataNode("Dimension");
    dim.appendChild(horiz);
    dim.appendChild(vert);

    IIOMetadataNode root = new IIOMetadataNode("javax_imageio_1.0");
    root.appendChild(dim);

    metadata.mergeTree("javax_imageio_1.0", root);
  }

  /**
   * Save a Chart as a JPEG file
   *
   * @param chart
   * @param fileName
   * @param quality - a float between 0 and 1 (1 = maximum quality)
   * @throws IOException
   */
  public static <T extends Chart<?, ?>> void saveJPGWithQuality(
      T chart, String fileName, float quality) throws IOException {

    BufferedImage bufferedImage = getBufferedImage(chart);

    Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpeg");
    ImageWriter writer = iter.next();
    // instantiate an ImageWriteParam object with default compression options
    ImageWriteParam iwp = writer.getDefaultWriteParam();
    iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
    iwp.setCompressionQuality(quality);
    File file = new File(fileName);

    try (FileImageOutputStream output = new FileImageOutputStream(file)) {
      writer.setOutput(output);
      IIOImage image = new IIOImage(bufferedImage, null, null);
      writer.write(null, image, iwp);
      writer.dispose();
    }
  }

  /**
   * Generates a byte[] for a given chart
   *
   * @param chart
   * @return a byte[] for a given chart
   * @throws IOException
   */
  public static <T extends Chart<?, ?>> byte[] getBitmapBytes(T chart, BitmapFormat bitmapFormat)
      throws IOException {

    BufferedImage bufferedImage = getBufferedImage(chart);

    byte[] imageInBytes;

    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      ImageIO.write(bufferedImage, bitmapFormat.toString().toLowerCase(), baos);
      baos.flush();
      imageInBytes = baos.toByteArray();
    }
    return imageInBytes;
  }

  public static <T extends Chart<?, ?>> BufferedImage getBufferedImage(T chart) {

    BufferedImage bufferedImage =
        new BufferedImage(chart.getWidth(), chart.getHeight(), BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics2D = bufferedImage.createGraphics();
    chart.paint(graphics2D, chart.getWidth(), chart.getHeight());
    return bufferedImage;
  }

  private static BufferedImage mergeImages(List<BufferedImage> images, Integer rows, Integer cols) {

    BufferedImage first = images.get(0);
    int singleImageWidth = first.getWidth();
    int singleImageHeight = first.getHeight();
    int totalWidth = singleImageWidth * cols;
    int totalHeight = singleImageHeight * rows;
    BufferedImage mergedImage =
        new BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_ARGB);

    Graphics g = mergedImage.getGraphics();
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        BufferedImage image = images.get(row * cols + col);
        g.drawImage(image, col * singleImageWidth, row * singleImageHeight, null);
      }
    }

    return mergedImage;
  }

  public enum BitmapFormat {
    PNG,
    JPG,
    BMP,
    GIF
  }
}
