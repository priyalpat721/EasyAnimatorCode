package cs5004.animator;

public class Circle extends AbstractShape {



  @Override
  public IShape copy() {
    return new Circle(this.name, this.color, this.radius,
            this.getPosition().getX(), this.getPosition().getY(),
            this.getTotalTime().getStartTime(), this.getTotalTime().getEndTime());
  }

}
