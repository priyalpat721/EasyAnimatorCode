package cs5004.animator;

/**
 * This class represents a Square.
 * It extends the abstract class AbstractShape.
 * The circle is of type Shape.SQUARE.
 * The square has its width as its length.
 */
public class Square extends AbstractShape {

  public Square(String name, RGB color, double width, double height,
                double x, double y, int startTime, int endTime) {
    super(name, color, width, height, x, y, startTime, endTime);

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
    return "Name: " + this.name + "\n" + "Type: " + this.type.toString() + "\n" + "Min corner: "
        + this.position.toString() + " Length: " + length + ", " + "Color: " + this.color
        + "\nAppears at t=" + this.getTotalTime().getStartTime()
        + "\nDisappears at t=" + this.getTotalTime().getEndTime();
  }
}
