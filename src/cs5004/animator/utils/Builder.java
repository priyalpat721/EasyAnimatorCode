package cs5004.animator.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import cs5004.animator.action.IAction;
import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.shape.Circle;
import cs5004.animator.shape.IShape;
import cs5004.animator.shape.Oval;
import cs5004.animator.shape.Rectangle;
import cs5004.animator.shape.Rhombus;
import cs5004.animator.shape.Shape;
import cs5004.animator.shape.Square;
import cs5004.animator.shape.Triangle;


public class Builder implements AnimationBuilder<IAnimatorModel> {
  private final HashMap<String, IShape> logOfShapes;
  private final HashMap<String, List<IAction>> logOfActions;
  private final List<String> chronologicalOrderOfActions;

  public Builder() {
    this.logOfShapes = new HashMap<>();
    this.logOfActions = new HashMap<>();
    this.chronologicalOrderOfActions = new LinkedList<>();
  }

  @Override
  public IAnimatorModel build() {
    return new AnimatorModelImpl(this);
  }

  @Override
  public AnimationBuilder<IAnimatorModel> setBounds(int x, int y, int width, int height) {
    return this;
  }

  @Override
  public AnimationBuilder<IAnimatorModel> declareShape(String name, String type) {
    Shape finalType = null;

    for (Shape shape : Shape.values()) {
      if (shape.toString().equalsIgnoreCase(type)) {
        finalType = shape;
      } else {
        throw new IllegalArgumentException("Shape does not exist");
      }
    }

    switch (finalType) {
      case CIRCLE -> logOfShapes.put(name, new Circle(name));
      case SQUARE -> logOfShapes.put(name, new Square(name));
      case RECTANGLE -> logOfShapes.put(name, new Rectangle(name));
      case TRIANGLE -> logOfShapes.put(name, new Triangle(name));
      case RHOMBUS -> logOfShapes.put(name, new Rhombus(name));
      case OVAL -> logOfShapes.put(name, new Oval(name));
    }

    return this;
  }

  @Override
  public AnimationBuilder<IAnimatorModel> addMotion(String name, int t1, int x1, int y1,
                                                    int w1, int h1, int r1, int g1, int b1,
                                                    int t2, int x2, int y2, int w2, int h2,
                                                    int r2, int g2, int b2) {
    return this;
  }

  public HashMap<String, IShape> getLogOfShapes() {
    return logOfShapes;
  }

  public HashMap<String, List<IAction>> getLogOfActions() {
    return logOfActions;
  }

  public List<String> getChronologicalOrderOfActions() {
    return chronologicalOrderOfActions;
  }
}
