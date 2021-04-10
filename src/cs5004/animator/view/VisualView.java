package cs5004.animator.view;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.*;

import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.shape.Shape;
import cs5004.animator.tools.RGB;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;

import static cs5004.animator.utils.AnimationReader.parseFile;

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
    AnimationBuilder<IAnimatorModel> builder = new Builder();
    var fileName = "src/cs5004/animator/demo.txt";
    Readable in = new FileReader(fileName);
    IAnimatorModel animation = parseFile(in, builder);
    Canvas canvas = new Canvas(animation.getBox()[0], animation.getBox()[1], animation.getBox()[2], animation.getBox()[3], animation.getShapesAtTicks(0));
    canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    int count = 0;
    // controls the frame
    while (count < 100) {
      canvas.currentView(animation.getShapesAtTicks(count));
      count++;
      try {
        Thread.sleep(100);

      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

  }

}