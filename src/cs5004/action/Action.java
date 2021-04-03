package cs5004.action;

public enum Action { MOVE("moves"),
                    CHANGECOLOR("changes"),
                    SCALE("scales");

  private final String desc;

  Action(String desc) {
    this.desc = desc;
  }

  public String toString() {
    return desc;
  }
}
