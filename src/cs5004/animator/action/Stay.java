package cs5004.animator.action;

import cs5004.animator.shape.IShape;
import cs5004.animator.tools.RGB;

public class Stay extends AbstractAction{
  public Stay(String name, IShape currentShape, RGB newColor, int startTime, int endTime) {
    super(name, currentShape, newColor, startTime, endTime);
  }

  public Stay(String name, IShape currentShape, int startTime, int endTime) {
    super(name, currentShape, startTime, endTime);
  }

  @Override
  public IShape getShapeAtTick(int tick, IShape accumulatorShape) {
    return accumulatorShape;
  }
}
