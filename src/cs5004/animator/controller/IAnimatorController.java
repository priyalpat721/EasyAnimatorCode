package cs5004.animator.controller;

/**
 * This interface mediates between the IAnimatorModel and the IAnimatorView interfaces. It
 * coordinates between the view of the animation and the animator's data. Manages user input and
 * data from the model.
 */
public interface IAnimatorController {

  /**
   * Getter for the TextView. Returns a String representation of the animation.
   *
   * @return String representation of the animation.
   */
  String getTextView();

  /**
   * Getter for the SVG view. Returns a formatted String representation of the animation.
   * @return String representation of the animation in .svg format.
   */
  String getSVGView();

  /**
   * Getter for the VisualView. Generates a visual representation of the canvas given the model.
   * It initiates the timer for the animation.
   */
  void getVisualView();

  /**
   *  Generates a visual representation of the animation.
   *  It creates the frame for the animation and instantiates all the action buttons' listeners.
   */
  void getPlayBackView();

  /**
   * Initiates mouse handlers for Mouse and for Keyboard.
   * Sets command listeners for Mouse and Keyboard in PlayBackView.
   * Makes the PlayBack frame visible.
   */
  void go();

}
