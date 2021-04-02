import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cs5004.animator.AnimatorModelImpl;
import cs5004.shape.RGB;
import cs5004.shape.Shape;

public class AnimatorModelImplTest {
  @Before
  public void setUp() {

  }

  @Test
  public void testCreateShape() {
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
    AnimatorModelImpl Model1 = new AnimatorModelImpl();
    Model1.createShape("R", Shape.RECTANGLE, new RGB(1, 0, 0),
            50, 100, 200, 200, 1, 100);
    Model1.createShape("C", Shape.OVAL, new RGB(0, 0, 1), 60,
            30, 500, 100, 6, 100);
    Model1.move("R", 300, 300, 10, 50);
    System.out.printf("Testing getShapesAtTicks");
    System.out.println(Model1.getShapesAtTicks(11));
    Model1.move("C", 500, 400, 20, 70);
    Model1.changeColor("C", new RGB(0, 1, 0), 50, 80);
    Model1.scale("R", 25, 100, 51, 70);
    Model1.move("R", 200, 200, 70, 100);
    Model1.move("C", 0, 0, 70, 100);

    System.out.println("Testing toString");
    System.out.println(Model1.toString());

  }

}
