package cs5004.animator;

/**
 * Action class for movement.
 */
public class Move implements IAction {
  private String name;
  private double newX;
  private double newY;
  private Time time;

  public Move(String name, double newX, double newY
          , int startTime, int endTime) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    if (name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be empty");
    }
    if (newX < 0 || newY < 0) {
      throw new IllegalArgumentException("Coordinates cannot be negative");
    }

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
    if (tick < 0) {
      throw new IllegalArgumentException("Ticks cannot be negative");
    }
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null");
    }
    IShape copy = shape.copy();
    if (tick <= this.time.getStartTime()) {
      return copy;
    } else if (tick > this.time.getEndTime()) {
      copy.setPosition(newX, newY);
      return copy;
    } else {
      double oldX = shape.getPosition().getX();
      double oldY = shape.getPosition().getY();

      double percent = (double) (tick - this.time.getStartTime()) /
              (this.time.getEndTime() - this.time.getStartTime());

      double currentX = (percent * (newX - oldX)) + oldX;
      double currentY = (percent * (newY - oldY)) + oldY;

      copy.setPosition(currentX, currentY);

      return copy;
    }
  }
}
