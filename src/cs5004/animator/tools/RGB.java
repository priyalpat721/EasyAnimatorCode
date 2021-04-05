package cs5004.animator.tools;

/**
 * This class represents a color in format RGB. A color has a red component, a green component, and
 * a blue component.
 */
public class RGB {
  public double red;
  public double green;
  public double blue;

  /**
   * Constructs an RGB object.
   *
   * @param red   the red component.
   * @param green the green component.
   * @param blue  the blue component.
   * @throws IllegalArgumentException if the components are not within 0-255 range.
   */
  public RGB(double red, double green, double blue) {
    if (red < 0 || red > 255) {
      throw new IllegalArgumentException("Red has to be within 0-255 range");
    } else if (green < 0 || green > 255) {
      throw new IllegalArgumentException("Green has to be within 0-255 range");
    } else if (blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Blue has to be within 0-255 range");
    }

    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Gets the red component.
   *
   * @return return the red component.
   */
  public double getRed() {
    return red;
  }

  /**
   * Gets the green component.
   *
   * @return the green component.
   */
  public double getGreen() {
    return green;
  }

  /**
   * Gets the blue component.
   *
   * @return the blue component.
   */
  public double getBlue() {
    return blue;
  }

  /**
   * A String representation of an RGB object.
   *
   * @return the string representation of an RGB object.
   */
  public String toString() {
    return String.format("(%.1f,%.1f,%.1f)", this.red, this.green, this.blue);
  }

}
