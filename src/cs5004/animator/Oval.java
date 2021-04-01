package cs5004.animator;

public class Oval extends AbstractShape {

  public Oval(String name, RGB color, double width, double height,
              double x, double y, int startTime, int endTime) {
    super(name, color, width, height, x, y, startTime, endTime);

    this.type = Shape.OVAL;
  }

  @Override
  public IShape copy() {
    return new Oval(this.name, this.color, this.getWidth(), this.getHeight(),
            this.getPosition().getX(), this.getPosition().getY(),
            this.getTotalTime().getStartTime(), this.getTotalTime().getEndTime());
  }
  //Name: C
  //Type: oval
  //Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)
  //Appears at t=6
  //Disappears at t=100

  public String toString() {
    return "Name: " + this.name + "\n" + "Type: " + this.type.toString() + "\n" + "Min corner: " + "("
        + this.getPosition().getX() + "," + this.getPosition().getY() + "), "
        + "X radius: " + this.getWidth() + "," + "Y radius: " + this.getHeight()
        + "Color: " + "(" + this.color + ")\n"
        + "Appears at t=" + this.getTotalTime().getStartTime() + "\n"
        + "Disappears at t=" + this.getTotalTime().getEndTime() + "\n";
  }

}
