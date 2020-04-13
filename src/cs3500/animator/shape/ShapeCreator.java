package cs3500.animator.shape;

/**
 * a class to create a shape with the given name and throws Illegal argument exception if the
 * shape name is not created as now.
 */
public class ShapeCreator {


  /**
   * create a shape according to the given type.
   *
   * @param type   type of the shape
   * @param posn   position of the shape
   * @param color  color of the shape
   * @param width  width of the shape
   * @param height height of the shape
   * @return Shape as the result
   * @throws IllegalArgumentException if couldn't find the type
   */
  public static Shape create(ShapeType type, Posn posn, Color color, int width, int height) {
    switch (type) {
      case RECTANGLE:
        return new Rectangle(posn, color, width, height);
      case OVAL:
        return new Oval(posn, color, width, height);
      default:
        throw new IllegalArgumentException("can't create because given type not found");

    }
  }
}
