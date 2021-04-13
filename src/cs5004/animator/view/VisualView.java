package cs5004.animator.view;

import javax.swing.*;

import cs5004.animator.model.IAnimatorModel;

import static cs5004.animator.tools.Helpers.showMessage;

public class VisualView implements IAnimatorView {

  @Override
  public void create(IAnimatorModel model, int speed) {
    if (model.getLogOfShapes().size() == 0) {
      showMessage("Input file is empty", 2);
    } else {
      Canvas canvas = new Canvas(model.getBox()[0], model.getBox()[1]
              , model.getBox()[2], model.getBox()[3], model.getShapesAtTicks(0));
      canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      int end = model.getTotalTime()[1];
      double count = 0;
      // controls the frame
      while (count < end) {
        canvas.currentView(model.getShapesAtTicks(count));
        count = count + speed / 100.0;
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    }
  }
}
