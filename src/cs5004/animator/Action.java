package cs5004.animator;

public enum Action {
    CHANGE("changes"), //color
    CREATE("create"),
    MOVE("moves"),
    SCALE("scales"); //size

    private final String desc;

    Action (String desc) {
      this.desc = desc;
    }

    public String toString() {
      return this.desc;
    }

}
