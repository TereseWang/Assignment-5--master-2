package viewTest;

import static org.junit.Assert.assertEquals;

import cs3500.animation.model.Frame;
import cs3500.animation.model.Motion;
import cs3500.animation.model.SimpleAnimation;
import cs3500.animator.shape.Color;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.Rectangle;
import cs3500.animator.shape.Shape;
import cs3500.animator.view.TweeningMotion;
import org.junit.Before;
import org.junit.Test;

/**
 * test cases for tweening motion class to check if it's working correctly. Test all public methods
 * in the tweening motion class.
 */
public class TweeningMotionTest {

  private TweeningMotion frame;
  private SimpleAnimation model;

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
    frame = new TweeningMotion(model, 10);
  }

  @Test
  public void fillInBlankMotion() {
    init();
    assertEquals("{a=[1 100 200 4 10 100 100 100  4 10 10 3 3 100 100 100, "
        + "4 10 10 3 3 100 100 100  5 100 100 3 3 100 100 100, "
        + "5 100 100 3 3 100 100 100  10 100 200 5 5 0 0 255, "
        + "10 100 200 5 5 0 0 255  23 100 200 4 10 100 100 100], "
        + "b=[1 100 200 4 10 100 100 100  4 10 10 3 3 100 100 100, "
        + "4 10 10 3 3 100 100 100  5 100 100 3 3 100 100 100]}", model.getAnimate().toString());
    frame.fillInBlankMotion();
    assertEquals(
        "{a=[1 100 200 4 10 100 100 100  4 10 10 3 3 100 100 100, "
            + "4 10 10 3 3 100 100 100  5 100 100 3 3 100 100 100, "
            + "5 100 100 3 3 100 100 100  10 100 200 5 5 0 0 255, "
            + "10 100 200 5 5 0 0 255  23 100 200 4 10 100 100 100], "
            + "b=[1 100 200 4 10 100 100 100  4 10 10 3 3 100 100 100, "
            + "4 10 10 3 3 100 100 100  5 100 100 3 3 100 100 100, "
            + "5 100 100 3 3 100 100 100  23 100 100 3 3 100 100 100]}",
        model.getAnimate().toString());
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
    assertEquals("100 200 5 5 0 0 255", frame.getMotionState("a", 10).toString());
    assertEquals("100 200 4 4 7 7 243", frame.getMotionState("a", 11).toString());
    assertEquals("100 200 4 4 15 15 231", frame.getMotionState("a", 12).toString());
    assertEquals("100 200 4 4 23 23 219", frame.getMotionState("a", 13).toString());
    assertEquals("100 200 4 4 30 30 207", frame.getMotionState("a", 14).toString());
    assertEquals("100 200 4 4 38 38 195", frame.getMotionState("a", 15).toString());
    assertEquals("100 200 4 4 46 46 183", frame.getMotionState("a", 16).toString());
  }


}