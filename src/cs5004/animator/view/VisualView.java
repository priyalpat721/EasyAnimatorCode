package cs5004.animator.view;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;

import static cs5004.animator.utils.AnimationReader.parseFile;

public class VisualView {

  public void buildVisualView(IAnimatorModel model) throws InterruptedException {
    // creates the JFrame = canvas
    Canvas canvas = new Canvas(model.getBox()[0], model.getBox()[1],
            model.getBox()[2], model.getBox()[3], model);

    try {
      // controls the frame
      Thread.sleep(5);
      for (int i = 1; i < 101; i++) {
        canvas.currentView(model.getShapesAtTicks(i));
        //System.out.println(model.getShapesAtTicks(i));
      }
    } catch (InterruptedException e) {

    }
  }

  public static void main(String args[]) throws FileNotFoundException {
    AnimationBuilder<IAnimatorModel> builder = new Builder();
    var fileName = "src/cs5004/animator/demo.txt";
    Readable in = new FileReader(fileName);
    IAnimatorModel animation = parseFile(in, builder);


    java.awt.EventQueue.invokeLater(() -> {
      VisualView visual = new VisualView();
      try {
        visual.buildVisualView(animation);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
  }
}
