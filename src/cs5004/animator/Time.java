package cs5004.animator;

public class Time {
  private int startTime;
  private int endTime;

  public Time(int startTime, int endTime) {
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public int getStartTime() {
    return this.startTime;
  }

  public int getEndTime() {
    return this.endTime;
  }

}
