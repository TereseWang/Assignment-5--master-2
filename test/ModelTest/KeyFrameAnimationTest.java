package ModelTest;

import static org.junit.Assert.assertEquals;

import cs3500.animation.model.Frame;
import cs3500.animation.model.KeyFrameAnimation;
import cs3500.animation.model.Motion;
import cs3500.animator.shape.Color;
import cs3500.animator.shape.Oval;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.Rectangle;
import cs3500.animator.shape.Shape;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class KeyFrameAnimationTest {
  KeyFrameAnimation model;
  List<Frame> list1;
  List<Frame> list2;
  Shape s;
  Shape s2;
  Shape s3;
  Shape s4;


  Frame f;
  Frame f1;
  Frame f2;
  Frame f3;
  Frame f4;
  Frame f5;
  Frame f6;

  @Before
  public void init() {
    model = new KeyFrameAnimation();
    list1 = new ArrayList<>();
    list2 = new ArrayList<>();
    s = new Rectangle(new Posn(100, 100), new Color(0, 255,0), 10, 10);
    f = new Frame(s, 1);
    list1.add(f);
    s2 = new Rectangle(new Posn(100, 100), new Color(0, 255, 0), 100, 100);
    f1 = new Frame(s2, 10);
    list1.add(f1);
    s3 = new Rectangle(new Posn(200, 200), new Color(0, 255, 0), 100, 100);
    f2 = new Frame(s3, 20);
    list1.add(f2);
    s4 = new Oval(new Posn(300, 300), new Color(0, 200, 0), 30, 30);
    f3 = new Frame(s4, 1);
    f4 = new Frame(s4, 1);
    f5 = new Frame(s4, 2);
    f6 = new Frame(s4, 10);
    list2.add(f3);
    list2.add(f4);
    list2.add(f5);
    list2.add(f6);
    model.declareShape("a", "Rectangle");
    model.declareShape("b", "Oval");
    model.addKeyFrame("a", f);
    model.addKeyFrame("a", f1);
    model.addKeyFrame("a", f2);
    model.addKeyFrame("b", f3);
    model.addKeyFrame("b", f4);
    model.addKeyFrame("b", f5);
    model.addKeyFrame("b", f6);

  }

  @Test
  public void testDeclareShape() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.declareShape("Circle", "Oval");
    assertEquals(0, model.getSequence("Rectangle").size());
    assertEquals(0, model.getSequence("Circle").size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidMotion() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidName() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangleha", new Motion(f, f1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidOrder() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", new Motion(f2, f1));
    model.addMotion("Rectangle", new Motion(f, f2));
  }


  @Test
  public void testAddMotion() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", new Motion(f, f1));
    model.addMotion("Rectangle", new Motion(f1, f2));
    model.addMotion("Rectangle", new Motion(f2, f3));

    assertEquals(list1, model.getSequence("Rectangle"));
    assertEquals(23, model.getLength());

  }

  @Test
  public void testAddKeyFrame() {
    init();
    model.declareShape("Rectangle", "Rectangle");


  }


}
