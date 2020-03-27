package cs3500.animatior.shape;


/**
 * represent the shape Oval which is a round shaped shape.
 */
public class Oval extends AbstractShape {

  /**
   * construct a shape with given attributes.
   *
   * @param position as the position of the shape
   * @param color    as the color of the shape
   * @param width    as the width of the shape
   * @param height   as the height of the shape
   * @throws IllegalArgumentException if the given position or color is null
   */
  public Oval(Posn position, Color color, double width, double height) {
    super(position, color, width, height);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (o instanceof Oval) {
      return ((Oval) o).position.equals(position) && ((Oval) o).color.equals(color)
              && ((Oval) o).width == width && ((Oval) o).height == height;
    } else {
      return false;
    }
  }

  @Override
  public Shape copyShape() {
    return new Oval(this.getPosition(), this.getColor(), width, height);
  }

  @Override
  public String getShapeName() {
    return "Oval";
  }

  @Override
  public int hashCode() {
    return position.hashCode() + color.hashCode()
            + Integer.hashCode((int) width) + Integer.hashCode((int) height);
  }
}