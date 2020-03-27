package cs3500.animator.view;

import cs3500.animation.model.SimpleAnimation;
import javax.swing.*;

public class VisualView extends JFrame implements View {
  private JFrame frame;
  private VisualPanel panel;
  private final int top;
  private final int left;
  private final int width;
  private final int height;

  public VisualView(int top, int left, int width, int height) {
    super();
    this.top = top;
    this.left = left;
    this.width = width;
    this.height = height;
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
}


