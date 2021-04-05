package cs5004.animator.shape;

import cs5004.animator.tools.Position;
import cs5004.animator.tools.RGB;
import cs5004.animator.tools.Time;

/**
 * This interface represents all the operations supported by a shape.
 */
public interface IShape {

  /**
   * Gets the name of the shape.
   *
   * @return the name of the shape.
   */
  String getName();

  /**
   * Gets the type of the shape.
   *
   * @return the type of the shape.
   */
  Shape getType();

  /**
   * Gets the total time of existence of the shape.
   *
   * @return the total time of existence of the shape.
   */
  Time getTotalTime();

  /**
   * Gets the position of the shape.
   *
   * @return the position of the shape.
   */
  Position getPosition();

  /**
   * Sets the position of the shape.
   *
   * @param newX the new X coordinate.
   * @param newY the new Y coordinate.
   */
  void setPosition(double newX, double newY);

  /**
   * Gets the color of the shape.
   *
   * @return the color of the shape.
   */
  RGB getColor();

  /**
   * Sets the color of the shape.
   *
   * @param newColor the new RGB color.
   */
  void setColor(RGB newColor);

  /**
   * Gets the width of the shape.
   *
   * @return the width of the shape.
   * @throws IllegalArgumentException if the shape is of type circle or square.
   */
  double getWidth();

  /**
   * Gets the height of the shape.
   *
   * @return the height of the shape.
   * @throws IllegalArgumentException if the shape is of type circle or square.
   */
  double getHeight();

  /**
   * Sets the width of the shape.
   *
   * @param newWidth the new width of the shape.
   * @throws IllegalArgumentException if the new width is less than or equal to zero. if the shape
   *                                  is of type circle or square.
   */
  void setWidth(double newWidth);

  /**
   * Sets the height of the shape.
   *
   * @param newHeight the new height of the shape.
   * @throws IllegalArgumentException if the new height is less than or equal to zero. if the shape
   *                                  is of type circle or square.
   */
  void setHeight(double newHeight);

  /**
   * Gets the radius of a circle shape.
   *
   * @return the radius of the circle.
   * @throws IllegalArgumentException if the shape is not of type circle.
   */
  double getRadius();

  /**
   * Sets the radius of a circle shape.
   *
   * @param newRadius the new radius of the circle.
   * @throws IllegalArgumentException if the new radius is less than or equal to zero. if the shape
   *                                  is not of type circle.
   */
  void setRadius(double newRadius);

  /**
   * Gets the length of a square shape.
   *
   * @return the shape of the square.
   * @throws IllegalArgumentException if the shape is not of type square.
   */
  double getLength();

  /**
   * Sets the length of a square shape.
   *
   * @param newLength the new radius of the square.
   * @throws IllegalArgumentException if the new length is less than or equal to zero. if the shape
   *                                  is not of type square.
   */
  void setLength(double newLength);

  /**
   * Creates a deep copy of the shape.
   *
   * @return returns a copy of the shape.
   */
  IShape copy();
}
