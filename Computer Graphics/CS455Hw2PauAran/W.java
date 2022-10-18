/*
Course: CS 45500
Assignment: 1
Name: Pau Aran 
Email: paaranmig@pnw.edu
*/

import renderer.scene.*;
import renderer.scene.primitives.*;

/**
   A two-dimensional model of the letter W.
*/
public class W extends Model
{
   /**
      The letter W.
   */
   public W()
   {
      super("W");

      addVertex(new Vertex(0.00, 1.00, 0.0),
      new Vertex(0.20, 1.00, 0.0),
      new Vertex(0.30, 0.50, 0.0),
      new Vertex(0.40, 1.00, 0.0),
      new Vertex(0.60, 1.00, 0.0),
      new Vertex(0.70, 0.50, 0.0),
      new Vertex(0.80, 1.00, 0.0),
      new Vertex(1.00, 1.00, 0.0),
      new Vertex(0.80, 0.00, 0.0),
      new Vertex(0.60, 0.00, 0.0),
      new Vertex(0.50, 0.50, 0.0),
      new Vertex(0.40, 0.00, 0.0),
      new Vertex(0.20, 0.00, 0.0));

      addPrimitive( new LineSegment(0, 1),
      new LineSegment(1, 2),
      new LineSegment(2, 3),
      new LineSegment(3, 4),
      new LineSegment(4, 5),
      new LineSegment(5, 6),
      new LineSegment(6, 7),
      new LineSegment(7, 8),
      new LineSegment(8, 9),
      new LineSegment(9, 10),
      new LineSegment(10, 11),
      new LineSegment(11, 12),
      new LineSegment(12, 0));
   }
}
