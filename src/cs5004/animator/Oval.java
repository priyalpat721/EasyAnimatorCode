package cs5004.animator;

/**
 * This class represents an Oval.
 * It extends the abstract class AbstractShape.
 * The circle is of type Shape.OVAL.
 */
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
    return "Name: " + this.name + "\nType: " + this.type.toString()
            + "\nMin corner: " + this.position.toString() + ", X radius: " + this.width +
            ", Y radius: " + this.height + ", Color: " + this.color.toString() + "\nAppears at t="
            + this.totalTime.getStartTime()
            + "\nDisappears at t=" + this.totalTime.getEndTime();
  }
}
