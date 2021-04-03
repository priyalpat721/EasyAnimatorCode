package cs5004.animator;

import java.util.List;

import cs5004.action.IAction;
import cs5004.shape.IShape;
import cs5004.utilities.RGB;
import cs5004.shape.Shape;

/**
 * An interface for the animator model.
 */
public interface IAnimatorModel {

  /**
   * Creates specific shapes based on what the user inputs.
   *
   * @param name      name of the shape that is associated with a specific type of shape.
   * @param shape     a specific type of the shape class.
   * @param color     a color type of the RGB class.
   * @param width     the width of the shape.
   *                  For squares and circles, the width and height are the same.
   * @param height    the height of the shape.
   *                  For squares and circles, the width and height are the same.
   * @param x         the shape's top left corner's x coordinates.
   * @param y         the shape's top left corner's y coordinates.
   * @param startTime the start time of when the shape will appear.
   * @param endTime   the end time of when the shape will disappear.
   * @throws IllegalArgumentException if the name already exists.
   *                                  if the name is null or empty.
   *                                  if the shape is null.
   *                                  if the color is null.
   */
  void createShape(String name, Shape shape, RGB color,
                   double width, double height, double x, double y, int startTime, int endTime);

  /**
   * Moves an object by the specified new x and new y coordinates.
   *
   * @param name      name of the shape that is associated with a specific type of shape.
   * @param newX      the shape's top left corner's new x coordinates.
   * @param newY      the shape's top left corner's new y coordinates.
   * @param startTime the start time of when the shape will appear.
   * @param endTime   the end time of when the shape will disappear.
   * @throws IllegalArgumentException if the name of the shape does not exist.
   *                                  if the name is null or empty.
   *                                  if there is a time overlap.
   */
  void move(String name, double newX, double newY, int startTime, int endTime);

  /**
   * Changes an object's color by the specified new color.
   *
   * @param name      name of the shape that is associated with a specific type of shape.
   * @param newColor  new color of the shape.
   * @param startTime the start time of when the shape will appear.
   * @param endTime   the end time of when the shape will disappear.
   * @throws IllegalArgumentException if the name of the shape does not exist.
   *                                  if the name is null or empty.
   *                                  if the new color is null.
   *                                  if there is a time overlap.
   */
  void changeColor(String name, RGB newColor, int startTime, int endTime);

  /**
   * Scale an object's dimensions by the new specified width and height.
   *
   * @param name      name of the shape that is associated with a specific type of shape.
   * @param newWidth  the new width of the shape.
   * @param newHeight the new height of the shape.
   * @param startTime the start time of when the shape will appear.
   * @param endTime   the end time of when the shape will disappear.
   * @throws IllegalArgumentException if the name of the shape does not exist.
   *                                  if the name is null or empty.
   *                                  if there is a time overlap.
   */
  void scale(String name, double newWidth, double newHeight, int startTime, int endTime);

  /**
   * A general function that takes in an action from the user and adds it to the hashmap.
   *
   * @param name    name of the shape that is associated with a specific type of shape.
   * @param action is one of the IAction: Move, ChangeColor or Scale.
   */
  void addAction(String name, IAction action);

  /**
   * A function that returns a list of shapes at the specified frame. The function works by
   * identifying the tick of a move at a specified time. If the tick falls within the the interval
   * of event's duration, that specific frame is added to a list and returned back to the
   * controller.
   *
   * @param tick an int that is passed into the model. It specifies the tick at which the frame of
   *             the animation is currently in/
   * @return a list of shapes in their specific states at a certain tick. Each list return
   * represents a specific frame in the animation.
   */
  List<IShape> getShapesAtTicks(int tick);

  /**
   * A formatted representation of the modelImpl as a string.
   * @return a formatted string.
   */
  String toString();
}
