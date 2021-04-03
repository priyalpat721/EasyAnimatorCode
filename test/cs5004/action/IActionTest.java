package cs5004.action;

import org.junit.Before;
import org.junit.Test;

import cs5004.shape.IShape;
import cs5004.shape.Rectangle;
import cs5004.utilities.RGB;

import static org.junit.Assert.assertEquals;

public class IActionTest {
  IShape rectangle1;
  IAction move;

  @Before
  public void setUp() throws Exception {
    rectangle1 = new Rectangle("R",  new RGB(1.0, 1.0, 1.0),
            50, 100, 200, 200, 1, 100);
    move = new Move("Rectangle1", rectangle1,
            300.8, 300.156, 1, 5);
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
  }

  @Test
  public void moveGetTimeTest() {
  }

  @Test
  public void moveGetTypeTest() {
  }
}