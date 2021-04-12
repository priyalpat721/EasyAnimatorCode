import static org.junit.Assert.assertEquals;

import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.shape.Shape;
import cs5004.animator.tools.RGB;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;
import cs5004.animator.view.TextView;
import org.junit.Before;
import org.junit.Test;

public class IAnimatorViewTest {
  /// IAnimatorViewTest
  ///create a test from the interface of the view
  //and create a private method on the test that populates a model following the cycle that the builder follows
  //model.createShape(String name, String type)
  //model.setBounds(..)
  //model.setAttributes(..)
  //model.addMotion(..)
  //setBounds goes first, it is the canvas
  /// - bounds, create, attributes, motion
  // we have to test the cycle of the builder


//  private Builder populateModel() {
////    AnimationBuilder<AnimatorModelImpl> animatorModel = new AnimationBuilder<>();
//    Builder animatorModel = new Builder();
//    animatorModel.build();
//    animatorModel.setBounds(200, 70, 360, 360);
//    animatorModel.declareShape("R", "rectangle");
//    animatorModel.addMotion("R",1,200,200,50,100,255, 0, 0,
//        10, 200,200,50,100,255,0,0);
//
//    animatorModel.addMotion("R",10,200,200,50,100,255, 0, 0,
//        50, 300,300, 50,100,255,0,0);
//
//    animatorModel.addMotion("R",50,300,300,50,100,255, 0, 0,
//        51, 300,300,50,100,255,0,0);
//
//    animatorModel.addMotion("R",51,300,300,50,100,255, 0, 0,
//        70,300,300,25,100,255,0, 0);
//
//    animatorModel.addMotion("R",70,300,300,25,100,255, 0, 0,
//        100,200,200,25,100,255,0,0);
//
//    animatorModel.declareShape("C", "ellipse");
//
//    animatorModel.addMotion("C",6,440,70,120,60,0, 0,255,
//        20,440,70,120,60,0,0,255);
//
//    animatorModel.addMotion("C",20,440,70,120,60,0, 0, 255,
//        50,440,250,120, 60,0,0,255);
//
//    animatorModel.addMotion("C",50,440,250,120,60, 0, 0,255,
//        70, 440,370,120,60,0,170,85);
//
//    animatorModel.addMotion("C",70,440,370,120,60,0, 170,85,
//        80,440,370,120,60,0,255, 0);
//
//    animatorModel.addMotion("C",80,440,370,120,60,0, 255,0,
//        100,440,370,120,60,0,255,0);
//
//    return animatorModel;
//  }

  private void populateModel() {
//    AnimationBuilder<AnimatorModelImpl> animatorModel = new AnimationBuilder<>();
    Builder animatorModel = new Builder();
    animatorModel.build();
    animatorModel.setBounds(200, 70, 360, 360);
    animatorModel.declareShape("R", "rectangle");
    animatorModel.addMotion("R",1,200,200,50,100,255, 0, 0,
        10, 200,200,50,100,255,0,0);

    animatorModel.addMotion("R",10,200,200,50,100,255, 0, 0,
        50, 300,300, 50,100,255,0,0);

    animatorModel.addMotion("R",50,300,300,50,100,255, 0, 0,
        51, 300,300,50,100,255,0,0);

    animatorModel.addMotion("R",51,300,300,50,100,255, 0, 0,
        70,300,300,25,100,255,0, 0);

    animatorModel.addMotion("R",70,300,300,25,100,255, 0, 0,
        100,200,200,25,100,255,0,0);

    animatorModel.declareShape("C", "ellipse");

    animatorModel.addMotion("C",6,440,70,120,60,0, 0,255,
        20,440,70,120,60,0,0,255);

    animatorModel.addMotion("C",20,440,70,120,60,0, 0, 255,
        50,440,250,120, 60,0,0,255);

    animatorModel.addMotion("C",50,440,250,120,60, 0, 0,255,
        70, 440,370,120,60,0,170,85);

    animatorModel.addMotion("C",70,440,370,120,60,0, 170,85,
        80,440,370,120,60,0,255, 0);

    animatorModel.addMotion("C",80,440,370,120,60,0, 255,0,
        100,440,370,120,60,0,255,0);
  }
  //create an IAnimatorViewTest and test svg and text
  @Test
  public void GenerateTextViewTest() {
    AnimatorModelImpl model1 = new AnimatorModelImpl();
    model1.createShape("R", Shape.RECTANGLE, new RGB(1, 1, 1),
        50, 100, 200, 200, 1, 100);
    model1.createShape("O", Shape.ELLIPSE, new RGB(21, 21, 21), 60,
        30, 500, 100, 6, 100);
    model1.createShape("T", Shape.TRIANGLE, new RGB(34, 0, 1),
        45.12, 30.54, 78, 234, 23, 75);
    model1.createShape("S", Shape.SQUARE, new RGB(1, 1, 1),
        15, 15, 125, 34, 30, 60);
    model1.createShape("RH", Shape.RHOMBUS, new RGB(2, 3, 4),
        20, 20, 45, 15, 98, 99);


    //void create(IAnimatorModel model, int speed);
    TextView textView = new TextView();
    textView.create(model1, 2);
    assertEquals("Shapes:\n"
                 + "Name: R\n"
                 + "Type: rectangle\n"
                 + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=0\n"
                 + "Disappears at t=0\n"
                 + "\n"
                 + "Name: O\n"
                 + "Type: ellipse\n"
                 + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (21.0,21.0,21.0)\n"
                 + "Appears at t=0\n"
                 + "Disappears at t=0\n"
                 + "\n"
                 + "Name: T\n"
                 + "Type: triangle\n"
                 + "Min corner: (78.0,234.0), Width: 45.1, Height: 30.5, Color: (34.0,0.0,1.0)\n"
                 + "Appears at t=0\n"
                 + "Disappears at t=0\n"
                 + "\n"
                 + "Name: S\n"
                 + "Type: square\n"
                 + "Min corner: (125.0,34.0), Length: 15.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=0\n"
                 + "Disappears at t=0\n"
                 + "\n"
                 + "Name: RH\n"
                 + "Type: rhombus\n"
                 + "Min corner: (45.0,15.0), Width: 20.0, Height: 20.0, Color: (2.0,3.0,4.0)\n"
                 + "Appears at t=0\n"
                 + "Disappears at t=0\n"
                 + "\n"
                 + "Speed of animation: 2 tick(s) per second\n\n", textView.generate());

  }

  @Test
  public void generateTest() {
    //String generate();
  }




}
