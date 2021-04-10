package cs5004.animator.tools;

/**
 * This class represents a point in the Cartesian coordinate system. A position has an x coordinate
 * and a y coordinate.
 */
public class Position {
  public double x;
  public double y;

  /**
   * Constructs a Position object.
   *
   * @param x the x coordinate.
   * @param y the y coordinate.
   */
  public Position(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Gets the X coordinate.
   *
   * @return the X coordinate.
   */
  public double getX() {
    return this.x;
  }

  /**
   * Gets the Y coordinate.
   *
   * @return the Y coordinate.
   */
  public double getY() {
    return this.y;
  }

  /**
   * String representation of a Position object.
   *
   * @return the string representation of the Position.
   */
  public String toString() {
    return String.format("(%.1f,%.1f)", this.x, this.y);
  }

}
