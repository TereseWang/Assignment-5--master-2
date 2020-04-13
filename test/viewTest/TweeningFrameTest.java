package viewTest;

import static org.junit.Assert.assertEquals;

import cs3500.animation.model.Frame;
import cs3500.animation.model.KeyFrameAnimation;
import cs3500.animator.shape.Color;
import cs3500.animator.shape.Oval;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.Rectangle;
import cs3500.animator.shape.Shape;
import cs3500.animator.view.TweeningFrame;
import org.junit.Before;
import org.junit.Test;

/**
 * test cases for tweening frame class to check if it's working correctly.
 */
public class TweeningFrameTest {

  private TweeningFrame frame;
  private KeyFrameAnimation model;

  @Before
  public void init() {
    model = new KeyFrameAnimation();
    Shape s = new Rectangle(new Posn(100, 100), new Color(0, 255, 0), 10, 10);
    Frame f = new Frame(s, 1);
    Shape s2 = new Rectangle(new Posn(100, 100), new Color(0, 255, 0), 100, 100);
    Frame f1 = new Frame(s2, 10);
    Shape s3 = new Rectangle(new Posn(200, 200), new Color(0, 255, 0), 100, 100);
    Frame f2 = new Frame(s3, 20);
    Shape s4 = new Oval(new Posn(300, 300), new Color(0, 200, 0), 30, 30);
    Frame f3 = new Frame(s4, 1);
    Frame f4 = new Frame(s4, 1);
    Frame f5 = new Frame(s4, 2);
    Frame f6 = new Frame(s4, 10);
    model.declareShape("a", "Rectangle");
    model.declareShape("b", "Oval");
    model.addKeyFrame("a", f1);
    model.addKeyFrame("a", f);
    model.addKeyFrame("a", f2);
    model.addKeyFrame("b", f3);
    model.addKeyFrame("b", f4);
    model.addKeyFrame("b", f5);
    model.addKeyFrame("b", f6);
    frame = new TweeningFrame(10, model);
  }

  @Test
  public void fillInBlankMotion() {
    init();
    assertEquals("{a=[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, "
        + "20 200 200 100 100 0 255 0], "
        + "b=[1 300 300 30 30 0 200 0, 2 300 300 30 30 0 200 0, "
        + "10 300 300 30 30 0 200 0]}", model.getAnimate().toString());
    frame.fillInBlankMotion();
    assertEquals("{a=[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, "
        + "20 200 200 100 100 0 255 0], "
        + "b=[1 300 300 30 30 0 200 0, 2 300 300 30 30 0 200 0, "
        + "10 300 300 30 30 0 200 0, 20 300 300 30 30 0 200 0]}", model.getAnimate().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void getMotionStateInvalidName() {
    init();
    assertEquals(null, frame.getMotionState("dd", 13));
  }

  @Test
  public void getMotionStateInvalidTime() {
    init();
    assertEquals(null, frame.getMotionState("a", 2));
  }

  @Test
  public void testGetMotionStateValid() {
    init();
    assertEquals("100 100 100 100 0 255 0", frame.getMotionState("a", 10).toString());
    assertEquals("110 110 100 100 0 255 0", frame.getMotionState("a", 11).toString());
    assertEquals("120 120 100 100 0 255 0", frame.getMotionState("a", 12).toString());
    assertEquals("130 130 100 100 0 255 0", frame.getMotionState("a", 13).toString());
    assertEquals("140 140 100 100 0 255 0", frame.getMotionState("a", 14).toString());
    assertEquals("150 150 100 100 0 255 0", frame.getMotionState("a", 15).toString());
    assertEquals("160 160 100 100 0 255 0", frame.getMotionState("a", 16).toString());
  }
}