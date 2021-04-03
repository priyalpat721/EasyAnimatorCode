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
  }

  @Test
  public void testCreateShape() {
    model1.createShape("R", Shape.RECTANGLE, new RGB(1, 1, 1),
        50, 100, 200, 200, 1, 100);
    model1.createShape("O", Shape.OVAL, new RGB(21, 21, 21), 60,
        30, 500, 100, 6, 100);
    model1.createShape("C", Shape.CIRCLE, new RGB(0, 255, 125),
        32,32, 0,30, 1,25);
    model1.createShape("T", Shape.TRIANGLE, new RGB(34,0, 1), 45.12,30.54,
        78, 234, 23, 75);
  }

  @Test
  public void testMove() {
  }

  @Test
  public void testChangeColor() {
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
    AnimatorModelImpl model2 = new AnimatorModelImpl();
    model2.createShape("R", Shape.RECTANGLE, new RGB(1, 0, 0),
            50, 100, 200, 200, 1, 100);
    model2.createShape("C", Shape.OVAL, new RGB(0, 0, 1), 60,
            30, 500, 100, 6, 100);
    model2.move("R", 300, 300, 10, 50);
    System.out.printf("Testing getShapesAtTicks\n");
    System.out.println(model2.getShapesAtTicks(11));
    model2.move("C", 500, 400, 20, 70);
    model2.changeColor("C", new RGB(0, 1, 0), 50, 80);
    model2.scale("R", 25, 100, 51, 70);
    model2.move("R", 200, 200, 70, 100);
    model2.move("C", 0, 0, 70, 100);

    System.out.println("Testing toString");
    System.out.println(model2.toString());

  }

}
