package ViewTest;

import cs3500.animation.model.Animation;
import cs3500.animation.model.Motion;
import cs3500.animation.model.SimpleAnimation;
import cs3500.animator.shape.Color;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.Rectangle;
import cs3500.animator.shape.Shape;
import cs3500.animator.view.TextualView;
import cs3500.animator.view.View;

import java.io.IOException;
import java.io.OutputStreamWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TextualViewTest {

  Animation model;
  View view;
  OutputStreamWriter out;
  Motion m;
  Motion m2;


  @Before
  public void setUp() {
    view = new TextualView(model, out);

    model = new SimpleAnimation();
    Shape s = new Rectangle(new Posn(10, 10), new Color(100, 100, 100), 3, 3);
    Shape s1 = new Rectangle(new Posn(100, 100), new Color(100, 100, 100), 3, 3);
    m = new Motion(4, 5, s, s1);
    Shape s2 = new Rectangle(new Posn(100, 100), new Color(100, 100, 100), 3, 3);
    Shape s3 = new Rectangle(new Posn(100, 200), new Color(0, 0, 255), 5, 5);
    m2 = new Motion(5, 10, s2, s3);
    Shape s4 = new Rectangle(new Posn(100, 200), new Color(0, 0, 255), 5, 5);
    Shape s5 = new Rectangle(new Posn(100, 200), new Color(100, 100, 100), 4, 10);

  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRefresh() {
    view.refresh();
  }

  @Test
  public void testDisplay() {

    try {
      java.awt.Rectangle canvas = new java.awt.Rectangle();
      canvas.setBounds(50, 145, 410, 220);
      model.declareShape("Rectangle", "Rectangle");
      model.addMotion("Rectangle", m);
      model.addMotion("Rectangle", m2);

      while (out != null) {
        out.append(String.format("canvas %d %d %d %d\n", canvas.y, canvas.x, canvas.width,
            canvas.height));
        out.append(model.toString());
        view.display();

        assertEquals("canvas 50 145 410 220\n"
            + "shape Rectangle Rectangle\n"
            + "motion Rectangle 4 10 10 3 3 100 100 100  5 100 100 3 3 100 100 100\n"
            + "motion Rectangle 5 100 100 3 3 100 100 100  10 100 200 5 5 0 0 255", out);
      }
    } catch (IOException e) {
      Assert.fail("Exception " + e);
    }
  }

}
