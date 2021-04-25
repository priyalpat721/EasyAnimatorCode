package cs5004.animator.controller;

/**
 * This interface represents all the operations that an animator controller must support.
 */
public interface IAnimatorController {

  /**
   * Getter for the TextView. Returns a String representation of the animation.
   *
   * @return String representation of the animation.
   */
  String getTextView();

  /**
   * Getter for the SVG view.
   *
   * @return SVG representation of the animation.
   */
  String getSVGView();

  /**
   * Getter for the VisualView.
   */
  void getVisualView();

  /**
   *  Generates a visual representation of the animation.
   */
  void getPlayBackView();

  /**
   * Initiates the controller.
   */
  void createView();

}
