package cs5004.animator.shape;

import cs5004.animator.tools.RGB;

/**
 * This class represents a Rectangle. It extends the abstract class AbstractShape. The circle is of
 * type Shape.RECTANGLE.
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructor for the Rectangle class.
   *
   * @param name shape identifier.
   * @param color shape color.
   * @param width width of the rectangle.
   * @param height height of the rectangle.
   * @param x X coordinate for the lower left corner of the triangle.
   * @param y Y coordinate for the lower left corner of the triangle.
   * @param startTime Showtime start time for the triangle shape.
   * @param endTime Showtime end time for the triangle shape.
   */
  public Rectangle(String name, RGB color, double width, double height,
                   double x, double y, int startTime, int endTime) {
    super(name, color, width, height, x, y, startTime, endTime);

    this.type = Shape.RECTANGLE;
  }

  public Rectangle(String name) {
    super(name);
    this.type = Shape.RECTANGLE;
  }

  @Override
  public IShape copy() {
    if (this.position == null) {
      return new Rectangle(this.name);
    }

    return new Rectangle(this.name, this.color, this.getWidth(), this.getHeight(),
            this.getPosition().getX(), this.getPosition().getY(),
            this.getShowTime().getStartTime(), this.getShowTime().getEndTime());
  }

}
