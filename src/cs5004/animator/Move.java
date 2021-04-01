package cs5004.animator;

/**
 * Action class for movement.
 */
public class Move implements IAction {
  private String name;
  private double newX;
  private double newY;
  private Time time;
  double oldX;
  double oldY;

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
    IShape copy = shape.copy();
    if (tick <= this.time.getStartTime()) {
      return copy;
    }

    else if(tick > this.time.getEndTime()) {
      copy.setPosition(newX, newY);
      return copy;
    }
    else {
      oldX = shape.getPosition().getX();
      oldY = shape.getPosition().getY();

      double percent = (double) (tick - this.time.getStartTime()) /
              (this.time.getEndTime() - this.time.getStartTime());

      double currentX = (percent * (newX - oldX)) + oldX;
      double currentY = (percent * (newY - oldY)) + oldY;

      copy.setPosition(currentX, currentY);

      return copy;
    }
  }

  //R moves from (200,200) to (300,300) from time t=10 to t=50
  //C moves from (500,100) to (500,400) from time t=20 to t=70

  @Override
  public String toString() {
    return this.name + " moves from " + "(" + oldX + ", " + oldY + ") to ("
        + this.newX + ", " + this.newY + ") " + "from time t="
        + this.time.getStartTime() + " to t=" + this.time.getEndTime();
  }
}
