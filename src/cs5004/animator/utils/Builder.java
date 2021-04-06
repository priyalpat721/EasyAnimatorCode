package cs5004.animator.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cs5004.animator.action.Action;
import cs5004.animator.action.ChangeColor;
import cs5004.animator.action.IAction;
import cs5004.animator.action.Move;
import cs5004.animator.action.Scale;
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
import cs5004.animator.tools.RGB;


public class Builder implements AnimationBuilder<IAnimatorModel> {
  private final HashMap<String, IShape> logOfShapes;
  private final HashMap<String, List<IAction>> logOfActions;
  private final List<String> chronologicalOrderOfActions;

  private final int[] box;

  public Builder() {
    this.logOfShapes = new HashMap<>();
    this.logOfActions = new HashMap<>();
    this.chronologicalOrderOfActions = new LinkedList<>();
    this.box = new int[4];
  }

  @Override
  public IAnimatorModel build() {
    return new AnimatorModelImpl(this);
  }

  @Override
  public AnimationBuilder<IAnimatorModel> setBounds(int x, int y, int width, int height) {
    if (width <= 0) {
      throw new IllegalArgumentException("Invalid width");
    } else if (height <= 0) {
      throw new IllegalArgumentException("Invalid height");
    }

    this.box[1] = x;
    this.box[2] = y;
    this.box[3] = width;
    this.box[4] = height;

    return this;
  }

  @Override
  public AnimationBuilder<IAnimatorModel> declareShape(String name, String type) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Invalid name");
    } else if (type == null) {
      throw new IllegalArgumentException("Invalid shape");
    }

    for (Map.Entry<String, IShape> entry : logOfShapes.entrySet()) {
      if (entry.getValue().getName().equals(name)) {
        throw new IllegalArgumentException("Name already exists");
      }
    }

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

    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Invalid name");
    }

    // TODO: Action overlap

    IShape currentShape = getCurrentShape(name);

    IAction newAction = null;

    if (x1 != x2 || y1 != y2) {
      newAction = new Move(name, currentShape, x2, y2, t1, t2);
    }
    else if (w1 != w2 || h1 != h2) {
      newAction = new Scale(name, currentShape, w2, h2, t1, t2);
    }
    else if (r1 != r2 || g1 != g2 || b1 != b2) {
      newAction = new ChangeColor(name, currentShape,
              new RGB((double) r2, (double) g2, (double) b2), t1, t2);
    } else {
      currentShape.setPosition(x1, y1);
      currentShape.setColor(new RGB((double) r1, (double) g2, (double) b2));
      switch (currentShape.getType()) {
        case CIRCLE:
          currentShape.setRadius(w1);
          break;
        case SQUARE:
          currentShape.setLength(w1);
          break;
        default:
          currentShape.setWidth(w1);
          currentShape.setHeight(h1);
      }

      currentShape.setWidth(w1);
      currentShape.setHeight(h1);
    }

    if (newAction != null) {
      addActionToShape(name, newAction);
      chronologicalOrderOfActions.add(newAction.toString());
    } else {
      throw new IllegalArgumentException("No action registered");
    }

    return this;
  }

  @Override
  public HashMap<String, IShape> getLogOfShapes() {
    return logOfShapes;
  }

  @Override
  public HashMap<String, List<IAction>> getLogOfActions() {
    return logOfActions;
  }

  @Override
  public List<String> getChronologicalOrderOfActions() {
    return chronologicalOrderOfActions;
  }

  @Override
  public int[] getBox() {
    return box;
  }

  private IShape getCurrentShape(String name) {

    for (Map.Entry<String, IShape> objects : logOfShapes.entrySet()) {
      if (objects.getKey().equals(name)) {
        IShape accumulatorShape = objects.getValue().copy();

        if (logOfActions.size() > 0 && logOfActions.get(objects.getKey()) != null) {
          for (IAction actions : logOfActions.get(objects.getKey())) {
            accumulatorShape = actions.getCurrentShape();
          }
        }
        return accumulatorShape;
      }
    }

    throw new IllegalArgumentException("Shape does not exist");
  }

  private void addActionToShape(String name, IAction action) {
    if (logOfActions.get(name) == null) {
      logOfActions.put(name, new LinkedList<>());
    }
    logOfActions.get(name).add(action);
  }

  private boolean checkOverlap(String name, Action type, int startTime, int endTime) {
    for (Map.Entry<String, List<IAction>> entry : logOfActions.entrySet()) {
      if (entry.getKey().equals(name)) {
        List<IAction> actions = entry.getValue();
        for (IAction action : actions) {
          if (action.getType() == type) {
            if (startTime >= action.getTime().getStartTime() &&
                    startTime <= action.getTime().getEndTime() ||
                    endTime >= action.getTime().getStartTime() &&
                            endTime <= action.getTime().getEndTime()) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }

}
