package ModelTest;

import static org.junit.Assert.assertEquals;

import cs3500.animation.model.Frame;
import cs3500.animation.model.Motion;
import cs3500.animation.model.SimpleAnimation;
import cs3500.animator.shape.Color;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.Rectangle;
import cs3500.animator.shape.Shape;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * A test class to test all public methods in the SimpleAnimation class.
 */
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
    Frame f1 = new Frame(s, 4);
    Frame f2 = new Frame(s1, 5);
    m = new Motion(f1, f2);
    Shape s2 = new Rectangle(new Posn(100, 100), new Color(100, 100, 100), 3, 3);
    Shape s3 = new Rectangle(new Posn(100, 200), new Color(0, 0, 255), 5, 5);
    Frame f3 = new Frame(s2, 5);
    Frame f4 = new Frame(s3, 10);
    m2 = new Motion(f3, f4);
    Shape s4 = new Rectangle(new Posn(100, 200), new Color(0, 0, 255), 5, 5);
    Shape s5 = new Rectangle(new Posn(100, 200), new Color(100, 100, 100), 4, 10);
    Frame f5 = new Frame(s4, 10);
    Frame f6 = new Frame(s5, 23);
    m3 = new Motion(f5, f6);
    Shape s6 = new Rectangle(new Posn(100, 200), new Color(100, 100, 100), 4, 10);
    Shape s7 = new Rectangle(new Posn(10, 10), new Color(100, 100, 100), 3, 3);
    Frame f7 = new Frame(s6, 1);
    Frame f8 = new Frame(s7, 4);
    m4 = new Motion(f7, f8);
  }

  @Test
  public void testDeclareShape() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.declareShape("Circle", "Oval");
    assertEquals(0, model.getSequence("Rectangle").size());
    assertEquals(0, model.getSequence("Circle").size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testdeclareShapeExisted() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.declareShape("Rectangle", "Rectangle");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidName() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangleha", m2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidOrder() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m4);
    model.addMotion("Rectangle", m3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidOrderTele() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m3);
    model.addMotion("Rectangle", m);
  }

  @Test
  public void testAddMotion() {
    init();
    model.declareShape("Rectangle", "Rectangle");
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


  @Test(expected = IllegalArgumentException.class)
  public void testDeleteMotionInvalidS() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m2);
    model.addMotion("Rectangle", m3);
    model.addMotion("Rectangle", m);
    model.deleteMotion("Rectangle", 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteMotionNoName() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m2);
    model.addMotion("Rectangle", m3);
    model.addMotion("Rectangle", m);
    model.deleteMotion("Rectangle2", 5);
  }

  @Test
  public void testDeleteMotion() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m2);
    model.addMotion("Rectangle", m3);
    model.addMotion("Rectangle", m);
    model.deleteMotion("Rectangle", 5);
    List l = new ArrayList();
    l.add(m);
    assertEquals(l, model.getSequence("Rectangle"));
    model.deleteMotion("Rectangle", 4);
    l.remove(m);
    assertEquals(l, model.getSequence("Rectangle"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteMotionEmp() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    List l = new ArrayList();
    model.deleteMotion("Rectangle", 0);
    model.deleteMotion("Rectangle2", 5);
  }

  @Test
  public void testDeleteMotionAll() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m2);
    model.addMotion("Rectangle", m3);
    model.addMotion("Rectangle", m);
    model.deleteMotion("Rectangle", 5);
    List l = new ArrayList();
    l.add(m);
    assertEquals(l, model.getSequence("Rectangle"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorNullC() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m);
    model.changeColor("Rectangle", null, 4);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorWrongS() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m);
    model.changeColor("Rectangle", new Color(), 3);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorWrongE() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m);
    model.changeColor("Rectangle", new Color(), 0);

  }

  @Test
  public void testChangeColor() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m);
    model.changeColor("Rectangle", new Color(), 4);
    Shape sClone = new Rectangle(new Posn(10, 10), new Color(), 3, 3);
    Shape s1Clone = new Rectangle(new Posn(100, 100), new Color(100, 100, 100), 3, 3);
    assertEquals(sClone, model.getSequence("Rectangle").get(0).getStartShape());
    assertEquals(s1Clone, model.getSequence("Rectangle").get(0).getFinalImages());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeSizeNoName() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m);
    model.changeSize("Rectangle!", 1, 1, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeSizeWrongS() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m);
    model.changeSize("Rectangle", 1, 1, 43);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeSizeWrongE() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m);
    model.changeSize("Rectangle", 1, 1, 4);
  }

  @Test
  public void testChangeSize() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m2);
    assertEquals(m2,
        model.getSequence("Rectangle"));
    model.changeSize("Rectangle", 1, 1, 5);
    assertEquals( "5 100 100 3 3 100 100 100  10 100 200 1 1 0 0 255",
        model.getSequence("Rectangle"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangePositionNoName() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m);
    model.changePosition("Rectangle!", new Posn(10, 10), 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangePositionWrongS() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m);
    model.changePosition("Rectangle", new Posn(100, 100), 43);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChanggPositionWrongE() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m);
    model.changePosition("Rectangle", new Posn(100, 100), 4);
  }

  @Test
  public void testChangePosition() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m);
    assertEquals("shape Rectangle Rectangle\n"
            + "motion Rectangle 4 10 10 3 3 100 100 100  5 100 100 3 3 100 100 100",
        model.getSequence("Rectangle"));
    model.changePosition("Rectangle", new Posn(500, 500), 4);
    assertEquals("shape Rectangle Rectangle\n"
            + "motion Rectangle 4 10 10 3 3 100 100 100  5 500 500 3 3 100 100 100",
        model.getSequence("Rectangle"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeEndSpeedWrongName() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m2);
    model.changeSpeedAnchorEndPoint("Circle", 5, 10);
  }

  @Test
  public void testchangeSpeedAnchorEndPointContrast() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m4);// 1 4
    model.addMotion("Rectangle", m);  //4 5
    model.addMotion("Rectangle", m2); // 5  10
    model.addMotion("Rectangle", m3); // 10 23
    model.changeSpeedAnchorEndPoint("Rectangle", 6, 10);
    Motion m4Change = new Motion(2, 5, m4.getStartShape(), m4.getFinalImages());
    Motion mChange = new Motion(5, 6, m.getStartShape(), m.getFinalImages());
    Motion m2Change = new Motion(6, 10, m2.getStartShape(), m2.getFinalImages());
    assertEquals(m4Change, model.getSequence("Rectangle").get(0));
    assertEquals(mChange, model.getSequence("Rectangle").get(1));
    assertEquals(m2Change, model.getSequence("Rectangle").get(2));
  }

  @Test
  public void testchangeSpeedAnchorEndPointExpand() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m);  //4 5
    model.addMotion("Rectangle", m2); // 5  10
    model.addMotion("Rectangle", m3); // 10 23
    model.changeSpeedAnchorEndPoint("Rectangle", 4, 10);
    Motion mChange = new Motion(3, 4, m.getStartShape(), m.getFinalImages());
    Motion m2Change = new Motion(4, 10, m2.getStartShape(), m2.getFinalImages());
    assertEquals(mChange, model.getSequence("Rectangle").get(0));
    assertEquals(m2Change, model.getSequence("Rectangle").get(1));
  }

  @Test
  public void testAnimateDescription() {
    OutputStreamWriter output;
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m);
    model.addMotion("Rectangle", m2);
    assertEquals("shape Rectangle Rectangle\n"
            + "motion Rectangle 4 10 10 3 3 100 100 100  5 100 100 3 3 100 100 100\n"
            + "motion Rectangle 5 100 100 3 3 100 100 100  10 100 200 5 5 0 0 255",
        model.toString());
  }

  @Test
  public void testAnimatedDescriptionEmpty() {
    model.declareShape("Rectangle", "Rectangle");
    assertEquals("", model.getSequence("Rectangle"));
  }


  @Test
  public void testGetSequence() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m);
    model.addMotion("Rectangle", m2);
    model.addMotion("Rectangle", m3);
    model.addMotion("Rectangle", m4);
    assertEquals(4, model.getSequence("Rectangle").size());
    assertEquals("1 100 200 4 10 100 100 100  4 10 10 3 3 100 100 100",
        model.getSequence("Rectangle").get(0).toString());
    assertEquals("4 10 10 3 3 100 100 100  5 100 100 3 3 100 100 100",
        model.getSequence("Rectangle").get(1).toString());
    assertEquals("5 100 100 3 3 100 100 100  10 100 200 5 5 0 0 255",
        model.getSequence("Rectangle").get(2).toString());
    assertEquals("10 100 200 5 5 0 0 255  23 100 200 4 10 100 100 100",
        model.getSequence("Rectangle").get(3).toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSequenceWrongName() {
    init();
    model.declareShape("Rectangle", "Rectangle");
    model.addMotion("Rectangle", m);
    model.addMotion("Rectangle", m2);
    model.addMotion("Rectangle", m3);
    model.addMotion("Rectangle", m4);
    List<Motion> l = model.getSequence("Circle");
  }

  @Test
  public void testGetLength() {
    model.declareShape("a", "Rectangle");
    model.declareShape("b", "Rectangle");
    model.declareShape("c", "Rectangle");
    model.addMotion("a", m);
    model.addMotion("b", m4);
    model.addMotion("c", m3);
    assertEquals(23, model.getLength());
  }

  @Test
  public void testGetLengthNotfromZero() {
    model.declareShape("a", "Rectangle");
    model.declareShape("b", "Rectangle");
    model.declareShape("c", "Rectangle");
    model.addMotion("a", m);
    model.addMotion("c", m3);
    assertEquals(23, model.getLength());
  }

  @Test
  public void testGetLengthEmpty() {
    assertEquals(0, model.getLength());
  }

  @Test
  public void testGetAnimate() {
    model.declareShape("a", "Rectangle");
    model.declareShape("b", "Rectangle");
    model.declareShape("c", "Rectangle");
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
        new SimpleAnimation(model.getAnimate()));
  }

  @Test
  public void testGetEmptyAnimate() {
    model.declareShape("a", "Rectangle");
    assertEquals("", new SimpleAnimation(model.getAnimate()));
  }
}
