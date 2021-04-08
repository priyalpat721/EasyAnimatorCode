package cs5004.animator.action;

import cs5004.animator.shape.IShape;
import cs5004.animator.tools.RGB;

public class Stay extends AbstractAction{
  public Stay(String name, IShape currentShape, int startTime, int endTime) {
    super(name, currentShape, startTime, endTime);

    this.type = Action.STAY;
  }

  @Override
  public IShape getShapeAtTick(int tick, IShape accumulatorShape) {
    return accumulatorShape;
  }
}
