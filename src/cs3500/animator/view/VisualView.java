package cs3500.animator.view;

import cs3500.animation.model.SimpleAnimation;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class VisualView extends JFrame implements View {

  private VisualPanel panel;
  private SimpleAnimation model;
  private final int top;
  private final int left;
  private final int width;
  private final int height;

  public VisualView(SimpleAnimation model, int top, int left, int width, int height) {
    super();
    this.model = model;
    this.top = top;
    this.left = left;
    this.width = width;
    this.height = height;
    this.setTitle("Animation player");
    this.setBounds(model.getBox());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    panel = new VisualPanel(model);
    panel.setPreferredSize(new Dimension(width, height));

    this.add(panel, BorderLayout.CENTER);
    pack();
    this.setVisible(true);
  }


  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void display() {

  }

  @Override
  public void setCanvas(int top, int left, int width, int height) {
  }

  @Override
  public void setTickPerSeocnd(int tickPerSeocnd) {

  }
}


