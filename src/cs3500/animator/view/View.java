package cs3500.animator.view;

import java.io.IOException;

/**
 * To represent a view which display the animation model in either string, svg, moving visual
 * animation format. This interface allows functionality of refresh which refresh the entire canvas
 * or display which put the string or image onto the canvas.
 */
public interface View {

  /**
   * Refresh the view to reflect any changes in the game state.
   *
   * @throws UnsupportedOperationException if the view didn't support this operation
   */
  void refresh();

  /**
   * Make the view visible to start the game session.
   */
  void display() throws IOException;
}
