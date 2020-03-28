package cs3500.animator.view;

import cs3500.animation.model.SimpleAnimation;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * To represent the visual view of the animation.
 */
public class VisualView extends JFrame implements View {
  private VisualPanel panel;
  /**
   * The constructor of the visual view which set up for animation to run.
   *
   * @param model      the animation model to be run with.
   * @param tickPerSec tick per second.
   */
  public VisualView(SimpleAnimation model, int tickPerSec) {
    super();

    this.setTitle("Animation player");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    panel = new VisualPanel(model, tickPerSec);

    JScrollPane scroll = new JScrollPane(panel);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    this.add(scroll);
    pack();
    this.setBounds(model.getBox());
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void display() {
    this.setVisible(true);
  }
}