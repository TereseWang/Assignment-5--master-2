package cs3500.animation.model;


import cs3500.animator.shape.Color;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.Shape;

/**
 * represent a shape's transition with a timeline, which is inclusive at the start point but
 * exclusive at the endpoint. The startShape is the start state of the motion and endshape is the
 * end state of the motion.
 */
public class Motion {

  private Frame startKeyFrame;
  private Frame endKeyFrame;

  /**
   * construct a Motion with given startshape, endshape, starttick and endtick.
   *
   * @param start   the start time of the motion
   * @param end     the end time of the motion
   * @param startS  the start shape of motion
   * @param enshape the end shape of the motion
   * @throws IllegalArgumentException if the given shapes is null of the time is invalid
   */
  public Motion(int start, int end, Shape startS, Shape enshape) {
    if (start < 0 || end < start || startS == null || enshape == null ||
        !startS.isSameType(enshape)) {
      throw new IllegalArgumentException("can not construct a motion because 1condi: " + (start < 0)
          + " 2condi: " + (end <= start) + " 3condi: " + (startS == null) + " 4condi: "
          + (enshape == null) + " showStart tick: " + start + " endTick: " + end);
    }
    this.startKeyFrame = new Frame(startS, start);
    this.endKeyFrame = new Frame(enshape, end);
  }

  /**
   * Construct a Motion that input start keyframe and end keyframe.
   *
   * @param start the start keyframe
   * @param end   the end keyframe
   */
  public Motion(Frame start, Frame end) {
    if (start == null || end == null || end.getTime() < start.getTime()
        || start.getTime() < 0 || !start.getShape().isSameType(end.getShape())) {
      throw new IllegalArgumentException("can't construct motion with null frames");
    }
    this.startKeyFrame = start;
    this.endKeyFrame = end;
  }

  /**
   * get the shape after motion.
   *
   * @return shape the end shape of this motion
   */
  public Shape getFinalImages() {
    return endKeyFrame.getShape();
  }

  /**
   * determine whether the given motion is adjacent next to this motion.
   *
   * @param other given motion
   * @return true if the other motion if right after this motion
   */
  public boolean adjNext(Motion other) {
    int endTick = endKeyFrame.getTime();
    return endTick == other.getStartTick() && endKeyFrame.getShape().equals(other.getStartShape());
  }

  /**
   * determine whether the given motion is come right before to this motion.
   *
   * @param other given motion
   * @return true if the other motion is right before this motion
   */
  public boolean adjPrior(Motion other) {
    int startTick = startKeyFrame.getTime();
    return other.getEndTick() == startTick && other.getFinalImages()
        .equals(startKeyFrame.getShape());
  }

  /**
   * make a copy of this motion.
   *
   * @return Motion as the copy
   */
  public Motion clone() {
    return new Motion(new Frame(startKeyFrame), new Frame(endKeyFrame));
  }

  /**
   * compute the length of the timeline of this motion.
   *
   * @return int as the length of timeline
   */
  public int getPeriod() {
    return Math.abs(startKeyFrame.getTime() - endKeyFrame.getTime());
  }

  /**
   * get the starting point of the timeline.
   *
   * @return int as the starting point
   */
  public int getStartTick() {
    return this.startKeyFrame.getTime();
  }

  /**
   * Get the ending point of the timeline.
   *
   * @return int as ending point
   */
  public int getEndTick() {
    return this.endKeyFrame.getTime();
  }

  /**
   * push the timeline forward with the length of given period.
   *
   * @param period the given length
   * @throws IllegalArgumentException if it can't be push forward at that long or the period is a
   *                                  negative number
   */
  public void pushForward(int period) {
    int startTick = startKeyFrame.getTime();
    int endTick = endKeyFrame.getTime();
    if (startTick - period < 0 || period < 0) {
      throw new IllegalArgumentException("can't push this motion forward");
    }
    startTick = startTick - period;
    endTick = endTick - period;
    startKeyFrame.changeTime(startTick);
    endKeyFrame.changeTime(endTick);
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
    int startTick = startKeyFrame.getTime() + period;
    int endTick = endKeyFrame.getTime() + period;

    startKeyFrame.changeTime(startTick);
    endKeyFrame.changeTime(endTick);
  }

  /**
   * extend the timeline to the given point from endpoint to the right.
   *
   * @param endpoint the given point
   */
  public void changeEndTick(int endpoint) {
    if (endpoint < 0 || endpoint <= startKeyFrame.getTime()) {
      throw new IllegalArgumentException("Invalid endpoint");
    }
    endKeyFrame.changeTime(endpoint);
  }

  /**
   * Extend the timeline to the given point from starter point to the left.
   *
   * @param startpoint the given startpoint
   * @throws IllegalArgumentException if the startpoint is greater than the endpoint of the
   *                                  startpoint is less than zero
   */
  public void changeStartTick(int startpoint) {
    if (startpoint < 0 || startpoint >= endKeyFrame.getTime()) {
      throw new IllegalArgumentException("Invalid startpoint");
    }
    startKeyFrame.changeTime(startpoint);
  }

  /**
   * change the color of the shape within this timeLine.
   *
   * @param color the desired color
   */
  public void changeColor(Color color) {
    startKeyFrame.changeColor(color);
  }

  /**
   * change the position of the final shape.
   *
   * @param position the desired position
   */
  public void changePosition(Posn position) {
    endKeyFrame.changePosition(position);
  }

  /**
   * change the size of the final shape.
   *
   * @param width  the width of the shape
   * @param height the height of the shape
   * @throws IllegalArgumentException if the given width and height are invalid.
   */
  public void changeSize(int width, int height) {
    endKeyFrame.changeSize(width, height);
  }

  /**
   * Get the startshape of this motion.
   *
   * @return the startshape of the motion
   */
  public Shape getStartShape() {
    return startKeyFrame.getShape();
  }

  /**
   * check whether the motion has changed the color.
   *
   * @return boolean as
   */
  public boolean isChangeColor() {
    return !getStartShape().getColor().equals(getFinalImages().getColor());
  }

  public boolean isChangeSize() {
    return !(getStartShape().getWidth() == getFinalImages().getWidth()
        && getStartShape().getHeight() == getFinalImages().getHeight());
  }

  public boolean isChangePosn() {
    return !getStartShape().getPosition().equals(getFinalImages().getPosition());
  }

  @Override
  public String toString() {
    return String.format("%d " + getStartShape().toString() + "  %d " + getFinalImages().toString(),
        getStartTick(),
        getEndTick());
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (o instanceof Motion) {
      return ((Motion) o).startKeyFrame.equals(startKeyFrame) &&
          ((Motion) o).endKeyFrame.equals(endKeyFrame);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return startKeyFrame.hashCode() + endKeyFrame.hashCode();
  }
}