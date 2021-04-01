package cs5004.animator;

/**
 * This class represents a Circle.
 * It extends the abstract class AbstractShape.
 * The circle is of type Shape.CIRCLE.
 * The circle has its width as its radius.
 */
public class Circle extends AbstractShape {

  public Circle(String name, RGB color, double width, double height,
                double x, double y, int startTime, int endTime) {
    super(name, color, width, height, x, y, startTime, endTime);

    this.radius = width;
    this.type = Shape.CIRCLE;
  }

  @Override
  public IShape copy() {
    return new Circle(this.name, this.color, this.radius, this.radius,
            this.getPosition().getX(), this.getPosition().getY(),
            this.getTotalTime().getStartTime(), this.getTotalTime().getEndTime());
  }

  //Name: C
  //Type: circle
  //Center: (500.0,100.0), Radius: 60.0, Color: (0.0,0.0,1.0)
  //Appears at t=6
  //Disappears at t=100

  @Override
  public String toString() {
    return "Name: " + this.name + "\n"
            + "Type: " + this.type.toString() + "\n"
            + "Center: " + this.position.toString()
            + ", Radius: " + this.radius
            + ", Color: " + this.color.toString() + "\n"
            + "Appears at t=" + this.totalTime.getStartTime() + "\n"
            + "Disappears at t=" + this.totalTime.getEndTime();
  }
}
