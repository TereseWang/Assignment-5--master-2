package cs3500.animator.view;
//random comment added by Lei Bao for testing git
import cs3500.animation.model.Animation;
import java.awt.Graphics2D;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.Timer;

import cs3500.animation.model.Motion;
import cs3500.animation.model.SimpleAnimation;
import cs3500.animatior.shape.Color;
import cs3500.animatior.shape.Posn;
import cs3500.animatior.shape.Shape;

/**
 * JPanel class that do all the drawings and motions.
 */
public class VisualPanel extends JPanel implements ActionListener {

  private Animation<List<Motion>> animation;
  private int count;

  /**
   * Constructor to run the animation.
   *
   * @param animation     the model of the animation
   * @param tickPerSecond the animation moving rate
   */
  public VisualPanel(Animation animation, int tickPerSecond) {
    super();
    if (tickPerSecond == 0) {
      tickPerSecond = 100;
    }
    Timer timer = new Timer(1000 / tickPerSecond, this);
    List<Shape> shapes = new ArrayList<>();
    this.animation = animation;
    setMotionOneTick();
    fillInBlankMotion();
    timer.start();
  }

  /**
   * If the shape does not have any motions in between the very start time of the whole animtion and
   * the end time, add motions to make it just stay there and not move.
   */
  private void fillInBlankMotion() {
    Animation result = new SimpleAnimation(animation.getAnimate());
    int startTime = animation.getStartTime();
    int endTime = animation.getLength();
    for (Entry<String, List<Motion>> entry : animation.getAnimate().entrySet()) {
      List<Motion> l = entry.getValue();
      int endMotionTime = l.get(l.size() - 1).getEndTick();
      if (endMotionTime < endTime) {
        while (endMotionTime < endTime) {
          Motion m = new Motion(endMotionTime, endMotionTime + 1,
                  l.get(l.size() - 1).getFinalImages(), l.get(l.size() - 1).getFinalImages());
          result.addMotion(entry.getKey(), m);
          endMotionTime++;
        }
      }
    }
    animation = result;
  }

  /**
   * Convert all motions with time period more than one tick to a list of motions with only one tick
   * time period, and do that for the whole animation.
   */
  private void setMotionOneTick() {
    SimpleAnimation result = new SimpleAnimation();
    for (Entry<String, List<Motion>> entry : animation.getAnimate().entrySet()) {
      result.declareShape(entry.getKey(), entry.getValue().get(0).getStartShape().getShapeName());
      List<Motion> value = entry.getValue();
      for (Motion m : value) {
        List<Motion> l = convertToMotions(m, entry.getKey());
        for (Motion a : l) {
          result.addMotion(entry.getKey(), a);
        }
      }
    }
    animation = new SimpleAnimation(result.getAnimate());
  }

  /**
   * Convert all motions with time period more than one tick to a list of motions with only one
   * tick.
   *
   * @param m    the given motion to convert
   * @param name the name of the motion in the aniamtion
   * @return a list of motions of that motion with every single motion has one tick period
   */
  private List<Motion> convertToMotions(Motion m, String name) {
    int startTime = m.getStartTick();
    int endTime = m.getEndTick();
    Shape currentShape = m.getStartShape();
    List<Motion> result = new ArrayList<Motion>();
    if (startTime == endTime) {
      result.add(m);
    } else {
      for (int i = startTime; i < endTime; i++) {
        Shape b = getMotionState(name, i + 1);
        Motion a = new Motion(i, i + 1, currentShape, b);
        result.add(a);
        currentShape = b.copyShape();
      }
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
  private Shape getMotionState(String name, int time) {
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

  /**
   * The tweening function to help calculating the motion state of each shape with the given time.
   *
   * @param a    the start state
   * @param b    the end state
   * @param ta   the start time
   * @param tb   the end time
   * @param time the current time
   * @return the result of the tweening function
   */
  private double tweeningFunction(double a, double b, int ta, int tb, int time) {
    double taa = (double) ta;
    double tbb = (double) tb;
    return (a * ((tbb - time) / (tbb - taa))) + (b * ((time - taa) / (tbb - taa)));
  }

  /**
   * Get the time of the current time.
   *
   * @return the time of the current time
   */
  public int getTime() {
    return count;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    for (Entry<String, List<Motion>> entry : animation.getAnimate().entrySet()) {
      for (Motion m : entry.getValue()) {
        if (count == m.getStartTick()) {
          Shape startShape = m.getStartShape();
          Shape endShape = m.getFinalImages();
          Color startColor = startShape.getColor();
          Color endColor = endShape.getColor();
          double startWidth = startShape.getWidth();
          double endWidth = endShape.getWidth();
          Posn startPosn = startShape.getPosition();
          Posn endPosn = endShape.getPosition();
          double startHeight = startShape.getHeight();
          double endHeight = endShape.getHeight();
          switch (startShape.getShapeName()) {
            case "Oval":
              g2d.setColor(new java.awt.Color((int) startColor.getR(), (int) startColor.getG(),
                      (int) startColor.getB()));
              g2d.fillOval((int) startPosn.getX(), (int) startPosn.getY(),
                      (int) startWidth,
                      (int) startHeight);
              break;
            case "Rectangle":
              g2d.setColor(new java.awt.Color((int) startColor.getR(), (int) startColor.getG(),
                      (int) startColor.getB()));
              g2d.fillRect((int) startPosn.getX(), (int) startPosn.getY(),
                      (int) startWidth,
                      (int) startHeight);
              break;
            default:
              break;
          }
        }
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (count == animation.getLength()) {
      count = animation.getStartTime();
      repaint();
    }
    count++;
    repaint();
  }
}