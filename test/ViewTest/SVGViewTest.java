package ViewTest;

import static org.junit.Assert.*;

import cs3500.animation.model.AbstractAnimation;
import cs3500.animation.model.Animation;
import cs3500.animation.model.Motion;
import cs3500.animation.model.SimpleAnimation;
import cs3500.animator.shape.Color;
import cs3500.animator.shape.Posn;
import cs3500.animator.shape.Rectangle;
import cs3500.animator.shape.Shape;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.View;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SVGViewTest {

  Animation model;
  View view;
  OutputStreamWriter outputStreamWriter;
  int tickPerSec;
  Motion m;
  Motion m2;

  @Before
  public void setUp() {
    model = new SimpleAnimation();
    view = new SVGView(model, outputStreamWriter, tickPerSec);
    Shape s = new Rectangle(new Posn(10, 10), new Color(100, 100, 100), 3, 3);
    Shape s1 = new Rectangle(new Posn(100, 100), new Color(100, 100, 100), 3, 3);
    m = new Motion(4, 5, s, s1);
    Shape s2 = new Rectangle(new Posn(100, 100), new Color(100, 100, 100), 3, 3);
    Shape s3 = new Rectangle(new Posn(100, 200), new Color(0, 0, 255), 5, 5);
    m2 = new Motion(5, 10, s2, s3);
    Shape s4 = new Rectangle(new Posn(100, 200), new Color(0, 0, 255), 5, 5);
    Shape s5 = new Rectangle(new Posn(100, 200), new Color(100, 100, 100), 4, 10);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRefresh() {
    view.refresh();
  }

  @Test
  public void testDisplay() {
    try {
      java.awt.Rectangle canvas = new java.awt.Rectangle();
      canvas.setBounds(50, 145, 410, 220);
      model.declareShape("Rectangle", "Rectangle");
      model.addMotion("Rectangle", m);
      model.addMotion("Rectangle", m2);

      while (outputStreamWriter != null) {
        outputStreamWriter
            .append(String.format("canvas %d %d %d %d\n", canvas.y, canvas.x, canvas.width,
                canvas.height));
        outputStreamWriter.append(view.toString());
        view.display();

        assertEquals(
            "<svg width=\"500\" height=\"500\" version=\"1.1\" xmlns=\"http://www.w3.org/200"
                + "0/svg\">\n" +
                "<rect id=\"Rectangle\" x=\"10.0\"  y=\"10.0\" width=\"10\" height=\"10\" fill=\"rgb(255,255,"
                + "255)\" visibility=\"visible\" >\n" +
                "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"100.0ms\" attribut"
                + "eName=\"fill\" from=\"rgb(255,255,255)\" to=\"rgb(4,4,4)\" fill=\"freeze\" />\n"
                +
                "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"100.0ms\" attribut"
                + "eName=\"x\" from=\"10.0\" to=\"4.0\" fill=\"freeze\" />\n" +
                "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"100.0ms\" attribute"
                + "Name=\"y\" from=\"10.0\" to=\"4.0\" fill=\"freeze\" />\n" +
                "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"100.0ms\" attribute"
                + "Name=\"width\" from=\"10\" to=\"4\" fill=\"freeze\" />\n" +
                "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"100.0ms\" attribute"
                + "Name=\"height\" from=\"10\" to=\"4\" fill=\"freeze\" />\n" +
                "<animate attributesType=\"xml\" begin=\"133.33333333333334ms\" dur=\"433.33333333333337m"
                + "s\" attributeName=\"fill\" from=\"rgb(4,4,4)\" to=\"rgb(199,67,255)\" fill=\"fr"
                + "eeze\" />\n"
                + "<animate attributesType=\"xml\" begin=\"133.33333333333334ms\" dur=\"433.33333333333337m"
                + "s\" attributeName=\"x\" from=\"4.0\" to=\"76.0\" fill=\"freeze\" />\n" +
                "<animate attributesType=\"xml\" begin=\"133.33333333333334ms\" dur=\"433.33333333333337m"
                + "s\" attributeName=\"y\" from=\"4.0\" to=\"30.0\" fill=\"freeze\" />\n" +
                "<animate attributesType=\"xml\" begin=\"133.33333333333334ms\" dur=\"433.33333333333337ms"
                + "\" attributeName=\"width\" from=\"4\" to=\"30\" fill=\"freeze\" />\n" +
                "<animate attributesType=\"xml\" begin=\"133.33333333333334ms\" dur=\"433.33333333333337ms"
                + "\" attributeName=\"height\" from=\"4\" to=\"23\" fill=\"freeze\" />\n" +
                "</rect>\n" +
                "<rect id=\"Rectangle\" x=\"10.0\"  y=\"10.0\" width=\"10\" height=\"10\" fill=\"rgb(255,255,"
                + "255)\" visibility=\"visible\" >\n" +
                "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"100.0ms\" attribut"
                + "eName=\"fill\" from=\"rgb(255,255,255)\" to=\"rgb(4,4,4)\" fill=\"freeze\" />\n"
                + "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"100.0ms\" attribut"
                + "eName=\"x\" from=\"10.0\" to=\"4.0\" fill=\"freeze\" />\n" +
                "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"100.0ms\" attribute"
                + "Name=\"y\" from=\"10.0\" to=\"4.0\" fill=\"freeze\" />\n" +
                "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"100.0ms\" attribute"
                + "Name=\"width\" from=\"10\" to=\"4\" fill=\"freeze\" />\n" +
                "<animate attributesType=\"xml\" begin=\"33.333333333333336ms\" dur=\"100.0ms\" attribute"
                + "Name=\"height\" from=\"10\" to=\"4\" fill=\"freeze\" />\n" +
                "<animate attributesType=\"xml\" begin=\"133.33333333333334ms\" dur=\"433.33333333333337m"
                + "s\" attributeName=\"fill\" from=\"rgb(4,4,4)\" to=\"rgb(199,67,255)\" fill=\"fr"
                + "eeze\" />\n" +
                "<animate attributesType=\"xml\" begin=\"133.33333333333334ms\" dur=\"433.33333333333337m"
                + "s\" attributeName=\"x\" from=\"4.0\" to=\"76.0\" fill=\"freeze\" />\n" +
                "<animate attributesType=\"xml\" begin=\"133.33333333333334ms\" dur=\"433.33333333333337m"
                + "s\" attributeName=\"y\" from=\"4.0\" to=\"30.0\" fill=\"freeze\" />\n" +
                "<animate attributesType=\"xml\" begin=\"133.33333333333334ms\" dur=\"433.33333333333337ms"
                + "\" attributeName=\"width\" from=\"4\" to=\"30\" fill=\"freeze\" />\n" +
                "<animate attributesType=\"xml\" begin=\"133.33333333333334ms\" dur=\"433.33333333333337ms"
                + "\" attributeName=\"height\" from=\"4\" to=\"23\" fill=\"freeze\" />\n" +
                "</rect>\n" +
                "\n" +
                "</svg>\n", outputStreamWriter);
      }
    } catch (IOException e) {
      Assert.fail("Exception " + e);
    }
  }
}
