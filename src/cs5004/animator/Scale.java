package cs5004.animator;

import cs5004.shape.IShape;
import cs5004.shape.Shape;
import cs5004.shape.Time;

/**
 * Action class for scaling.
 */
public class Scale implements IAction {
  private String name;
  private IShape currentShape;
  private double oldWidth;
  private double oldHeight;
  private double newWidth;
  private double newHeight;
  private Time time;


  public Scale(String name, IShape currentShape, double newWidth, double newHeight, int startTime, int endTime) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    if (name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be empty");
    }

    if (newWidth <= 0 || newHeight <= 0) {
      throw new IllegalArgumentException("Width or height cannot be negative or zero");
    }
    this.name = name;
    this.currentShape = currentShape;
    this.oldWidth = currentShape.getWidth();
    this.oldHeight = currentShape.getHeight();
    this.newHeight = newHeight;
    this.newWidth = newWidth;
    this.time = new Time(startTime, endTime);
  }

  @Override
  public IShape getShapeAtTick(int tick, IShape shape) {
    if (tick < 0) {
      throw new IllegalArgumentException("Ticks cannot be negative");
    }
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null");
    }
    IShape copy = shape.copy();
    if (tick <= this.time.getStartTime()) {
      return shape.copy();
    } else if (tick > this.time.getEndTime()) {
      copy.setWidth(newWidth);
      copy.setHeight(newHeight);
      return copy;
    }


    double percent = (double) (tick - this.time.getStartTime()) /
            (this.time.getEndTime() - this.time.getStartTime());

    double currentWidth = (percent * (newWidth - oldWidth) + oldWidth);
    double currentHeight = (percent * (newHeight - oldHeight) + oldHeight);
    copy.setWidth(currentWidth);
    copy.setHeight(currentHeight);
    return copy;
  }

  //  R changes width from 50 to 25 from time t=51 to t=70

  @Override
  public String toString() {
    if (currentShape.getType() == Shape.OVAL) {
      return name + " changes from X radius " + oldWidth + "and Y radius "
          + oldHeight + " to X radius " + newWidth + " and Y radius "
          + newHeight + "from time t="
          + this.time.getStartTime() + " to t=" + this.time.getEndTime();
    } else if (currentShape.getType() == Shape.CIRCLE) {
      return name + " changes from radius " + oldWidth + " to radius "
          + newWidth + " and Y radius " + newHeight + "from time t="
          + this.time.getStartTime() + " to t=" + this.time.getEndTime();
    } else if (currentShape.getType() == Shape.SQUARE) {
      return name + " changes from length " + oldWidth + " to length "
          + newWidth + "from time t=" + this.time.getStartTime()
          + " to t=" + this.time.getEndTime();
    } else {
      return name + " changes from width " + oldWidth + " and height "
          + oldHeight + " to width " + newWidth + " and height "
          + newHeight + " to height " + newWidth + "from time t="
          + this.time.getStartTime() + " to t=" + this.time.getEndTime();
    }
  }

}
