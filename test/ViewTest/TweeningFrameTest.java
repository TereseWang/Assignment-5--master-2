package ViewTest;

import static org.junit.Assert.*;

import cs3500.animation.model.Frame;
import cs3500.animation.model.KeyFrameAnimation;
import cs3500.animator.shape.Color;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.Rectangle;
import cs3500.animator.shape.Shape;
import cs3500.animator.view.TweeningFrame;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TweeningFrameTest {

  KeyFrameAnimation model;
  List<Frame> list1;
  Shape s;
  Shape s2;
  Shape s3;
  Frame f;
  Frame f1;
  Frame f2;
  TweeningFrame tweeningFrame;

  @Before
  public void init() {
    model = new KeyFrameAnimation();
    list1 = new ArrayList<>();
    s = new Rectangle(new Posn(100, 100), new Color(0, 255, 0), 10, 10);
    f = new Frame(s, 1);
    list1.add(f);
    s2 = new Rectangle(new Posn(100, 100), new Color(0, 255, 0), 100, 100);
    f1 = new Frame(s2, 10);
    list1.add(f1);
    s3 = new Rectangle(new Posn(200, 200), new Color(0, 255, 0), 100, 100);
    f2 = new Frame(s3, 20);
    list1.add(f2);

    model.declareShape("a", "Rectangle");
    model.addKeyFrame("a", f1);
    model.addKeyFrame("a", f);


  }


  @Test
  public void fillInBlankMotion() {
    init();
    assertEquals(10, model.getLength());
    assertEquals(2, model.getSequence("a").size());
    tweeningFrame = new TweeningFrame(3, model);
    tweeningFrame.fillInBlankMotion();
    assertEquals(3, model.getSequence("a").size());
  }

  @Test
  public void getMotionState() {
  }

  @Test
  public void testTweeningFunction() {
  }

}