package cs5004.animator.action;

import cs5004.animator.shape.IShape;
import cs5004.animator.tools.RGB;

/**
 * Action class for color change. This action is of type Action.CHANGECOLOR.
 */
public class ChangeColor extends AbstractAction {

  public ChangeColor(String name, IShape currentShape, RGB newColor,
                     int startTime, int endTime) {
    super(name, currentShape, newColor, startTime, endTime);
  }

  @Override
  public IShape getShapeAtTick(double tick, IShape shape) {
    if (tick < 0) {
      throw new IllegalArgumentException("Ticks cannot be negative");
    }
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null");
    }
    IShape copy = shape.copy();
    if (shape.isVisible()) {
      copy.setVisible(shape.isVisible());
    }
    if (tick <= this.time.getStartTime()) {
      return copy;
    } else if (tick > this.time.getEndTime()) {
      copy.setColor(newColor);
      return copy;
    } else {

      double percent = (double) (tick - this.time.getStartTime())
                       / (this.time.getEndTime() - this.time.getStartTime());

      double currentR = (percent * (newColor.getRed() - oldColor.getRed())) + oldColor.getRed();
      double currentG = (percent * (newColor.getGreen() - oldColor.getGreen()))
                        + oldColor.getGreen();
      double currentB = (percent * (newColor.getBlue() - oldColor.getBlue())) + oldColor.getBlue();
      copy.setColor(new RGB(currentR, currentG, currentB));
      return copy;
    }
  }

  @Override
  public String toString() {
    return name + " changes color from " + oldColor.toString()
           + " to " + newColor.toString() + " from time t=" + this.time.getStartTime()
           + " to t=" + this.time.getEndTime();
  }

}