package cs3500.animation.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * a simple animation class, use list of motion as representation of sequence of motions.
 */
public class SimpleAnimation implements Animation<List<Motion>> {

  private LinkedHashMap<String, List<Motion>> animation;

  /**
   * construct an empty animation.
   */
  public SimpleAnimation() {
    animation = new LinkedHashMap<>();
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
  public void declareShape(String name) {
    if (animation.containsKey(name)) {
      throw new IllegalArgumentException("Shape " + name + "has benn declared.");
    }
    animation.put(name, new ArrayList<>());
  }

  @Override
  public void addMotion(String name, Motion motion) {
    validate(name);
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
      sequence.add(motion.clone());
    }
  }

  /**
   * check if the name exist in the animation.
   *
   * @param name the given name
   * @throws IllegalArgumentException if the name is not in the animation.
   */
  private void validate(String name) {
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
  public void changeColor(String name, Color color, int startTick, int endTick) {
    if (color == null) {
      throw new IllegalArgumentException("color can't be null");
    }
    Motion m = findMotion(name, startTick, endTick);
    m.changeColor(color);

  }

  @Override
  public void changePosition(String name, Posn position, int startTick, int endTick) {
    if (position == null) {
      throw new IllegalArgumentException("position can't be null");
    }
    Motion m = findMotion(name, startTick, endTick);
    m.changePosition(position);
  }

  @Override
  public void changeSize(String name, int width, int height, int startTick, int endTick) {
    Motion m = findMotion(name, startTick, endTick);
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
    LinkedHashMap<Integer, String> l = getAnimateHelper();
    LinkedHashMap<String, List<Motion>> result = new LinkedHashMap<>();
    for (Entry<Integer, String> entry : l.entrySet()) {
      String s = entry.getValue();
      List<Motion> listMotion = getSequence(s);
      result.put(s, listMotion);
    }
    return result;
  }

  private LinkedHashMap<Integer, String> getAnimateHelper() {
    LinkedHashMap<Integer, String> result = new LinkedHashMap<>();
    ArrayList<Integer> l = new ArrayList<>();
    LinkedHashMap<Integer, String> temp = new LinkedHashMap<>();
    for (Entry<String, List<Motion>> entry : animation.entrySet()) {
      if (!entry.getValue().isEmpty()) {
        int i = entry.getValue().get(0).getStartTick();
        String s = entry.getKey();
        l.add(i);
        temp.put(i, s);
      }
    }
    Collections.sort(l);
    for (int i = 0; i < l.size(); i++) {
      int key = l.get(i);
      result.put(key, temp.get(key));
    }
    return result;
  }

  @Override
  public String animateDescription() {
    if (animation.isEmpty()) {
      return "";
    }
    String result = new String();
    // make a change here: change from "animation.entrySet()" to getAnimate.entrySet() in line 262.
    for (Entry<String, List<Motion>> entry : getAnimate().entrySet()) {
      String key = entry.getKey();
      List<Motion> l = entry.getValue();
      if (l.isEmpty()) {
        return "";
      } else {
        String s = l.get(0).getStartShape().getShapeName();
        result += "shape " + key + " " + s + "\n";
        for (int i = 0; i < l.size(); i++) {
          result += "motion " + key + " " + l.get(i).toString() + "\n";
        }
        result += "\n";
      }
    }
    int i = result.lastIndexOf("\n");
    result = result.substring(0, i - 1);
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
}