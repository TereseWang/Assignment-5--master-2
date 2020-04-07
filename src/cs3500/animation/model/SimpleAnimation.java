package cs3500.animation.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cs3500.animator.shape.Color;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.ShapeType;


/**
 * a simple animation class, use list of motion as representation of sequence of motions.
 */
public class SimpleAnimation extends AbstractAnimation<Motion> {


  /**
   * construct an empty animation.
   */
  public SimpleAnimation() {
    super();
  }

  /**
   * Constructor for testing.
   *
   * @param animation the map of string combined with a list of motions
   */
  public SimpleAnimation(LinkedHashMap<String, List<Motion>> animation) {
    this.animation = animation;
  }


  @Override
  public void addMotion(String name, Motion motion) {
    super.validate(name);
    List<Motion> sequence = animation.get(name);
    int size = sequence.size();
    if (size != 0) {
      Motion last = sequence.get(sequence.size() - 1);
      if (last.adjNext(motion)) {
        sequence.add(motion.clone());
      } else if (sequence.get(0).adjPrior(motion)) {
        sequence.add(0, motion);
      } else {
        throw new IllegalArgumentException("can't add that motion!");
      }
    } else {
      ShapeType sp = contact.get(name);
      ShapeType msp = ShapeType.findShapeType(motion.getStartShape().getShapeName());
      if (sp.equals(msp)) {
        sequence.add(motion.clone());
        return;
      }
      throw new IllegalArgumentException("can't add that motion!");
    }
  }

  @Override
  public void addKeyFrame(String name, Frame kf) {
    Frame f = new Frame(kf.getShape(), kf.getTime() + 1);
    Motion m = new Motion(kf, f);
    addMotion(name, m);
  }


  @Override
  public void deleteMotion(String name, int startTick) {
    validate(name);
    Iterator<Motion> sequenceI = animation.get(name).iterator();
    List<Motion> removeList = new ArrayList<>();
    int start = startTick;
    while (sequenceI.hasNext()) {
      Motion current = sequenceI.next();
      if (current.getStartTick() == start) {
        removeList.add(current);
        start = current.getEndTick();
      }
    }
    if (removeList.size() == 0) {
      throw new IllegalArgumentException("couldn't find the motion start at " + startTick);
    }
    animation.get(name).removeAll(removeList);
  }

  /**
   * Find the motion with the exact same starting point of a shape.
   *
   * @param name      the name of the shape
   * @param startTick the starting point
   * @return Motion as the result
   * @throws IllegalArgumentException if couldn't find such motion
   */
  private Motion findMotionBaseOnS(String name, int startTick) {
    validate(name);
    List<Motion> motionList = animation.get(name);
    for (Motion m : motionList) {
      if (m.getStartTick() == startTick) {
        return m;
      }
    }
    throw new IllegalArgumentException("couldn't find the motion start at " + startTick +
            " in the shape " + name);
  }

  /**
   * Find the motion with the exact same ending point of a shape.
   *
   * @param name    the name of the shape
   * @param endTick the ending point
   * @return Motion as the result * @throws IllegalArgumentException if couldn't find such motion
   */
  private Motion findMotionBaseOnE(String name, int endTick) {
    validate(name);
    List<Motion> motionList = animation.get(name);
    for (Motion m : motionList) {
      if (m.getEndTick() == endTick) {
        return m;
      }
    }
    throw new IllegalArgumentException("couldn't find the motion end at " + endTick +
            " in the shape " + name);
  }

  /**
   * Find the motion with the exact same starting point and the ending point of a shape.
   *
   * @param name      the name of the shape
   * @param startTick the starting point
   * @param endTick   the ending point
   * @return Motion as the result * @throws IllegalArgumentException if couldn't find such motion
   */
  private Motion findMotion(String name, int startTick, int endTick) {
    Motion m = findMotionBaseOnS(name, startTick);
    if (m.getEndTick() == endTick) {
      return m;
    }
    throw new IllegalArgumentException("couldn't find the motion start at " + startTick +
            "and end at " + endTick + " in the shape " + name);
  }

  @Override
  public void changeColor(String name, Color color, int startTick) {
    if (color == null) {
      throw new IllegalArgumentException("color can't be null");
    }
    Motion m = findMotionBaseOnS(name, startTick);
    m.changeColor(color);

  }

