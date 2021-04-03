import static org.junit.Assert.assertEquals;

import cs5004.animator.IAnimatorModel;
import cs5004.shape.Circle;
import cs5004.shape.IShape;
import cs5004.shape.Oval;
import cs5004.shape.Rectangle;
import cs5004.shape.Rhombus;
import cs5004.shape.Square;
import cs5004.shape.Triangle;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cs5004.animator.AnimatorModelImpl;
import cs5004.utilities.RGB;
import cs5004.shape.Shape;

public class AnimatorModelImplTest {
  AnimatorModelImpl model1;


  @Before
  public void setUp() {
    model1 = new AnimatorModelImpl();
    model1.createShape("R", Shape.RECTANGLE, new RGB(1, 1, 1),
        50, 100, 200, 200, 1, 100);
    model1.createShape("O", Shape.OVAL, new RGB(21, 21, 21), 60,
        30, 500, 100, 6, 100);
    model1.createShape("T", Shape.TRIANGLE, new RGB(34,0, 1),
        45.12,30.54, 78, 234, 23, 75);
    model1.createShape("S", Shape.SQUARE, new RGB(1,1,1),
        15,15,125, 34, 35,36);
    model1.createShape("RH", Shape.RHOMBUS, new RGB(2,3,4),
        20, 20, 45, 15, 98, 99);
  }

  @Test
  public void testCreateShape() {
    model1.createShape("C", Shape.CIRCLE, new RGB(1, 1, 1),
        12, 12, 100, 50, 1, 100);
    model1.toString();
  }

  @Test
  public void testMove() {
    model1.move("R", 350, 375, 10, 50);
    assertEquals("""
        Shapes:
        Name: R
        Type: rectangle
        Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)
        Appears at t=1
        Disappears at t=100

        Name: S
        Type: square
        Min corner: (125.0,34.0), Length: 15.0, Color: (1.0,1.0,1.0)
        Appears at t=35
        Disappears at t=36

        Name: T
        Type: triangle
        Min corner: (78.0,234.0), Width: 45.1, Height: 30.5, Color: (34.0,0.0,1.0)
        Appears at t=23
        Disappears at t=75

        Name: RH
        Type: rhombus
        Min corner: (45.0,15.0), Width: 20.0, Height: 20.0, Color: (2.0,3.0,4.0)
        Appears at t=98
        Disappears at t=99

        Name: O
        Type: oval
        Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (21.0,21.0,21.0)
        Appears at t=6
        Disappears at t=100

        Shape R moves from (200.0, 200.0) to (350.0, 375.0) from time t=10 to t=50\n""", model1.toString());
  }

  @Test
  public void testChangeColor() {
    model1.changeColor("S",new RGB(254.16, 35.44, 122),
        33,44);
    assertEquals("""
        Shapes:
        Name: R
        Type: rectangle
        Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)
        Appears at t=1
        Disappears at t=100

        Name: S
        Type: square
        Min corner: (125.0,34.0), Length: 15.0, Color: (1.0,1.0,1.0)
        Appears at t=35
        Disappears at t=36

        Name: T
        Type: triangle
        Min corner: (78.0,234.0), Width: 45.1, Height: 30.5, Color: (34.0,0.0,1.0)
        Appears at t=23
        Disappears at t=75

        Name: RH
        Type: rhombus
        Min corner: (45.0,15.0), Width: 20.0, Height: 20.0, Color: (2.0,3.0,4.0)
        Appears at t=98
        Disappears at t=99

        Name: O
        Type: oval
        Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (21.0,21.0,21.0)
        Appears at t=6
        Disappears at t=100

        Shape S changes color from (1.0,1.0,1.0) to (254.2,35.4,122.0) from time t= 33 to t=44
        """, model1.toString());
  }

  @Test
  public void testScale() {
  }

  @Test
  public void testAddActions() {
  }

  @Test
  public void testGetShapesAtTicks() {
  }

  @Test
  public void testTestToString() {
  }


  @Test
  public void testToStringShapesAndActions() {
//    AnimatorModelImpl model2 = new AnimatorModelImpl();
//    model2.createShape("R", Shape.RECTANGLE, new RGB(1, 0, 0),
//            50, 100, 200, 200, 1, 100);
//    model2.createShape("C", Shape.OVAL, new RGB(0, 0, 1), 60,
//            30, 500, 100, 6, 100);
//    model2.move("R", 300, 300, 10, 50);
//    System.out.printf("Testing getShapesAtTicks\n");
//    System.out.println(model2.getShapesAtTicks(11));
//    model2.move("C", 500, 400, 20, 70);
//    model2.changeColor("C", new RGB(0, 1, 0), 50, 80);
//    model2.scale("R", 25, 100, 51, 70);
//    model2.move("R", 200, 200, 70, 100);
//    model2.move("C", 0, 0, 70, 100);
//
//    System.out.println("Testing toString");
//    System.out.println(model2.toString());

  }

}
