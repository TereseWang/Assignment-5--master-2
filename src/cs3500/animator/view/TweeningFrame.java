package cs3500.animator.view;

import cs3500.animation.model.KeyFrameAnimation;
import cs3500.animation.model.Motion;
import cs3500.animator.shape.Shape;
import java.util.List;
import java.util.Map.Entry;


/**
 * .
 */
public class TweeningFrame extends AbstractTweening{
  private KeyFrameAnimation animation;
  private int count;

  /**
   *
   * @param animation
   * @param count
   */
  public TweeningFrame(KeyFrameAnimation animation, int count) {
    this.count = count;
    this.animation = animation;
  }

  @Override
  public void fillInBlankMotion() {
    KeyFrameAnimation result = new KeyFrameAnimation();
    int endTime = animation.getLength();

    for (Entry<String, List<Motion>> entry : animation.getAnimate().entrySet()) {
      List<Motion> l = entry.getValue();
      int endMotionTime = l.get(l.size() - 1).getEndTick();
      if (endMotionTime < endTime) {
        Motion m = new Motion(endMotionTime, endTime, l.get(l.size() - 1).getFinalImages(),
            l.get(l.size() - 1).getFinalImages());
        result.addMotion(entry.getKey(), m);
      }
    }
    animation = result;
  }

  @Override
  public Shape getMotionState(String name, int Time) {
    return null;
  }

}
