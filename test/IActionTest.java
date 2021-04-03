import org.junit.Before;
import org.junit.Test;

import cs5004.action.ChangeColor;
import cs5004.action.IAction;
import cs5004.action.Move;
import cs5004.shape.Circle;
import cs5004.shape.IShape;
import cs5004.shape.Rectangle;
import cs5004.shape.Shape;
import cs5004.utilities.RGB;

import static org.junit.Assert.assertEquals;

public class IActionTest {
  IShape rectangle1;
  IAction move;
  IShape circle1;
  IAction color;
  @Before
  public void setUp() {
    rectangle1 = new Rectangle("R", new RGB(1.0, 1.0, 1.0),
            50, 100, 200, 200, 1, 100);
    move = new Move("Rectangle1", rectangle1,
            300.8, 300.156, 1, 5);

    circle1 = new Circle("Circle1", new RGB(5, 5, 100),
            12, 12, 100, 50, 1, 100);
    color = new ChangeColor("Circle1", new(200, 200, 200), )
  }

  // tests the getShapesAtTick for the Move class over an interval
  @Test
  public void moveGetShapeAtTickTest() {
    StringBuilder intervals = new StringBuilder();
    IShape currentShape = rectangle1;
    for (int i = 1; i <= 5; i++) {
      currentShape = move.getShapeAtTick(i, currentShape);
      intervals.append(currentShape.toString());
      if (i != 5) {
        intervals.append("\n");
      }
    }
    assertEquals("""
            Name: R
            Type: rectangle
            Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)
            Appears at t=1
            Disappears at t=100
            Name: R
            Type: rectangle
            Min corner: (225.2,225.0), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)
            Appears at t=1
            Disappears at t=100
            Name: R
            Type: rectangle
            Min corner: (250.4,250.1), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)
            Appears at t=1
            Disappears at t=100
            Name: R
            Type: rectangle
            Min corner: (275.6,275.1), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)
            Appears at t=1
            Disappears at t=100
            Name: R
            Type: rectangle
            Min corner: (300.8,300.2), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)
            Appears at t=1
            Disappears at t=100""", intervals.toString());
  }

  @Test
  public void moveGetCurrentShapeTest() {
    assertEquals("""
            Name: R
            Type: rectangle
            Min corner: (300.8,300.2), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)
            Appears at t=1
            Disappears at t=100""", move.getCurrentShape().toString());
  }

  @Test
  public void moveGetTimeTest() {
    assertEquals(1, move.getTime().getStartTime());
    assertEquals(5, move.getTime().getEndTime());
  }

  @Test
  public void moveGetTypeTest() {
    assertEquals("MOVE", move.getType().toString());
  }

  @Test
  public void moveToStringTest() {
    assertEquals("Rectangle1 moves from (200.0, 200.0) to (300.8, 300.156) " +
            "from time t=1 to t=5", move.toString());
  }
}