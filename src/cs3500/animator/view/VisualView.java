package cs3500.animator.view;

import cs3500.animation.model.Animation;
import cs3500.animation.model.SimpleAnimation;
import javax.swing.*;

public class VisualView extends JFrame implements View {
  private JFrame frame;
  private VisualPanel panel;
  private Animation model;

  public VisualView(Animation model){
    super();
    this.model = model;
  }
  public VisualView(int top, int left, int width, int height) {
    super();
    this.model = model;
    this.setTitle("Animation player");
    this.setBounds(top,left,width,height);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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


