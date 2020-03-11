import org.junit.Test;

import cs3500.animation.model.Color;
import cs3500.animation.model.Oval;
import cs3500.animation.model.Posn;
import cs3500.animation.model.Rectangle;

import static org.junit.Assert.*;

public class OvalTest {

  @Test
  public void testEquals() {
    Oval o1 = new Oval(new Posn(0,0), new Color(200,200,200), 3,4);
    Rectangle r1 = new Rectangle(new Posn(0,0),
            new Color(200,200,200), 3,4);
    assertFalse(o1.equals(r1));
  }

  @Test
  public void copyShape() {
  }
}