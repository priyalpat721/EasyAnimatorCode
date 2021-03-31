package cs5004.animator;

public class Position {
  public double x;
  public double y;

  public Position(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  public void setX(double newX) {
    this.x = newX;
  }

  public void setY(double newY) {
    this.y = newY;
  }

}
