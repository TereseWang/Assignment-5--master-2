package cs3500.animation.model;

/**
 * represent the shape Rectangle.
 */
public class Rectangle extends AbstractShape {

  public Rectangle(Posn posn, Color c, int w, int h) {
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
}
