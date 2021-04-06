package cs5004.animator.utils;

import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.shape.Circle;
import cs5004.animator.shape.Oval;
import cs5004.animator.shape.Rectangle;
import cs5004.animator.shape.Rhombus;
import cs5004.animator.shape.Shape;
import cs5004.animator.shape.Square;
import cs5004.animator.shape.Triangle;

public class Builder implements AnimationBuilder<IAnimatorModel> {
  private IAnimatorModel model;

  public Builder() {
    this.model = new AnimatorModelImpl();
  }

  @Override
  public IAnimatorModel build() {
    return new AnimatorModelImpl();
  }

  @Override
  public AnimationBuilder<IAnimatorModel> setBounds(int x, int y, int width, int height) {
    return null;
  }

  @Override
  public AnimationBuilder<IAnimatorModel> declareShape(String name, String type) {
    if (type.equalsIgnoreCase(Shape.CIRCLE.toString())) {
      model.createShape(name, Shape.CIRCLE);
    } else if (type.equalsIgnoreCase(Shape.SQUARE.toString())) {
      model.createShape(name, Shape.SQUARE);
    } else if (type.equalsIgnoreCase(Shape.RECTANGLE.toString())) {
      model.createShape(name, Shape.RECTANGLE);
    } else if (type.equalsIgnoreCase(Shape.TRIANGLE.toString())) {
      model.createShape(name, Shape.TRIANGLE);
    } else if (type.equalsIgnoreCase(Shape.RHOMBUS.toString())) {
      model.createShape(name, Shape.RHOMBUS);
    } else if (type.equalsIgnoreCase(Shape.OVAL.toString())) {
      model.createShape(name, Shape.OVAL);
    }

    return this;
  }

  @Override
  public AnimationBuilder<IAnimatorModel> addMotion(String name, int t1, int x1, int y1,
                                                    int w1, int h1, int r1, int g1, int b1,
                                                    int t2, int x2, int y2, int w2, int h2,
                                                    int r2, int g2, int b2) {
    return null;
  }
}
