package cs5004.animator;

/**
 * An interface for the Shapes class. The abstract shape class implements the interface and
 * the respective functions are then used for the child shapes classes.
 */
public interface IShape {

  /**
   * Gets the name of the shape.
   * @return a string of the shape's name.
   */
  String getName();

  /**
   *Gets the type of the shape.
   * @return a shape type of the current shape.
   */
  Shape getType();

  /**
   * Gets the total time that a shape "exists" or appears.
   * @return a time class with the current time.
   */
  Time getTotalTime();

  /**
   * Gets the
   * @return
   */
  Position getPosition();

  void setPosition(double newX, double newY);

  RGB getColor();

  void setColor(RGB newColor);

  double getWidth();

  double getHeight();

  void setWidth(double newWidth);

  void setHeight(double newHeight);

  double getRadius();

  void setRadius(double newRadius);

  double getLength();

  void setLength(double newLength);

  IShape copy();
}
