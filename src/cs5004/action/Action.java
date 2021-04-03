package cs5004.action;

/**
 * This enum represents all the types that an IAction object can have.
 */
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
