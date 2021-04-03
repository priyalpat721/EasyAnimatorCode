package cs5004.shape;

import cs5004.utilities.RGB;

/**
 * This class represents an Oval.
 * It extends the abstract class AbstractShape.
 * The circle is of type Shape.OVAL.
 */
public class Oval extends AbstractShape {

  public Oval(String name, RGB color, double width, double height,
              double x, double y, int startTime, int endTime) {
    super(name, color, width, height, x, y, startTime, endTime);

    this.type = Shape.OVAL;
  }

  @Override
  public IShape copy() {
    return new Oval(this.name, this.color, this.getWidth(), this.getHeight(),
            this.getPosition().getX(), this.getPosition().getY(),
            this.getTotalTime().getStartTime(), this.getTotalTime().getEndTime());
  }

  public String toString() {
    return "Name: " + this.name + "\n"
            + "Type: " + this.type.toString() + "\n"
            + "Center: " + this.position.toString()
            + String.format(", X radius: %.1f, Y radius: %.1f", this.width, this.height)
            + ", Color: " + this.color.toString() + "\n"
            + "Appears at t=" + this.totalTime.getStartTime() + "\n"
            + "Disappears at t=" + this.totalTime.getEndTime();
  }

}
