import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import cs3500.animation.model.Color;
import cs3500.animation.model.Motion;
import cs3500.animation.model.Posn;
import cs3500.animation.model.Rectangle;
import cs3500.animation.model.Shape;
import cs3500.animation.model.SimpleAnimation;

import static org.junit.Assert.assertEquals;

public class AnimationTest {
  SimpleAnimation model;
  Motion m;
  Motion m2;
  Motion m3;

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
    ArrayList list = new ArrayList();
    list.add(m);
    list.add(m2);
    list.add(m3);
    assertEquals(list, model.getSequence("Rectangle"));
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
    assertEquals(0,model.getSequence("Rectangle").size());
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

  }
}
