package cs3500.animator.view;

import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import cs3500.animation.model.Animation;
import cs3500.animation.model.Motion;
import cs3500.animation.model.SimpleAnimation;
import cs3500.animatior.shape.Shape;
import cs3500.animatior.shape.ShapeType;

/**
 * A view that output a svg format file.
 */
public class SVGView implements View {
  SimpleAnimation model;
  OutputStreamWriter out;

  /**
   * construct a SVGVIew with given model
   *
   * @param model
   * @param outputStreamWriter
   */
  public SVGView(Animation model, OutputStreamWriter outputStreamWriter) {
    if (model == null) {
      throw new IllegalArgumentException("model can't be null");
    }
    this.model = (SimpleAnimation) model;
    this.out = outputStreamWriter;

  }

  @Override
  public void refresh() {
    throw new UnsupportedOperationException("SVGView does not support refresh");
  }

  private void translate() {
    StringBuilder translated = new StringBuilder();
    LinkedHashMap<String, List<Motion>> animation = model.getAnimate();

    translated.append(String.format("<svg width=\"%d\" height=\"%d\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n"));
    Iterator<String> nameI = animation.keySet().iterator();
    while (nameI.hasNext()) {
      String name = nameI.next();
      Iterator<Motion> listOfMotion = animation.get(name).iterator();
      if (listOfMotion.hasNext()) {
        Motion first = listOfMotion.next();
        Shape startShape = first.getStartShape();
        //shape
        translated.append(String.format("<%s id=\"%s\" x=\"%d\" y=\"%d\" width=\"%d\" " +
                "height=\"%d\" fill=\"rgb(%d,%d,%d)\" visibility=\"visible\" >\n",
                getSVGShapeType(model.getShapeType(name)), name, startShape.getPosition().getX(),
                startShape.getPosition().getY(), startShape.getWidth(),startShape.getHeight(),
                startShape.getColor().getR(), startShape.getColor().getG(),
                startShape.getColor().getB()));
        //first animation
        translated.append(String.format(" <animate attributeType=\"xml\" begin=\"2000.0ms\"" +
                " dur=\"5000.0ms\" attributeName=\"cx\" from=\"500\" to=\"600\" fill=\"freeze\" />",)
      }


      translated.append(String.format("</%s>\n",  getSVGShapeType(model.getShapeType(name))));
    }
  }

  private String getSVGShapeType(ShapeType type){
    switch (type){
      case RECTANGLE:
        return "rect";
      case OVAL:
        return "ellipse";
      default:
        throw new IllegalArgumentException("couldn't find shape");

    }
  }


  @Override
  public void display() {

  }


}
