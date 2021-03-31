package cs5004.animator;

import java.awt.Color;

public class Triangle extends AbstractShape {

  public Triangle(String name, RGB color, double width, double height,
                  int initX, int initY, int startTime, int endTime) {
    super(name, color, width, height, initX, initY, startTime, endTime);

    this.type = Shape.TRIANGLE;
  }

  @Override
  public IShape copy() {
    return new Triangle(this.name, this.color, this.getWidth(), this.getHeight(),
            this.getPosition().getX(), this.getPosition().getY(),
            this.getTotalTime().getStartTime(), this.getTotalTime().getEndTime());
  }

}
