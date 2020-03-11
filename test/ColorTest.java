import static org.junit.Assert.assertEquals;

import cs3500.animation.model.Color;
import org.junit.Test;

/**
 * To test class Color for all public methods inside the class.
 */
public class ColorTest {

  Color c = new Color(200, 100, 240);

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColorR() {
    Color c = new Color(-1, 100, 200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColorR1() {
    Color c = new Color(300, 100, 200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColorG() {
    Color c = new Color(200, -100, 200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColorG1() {
    Color c = new Color(200, 300, 200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColorB() {
    Color c = new Color(200, 100, -100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColorB1() {
    Color c = new Color(200, 300, 400);
  }

  @Test
  public void testGetR() {
    assertEquals(200, c.getR());
  }

  @Test
  public void testGetG() {
    assertEquals(100, c.getG());
  }

  @Test
  public void testGetB() {
    assertEquals(240, c.getB());
  }

  @Test
  public void testToString() {
    Color c1 = new Color(1, 20, 240);
    assertEquals("(200, 100, 240)", c.toString());
    assertEquals("(1, 20, 240)", c1.toString());
  }

  @Test
  public void testEqual() {
    assertEquals(true, c.equals(c));
    Color c1 = new Color(200, 100, 240);
    Color c2 = new Color(200, 200, 200);
    assertEquals(true, c.equals(c1));
    assertEquals(false, c.equals(c2));
  }
}
