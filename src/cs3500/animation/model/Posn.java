package cs3500.animation.model;

/**
 * represent a position with x and y axis.
 */
public class Posn {
  private final int x;
  private final int y;

  /**
   * construct a position with given x and y coordinates.
   *
   * @param x represent value in x axis
   * @param y represent value in y axis
   */
  public Posn(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Copy constructor.
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
  public int getX() {
    return x;
  }

  /**
   * get the y value of this position.
   *
   * @return int as value in y
   */
  public int getY() {
    return y;
  }


  @Override
  public String toString() {
    return String.format("(%d, %d)", x, y);
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
    return Integer.hashCode(x) + Integer.hashCode(y);
  }
}
