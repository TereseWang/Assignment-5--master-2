package cs3500.animator.shape;

import cs3500.animator.shape.Color;
import cs3500.animator.shape.Posn;

/**
 * an abstract calss for shape representation.
 */
public abstract class AbstractShape implements Shape {

  protected Posn position;
  protected Color color;
  protected double width;
  protected double height;

  /**
   * construct a shape with given attributes.
   *
   * @param position as the position of the shape
   * @param color    as the color of the shape
   * @param width    as the width of the shape
   * @param height   as the height of the shape
   * @throws IllegalArgumentException if the given position or color is null
   */
  public AbstractShape(Posn position, Color color, double width, double height) {
    if (position == null || color == null) {
      throw new IllegalArgumentException("position and color of a shape can't be null");
    }
    this.position = position;
    this.color = color;
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height should not be zero or negative");
    }
    this.width = width;
    this.height = height;
  }

  @Override
  public void changeColor(Color color) {
    this.color = new Color(color);
  }

  @Override
  public void changePosn(Posn position) {
    this.position = new Posn(position);
  }

  @Override
  public void changeSize(double width, double height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("the width and the height needed to br positive");
    }
    this.width = width;
    this.height = height;
  }

  @Override
  public Posn getPosition() {
    return new Posn(position);
  }

  @Override
  public Color getColor() {
    return new Color(color);
  }

  @Override
  public double getHeight() {
    return height;
  }

  @Override
  public double getWidth() {
    return width;
  }

  @Override
  public String toString() {
    return String
        .format("%d %d %d %d %d %d %d", (int) position.getX(), (int) position.getY(), (int) width,
            (int) height, (int) color.getR(), (int) color.getG(), (int) color.getB());
  }

  @Override
  public abstract boolean equals(Object o);

  @Override
  public abstract Shape copyShape();

  @Override
  public boolean isSameType(Shape other) {
    if (other == null) {
      return false;
    }
    return other.getShapeName() == getShapeName();
  }

  @Override
  public abstract String getShapeName();

  @Override
  public abstract int hashCode();
}
