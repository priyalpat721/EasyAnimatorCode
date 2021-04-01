package cs5004.animator;

public class RGB {
  public double red;
  public double green;
  public double blue;

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

  public double getRed() {
    return red;
  }

  public double getGreen() {
    return green;
  }

  public double getBlue() {
    return blue;
  }

  public String toString() {
    return String.format("(%.1f,%.1f,%.1f)", this.red, this.green, this.blue);
  }

}