  @Override
  public void changePosition(String name, Posn position, int startTick) {
    if (position == null) {
      throw new IllegalArgumentException("position can't be null");
    }
    Motion m = findMotionBaseOnS(name, startTick);
    m.changePosition(position);
  }

  @Override
  public void changeSize(String name, int width, int height, int startTick) {
    Motion m = findMotionBaseOnS(name, startTick);
    m.changeSize(width, height);
  }

  @Override
  public void changeSpeedAnchorStartPoint(String name, int startTick, int endTick) {
    Motion m = findMotionBaseOnS(name, startTick);
    List<Motion> sequence = animation.get(name);
    int desIndex = sequence.indexOf(m);
    int before = m.getPeriod();
    m.changeEndTick(endTick);
    int after = m.getPeriod();
    int changeP = after - before;
    if (changeP < 0) {
      //push all the following motions' timeline forward.
      for (int i = desIndex + 1; i < sequence.size(); i++) {
        sequence.get(i).pushForward(-changeP);
      }
    } else {
      //push all the following motions' timeline backward.
      for (int i = desIndex + 1; i < sequence.size(); i++) {
        sequence.get(i).pushBackward(changeP);
      }
    }
  }

  @Override
  public void changeSpeedAnchorEndPoint(String name, int startTick, int endTick) {
    Motion m = findMotionBaseOnE(name, endTick);
    List<Motion> sequence = animation.get(name);
    int desIndex = sequence.indexOf(m);
    int before = m.getPeriod();
    m.changeStartTick(startTick);
    int after = m.getPeriod();
    int changeP = after - before;
    if (changeP < 0) {
      //push all the previous motions' timeline backward.
      for (int i = 0; i < desIndex; i++) {
        sequence.get(i).pushBackward(-changeP);
      }
    } else {
      //push all the previous motions' timeline forward.
      for (int i = 0; i < desIndex; i++) {
        sequence.get(i).pushForward(changeP);
      }
    }

  }

  @Override
  public LinkedHashMap<String, List<Motion>> getAnimate() {
    LinkedHashMap<String, Integer> l = getAnimateHelper();
    LinkedHashMap<String, List<Motion>> result = new LinkedHashMap<>();
    for (Entry<String, Integer> entry : l.entrySet()) {
      String s = entry.getKey();
      List<Motion> listMotion = getSequence(s);
      result.put(s, listMotion);
    }
    return result;
  }

  private LinkedHashMap<String, Integer> getAnimateHelper() {
    LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
    ArrayList<Integer> l = new ArrayList<>();
    LinkedHashMap<String, Integer> temp = new LinkedHashMap<>();
    for (Entry<String, List<Motion>> entry : animation.entrySet()) {
      if (!entry.getValue().isEmpty()) {
        int i = entry.getValue().get(0).getStartTick();
        String s = entry.getKey();
        l.add(i);
        temp.put(s, i);
      }
    }
    temp.entrySet().stream().sorted(Map.Entry.comparingByValue())
            .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
    return result;
  }


  @Override
  public List<Motion> getSequence(String name) {
    if (!animation.containsKey(name)) {
      throw new IllegalArgumentException("Invalid name");
    }
    List<Motion> l = animation.get(name);
    List<Motion> result = new ArrayList<Motion>() {
    };
    if (getLength() == 0) {
      return result;
    }
    for (int i = 0; i < l.size(); i++) {
      Motion a = l.get(i);
      Motion b = a.clone();
      result.add(b);
    }
    return result;
  }

  @Override
  public int getLength() {
    int result = 0;
    for (Entry<String, List<Motion>> entry : animation.entrySet()) {
      List<Motion> l = entry.getValue();
      if (!l.isEmpty()) {
        int a = l.get(l.size() - 1).getEndTick();
        if (result == 0) {
          result = a;
        }
        if (result < a) {
          result = a;
        }
      }
    }
    return result;
  }

  @Override
  public int getStartTime() {
    int result = 0;
    for (Entry<String, List<Motion>> entry : getAnimate().entrySet()) {
      result = entry.getValue().get(0).getStartTick();
      break;
    }
    return result;
  }

}