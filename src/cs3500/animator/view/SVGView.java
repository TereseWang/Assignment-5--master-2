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
   * @param tickPerSec         tick
   */
  public SVGView(Animation model, OutputStreamWriter outputStreamWriter, int tickPerSec) {
    if (model == null) {
      throw new IllegalArgumentException("model can't be null");
    }
    this.model = (SimpleAnimation) model;
    this.out = outputStreamWriter;
    if (tickPerSec == 0) {
      tick = 1;
    } else {
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
                    "     xmlns=\"http://www.w3.org/2000/svg\">\n", (int) model.getBox().getX(),
            (int) model.getBox().getY(), (int) model.getBox().getWidth(),
            (int) model.getBox().getHeight()));
    // started to append animation.
    Iterator<String> nameI = animation.keySet().iterator();
    while (nameI.hasNext()) {
      String name = nameI.next();
      ShapeType type = model.getShapeType(name);
      Iterator<Motion> listOfMotion = animation.get(name).iterator();
      // only translate the shape if it has animation.
      if (listOfMotion.hasNext()) {
        Motion first = listOfMotion.next();
        Shape startShape = first.getStartShape();
        translated.append(paintShape(first, name, startShape));

        //first animation
        translated.append(translateMotions(first, type));

        //append the rest
        while (listOfMotion.hasNext()) {
          Motion current = listOfMotion.next();
          translated.append(translateMotions(current, type));


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

  private StringBuilder translateMotions(Motion motion, ShapeType type) {
    StringBuilder motionGroup = new StringBuilder();
    Shape startShape = motion.getStartShape();
    Shape endShape = motion.getFinalImages();
    if (motion.isChangePosn()) {
      switch (type) {
        case RECTANGLE:
          motionGroup.append(String.format(" <animate attributeType=\"xml\" begin=\"%.1fms\"" +
                          " dur=\"%.1fms\" attributeName=\"x\" from=\""
                          + startShape.getPosition().getX() + "\" to=\"" +
                          endShape.getPosition().getX() + "\" fill=\"freeze" +
                          "\" />\n",
                  (double) (motion.getStartTick()) / tick * 1000, (double) (motion.getEndTick())
                          / tick * 1000));
          motionGroup.append(String.format(" <animate attributeType=\"xml\" begin=\"%.1fms\"" +
                          " dur=\"%.1fms\" attributeName=\"y\" from=\""
                          + startShape.getPosition().getY() + "\" to=\"" +
                          endShape.getPosition().getY() + "\" fill=\"freeze" +
                          "\" />\n",
                  (double) (motion.getStartTick()) / tick * 1000, (double) (motion.getEndTick())
                          / tick * 1000));
          break;
        case OVAL:
          motionGroup.append(String.format(" <animate attributeType=\"xml\" begin=\"%.2fms\"" +
                          " dur=\"%.2fms\" attributeName=\"cx\" from=\"" +
                          startShape.getPosition().getX() + "\" to=\""
                          + endShape.getPosition().getX() + "\"" +
                          " fill=\"freeze\" />\n",
                  (double) (motion.getStartTick()) / tick * 1000, (double) motion.getEndTick()
                          / tick * 1000));
          motionGroup.append(String.format(" <animate attributeType=\"xml\" begin=\"%.2fms\"" +
                          " dur=\"%.2fms\" attributeName=\"cy\" from=\""
                          + startShape.getPosition().getY() + "\" to=\""
                          + endShape.getPosition().getY() + "\" " +
                          "fill=\"freeze\" />\n",
                  (double) (motion.getStartTick()) / tick * 1000, (double) (motion.getEndTick()
                  ) / tick * 1000));
          break;
        default:
          throw new IllegalArgumentException("Wrong shape type");
      }
    }
    if (motion.isChangeColor()) {
      motionGroup.append(String.format(" <animate attributeType=\"xml\" begin=\"%.2fms\"" +
                      " dur=\"%.2fms\" attributeName=\"fill\" from=\"rgb%s\" to=\"rgb%s\"" +
                      " fill=\"freeze\" />\n",
              (double) (motion.getStartTick()) / tick * 1000, (double) motion.getEndTick()
                      / tick * 1000,
              startShape.getColor().toString(),
              endShape.getColor().toString()));
    }
    if (motion.isChangeSize()) {
      switch (type) {
        case OVAL:
          motionGroup.append(String.format(" <animate attributeType=\"xml\" begin=\"%.2fms\"" +
                          " dur=\"%.2fms\" attributeName=\"cx\" from=\"%.2f\" to=\"%.2f\"" +
                          " fill=\"freeze\" />\n",
                  (double) (motion.getStartTick()) / tick * 1000, (double) motion.getEndTick()
                          / tick * 1000,
                  startShape.getWidth(),
                  endShape.getWidth()));
          motionGroup.append(String.format(" <animate attributeType=\"xml\" begin=\"%.2fms\"" +
                          " dur=\"%.2fms\" attributeName=\"cy\" from=\"%.2f\" to=\"%.2f\" " +
                          "fill=\"freeze\" />\n",
                  (double) (motion.getStartTick()) / tick * 1000, (double) (motion.getEndTick()
                  ) / tick * 1000,
                  startShape.getHeight(),
                  endShape.getHeight()));

          break;
        case RECTANGLE:
          motionGroup.append(String.format(" <animate attributeType=\"xml\" begin=\"%.1fms\"" +
                          " dur=\"%.1fms\" attributeName=\"width\" from=\"%.2f\" to=\"%.2f\"" +
                          " fill=\"freeze" +
                          "\" />\n",
                  (double) (motion.getStartTick()) / tick * 1000, (double) (motion.getEndTick())
                          / tick * 1000,
                  startShape.getWidth(), endShape.getWidth()));
          motionGroup.append(String.format(" <animate attributeType=\"xml\" begin=\"%.1fms\"" +
                          " dur=\"%.1fms\" attributeName=\"height\" from=\"%.2f\" to=\"%.2f\" " +
                          "fill=\"freeze" +
                          "\" />\n",
                  (double) (motion.getStartTick()) / tick * 1000, (double) (motion.getEndTick())
                          / tick * 1000,
                  startShape.getHeight(), endShape.getHeight()));
          break;
        default:
          throw new IllegalArgumentException("Wrong shape type");
      }
    }
    return motionGroup;
  }

  private StringBuilder paintShape(Motion motion, String name, Shape shape) {
    StringBuilder image = new StringBuilder();
    String xPosi = "";
    String yPosi = "";
    String widthPosi = "";
    String heightPosi = "";
    double widthI = 0;
    double heightI = 0;
    ShapeType sp = ShapeType.findShapeType(shape.getShapeName());
    switch (sp) {
      case RECTANGLE:
        xPosi = "x";
        yPosi = "y";
        widthPosi = "width";
        heightPosi = "height";
        widthI = shape.getWidth();
        heightI = shape.getHeight();
        break;
      case OVAL:
        xPosi = "cx";
        yPosi = "cy";
        widthPosi = "rx";
        heightPosi = "ry";
        widthI = shape.getWidth() / 2;
        heightI = shape.getHeight() / 2;
        break;
      default:
        throw new IllegalArgumentException("couldn't find the type of shape");
    }
    image.append(String.format("<%s id=\"%s\" " + xPosi + "=\"%.2f\" " + yPosi + "=\"%.2f\" "
                    + widthPosi + "=\"" + widthI + "\" " +
                    "" + heightPosi + "=\"" + heightI + "\" fill=\"rgb(%d,%d,%d)\"" +
                    " visibility=\"visible\" >\n",
            getSVGShapeType(sp), name, shape.getPosition().getX(),
            shape.getPosition().getY(),
            (int) shape.getColor().getR(), (int) shape.getColor().getG(),
            (int) shape.getColor().getB()));

    return image;
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
