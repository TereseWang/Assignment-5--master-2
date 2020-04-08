package cs3500.animator.controller;

import java.io.IOException;

import cs3500.animator.view.View;

/**
 * controller for the project.
 */
public class SimpleController implements AnimationController {

  View view;

  /**
   * construct a controller with given view.
   *
   * @param v given view
   */
  public SimpleController(View v) {
    view = v;
  }

  @Override
  public void execute() {
    try {
      view.display();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
