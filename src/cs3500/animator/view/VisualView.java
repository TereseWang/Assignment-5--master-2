package cs3500.animator.view;

import cs3500.animation.model.SimpleAnimation;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class VisualView extends JFrame implements View {

  private VisualPanel panel;
  private SimpleAnimation model;

  /**
   * @param model
   * @param tickPerSec tick per second.
   */
  public VisualView(SimpleAnimation model, int tickPerSec) {
    super();
    this.model = model;

    this.setTitle("Animation player");
    this.setBounds(model.getBox());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    panel = new VisualPanel(model);
    this.setTickPerSeocnd(tickPerSec);

    this.add(panel, BorderLayout.CENTER);
    pack();
  }


  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void display() {
    this.setVisible(true);
  }

  @Override
  public void setCanvas(int top, int left, int width, int height) {
    this.setBounds(top, left, width, height);
  }

  @Override
  public void setTickPerSeocnd(int tickPerSeocnd) {
    panel.setTickPerSecond(tickPerSeocnd);
  }
}


