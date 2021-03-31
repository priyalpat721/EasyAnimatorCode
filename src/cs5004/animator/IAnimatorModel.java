package cs5004.animator;

import java.util.List;

public interface IAnimatorModel {

  void createShapes(String name, Shape shape, RGB color,
                    double width, double height, double x, double y, int startTime, int endTime);

  void addActionsToShape(String name, Action action);

  List<IShape> getShapesAtTicks(int tick);


}
