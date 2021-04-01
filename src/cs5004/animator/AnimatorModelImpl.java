package cs5004.animator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AnimatorModelImpl implements IAnimatorModel {
  private HashMap<String, IShape> logOfShapes;
  private HashMap<String, List<IAction>> logOfActions;
  private List<IAction> shapeActions;

  public AnimatorModelImpl() {
    this.logOfShapes = new HashMap<>();
    this.logOfActions = new HashMap<>();
    this.shapeActions = new LinkedList<>();
  }

  @Override
  public void createShape(String name, Shape shape, RGB color, double width, double height,
                          double x, double y, int startTime, int endTime) {
    if (shape == Shape.CIRCLE) {
      logOfShapes.put(name, new Circle(name, color, width, width, x, y, startTime, endTime));
    } else if (shape == Shape.SQUARE) {
      logOfShapes.put(name, new Square(name, color, width, width, x, y, startTime, endTime));
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
    IAction newMove = new Move(name, newX, newY, startTime, endTime);
    addActionToShape(name, newMove);
  }

  @Override
  public void changeColor(String name, RGB newColor, int startTime, int endTime) {
    IAction newChangeColor = new ChangeColor(name, newColor, startTime, endTime);
    addActionToShape(name, newChangeColor);
  }

  @Override
  public void scale(String name, double newWidth, double newHeight, int startTime, int endTime) {

  }

  // adds any action
  public void addActions(String name, IAction actions) {
    addActionToShape(name, actions);
  }

  // create, move, change Color, scale
  private void addActionToShape(String name, IAction action) {
    if (action == null || name.equals("")) {
      throw new IllegalArgumentException("Not a valid action");
    }
    shapeActions.add(action);
    logOfActions.put(name, shapeActions);
  }

  // one frame, tick = 3 is the third frame
  // tick != seconds -> 50 ticks per second
  @Override
  public List<IShape> getShapesAtTick(int tick) {
    // if time lapsed 10% (at time 1)
    // action proportional to time
    List<IShape> frameOfShapes = new LinkedList<>();

    for (Map.Entry<String, IShape> objects : logOfShapes.entrySet()) {
      IShape accumulatorShape = objects.getValue().copy();

      for(IAction actions : logOfActions.get(objects.getKey())) {
        accumulatorShape = actions.getShapeAtTick(tick, accumulatorShape);
      }

      frameOfShapes.add(accumulatorShape);
    }

    return frameOfShapes;
  }
}
