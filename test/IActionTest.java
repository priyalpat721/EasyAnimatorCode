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
import cs5004.shape.Square;
import cs5004.utilities.RGB;

import static org.junit.Assert.assertEquals;

public class IActionTest {
  IShape rectangle1;
  IAction move;
  IShape circle1;
  IAction color;
  IShape oval1;
  IAction scale;
  IShape square1;
  IAction move2;
  IAction move3;
  IAction color2;
  IAction color3;
  IAction scale2;
  IAction scale3;

  @Before
  public void setUp() {
    // for the move class
    rectangle1 = new Rectangle("Rectangle1", new RGB(1.0, 1.0, 1.0),
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
            300.8, 300.156, 10, 15);
  }

  @Test
  public void constructorMoveTest() {
    square1 = new Square("Square1", new RGB(10, 15, 20),
            4, 4, 0, 0, 1, 50);
    move2 = new Move("Square1", square1, 100, 0, 1, 5);
    assertEquals("Square1 moves from (0.0, 0.0) to (100.0, 0.0) "
            + "from time t=1 to t=5", move2.toString());
    move3 = new Move("Square1", square1, 100, 100, 5, 10);
    assertEquals("Square1 moves from (100.0, 0.0) to (100.0, 100.0) "
            + "from time t=5 to t=10", move3.toString());
  }

  @Test (expected=IllegalArgumentException.class)
  public void illegalNameMoveConstructorTest() {
    move2 = new Move("Square1", oval1, 100, 100, 1,10);
  }

  @Test (expected=IllegalArgumentException.class)
  public void illegalNameSpaceMoveConstructorTest() {
    move2 = new Move(" ", circle1, 100, 100, 1, 10);
  }

  @Test (expected=IllegalArgumentException.class)
  public void illegalNameEmptyMoveConstructorTest() {
    move2 = new Move("", rectangle1, 100, 100, 1, 10);
  }

  @Test
  public void constructorChangeColorTest() {
    square1 = new Square("Square1", new RGB(10, 15, 20),
            4, 4, 0, 0, 1, 50);
    color2 = new ChangeColor("Square1", square1, new RGB(12, 15, 18),
            0, 1);
    assertEquals("Square1 changes color from (10.0,15.0,20.0) "
            + "to (12.0,15.0,18.0) from time t= 0 to t=1", color2.toString());
    color3 = new ChangeColor("Square1", square1, new RGB(200, 215, 218),
            0, 1);
    assertEquals("Square1 changes color from (12.0,15.0,18.0) "
            + "to (200.0,215.0,218.0) from time t= 0 to t=1", color3.toString());
  }

  @Test (expected=IllegalArgumentException.class)
  public void illegalNameChangeColorConstructorTest() {
    color2 = new ChangeColor("Square1", oval1, new RGB(200, 215, 218),
            1, 10);
  }

  @Test (expected=IllegalArgumentException.class)
  public void illegalNameSpaceChangeColorConstructorTest() {
    color2 = new ChangeColor(" ", rectangle1, new RGB(200, 215, 218),
            1, 10);
  }

  @Test (expected=IllegalArgumentException.class)
  public void illegalNameEmptyChangeColorConstructorTest() {
    color3 = new ChangeColor("", oval1, new RGB(200, 215, 218),
            1, 10);
  }

  @Test
  public void constructorScaleTest() {
    square1 = new Square("Square1", new RGB(10, 15, 20),
            4, 4, 0, 0, 1, 50);
    scale2 = new Scale("Square1", square1, 8, 8, 0, 1);
    assertEquals("Square1 changes color from (10.0,15.0,20.0) "
            + "to (12.0,15.0,18.0) from time t= 0 to t=1", scale2.toString());
    scale3 = new ChangeColor("Square1", square1, new RGB(200, 215, 218),
            0, 1);
    assertEquals("Square1 changes color from (12.0,15.0,18.0) "
            + "to (200.0,215.0,218.0) from time t= 0 to t=1", scale3.toString());
  }

  @Test (expected=IllegalArgumentException.class)
  public void illegalNameScaleConstructorTest() {
    scale2 = new Scale("Square1", oval1, 24,38, 1, 10);
  }

  @Test (expected=IllegalArgumentException.class)
  public void illegalNameSpaceScaleConstructorTest() {
    scale3 = new Scale(" ", rectangle1, 12, 15, 1, 10);
  }

