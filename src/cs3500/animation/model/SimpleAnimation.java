package cs3500.animation.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

  @Override
  public void declareShape(String name) {
    animation.put(name, new ArrayList<>());
  }

  @Override
  public void addMotion(String name, Motion motion) {
    validate(name);
    List<Motion> sequence = animation.get(name);
    int size = sequence.size();
    if (size != 0) {
      Motion last = sequence.get(sequence.size() - 1);
      if (last.isConnect(motion)) {
        sequence.add(motion.clone());
      }
    } else {
      sequence.add(motion.clone());
    }

  }

  /**
   * check whether there is a shape is
   *
   * @param name
   */
  private void validate(String name) {

  }

  @Override
  public void deleteShape(String name) {

  }

  @Override
  public void deleteMotion(String name, int startTick) {

  }

  /**
   * Find the motion with the exact same starting point and the ending point of a shpe.
   *
   * @param name the name of the shape
   * @param startTick the starting point
   * @param endTick the ending point
   * @return Motion as the result
   */
  private Motion findMotion(String name, int startTick, int endTick) {
    return null;
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
  public void changeSpeed(String name, int startTick, int endTick) {

  }

  @Override
  public String animateDescription() {
    if(animation.isEmpty()) {
      return "";
    }
    String result = new String();
    for(Entry<String, List<Motion>> entry : animation.entrySet()) {
      String key = entry.getKey().toString();
      List<Motion> l = entry.getValue();
      for(int i = 0; i  < l.size(); i++) {
        result += "motion " + key + " " + l.get(i).toString() + "\n";
      }
    }
    return result;
  }

  @Override
  public List<Motion> getSequence(String name) {
    if (!animation.containsKey(name)) {
      throw new IllegalArgumentException("Invalid name");
    }
    List<Motion> l = animation.get(name);
    List<Motion> result = new ArrayList<Motion>() {};
    for (int i = 0; i < l.size(); i++) {
      Motion a = l.get(i);
      Motion b = a.clone();
      result.add(b);
    }
    return result;
  }

  @Override
  public int getLength() {
    return 0;
  }
}