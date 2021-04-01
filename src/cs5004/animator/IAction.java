package cs5004.animator;

/**
 * An interface for the actions classes that make changes specified by the user to the shape.
 */
public interface IAction {

  /**
   *
   * @return
   */
  int getStartTime();

  int getEndTime();

  IShape getShapeAtTick(int tick, IShape accumulatorShape);

}
