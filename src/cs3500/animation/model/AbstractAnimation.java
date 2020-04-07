package cs3500.animation.model;

import cs3500.animator.shape.Color;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.ShapeType;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * This is an abstract animation class for animation model so that we can add more animation models
 * with less code.
 **/
public abstract class AbstractAnimation<K> implements Animation<List<K>> {

  protected LinkedHashMap<String, List<K>> animation;
  protected HashMap<String, ShapeType> contact;
  protected Rectangle canvas;
  private static int X = 0;
  private static int Y = 0;
  private static int WIDTH = 600;
  private static int HEIGHT = 600;

  /**
   * construct an empty animation.
   */
  public AbstractAnimation(){
    animation = new LinkedHashMap<>();
    contact = new LinkedHashMap<>();
    canvas = new Rectangle(X, Y, WIDTH, HEIGHT);
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
  public abstract void addMotion(String name, Motion motion);

  /**
   * check if the name exist in the animation.
   *
   * @param name the given name
   * @throws IllegalArgumentException if the name is not in the animation.
   */
  protected void validate(String name) {
    if (!animation.containsKey(name)) {
      throw new IllegalArgumentException("couldn't find the shape: " + name);
    }
  }

  @Override
  public void deleteShape(String name) {
    validate(name);
    animation.remove(name);
  }

  @Override
  public abstract void deleteMotion(String name, int startTick);

  @Override
  public abstract void changeColor(String name, Color color, int startTick);

  @Override
  public abstract void changePosition(String name, Posn position, int startTick);

  @Override
  public abstract void changeSize(String name, int width, int height, int startTick);

  @Override
  public abstract void changeSpeedAnchorStartPoint(String name, int startTick, int endTick);

  @Override
  public abstract void changeSpeedAnchorEndPoint(String name, int startTick, int endTick);

  @Override
  public abstract LinkedHashMap<String, List<K>> getAnimate();

  @Override
  public abstract List<K> getSequence(String name);

  @Override
  public abstract int getLength();

  @Override
  public abstract int getStartTime();

  @Override
  public Rectangle getBox() {
      return new Rectangle(canvas.getBounds());
  }

  @Override
  public ShapeType getShapeType(String name) {
    return contact.get(name);
  }
}