  @Test (expected=IllegalArgumentException.class)
  public void illegalNameEmptyScaleConstructorTest() {
    scale3 = new Scale("", oval1, 16, 10, 1, 10);
  }
  @Test(expected = IllegalArgumentException.class)
  public void negativeMoveTickGetShapeAtTick() {
    move.getShapeAtTick(-1, rectangle1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullShapeMoveTickGetShapeAtTick() {
    move.getShapeAtTick(10, null);
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
            Name: Rectangle1
            Type: rectangle
            Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)
            Appears at t=1
            Disappears at t=100
            Name: Rectangle1
            Type: rectangle
            Min corner: (225.2,225.0), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)
            Appears at t=1
            Disappears at t=100
            Name: Rectangle1
            Type: rectangle
            Min corner: (250.4,250.1), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)
            Appears at t=1
            Disappears at t=100
            Name: Rectangle1
            Type: rectangle
            Min corner: (275.6,275.1), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)
            Appears at t=1
            Disappears at t=100
            Name: Rectangle1
            Type: rectangle
            Min corner: (300.8,300.2), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)
            Appears at t=1
            Disappears at t=100""", intervals.toString());
  }

  // tests the get current shape function for move
  @Test
  public void moveGetCurrentShapeTest() {
    assertEquals("""
            Name: Rectangle1
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

  @Test(expected = IllegalArgumentException.class)
  public void negativeChangeColorTickGetShapeAtTick() {
    color.getShapeAtTick(-1, circle1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullShapeChangeColorTickGetShapeAtTick() {
    color.getShapeAtTick(10, null);
  }

  // tests the getShapesAtTick for the changeColor class over an interval
  @Test
  public void changeColorGetShapeAtTickTest() {
    StringBuilder intervals = new StringBuilder();
    IShape currentShape = circle1;
    for (int i = 16; i <= 20; i++) {
      currentShape = color.getShapeAtTick(i, currentShape);
      intervals.append(currentShape.toString());
      if (i != 20) {
        intervals.append("\n");
      }
    }
    assertEquals("""
                    Name: Circle1
                    Type: circle
                    Center: (100.0,50.0), Radius: 12.0, Color: (44.1,120.2,44.1)
                    Appears at t=1
                    Disappears at t=100
                    Name: Circle1
                    Type: circle
                    Center: (100.0,50.0), Radius: 12.0, Color: (83.1,140.4,83.2)
                    Appears at t=1
                    Disappears at t=100
                    Name: Circle1
                    Type: circle
                    Center: (100.0,50.0), Radius: 12.0, Color: (122.1,160.6,122.3)
                    Appears at t=1
                    Disappears at t=100
                    Name: Circle1
                    Type: circle
                    Center: (100.0,50.0), Radius: 12.0, Color: (161.2,180.8,161.4)
                    Appears at t=1
                    Disappears at t=100
                    Name: Circle1
                    Type: circle
                    Center: (100.0,50.0), Radius: 12.0, Color: (200.3,201.0,200.5)
                    Appears at t=1
                    Disappears at t=100"""
            , intervals.toString());
  }

  @Test
  public void changeColorGetCurrentShapeTest() {
    assertEquals("""
            Name: Circle1
            Type: circle
            Center: (100.0,50.0), Radius: 12.0, Color: (200.3,200.5,201.0)
            Appears at t=1
            Disappears at t=100""", color.getCurrentShape().toString());
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

  @Test(expected = IllegalArgumentException.class)
  public void negativeScaleTickGetShapeAtTick() {
    scale.getShapeAtTick(-1, circle1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullShapeScaleTickGetShapeAtTick() {
    scale.getShapeAtTick(10, null);
  }

  @Test
  public void scaleGetShapeAtTickTest() {
    StringBuilder intervals = new StringBuilder();
    IShape currentShape = oval1;
    for (int i = 11; i <= 15; i++) {
      currentShape = scale.getShapeAtTick(i, currentShape);
      intervals.append(currentShape.toString());
      if (i != 15) {
        intervals.append("\n");
      }
    }
    assertEquals("""
                    Name: Oval1
                    Type: oval
                    Center: (200.0,200.0), X radius: 100.2, Y radius: 140.0, Color: (1.0,1.0,1.0)
                    Appears at t=1
                    Disappears at t=100
                    Name: Oval1
                    Type: oval
                    Center: (200.0,200.0), X radius: 150.3, Y radius: 180.1, Color: (1.0,1.0,1.0)
                    Appears at t=1
                    Disappears at t=100
                    Name: Oval1
                    Type: oval
                    Center: (200.0,200.0), X radius: 200.5, Y radius: 220.1, Color: (1.0,1.0,1.0)
                    Appears at t=1
                    Disappears at t=100
                    Name: Oval1
                    Type: oval
                    Center: (200.0,200.0), X radius: 250.6, Y radius: 260.1, Color: (1.0,1.0,1.0)
                    Appears at t=1
                    Disappears at t=100
                    Name: Oval1
                    Type: oval
                    Center: (200.0,200.0), X radius: 300.8, Y radius: 300.2, Color: (1.0,1.0,1.0)
                    Appears at t=1
                    Disappears at t=100"""
            , intervals.toString());
  }

  @Test
  public void scaleGetCurrentShapeTest() {
    assertEquals("""
            Name: Oval1
            Type: oval
            Center: (200.0,200.0), X radius: 300.8, Y radius: 300.2, Color: (1.0,1.0,1.0)
            Appears at t=1
            Disappears at t=100""", scale.getCurrentShape().toString());
  }

  @Test
  public void scaleGetTimeTest() {
    assertEquals(10, scale.getTime().getStartTime());
    assertEquals(15, scale.getTime().getEndTime());
  }

  @Test
  public void scaleGetTypeTest() {
    assertEquals("SCALE", scale.getType().toString());
  }

  @Test
  public void scaleToStringTest() {
    assertEquals("Oval1 scales from X radius:, 50.0Y radius: 100.0 to X " +
            "radius, 300.8Y radius 300.156from time t=10 to t=15", scale.toString());
  }

}