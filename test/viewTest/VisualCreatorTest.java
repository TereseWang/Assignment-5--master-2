package viewTest;

import static org.junit.Assert.assertTrue;

import cs3500.animation.model.KeyFrameAnimation;
import cs3500.animation.model.SimpleAnimation;
import cs3500.animator.view.EditorView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextualView;
import cs3500.animator.view.View;
import cs3500.animator.view.ViewCreator;
import cs3500.animator.view.ViewCreator.ViewType;
import cs3500.animator.view.VisualView;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to see if the visual creator acturately create the type of view we desired.
 */
public class VisualCreatorTest {

  private ViewCreator creator;
  private OutputStreamWriter out;
  private SimpleAnimation model;
  private KeyFrameAnimation framemodel;
  private int tick;

  @Before
  public void init() throws IOException {
    creator = new ViewCreator();
    try {
      java.awt.Rectangle canvas = new java.awt.Rectangle();
      canvas.setBounds(50, 145, 410, 220);

      while (out != null) {
        out.append(String.format("canvas %d %d %d %d\n", canvas.y, canvas.x, canvas.width,
            canvas.height));
        out.append(model.toString());
      }
    } catch (IOException e) {
      Assert.fail("Exception " + e);
    }
    model = new SimpleAnimation();
    framemodel = new KeyFrameAnimation();
  }

  @Test
  public void testCreateEdit() throws IOException {
    init();
    View view = creator.create(ViewType.EDIT, framemodel, out, tick);
    assertTrue(view instanceof EditorView);
  }

  @Test
  public void testCreateVisual() throws IOException {
    init();
    View view = creator.create(ViewType.VISUAL, model, out, tick);
    assertTrue(view instanceof VisualView);
  }

  @Test
  public void testCreateSVG() throws IOException {
    init();
    View view = creator.create(ViewType.SVG, model, out, tick);
    assertTrue(view instanceof SVGView);
  }

  @Test
  public void testCreateTextual() throws IOException {
    init();
    View view = creator.create(ViewType.TEXTUAL, model, out, tick);
    assertTrue(view instanceof TextualView);
  }
}
