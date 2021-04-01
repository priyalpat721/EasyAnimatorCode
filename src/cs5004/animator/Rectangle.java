package cs5004.animator;

public class Rectangle extends AbstractShape {

  public Rectangle(String name, RGB color, double width, double height,
                   double x, double y, int startTime, int endTime) {
    super(name, color, width, height, x, y, startTime, endTime);

    this.type = Shape.RECTANGLE;
  }

  // use only oval and rectangle
  // add to interface
  @Override
  public IShape copy() {
    return new Rectangle(this.name, this.color, this.getWidth(), this.getHeight(),
            this.getPosition().getX(), this.getPosition().getY(),
            this.getTotalTime().getStartTime(), this.getTotalTime().getEndTime());
  }


  public IShape actionMove ( double x, double y){
    return new Rectangle(this.name, this.color, this.getWidth(), this.getHeight(), x, y,
            this.getTotalTime().getStartTime(), this.getTotalTime().getEndTime());
  }

  public IShape actionColor (RGB color){
    return new Rectangle(this.name, color, this.getWidth(), this.getHeight(),
            this.getPosition().getX(), this.getPosition().getY(),
            this.getTotalTime().getStartTime(), this.getTotalTime().getEndTime());
  }

  public IShape actionScale ( double width, double height){
    return new Rectangle(this.name, this.color, width, height,
            this.getPosition().getX(), this.getPosition().getY(),
            this.getTotalTime().getStartTime(), this.getTotalTime().getEndTime());
  }

  // This needs to be redone. Maybe have a flag for visibility where if false = disappear,
  // true = appear and the controller decides what to do based on the flag
  // appear or disappear use a flag
  public IShape actionMove () {
    return new Rectangle(this.name, color, this.getWidth(), this.getHeight(),
            this.getPosition().getX(), this.getPosition().getY(),
            this.getTotalTime().getStartTime(), this.getTotalTime().getEndTime());
  }
}
