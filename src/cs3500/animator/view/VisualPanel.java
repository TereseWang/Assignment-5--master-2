package cs3500.animator.view;

import cs3500.animation.model.Motion;
import cs3500.animation.model.SimpleAnimation;
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


/**
 * JPanel class that do all the drawings and motions for the visual view.
 */
public class VisualPanel extends JPanel implements ActionListener {

  private SimpleAnimation animation;
  private int count;
  private int tickPerSec;

  /**
   * Constructor to run the animation.
   *
   * @param animation     the model of the animation
   * @param tickPerSecond the animation moving rate
   */
  public VisualPanel(SimpleAnimation animation, int tickPerSecond) {
    super();
    if (tickPerSecond == 0) {
      tickPerSecond = 2;
    }
    tickPerSec = tickPerSecond;
    Timer timer = new Timer(1000 / tickPerSec, this);
    this.count = animation.getStartTime();
    this.animation = animation;
    new TweeningMotion(animation, count).fillInBlankMotion();
    timer.start();
  }

  /**
   * finds the speed of the animation.
   *
   * @return speed of the animation
   */
  public int getSpeed() {
    return tickPerSec;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    for (Entry<String, List<Motion>> entry : animation.getAnimate().entrySet()) {
      Shape shape = new TweeningMotion(animation, count).getMotionState(entry.getKey(), count);
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

  // automatically replay the animation when it ended
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