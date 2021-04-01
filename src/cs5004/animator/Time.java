package cs5004.animator;

/**
 * This class represents a time of occurrence. A time has a start time and an end time.
 */
public class Time {
  private int startTime;
  private int endTime;

  /**
   * Constructs a Time object.
   *
   * @param startTime the start time.
   * @param endTime   the end time.
   * @throws IllegalArgumentException if the start time is less than zero. if the start time is
   *                                  greater than the end time. if the end time is less than zero.
   */
  public Time(int startTime, int endTime) {
    if (startTime < 0) {
      throw new IllegalArgumentException("Start time cannot be less than to zero");
    } else if (endTime < 0) {
      throw new IllegalArgumentException("End time cannot be less than zero.");
    } else if (startTime > endTime) {
      throw new IllegalArgumentException("Start time cannot be greater than end time.");
    }

    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * Gets the start time.
   *
   * @return the start time.
   */
  public int getStartTime() {
    return this.startTime;
  }

  /**
   * Gets the end time.
   *
   * @return the end time.
   */
  public int getEndTime() {
    return this.endTime;
  }

}
