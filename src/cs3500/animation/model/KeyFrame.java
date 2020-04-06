package cs3500.animation.model;

import cs3500.animator.shape.Shape;

/**
 * represent a keyFrame. It cantains a state and the time of the state.
 */
public class KeyFrame {
  private Shape state;
  private int time;

  public KeyFrame(Shape state, int time){
    if(state == null || time < 0){
      throw  new IllegalArgumentException("the time cane't be less than zero and state can't be " +
              "null");
    }
    this.state = state;
    this.time = time;
  }

  @Override
  public boolean equals(Object o){
    if (o instanceof KeyFrame);
  return true;}

}
