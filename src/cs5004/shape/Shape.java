package cs5004.shape;

public enum Shape { CIRCLE("circle"),
                    OVAL("oval"),
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
