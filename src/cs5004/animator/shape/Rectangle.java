package cs5004.animator.shape;

import cs5004.animator.tools.RGB;

/**
 * This class represents a Rectangle. It extends the abstract class AbstractShape. The circle is of
 * type Shape.RECTANGLE.
 */
public class Rectangle extends AbstractShape {

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
