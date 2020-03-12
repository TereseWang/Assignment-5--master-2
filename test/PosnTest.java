import static org.junit.Assert.assertEquals;

import cs3500.animation.model.Posn;
import org.junit.Test;

/**
 * Test all methods in class Posn.
 */
public class PosnTest {

  Posn p = new Posn(1, 1);
  Posn p1 = new Posn(100, 200);

  @Test
  public void testGetX() {
    assertEquals(1, p.getX());
    assertEquals(100, p1.getX());
  }

  @Test
  public void testNegativePosn() {
    Posn p = new Posn(-100, 200);
    assertEquals(-100, p.getX());
  }

  @Test
  public void testGetY() {
    assertEquals(1, p.getY());
    assertEquals(200, p1.getY());
  }

  @Test
  public void testToString() {
    assertEquals("(1, 1)", p.toString());
    assertEquals("(100, 200)", p1.toString());
  }

  @Test
  public void testEqual() {
    assertEquals(true, p.equals(p));
    assertEquals(false, p.equals(p1));
    Posn p2 = new Posn(1, 1);
    assertEquals(true, p.equals(p2));
  }

  @Test
  public void testHashCode() {
    assertEquals(2, p.hashCode());
    assertEquals(300, p1.hashCode());
  }
}
