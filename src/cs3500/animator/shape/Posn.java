package cs3500.animator.shape;

/**
 * represent a position with x and y axis.
 */
public class Posn {

  private final double x;
  private final double y;

  /**
   * construct a position with given x and y coordinates.
   *
   * @param x represent value in x axis
   * @param y represent value in y axis
   */
  public Posn(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Copy constructor.
   *
   * @param p the desired posn to be copied
   */
  public Posn(Posn p) {
    this.x = p.getX();
    this.y = p.getY();
  }

  /**
   * get the x value of this position.
   *
   * @return int as value in x
   */
  public double getX() {
    return x;
  }

  /**
   * get the y value of this position.
   *
   * @return int as value in y
   */
  public double getY() {
    return y;
  }


  @Override
  public String toString() {
    return String.format("(%d, %d)", (int) x, (int) y);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (o instanceof Posn) {
      return ((Posn) o).x == x && ((Posn) o).y == y;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Integer.hashCode((int) x) + Integer.hashCode((int) y);
  }
}
