import static org.junit.Assert.assertEquals;

import cs3500.animation.model.Color;
import cs3500.animation.model.Motion;
import cs3500.animation.model.Posn;
import cs3500.animation.model.Rectangle;
import cs3500.animation.model.Shape;
import cs3500.animation.model.SimpleAnimation;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class AnimationTest {

  SimpleAnimation model;
  Motion m;
  Motion m2;
  Motion m3;
  Motion m4;

  @Before
  public void init() {
    model = new SimpleAnimation();
    Shape s = new Rectangle(new Posn(10, 10), new Color(100, 100, 100), 3, 3);
    Shape s1 = new Rectangle(new Posn(100, 100), new Color(100, 100, 100), 3, 3);
    m = new Motion(4, 5, s, s1);
    Shape s2 = new Rectangle(new Posn(100, 100), new Color(100, 100, 100), 3, 3);
    Shape s3 = new Rectangle(new Posn(100, 200), new Color(0, 0, 255), 5, 5);
    m2 = new Motion(5, 10, s2, s3);
    Shape s4 = new Rectangle(new Posn(100, 200), new Color(0, 0, 255), 5, 5);
    Shape s5 = new Rectangle(new Posn(100, 200), new Color(100, 100, 100), 4, 10);
    m3 = new Motion(10, 23, s4, s5);
    Shape s6 = new Rectangle(new Posn(100, 200), new Color(100, 100, 100), 4, 10);
    Shape s7 = new Rectangle(new Posn(10, 10), new Color(100, 100, 100), 3, 3);
    m4 = new Motion(1, 4, s6, s7);
  }

  @Test
  public void testdeclareShape() {
    init();
    model.declareShape("Rectangle");
    model.declareShape("Circle");
    assertEquals(0, model.getSequence("Rectangle").size());
    assertEquals(0, model.getSequence("Circle").size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testdeclareShapeExisted() {
    init();
    model.declareShape("Rectangle");
    model.declareShape("Rectangle");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidName() {
    init();
    model.declareShape("Rectangle");
    model.addMotion("Rectangleha", m2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidOrder() {
    init();
    model.declareShape("Rectangle");
    model.addMotion("Rectangle", m);
    model.addMotion("Rectangle", m3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidOrderTele() {
    init();
    model.declareShape("Rectangle");
    model.addMotion("Rectangle", m3);
    model.addMotion("Rectangle", m);
  }

  @Test
  public void testAddMotion() {
    init();
    model.declareShape("Rectangle");
    model.addMotion("Rectangle", m2);
    model.addMotion("Rectangle", m3);
    model.addMotion("Rectangle", m);
    model.addMotion("Rectangle", m4);
    ArrayList list = new ArrayList();
    list.add(m4);
    list.add(m);
    list.add(m2);
    list.add(m3);
    assertEquals(list, model.getSequence("Rectangle"));
    assertEquals(23, model.getLength());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteShapeNotFound() {
    init();
    model.deleteShape("Rectangle");
  }

  @Test
  public void testDeleteShape() {
    init();
    model.declareShape("Rectangle");
    model.addMotion("Rectangle", m2);
    model.addMotion("Rectangle", m3);
    model.addMotion("Rectangle", m);
    model.deleteShape("Rectangle");
    model.declareShape("Rectangle");
    assertEquals(0, model.getSequence("Rectangle").size());
  }

  @Test
  public void testDeleteMotion() {

  }

  @Test
  public void testChangeColor() {

  }

  @Test
  public void testChangeSize() {

  }

  @Test
  public void testChangePosition() {

  }

  @Test
  public void testChangeSpeed() {

  }

  @Test
  public void testAnimateDescription() {

  }

  @Test
  public void testGetSequence() {

  }

  @Test
  public void testGetLength() {
    model.declareShape("a");
    model.declareShape("b");
    model.declareShape("c");
    model.addMotion("a", m);
    model.addMotion("b", m4);
    model.addMotion("c", m3);
    assertEquals(23, model.getLength());
  }

  @Test
  public void testGetAnimate() {
    model.declareShape("a");
    model.declareShape("b");
    model.declareShape("c");
    model.addMotion("a", m);
    model.addMotion("b", m4);
    model.addMotion("c", m3);
    assertEquals("shape b Rectangle\n"
            + "motion b 1 100 200 4 10 100 100 100  4 10 10 3 3 100 100 100\n"
            + "\n"
            + "shape a Rectangle\n"
            + "motion a 4 10 10 3 3 100 100 100  5 100 100 3 3 100 100 100\n"
            + "\n"
            + "shape c Rectangle\n"
            + "motion c 10 100 200 5 5 0 0 255  23 100 200 4 10 100 100 100",
        new SimpleAnimation(model.getAnimate()).animateDescription());
  }
}
