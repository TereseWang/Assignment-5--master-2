package cs3500.animator.view;

import cs3500.animation.model.Color;
import cs3500.animation.model.Motion;
import cs3500.animation.model.Posn;
import cs3500.animation.model.Shape;
import cs3500.animation.model.SimpleAnimation;
import java.util.List;
import javax.swing.JPanel;

public class VisualPanel extends JPanel {

  private SimpleAnimation animation;
  private int count;

  public VisualPanel(SimpleAnimation animation) {
    super();
    this.animation = animation;
    this.count = 0;
  }

  /**
   * Get the state of the shape with the given name with the current time count. Using the method
   * tweening to find the appropriate motion state.
   *
   * @param name the desired shape name
   * @return the state of the shape
   */
  public Motion getMotionState(String name) {
    List<Motion> l = animation.getSequence(name);
    Motion currentMotion = null;
    int startTime = 0;
    int endTime = 0;
    for (int i = 0; i < l.size(); i++) {
      Motion m = l.get(i);
      if (count <= endTime && count >= startTime) {
        startTime = m.getStartTick();
        endTime = m.getEndTick();
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
      int timePerid = count - startTime;
      int timeSlot = endTime - startTime;
      if (timeSlot != 0) {
        if (timePerid != 0) {
          if (!startShape.equals(endShape)) {
            double changeInPosnX =
            double changeInPosnY =
            double changeInWidth =
            double changeInHeight =
            double changeInR =
            double changeInG =
            double changeInB =
          }
        }
      }
    }
    return currentMotion;
  }

  private double tweeningFunction(double a, double b, int ta, int tb) {
    return (a * ((tb - count) / (tb - ta))) + (b * ((count - ta) / (tb - ta)));
  }
}
