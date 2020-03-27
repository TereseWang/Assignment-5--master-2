package cs3500.animator.view;

import cs3500.animation.model.Color;
import cs3500.animation.model.Motion;
import cs3500.animation.model.Posn;
import cs3500.animation.model.Shape;
import cs3500.animation.model.SimpleAnimation;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import javax.swing.JPanel;

public class VisualPanel extends JPanel {

  private SimpleAnimation animation;
  private int count;

  public VisualPanel(SimpleAnimation animation) {
    super();
    this.animation = animation;
    this.count = animation.getStartTime();
  }

  public List<Motion> allMotions() {
    List<Motion> temp = new ArrayList<Motion>();
    for (Entry<String, List<Motion>> entry : animation.getAnimate().entrySet()) {
      List<Motion> value = entry.getValue();
      for (Motion m : value) {
        List<Motion> l = convertToMotions(m, entry.getKey());
        temp.addAll(l);
      }
    }
    return sortMotion(temp);
  }

  public List<Motion> sortMotion(List<Motion> list) {
    List<Motion> result = new ArrayList<Motion>();
    result.addAll(list);
    int n = result.size();
    for (int i = 1; i < n; ++i) {
      Motion key = result.get(i);
      int j = i - 1;
      while (j >= 0 && result.get(j).getStartTick() > key.getStartTick()) {
        result.set(j + 1, result.get(j));
        j = j - 1;
      }
      result.set(j + 1, key);
    }
    return result;
  }


  public List<Motion> convertToMotions(Motion m, String name) {
    int startTime = m.getStartTick();
    int endTime = m.getEndTick();
    Shape currentShape = m.getStartShape();
    List<Motion> result = new ArrayList<Motion>();
    for (int i = startTime; i < endTime; i++) {
      Shape b = getMotionState(name, i + 1);
      Motion a = new Motion(i, i + 1, currentShape, b);
      result.add(a);
      currentShape = b.copyShape();
    }
    return result;
  }

  /**
   * Get the state of the shape with the given name with the current time count. Using the method
   * tweening to find the appropriate motion state.
   *
   * @param name the desired shape name
   * @return the state of the shape
   */
  public Shape getMotionState(String name, int time) {
    if (time < count) {
      throw new IllegalArgumentException("Animation not started");
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
            startShape.changeColor(color);
            startShape.changePosn(postion);
            startShape.changeSize(changeInWidth, changeInHeight);
            currentShape = startShape;
          }
        }
      }
    }
    return currentShape;
  }

  private double tweeningFunction(double a, double b, int ta, int tb, int time) {
    double taa = (double) ta;
    double tbb = (double) tb;
    return (a * ((tbb - time) / (tbb - taa))) + (b * ((time - taa) / (tbb - taa)));
  }

  public int getTime() {
    return count;
  }

  private void setTime(int time) {
    count = time;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    List<Motion> list = allMotions();
    for (Motion m : list) {
      switch (m.getStartShape().getShapeName()) {
        case "Oval":
          m.getStartShape()
          break;
        case "Rectangle":
          break;
        default:
          break;
      }
    }
  }
}
