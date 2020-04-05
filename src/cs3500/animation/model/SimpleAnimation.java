package cs3500.animation.model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cs3500.animatior.shape.Color;
import cs3500.animatior.shape.Posn;
import cs3500.animatior.shape.Shape;
import cs3500.animatior.shape.ShapeCreator;
import cs3500.animatior.shape.ShapeType;
import cs3500.animator.util.AnimationBuilder;


/**
 * a simple animation class, use list of motion as representation of sequence of motions.
 */
public class SimpleAnimation extends AbstractAnimation{
  /**
   * construct an empty animation.
   */
  public SimpleAnimation() {
    super();
  }

  public SimpleAnimation(LinkedHashMap<String, List<Motion>> animation) {
    super(animation);
  }
}