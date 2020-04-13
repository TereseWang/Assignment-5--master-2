package modelTest;

import static org.junit.Assert.assertEquals;

import cs3500.animation.model.Frame;
import cs3500.animation.model.KeyFrameAnimation;
import cs3500.animation.model.KeyFrameAnimation.Builder;
import cs3500.animator.shape.Color;
import cs3500.animator.shape.Oval;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.Rectangle;
import cs3500.animator.shape.Shape;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * test cases for builder class to test all public methods in the frame class to see if they works
 * properly as desired.
 */
public class BuilderTest {

  Builder builder;
  KeyFrameAnimation model;
  List<Frame> list1;
  List<Frame> list2;

  @Before
  public void init() {
    model = new KeyFrameAnimation();
    list1 = new ArrayList<>();
    list2 = new ArrayList<>();
    Shape s = new Rectangle(new Posn(100, 100), new Color(0, 255, 0), 10, 10);
    Frame f = new Frame(s, 1);
    list1.add(f);
    Shape s2 = new Rectangle(new Posn(100, 100), new Color(0, 255, 0), 100, 100);
    Frame f1 = new Frame(s2, 10);
    list1.add(f1);
    Shape s3 = new Rectangle(new Posn(200, 200), new Color(0, 255, 0), 100, 100);
    Frame f2 = new Frame(s3, 20);
    list1.add(f2);
    Shape s4 = new Oval(new Posn(300, 300), new Color(0, 200, 0), 30, 30);
    Frame f3 = new Frame(s4, 1);
    Frame f5 = new Frame(s4, 2);
    Frame f6 = new Frame(s4, 10);
    list2.add(f3);
    list2.add(f5);
    list2.add(f6);
    model.declareShape("a", "Rectangle");
    model.declareShape("b", "Oval");
    model.addKeyFrame("a", f);
    model.addKeyFrame("a", f1);
    model.addKeyFrame("a", f2);
    model.addKeyFrame("b", f3);
    model.addKeyFrame("b", f5);
    model.addKeyFrame("b", f6);
    builder = new Builder(model);
  }

  @Test
  public void testBuilder() {
    init();
    assertEquals(
        "{a=[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, 20 200 200 100 100 0 255 0], "
            + "b=[1 300 300 30 30 0 200 0, 2 300 300 30 30 0 200 0, "
            + "10 300 300 30 30 0 200 0]}", builder.build().getAnimate().toString());
  }

  @Test
  public void testSetBounds() {
    init();
    assertEquals("java.awt.Rectangle[x=0,y=0,width=600,height=600]",
        builder.build().getBox().toString());
    assertEquals("java.awt.Rectangle[x=100,y=100,width=100,height=100]",
        builder.setBounds(100, 100, 100, 100).build().getBox().toString());
  }

  @Test
  public void testDeclareShape() {
    init();
    assertEquals(
        "{a=[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, 20 200 200 100 100 0 255 0], "
            + "b=[1 300 300 30 30 0 200 0, 2 300 300 30 30 0 200 0, 10 300 300 30 30 0 200 0], "
            + "c=[1 100 100 10 10 200 200 200]}",
        builder.declareShape("c", "Oval").addKeyframe("c", 1, 100, 100, 10, 10, 200, 200, 200)
            .build().getAnimate().toString());
  }

  @Test
  public void testAddMotion() {
    init();
    assertEquals("{a=[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, "
            + "20 200 200 100 100 0 255 0, 20 200 200 100 100 0 255 0, "
            + "30 100 100 100 100 0 255 0], b=[1 300 300 30 30 0 200 0, 2 300 300 30 30 0 200 0, "
            + "10 300 300 30 30 0 200 0]}",
        builder.addMotion("a", 20, 200, 200, 100, 100, 0, 255, 0, 30, 100, 100, 100, 100, 0, 255, 0)
            .build().getAnimate().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddMotion() {
    init();
    assertEquals("{a=[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, "
        + "20 200 200 100 100 0 255 0, 20 200 200 100 100 0 255 0, "
        + "30 100 100 100 100 0 255 0], b=[1 300 300 30 30 0 200 0, "
        + "1 300 300 30 30 0 200 0, 2 300 300 30 30 0 200 0, 10 300 300 30 30 0 200 0]}", builder
        .addMotion("a", 22, 200, 200, 100, 100, 0, 255, 0, 30, 100, 100, 100, 100, 0, 255, 0)
        .build().getAnimate().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddOverlappingMotin() {
    init();
    assertEquals("",
        builder.addMotion("a", 15, 200, 200, 100, 100, 0, 255, 0, 30, 100, 100, 100, 100, 0, 255, 0)
            .build().getAnimate().toString());
  }

  @Test
  public void testAddKeyFrame() {
    init();
    assertEquals("{a=[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, "
            + "20 100 100 100 100 0 255 0], "
            + "b=[1 300 300 30 30 0 200 0, 2 300 300 30 30 0 200 0, 10 300 300 30 30 0 200 0]}",
        builder.addKeyframe("a", 20, 100, 100, 100, 100, 0, 255, 0).build().getAnimate()
            .toString());
  }

  @Test
  public void testAddKeyFrameInBetween() {
    init();
    assertEquals("{a=[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, "
            + "15 100 100 100 100 0 255 0, 20 200 200 100 100 0 255 0], "
            + "b=[1 300 300 30 30 0 200 0, 2 300 300 30 30 0 200 0, 10 300 300 30 30 0 200 0]}",
        builder.addKeyframe("a", 15, 100, 100, 100, 100, 0, 255, 0).build().getAnimate()
            .toString());
  }

  @Test
  public void testAddKeyFrameWithSameTimeInterval() {
    init();
    assertEquals(
        "{a=[1 100 100 10 10 0 255 0, 10 200 200 100 100 0 255 0, 20 200 200 100 100 0 255 0], "
            + "b=[1 300 300 30 30 0 200 0, 2 300 300 30 30 0 200 0, 10 300 300 30 30 0 200 0]}",
        builder.addKeyframe("a", 10, 200, 200, 100, 100, 0, 255, 0).build().getAnimate()
            .toString());
  }
}
