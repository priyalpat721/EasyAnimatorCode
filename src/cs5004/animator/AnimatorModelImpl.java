package cs5004.animator;

import java.util.HashMap;
import java.util.List;

public class AnimatorModelImpl implements IAnimatorModel{
  private HashMap<String,IShape> logOfShapes;
  private HashMap<String, List<IActions>> logOfActions;

  @Override
  public void createShapes(String name, Shape shape, RGB color,
                           double width, double height, double x, double y, int startTime, int endTime) {
    if (name == null || shape == null || color == null) {
      throw new IllegalArgumentException("The object's name, type or color cannot be empty");
    }

    if (width < 0 || height < 0) {
      throw new IllegalArgumentException ("The object's width or height cannot be negative");
    }

    if (shape == Shape.CIRCLE) {
      logOfShapes.put(name, new Circle(name, color, width, height, x, y, startTime, endTime));
    }

    else if (shape == Shape.SQUARE) {
      logOfShapes.put(name, new Circle(name, color, width, height, x, y, startTime, endTime));
    }
    if (shape == Shape.RECTANGLE) {
      logOfShapes.put(name, new Rectangle(name, color, width, height, x, y, startTime, endTime));
    } else if (shape == Shape.TRIANGLE) {
      logOfShapes.put(name, new Triangle(name, color, width, height, x, y, startTime, endTime));
    } else if (shape == Shape.RHOMBUS) {
      logOfShapes.put(name, new Rhombus(name, color, width, height, x, y, startTime, endTime));
    } else if (shape == Shape.OVAL) {
      logOfShapes.put(name, new Oval(name, color, width, height, x, y, startTime, endTime));
    }
  }

  @Override
  public void addActionsToShape(IActions action) {

  }

  @Override
  public List<IShape> getShapesAtTicks(int tick) {
    return null;
  }
}
