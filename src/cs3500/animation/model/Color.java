package cs3500.animation.model;

/**
 * represent a RGB Color.
 */
public class Color {
  private final int r;
  private final int g;
  private final int b;

  /**
   * Construct a color with given RGB attributes.
   *
   * @param r represent red
   * @param g represent green
   * @param b represent blue
   * @throws IllegalArgumentException if the any of the given attributes is not valid
   */
  public Color(int r, int g, int b) {
    checkValid(r);
    checkValid(g);
    checkValid(b);
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * construct a color with default RGB attributes which all are 255.
   */
  public Color() {
    r = 255;
    g = 255;
    b = 255;
  }

  /**
   * This is a copy constructor.
   *
   * @param c represent the color that need to be copied
   */
  public Color(Color c) {
    this.r = c.getR();
    this.g = c.getG();
    this.b = c.getB();
  }


  /**
   * return Red attribute of this color.
   *
   * @return int as red value
   */
  public int getR() {
    return r;
  }

  /**
   * return blue attribute of this color.
   *
   * @return int as blue value
   */
  public int getB() {
    return b;
  }

  /**
   * return greenattribute of this color.
   *
   * @return int as green value
   */
  public int getG() {
    return g;
  }


  /**
   * check whether the given int is valid for a RGB attribute, which means greater or equal to zero
   * and smaller or equal to 255.
   *
   * @param i the given int
   * @throws IllegalArgumentException if the int is invalid
   */
  private void checkValid(int i) {
    if (i < 0 || i > 255) {
      throw new IllegalArgumentException("Not a valid RGB attribute!");
    }
  }

  @Override
  public String toString() {
    return String.format("(%d, %d, &d)", r, g, b);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (o instanceof Color) {
      return ((Color) o).r == r && ((Color) o).g == g && ((Color) o).b == b;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(r) + Integer.hashCode(g) + Integer.hashCode(b);
  }
}
