package cs3500.animator.view;

import java.io.IOException;
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
  int tick;

  /**
   * construct a SVGVIew with given model.
   *
   * @param model              the given model
   * @param outputStreamWriter output
   * @param  tickPerSec  tick
   */
  public SVGView(Animation model, OutputStreamWriter outputStreamWriter,int tickPerSec) {
    if (model == null) {
      throw new IllegalArgumentException("model can't be null");
    }
    this.model = (SimpleAnimation) model;
    this.out = outputStreamWriter;
    if(tickPerSec == 0 ){
      tick = 1;
    }else{
      tick = tickPerSec;
    }
  }

  @Override
  public void refresh() {
    throw new UnsupportedOperationException("SVGView does not support refresh");
  }

  private void translate() {
    StringBuilder translated = new StringBuilder();
    LinkedHashMap<String, List<Motion>> animation = model.getAnimate();
// set the view box
    translated.append(String.format("<svg viewBox=\"%d %d %d %d\" version=\"1.1\"\n" +
                    "     xmlns=\"http://www.w3.org/2000/svg\">\n", (int)model.getBox().getX(),
            (int)model.getBox().getY(), (int)model.getBox().getWidth(),
            (int)model.getBox().getHeight()));
    // started to append animation.
    Iterator<String> nameI = animation.keySet().iterator();
  while (nameI.hasNext()) {
      String name = nameI.next();
      Iterator<Motion> listOfMotion = animation.get(name).iterator();
      // only transalte the shape if it has animation.
      if (listOfMotion.hasNext()) {
        Motion first = listOfMotion.next();
        Shape startShape = first.getStartShape();
        //shape
        translated.append(String.format("<%s id=\"%s\" x=\"%.2f\" y=\"%.2f\" width=\"%.2f\" " +
                        "height=\"%.2f\" fill=\"rgb(%d,%d,%d)\" visibility=\"visible\" >\n",
                getSVGShapeType(model.getShapeType(name)), name, startShape.getPosition().getX(),
                startShape.getPosition().getY(), startShape.getWidth(), startShape.getHeight(),
                (int)startShape.getColor().getR(), (int)startShape.getColor().getG(),
                (int)startShape.getColor().getB()));

        //first animation
        translated.append(String.format(" <animate attributeType=\"xml\" begin=\"%d\"" +
                        " dur=\"%d\" attributeName=\"x\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n"
                , first.getStartTick(), first.getEndTick(), startShape.getPosition().getX(),
                first.getFinalImages().getPosition().getX()));
        translated.append(String.format(" <animate attributeType=\"xml\" begin=\"%d\"" +
                        " dur=\"%d\" attributeName=\"x\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n"
                , first.getStartTick(), first.getEndTick(), startShape.getPosition().getY(),
                first.getFinalImages().getPosition().getY()));

        while (listOfMotion.hasNext()) {
          Motion current = listOfMotion.next();
          Shape currentS = current.getStartShape();
          translated.append(String.format(" <animate attributeType=\"xml\" begin=\"%d\"" +
                          " dur=\"%d\" attributeName=\"x\" from=\"%d\" to=\"%d\" fill=\"freeze" +
                          "\" />\n",
                  current.getStartTick(), current.getEndTick(), currentS.getPosition().getX(),
                  current.getFinalImages().getPosition().getX()));
          translated.append(String.format(" <animate attributeType=\"xml\" begin=\"%d\"" +
                          " dur=\"%d\" attributeName=\"x\" from=\"%d\" to=\"%d\" fill=\"freeze\"" +
                          " />\n",
                  current.getStartTick(), current.getEndTick(), currentS.getPosition().getY(),
                  current.getFinalImages().getPosition().getY()));

        }
      }
      translated.append(String.format("</%s>\n", getSVGShapeType(model.getShapeType(name))));
    }
    translated.append("</svg>");
    try {
      out.append(translated);
    } catch (IOException e) {
      System.out.print("an error occur during appending");
      e.printStackTrace();
    }
  }

  private String getSVGShapeType(ShapeType type) {
    switch (type) {
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
    translate();
  }


}
