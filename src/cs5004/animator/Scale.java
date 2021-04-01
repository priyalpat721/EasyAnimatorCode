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
    this.name = name;
    this.newHeight = newHeight;
    this.newWidth = newWidth;
    this.time = new Time(startTime, endTime);
  }

  @Override
  public int getStartTime() {
    return this.time.getStartTime();
  }

  @Override
  public int getEndTime() {
    return this.time.getEndTime();
  }

  @Override
  public IShape getShapeAtTick(int tick, IShape shape) {
    IShape copy = shape.copy();
    if (tick <= this.time.getStartTime()) {
      return shape.copy();
    } else if (tick > this.getEndTime()) {
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
}
