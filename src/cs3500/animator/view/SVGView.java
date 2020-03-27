package cs3500.animator.view;

import cs3500.animation.model.Animation;

/**
 * A view that output a svg format file.
 */
public class SVGView implements View{
  Animation model;

  /**
   * cons
   * @param model
   */
  public SVGView(Animation model){
    if(model== null){
      throw new IllegalArgumentException("model can't be null");
    }
    this.model = model;

  }
  @Override
  public void refresh() {

  }

  @Override
  public void display() {

  }

  @Override
  public void setCanvas(int top, int left, int width, int height) {

  }

  @Override
  public void setTickPerSeocnd(int tickPerSeocnd) {

  }
}
