package cs5004.action;

import cs5004.shape.IShape;
import cs5004.utilities.Time;

/**
 * An interface for the actions classes that make changes specified by the user to the shape.
 */
public interface IAction {
  /**
   * A function that returns the state of the object at a particular tick. The object's state is in
   * proportion to where the tick is in the action's interval (between start and end time).
   *
   * @param tick             an int that represents the frame of the animation per unit of time.
   * @param accumulatorShape
   * @return the shape at the tick.
   * @throws IllegalArgumentException if the tick is negative. if the shape is null.
   */
  IShape getShapeAtTick(int tick, IShape accumulatorShape);

  /**
   * Gets the current shape of the Action object.
   *
   * @return the current shape of the Action object.
   */
  IShape getCurrentShape();

  /**
   * Gets the time of the Action object.
   *
   * @return the time of the Action object.
   */
  Time getTime();

  /**
   * Gets the type of the Action object.
   *
   * @return the type of the Action object.
   */
  Action getType();
}
