package cs5004.animator;

/**
 * Action class for movement.
 */
public class Move implements IActions{
  String name;
  double oldX;
  double oldY;
  double newX;
  double newY;
  int startTime;
  int endTime;

  public Move(String name, double oldX, double oldY, double newX, double newY
          , int startTime, int endTime) {
    this.name = name;
    this.oldX = oldX;
    this.oldY = oldY;
    this.newX = newX;
    this.newY = newY;
    this.startTime = startTime;
    this.endTime = endTime;
  }


  @Override
  public int getStartTime() {
    return 0;
  }

  @Override
  public int getEndTime() {
    return 0;
  }

  public IShape getShapeAtTick(int tick, IShape shape) {
    if (tick <= this.startTime) {
      return shape.copy();
    }

    else if( tick > endTime) {
      return shape.actionMove(newX, newY);
    }

    double percent = (tick - startTime) / (this.endTime - startTime);
    // start time = 5, end time = 10
    // tick is 7
    // 7-5 / 10 -5 = 2/5 into program

    // 0, 10 to 10, 10
    // 2/5 * 10 + 0 = 4 for x
    // 2/5 * 0 + 10 = 10 for y
    double currentX = (percent * (newX - oldX)) + oldX;
    double currentY = (percent * (newY - oldY)) + oldY;
    return shape.actionMove(currentX, currentY);
  }
}
