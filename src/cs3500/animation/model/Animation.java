package cs3500.animation.model;

import java.util.Map;

/**
 * represent an Animation that contains shapes and their motion. Work as model for the project.
 */
public interface Animation<K> {


  //add
  /**
   * Add a shape to this animation with given name.
   * @param name as the name of the shape
   */
  void  declareShape(String name);

  /**
   * Add a new motion to the shape with given name.
   * @param name the name of the shape
   * @param motion a new motion
   * @throws  IllegalArgumentException if couldn't find the name or the motion can't be added
   */
  void addMotion(String name, Motion motion);

  //delete

  /**
   * Delete a shape and all its motion as the name specified.
   * @param name the name of the shape
   * @throws IllegalArgumentException if couldn't find such shape
   */
  void deleteShape(String name);

  /**
   * Delete all motion from the given startTick.
   * @param name
   * @param startTick
   * @throws IllegalArgumentException if the move can't be made
   */
  void deleteMotion(String name, int startTick);

//modify

  /**
   * change the color of shape in the motion of given timeline.
   * @param name the name of the shape
   * @param color the color to change
   * @param startTick the start point of the time line
   * @param endTick the end point of the time line
   * @throws  IllegalArgumentException if the move couldn't be made.
   */
  void   changeColor(String name, Color color, int startTick, int endTick);

  /**
   *
   * Make a change of position to an existed motion.
   * @param name the name of the shape
   * @param position the position to change
   * @param startTick the start point of the time line
   * @param endTick the end point of the time line
   * @throws  IllegalArgumentException if the move couldn't be made.
   */
  void   changePosition(String name, Posn position, int startTick, int endTick);

  /**
   *
   * Make a change of the size of  to an existed motion.
   * @param name the name of the shape
   * @param width the width to change
   * @param height the height to change
   * @param startTick the start point of the time line
   * @param endTick the end point of the time line
   * @throws  IllegalArgumentException if the move couldn't be made.
   */
  void   changeSize(String name, int width, int height, int startTick, int endTick);

  /**
   * change the length of the motion at the given start point to the end point.
   * @param name the name of the shape
   * @param startTick the start tick to find
   * @param endTick the end tick to
   * @throws IllegalArgumentException if the move couldn't be made.
   */
  void changeSpeed(String name, int startTick, int endTick);

  // getInfo
  String getFrame(int tick);
  Map<String, K> getAnimation();
  K getSequence(String name);

}
