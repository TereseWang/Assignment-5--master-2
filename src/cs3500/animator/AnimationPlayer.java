package cs3500.animator;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import cs3500.animation.model.Animation;
import cs3500.animation.model.SimpleAnimation;
import cs3500.animator.controller.SimpleController;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.View;

/**
 * Entry point for this program.
 */
public final class AnimationPlayer {
  public static void main(String[] args) {

    Animation model;
    FileReader in;
    FileWriter out;
    View view;


    AnimationReader reader = new AnimationReader();
    AnimationBuilder builder = new SimpleAnimation.Builder();
    Animation model = reader.parseFile(in,builder);

    SimpleController controller = new SimpleController(view);

    controller.execute();

  }
}