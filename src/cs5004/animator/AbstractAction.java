package cs5004.animator;

import cs5004.shape.IShape;
import cs5004.shape.Position;
import cs5004.shape.RGB;
import cs5004.shape.Time;

public abstract class AbstractAction implements IAction {
  protected String name;
  protected IShape currentShape;
  protected RGB newColor;
  protected Time time;
  protected RGB oldColor;

  protected Position newPosition;
  protected Position oldPosition;

  protected double oldWidth;
  protected double oldHeight;
  protected double newWidth;
  protected double newHeight;

  protected Action type;

  // For color change
  public AbstractAction(String name, IShape currentShape, RGB newColor,
                        int startTime, int endTime) {

    this.name = name;
    this.currentShape = currentShape;
    this.time = new Time(startTime, endTime);

    this.newColor = newColor;
    this.oldColor = new RGB(currentShape.getColor().getRed(), currentShape.getColor().getGreen(),
            currentShape.getColor().getBlue());
    this.currentShape.setColor(newColor);

    this.type = Action.CHANGE;
  }

  // For move and scale
  public AbstractAction(String name, IShape currentShape, double newA, double newB,
                        int startTime, int endTime) {

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

}
