package cs5004.animator;

import java.awt.*;
import java.util.List;

public interface IAnimatorModel {

  void createShapes(String name, Shape shape, RGB color,
                    double width, double height, double x, double y, int startTime, int endTime);

  void move(String name, double newX, double newY, int startTime, int endTime);

  void changeColor(String name, RGB newColor, int startTime, int endTime);

  void scale(String name, double newWidth, double newHeight, int startTime, int endTime);

  List<IShape> getShapesAtTicks(int tick);

}
