import cs3500.animation.model.Color;
import org.junit.Test;

public class ColorTest {

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
}
