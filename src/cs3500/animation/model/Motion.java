package cs3500.animation.model;

/**
 * represent a shape's transition with a timeline, which is inclusive at the start point but
 * exclusive at the endpoint.
 */
public class Motion {
  private int startTick;
  private int endTick;
  private Shape startShape;
  private Shape endShape;

  /**
   * construct a Motion with given
   *
   * @param start
   * @param end
   * @param startS
   * @param enshape
   */
  public Motion(int start, int end, Shape startS, Shape enshape) {
    if (start < 0 || end <= start || startS == null || enshape == null) {
      throw new IllegalArgumentException("can not construct a motion");
    }
    this.startTick = start;
    this.endTick = end;
    this.startShape = startS;
    this.endShape = enshape;
  }


  //modify

  /**
   * push the timeline forward with the length of given period.
   *
   * @param period the given length
   * @throws IllegalArgumentException if it can't be push forward at that long.
   */
  public void pushForward(int period) {
    if (startTick - period < 0) {
      throw new IllegalArgumentException("can't push thie motion forward");
    }
    startTick = startTick - period;
    endTick = endTick - period;

  }

  /**
   * push the timeline backward with the length of given period.
   *
   * @param period the given length
   */
  public void pushBackward(int period) {
    startTick = startTick + period;
    endTick = endTick + period;
  }

  /**
   * extend the timeline to the given point.
   *
   * @param endpoint the given point
   */
  public void changeTimeline(int endpoint) {
    endTick = endpoint;
  }

  /**
   * change the color of the shape within this timeLine
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

  //get info


  /**
   * get the shape after motion.
   *
   * @return shape as the shape after changing
   */
  public Shape getFinalImages() {
    return endShape.copyShape();
  }

  /**
   * get the starting point of the timeline.
   *
   * @return int as the starting point
   */
  public int getStartPoint() {
    return startTick;
  }

  /**
   * determine whether the given motion is adjacent next to this motion.
   *
   * @param other given motion
   * @return boolean as the result
   */
  public boolean isConnect(Motion other) {
    return endTick == other.getStartPoint() && endShape.equals(other.getFinalImages());
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
   * make a copy of this Motion.
   *
   * @return Motion as the copy
   */
  public Motion clone() {
    return new Motion(startTick, endTick, startShape.copyShape(), endShape.copyShape());
  }


}
