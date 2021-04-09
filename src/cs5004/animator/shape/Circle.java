package cs5004.animator.shape;

import cs5004.animator.tools.RGB;

/**
 * This class represents a Circle. It extends the abstract class AbstractShape. The circle is of
 * type Shape.CIRCLE. For a circle, the width and the height are equal.
 */
public class Circle extends AbstractShape {

  public Circle(String name, RGB color, double radius, double radiusB,
                double x, double y, int startTime, int endTime) {
    super(name, color, radius, radiusB, x, y, startTime, endTime);

    if (radius != radiusB) {
      throw new IllegalArgumentException("Radius and RadiusB must be equal");
    }

    this.radius = radius;
    this.type = Shape.CIRCLE;
  }

  public Circle(String name) {
    super(name);
    this.type = Shape.CIRCLE;
  }

  @Override
  public Shape getType() {
    return super.getType();
  }

  @Override
  public IShape copy() {
    if (this.position == null) {
      return new Circle(this.name);
    }

    return new Circle(this.name, this.color, this.radius, this.radius,
            this.getPosition().getX(), this.getPosition().getY(),
            this.getShowTime().getStartTime(), this.getShowTime().getEndTime());
  }

  @Override
  public String toString() {
    return "Name: " + this.name + "\n"
           + "Type: " + this.type.toString() + "\n"
           + "Center: " + this.position.toString()
           + String.format(", Radius: %.1f", this.radius)
           + ", Color: " + this.color.toString() + "\n"
           + "Appears at t=" + this.showTime.getStartTime() + "\n"
           + "Disappears at t=" + this.showTime.getEndTime();
  }

}
