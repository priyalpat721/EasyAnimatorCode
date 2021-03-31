package cs5004.animator;

public interface IShape {

  String getName();

  Shape getType();

  Time getTotalTime();

  Position getPosition();

  void setPosition(int newX, int newY);

  RGB getColor();

  void setColor(RGB newColor);

  double getWidth();

  double getHeight();

  void setWidth(double newWidth);

  void setHeight(double newHeight);

  double getRadius();

  void setRadius(double newRadius);

  double getLength();

  void setLength(double newLength);

  IShape copy();

}
