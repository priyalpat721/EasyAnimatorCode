package cs5004.animator;

/**
 * Action class for scaling.
 */
public class Scale implements IAction {
  private String name;
  private double newWidth;
  private double newHeight;
  private Time time;


  public Scale(String name, double newWidth, double newHeight, int startTime, int endTime) {
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

    double oldWidth = shape.getWidth();
    double oldHeight = shape.getHeight();

    double percent = (double) (tick - this.time.getStartTime()) /
            (this.time.getEndTime() - this.time.getStartTime());

    double currentWidth = (percent * (newWidth - oldWidth) + oldWidth);
    double currentHeight = (percent * (newHeight - oldHeight) + oldHeight);
    copy.setWidth(currentWidth);
    copy.setHeight(currentHeight);
    return copy;
  }
  //  C changes from blue to green from time t=50 to t=80
  //  R changes width from 50 to 25 from time t=51 to t=70
}
