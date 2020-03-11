import cs3500.animation.model.Color;
import org.junit.Test;

public class ColorTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColor() {
    Color c = new Color(-1, 100, 200);
  }
}
