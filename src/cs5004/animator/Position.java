package cs5004.animator;

public class Position {
  public double x;
  public double y;

  public Position(double x, double y) {
    if (x < 0) {
      throw new IllegalArgumentException("X cannot be negative");
    } else if (y < 0) {
      throw new IllegalArgumentException("Y cannot be negative");
    }

    this.x = x;
    this.y = y;
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  public String toString() {
    return String.format("(%.1f,%.1f)", this.x, this.y);
  }

}
