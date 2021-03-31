package cs5004.animator;

public class Square extends AbstractShape {



  @Override
  public IShape copy() {
    return new Square(this.name, this.color, this.length,
            this.getPosition().getX(), this.getPosition().getY(),
            this.getTotalTime().getStartTime(), this.getTotalTime().getEndTime());
  }

}
