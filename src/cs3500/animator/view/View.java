package cs3500.animator.view;

import java.io.IOException;

/**
 * the view for the project.
 */
public interface View {
  /**
   * Refresh the view to reflect any changes in the game state.
   * @throws  UnsupportedOperationException if the view didn't support this operation
   */
  void refresh();

  /**
   * Make the view visible to start the game session.
   */
  void display() throws IOException;





}
