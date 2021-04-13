package cs5004.animator.view;

import cs5004.animator.model.IAnimatorModel;

/**
 * This interface contains all the operations that a view must support.
 */
public interface IAnimatorView {

  /**
   * Creates a view.
   * @param model the animator model.
   * @param speed the speed of the animation.
   * @throws NullPointerException if the model is null.
   * @throws IllegalArgumentException if the speed is not greater than zero.
   */
  void create(IAnimatorModel model, int speed);

}
