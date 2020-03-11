package cs3500.animation.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * a simple animation class, use list of motion as representation of sequence of motions.
 */
public class SimpleAnimation implements Animation<List<Motion>> {
  LinkedHashMap<String, List<Motion>> animation;

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
    List<Motion> sequence =  animation.get(name);
    Motion last = sequence.get(sequence.size() - 1);
    if(last.get)
  }

  /**
   * check whether there is a shape's name as the given string.
   *
   * @param name
   * @throws IllegalArgumentException if couldn't find it.
   */
  private void validate(String name) {
    Set<String> nameSet = animation.keySet();
    Iterator nameI = nameSet.iterator();
    boolean find = false;
    while (nameI.hasNext()) {
      if (nameI.next().equals(name)) {
        find = true;
      }
    }
    if (!find) {
      throw new IllegalArgumentException("couldn't find the shape: " + name);
    }
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
  public void changeSpeed(String name, int startTick, int endTick) {

  }

  @Override
  public String animateDescription() {
    return null;
  }

  @Override
  public List<Motion> getSequence(String name) {
    return null;
  }
}
