package cs5004.shape;

import cs5004.utilities.RGB;

/**
 * This class represents a Circle.
 * It extends the abstract class AbstractShape.
 * The circle is of type Shape.CIRCLE.
 * For a circle, the width and the height are equal.
 */
public class Circle extends AbstractShape {

  public Circle(String name, RGB color, double width, double height,
                double x, double y, int startTime, int endTime) {
    super(name, color, width, height, x, y, startTime, endTime);

    if (width != height) {
      throw new IllegalArgumentException("Width and height must be equal");
    }

    this.radius = width;
    this.type = Shape.CIRCLE;
  }

  @Override
  public Shape getType() {
    return super.getType();
  }

  @Override
  public IShape copy() {
    return new Circle(this.name, this.color, this.radius, this.radius,
            this.getPosition().getX(), this.getPosition().getY(),
            this.getTotalTime().getStartTime(), this.getTotalTime().getEndTime());
  }

  @Override
  public String toString() {
    return "Name: " + this.name + "\n"
            + "Type: " + this.type.toString() + "\n"
            + "Center: " + this.position.toString()
            + String.format(", Radius: %.1f", this.radius)
            + ", Color: " + this.color.toString() + "\n"
            + "Appears at t=" + this.totalTime.getStartTime() + "\n"
            + "Disappears at t=" + this.totalTime.getEndTime();
  }

}
