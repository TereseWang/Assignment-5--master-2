package cs3500.animator.view;

import org.w3c.dom.Text;

import cs3500.animation.model.Animation;

public class TextualView implements View{
  private  final Animation model;


  public TextualView(Animation model){
    this.model = model;
  }
  @Override
  public void refresh() {
    throw  new UnsupportedOperationException("Textual view can't be refresh.");

  }

  @Override
  public void display() {
System.out.print();
  }
}
