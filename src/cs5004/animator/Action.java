package cs5004.animator;

public enum Action { MOVE("moves"),
                    CHANGE("changes"),
                    SCALE("scales");

  private final String desc;

  Action(String desc) {
    this.desc = desc;
  }

  public String toString() {
    return desc;
  }
}
