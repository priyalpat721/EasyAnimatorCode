package cs5004.animator.action;

import cs5004.animator.shape.IShape;
import cs5004.animator.tools.Position;

/**
 * Action class for movement. This action is of type Action.MOVE.
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

  public IShape getShapeAtTick(double tick, IShape shape) {
    if (tick < 0) {
      throw new IllegalArgumentException("Ticks cannot be negative");
    }
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null");
    }
    IShape copy = shape.copy();
    if (shape.isVisible()) {
      copy.setVisible(shape.isVisible());
    }
    if (tick >= copy.getShowTime().getStartTime()) {
      copy.setVisible(true);
    }
    if (tick < this.time.getStartTime() && copy.isVisible()) {
      return copy;
    } else if (tick > this.time.getEndTime() && copy.isVisible()) {
      copy.setPosition(newPosition.getX(), newPosition.getY());
      return copy;
    } else {

      double percent =  (tick - this.time.getStartTime()) /
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
