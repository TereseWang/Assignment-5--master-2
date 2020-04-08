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
import cs3500.animator.shape.ShapeType;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * test cases for key frame animation class to test all public methods in the frame class to see if
 * they works properly as desired.
 */
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
    s = new Rectangle(new Posn(100, 100), new Color(0, 255, 0), 10, 10);
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
    model.addKeyFrame("a", f1);
    model.addKeyFrame("a", f);
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
    assertEquals("[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, "
            + "10 100 100 100 100 0 255 0, 20 200 200 100 100 0 255 0]",
        model.getSequence("Rectangle").toString());
    assertEquals(20, model.getLength());
  }

  @Test
  public void testAddKeyFrameAfter() {
    init();
    Frame f3 = new Frame(f1.getShape(), 30);
    assertEquals("[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, "
        + "20 200 200 100 100 0 255 0]", model.getSequence("a").toString());
    model.addKeyFrame("a", f3);
    assertEquals("[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, "
            + "20 200 200 100 100 0 255 0, 30 100 100 100 100 0 255 0]",
        model.getSequence("a").toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidFrameWithNoExistingShape() {
    init();
    model.addKeyFrame("d", f1);
  }

  @Test
  public void testAddKeyFrameBefore() {
    init();
    model.declareShape("c", "Oval");
    Shape s4 = new Oval(new Posn(300, 300), new Color(0, 200, 0), 30, 30);
    Frame f3 = new Frame(s4, 3);
    Frame f6 = new Frame(s4, 10);
    model.addKeyFrame("c", f3);
    model.addKeyFrame("c", f6);
    assertEquals("[3 300 300 30 30 0 200 0, 10 300 300 30 30 0 200 0]",
        model.getSequence("c").toString());
    Frame f = new Frame(s4, 1);
    model.addKeyFrame("c", f);
    assertEquals("[1 300 300 30 30 0 200 0, 3 300 300 30 30 0 200 0, "
        + "10 300 300 30 30 0 200 0]", model.getSequence("c").toString());
  }

  @Test
  public void testAddKeyFrameBetween() {
    init();
    assertEquals(
        "[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, 20 200 200 100 100 0 255 0]",
        model.getSequence("a").toString());
    Frame f = new Frame(s4, 15);
    model.addKeyFrame("a", f);
    assertEquals(
        "[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, "
            + "15 300 300 30 30 0 200 0, 20 200 200 100 100 0 255 0]",
        model.getSequence("a").toString());
  }

  @Test
  public void testAddKeyFrameToExistingFrameWithSameFrame() {
    init();
    assertEquals(
        "[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, 20 200 200 100 100 0 255 0]",
        model.getSequence("a").toString());
    Frame f = new Frame(s, 1);
    model.addKeyFrame("a", f);
    assertEquals(
        "[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, 20 200 200 100 100 0 255 0]",
        model.getSequence("a").toString());
  }

  @Test
  public void testAddKeyFrameToExistingFrameWithNotSameFrame() {
    init();
    assertEquals(
        "[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, 20 200 200 100 100 0 255 0]",
        model.getSequence("a").toString());
    Shape s10 = new Rectangle(new Posn(100, 100), new Color(10, 0, 0), 20, 20);
    Frame f = new Frame(s10, 1);
    model.addKeyFrame("a", f);
    assertEquals(
        "[1 100 100 20 20 10 0 0, 10 100 100 100 100 0 255 0, 20 200 200 100 100 0 255 0]",
        model.getSequence("a").toString());
  }

  @Test
  public void testDeleteShape() {
    init();
    assertEquals("{a=[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, "
        + "20 200 200 100 100 0 255 0], b=[1 300 300 30 30 0 200 0, "
        + "2 300 300 30 30 0 200 0, 10 300 300 30 30 0 200 0]}", model.getAnimate().toString());
    model.deleteShape("a");
    assertEquals("{b=[1 300 300 30 30 0 200 0, 2 300 300 30 30 0 200 0, 10 300 300 30 30 0 200 0]}",
        model.getAnimate().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDeleteShapeWithNonExistingName() {
    init();
    model.deleteShape("dd");
  }

  @Test
  public void testDeleteMotion() {
    init();
    assertEquals(
        "[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, 20 200 200 100 100 0 255 0]",
        model.getSequence("a").toString());
    model.deleteMotion("a", 1);
    assertEquals("[10 100 100 100 100 0 255 0, 20 200 200 100 100 0 255 0]",
        model.getSequence("a").toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDeleteMotionWithNonExistingName() {
    init();
    model.deleteMotion("c", 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDeleteMotionWithWrongStartTick() {
    init();
    model.deleteMotion("a", 100);
  }

  @Test
  public void testChangeColor() {
    init();
    assertEquals(
        "[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, 20 200 200 100 100 0 255 0]",
        model.getSequence("a").toString());
    model.changeColor("a", new Color(0, 0, 0), 10);
    assertEquals("[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 0 0, 20 200 200 100 100 0 255 0]",
        model.getSequence("a").toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidChangeColorWithNoExistingName() {
    init();
    model.changeColor("c", new Color(10, 10, 10), 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidChangeColorWithNoExistingTime() {
    init();
    model.changeColor("a", new Color(10, 10, 10), 100);
  }

  @Test
  public void testChangePosition() {
    init();
    assertEquals(
        "[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, 20 200 200 100 100 0 255 0]",
        model.getSequence("a").toString());
    model.changePosition("a", new Posn(0, 0), 1);
    assertEquals("[1 0 0 10 10 0 255 0, 10 100 100 100 100 0 255 0, 20 200 200 100 100 0 255 0]",
        model.getSequence("a").toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidChangePosnWithNoExistingName() {
    init();
    model.changePosition("c", new Posn(10, 10), 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidChangePosnWithNoExistingTime() {
    init();
    model.changePosition("a", new Posn(10, 10), 100);
  }

  @Test
  public void testChangeSize() {
    init();
    assertEquals(
        "[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, 20 200 200 100 100 0 255 0]",
        model.getSequence("a").toString());
    model.changeSize("a", 200, 200, 1);
    assertEquals(
        "[1 100 100 200 200 0 255 0, 10 100 100 100 100 0 255 0, 20 200 200 100 100 0 255 0]",
        model.getSequence("a").toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidChangeSizeWithNoExistingName() {
    init();
    model.changeSize("c", 10, 10, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidChangeSizeWithNoExistingTime() {
    init();
    model.changeSize("a", 10, 10, 100);
  }

  @Test
  public void testGetSequence() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    assertEquals(3, model.getSequence("a").size());
    assertEquals("1 100 100 10 10 0 255 0",
        model.getSequence("a").get(0).toString());
    assertEquals("10 100 100 100 100 0 255 0",
        model.getSequence("a").get(1).toString());

    assertEquals(3, model.getSequence("b").size());
    assertEquals("1 300 300 30 30 0 200 0",
        model.getSequence("b").get(0).toString());
    assertEquals("2 300 300 30 30 0 200 0",
        model.getSequence("b").get(1).toString());
  }

  @Test
  public void testGetLength() {
    init();
    assertEquals("{a=[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, "
        + "20 200 200 100 100 0 255 0], b=[1 300 300 30 30 0 200 0, "
        + "2 300 300 30 30 0 200 0, 10 300 300 30 30 0 200 0]}", model.getAnimate().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSequenceNoExistingName() {
    init();
    assertEquals("", model.getSequence("d"));
    model.declareShape("Rectangle", "Rectangle");
    assertEquals(20, model.getLength());
  }

  @Test
  public void testGetLengthEmpty() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    KeyFrameAnimation model1 = new KeyFrameAnimation();
    model1.declareShape("Rectangle", "Rectangle");

    assertEquals(0, model1.getLength());
  }

  @Test
  public void testGetLengthNotfromZero() {
    KeyFrameAnimation model1 = new KeyFrameAnimation();
    model1.declareShape("Rectangle", "Rectangle");
    model1.declareShape("a", "Rectangle");

    model1.addKeyFrame("a", f5);
    assertEquals(2, model1.getLength());
  }


  @Test
  public void testGetStartTime() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    assertEquals(1, model.getStartTime());
  }

  @Test
  public void testGetBox() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    assertEquals(new java.awt.Rectangle(0, 0, 600, 600), model.getBox());
  }

  @Test
  public void testGetShapeType() {
    init();
    model.declareShape("Rectangle", "Rectangle");

    assertEquals(ShapeType.RECTANGLE, model.getShapeType("a"));
    assertEquals(ShapeType.OVAL, model.getShapeType("b"));
  }

  @Test
  public void testGetAnimate() {
    init();
    assertEquals(
        "{a=[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, 20 200 200 100 100 0 255 0], "
            + "b=[1 300 300 30 30 0 200 0, 2 300 300 30 30 0 200 0, 10 300 300 30 30 0 200 0]}",
        model.getAnimate().toString());
  }
}
