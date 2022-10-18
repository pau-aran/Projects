/*
Course: CS 45500
Assignment: 1
Name: Pau Aran 
Email: paaranmig@pnw.edu
*/

import framebuffer.FrameBuffer;

import java.awt.Color;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;

/**
 * Outline of CS 45500 Assignment 1.
 * 
 */
public class Hw1 {
   public static void main(String[] args) {
      // Use a properties file to find out
      // which PPM files to use as assets.
      final Properties properties = new Properties();
      try {
         properties.load(
               new FileInputStream(
                     new File("assets.properties")));
      } catch (IOException e) {
         e.printStackTrace(System.err);
         System.exit(-1);
      }
      final String file_1 = properties.getProperty("file1");
      final String file_2 = properties.getProperty("file2");

      // This framebuffer holds the image that will be embedded
      // within two viewports of the larger framebuffer.
      final FrameBuffer fbEmbedded = new FrameBuffer(file_1);

      /******************************************/

      // Your code goes here.
      // Create a framebuffer. Fill it with the checkerboard pattern.

      FrameBuffer fb = new FrameBuffer(1000, 600);

      for (int x = 0; x < 1000; x++) {
         for (int y = 0; y < 600; y++) {
            if ((x / 100) % 2 == 0) {
               if ((y / 100) % 2 == 0) {
                  fb.setPixelFB(x, y, new Color(255, 189, 96));
               } else {
                  fb.setPixelFB(x, y, new Color(192, 56, 14));
               }
            } else {
               if ((y / 100) % 2 == 0) {
                  fb.setPixelFB(x, y, new Color(192, 56, 14));
               } else {
                  fb.setPixelFB(x, y, new Color(255, 189, 96));
               }
            }
         }
      }

      // Create a viewport and fill it with a flipped copy of the first PPM file.

      FrameBuffer.Viewport vp1 = fbEmbedded.new Viewport(0, 0, fbEmbedded.getWidthFB(), fbEmbedded.getHeightFB());
      for (int y = 0; y < vp1.getHeightVP(); ++y) {
         for (int x = 0; x < vp1.getWidthVP(); ++x) {
            Color c = vp1.getPixelVP(x, y);
            if (c.getRGB() != c.WHITE.getRGB()) {
               fb.setPixelFB(x + 75, vp1.getHeightVP() - 1 - y + 125, c);
            }
         }
      }

      // Create another viewport and fill it with another flipped copy of the first
      // PPM file.

      FrameBuffer.Viewport vp2 = fbEmbedded.new Viewport(0, 0, fbEmbedded.getWidthFB(), fbEmbedded.getHeightFB());
      for (int y = 0; y < vp2.getHeightVP(); ++y) {
         for (int x = 0; x < vp2.getWidthVP(); ++x) {
            Color c = vp2.getPixelVP(x, y);
            if (c.getRGB() != c.WHITE.getRGB()) {
               fb.setPixelFB(vp2.getWidthVP() - 1 - x + 331, y + 125, c);
            }
         }
      }

      // Draw the striped pattern.

      for (int y = 420; y < 540; y++) {
         for (int x = 610; x < 910; x++) {
            if (((x + y - 10) / 30) % 3 == 0) {
               fb.setPixelFB(x, y, new Color(84, 129, 230));
            } else if (((x + y - 10) / 30) % 3 == 1) {
               fb.setPixelFB(x, y, new Color(238, 94, 115));
            } else {
               fb.setPixelFB(x, y, new Color(150, 203, 74));
            }
         }
      }

      // Create another viewport that covers the selected region of the framebuffer.
      // Create another viewport to hold a "framed" copy of the selected region.
      // Give this viewport a grayish background color.

      FrameBuffer.Viewport vp3 = fb.new Viewport(725, 25, 250, 350);
      for (int y = 0; y < vp3.getHeightVP(); ++y) {
         for (int x = 0; x < vp3.getWidthVP(); ++x) {
            vp3.setPixelVP(x, y, new Color(192, 192, 192));
         }
      }

      // Create another viewport inside the last one.

      FrameBuffer.Viewport vp4 = fb.new Viewport(750, 50, 200, 300);

      // Copy the selected region's viewport into this last viewport.

      for (int y = 200; y < 200 + vp4.getHeightVP(); ++y) {
         for (int x = 500; x < 500 + vp4.getWidthVP(); ++x) {
            Color c = fb.getPixelFB(x, y);
            vp4.setPixelVP(x + 500, y - 200, c);
         }
      }

      // Create a viewport to hold Dumbledore's ghost.
      // Blend Dumbledore from its framebuffer into the viewport.

      FrameBuffer dumbledore = new FrameBuffer(file_2);

      FrameBuffer.Viewport vp5 = fb.new Viewport(0, 0, dumbledore.getWidthFB(), dumbledore.getHeightFB());
      for (int y = 0; y < vp5.getHeightVP(); ++y) {
         for (int x = 0; x < vp5.getWidthVP(); ++x) {
            Color c1 = dumbledore.getPixelFB(x, y);
            Color c2 = vp5.getPixelVP(x + 400, y + 100);

            int r = (int) (c1.getRed() * 0.7 + c2.getRed() * 0.3);
            int g = (int) (c1.getGreen() * 0.7 + c2.getGreen() * 0.3);
            int b = (int) (c1.getBlue() * 0.7 + c2.getBlue() * 0.3);

            final Color c = new Color(r, g, b);

            if (c1.getRGB() != c1.WHITE.getRGB()) {
               fb.setPixelFB(x + 400, y + 100, c);
            }
         }
      }


      /******************************************/
      // Save the resulting image in a file.
      final String savedFileName = "Hw1.ppm";
      fb.dumpFB2File(savedFileName);
      System.err.println("Saved " + savedFileName);
   }
}