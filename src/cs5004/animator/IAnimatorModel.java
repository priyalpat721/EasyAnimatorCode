package cs5004.animator;

import java.util.List;

public interface IAnimatorModel {

  void createShapes(IShape shape, String name, RGB color, double size, double x, double y);

  void addActionsToShape(IActions action);

  List<IShape> getShapesAtTicks(int tick);



}
