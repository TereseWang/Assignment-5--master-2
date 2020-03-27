package cs3500.animator;

import java.io.FileWriter;
import java.util.Scanner;

import cs3500.animation.model.Animation;

/**
 * Entry point for this program.
 */
public final class AnimationPlayer {
  public static void main(String[] args) {

    Animation model;
    Scanner in;
    FileWriter out;

    // New Hotness: Graphical User Interface:
    // 1. Create an instance of the model.
    Animation model = new
    // 2. Create an instance of the view.
    TTTSwingViews view = new TTTSwingViews(model);
    // 3. Create an instance of the controller, passing the view to its constructor.
    TTTSwingController controller = new TTTSwingController(view);
    // 4. Call playGame() on the controller.
    controller.playGame(model);

  }
}