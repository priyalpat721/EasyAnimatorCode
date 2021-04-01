package cs5004.animator;

/**
 * This class represents a Square.
 * It extends the abstract class AbstractShape.
 * The circle is of type Shape.SQUARE.
 * For a square, the width and the height are equal.
 */
public class Square extends AbstractShape {

  public Square(String name, RGB color, double width, double height,
                double x, double y, int startTime, int endTime) {
    super(name, color, width, height, x, y, startTime, endTime);

    if (width != height) {
      throw new IllegalArgumentException("Width and height must be equal");
    }

    this.length = width;
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
            + ", Length: " + this.length
            + ", Color: " + this.color.toString() + "\n"
            + "Appears at t=" + this.totalTime.getStartTime() + "\n"
            + "Disappears at t=" + this.totalTime.getEndTime();
  }
}
