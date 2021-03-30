package cs5004.animator;

import java.awt.Color;

public class Circle extends AbstractShape {

  public Circle(String name, Color color, double size,
                int initX, int initY, int startTime, int endTime) {
    super(name, color, size, initX, initY, startTime, endTime);

    this.radius = size;
    this.type = Shape.CIRCLE;
  }

  @Override
  public IShape copy() {
    return new Circle(this.name, this.color, this.radius,
            this.getPosition().getX(), this.getPosition().getY(),
            this.getTotalTime().getStartTime(), this.getTotalTime().getEndTime());
  }

}
