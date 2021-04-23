package cs5004.animator.view;

import java.util.List;

/**
 * This class represents a Text view.
 * The Text view has a result containing all events in an animation.
 * This class generates animation events in text format.
 * The class implements the interface IAnimatorView.
 */
public class TextView implements IAnimatorView<String> {
  String result;

  /**
   * Constructs a Text view object.
   */
  public TextView() {
    this.result = "";
  }

  @Override
  public void create(List modelData) {
    result = (String) modelData.get(0);
  }

  /**
   * Generates a String representation of the SVG view.
   * @return a String representation of the SVG view.
   */
  @Override
  public String generate() {
    String result = this.result;
    return result;
  }

}
