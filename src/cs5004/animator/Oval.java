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
    return String.format("Name: %s" + "\nType: %s" + "\nMin corner: (%.1f,%.1f), "
            + "X radius: %.1f, Y radius: %.1f, Color: " + this.color + "\nAppears at t="
            + this.getTotalTime().getStartTime()
            + "\nDisappears at t=" + this.getTotalTime().getEndTime(),
        this.name, this.type.toString(), this.getPosition().getX(), this.getPosition().getY(),
        this.getWidth(), this.getHeight());
  }

}
