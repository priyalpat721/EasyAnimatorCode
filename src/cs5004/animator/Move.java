package cs5004.animator;

/**
 * Action class for movement.
 */
public class Move implements IAction {
  private String name;
  private Position newPosition;
  private Time time;
  private Position oldPosition;

  public Move(String name, double newX, double newY,
              int startTime, int endTime) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    if (name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be empty");
    }

    this.name = name;
    this.newPosition = new Position(newX, newY);
    this.time = new Time(startTime, endTime);
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
      copy.setPosition(newPosition.getX(), newPosition.getY());
      return copy;
    } else {
      this.oldPosition = new Position(shape.getPosition().getX(), shape.getPosition().getY());

      double oldX = shape.getPosition().getX();
      double oldY = shape.getPosition().getY();

      double percent = (double) (tick - this.time.getStartTime()) /
              (this.time.getEndTime() - this.time.getStartTime());

      double currentX = (percent * (newPosition.getX() - oldPosition.getX()))
              + oldPosition.getX();
      double currentY = (percent * (newPosition.getY() - oldPosition.getY()))
              + oldPosition.getY();

      copy.setPosition(currentX, currentY);

      return copy;
    }
  }

  //R moves from (200,200) to (300,300) from time t=10 to t=50
  //C moves from (500,100) to (500,400) from time t=20 to t=70

  @Override
  public String toString() {
    return this.name + " moves from " + "(" + oldPosition.getX() + ", "
            + oldPosition.getY() + ") to ("
            + newPosition.getX() + ", " + newPosition.getY() + ") " + "from time t="
            + this.time.getStartTime() + " to t=" + this.time.getEndTime();
  }
}
