package cs5004.animator;

/**
 * This class represents a Square.
 * It extends the abstract class AbstractShape.
 * The circle is of type Shape.SQUARE.
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

}
