package cs3500.animation.model;

import cs3500.animator.shape.Color;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.Shape;
import cs3500.animator.shape.ShapeCreator;
import cs3500.animator.shape.ShapeType;
import cs3500.animator.util.AnimationBuilder;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This is an abstract animation class for animation model so that we can add more animation models
 * with less code.
 **/
public class AbstractAnimation<K> implements Animation<List<K>> {

  /**
   * implement the nested class Builder.
   */
  public static final class Builder implements AnimationBuilder<Animation> {

    AbstractAnimation model;

    public Builder() {
      model = new SimpleAnimation();
    }

    @Override
    public Animation build() {

      return model;
    }

    @Override
    public AnimationBuilder<Animation> setBounds(int x, int y, int width, int height) {
      model.canvas.setBounds(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<Animation> declareShape(String name, String type) {
      model.declareShape(name, type);
      return this;
    }

    @Override
    public AnimationBuilder<Animation> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int t2, int x2,
        int y2, int w2, int h2, int r2, int g2, int b2) {
      Shape startShape;
      Shape endShape;
      Motion m;
      startShape = ShapeCreator.create(model.getShapeType(name), new Posn(x1, y1),
          new Color(r1, g1, b1), w1, h1);
      endShape = ShapeCreator.create(model.getShapeType(name), new Posn(x2, y2), new Color(r2, g2,
              b2),
          w2, h2);

      m = new Motion(t1, t2, startShape, endShape);
      model.addMotion(name, m);

      return this;
    }

    @Override
    public AnimationBuilder<Animation> addKeyframe(String name, int t, int x, int y, int w, int h,
        int r, int g, int b) {
      Shape startShape = ShapeCreator.create(model.getShapeType(name), new Posn(x, y),
          new Color(r, g, b), w, h);
      Motion m = new Motion(t, t + 1, startShape, startShape);

      return this;
    }

  }
  protected LinkedHashMap<String, List<K>> animation;
  protected HashMap<String, ShapeType> contact;
  protected Rectangle canvas;
  private static int X = 0;
  private static int Y = 0;
  private static int WIDTH = 600;
  private static int HEIGHT = 600;

  public AbstractAnimation(){
    animation = new LinkedHashMap<>();
    contact = new LinkedHashMap<>();
    canvas = new Rectangle(X, Y, WIDTH, HEIGHT);
  }


  public AbstractAnimation(LinkedHashMap<String, List<K>> animation) {
    this.animation = animation;
  }
  @Override
  public void declareShape(String name, String type) {
    if (animation.containsKey(name) || contact.containsKey(name)) {
      throw new IllegalArgumentException("Shape " + name + "has been declared.");
    }
    animation.put(name, new ArrayList<>());
    contact.put(name, ShapeType.findShapeType(type));
  }

  @Override
  public void addMotion(String name, Motion motion) {

  }

  @Override
  public void deleteShape(String name) {

  }

  @Override
  public void deleteMotion(String name, int startTick) {

  }

  @Override
  public void changeColor(String name, Color color, int startTick, int endTick) {

  }

  @Override
  public void changePosition(String name, Posn position, int startTick, int endTick) {

  }

  @Override
  public void changeSize(String name, int width, int height, int startTick, int endTick) {

  }

  @Override
  public void changeSpeedAnchorStartPoint(String name, int startTick, int endTick) {

  }

  @Override
  public void changeSpeedAnchorEndPoint(String name, int startTick, int endTick) {

  }

  @Override
  public LinkedHashMap<String, List<K>> getAnimate() {
    return null;
  }

  @Override
  public String animateDescription() {
    return null;
  }

  @Override
  public List<K> getSequence(String name) {
    return null;
  }

  @Override
  public int getLength() {
    return 0;
  }

  @Override
  public int getStartTime() {
    return 0;
  }

  @Override
  public Rectangle getBox() {
    return null;
  }

  @Override
  public ShapeType getShapeType(String name) {
    return null;
  }

}
