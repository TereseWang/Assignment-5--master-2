package viewTest;

import static org.junit.Assert.assertEquals;

import cs3500.animation.model.Frame;
import cs3500.animation.model.KeyFrameAnimation;
import cs3500.animator.shape.Color;
import cs3500.animator.shape.Oval;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.Rectangle;
import cs3500.animator.shape.Shape;
import cs3500.animator.view.EditorPanel;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to test the editorpanel to see if all public methods in editorpanel class is correctly
 * worked as defined.
 */
public class EditorPanelTest {

  EditorPanel panel;
  KeyFrameAnimation model;
  Shape s;
  Shape s2;
  Shape s3;
  Shape s4;
  Frame f;
  Frame f1;
  Frame f2;
  Frame f3;
  Frame f4;
  Frame f5;
  Frame f6;

  @Before
  public void init() {
    model = new KeyFrameAnimation();
    s = new Rectangle(new Posn(100, 100), new Color(0, 255, 0), 10, 10);
    f = new Frame(s, 1);
    s2 = new Rectangle(new Posn(100, 100), new Color(0, 255, 0), 100, 100);
    f1 = new Frame(s2, 10);
    s3 = new Rectangle(new Posn(200, 200), new Color(0, 255, 0), 100, 100);
    f2 = new Frame(s3, 20);
    s4 = new Oval(new Posn(300, 300), new Color(0, 200, 0), 30, 30);
    f3 = new Frame(s4, 1);
    f4 = new Frame(s4, 1);
    f5 = new Frame(s4, 2);
    f6 = new Frame(s4, 10);
    model.declareShape("a", "Rectangle");
    model.declareShape("b", "Oval");
    model.addKeyFrame("a", f1);
    model.addKeyFrame("a", f);
    model.addKeyFrame("a", f2);
    model.addKeyFrame("b", f3);
    model.addKeyFrame("b", f4);
    model.addKeyFrame("b", f5);
    model.addKeyFrame("b", f6);
    panel = new EditorPanel(model, 10);
  }

  @Test
  public void testChangeSpeed() {
    init();
    assertEquals(10, panel.getSpeed());
    panel.changeSpeed(100);
    assertEquals(100, panel.getSpeed());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidChangeSpeed() {
    init();
    panel.changeSpeed(-1);
  }

  @Test
  public void testInsertKeyFrame() {
    init();
    assertEquals("{a=[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, "
            + "20 200 200 100 100 0 255 0], b=[1 300 300 30 30 0 200 0, "
            + "2 300 300 30 30 0 200 0, 10 300 300 30 30 0 200 0, 20 300 300 30 30 0 200 0]}",
        model.getAnimate().toString());
    panel.addKeyFrame("a", new Frame(s, 40));
    assertEquals("{a=[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, "
        + "20 200 200 100 100 0 255 0, 40 100 100 10 10 0 255 0], "
        + "b=[1 300 300 30 30 0 200 0, 2 300 300 30 30 0 200 0, "
        + "10 300 300 30 30 0 200 0, 20 300 300 30 30 0 200 0, "
        + "40 300 300 30 30 0 200 0]}", model.getAnimate().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalDeletingKeyFrameWrongName() {
    init();
    panel.deleteKeyFrame("c", 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalDeletingKeyFrameWrongTime() {
    init();
    panel.deleteKeyFrame("a", 100);
  }

  @Test
  public void testDeleteKeyFrame() {
    init();
    assertEquals("{a=[1 100 100 10 10 0 255 0, 10 100 100 100 100 0 255 0, "
            + "20 200 200 100 100 0 255 0], b=[1 300 300 30 30 0 200 0, "
            + "2 300 300 30 30 0 200 0, 10 300 300 30 30 0 200 0, 20 300 300 30 30 0 200 0]}",
        model.getAnimate().toString());
    panel.deleteKeyFrame("a", 10);
    assertEquals(
        "{a=[1 100 100 10 10 0 255 0, 20 200 200 100 100 0 255 0], "
            + "b=[1 300 300 30 30 0 200 0, 2 300 300 30 30 0 200 0, "
            + "10 300 300 30 30 0 200 0, 20 300 300 30 30 0 200 0]}",
        model.getAnimate().toString());
  }
}
