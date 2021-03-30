package cs5004.animator;

public class Position {
  private int x;
  private int y;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public void setX(int newX) {
    this.x = newX;
  }

  public void setY(int newY) {
    this.y = newY;
  }

}
