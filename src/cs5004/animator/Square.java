package cs5004.animator;

import java.awt.Color;

public class Square extends AbstractShape {

  public Square(String name, Color color, double size,
                int initX, int initY, int startTime, int endTime) {
    super(name, color, size, initX, initY, startTime, endTime);

    this.length = size;
    this.type = Shape.SQUARE;
  }

  @Override
  public IShape copy() {
    return new Square(this.name, this.color, this.length,
            this.getPosition().getX(), this.getPosition().getY(),
            this.getTotalTime().getStartTime(), this.getTotalTime().getEndTime());
  }

}
