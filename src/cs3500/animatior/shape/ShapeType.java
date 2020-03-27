package cs3500.animatior.shape;

/**
 * representing a type of shape.
 */
public enum ShapeType {
  RECTANGLE,
  OVAL;

  /**
   * find the type according to the given name.
   *
   * @param name the given name
   * @return ShapeType as the type
   */
  public static ShapeType findShapeType(String name) {
    if (name.equalsIgnoreCase("Rectangle")) {
      return RECTANGLE;
    }
    if (name.equalsIgnoreCase("Oval") || name.equalsIgnoreCase("ellipse")) {
      return OVAL;
    }
    throw new IllegalArgumentException("can't find the type the given type name is" + name);
  }
}