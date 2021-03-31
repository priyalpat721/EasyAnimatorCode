package cs5004.animator;

public class ChangeColor implements IActions{
  private String name;
  private RGB newColor;
  private Time time;

  public ChangeColor(String name, RGB newColor, int startTime, int endTime) {
    this.name = name;
    this.newColor = newColor;
    this.time = new Time(startTime, endTime);
  }

  @Override
  public int getStartTime() {
    return  this.time.getStartTime();
  }

  @Override
  public int getEndTime() {
    return this.time.getEndTime();
  }

  @Override
  public IShape getShapeAtTick(int tick, IShape shape) {
    if (tick <= this.time.getStartTime()) {
      return shape.copy();
    }

    else if( tick > this.time.getEndTime()) {
      return shape.actionColor(this.newColor);
    }

    double oldR = shape.getColor().getRed();
    double oldG = shape.getColor().getGreen();
    double oldB = shape.getColor().getBlue();

    double percent = (double) (tick - this.time.getStartTime())
            / (this.time.getEndTime() - this.time.getStartTime());

    // start time = 5, end time = 10
    // tick is 7
    // 7-5 / 10 -5 = 2/5 into program

    // 0, 0, 0 to 255, 255, 255
    // 2/5 * 255 + 0 = 102 for r
    // 2/5 * 255 + 0 = 102 for g
    // 2/5 * 255 + 0 = 102 for g

    double currentR = (percent * (newColor.getRed() - oldR)) + oldR;
    double currentG = (percent * (newColor.getBlue() - oldB)) + oldB;
    double currentB = (percent * (newColor.getGreen() - oldG)) + oldG;

    return shape.actionColor(new RGB(currentR, currentG, currentB));
  }

}
