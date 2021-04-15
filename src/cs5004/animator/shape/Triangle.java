package cs5004.animator.shape;

import cs5004.animator.tools.RGB;

/**
 * This class represents a Triangle. It extends the abstract class AbstractShape. The circle is of
 * type Shape.TRIANGLE.
 */
public class Triangle extends AbstractShape {

  /**
   * Constructor for the Triangle class.
   *
   * @param name shape identifier.
   * @param color shape color.
   * @param width base of the Triangle shape object.
   * @param height height of the Triangle shape object.
   * @param x X coordinate of the lower left corner of the Triangle.
   * @param y Y coordinate of the lower left corner of the Triangle.
   * @param startTime Showtime start time of the Triangle shape object.
   * @param endTime Showtime end time of the Triangle shape object.
   */
  public Triangle(String name, RGB color, double width, double height,
                  double x, double y, int startTime, int endTime) {
    super(name, color, width, height, x, y, startTime, endTime);

    this.type = Shape.TRIANGLE;
  }

  public Triangle(String name) {
    super(name);
    this.type = Shape.TRIANGLE;
  }

  @Override
  public IShape copy() {
    if (this.position == null) {
      return new Triangle(this.name);
    }

    return new Triangle(this.name, this.color, this.getWidth(), this.getHeight(),
            this.getPosition().getX(), this.getPosition().getY(),
            this.getShowTime().getStartTime(), this.getShowTime().getEndTime());
  }
}
