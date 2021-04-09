package cs5004.animator.view;

import java.io.FileNotFoundException;
import java.io.FileReader;


import javax.swing.*;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;

import static cs5004.animator.utils.AnimationReader.parseFile;

public class VisualView {

  public void buildVisualView(IAnimatorModel model) throws InterruptedException {
    Canvas canvas = new Canvas(model.getBox()[2], model.getBox()[3]);

      //System.out.println(model.getShapesAtTicks(i).get(0));
//      canvas.setShapes(model.getShapesAtTicks(10));
//      try{
//        Thread.sleep(1000);
//        canvas.repaint();
//        canvas.setShapes(model.getShapesAtTicks(51));
//      }
//      catch (InterruptedException e) {
//
//      }
//
//

//    for (int i = 1; i < 10; i++) {
//      try {
//        Thread.sleep(1000);
//        //System.out.println(model.getShapesAtTicks(i).get(0));
//        canvas.setShapes(model.getShapesAtTicks(i));
//      }
//      catch (InterruptedException e) {
//      }
//    }

    for (int j = 1; j < 2; j++) {
      try {
        Thread.sleep(1000);
        System.out.println(model.getShapesAtTicks(j).get(0));
        canvas.setShapes(model.getShapesAtTicks(j));
      }
      catch (InterruptedException e) {
      }
    }
  }

  public static void main(String args[]) throws FileNotFoundException {
    AnimationBuilder<IAnimatorModel> builder = new Builder();
    var fileName = "src/cs5004/animator/demo.txt";
    Readable in = new FileReader(fileName);
    IAnimatorModel animation = parseFile(in, builder);


    java.awt.EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        VisualView visual = new VisualView();
        try {
          visual.buildVisualView(animation);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
  }
}
