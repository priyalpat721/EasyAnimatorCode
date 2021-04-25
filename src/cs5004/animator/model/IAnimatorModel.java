package cs5004.animator.model;

import java.util.HashMap;
import java.util.List;

import cs5004.animator.action.IAction;
import cs5004.animator.shape.Shape;
import cs5004.animator.tools.RGB;
import cs5004.animator.shape.IShape;

/**
 * An interface for the animator model.
 */
public interface IAnimatorModel {

  /**
   * Creates specific shapes based on what the user inputs.
   *
   * @param name      name of the shape that is associated with a specific type of shape.
   * @param type     a specific type of the shape class.
   * @param color     a color type of the RGB class.
   * @param width     the width of the shape. For squares and circles, the width and height are the
   *                  same.
   * @param height    the height of the shape. For squares and circles, the width and height are the
   *                  same.
   * @param x         the shape's top left corner's x coordinates.
   * @param y         the shape's top left corner's y coordinates.
   * @param startTime the start time of when the shape will appear.
   * @param endTime   the end time of when the shape will disappear.
   * @throws IllegalArgumentException if the name already exists. if the name is null or empty. if
   *                                  the shape is null. if the color is null.
   */
  void createShape(String name, Shape type, RGB color,
                   double width, double height, double x, double y, int startTime, int endTime);

  /**
   * Creates a shape based on name and shape type.
   *
   * @param name of the shape.
   * @param type of the shape.
   */
  void createShape(String name, String type);

  /**
   * Moves an object by the specified new x and new y coordinates.
   *
   * @param name      name of the shape that is associated with a specific type of shape.
   * @param newX      the shape's top left corner's new x coordinates.
   * @param newY      the shape's top left corner's new y coordinates.
   * @param startTime the start time of when the shape will appear.
   * @param endTime   the end time of when the shape will disappear.
   * @throws IllegalArgumentException if the name of the shape does not exist. if the name is null
   *                                  or empty. if there is a time overlap. if the action is out of
   *                                  range.
   */
  void move(String name, double newX, double newY, int startTime, int endTime);

  /**
   * Changes an object's color by the specified new color.
   *
   * @param name      name of the shape that is associated with a specific type of shape.
   * @param newColor  new color of the shape.
   * @param startTime the start time of when the shape will appear.
   * @param endTime   the end time of when the shape will disappear.
   * @throws IllegalArgumentException if the name of the shape does not exist. if the name is null
   *                                  or empty. if the new color is null. if there is a time
   *                                  overlap. if the action is out of range.
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
   * @throws IllegalArgumentException if the name of the shape does not exist. if the name is null
   *                                  or empty. if there is a time overlap. if the action is out of
   *                                  range.
   */
  void scale(String name, double newWidth, double newHeight, int startTime, int endTime);

  /**
   * Private method that adds a Stay action to a shape.
   *
   * @param name      name of the shape.
   * @param startTime start time of the action.
   * @param endTime   end time of the action.
   * @throws IllegalArgumentException if the name is invalid.
   */

  void stay(String name, int startTime, int endTime);

  /**
   * A function that returns a list of shapes at the specified frame. The function works by
   * identifying the tick of a move at a specified time. If the tick falls within the the interval
   * of event's duration, that specific frame is added to a list and returned back to the
   * controller.
   *
   * @param tick an int that is passed into the model. It specifies the tick at which the frame of
   *             the animation is currently in/
   * @return a list of shapes in their specific states at a certain tick. Each list return
   *            represents a specific frame in the animation.
   * @throws IllegalArgumentException if the tick is negative.
   */
  List<IShape> getShapesAtTicks(double tick);

  /**
   * A formatted representation of the modelImpl as a string.
   *
   * @return a formatted string.
   */
  String toString();

  /**
   * String representation of the modelImpl as a string.
   *
   * @param speed speed of play.
   * @return a String representation of modelImpl.
   */
  String toString(int speed);

  /**
   * Method that sets the boundaries of the window frame.
   * @param x coordinate.
   * @param y coordinate.
   * @param width width of the frame.
   * @param height height of the frame.
   */
  void setBounds(int x, int y, int width, int height);

  /**
   * Method that creates a shape and sets its initial attributes.
   *
   * @param name of the shape.
   * @param x1 x coordinate of the left corner of shape (or radius if a circle).
   * @param y1 y coordinate of the left corner of shape (or radius if a circle).
   * @param w1 width of the shape.
   * @param h1 height of the shape.
   * @param r1 red value for the RGB color of the shape.
   * @param g1 green value for the RGB color of the shape.
   * @param b1 blue value for the RGB color of the shape.
   * @param t1 starting time for the shape.
   * @param t2 ending time for the shape.
   */
  void setAttributes(String name, int x1, int y1, int w1, int h1,
                     int r1, int g1, int b1, int t1, int t2);

  /**
   * Gets the size parameters for the animation canvas.
   *
   * @return array of int representing the parameters.
   */
  int[] getBox();

  /**
   * Gets the total play time for the animation.
   *
   * @return array of int with the total animation time.
   */
  int[] getTotalTime();

  /**
   * Gets a list of the shapes to be deployed.
   *
   * @return  list of shapes.
   */
  List<IShape> getLogOfShapes();


  /**
   * Gets the log of actions for the animation.
   *
   * @return a HashMap with the shapes names and the list of Actions for each shape.
   */
  HashMap<String, List<IAction>> getLogOfActions();

  /**
   * Gets the order of actions for the entire animation.
   * @return a list of IAction where each action is assocaited with a particular shape.
   */
  List<IAction> getOrderOfActions();

  /**
   * Gets all of the motions for a single shape.
   * @param name takes in the name of the shape.
   * @return a list of all actions that are associated with a particular shape.
   */
  List<IAction> getMotionForShape(String name);

  /**
   * Removes the shape and all of its associated actions.
   * @param name takes in the name of the shape that needs to be removed.
   */
  void removeShape(String name);

  /**
   * Removes a particular action associated with a shape.
   * @param name takes in the name of the shape.
   * @param action takes in the specific action that needs to be removed from the shape.
   */
  void removeAction(String name, IAction action);
}
