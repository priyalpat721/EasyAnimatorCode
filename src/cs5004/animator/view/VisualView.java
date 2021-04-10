package cs5004.animator.view;

import java.io.FileNotFoundException;

import javax.swing.*;

import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.shape.Shape;
import cs5004.animator.tools.RGB;

public class VisualView {

  public void buildVisualView(IAnimatorModel model) {
    // creates the JFrame = canvas
    Canvas canvas = new Canvas(model.getBox()[0], model.getBox()[1],
            model.getBox()[2], model.getBox()[3], model.getShapesAtTicks(0));
    canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    int count = 0;
    // controls the frame
    while (count < 100) {
      canvas.currentView(model.getShapesAtTicks(count));
      //System.out.println(model.getShapesAtTicks(count));
      count++;
      System.out.println(count);
      try {
        Thread.sleep(10);

      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  public static void main(String args[]) throws FileNotFoundException {
//    AnimationBuilder<IAnimatorModel> builder = new Builder();
//    var fileName = "src/cs5004/animator/demo.txt";
//    Readable in = new FileReader(fileName);
//    IAnimatorModel animation = parseFile(in, builder);
//
//
//    java.awt.EventQueue.invokeLater(() -> {
//      VisualView visual = new VisualView();
//      visual.buildVisualView(animation);
//    });
//  }

    IAnimatorModel model = new AnimatorModelImpl();
    model.createShape("R", Shape.RECTANGLE, new RGB(255, 255, 255),
            200, 20, 0, 0, 0, 100);
    model.move("R", 200, 200, 0, 20);
    model.scale("R", 200, 200, 0, 20);
    model.changeColor("R", new RGB(255, 0, 0), 0,50);
    Canvas canvas = new Canvas(0, 0, 500, 500, model.getShapesAtTicks(0));
    int count = 0;
    while (count < 200) {
      canvas.currentView(model.getShapesAtTicks(count));
      count++;
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }
}