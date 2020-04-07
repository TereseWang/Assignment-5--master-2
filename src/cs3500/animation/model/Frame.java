package cs3500.animation.model;

import cs3500.animator.shape.Color;
import cs3500.animator.shape.Posn;
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
    this.time = frame.getTime();
  }

  public Shape getShape() {
    return state.copyShape();
  }

  public int getTime() {
    return time;
  }

  public void changeColor(Color color) {
    if (color == null) {
      throw new IllegalArgumentException("color can't be null");
    }
    state.changeColor(color);
  }

  public void changePosition(Posn posn) {
    if (posn == null) {
      throw new IllegalArgumentException("given posn can't be null");
    }
    state.changePosn(posn);
  }

  public void changeSize(double width, double height) {
    state.changeSize(width, height);
  }

  public void changeTime(int time){
    if (time<= 0){
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
  public int hashCode(){
    return Integer.hashCode(time) + state.hashCode();
  }


}