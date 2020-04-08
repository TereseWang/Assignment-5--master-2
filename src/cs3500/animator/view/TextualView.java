package cs3500.animator.view;

import java.awt.Rectangle;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cs3500.animation.model.Animation;
import cs3500.animation.model.Motion;


/**
 * A view that take in an animation model and interpret it into a string description.
 */
public class TextualView implements View {

  private final Animation model;
  private final OutputStreamWriter out;


  /**
   * Construct a TextualView with given Model.
   *
   * @param model a given not null model
   * @param out   where the output should go
   * @throws IllegalArgumentException if the model given is null
   */
  public TextualView(Animation model, OutputStreamWriter out) {
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null");
    }
    this.model = model;
    this.out = out;
  }

  @Override
  public void refresh() {
    throw new UnsupportedOperationException("Textual view can't be refresh.");

  }

  @Override
  public void display() {
    StringBuilder output = new StringBuilder("");
    Rectangle canvas = model.getBox();
    try {
      if (out == null) {
        System.out.print(output);
      } else {
        out.append(String.format("canvas %d %d %d %d\n", canvas.y, canvas.x, canvas.width,
            canvas.height));
        out.append(animateDescription());
      }
    } catch (IOException e) {
      System.out.print("an error occured when appending");
    }
  }

  private String animateDescription() {
    LinkedHashMap<String, List<Motion>> animation = model.getAnimate();
    if (animation.isEmpty()) {
      return "";
    } else {
      String result = new String();
      // make a change here: change from "animation.entrySet()" to getAnimate.entrySet()
      // in line 262.
      for (Map.Entry<String, List<Motion>> entry : animation.entrySet()) {
        String key = entry.getKey();
        List<Motion> l = entry.getValue();
        if (l.isEmpty()) {
          return "";
        } else {
          String s = l.get(0).getStartShape().getShapeName();
          result += "shape " + key + " " + s + "\n";
          for (int i = 0; i < l.size(); i++) {
            result += "motion " + key + " " + l.get(i).toString() + "\n";
          }
          result += "\n";
        }
      }
      int i = result.lastIndexOf("\n");
      if (result.length() != 0) {
        result = result.substring(0, i - 1);
      }
      return result;
    }
  }

}
