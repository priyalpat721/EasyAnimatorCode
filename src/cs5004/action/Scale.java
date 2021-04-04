package cs5004.action;

import cs5004.shape.IShape;
import cs5004.shape.Shape;

/**
 * Action class for scaling.
 * This action is of type Action.SCALE.
 */
public class Scale extends AbstractAction {

  public Scale(String name, IShape currentShape, double newWidth, double newHeight,
               int startTime, int endTime) {
    super(name, currentShape, newWidth, newHeight, startTime, endTime);

    this.oldWidth = currentShape.getWidth();
    this.oldHeight = currentShape.getHeight();
    this.newWidth = newWidth;
    this.newHeight = newHeight;
    this.currentShape.setWidth(newWidth);
    this.currentShape.setHeight(newHeight);

    this.type = Action.SCALE;
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

  @Override
  public String toString() {
    if (currentShape.getType() == Shape.OVAL) {
      return name + " scales from X radius:, " + oldWidth + "Y radius: "
          + oldHeight + " to X radius, " + newWidth + "Y radius "
          + newHeight + "from time t="
          + this.time.getStartTime() + " to t=" + this.time.getEndTime();
    } else if (currentShape.getType() == Shape.CIRCLE) {
      return name + " scales from Radius: " + oldWidth + " to Radius: "
          + newWidth + ", Y radius " + newHeight + "from time t="
          + this.time.getStartTime() + " to t=" + this.time.getEndTime();
    } else if (currentShape.getType() == Shape.SQUARE) {
      return name + " scales from Length: " + oldWidth + " to Length: "
          + newWidth + "from time t=" + this.time.getStartTime()
          + " to t=" + this.time.getEndTime();
    } else {
      return name + " scales from Width: " + oldWidth + ", Height: "
          + oldHeight + " to Width: " + newWidth + ", Height: "
          + newHeight  + " from time t="
          + this.time.getStartTime() + " to t=" + this.time.getEndTime();
    }
  }
  //Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 from t=51 to t=70

}
