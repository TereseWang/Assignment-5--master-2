package cs3500.animation.model;

import cs3500.animatior.shape.Color;
import cs3500.animatior.shape.Posn;
import cs3500.animatior.shape.Shape;

import static java.lang.String.format;

/**
 * represent a shape's transition with a timeline, which is inclusive at the start point but
 * exclusive at the endpoint. The startShape is the start state of the motion and endshape is the
 * end state of the motion.
 */
public class Motion {

  private int startTick;
  private int endTick;
  private Shape startShape;
  private Shape endShape;

  /**
   * construct a Motion with given startshape, endshape, starttick and endtick.
   *
   * @param start   the start time of the motion
   * @param end     the end time of the motion
   * @param startS  the start shape of motion
   * @param enshape the end shape of the motion
   */
  public Motion(int start, int end, Shape startS, Shape enshape) {
    if (start < 0 || end < start || startS == null || enshape == null ||
            !startS.isSameType(enshape)) {
      throw new IllegalArgumentException("can not construct a motion because 1condi: " + (start < 0)
              + " 2condi: " + (end <= start) + " 3condi: " + (startS == null) + " 4condi: "
              + (enshape == null) + " showStart tick: " + start + " endTick: " + end);
    }

    this.startTick = start;
    this.endTick = end;
    this.startShape = startS;
    this.endShape = enshape;
  }

  /**
   * get the shape after motion.
   *
   * @return shape the end shape of this motion
   */
  public Shape getFinalImages() {
    return endShape.copyShape();
  }

  /**
   * determine whether the given motion is adjacent next to this motion.
   *
   * @param other given motion
   * @return true if the other motion if right after this motion
   */
  public boolean adjNext(Motion other) {
    return endTick == other.getStartTick() && endShape.equals(other.getStartShape());
  }

  /**
   * determine whether the given motion is come right before to this motion.
   *
   * @param other given motion
   * @return true if the other motion is right before this motion
   */
  public boolean adjPrior(Motion other) {
    return other.getEndTick() == startTick && other.getFinalImages().equals(startShape);
  }

  /**
   * make a copy of this Motion.
   *
   * @return Motion as the copy
   */
  public Motion clone() {
    return new Motion(startTick, endTick, startShape.copyShape(), endShape.copyShape());
  }

  /**
   * compute the length of the timeline of this motion.
   *
   * @return int as the length of timeline
   */
  public int getPeriod() {
    return endTick - startTick;
  }

  /**
   * get the starting point of the timeline.
   *
   * @return int as the starting point
   */
  public int getStartTick() {
    return this.startTick;
  }

  /**
   * Get the ending point of the timeline.
   *
   * @return int as ending point
   */
  public int getEndTick() {
    return this.endTick;
  }

  /**
   * push the timeline forward with the length of given period.
   *
   * @param period the given length
   * @throws IllegalArgumentException if it can't be push forward at that long or the period is a
   *                                  negative number
   */
  public void pushForward(int period) {
    if (startTick - period < 0 || period < 0) {
      throw new IllegalArgumentException("can't push this motion forward");
    }
    startTick = startTick - period;
    endTick = endTick - period;

  }

  /**
   * push the timeline backward with the length of given period.
   *
   * @param period the given length
   * @throws IllegalArgumentException if the period is a negative number
   */
  public void pushBackward(int period) {
    if (period < 0) {
      throw new IllegalArgumentException("Cannot push this motion backward");
    }
    startTick = startTick + period;
    endTick = endTick + period;
  }

  /**
   * extend the timeline to the given point from endpoint to the right.
   *
   * @param endpoint the given point
   */
  public void changeEndTick(int endpoint) {
    if (endpoint < 0 || endpoint <= startTick) {
      throw new IllegalArgumentException("Invalid endpoint");
    }
    endTick = endpoint;
  }

  /**
   * Extend the timeline to the given point from starter point to the left.
   *
   * @param startpoint the given startpoint
   * @throws IllegalArgumentException if the startpoint is greater than the endpoint of the
   *                                  startpoint is less than zero
   */
  public void changeStartTick(int startpoint) {
    if (startpoint < 0 || startpoint >= endTick) {
      throw new IllegalArgumentException("Invalid startpoint");
    }
    startTick = startpoint;
  }

  /**
   * change the color of the shape within this timeLine.
   *
   * @param color the desired color
   */
  public void changeColor(Color color) {
    startShape.changeColor(color);
  }

  /**
   * change the position of the final shape.
   *
   * @param position the desired position
   */
  public void changePosition(Posn position) {
    endShape.changePosn(position);
  }

  /**
   * change the size of the final shape.
   *
   * @param width  the width of the shape
   * @param height the height of the shape
   * @throws IllegalArgumentException if the given width and height are invalid.
   */
  public void changeSize(int width, int height) {
    endShape.changeSize(width, height);
  }

  /**
   * Get the startshape of this motion.
   *
   * @return the startshape of the motion
   */
  public Shape getStartShape() {
    return startShape.copyShape();
  }

  public boolean isChangeColor() {
    return startShape.getColor().equals(endShape.getColor());
  }

  public boolean isChangeSize() {
    return startShape.getWidth() == endShape.getWidth()
            && startShape.getHeight() == endShape.getHeight();
  }

  public boolean isChangePosn() {
    return startShape.getPosition().equals(endShape.getPosition());
  }

  @Override
  public String toString() {
    return format("%d " + startShape.toString() + "  %d " + endShape.toString(), startTick,
            endTick);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (o instanceof Motion) {
      return ((Motion) o).startTick == startTick &&
              ((Motion) o).endTick == endTick &&
              ((Motion) o).startShape.equals(startShape) &&
              ((Motion) o).endShape.equals(endShape);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(startTick + endTick) +
            startShape.hashCode() + endShape.hashCode();
  }
}
