package cs5004.animator;

/**
 * Action class for movement.
 */
public class Move implements IActions{
  private String name;
  private double newX;
  private double newY;
  private Time time;

  public Move(String name, double newX, double newY
          , int startTime, int endTime) {
    this.name = name;
    this.newX = newX;
    this.newY = newY;
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

  public IShape getShapeAtTick(int tick, IShape shape) {
    if (tick <= this.time.getStartTime()) {
      return shape.copy();
    }

    else if(tick > this.time.getEndTime()) {
      return shape.actionMove(newX, newY);
    }
    double oldX = shape.getPosition().getX();
    double oldY = shape.getPosition().getY();

    double percent = (double) (tick - this.time.getStartTime()) /
            (this.time.getEndTime() - this.time.getStartTime());
    // start time = 5, end time = 10
    // tick is 7
    // 7-5 / 10 -5 = 2/5 into program

    // 0, 10 to 10, 10
    // 2/5 * 10 + 0 = 4 for x
    // 2/5 * 0 + 10 = 10 for y
    IShape copy = shape.copy();

    double currentX = (percent * (newX - oldX)) + oldX;
    double currentY = (percent * (newY - oldY)) + oldY;

    copy.setPosition(currentX, currentY);

    return copy;

    //return shape.actionMove(currentX, currentY);
  }


}
