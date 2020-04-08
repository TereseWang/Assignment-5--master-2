package cs3500.animator.view;

import cs3500.animation.model.Motion;
import cs3500.animation.model.SimpleAnimation;
import cs3500.animator.shape.Color;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.Shape;
import java.util.List;
import java.util.Map.Entry;

/**
 * represents the tweening class by using simple animation with motions.
 */
public class TweeningMotion extends AbstractTweening {

  private SimpleAnimation animation;
  private int count;

  /**
   * constructs a tweening motion with given count and model.
   *
   * @param animation keyFrameAnimation model
   * @param count current time in running animation
   */
  public TweeningMotion(SimpleAnimation animation, int count) {
    this.count = count;
    this.animation = animation;
  }

  @Override
  public void fillInBlankMotion() {
    SimpleAnimation result = new SimpleAnimation(animation.getAnimate());
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
  public Shape getMotionState(String name, int time) {
    if (time < count) {
      return null;
    }
    List<Motion> l = animation.getSequence(name);
    Motion currentMotion = null;
    Shape currentShape = null;
    int startTime = 0;
    int endTime = 0;
    for (int i = 0; i < l.size(); i++) {
      Motion m = l.get(i);
      startTime = m.getStartTick();
      endTime = m.getEndTick();
      if (time <= endTime && time >= startTime) {
        currentMotion = m;
        break;
      }
    }
    if (currentMotion != null) {
      Shape startShape = currentMotion.getStartShape();
      Shape endShape = currentMotion.getFinalImages();
      Posn startPosition = startShape.getPosition();
      Posn endPosition = endShape.getPosition();
      double startWidth = startShape.getWidth();
      double endWidth = endShape.getWidth();
      double startHeight = startShape.getHeight();
      double endHeight = endShape.getHeight();
      Color startColor = startShape.getColor();
      Color endColor = endShape.getColor();
      int timePerid = time - startTime;
      int timeSlot = endTime - startTime;
      if (timeSlot != 0) {
        if (timePerid != 0) {
          if (!startShape.equals(endShape)) {
            double changeInPosnX = tweeningFunction(startPosition.getX(), endPosition.getX(),
                startTime, endTime, time);
            double changeInPosnY = tweeningFunction(startPosition.getY(), endPosition.getY(),
                startTime, endTime, time);
            double changeInWidth = tweeningFunction(startWidth, endWidth, startTime, endTime, time);
            double changeInHeight = tweeningFunction(startHeight, endHeight, startTime, endTime,
                time);
            double changeInR = tweeningFunction(startColor.getR(), endColor.getR(), startTime,
                endTime, time);
            double changeInG = tweeningFunction(startColor.getG(), endColor.getG(), startTime,
                endTime, time);
            double changeInB = tweeningFunction(startColor.getB(), endColor.getB(), startTime,
                endTime, time);
            Posn postion = new Posn(changeInPosnX, changeInPosnY);
            Color color = new Color(changeInR, changeInG, changeInB);
            if (!startShape.getPosition().equals(endShape.getPosition())) {
              startShape.changePosn(postion);
            }
            if (!startColor.equals(endColor)) {
              startShape.changeColor(color);
            }
            if (startHeight != endHeight) {
              startShape.changeSize(startWidth, changeInHeight);
            }
            if (startWidth != endWidth) {
              startShape.changeSize(changeInWidth, endWidth);
            }
            currentShape = startShape;

          } else {
            currentShape = startShape;
          }
        } else {
          currentShape = startShape;
        }
      } else {
        currentShape = startShape;
      }
    }
    return currentShape;
  }
}
