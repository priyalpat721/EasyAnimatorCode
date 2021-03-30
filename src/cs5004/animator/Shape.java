package cs5004.animator;

public enum Shape { CIRCLE("Circle"),
                    OVAL("Oval"),
                    RECTANGLE("Rectangle"),
                    RHOMBUS("Rhombus"),
                    SQUARE("Square"),
                    TRIANGLE("Triangle");

  private final String desc;

  Shape(String desc) {
    this.desc = desc;
  }

  public String toString() {
    return desc;
  }

}
