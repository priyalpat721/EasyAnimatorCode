package cs5004.animator.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.*;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;

import static cs5004.animator.utils.AnimationReader.parseFile;

// a view to test animation stuff
public class TestView2 {
  private IAnimatorModel model;
  private int speed;
  private int end;
  private Canvas canvas;

  public TestView2(IAnimatorModel model, int speed) {
    this.model = model;
    this.canvas = new Canvas(model.getBox()[0], model.getBox()[1],
            model.getBox()[2], model.getBox()[3], model.getShapesAtTicks(0));
    this.canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.canvas.setVisible(false);
    this.speed = speed;
    this.end = model.getTotalTime()[1];
  }

  public void play() {
    double count = 0;

    while (count < end) {
      this.canvas.currentView(model.getShapesAtTicks(count));
      count = count + speed / 100.0;
      System.out.println("here");
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    System.out.println("out");
  }

  public void makeVisible() {
    this.canvas.setVisible(true);
  }

  // start the mouse listener in the canvas
  public void setCommandButtonListener(MouseListener mouseEvent) {
    this.canvas.setCommandButtonListener(mouseEvent);
  }

  public static void main(String[] args) throws FileNotFoundException {
    AnimationBuilder<IAnimatorModel> builder = new Builder();

    Readable in = new FileReader("test/smalldemo.txt");

    IAnimatorModel model = parseFile(in, builder);

    TestView2 view2 = new TestView2(model, 1);

    view2.makeVisible();

    view2.play();
  }

}
