package cs5004.animator;

public abstract class AbstractShape implements IShape {
  protected String name;
  protected Shape type;
  protected Position position;
  protected Time totalTime;
  protected RGB color;
  protected double length;
  protected double radius;
  protected double width;
  protected double height;

  // Square and Circle
  public AbstractShape(String name, RGB color, double size,
                       int x, int y, int startTime, int endTime) {
    if (size <= 0) {
      throw new IllegalArgumentException("Invalid size");
    }

    this.name = name;
    this.color = color;
    this.position = new Position(x, y);
    this.totalTime = new Time(startTime, endTime);

  }


  // Rectangle, Triangle, Rhombus, and Oval
  public AbstractShape(String name, RGB color, double width, double height,
                       int x, int y, int startTime, int endTime) {
    if (width <= 0) {
      throw new IllegalArgumentException("Invalid width");
    } else if (height <= 0) {
      throw new IllegalArgumentException("Invalid height");
    }

    this.name = name;
    this.color = color;
    this.position = new Position(x, y);
    this.totalTime = new Time(startTime, endTime);

    this.width = width;
    this.height = height;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Shape getType() {
    return this.type;
  }

  @Override
  public Time getTotalTime() {
    return this.totalTime;
  }

  @Override
  public Position getPosition() {
    return this.position;
  }

  @Override
  public void setPosition(int newX, int newY) {
    this.position.setX(newX);
    this.position.setY(newY);
  }

  @Override
  public RGB getColor() {
    return this.color;
  }

  @Override
  public void setColor(RGB newColor) {
    this.color = newColor;
  }

  @Override
  public double getSize() {
    if (this.type == Shape.CIRCLE) {
      return this.radius;
    } else if (this.type == Shape.SQUARE) {
      return this.length;
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }

  @Override
  public double getWidth() {
    if (this.type != Shape.CIRCLE && this.type != Shape.SQUARE) {
      return this.width;
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }

  @Override
  public double getHeight() {
    if (this.type != Shape.CIRCLE && this.type != Shape.SQUARE) {
      return this.height;
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }

  @Override
  public void setSize(double newSize) {
    if (this.type == Shape.CIRCLE) {
      this.radius = newSize;
    } else if (this.type == Shape.SQUARE) {
      this.length = newSize;
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }

  @Override
  public void setWidth(double newWidth) {
    if (this.type != Shape.CIRCLE && this.type != Shape.SQUARE) {
      this.width = newWidth;
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }

  @Override
  public void setHeight(double newHeight) {
    if (this.type != Shape.CIRCLE && this.type != Shape.SQUARE) {
      this.height = newHeight;
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }


}
