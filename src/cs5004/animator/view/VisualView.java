package cs5004.animator.view;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.*;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;

import static cs5004.animator.utils.AnimationReader.parseFile;

public class VisualView implements IAnimatorView{

  public static void main(String args[]) throws FileNotFoundException {
    AnimationBuilder<IAnimatorModel> builder = new Builder();
    var fileName = "src/cs5004/animator/demo.txt";
    Readable in = new FileReader(fileName);
    IAnimatorModel animation = parseFile(in, builder);
    Canvas canvas = new Canvas(animation.getBox()[2], animation.getBox()[3], animation.getShapesAtTicks(0));
    canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    double count = 0;
    // controls the frame
    while (count < 1000000) {
      canvas.currentView(animation.getShapesAtTicks(count));
      count++;
      try {
        Thread.sleep(100);

      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

  }

  @Override
  public void create(IAnimatorModel model, int speed) {

  }
}