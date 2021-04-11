import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.shape.Shape;
import cs5004.animator.tools.RGB;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;
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

  private void populateModel() {
//    AnimationBuilder<AnimatorModelImpl> animatorModel = new AnimationBuilder<>();
    Builder animatorModel = new Builder();
    animatorModel.setBounds(200, 70, 360, 360);
    animatorModel.createShape("R", "rectangle");
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

    animatorModel.createShape("C", "ellipse");
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
  public void createTest() {
    //void create(IAnimatorModel model, int speed);
  }

  @Test
  public void generateTest() {
    //String generate();
  }




}
