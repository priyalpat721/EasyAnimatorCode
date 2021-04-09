package cs5004.animator.shape;

import cs5004.animator.tools.RGB;

/**
 * This class represents an Ellipse. It extends the abstract class AbstractShape.
 * The ellipse is of type Shape.ELLIPSE.
 */
public class Ellipse extends AbstractShape {

  public Ellipse(String name, RGB color, double width, double height,
                 double x, double y, int startTime, int endTime) {
    super(name, color, width, height, x, y, startTime, endTime);

    this.type = Shape.ELLIPSE;
  }

  public Ellipse(String name) {
    super(name);
    this.type = Shape.ELLIPSE;
  }

  @Override
  public IShape copy() {
    if (this.position == null) {
      return new Ellipse(this.name);
    }

    return new Ellipse(this.name, this.color, this.getWidth(), this.getHeight(),
            this.getPosition().getX(), this.getPosition().getY(),
            this.getShowTime().getStartTime(), this.getShowTime().getEndTime());
  }

  public String toString() {
    return "Name: " + this.name + "\n"
           + "Type: " + this.type.toString() + "\n"
           + "Center: " + this.position.toString()
           + String.format(", X radius: %.1f, Y radius: %.1f", this.width, this.height)
           + ", Color: " + this.color.toString() + "\n"
           + "Appears at t=" + this.showTime.getStartTime() + "\n"
           + "Disappears at t=" + this.showTime.getEndTime();
  }

}
