package cs5004.animator;

/**
 * An interface for the actions classes that make changes specified by the user to the shape.
 */
public interface IAction {

  /**
   *Gets the start time of the event.
   * @return an int for the starting time.
   */
  int getStartTime();

  /**
   *Gets the end time of the event.
   * @return an int for the ending time.
   */
  int getEndTime();

  /**
   * A function that returns the state of the object at a particular tick. The object's state
   * is in proportion to where the tick is in the action's interval (between start and end time).
   * @param tick an int that represents the frame of the animation per unit of time.
   * @param accumulatorShape
   * @return
   */
  IShape getShapeAtTick(int tick, IShape accumulatorShape);
}
