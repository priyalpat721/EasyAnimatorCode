package cs5004.animator;

/**
 * Action class for appear and disappear.
 */
public class Visibility implements IActions{
  @Override
  public int getStartTime() {
    return 0;
  }

  @Override
  public int getEndTime() {
    return 0;
  }

  @Override
  public IShape getShapeAtTick(int tick, IShape accumulatorShape) {
    return null;
  }
}
