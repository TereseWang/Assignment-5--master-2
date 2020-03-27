import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.animatior.shape.Color;
import cs3500.animatior.shape.Oval;
import cs3500.animatior.shape.Posn;
import cs3500.animatior.shape.Rectangle;

import org.junit.Before;
import org.junit.Test;

/**
 * To test the method isSameType() in the shape interface.
 */
public class ShapeTest {

  Rectangle r;
  Rectangle r1;
  Oval o;
  Oval o1;
  @Before
  public void reset() {
    r = new Rectangle(new Posn(0, 0), new Color(), 2, 2);
    r1 = new Rectangle(new Posn(0, 0), new Color(), 3, 2);
    o = new Oval(new Posn(0, 0), new Color(), 2, 2);
    o1 = new Oval(new Posn(0, 0), new Color(), 3, 2);
  }

  @Test
  public void testIsSameTypeNull() {
    reset();
    assertFalse(o.isSameType(null));
    assertFalse(r.isSameType(null));
  }

  @Test
  public void testIsSameType() {
    reset();
    assertFalse(o.isSameType(r));
    assertFalse(r.isSameType(o));
    assertTrue(o.isSameType(o1));
    assertTrue(r.isSameType(r1));
  }
}