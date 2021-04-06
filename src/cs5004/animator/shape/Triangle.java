package cs5004.animator.shape;

import cs5004.animator.tools.RGB;

/**
 * This class represents a Triangle. It extends the abstract class AbstractShape. The circle is of
 * type Shape.TRIANGLE.
 */
public class Triangle extends AbstractShape {

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
    return new Triangle(this.name, this.color, this.getWidth(), this.getHeight(),
            this.getPosition().getX(), this.getPosition().getY(),
            this.getBeginTime().getStartTime(), this.getBeginTime().getEndTime());
  }
}
