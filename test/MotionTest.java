import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import cs3500.animation.model.Color;
import cs3500.animation.model.Motion;
import cs3500.animation.model.Oval;
import cs3500.animation.model.Posn;
import cs3500.animation.model.Rectangle;
import cs3500.animation.model.Shape;
import org.junit.Before;
import org.junit.Test;

public class MotionTest {

  private Motion m;
  private Shape s;
  private Shape s1;

  @Before
  public void init() {
    s = new Oval(new Posn(200, 200), new Color(200, 200, 200), 5, 5);
    s1 = new Rectangle(new Posn(100, 100), new Color(200, 200, 200), 5, 5);
    m = new Motion(4, 10, s, s1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMotionInvalidStart() {
    init();
    Motion m = new Motion(-3, 10, s, s1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMotionInvalidEnd() {
    init();
    Motion m = new Motion(10, 4, s, s1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMotionInvalidStartShape() {
    init();
    Motion m = new Motion(4, 10, null, s1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMotionInvalidEndShape() {
    init();
    Motion m = new Motion(4, 10, s1, null);
  }

  @Test
  public void testGetFinalImage() {
    init();
    Motion m = new Motion(4, 10, s, s1);
    assertEquals(s1.copyShape(), m.getFinalImages());
    Motion m1 = new Motion(4, 10, s1, s);
    assertEquals(s.copyShape(), m1.getFinalImages());
  }

  @Test
  public void testGetPeriod() {
    init();
    assertEquals(6, m.getPeriod());
    Motion m1 = new Motion(30, 60, s, s1);
    assertEquals(30, m1.getPeriod());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPushForwardInvalidPeriod() {
    init();
    m.pushForward(-10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPushForwardInvalidTimeLine() {
    init();
    m.pushForward(5);
  }

  @Test
  public void testPushForward() {
    init();
    m.pushForward(3);
    assertEquals(6, m.getPeriod());
    assertEquals(1, m.getStartTick());
    assertEquals(7, m.getEndTick());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPushBackwardInvalidPeriod() {
    init();
    m.pushBackward(-1);
  }

  @Test
  public void testPushBackward() {
    init();
    m.pushBackward(100);
    assertEquals(104, m.getStartTick());
    assertEquals(110, m.getEndTick());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeEndTickInvalidEndPointNegative() {
    init();
    m.changeEndTick(-100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeEndTickInvalidEndPointTooSmall() {
    init();
    m.changeEndTick(3);
  }

  @Test
  public void testChangeEndTick() {
    init();
    m.changeEndTick(30);
    assertEquals(4, m.getStartTick());
    assertEquals(30, m.getEndTick());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeStartTickInvalidStartPointNegative() {
    init();
    m.changeStartTick(-10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeStartTickInvalidStartPointTooLarge() {
    init();
    m.changeStartTick(20);
  }

  @Test
  public void testChangeStartTick() {
    init();
    m.changeStartTick(1);
    assertEquals(1, m.getStartTick());
    assertEquals(10, m.getEndTick());
    m.changeStartTick(3);
    assertEquals(3, m.getStartTick());
    assertEquals(10, m.getEndTick());
  }

  @Test
  public void testChangeColor() {
    init();
    m.changeColor(new Color(255, 0, 0));
    assertEquals(new Rectangle(new Posn(100, 100), new Color(255, 0, 0), 5, 5), m.getFinalImages());
    assertFalse(
        new Rectangle(new Posn(100, 100), new Color(255, 0, 0), 5, 5) == m.getFinalImages());
  }

  @Test
  public void testChangeSize() {
    init();
    m.changeSize(10, 10);
    assertEquals(10, m.getFinalImages().getHeight());
    assertEquals(10, m.getFinalImages().getWidth());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeSizeInvalidWH() {
    init();
    m.changeSize(-10, 20);
  }
  @Test
  public void testChangePostion() {
    init();
    m.changePosition(new Posn(20, 20));
    assertEquals(new Posn(20, 20), m.getFinalImages().getPosition());
    assertFalse(new Posn(20, 20) == m.getFinalImages().getPosition());
  }

  @Test
  public void testGetStartTick() {
    init();
    assertEquals(4, m.getStartTick());
  }

  @Test
  public void testGetEndTick() {
    init();
    assertEquals(10, m.getEndTick());
  }
}