package cs5004.animator.action;

import cs5004.animator.shape.IShape;

/**
 * Action class for having shapes retain visibility. This action is of type Action.STAY.
 */
public class Stay extends AbstractAction {
  public Stay(String name, IShape currentShape, int startTime, int endTime) {
    super(name, currentShape, startTime, endTime);

    this.type = Action.STAY;
  }

  @Override
  public IShape getShapeAtTick(double tick, IShape shape) {
    IShape copy = shape.copy();
    if (tick >= copy.getShowTime().getStartTime()) {
      copy.setVisible(true);
    }
    if (tick < this.time.getStartTime() && copy.isVisible()) {
      return copy;
    } else if (tick > this.time.getEndTime() && copy.isVisible()) {
      return copy;
    } else {
      return copy;
    }
  }
}
