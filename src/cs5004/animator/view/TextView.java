package cs5004.animator.view;

import java.util.Objects;

import cs5004.animator.model.IAnimatorModel;

/**
 * This class represents a Text view.
 * The Text view has a result containing all events in an animation.
 * This class generates animation events in text format.
 * The class implements the interface IAnimatorView.
 */
public class TextView implements IAnimatorView {
  String result;

  /**
   * Constructs a Text view object.
   */
  public TextView() {
    this.result = "";
  }

  @Override
  public void create(IAnimatorModel model, int speed) {
    Objects.requireNonNull(model, "Must have non-null model");
    if (speed <= 0) {
      throw new IllegalArgumentException("Speed must be greater than zero");
    }

    result = model.toString(speed);
  }

  /**
   * Generates a String representation of the SVG view.
   * @return a String representation of the SVG view.
   */
  public String generate() {
    return this.result;
  }

}
