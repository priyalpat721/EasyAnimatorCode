package cs5004.utilities;

/**
 * This class represents a time of occurrence. A time has a start time and an end time.
 */
public class Time {
  private int startTime;
  private int endTime;

  /**
   * Constructs a Time object.
   * @param startTime the start time.
   * @param endTime   the end time.
   * @throws IllegalArgumentException if the start time is negative.
   *                                  if the end time is negative.
   *                                  if the start time is greater than the end time.
   */
  public Time(int startTime, int endTime) {
    if (startTime < 0) {
      throw new IllegalArgumentException("Start time cannot be negative");
    } else if (endTime < 0) {
      throw new IllegalArgumentException("End time cannot be negative");
    } else if (startTime >= endTime) {
      throw new IllegalArgumentException("Start time cannot be greater than or equal to end time");
    }

    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * Gets the start time.
   * @return the start time.
   */
  public int getStartTime() {
    return this.startTime;
  }

  /**
   * Gets the end time.
   * @return the end time.
   */
  public int getEndTime() {
    return this.endTime;
  }

}
