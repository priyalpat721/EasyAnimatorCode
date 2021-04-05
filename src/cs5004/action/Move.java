package cs5004.action;

import cs5004.shape.IShape;
import cs5004.utilities.Position;

/**
 * Action class for movement.
 * This action is of type Action.MOVE.
 */
public class Move extends AbstractAction {

  public Move(String name, IShape currentShape, double newX, double newY,
              int startTime, int endTime) {
    super(name, currentShape, newX, newY, startTime, endTime);

    this.oldPosition = new Position(currentShape.getPosition().getX(),
            currentShape.getPosition().getY());
    this.newPosition = new Position(newX, newY);
    this.currentShape.setPosition(newX, newY);

    this.type = Action.MOVE;
  }

  public IShape getShapeAtTick(int tick, IShape shape) {
    if (tick < 0) {
      throw new IllegalArgumentException("Ticks cannot be negative");
    }
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null");
    }
    IShape copy = shape.copy();
    if (tick < this.time.getStartTime()) {
      return copy;
    } else if (tick > this.time.getEndTime()) {
      copy.setPosition(newPosition.getX(), newPosition.getY());
      return copy;
    } else {

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

  @Override
  public String toString() {
    return this.name + " moves from " + "(" + oldPosition.getX() + ", "
            + oldPosition.getY() + ") to ("
            + newPosition.getX() + ", " + newPosition.getY() + ") " + "from time t="
            + this.time.getStartTime() + " to t=" + this.time.getEndTime();
  }
}
