package cs5004.animator;

public class Rhombus extends AbstractShape {

  public Rhombus(String name, RGB color, double width, double height,
                 int initX, int initY, int startTime, int endTime) {
    super(name, color, width, height, initX, initY, startTime, endTime);

    this.type = Shape.RHOMBUS;
  }

  @Override
  public IShape copy() {
    return new Rhombus(this.name, this.color, this.getWidth(), this.getHeight(),
            this.getPosition().getX(), this.getPosition().getY(),
            this.getTotalTime().getStartTime(), this.getTotalTime().getEndTime());
  }

}
