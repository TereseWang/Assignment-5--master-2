package cs3500.animation.model;

import cs3500.animator.shape.Color;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.Shape;

/**
 * This class represent a single frame which as the time indicating what time frame they located and
 * a shape indicating the current state at the that time frame.
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
    this.time = frame.getTime();
  }

  /**
   * Return the current state of this frame.
   *
   * @return the shape as the state of this frame
   */
  public Shape getShape() {
    return state.copyShape();
  }

  /**
   * Return the time of this frame.
   *
   * @return int as the time of this frame
   */
  public int getTime() {
    return time;
  }

  /**
   * Change the color of this frame.
   *
   * @param color the desired color to be replaced with the current color of the shape
   * @throws IllegalArgumentException if the color is null
   */
  public void changeColor(Color color) {
    if (color == null) {
      throw new IllegalArgumentException("color can't be null");
    }
    state.changeColor(color);
  }

  /**
   * Change the position of this frame.
   *
   * @param posn the desired position to be replaced with the current position of the shape
   * @throws IllegalArgumentException if the posn is null
   */
  public void changePosition(Posn posn) {
    if (posn == null) {
      throw new IllegalArgumentException("given posn can't be null");
    }
    state.changePosn(posn);
  }

  /**
   * Change the size of the shape of this frame.
   *
   * @param width  the desired width to be replaced with the current width of the shape
   * @param height the desired height to be replaced with the current height of the shape
   */
  public void changeSize(double width, double height) {
    state.changeSize(width, height);
  }

  /**
   * Change the time of this frame.
   *
   * @param time the desired time to be replaced with the current time of the shape
   * @throws IllegalArgumentException if the given time if negative
   */
  public void changeTime(int time) {
    if (time <= 0) {
      throw new IllegalArgumentException("time can't be less than 0");
    }
    this.time = time;
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

  @Override
  public int hashCode() {
    return Integer.hashCode(time) + state.hashCode();
  }
}