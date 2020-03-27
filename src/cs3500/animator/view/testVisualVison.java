package cs3500.animator.view;

import cs3500.animation.model.Motion;
import cs3500.animation.model.SimpleAnimation;
import cs3500.animatior.shape.Color;
import cs3500.animatior.shape.Posn;
import cs3500.animatior.shape.Rectangle;
import cs3500.animatior.shape.Shape;

/**
 * To test the Visual Vision.
 */
public class testVisualVison {

  public static void main(String[] args) {
    SimpleAnimation model = new SimpleAnimation();
    model.declareShape("disk1", "Rectangle");
    model.declareShape("disk2", "Rectangle");
    model.declareShape("disk3", "Rectangle");
    model.declareShape("disk4", "Rectangle");
    model.declareShape("disk5", "Rectangle");
    model.declareShape("disk6", "Rectangle");
    model.declareShape("disk7", "Rectangle");
    model.declareShape("disk8", "Rectangle");
    Shape s = new Rectangle(new Posn(190, 161), new Color(113, 87, 151), 20, 11);
    Shape s1 = new Rectangle(new Posn(190, 161), new Color(113, 87, 151), 20, 11);
    model.addMotion("disk1", new Motion(1, 1, s, s1));
    model.addMotion("disk1", new Motion(1, 25, s, s1));
    Shape s2 = new Rectangle(new Posn(190, 50), new Color(113, 87, 151), 20, 11);
    model.addMotion("disk1", new Motion(25, 35, s, s2));
    model.addMotion("disk1", new Motion(35, 36, s2, s2));
    Shape s3 = new Rectangle(new Posn(340, 50), new Color(113, 87, 151), 20, 11);
    model.addMotion("disk1", new Motion(36, 46, s2, s3));
    VisualView view = new VisualView(model, 20);
    view.display();
  }
}