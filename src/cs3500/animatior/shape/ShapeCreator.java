package cs3500.animatior.shape;

/**
 * a class to create a shape.
 */
public class ShapeCreator {
  public enum ShapeType{
    RECTANGLE,
    OVAL;

    public static ShapeType findShapeType(String name){
      if (name.equalsIgnoreCase("Rectangle")) {
        return RECTANGLE;
      }
      if (name.equalsIgnoreCase("Oval")) {
        return OVAL;
      }
      throw new IllegalArgumentException("can't find the type");
    }
    }
  }
  /**
   * create a shape accoring to the given type.
   *
   * @param type  type of the shape
   * @param posn   position of the shape
   * @param color  color of the shape
   * @param width  width of the shape
   * @param height height of the shape
   * @return Shape as the result
   * @throws IllegalArgumentException if couldn't find the type
   */
  public static Shape create(ShapeType type, Posn posn, Color color, int width, int height) {
    switch (type){
      case RECTANGLE:
        return new Rectangle(posn,color, width,height);
      case OVAL:
        return new Oval(posn,color,width,height);
      default:
        throw new IllegalArgumentException("can't create because given type not found");

    }}
