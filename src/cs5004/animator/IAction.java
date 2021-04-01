package cs5004.animator;

public interface IAction {

  int getStartTime();

  int getEndTime();

  IShape getShapeAtTick(int tick, IShape accumulatorShape);

}
