package cs5004.animator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AnimatorModelImpl implements IAnimatorModel {
  private HashMap<String, IShape> logOfShapes;
  private HashMap<String, List<IActions>> logOfActions;
  private List<IActions> shapeActions;

  public AnimatorModelImpl() {
    this.logOfShapes = new HashMap<>();
    this.logOfActions = new HashMap<>();
    this.shapeActions = new LinkedList<>();
  }

  @Override
  public void createShapes(String name, Shape shape, RGB color,
                           double width, double height, double x, double y, int startTime, int endTime) {
    if (name == null || shape == null || color == null) {
      throw new IllegalArgumentException("The object's name, type or color cannot be empty");
    }

    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("The object's width or height cannot be negative");
    }

    if (shape == Shape.CIRCLE) {
      logOfShapes.put(name, new Circle(name, color, width, height, x, y, startTime, endTime));
    } else if (shape == Shape.SQUARE) {
      logOfShapes.put(name, new Circle(name, color, width, height, x, y, startTime, endTime));
    } else if (shape == Shape.RECTANGLE) {
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
  public void move(String name, double newX, double newY, int startTime, int endTime) {
    IActions newMove = new Move(name, newX, newY, startTime, endTime);
    addActionsToShape(name, newMove);
  }


  @Override
  public void changeColor(String name, RGB newColor, int startTime, int endTime) {
    IActions changedColor = new ChangeColor(name, newColor, startTime, endTime);
    addActionsToShape(name, changedColor);
  }

  @Override
  public void scale(String name, double newWidth, double newHeight, int startTime, int endTime) {

  }

  // adds any action
  public void addActions(String name, IActions actions) {
    addActionsToShape(name, actions);
  }

  // create, move, change Color, scale
  private void addActionsToShape(String name, IActions action) {
    if (action == null || name.equals("")) {
      throw new IllegalArgumentException("Not a valid action");

    }
    shapeActions.add(action);
    logOfActions.put(name, shapeActions);
  }

  // one frame, tick 3 is the third frame
  // tick != seconds
  @Override
  public List<IShape> getShapesAtTicks(int tick) {
    // if time lapsed 10% (at time 1)
    // action proportional to time
    List<IShape> frameOfShapes = new LinkedList<>();
    for (Map.Entry<String, IShape> objects : logOfShapes.entrySet()) {
      IShape accumulatorShape = objects.getValue().copy();

      for(IActions actions : logOfActions.get(objects.getKey())) {
        accumulatorShape = actions.getShapeAtTick(tick, accumulatorShape);
      }

      frameOfShapes.add(accumulatorShape);
    }

    return frameOfShapes;
  }
}
