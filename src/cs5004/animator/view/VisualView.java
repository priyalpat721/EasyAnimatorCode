package cs5004.animator.view;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.*;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;

import static cs5004.animator.utils.AnimationReader.parseFile;

public class VisualView implements IAnimatorView {

  @Override
  public void create(IAnimatorModel model, int speed) {
    Canvas canvas = new Canvas(model.getBox()[2], model.getBox()[3], model.getShapesAtTicks(0));
    canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    int end = model.getTotalTime()[1];
    double count = 0;
    // controls the frame
    while (count < end) {
      canvas.currentView(model.getShapesAtTicks(count));
      count += 1;
      try {
        Thread.sleep(speed);

      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  @Override
  public String generate() {
    return "";
  }

  public static void main(String args[]) throws FileNotFoundException {
    AnimationBuilder<IAnimatorModel> builder = new Builder();
    var fileName = "src/cs5004/animator/demo.txt";
    Readable in = new FileReader(fileName);
    IAnimatorModel animation = parseFile(in, builder);
    VisualView view = new VisualView();
    view.create(animation, 10);
  }

}
