package ModelTest;

import cs3500.animation.model.Frame;
import cs3500.animation.model.KeyFrameAnimation;
import cs3500.animator.shape.Color;
import cs3500.animator.shape.Oval;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.Rectangle;
import cs3500.animator.shape.Shape;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class KeyFrameAnimationTest {
  KeyFrameAnimation model;
  List<Frame> list1;
  List<Frame> list2;


  @Before
  public void init() {
    model = new KeyFrameAnimation();
    list1 = new ArrayList<>();
    list2 = new ArrayList<>();
    Shape s = new Rectangle(new Posn(100, 100), new Color(0, 255,0), 10, 10);
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
    Frame f4 = new Frame(s4, 1);
    Frame f5 = new Frame(s4, 2);
    Frame f6 = new Frame(s4, 10);
    list2.add(f3);
    list2.add(f4);
    list2.add(f5);
    list2.add(f6);
    model.declareShape("a", "Rectangle");
    model.declareShape("b", "Oval");
  }

  @Test
  public void testDecalreShape() {
    init();
  }
}
