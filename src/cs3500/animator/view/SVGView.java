package cs3500.animator.view;

import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.List;

import cs3500.animation.model.Animation;
import cs3500.animation.model.Motion;

/**
 * A view that output a svg format file.
 */
public class SVGView implements View{
  Animation model;
  OutputStreamWriter out;

  /**
   *construct a SVGVIew with given model
   * @param model
   * @param outputStreamWriter
   */
  public SVGView(Animation model, OutputStreamWriter outputStreamWriter){
    if(model== null){
      throw new IllegalArgumentException("model can't be null");
    }
    this.model = model;
    this.out = outputStreamWriter;

  }
  @Override
  public void refresh() {
    throw  new UnsupportedOperationException("SVGView does not support refresh");
  }

  private void translate(){
    StringBuilder translated = new StringBuilder();
    LinkedHashMap<String, List<Motion>> animation = model.getAnimate();

   translated.append( String.format("<svg width=\"%d\" height=\"%d\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n"));
  }


  @Override
  public void display() {

  }


}
