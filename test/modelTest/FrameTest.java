package modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import cs3500.animation.model.Frame;
import cs3500.animator.shape.Color;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.Rectangle;
import cs3500.animator.shape.Shape;
import org.junit.Before;
import org.junit.Test;

/**
 * This is a test class to test all public methods in the frame class to see if they works properly
 * as desired, which we tested all the public methods in the frame class.
 */
public class FrameTest {

  Frame frame;
  Shape state;
  int time;

  @Before
  public void init() {
    state = new Rectangle(new Posn(10, 10), new Color(100, 100, 100), 3, 3);
    time = 10;
    frame = new Frame(state, time);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNullShape() {
    Frame f = new Frame(null, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegativeTime() {
    Frame f = new Frame(state, -10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCopyConstructorNullFrame() {
    Frame f = new Frame(null);
  }

  @Test
  public void testValidCopyConstructor() {
    init();
    Frame f = new Frame(frame);
    assertTrue(f.getShape().equals(frame.getShape()));
    assertTrue(f.getTime() == frame.getTime());
  }

  @Test
  public void testGetShape() {
    init();
    //test if the getShape is actually copying the shape rather than copy its reference
    assertNotSame(state, frame.getShape());
    assertTrue(state.isSameType(frame.getShape()));
    assertTrue(state.toString().equals(frame.getShape().toString()));
  }

  @Test
  public void testGetTime() {
    init();
    assertTrue(frame.getTime() == 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidChangeColor() {
    init();
    assertTrue(frame.getShape().getColor().equals(new Color(100, 100, 100)));
    frame.changeColor(null);
  }

  @Test
  public void testValidChangeColor() {
    init();
    assertTrue(frame.getShape().getColor().equals(new Color(100, 100, 100)));
    frame.changeColor(new Color(0, 200, 0));
    assertTrue(frame.getShape().getColor().equals(new Color(0, 200, 0)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidChangePosn() {
    init();
    assertTrue(frame.getShape().getPosition().equals(new Posn(10, 10)));
    frame.changePosition(null);
  }

  @Test
  public void testValidChangePosn() {
    init();
    assertTrue(frame.getShape().getColor().equals(new Color(100, 100, 100)));
    frame.changePosition(new Posn(100, 100));
    assertTrue(frame.getShape().getPosition().equals(new Posn(100, 100)));
  }

  @Test
  public void testValidChangeSizeWidth() {
    init();
    assertTrue(frame.getShape().getWidth() == 3);
    assertTrue(frame.getShape().getHeight() == 3);
    frame.changeSize(10, 3);
    assertTrue(frame.getShape().getWidth() == 10);
    assertTrue(frame.getShape().getHeight() == 3);
  }

  @Test
  public void testValidChangeSizeHeight() {
    init();
    assertTrue(frame.getShape().getWidth() == 3);
    assertTrue(frame.getShape().getHeight() == 3);
    frame.changeSize(3, 10);
    assertTrue(frame.getShape().getWidth() == 3);
    assertTrue(frame.getShape().getHeight() == 10);
  }

  @Test
  public void testValidChangeSizeWidthHeight() {
    init();
    assertTrue(frame.getShape().getWidth() == 3);
    assertTrue(frame.getShape().getHeight() == 3);
    frame.changeSize(10, 10);
    assertTrue(frame.getShape().getWidth() == 10);
    assertTrue(frame.getShape().getHeight() == 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidChangeTimeNegativeTime() {
    init();
    assertTrue(frame.getTime() == 10);
    frame.changeTime(-1);
  }

  @Test
  public void testValidChangeTime() {
    init();
    assertTrue(frame.getTime() == 10);
    frame.changeTime(100);
    assertTrue(frame.getTime() == 100);
  }

  @Test
  public void testEqual() {
    init();
    Shape state1 = new Rectangle(new Posn(10, 10), new Color(100, 100, 100), 3, 3);
    Shape state2 = new Rectangle(new Posn(100, 20), new Color(200, 10, 100), 4, 3);
    int time1 = 10;
    Frame frame1 = new Frame(state1, time1);
    Frame frame2 = new Frame(state2, time1);
    assertTrue(frame1.equals(frame));
    assertTrue(frame.equals(frame1));
    assertTrue(frame.equals(frame));
    assertFalse(frame1.equals(new Frame(state2,time1)));
    assertFalse(frame2.equals(frame));

  }

  @Test
  public void testHashcode() {
    init();
    Shape state1 = new Rectangle(new Posn(10, 10), new Color(100, 100, 100), 3, 3);
    int time1 = 10;
    assertEquals(new Frame(state1, time1).hashCode(), frame.hashCode());
    assertEquals(336, frame.hashCode());

  }

  @Test
  public void testToString() {
    init();
    Shape state1 = new Rectangle(new Posn(10, 10), new Color(100, 100, 100), 3, 3);
    int time1 = 20;
    Frame frame1 = new Frame(state1,time1);
    assertEquals("10 " + "10 10 3 3 100 100 100", frame.toString());
    assertEquals("20 10 10 3 3 100 100 100", frame1.toString());
  }

}
