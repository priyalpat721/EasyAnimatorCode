import static org.junit.Assert.assertEquals;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.shape.Shape;
import cs5004.animator.tools.RGB;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;
import cs5004.animator.view.SVGView;
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

  private Builder populateModel() {
    Builder builder = new Builder();

    // First set canvas
    builder.setBounds(200, 70, 360, 360);

    // Second declare shapes with no attributes
    builder.declareShape("R", "rectangle");
    builder.declareShape("C", "ellipse");

    // Motion to the shapes
    // The first motion of the shape sets attributes to the shape (equal value on both columns)
    builder.addMotion("R",1,200,200,50,100,255, 0, 0,
        10, 200,200,50,100,255,0,0);

    builder.addMotion("R",10,200,200,50,100,255, 0, 0,
        50, 300,300, 50,100,255,0,0);

    builder.addMotion("R",50,300,300,50,100,255, 0, 0,
        51, 300,300,50,100,255,0,0);

    builder.addMotion("R",51,300,300,50,100,255, 0, 0,
        70,300,300,25,100,255,0, 0);

    builder.addMotion("R",70,300,300,25,100,255, 0, 0,
        100,200,200,25,100,255,0,0);


    // "C" is next: first line equal values to set attributes
    builder.addMotion("C",6,440,70,120,60,0, 0,255,
        20,440,70,120,60,0,0,255);

    builder.addMotion("C",20,440,70,120,60,0, 0, 255,
        50,440,250,120, 60,0,0,255);

    builder.addMotion("C",50,440,250,120,60, 0, 0,255,
        70, 440,370,120,60,0,170,85);

    builder.addMotion("C",70,440,370,120,60,0, 170,85,
        80,440,370,120,60,0,255, 0);

    builder.addMotion("C",80,440,370,120,60,0, 255,0,
        100,440,370,120,60,0,255,0);

    return builder;
  }

  @Test
  public void testSVGView() {
    Builder builder = populateModel();
    IAnimatorModel model = builder.build();

    SVGView svg = new SVGView();
    svg.create(model, 1);

    //System.out.println(svg.generate());
    assertEquals("eso", svg.generate());

  }

  //create an IAnimatorViewTest and test svg and text
  @Test
  public void testTextView() {
    Builder builder = populateModel();
    IAnimatorModel model = builder.build();

    TextView text = new TextView();
    text.create(model, 5);

    System.out.println(text.generate());
  }

}
