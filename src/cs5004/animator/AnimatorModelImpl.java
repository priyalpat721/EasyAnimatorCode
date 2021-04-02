package cs5004.animator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cs5004.shape.Circle;
import cs5004.shape.IShape;
import cs5004.shape.Oval;
import cs5004.shape.RGB;
import cs5004.shape.Rectangle;
import cs5004.shape.Rhombus;
import cs5004.shape.Shape;
import cs5004.shape.Square;
import cs5004.shape.Triangle;

public class AnimatorModelImpl implements IAnimatorModel {
  private HashMap<String, IShape> logOfShapes;
  private HashMap<String, List<IAction>> logOfActions;
  private List<IAction> shapeActions;
  // chronological order of actions for toString method
  private List<IAction> chronologicalOrderOfActions;

  public AnimatorModelImpl() {
    this.logOfShapes = new HashMap<>();
    this.logOfActions = new HashMap<>();
    this.shapeActions = new LinkedList<>();
    this.chronologicalOrderOfActions = new LinkedList<>();
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
    IShape currentShape = logOfShapes.get(name);
    if (currentShape == null) {
      throw new IllegalStateException("Shape with this name does not exist");
    }
    IAction newMove = new Move(name, currentShape, newX, newY, startTime, endTime);
    addActionToShape(name, newMove);
    chronologicalOrderOfActions.add(newMove);
  }

  @Override
  public void changeColor(String name, RGB newColor, int startTime, int endTime) {
    IShape currentShape = logOfShapes.get(name);
    if (currentShape == null) {
      throw new IllegalStateException("Shape with this name does not exist");
    }
    IAction newChangeColor = new ChangeColor(name, currentShape, newColor, startTime, endTime);
    addActionToShape(name, newChangeColor);
    chronologicalOrderOfActions.add(newChangeColor);
  }

  @Override
  public void scale(String name, double newWidth, double newHeight, int startTime, int endTime) {
    IShape currentShape = logOfShapes.get(name);
    if (currentShape == null) {
      throw new IllegalStateException("Shape with this name does not exist");
    }
    IAction newScale = new Scale(name, currentShape, newWidth, newHeight, startTime, endTime);
    addActionToShape(name, newScale);
    chronologicalOrderOfActions.add(newScale);
  }

  // adds any action
  public void addActions(String name, IAction actions) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    if (name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be empty");
    }
    if (actions == null) {
      throw new IllegalArgumentException("Actions cannot be null");
    }
    addActionToShape(name, actions);
    chronologicalOrderOfActions.add(actions);
  }

  // create, move, change Color, scale
  private void addActionToShape(String name, IAction action) {
    shapeActions.add(action);
    logOfActions.put(name, shapeActions);
  }

  @Override
  public List<IShape> getShapesAtTicks(int tick) {
    List<IShape> frameOfShapes = new LinkedList<>();

    for (Map.Entry<String, IShape> objects : logOfShapes.entrySet()) {
      IShape accumulatorShape = objects.getValue().copy();

      for (IAction actions : logOfActions.get(objects.getKey())) {
        accumulatorShape = actions.getShapeAtTick(tick, accumulatorShape);
      }

      frameOfShapes.add(accumulatorShape);
    }

    return frameOfShapes;
  }

  @Override
  public String toString() {

    //print log of shapes at create

    StringBuilder accString = new StringBuilder();
    accString.append("Shapes:\n");
    for (Map.Entry shape: logOfShapes.entrySet()) {
      accString.append(shape.getValue().toString());
      accString.append("\n");
    }
    for (IAction action : chronologicalOrderOfActions) {
      accString.append(action.toString());
      accString.append("\n");
    }
    return accString.toString();
  }
}
