package cs3500.animator.view;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStreamWriter;

import cs3500.animation.model.Animation;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * A view that will generate a textual discription
 */
public class TextualView implements View {

  private final Animation model;
  private final OutputStreamWriter out;


  /**
   * Construct a TextualView with given Model
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
    try{
    if(out == null){
    System.out.print(output);
  }else{out.append(String.format("canvas %d %d %d %d\n", canvas.y, canvas.x, canvas.width,
            canvas.height));
      out.append(model.animateDescription());
    }}catch (IOException e){
      System.out.print("an error occured when appending");
      }
    }

  public void setTickPerSeocnd(int tickPerSeocnd) {

  }
}
