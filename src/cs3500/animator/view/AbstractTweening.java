package cs3500.animator.view;

import cs3500.animator.shape.Shape;

/**
 * represents an abstract class for tweening, which is a function class that keeps the motion of
 * frame lasts if there is no image in between the given time.
 */
public abstract class AbstractTweening {

  /**
   * If the shape does not have any motions in between the very start time of the whole animation
   * and the end time, add motions to make it just stay there and not move.
   */
  abstract public void fillInBlankMotion();

  /**
   * Get the state of the shape with the given name with the current time count. Using the method
   * tweening to find the appropriate motion state.
   *
   * @param name the desired shape name
   * @return the state of the shape
   */
  abstract public Shape getMotionState(String name, int time);

  /**
   * The tweening function to help calculating the motion state of each shape with the given time.
   *
   * @param a    the start state
   * @param b    the end state
   * @param ta   the start time
   * @param tb   the end time
   * @param time the current time
   * @return the result of the tweening function
   */
  protected double tweeningFunction(double a, double b, int ta, int tb, int time) {
    double taa = ta;
    double tbb = tb;
    return (a * ((tbb - time) / (tbb - taa))) + (b * ((time - taa) / (tbb - taa)));
  }
}
