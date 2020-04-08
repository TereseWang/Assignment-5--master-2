package cs3500.animation.model;

import cs3500.animator.shape.Color;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.Shape;
import cs3500.animator.shape.ShapeCreator;
import cs3500.animator.util.AnimationBuilder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public Builder(KeyFrameAnimation model) {
      this.model = model;
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
      Frame f = new Frame(startShape, t);
      model.addKeyFrame(name, f);
      return this;
    }
  }

  /**
   * construct an empty animation that use keyframe.
   */
  public KeyFrameAnimation() {
    super();
  }

  public KeyFrameAnimation(LinkedHashMap<String, List<Frame>> animation) {
    this.animation = animation;
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
    if (sequence.isEmpty()) {
      sequence.add(start);
      sequence.add(end);
    }
    // add to last
    else if (sequence.get(sequence.size() - 1).getTime() == start.getTime()) {
      sequence.add(start);
      sequence.add(end);
    } else if (sequence.get(0).getTime() == end.getTime())  // add to first
    {
      sequence.add(0, end);
      sequence.add(0, start);
    } else {
      throw new IllegalArgumentException(
          "sorry can't add this motion because there is a motion in" +
              "that timeline");
    }
  }


  @Override
  public void addKeyFrame(String name, Frame kf) {
    if (checkKeyFrame(name, kf.getTime())) {
      for (int i = 0; i < animation.get(name).size(); i++) {
        if (animation.get(name).get(i).getTime() == kf.getTime()) {
          if (!animation.get(name).get(i).equals(kf)) {
            animation.get(name).set(i, new Frame(kf));
          }
        }
      }
    } else {
      animation.get(name).add(findPosition(name, kf.getTime()), kf);
    }
  }

  private int findPosition(String name, int time) {
    validate(name);
    List<Frame> copy = new ArrayList<>(animation.get(name));
    for (int i = 0; i < copy.size(); i++) {
      if (copy.get(i).getTime() > time) {
        return i;
      }
    }
    return copy.size();
  }


  @Override
  /**
   * deleting a keyframe.
   */
  public void deleteMotion(String name, int startTick) {
    Frame f = findKeyFrame(name, startTick);
    animation.get(name).remove(f);
  }

  private Frame findKeyFrame(String name, int tick) {
    if (checkKeyFrame(name, tick) == false) {
      throw new IllegalArgumentException("Cannot find the keyframe at the given time");
    }
    List<Frame> copy = new ArrayList<>(animation.get(name));
    Frame result = null;
    for (int i = 0; i < copy.size(); i++) {
      if (copy.get(i).getTime() == tick) {
        result = animation.get(name).get(i);
        break;
      }
    }
    return result;
  }

  private boolean checkKeyFrame(String name, int tick) {
    boolean result = false;
    if (animation.containsKey(name)) {
      List<Frame> copy = new ArrayList<>(animation.get(name));
      for (int i = 0; i < copy.size(); i++) {
        if (copy.get(i).getTime() == tick) {
          result = true;
          break;
        }
      }
    }
    return result;
  }


  @Override
  public void changeColor(String name, Color color, int startTick) {
    Frame f = findKeyFrame(name, startTick);
    if (color == null) {
      throw new IllegalArgumentException("color can't be null");
    }
    f.changeColor(color);

  }

  @Override
  public void changePosition(String name, Posn position, int startTick) {
    Frame f = findKeyFrame(name, startTick);
    f.changePosition(position);
  }

  @Override
  public void changeSize(String name, int width, int height, int startTick) {
    Frame f = findKeyFrame(name, startTick);
    f.changeSize(width, height);
  }

  @Override
  public void changeSpeedAnchorStartPoint(String name, int startTick, int endTick) {
    Frame f = findKeyFrame(name, startTick);
    try {
      Frame frame = findKeyFrame(name, endTick);
      throw new IllegalArgumentException("Can't change the keyframe to given time line because " +
          "there is a keyframe.");
    } catch (IllegalArgumentException ie) {
      f.changeTime(endTick);
    }
  }

  @Override
  public void changeSpeedAnchorEndPoint(String name, int startTick, int endTick) {
    Frame f = findKeyFrame(name, endTick);
    try {
      Frame frame = findKeyFrame(name, startTick);
      throw new IllegalArgumentException("Can't change the keyframe to given time line because " +
          "there is a keyframe.");
    } catch (IllegalArgumentException ie) {
      f.changeTime(startTick);
    }
  }

  @Override
  public LinkedHashMap<String, List<Frame>> getAnimate() {
    LinkedHashMap<String, Integer> sorted = getAnimateHelper();
    LinkedHashMap<String, List<Frame>> result = new LinkedHashMap<>();
    for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
      String s = entry.getKey();
      List<Frame> listMotion = getSequence(s);
      result.put(s, listMotion);
    }
    return result;
  }

  private LinkedHashMap<String, Integer> getAnimateHelper() {
    LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
    LinkedHashMap<String, Integer> forSort = new LinkedHashMap<>();
    for (Map.Entry<String, List<Frame>> entry : animation.entrySet()) {
      if (!entry.getValue().isEmpty()) {
        int i = entry.getValue().get(0).getTime();
        String s = entry.getKey();
        forSort.put(s, i);
      }
    }
    forSort.entrySet().stream().sorted(Map.Entry.comparingByValue())
        .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
    return result;
  }

  @Override
  public List<Frame> getSequence(String name) {
    validate(name);
    List<Frame> listOfFrame = animation.get(name);
    List<Frame> result = new ArrayList<>();
    for (Frame f : listOfFrame) {
      Frame copy = new Frame(f);
      result.add(copy);
    }
    return result;
  }

  @Override
  public int getLength() {
    int result = 0;
    for (Map.Entry<String, List<Frame>> entry : animation.entrySet()) {
      List<Frame> lof = entry.getValue();
      if (!lof.isEmpty()) {
        int time = lof.get(lof.size() - 1).getTime();
        if (result < time) {
          result = time;
        }
      }
    }
    return result;
  }

  @Override
  public int getStartTime() {
    int start = 0;
    int result = 0;
    for (Map.Entry<String, List<Frame>> entry : getAnimate().entrySet()) {
      result = entry.getValue().get(0).getTime();
      break;
    }
    return result;
  }
}