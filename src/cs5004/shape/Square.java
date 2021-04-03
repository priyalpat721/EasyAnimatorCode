package cs5004.shape;

import cs5004.utilities.RGB;

/**
 * This class represents a Square.
 * It extends the abstract class AbstractShape.
 * The circle is of type Shape.SQUARE.
 * For a square, the width and the height are equal.
 */
public class Square extends AbstractShape {

  public Square(String name, RGB color, double length, double lengthB,
                double x, double y, int startTime, int endTime) {
    super(name, color, length, lengthB, x, y, startTime, endTime);

    if (length != lengthB) {
      throw new IllegalArgumentException("Length and LengthB must be equal");
    }

    this.length = length;
    this.type = Shape.SQUARE;
  }

  @Override
  public IShape copy() {
    return new Square(this.name, this.color, this.length, this.length,
            this.getPosition().getX(), this.getPosition().getY(),
            this.getTotalTime().getStartTime(), this.getTotalTime().getEndTime());
  }

  @Override
  public String toString() {
    return "Name: " + this.name + "\n" +
            "Type: " + this.type.toString() + "\n"
            + "Min corner: " + this.position.toString()
            + String.format(", Length: %.1f", this.length)
            + ", Color: " + this.color.toString() + "\n"
            + "Appears at t=" + this.totalTime.getStartTime() + "\n"
            + "Disappears at t=" + this.totalTime.getEndTime();
  }
}
