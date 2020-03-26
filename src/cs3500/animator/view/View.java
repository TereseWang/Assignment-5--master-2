package cs3500.animator.view;

public interface View {
  /**
   * Refresh the view to reflect any changes in the game state.
   * @throws  UnsupportedOperationException if the view didn't support this operation
   */
  void refresh();

  /**
   * Make the view visible to start the game session.
   */
  void display();

  /**
   * change the dimension of the canvas
   * @param top the y of the top left corner
   * @param left the x of the top left corner
   * @param width the width of the canvas
   * @param height the hight of the canvas
   */
  void setCanvas(int top,int left, int width, int height);
}
