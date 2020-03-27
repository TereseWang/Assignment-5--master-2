import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import cs3500.animation.model.Color;
import cs3500.animation.model.Oval;
import cs3500.animation.model.Posn;
import cs3500.animation.model.Rectangle;
import org.junit.Test;

/**
 * To test all public methods in the Rectangle class.
 */
public class RectangleTest {

  Rectangle r = new Rectangle(new Posn(100, 100), new Color(200, 200, 200), 10, 20);
  Rectangle r1 = new Rectangle(new Posn(200, 200), new Color(100, 40, 200), 5, 3);

  @Test(expected = IllegalArgumentException.class)
  public void invalidRectangleNullPosn() {
    Rectangle r = new Rectangle(null, new Color(200, 200, 200), 10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidRectangleNullColor() {
    Rectangle r = new Rectangle(new Posn(100, 200), null, 10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidRectangleNullColorNullPosn() {
    Rectangle r = new Rectangle(null, null, 10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidRectangleInvalidWidth() {
    Rectangle r = new Rectangle(new Posn(200, 200), new Color(200, 200, 200), -10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidRectangleInvalidHeight() {
    Rectangle r = new Rectangle(null, new Color(200, 200, 200), 10, -20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidRectangleInvalidWH() {
    Rectangle r = new Rectangle(null, new Color(200, 200, 200), 0, -10);
  }

  @Test
  public void testChangeColor() {
    r.changeColor(new Color(100, 100, 100));
    assertEquals(new Color(100, 100, 100), r.getColor());
    r1.changeColor(new Color(255, 255, 255));
    assertEquals(new Color(255, 255, 255), r1.getColor());
  }

  @Test
  public void testChangePosn() {
    r.changePosn(new Posn(10, 10));
    assertEquals(new Posn(10, 10), r.getPosition());
    r1.changePosn(new Posn(10, 10));
    assertEquals(new Posn(10, 10), r1.getPosition());
  }

  @Test
  public void testChangeSize() {
    r.changeSize(5.0, 5.0);
    assertEquals(5.0, r.getWidth(), 0.001);
    assertEquals(5.0, r.getHeight(), 0.001);
    r1.changeSize(4.0, 4.0);
    assertEquals(4.0, r1.getHeight(), 0.001);
    assertEquals(4.0, r1.getWidth(), 0.001);
  }

  @Test
  public void testGetPosition() {
    assertEquals(new Posn(100, 100), r.getPosition());
    assertEquals(new Posn(200, 200), r1.getPosition());
  }

  @Test
  public void testGetColor() {
    assertEquals(new Color(200, 200, 200), r.getColor());
    assertEquals(new Color(100, 40, 200), r1.getColor());
  }

  @Test
  public void testGetWidth() {
    assertEquals(10, r.getWidth(), 0.001);
    assertEquals(5, r1.getWidth(), 0.001);
  }

  @Test
  public void testGetHeight() {
    assertEquals(20, r.getHeight(), 0.001);
    assertEquals(3, r1.getHeight(), 0.001);
  }

  @Test
  public void testToString() {
    assertEquals("100 100 10 20 200 200 200", r.toString());
    assertEquals("200 200 5 3 100 40 200", r1.toString());
  }

  @Test
  public void testEquals() {
    Rectangle r3 = new Rectangle(new Posn(0, 0), new Color(200, 200, 200), 3, 4);
    Oval o1 = new Oval(new Posn(0, 0), new Color(200, 200, 200), 3, 4);
    assertFalse(r3.equals(o1));
    assertEquals(true, r.equals(r));
    assertEquals(true, r1.equals(r1));
    assertEquals(false, r.equals(r1));
  }

  @Test
  public void testCopyShape() {
    assertEquals(r, r.copyShape());
    assertEquals(r1, r1.copyShape());
    assertFalse(r == r.copyShape());
  }

  @Test
  public void testHashCode() {
    assertEquals(830, r.hashCode());
    assertEquals(748, r1.hashCode());
  }

  @Test
  public void testGetShapeName() {
    assertEquals("Rectangle", r.getShapeName());
    assertEquals("Rectangle", r1.getShapeName());
  }
}