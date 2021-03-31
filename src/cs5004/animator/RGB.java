package cs5004.animator;

public class RGB {
  public double red;
  public double green;
  public double blue;

  public double getRed() {
    return red;
  }

  public double getGreen() {
    return green;
  }

  public double getBlue() {
    return blue;
  }

  public RGB (double red, double green, double blue) {
    if (red < 0 && red > 255) {
      throw new IllegalArgumentException("Red has to be within 0-255 range.");
    }
    else {
      this.red = red;
    }
    if (green < 0 && green > 255) {
      throw new IllegalArgumentException("Green has to be within 0-255 range.");
    }
    else {
      this.green = green;
    }
    if (blue < 0 && red > 255) {
      throw new IllegalArgumentException("Blue has to be within 0-255 range.");
    }
    else {
      this.blue = blue;
    }
  }

  public void setRed(double red) {
    this.red = red;
  }

  public void setGreen(double green) {
    this.green = green;
  }

  public void setBlue(double blue) {
    this.blue = blue;
  }

  public double


}
