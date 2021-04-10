package cs5004.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cs5004.animator.action.Action;
import cs5004.animator.action.ChangeColor;
import cs5004.animator.action.IAction;
import cs5004.animator.action.Move;
import cs5004.animator.action.Scale;
import cs5004.animator.action.Stay;
import cs5004.animator.shape.Circle;
import cs5004.animator.shape.IShape;
import cs5004.animator.shape.Ellipse;
import cs5004.animator.shape.Rectangle;
import cs5004.animator.shape.Rhombus;
import cs5004.animator.shape.Shape;
import cs5004.animator.shape.Square;
import cs5004.animator.shape.Triangle;
import cs5004.animator.tools.RGB;

/**
 * This class represents the Model component of an animator. The class implements the interface
 * IAnimatorModel. The class has a log of shapes, a log of actions, and a chronological order of
 * actions.
 */
public class AnimatorModelImpl implements IAnimatorModel {
  private final List<IShape> logOfShapes;
  //private final HashMap<String, IShape> logOfShapes;
  private final HashMap<String, List<IAction>> logOfActions;
  private final List<IAction> chronologicalOrderOfActions;

  // This would be our canvas
  // Array of 4 elements: x, y, width, height
  private final int[] box;

  /**
   * Constructs an Animator model object.
   */
  public AnimatorModelImpl() {
    //this.logOfShapes = new HashMap<>();
    this.logOfShapes = new ArrayList<>();
    this.logOfActions = new HashMap<>();
    this.chronologicalOrderOfActions = new LinkedList<>();
    this.box = new int[4];
  }

  @Override
  public void createShape(String name, Shape type, RGB color, double width, double height,
                          double x, double y, int startTime, int endTime) {

    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Invalid name");
    } else if (type == null) {
      throw new IllegalArgumentException("Invalid shape");
    } else if (color == null) {
      throw new IllegalArgumentException("Invalid color");
    }

//    for (Map.Entry<String, IShape> entry : logOfShapes.entrySet()) {
//      if (entry.getValue().getName().equals(name)) {
//        throw new IllegalArgumentException("Name already exists");
//      }
//    }

    for (IShape shape : logOfShapes) {
      if (shape.getName().equals(name)) {
        throw new IllegalArgumentException("Name already exists");
      }
    }

    if (type == Shape.CIRCLE) {
      //logOfShapes.put(name, new Circle(name, color, width, width, x, y, startTime, endTime));
      logOfShapes.add(new Circle(name, color, width, width, x, y, startTime, endTime));
    } else if (type == Shape.SQUARE) {
      //logOfShapes.put(name, new Square(name, color, width, width, x, y, startTime, endTime));
      logOfShapes.add(new Square(name, color, width, width, x, y, startTime, endTime));
    } else if (type == Shape.RECTANGLE) {
      //logOfShapes.put(name, new Rectangle(name, color, width, height, x, y, startTime, endTime));
      logOfShapes.add(new Rectangle(name, color, width, height, x, y, startTime, endTime));
    } else if (type == Shape.TRIANGLE) {
      //logOfShapes.put(name, new Triangle(name, color, width, height, x, y, startTime, endTime));
      logOfShapes.add(new Triangle(name, color, width, height, x, y, startTime, endTime));
    } else if (type == Shape.RHOMBUS) {
      //logOfShapes.put(name, new Rhombus(name, color, width, height, x, y, startTime, endTime));
      logOfShapes.add(new Rhombus(name, color, width, height, x, y, startTime, endTime));
    } else if (type == Shape.ELLIPSE) {
      //logOfShapes.put(name, new Ellipse(name, color, width, height, x, y, startTime, endTime));
      logOfShapes.add(new Ellipse(name, color, width, height, x, y, startTime, endTime));
    }
  }

  @Override
  public void createShape(String name, String type) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Invalid name");
    } else if (type == null) {
      throw new IllegalArgumentException("Invalid shape");
    }

