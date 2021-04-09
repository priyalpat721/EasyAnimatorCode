package cs5004.animator.view;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.*;

import cs5004.animator.model.IAnimatorModel;
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
    while (count < 1) {
      canvas.currentView(model.getShapesAtTicks(count));
      System.out.println(model.getShapesAtTicks(count));
      count++;
      System.out.println(count);
      try {
        Thread.sleep(10000);
        //System.out.println(model.getShapesAtTicks(i));


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


    java.awt.EventQueue.invokeLater(() -> {
      VisualView visual = new VisualView();
      visual.buildVisualView(animation);
    });
  }
}