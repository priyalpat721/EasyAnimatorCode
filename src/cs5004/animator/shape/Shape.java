package cs5004.animator.shape;

/**
 * This enum represents all the types that an IShape object can have.
 */
public enum Shape {
  CIRCLE("circle"),
  //ELLIPSE("ellipse"),
  ELLIPSE("ellipse"),
  RECTANGLE("rectangle"),
  RHOMBUS("rhombus"),
  SQUARE("square"),
  TRIANGLE("triangle");

  private final String desc;

  Shape(String desc) {
    this.desc = desc;
  }

  public String toString() {
    return desc;
  }

}
