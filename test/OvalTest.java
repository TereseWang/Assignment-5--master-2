import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import cs3500.animation.model.Color;
import cs3500.animation.model.Oval;
import cs3500.animation.model.Posn;
import cs3500.animation.model.Rectangle;
import org.junit.Test;

/**
 * Test all methods in the class Oval.
 */
public class OvalTest {

  Oval c = new Oval(new Posn(100, 100), new Color(200, 200, 200), 10, 20);
  Oval c1 = new Oval(new Posn(200, 200), new Color(100, 40, 200), 5, 3);

  @Test(expected = IllegalArgumentException.class)
  public void invalidOvalNullPosn() {
    Oval c = new Oval(null, new Color(200, 200, 200), 10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidOvalNullColor() {
    Oval c = new Oval(new Posn(100, 200), null, 10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidOvalNullColorNullPosn() {
    Oval c = new Oval(null, null, 10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidOvalInvalidWidth() {
    Oval c = new Oval(new Posn(200, 200), new Color(200, 200, 200), -10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidOvalInvalidHeight() {
    Oval c = new Oval(null, new Color(200, 200, 200), 10, -20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidOvalInvalidWH() {
    Oval c = new Oval(null, new Color(200, 200, 200), 0, -10);
  }

  @Test
  public void testChangeColor() {
    c.changeColor(new Color(100, 100, 100));
    assertEquals(new Color(100, 100, 100), c.getColor());
    c1.changeColor(new Color(255, 255, 255));
    assertEquals(new Color(255, 255, 255), c1.getColor());
  }

  @Test
  public void testChangePosn() {
    c.changePosn(new Posn(10, 10));
    assertEquals(new Posn(10, 10), c.getPosition());
    c1.changePosn(new Posn(10, 10));
    assertEquals(new Posn(10, 10), c1.getPosition());
  }

  @Test
  public void testChangeSize() {
    c.changeSize(5, 5);
    assertEquals(5, c.getWidth(), 0.001);
    assertEquals(5, c.getHeight(), 0.001);
    c1.changeSize(4, 4);
    assertEquals(4, c1.getHeight(), 0.001);
    assertEquals(4, c1.getWidth(), 0.001);
  }

  @Test
  public void testGetPosition() {
    assertEquals(new Posn(100, 100), c.getPosition());
    assertEquals(new Posn(200, 200), c1.getPosition());
  }

  @Test
  public void testGetColor() {
    assertEquals(new Color(200, 200, 200), c.getColor());
    assertEquals(new Color(100, 40, 200), c1.getColor());
  }

  @Test
  public void testGetWidth() {
    assertEquals(10, c.getWidth(), 0.001);
    assertEquals(5, c1.getWidth(), 0.001);
  }

  @Test
  public void testGetHeight() {
    assertEquals(20, c.getHeight(), 0.001);
    assertEquals(3, c1.getHeight(), 0.001);
  }

  @Test
  public void testToString() {
    assertEquals("100 100 10 20 200 200 200", c.toString());
    assertEquals("200 200 5 3 100 40 200", c1.toString());
  }

  @Test
  public void testEquals() {
    Oval o1 = new Oval(new Posn(0, 0), new Color(200, 200, 200), 3, 4);
    Rectangle r1 = new Rectangle(new Posn(0, 0),
        new Color(200, 200, 200), 3, 4);
    assertFalse(o1.equals(r1));
    assertEquals(true, c.equals(c));
    assertEquals(true, c1.equals(c1));
    assertEquals(false, c.equals(c1));
  }

  @Test
  public void testCopyShape() {
    assertEquals(c, c.copyShape());
    assertEquals(c1, c1.copyShape());
  }

  @Test
  public void testGetShapeName() {
    assertEquals("Oval", c.getShapeName());
    assertEquals("Oval", c1.getShapeName());
  }
}