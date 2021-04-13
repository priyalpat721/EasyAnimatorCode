package cs5004.animator.action;

import cs5004.animator.shape.IShape;
import cs5004.animator.tools.Position;
import cs5004.animator.tools.RGB;
import cs5004.animator.tools.Time;

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
  IShape getShapeAtTick(double tick, IShape accumulatorShape);

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

  /**
   * Gets the name of the Action object.
   *
   * @return the name of the Action object.
   */
  String getName();

  /**
   * Gets the old position of the Action object.
   *
   * @return the old position of the Action object.
   */
  Position getOldPosition();

  /**
   * Gets the new position of the Action object.
   *
   * @return the new position of the Action object.
   */
  Position getNewPosition();

  /**
   * Gets the old with of the Action object.
   *
   * @return the old width of the Action object.
   */
  double getOldWidth();

  /**
   * Gets the old height of the Action object.
   *
   * @return the new height of the Action object.
   */
  double getOldHeight();

  /**
   * Gets the new width of the Action object.
   *
   * @return the new width of the Action object.
   */
  double getNewWidth();

  /**
   * Gets the new height of the Action object.
   *
   * @return the new height of the Action object.
   */
  double getNewHeight();

  /**
   * Gets the old color of the Action object.
   *
   * @return the old color of the Action object.
   */
  RGB getOldColor();

  /**
   * Gets the new color of the Action object.
   *
   * @return the new color of the Action object
   */
  RGB getNewColor();
}
