package cs3500.animatior.shape;

/**
 * represent the shape Rectangle.
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructor for the rectangle.
   *
   * @param posn the position of the rectangle
   * @param c    the color of the rectangle
   * @param w    the width of the rectangle
   * @param h    the height of the rectangle
   */
  public Rectangle(Posn posn, Color c, double w, double h) {
    super(posn, c, w, h);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (o instanceof Rectangle) {
      return ((Rectangle) o).position.equals(position) && ((Rectangle) o).color.equals(color)
          && ((Rectangle) o).width == width && ((Rectangle) o).height == height;
    } else {
      return false;
    }
  }

  @Override
  public Shape copyShape() {
    return new Rectangle(this.getPosition(), this.getColor(), width, height);
  }

  @Override
  public String getShapeName() {
    return "Rectangle";
  }

  @Override
  public int hashCode() {
    return position.hashCode() + color.hashCode()
        + Integer.hashCode((int) width) + Integer.hashCode((int) height);
  }
}
