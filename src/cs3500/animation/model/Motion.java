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

  /**
   * get the shape after motion.
   *
   * @return shape as the shape after changing
   */
  public Shape getFinalImages() {
    return endShape.copyShape();
  }

  /**
   * compute the length of the timeline of this motion.
   *
   * @return int as the length of timeline
   */
  public int getPeriod() {
    return endTick - startTick;
  }

  public int getStartTick() {
    return this.startTick;
  }

  public int getEndTick() {
    return this.endTick;
  }

  //modify

  /**
   * push the timeline forward with the length of given period.
   * @param period the given length
   * @throws IllegalArgumentException if it can't be push forward at that long.
   */
  public void pushForward(int period){
    if(startTick-period<0 || period < 0){
      throw new IllegalArgumentException("can't push this motion forward");
    }
    startTick = startTick - period;
    endTick = endTick -period;

  }

  /**
   * push the timeline backward with the length of given period.
   * @param period the given length
   */
  public void pushBackward(int period){
    if(period < 0) {
      throw new IllegalArgumentException("Cannot push this motion forward");
    }
    startTick = startTick + period;
    endTick = endTick + period;
  }

  /**
   * extend the timeline to the given point.
   * @param endpoint the given point
   */
  public void changeEndTick(int endpoint){
    if(endpoint < 0 || endpoint <= startTick) {
      throw new IllegalArgumentException("Invalid endpoint");
    }
    endTick = endpoint;
  }

  public void changeStartTick(int startpoint) {
    if(startpoint < 0 || startpoint >= endTick) {
      throw new IllegalArgumentException("Invalid startpoint");
    }
    startTick = startpoint;
  }

  /**
   * change the color of the shape within this timeLine
   * @param color the desired color
   */
  public void changeColor(Color color){
    startShape.changeColor(color);
  }

  /**
   * change the position of the final shape.
   * @param position the desired position
   */
  public void changePosition(Posn position){
    endShape.changePosn(position);
  }

  /**
   * change the size of the final shape.
   * @param width the width of the shape
   * @param height the height of the shape
   * @throws IllegalArgumentException if the given width and height are invalid.
   */
  public void changeSize(int width, int height){
    endShape.changeSize(width,height);
  }

}