//    for (Map.Entry<String, IShape> entry : logOfShapes.entrySet()) {
//      if (entry.getKey().equals(name)) {
//        throw new IllegalArgumentException("Name already exists");
//      }
//    }

    for (IShape shape : logOfShapes) {
      if (shape.getName().equals(name)) {
        throw new IllegalArgumentException("Name already exists");
      }
    }

    Shape finalType = null;

    for (Shape shape : Shape.values()) {
      if (shape.toString().equalsIgnoreCase(type)) {
        finalType = shape;
      }
    }

    if (finalType == null) {
      throw new IllegalArgumentException("Shape does not exist");
    }

    switch (finalType) {
//      case CIRCLE -> logOfShapes.put(name, new Circle(name));
//      case SQUARE -> logOfShapes.put(name, new Square(name));
//      case RECTANGLE -> logOfShapes.put(name, new Rectangle(name));
//      case TRIANGLE -> logOfShapes.put(name, new Triangle(name));
//      case RHOMBUS -> logOfShapes.put(name, new Rhombus(name));
//      case ELLIPSE -> logOfShapes.put(name, new Ellipse(name));
      case CIRCLE -> logOfShapes.add(new Circle(name));
      case SQUARE -> logOfShapes.add(new Square(name));
      case RECTANGLE -> logOfShapes.add(new Rectangle(name));
      case TRIANGLE -> logOfShapes.add(new Triangle(name));
      case RHOMBUS -> logOfShapes.add(new Rhombus(name));
      case ELLIPSE -> logOfShapes.add(new Ellipse(name));

    }
  }

  @Override
  public void move(String name, double newX, double newY, int startTime, int endTime) {
    IShape currentShape = getCurrentShape(name);

    //checkRange(name, startTime, endTime);

    if (!checkOverlap(name, Action.MOVE, startTime, endTime)) {
      throw new IllegalArgumentException("Move overlap");
    }

    IAction newMove = new Move(name, currentShape, newX, newY, startTime, endTime);
    addActionToShape(name, newMove);
    chronologicalOrderOfActions.add(newMove);
  }

  @Override
  public void changeColor(String name, RGB newColor, int startTime, int endTime) {
    IShape currentShape = getCurrentShape(name);

    if (newColor == null) {
      throw new IllegalArgumentException("Invalid color");
    }

    //checkRange(name, startTime, endTime);

    if (!checkOverlap(name, Action.CHANGECOLOR, startTime, endTime)) {
      throw new IllegalArgumentException("Change color overlap");
    }

    IAction newChangeColor = new ChangeColor(name, currentShape, newColor, startTime, endTime);
    addActionToShape(name, newChangeColor);
    chronologicalOrderOfActions.add(newChangeColor);
  }

  @Override
  public void scale(String name, double newWidth, double newHeight, int startTime, int endTime) {
    IShape currentShape = getCurrentShape(name);

    //checkRange(name, startTime, endTime);

    if (!checkOverlap(name, Action.SCALE, startTime, endTime)) {
      throw new IllegalArgumentException("Scale overlap");
    }

    IAction newScale = new Scale(name, currentShape, newWidth, newHeight, startTime, endTime);
    addActionToShape(name, newScale);
    chronologicalOrderOfActions.add(newScale);
  }

  @Override
  public List<IShape> getShapesAtTicks(double tick) {
    if (tick < 0) {
      throw new IllegalArgumentException("Tick must be greater than 0.");
    }

    List<IShape> frameOfShapes = new LinkedList<>();
    //for (Map.Entry<String, IShape> objects : logOfShapes.entrySet()) {
    for (IShape shape : logOfShapes) {
      //IShape accumulatorShape = objects.getValue().copy();
      IShape accumulatorShape = shape.copy();

      //if (logOfActions.size() > 0 && logOfActions.get(objects.getKey()) != null) {
      if (logOfActions.size() > 0 && logOfActions.get(shape.getName()) != null) {
        //for (IAction actions : logOfActions.get(objects.getKey())) {
        for (IAction actions : logOfActions.get(shape.getName())) {
          accumulatorShape = actions.getShapeAtTick(tick, accumulatorShape);
        }
      }
      if (accumulatorShape.isVisible()) {
        frameOfShapes.add(accumulatorShape);
      }
    }
    return frameOfShapes;
  }

  @Override
  public void setBounds(int x, int y, int width, int height) {
    if (width <= 0) {
      throw new IllegalArgumentException("Invalid width");
    } else if (height <= 0) {
      throw new IllegalArgumentException("Invalid height");
    }

    this.box[0] = x;
    this.box[1] = y;
    this.box[2] = width;
    this.box[3] = height;
  }

  @Override
  public void setAttributes(String name, int x1, int y1, int w1, int h1,
                            int r1, int g1, int b1, int t1, int t2) {

    IShape originalShape = null;

//    for (Map.Entry<String, IShape> object : logOfShapes.entrySet()) {
//      if (object.getKey().equals(name)) {
//        originalShape = object.getValue();
//      }
//    }

    for (IShape shape : logOfShapes) {
      if (shape.getName().equals(name)) {
        originalShape = shape;
      }
    }

    // If the original shape has its attributes set to null, it means we are setting its attributes
    if (originalShape.getPosition() == null) {
      originalShape.setPosition(x1, y1);
      originalShape.setColor(new RGB((double) r1, (double) g1, (double) b1));
      originalShape.setShowTime(t1, t2);
      switch (originalShape.getType()) {
        case CIRCLE -> originalShape.setRadius(w1);
        case SQUARE -> originalShape.setLength(w1);
        default -> {
          originalShape.setWidth(w1);
          originalShape.setHeight(h1);
        }
      }
      // CHECK THIS
      //stay(name, t1, t2);
      // If the original shape already has attributes, it means it is a "stand still" action
    } else {
      stay(name, t1, t2);
    }
  }

  @Override
  public String toString() {
    StringBuilder accString = new StringBuilder();
    accString.append("Shapes:\n");

    //for (Map.Entry<String, IShape> entry : logOfShapes.entrySet()) {
    for (IShape shape : logOfShapes) {
      int[] timeOfDisplay = getTimeOfDisplay(shape.getName());
      shape.setShowTime(timeOfDisplay[0], timeOfDisplay[1]);
      accString.append(shape.toString());
      accString.append("\n\n");
    }

    for (int i = 0; i < chronologicalOrderOfActions.size(); i++) {
      if (chronologicalOrderOfActions.get(i).getType() != Action.STAY) {
        if (i != chronologicalOrderOfActions.size() - 1) {
          accString.append("Shape " + chronologicalOrderOfActions.get(i).toString());
          accString.append("\n");
        } else {
          accString.append("Shape " + chronologicalOrderOfActions.get(i).toString());
        }
      }
    }

    return accString.toString();
  }

  @Override
  public List<IShape> getLogOfShapes() {
    return this.logOfShapes;
  }

  @Override
  public List<IAction> getChronological() {
    return this.chronologicalOrderOfActions;
  }

  private void stay(String name, int startTime, int endTime) {
    IShape currentShape = getCurrentShape(name);

    //checkRange(name, startTime, endTime);

    if (!checkOverlap(name, Action.STAY, startTime, endTime)) {
      throw new IllegalArgumentException("Stay overlap");
    }

    IAction newStay = new Stay(name, currentShape, startTime, endTime);
    addActionToShape(name, newStay);
    chronologicalOrderOfActions.add(newStay);
  }

  /**
   * Private method that adds an action to the log.
   *
   * @param name   name of the shape.
   * @param action action to add to the log.
   */
  private void addActionToShape(String name, IAction action) {
    if (logOfActions.get(name) == null) {
      logOfActions.put(name, new LinkedList<>());
    }
    logOfActions.get(name).add(action);
  }

  /**
   * Private method that returns a shape from the log.
   *
   * @param name the name of the shape.
   * @return the shape from the log.
   * @throws IllegalArgumentException if name is null or empty. if the shape does not exist in the
   *                                  log.
   */
  private IShape getCurrentShape(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Invalid name");
    }

    //for (Map.Entry<String, IShape> objects : logOfShapes.entrySet()) {
    for (IShape shape : logOfShapes) {
      if (shape.getName().equals(name)) {
        IShape accumulatorShape = shape.copy();

        if (logOfActions.size() > 0 && logOfActions.get(shape.getName()) != null) {
          for (IAction actions : logOfActions.get(shape.getName())) {
            accumulatorShape = actions.getCurrentShape();
          }
        }
        return accumulatorShape;
      }
    }

    throw new IllegalArgumentException("Shape does not exist");
  }

  /**
   * Private method to determine if an action overlaps with another of the same type.
   *
   * @param name      name of the Shape.
   * @param type      type of the Action.
   * @param startTime the start time of the new action being tested.
   * @param endTime   the end time of the new action being tested.
   * @return true if there is no overlap; false otherwise.
   */
  private boolean checkOverlap(String name, Action type, int startTime, int endTime) {
    for (Map.Entry<String, List<IAction>> entry : logOfActions.entrySet()) {
      if (entry.getKey().equals(name)) {
        List<IAction> actions = entry.getValue();
        for (IAction action : actions) {
          if (action.getType() == type) {
            if (startTime > action.getTime().getStartTime() &&
                startTime < action.getTime().getEndTime() ||
                endTime > action.getTime().getStartTime() &&
                endTime < action.getTime().getEndTime()) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }

  /**
   * Private method to determine if an action is within the range of execution of a shape.
   *
   * @param name      name of the Shape.
   * @param startTime the start time of the new action being tested.
   * @param endTime   the end time of the new action being tested.
   * @throws IllegalArgumentException if the action is out of range.
   */
  private void checkRange(String name, int startTime, int endTime) {
    //for (Map.Entry<String, IShape> entry : logOfShapes.entrySet()) {
    for (IShape shape : logOfShapes) {
      if (shape.getName().equals(name)) {
        if (startTime < shape.getShowTime().getStartTime() ||
            endTime > shape.getShowTime().getEndTime()) {
          throw new IllegalArgumentException("Action is out of range");
        }
      }
    }
  }

  @Override
  public int[] getBox() {
    return this.box;
  }

  @Override
  public int[] getTotalTime() {
    if (logOfShapes.isEmpty()) {
      throw new IllegalArgumentException("Animation has no shapes");
    }

    int start = -1;
    int end = -1;

    //for (Map.Entry<String, IShape> entry : logOfShapes.entrySet()) {
    for (IShape shape : logOfShapes) {
      int[] timeOfDisplay = getTimeOfDisplay(shape.getName());

      if (start == -1 || timeOfDisplay[0] < start) {
        start = timeOfDisplay[0];
      }

      if (end == -1 || timeOfDisplay[1] > end) {
        end = timeOfDisplay[1];
      }
    }

    return new int[]{start, end};
  }

  private int[] getTimeOfDisplay(String name) {
    int[] result = new int[2];

    int start = -1;
    int end = -1;

    //for (Map.Entry<String, IShape> entry : logOfShapes.entrySet()) {
    for (IShape shape : logOfShapes) {
      if (shape.getName().equals(name)) {
        start = shape.getShowTime().getStartTime();
      }
    }

    for (Map.Entry<String, List<IAction>> entry : logOfActions.entrySet()) {
      if (entry.getKey().equals(name)) {
        List<IAction> actions = entry.getValue();
        for (IAction action : actions) {
          if (end == -1 || action.getTime().getEndTime() > end) {
            end = action.getTime().getEndTime();
          }
        }
      }
      result[0] = start;
      result[1] = end;
    }

    return result;
  }

}
