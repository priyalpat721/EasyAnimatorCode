package cs5004.animator.shape;

import cs5004.animator.tools.RGB;

/**
 * This class represents an Ellipse. It extends the abstract class AbstractShape.
 * The ellipse is of type Shape.ELLIPSE.
 */
public class Ellipse extends AbstractShape {

  /**
   * Constructor for the Ellipse class.
   *
   * @param name shape identifier.
   * @param color shape color.
   * @param width Ellipse width.
   * @param height Ellipse height.
   * @param x X coordinate for center of the Ellipse object.
   * @param y Y coordinate for center of the Ellipse object.
   * @param startTime Showtime start time for Ellipse object.
   * @param endTime Showtime end time for Ellipse object.
   */
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

  /**
   * String method for Ellipse class.
   *
   * @return a String representing the Ellipse and its attributes.
   */
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
