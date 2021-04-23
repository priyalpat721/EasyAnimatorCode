package cs5004.animator.view;

import java.util.List;

/**
 * This interface contains all the operations that a view must support.
 */
public interface IAnimatorView<T> {

  /**
   * Creates a specific formatted view type using the model.
   * @param modelData the input model to generate a view.
   */
  void create(List modelData);

  /**
   * Generates each of the views.
   * @return the requested view (.txt, .svg, GUI canvas)
   */
  T generate();
}
