package cs3500.animator.view;

import java.io.OutputStreamWriter;

import cs3500.animation.model.Animation;

/**
 * A view that output a svg format file.
 */
public class SVGView implements View{
  Animation model;

  /**
   *
   * @param model
   * @param outputStreamWriter
   */
  public SVGView(Animation model, OutputStreamWriter outputStreamWriter){
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


}
