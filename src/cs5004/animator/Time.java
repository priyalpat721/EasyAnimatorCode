package cs5004.animator;

public class Time {
  private int startTime;
  private int endTime;

  public Time(int startTime, int endTime) {
    if (startTime > endTime) {
      throw new IllegalArgumentException("Start time cannot be greater than end time.");
    }
    if (startTime < 0) {
      throw new IllegalArgumentException("Start time cannot be negative.");
    }
    else {
      this.startTime = startTime;
    }
    if (endTime < 0) {
      throw new IllegalArgumentException("End time cannot be negative.");
    }
    else {
      this.endTime = endTime;
    }
  }

  public int getStartTime() {
    return this.startTime;
  }

  public int getEndTime() {
    return this.endTime;
  }

}
