package cs5004.animator;

public interface IActions {

  int getStartTime();

  int getEndTime();

  IShape getShapeAtTick(int tick, IShape accumulatorShape);

  String toString();
}
