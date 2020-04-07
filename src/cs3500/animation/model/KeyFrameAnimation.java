package cs3500.animation.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import cs3500.animator.shape.Color;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.Shape;
import cs3500.animator.shape.ShapeCreator;
import cs3500.animator.util.AnimationBuilder;

/**
 * a class represent an animation that uses frame to document the moving of shape. Work as the model
 * for the project.
 */
public class KeyFrameAnimation extends AbstractAnimation<Frame> {

  /**
   * implement the nested class Builder.
   */
  public static final class Builder implements AnimationBuilder<Animation> {

    AbstractAnimation model;

    public Builder() {
      model = new KeyFrameAnimation();
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

  /**
   * construct an empty animation that use keyframe.
   */
  public KeyFrameAnimation() {
    super();
  }

  @Override
  public void addMotion(String name, Motion motion) {
    if (motion == null) {
      throw new IllegalArgumentException("given motion can't be null");
    }
    validate(name);
    Frame start = new Frame(motion.getStartShape(), motion.getStartTick());
    Frame end = new Frame(motion.getFinalImages(), motion.getEndTick());
    List<Frame> sequence = animation.get(name);
    // add to last
    if (sequence.get(sequence.size() - 1).getTime() == start.getTime()) {
      sequence.add(start);
      sequence.add(end);
    } else if (sequence.get(0).getTime() == end.getTime())  // add to first
    {
      sequence.add(0, end);
      sequence.add(0, start);
    } else {
      throw new IllegalArgumentException("sorry can't add this motion because there is a motion in" +
              "that timeline");
    }
  }

  @Override
  /**
   * deleting a keyframe.
   */
  public void deleteMotion(String name, int startTick) {
    Frame f = findKeyFrame(name,startTick);
    animation.get(name).remove(f);
  }

  private Frame findKeyFrame(String name, int tick) {
    validate(name);
    List<Frame> copy = new ArrayList<>(animation.get(name));
    for (int i = 0; i < copy.size(); i++) {
      if (copy.get(i).getTime() == tick) {
        return animation.get(name).get(i);
      }
    }
    throw new IllegalArgumentException("can't find any keyframe with given timeline");
  }


  @Override
  public void changeColor(String name, Color color, int startTick) {
    Frame f = findKeyFrame(name,startTick);
    if(color == null){
      throw  new IllegalArgumentException("color can't be null");
    }
    f.changeColor(color);

  }

  @Override
  public void changePosition(String name, Posn position, int startTick) {
    Frame f = findKeyFrame(name,startTick);
    f.changePosition(position);
  }

  @Override
  public void changeSize(String name, int width, int height, int startTick) {
    Frame f = findKeyFrame(name,startTick);
    f.changeSize(width,height);
  }

  @Override
  public void changeSpeedAnchorStartPoint(String name, int startTick, int endTick) {

  }

  @Override
  public void changeSpeedAnchorEndPoint(String name, int startTick, int endTick) {

  }

  @Override
  public LinkedHashMap<String, List<Frame>> getAnimate() {
    return null;
  }

  @Override
  public List<Frame> getSequence(String name) {
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
}
