package controllerTest;

import cs3500.animation.model.Frame;
import cs3500.animation.model.Motion;
import cs3500.animation.model.SimpleAnimation;
import cs3500.animator.controller.SimpleController;
import cs3500.animator.shape.Color;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.Rectangle;
import cs3500.animator.shape.Shape;
import cs3500.animator.view.View;
import cs3500.animator.view.ViewCreator;
import cs3500.animator.view.ViewCreator.ViewType;
import org.junit.Before;
import org.junit.Test;

public class SimpleControllerTest {

  SimpleAnimation model;
  View view;

  @Before
  public void init() {
    model = new SimpleAnimation();
    model.declareShape("a", "Rectangle");
    Shape s = new Rectangle(new Posn(10, 10), new Color(100, 100, 100), 3, 3);
    Shape s1 = new Rectangle(new Posn(100, 100), new Color(100, 100, 100), 3, 3);
    Frame f1 = new Frame(s, 4);
    Frame f2 = new Frame(s1, 5);
    Motion m = new Motion(f1, f2);
    model.addMotion("a", m);
    Shape s2 = new Rectangle(new Posn(100, 100), new Color(100, 100, 100), 3, 3);
    Shape s3 = new Rectangle(new Posn(100, 200), new Color(0, 0, 255), 5, 5);
    Frame f3 = new Frame(s2, 5);
    Frame f4 = new Frame(s3, 10);
    Motion m2 = new Motion(f3, f4);
    model.addMotion("a", m2);
    Shape s4 = new Rectangle(new Posn(100, 200), new Color(0, 0, 255), 5, 5);
    Shape s5 = new Rectangle(new Posn(100, 200), new Color(100, 100, 100), 4, 10);
    Frame f5 = new Frame(s4, 10);
    Frame f6 = new Frame(s5, 23);
    Motion m3 = new Motion(f5, f6);
    model.addMotion("a", m3);
    Shape s6 = new Rectangle(new Posn(100, 200), new Color(100, 100, 100), 4, 10);
    Shape s7 = new Rectangle(new Posn(10, 10), new Color(100, 100, 100), 3, 3);
    Frame f7 = new Frame(s6, 1);
    Frame f8 = new Frame(s7, 4);
    Motion m4 = new Motion(f7, f8);
    model.addMotion("a", m4);
    model.declareShape("b", "Rectangle");
    model.addMotion("b", m4);
    model.addMotion("b", m);
    view = new ViewCreator().create(ViewType.VISUAL, model, null, 50);
  }

  @Test
  public void testExexute() {
    init();
    SimpleController controller = new SimpleController(view);
    controller.execute();
  }
}
