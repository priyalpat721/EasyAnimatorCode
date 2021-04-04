package cs5004.action;

import cs5004.shape.IShape;
import cs5004.utilities.Position;
import cs5004.utilities.RGB;
import cs5004.utilities.Time;

/**
 * This class represents an action. It implements the interface IAction. The action has a name, a
 * current shape, an old color, a new color, a time, an old position, a new position, and old width,
 * an old height, a new width, a new height, and a type.
 */
public abstract class AbstractAction implements IAction {
  protected String name;
  protected IShape currentShape;
  protected Time time;
  protected RGB oldColor;
  protected RGB newColor;
  protected Position oldPosition;
  protected Position newPosition;
  protected double oldWidth;
  protected double oldHeight;
  protected double newWidth;
  protected double newHeight;
  protected Action type;

  /**
   * Constructs an IAction object. This constructor is used by the ChangeColor class.
   *
   * @param name         the name of the shape.
   * @param currentShape the current state of the shape.
   * @param newColor     the new color of the shape.
   * @param startTime    the start time of execution of the action.
   * @param endTime      the end time of execution of the action.
   * @throws IllegalArgumentException if the name is null or empty.
   *                                  if the shape is null.
   *                                  if the color is null.
   */
  public AbstractAction(String name, IShape currentShape, RGB newColor,
                        int startTime, int endTime) {

    checkArguments(name, currentShape);

    if (newColor == null) {
      throw new IllegalArgumentException("Invalid color");
    }

    this.name = name;
    this.currentShape = currentShape;
    this.time = new Time(startTime, endTime);

    this.oldColor = new RGB(currentShape.getColor().getRed(), currentShape.getColor().getGreen(),
            currentShape.getColor().getBlue());
    this.newColor = newColor;
    this.currentShape.setColor(newColor);

    this.type = Action.CHANGECOLOR;
  }

  /**
   * Constructs an IAction object. This constructor is used by the Move and Scale classes.
   *
   * @param name         the name of the shape.
   * @param currentShape the current state of the shape.
   * @param newA         for the move class: the x coordinate. for the scale class: the width.
   * @param newB         for the move class: the y coordinate. for the scale class: the height.
   * @param startTime    the start time of execution of the action.
   * @param endTime      the end time of execution of the action.
   * @throws IllegalArgumentException if the name is null or empty.
   *                                  if the shape is null.
   */
  public AbstractAction(String name, IShape currentShape, double newA, double newB,
                        int startTime, int endTime) {

    checkArguments(name, currentShape);

    this.name = name;
    this.currentShape = currentShape;
    this.time = new Time(startTime, endTime);
  }

  @Override
  public IShape getCurrentShape() {
    return this.currentShape;
  }

  @Override
  public Time getTime() {
    return this.time;
  }

  @Override
  public Action getType() {
    return this.type;
  }

  /**
   * Private method that throws IllegalArgumentException if the name or the shape are invalid.
   * @param name the name of the shape.
   * @param currentShape the shape.
   */
  private void checkArguments(String name, IShape currentShape) {
    if (currentShape == null) {
      throw new IllegalArgumentException("Invalid currentShape");
    } else if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Invalid name");
    } else if (!name.equals(currentShape.getName())) {
      throw new IllegalArgumentException("The action is not for the shape specified");
    }
  }

}
