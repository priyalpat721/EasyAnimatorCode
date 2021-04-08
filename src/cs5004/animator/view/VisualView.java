package cs5004.animator.view;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.*;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;

import static cs5004.animator.utils.AnimationReader.parseFile;

public class VisualView {
  int ticksPerSecond;
  // need start and end up
  Timer timer = new Timer(ticksPerSecond, null);
  ShapesPanel newPanel = new ShapesPanel();

  public void buildVisualView(IAnimatorModel model) {
    Canvas canvas = new Canvas(model.getBox()[2], model.getBox()[3]);
    for (int i = 10; i < 51; i++) {
      System.out.println(model.getShapesAtTicks(i).get(0).getPosition().getX());
    }
    canvas.add(newPanel);
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
