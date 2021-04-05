package cs5004.shape;

import cs5004.utilities.RGB;

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

  @Override
  public IShape copy() {
    return new Triangle(this.name, this.color, this.getWidth(), this.getHeight(),
            this.getPosition().getX(), this.getPosition().getY(),
            this.getTotalTime().getStartTime(), this.getTotalTime().getEndTime());
  }
}
