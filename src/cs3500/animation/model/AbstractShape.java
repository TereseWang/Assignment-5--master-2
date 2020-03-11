package cs3500.animation.model;

/**
 * an abstract calss for shape representation.
 */
public abstract class AbstractShape implements Shape {
  protected Posn position;
  protected Color color;
  protected int width;
  protected int height;

  /**
   * construct a shape with given attributes.
   *
   * @param position as the position of the shape
   * @param color    as the color of the shape
   * @param width    as the width of the shape
   * @param height   as the height of the shape
   * @throws IllegalArgumentException if the given position or color is null
   */
  public AbstractShape(Posn position, Color color, int width, int height) {
    if (position == null || color == null) {
      throw new IllegalArgumentException("position and color of a shape can't be null");
    }
    this.position = position;
    this.color = color;
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
  public void changeSize(int width, int height) {
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
  public int getHeight() {
    return height;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public String toString() {
    return String.format("%d %d %d %d %d %d %d", position.getX(), position.getY(), width, height,
            color.getR(), color.getG(), color.getB());
  }

  @Override
  public abstract boolean equals(Object o);

  @Override
  public abstract Shape copyShape();

  @Override
  public int hashCode() {
    return position.hashCode() + color.hashCode()
            + Integer.hashCode(width) + Integer.hashCode(height);
  }
}
