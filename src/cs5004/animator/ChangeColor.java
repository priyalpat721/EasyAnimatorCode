package cs5004.animator;

import cs5004.shape.IShape;
import cs5004.shape.RGB;
import cs5004.shape.Time;

/**
 * Action class for color change.
 */
public class ChangeColor implements IAction{
  private String name;
  private RGB newColor;
  private Time time;

  public ChangeColor(String name, RGB newColor, int startTime, int endTime) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    if (name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be empty");
    }
    if (newColor == null) {
      throw new IllegalArgumentException("Color cannot be null");
    }
    this.name = name;
    this.newColor = newColor;
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
    }

    else if( tick > this.time.getEndTime()) {
      copy.setColor(newColor);
      return copy;
    }

    else {
      double oldR = shape.getColor().getRed();
      double oldG = shape.getColor().getGreen();
      double oldB = shape.getColor().getBlue();

      double percent = (double) (tick - this.time.getStartTime())
              / (this.time.getEndTime() - this.time.getStartTime());

      double currentR = (percent * (newColor.getRed() - oldR)) + oldR;
      double currentG = (percent * (newColor.getBlue() - oldB)) + oldB;
      double currentB = (percent * (newColor.getGreen() - oldG)) + oldG;

      copy.setColor(new RGB(currentR, currentG, currentB));
      return copy;
    }
  }

}
