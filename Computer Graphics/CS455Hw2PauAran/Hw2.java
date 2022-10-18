/*
Course: CS 45500
Assignment: 2
Name: Pau Aran 
Email: paaranmig@pnw.edu

*/

import renderer.scene.*;
import renderer.models_L.Axes2D;
import renderer.pipeline.*;
import renderer.framebuffer.*;

/**

*/
public class Hw2 {
      final static int WIDTH_FB = 800;
      final static int HEIGHT_FB = 800;
      final static int XY_MAX = 3;

      public static void main(String[] args) {
            // Turn on clipping in the rasterizer.
            Rasterize.doClipping = true;

            // Create the Scene object that we shall render.
            Scene scene = new Scene();

            // Give the scene a coordinate axis.
            scene.addPosition(
                        new Position(
                                    new Axes2D(-XY_MAX, XY_MAX, // End points for x-axis.
                                                -XY_MAX, XY_MAX, // End points for y-axis.
                                                2 * XY_MAX, // Number of evenly spaced
                                                2 * XY_MAX))); // tick marks on each axis.
            scene.getPosition(0).translation(0.0, 0.0, -XY_MAX);

            // Add Models to the Scene.
            scene.addPosition(new Position(new P()),
                        new Position(new N()),
                        new Position(new W()));

            // Give each model an initial location.
            scene.getPosition(1).translation(-1.6, 0.0, -XY_MAX); // P
            scene.getPosition(2).translation(-0.5, 0.0, -XY_MAX); // N
            scene.getPosition(3).translation(0.6, 0.0, -XY_MAX); // W

            // Create a FrameBuffer.
            final FrameBuffer fb = new FrameBuffer(WIDTH_FB, HEIGHT_FB);

            // Create the frames of an animation.
            for (int i = 0; i < 650; ++i) {
                  // Render the current Scene into the FrameBuffer.
                  fb.clearFB();
                  Pipeline.render(scene, fb.vp);
                  fb.dumpFB2File(String.format("PPM_Hw2_Frame%03d.ppm", i));

                  // Update the Scene.
                  updateScene(scene, i);
            }
      }

      /**
       * Use the framenumber to determine how to move the models.
       */
      private static void updateScene(Scene scene, int frameNumber) {

            if( frameNumber < 100){
                  moveModelsBy(scene, 0.0, 0.02);
            }
            if (frameNumber >= 100 && frameNumber < 350) {
                  moveModelsBy(scene, 0.01, -0.02);
            }
            if (frameNumber >= 350 && frameNumber < 450) {
                  moveModelsBy(scene, -0.02, 0.0);
            }
            if (frameNumber >= 450 && frameNumber < 500) {
                  moveModelsBy(scene, -0.02, 0.02);
            }
            if (frameNumber >= 500 && frameNumber < 550) {
                  moveModelsBy(scene, -0.02, 0.0);
            }
            if (frameNumber >= 550 && frameNumber < 600) {
                  moveModelsBy(scene, 0.02, 0.02);
            }
            if (frameNumber >= 600 && frameNumber < 650) {
                  moveModelsBy(scene, 0.01, 0.02);
            }


            
      }

      /**
       * Move the models horizontally and vertically by the amounts deltaX and deltaY.
       */
      private static void moveModelsBy(Scene scene, double deltaX, double deltaY) {


            final Vector t0 = scene.getPosition(1).getTranslation();
            scene.getPosition(1).translation(t0.x + deltaX,
                        t0.y + deltaY,
                        t0.z);
            final Vector t1 = scene.getPosition(2).getTranslation();
            scene.getPosition(2).translation(t1.x + deltaX,
                        t1.y + deltaY,
                        t1.z);
            final Vector t2 = scene.getPosition(3).getTranslation();
            scene.getPosition(3).translation(t2.x + deltaX,
                        t2.y + deltaY,
                        t2.z);
      }

}
