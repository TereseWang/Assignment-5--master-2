package cs3500.animator.view;

import cs3500.animation.model.KeyFrameAnimation;
import cs3500.animator.shape.Shape;

public class TweeningFrame extends AbstractTweening{
  private int count;
  private KeyFrameAnimation animation;
  public TweeningFrame(int count, KeyFrameAnimation animation) {
    this.count = count;
    this.animation = animation;
  }

  @Override
  public void fillInBlankMotion() {

  }

  @Override
  public Shape getMotionState(String name, int Time) {
    return null;
  }
}
