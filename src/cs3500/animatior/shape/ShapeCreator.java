package cs3500.animatior.shape;

/**
 * a class to create a shape.
 */
public class ShapeCreator {
  /**
   * create a shape accoring to the given type.
   *
   * @param name   type of the shape
   * @param posn   position of the shape
   * @param color  color of the shape
   * @param width  width of the shape
   * @param height height of the shape
   * @return
   */
  public static Shape create(String name, Posn posn, Color color, int width, int height) {
    if (name.equalsIgnoreCase("Rectangle")) {
      return new Rectangle(posn, color, width, height);
    }
    if (name.equalsIgnoreCase("Oval")) {
      return new Oval(posn, color, width, height);
    }
    throw new IllegalArgumentException("can't find the type");
  }

}
