package cs5004.animator.utils;

import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.tools.RGB;

/**
 * This class represents an adapter from what the AnimationReader expects to what the model
 * implementation has.
 * The class implements the interface AnimationBuilder<IAnimatorModel>.
 */
public class Builder implements AnimationBuilder<IAnimatorModel> {
  private final IAnimatorModel model;

  /**
   * Constructs a Builder object.
   */
  public Builder() {
    this.model = new AnimatorModelImpl();
  }

  @Override
  public IAnimatorModel build() {
    return this.model;
  }

  @Override
  public AnimationBuilder<IAnimatorModel> setBounds(int x, int y, int width, int height) {
    model.setBounds(x, y, width, height);
    return this;
  }

  @Override
  public AnimationBuilder<IAnimatorModel> declareShape(String name, String type) {
    model.createShape(name, type);
    return this;
  }

  @Override
  public AnimationBuilder<IAnimatorModel> addMotion(String name, int t1, int x1, int y1,
                                                    int w1, int h1, int r1, int g1, int b1,
                                                    int t2, int x2, int y2, int w2, int h2,
                                                    int r2, int g2, int b2) {

    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Invalid name");
    }

    if (x1 != x2 || y1 != y2) {
      model.move(name, x2, y2, t1, t2);
    }

    if (w1 != w2 || h1 != h2) {
      model.scale(name, w2, h2, t1, t2);
    }

    if (r1 != r2 || g1 != g2 || b1 != b2) {
      model.changeColor(name, new RGB((double) r2, (double) g2, (double) b2), t1, t2);
    }

    // If the values in the start column and in the end column are equal (no motion)
    if (x1 == x2 && y1 == y2 && w1 == w2 && h1 == h2 && r1 == r2 && g1 == g2 && b1 == b2) {
      model.setAttributes(name, x1, y1, w1, h1, r1, g1, b1, t1, t2);
    }

    return this;
  }

}
