package cs5004.animator;

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

}
