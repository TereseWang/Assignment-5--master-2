package cs3500.animation.model;

import cs3500.animator.shape.Shape;

import static java.lang.String.format;

/**
 * represent a keyFrame. It cantains a state and the time of the state.
 */
public class Frame {
  private Shape state;
  private int time;

  /**
   * construct a
   * @param state
   * @param time
   */
  public Frame(Shape state, int time){
    if(state == null || time < 0){
      throw  new IllegalArgumentException("the time cane't be less than zero and state can't be " +
              "null");
    }
    this.state = state;
    this.time = time;
  }


  @Override
  public boolean equals(Object o){
    if (o instanceof Frame){
      return ((Frame) o).state == state && ((Frame) o).time == time;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return time +" " + state.toString();
  }

}
