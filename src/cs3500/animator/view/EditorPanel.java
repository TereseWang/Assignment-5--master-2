package cs3500.animator.view;

import cs3500.animation.model.KeyFrameAnimation;
import cs3500.animation.model.SimpleAnimation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    new TweeningFrame(animation, count).fillInBlankMotion();
    this.loop = true;
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }
}
