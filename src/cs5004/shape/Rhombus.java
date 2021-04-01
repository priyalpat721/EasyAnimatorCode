package cs5004.shape;

/**
 * This class represents a Rhombus.
 * It extends the abstract class AbstractShape.
 * The circle is of type Shape.RHOMBUS.
 */
public class Rhombus extends AbstractShape {

  public Rhombus(String name, RGB color, double width, double height,
                 double x, double y, int startTime, int endTime) {
    super(name, color, width, height, x, y, startTime, endTime);

    this.type = Shape.RHOMBUS;
  }

  @Override
  public IShape copy() {
    return new Rhombus(this.name, this.color, this.getWidth(), this.getHeight(),
            this.getPosition().getX(), this.getPosition().getY(),
            this.getTotalTime().getStartTime(), this.getTotalTime().getEndTime());
  }

}
