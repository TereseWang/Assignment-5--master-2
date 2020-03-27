package cs3500.animator.view;

import java.awt.*;

import cs3500.animation.model.Animation;

/**
 * A view that will generate a textual discription
 */
public class TextualView implements View {
  private final Animation model;
  private final Rectangle canvas;
  private static int X = 0;
  private static int Y = 0;
  private static int WIDTH = 250;
  private static int HEIGHT = 250;

  /**
   * Construct a TextualView with given Model
   *
   * @param model a given not null model
   * @throws IllegalArgumentException if the model given is null
   */
  public TextualView(Animation model) {
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null");
    }
    this.model = model;
    this.canvas = new Rectangle(Y, X, WIDTH, HEIGHT);
  }

  /**
   * Construct a TextualView with given Model and Dimension of Canvas
   *
   * @param model a given not null model
   * @param d     given dimension of the canvas
   * @throws IllegalArgumentException if the model given is null
   */
  public TextualView(Animation model, Rectangle d) {
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null");
    }
    this.model = model;
    if (d == null) {
      this.canvas = new Rectangle(Y, X, WIDTH, HEIGHT);
    } else {
      this.canvas = d;
    }
  }

  @Override
  public void refresh() {
    throw new UnsupportedOperationException("Textual view can't be refresh.");

  }

  @Override
  public void display() {
    StringBuilder output = new StringBuilder("");
    output.append(String.format("canvas %d %d %d %d\n", canvas.y, canvas.x, canvas.width,
            canvas.height));
    output.append(model.animateDescription());
    System.out.print(output);
  }


  @Override
  public void setCanvas(int top, int left, int width, int height) {

  }

  @Override
  public void setTickPerSeocnd(int tickPerSeocnd) {

  }
}
