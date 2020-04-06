package cs3500.animation.model;

import cs3500.animator.shape.Shape;

/**
 * represent a keyFrame. It cantains a state and the time of the state.
 */
public class Frame {
  private Shape state;
  private int time;

  /**
   * construct a frame with a shape and time.
   *
   * @param state the current shape
   * @param time  the time of this shape
   */
  public Frame(Shape state, int time) {
    if (state == null || time < 0) {
      throw new IllegalArgumentException("the time cane't be less than zero and state can't be " +
              "null");
    }
    this.state = state;
    this.time = time;
  }

  /**
   * construct a Frame that copy the content of given frame.
   *
   * @param frame given frame
   */
  public Frame(Frame frame) {
    if (frame == null) {
      throw new IllegalArgumentException("given frame is null");
    }
    this.state = frame.getShape();
    this.time = frame.getTime()

  }


  public Shape getShape() {
    return state.copyShape();
  }

  public int getTime() {
    return time;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Frame) {
      return ((Frame) o).state == state && ((Frame) o).time == time;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return time + " " + state.toString();
  }


}
