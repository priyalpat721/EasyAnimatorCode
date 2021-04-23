package cs5004.animator.view;

import java.util.List;

import cs5004.animator.shape.IShape;

/**
 * This class represents a Visual view. This class renders animations visually. The class implements
 * the interface IAnimatorView.
 */

public class VisualView implements IAnimatorView<Canvas> {
  private Canvas canvas;

  @Override
  public void create(List modelData) {
    this.canvas = new Canvas((double) modelData.get(0), (double) modelData.get(1),
            (double) modelData.get(2),(double) modelData.get(3), (List<IShape>) modelData.get(4));
  }

  @Override
  public Canvas generate() {
    return this.canvas;
  }
}
