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

  // For color change
  public AbstractAction(String name, IShape currentShape, RGB newColor,
                        int startTime, int endTime) {

    this.name = name;
    this.currentShape = currentShape;
    this.newColor = newColor;
    this.time = new Time(startTime, endTime);
    this.oldColor = new RGB(currentShape.getColor().getRed(), currentShape.getColor().getGreen(),
            currentShape.getColor().getBlue());
    this.currentShape.setColor(newColor);
  }

  // For move and scale
  public AbstractAction(String name, IShape currentShape, double newA, double newB,
                        int startTime, int endTime) {

    this.name = name;
    this.currentShape = currentShape;
    this.time = new Time(startTime, endTime);


//    This goes to the super of move
//    this.newPosition = new Position(newA, newB);
//    this.oldPosition = new Position(currentShape.getPosition().getX(),
//            currentShape.getPosition().getY());
//    this.currentShape.setPosition(newPosition.getX(), newPosition.getY());


//    This goes to the super of scale
//    this.oldWidth = currentShape.getWidth();
//    this.oldHeight = currentShape.getHeight();
//    this.newWidth = newA;
//    this.newHeight = newB;
//    this.currentShape.setWidth(newA);
//    this.currentShape.setHeight(newB);

  }

  @Override
  public IShape getCurrentShape() {
    return this.currentShape;
  }

  //@Override
  public Time getTime() {
    return this.time;
  }


}
