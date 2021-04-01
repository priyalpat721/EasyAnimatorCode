package cs5004.animator;

import java.util.List;

/**
 * An interface for the animator model.
 */
public interface IAnimatorModel {

  /**
   * Creates specific shapes based on what the user inputs.
   * @param name name of the shape that is associated with a specific type of shape.
   * @param shape a specific type of the shape class.
   * @param color a color type of the RGB class.
   * @param width the width of the shape.
   *              For squares and circles, the width and height are the same.
   * @param height the height of the shape.
   *    *              For squares and circles, the width and height are the same.
   * @param x the shape's bottom left corner's x coordinates.
   * @param y the shape's bottom left corner's y coordinates.
   * @param startTime the start time of when the shape will appear.
   * @param endTime the end time of when the shape will disappear.
   */
  void createShapes(String name, Shape shape, RGB color,
                    double width, double height, double x, double y, int startTime, int endTime);

  /**
   * Moves an object by the specified new x and new y coordinates.
   * @param name name of the shape that is associated with a specific type of shape.
   * @param newX the shape's bottom left corner's new x coordinates.
   * @param newY the shape's bottom left corner's new y coordinates.
   * @param startTime the start time of when the shape will appear.
   * @param endTime the end time of when the shape will disappear.
   */
  void move(String name, double newX, double newY, int startTime, int endTime);

  /**
   * Changes an object's color by the specified new color.
   * @param name name of the shape that is associated with a specific type of shape.
   * @param newColor new color of the shape.
   * @param startTime the start time of when the shape will appear.
   * @param endTime the end time of when the shape will disappear.
   */
  void changeColor(String name, RGB newColor, int startTime, int endTime);

  /**
   * Scale
   * @param name
   * @param newWidth
   * @param newHeight
   * @param startTime
   * @param endTime
   */
  void scale(String name, double newWidth, double newHeight, int startTime, int endTime);

  List<IShape> getShapesAtTicks(int tick);


}
