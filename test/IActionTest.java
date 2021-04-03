import org.junit.Before;
import org.junit.Test;

import cs5004.action.ChangeColor;
import cs5004.action.IAction;
import cs5004.action.Move;
import cs5004.action.Scale;
import cs5004.shape.Circle;
import cs5004.shape.IShape;
import cs5004.shape.Oval;
import cs5004.shape.Rectangle;
import cs5004.utilities.RGB;

import static org.junit.Assert.assertEquals;

public class IActionTest {
  IShape rectangle1;
  IAction move;
  IShape circle1;
  IAction color;
  IShape oval1;
  IAction scale;

  @Before
  public void setUp() {
    // for the move class
    rectangle1 = new Rectangle("R", new RGB(1.0, 1.0, 1.0),
            50, 100, 200, 200, 1, 100);
    move = new Move("Rectangle1", rectangle1,
            300.8, 300.156, 1, 5);

    // for the change color class
    circle1 = new Circle("Circle1", new RGB(5, 5, 100),
            12, 12, 100, 50, 1, 100);
    color = new ChangeColor("Circle1", circle1, new RGB(200.25, 200.46, 200.97),
            15, 20);

    // for the scale class
    oval1 = new Oval("Oval1", new RGB(1.0, 1.0, 1.0),
            50, 100, 200, 200, 1, 100);
    scale = new Scale("Oval1", oval1,
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

  // tests the get current shape function for move
  @Test
  public void moveGetCurrentShapeTest() {
    assertEquals("""
            Name: R
            Type: rectangle
            Min corner: (300.8,300.2), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)
            Appears at t=1
            Disappears at t=100""", move.getCurrentShape().toString());
  }

  // tests the get time function for move
  @Test
  public void moveGetTimeTest() {
    assertEquals(1, move.getTime().getStartTime());
    assertEquals(5, move.getTime().getEndTime());
  }

  // tests the get type for move
  @Test
  public void moveGetTypeTest() {
    assertEquals("MOVE", move.getType().toString());
  }

  // tests the toString for move
  @Test
  public void moveToStringTest() {
    assertEquals("Rectangle1 moves from (200.0, 200.0) to (300.8, 300.156) "
            + "from time t=1 to t=5", move.toString());
  }


  // tests the getShapesAtTick for the changeColor class over an interval
  @Test
  public void changeColorGetShapeAtTickTest() {
    StringBuilder intervals = new StringBuilder();
    IShape currentShape = circle1;
    for (int i = 1; i <= 5; i++) {
      currentShape = color.getShapeAtTick(i, currentShape);
      intervals.append(currentShape.toString());
      if (i != 5) {
        intervals.append("\n");
      }
    }
    assertEquals("""
                    Name: Circle1
                    Type: circle
                    Center: (100.0,50.0), Radius: 12.0, Color: (200.3,200.5,201.0)
                    Appears at t=1
                    Disappears at t=100"""
           , color.getCurrentShape().toString());
  }

  @Test
  public void changeColorGetTimeTest() {
    assertEquals(15, color.getTime().getStartTime());
    assertEquals(20, color.getTime().getEndTime());
  }

  @Test
  public void changeColorGetTypeTest() {
    assertEquals("CHANGECOLOR", color.getType().toString());
  }

  @Test
  public void changeColorToStringTest() {
    assertEquals("Circle1 changes color from (5.0,5.0,100.0) "
            + "to (200.3,200.5,201.0) from time t= 15 to t=20", color.toString());
  }
}