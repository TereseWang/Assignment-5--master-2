package cs3500.animator.view;

import cs3500.animation.model.Frame;
import cs3500.animation.model.KeyFrameAnimation;
import cs3500.animator.shape.Color;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.Shape;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map.Entry;
import javax.swing.JPanel;
import javax.swing.Timer;

public class EditorPanel extends JPanel implements ActionListener {

  private KeyFrameAnimation animation;
  private int count;
  private Timer timer;
  private boolean loop;
  private int tickPerSec;

  /**
   * Constructor to run the animation.
   *
   * @param animation     the model of the animation
   * @param tickPerSecond the animation moving rate
   */
  public EditorPanel(KeyFrameAnimation animation, int tickPerSecond) {
    super();
    if (tickPerSecond == 0) {
      tickPerSecond = 2;
    }
    tickPerSec = tickPerSecond;
    timer = new Timer(1000 / tickPerSec, this);
    this.count = animation.getStartTime();
    this.animation = animation;
    new TweeningFrame(count, animation).fillInBlankMotion();
    this.loop = true;
  }

  public void startTimer() {
    if (count == animation.getStartTime()) {
      timer.start();
    }
  }

  public void stopTimer() {
    if (count != animation.getStartTime()) {
      timer.stop();
    }
  }

  public void resumeTimer() {
    if (!timer.isRunning() && count != animation.getStartTime()) {
      timer.start();
    }
  }

  public void restartTimer() {
    if (count != animation.getStartTime()) {
      count = animation.getStartTime();
      timer.start();
    }
  }

  public void loopEnableDisable() {
    if (loop == true) {
      loop = false;
    } else {
      loop = true;
      restartTimer();
    }
  }

  public void changeSpeed(int tickPerSecond) {
    this.tickPerSec = tickPerSecond;
    timer.setDelay(1000 / tickPerSec);
  }

  public int getSpeed() {
    return this.tickPerSec;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    for (Entry<String, List<Frame>> entry : animation.getAnimate().entrySet()) {
      Shape shape = new TweeningFrame(count, animation).getMotionState(entry.getKey(), count);
      if (shape != null) {
        Color shapeColor = shape.getColor();
        Posn shapePosn = shape.getPosition();
        double shapeWidth = shape.getWidth();
        double shapeHeight = shape.getHeight();
        switch (shape.getShapeName()) {
          case "Oval":
            g2d.setColor(new java.awt.Color((int) shapeColor.getR(), (int) shapeColor.getG(),
                (int) shapeColor.getB()));
            g2d.fillOval((int) shapePosn.getX(), (int) shapePosn.getY(),
                (int) shapeWidth,
                (int) shapeHeight);
            break;
          case "Rectangle":
            g2d.setColor(new java.awt.Color((int) shapeColor.getR(), (int) shapeColor.getG(),
                (int) shapeColor.getB()));
            g2d.fillRect((int) shapePosn.getX(), (int) shapePosn.getY(),
                (int) shapeWidth,
                (int) shapeHeight);
            break;
          default:
            break;
        }
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (count == animation.getLength()) {
      if (loop == true && count == animation.getLength()) {
        count = animation.getStartTime();
        repaint();
      }
      if (loop == false && count == animation.getLength()) {
        timer.stop();
      }
    }
    count++;
    repaint();
  }
}
