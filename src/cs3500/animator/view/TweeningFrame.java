package cs3500.animator.view;

import cs3500.animation.model.Frame;
import cs3500.animation.model.KeyFrameAnimation;
import cs3500.animator.shape.Color;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.Shape;
import java.util.List;
import java.util.Map.Entry;

public class TweeningFrame extends AbstractTweening {

  private int count;
  private KeyFrameAnimation animation;

  public TweeningFrame(int count, KeyFrameAnimation animation) {
    this.count = count;
    this.animation = animation;
  }

  @Override
  public void fillInBlankMotion() {
    int endTime = animation.getLength();

    for (Entry<String, List<Frame>> entry : animation.getAnimate().entrySet()) {
      List<Frame> l = entry.getValue();
      int endFrameTime = l.get(l.size() - 1).getTime();
      if (endFrameTime < endTime) {
        Frame f = new Frame(l.get(l.size() - 1).getShape(), endTime);
        animation.addKeyFrame(entry.getKey(), f);
      }
    }
  }

  @Override
  public Shape getMotionState(String name, int time) {
    if (time < count) {
      return null;
    }
    List<Frame> l = animation.getSequence(name);
    Frame startFrame = null;
    Frame endFrame = null;
    Shape currentShape = null;
    int startTime = 0;
    int endTime = 0;
    for (int i = 0; i < l.size() - 1; i++) {
      Frame f = l.get(i);
      Frame f2 = l.get(i + 1);
      startTime = f.getTime();
      endTime = f2.getTime();
      if (time <= endTime && time >= startTime) {
        startFrame = f;
        endFrame = f2;
        break;
      }
    }
    if (startFrame != null && endFrame != null) {
      Shape startShape = startFrame.getShape();
      Shape endShape = endFrame.getShape();
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
