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

  //Name: C
  //Type: circle
  //Center: (500.0,100.0), Radius: 60.0, Color: (0.0,0.0,1.0)
  //Appears at t=6
  //Disappears at t=100

  @Override
  public String toString() {
    return "Name: " + this.name + "\n" + "Type: " + this.type.toString() + "\n" + "Center: " + "("
        + this.getPosition().getX() + "," + this.getPosition().getY() + "), "
        + "Radius: " + this.getWidth() + "," + "Color: " + "(" + this.color + ")\n"
        + "Appears at t=" + this.getTotalTime().getStartTime() + "\n"
        + "Disappears at t=" + this.getTotalTime().getEndTime() + "\n";
  }
}
