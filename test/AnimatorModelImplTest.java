import org.junit.Before;
import org.junit.Test;

import cs5004.animator.AnimatorModelImpl;
import cs5004.shape.Shape;
import cs5004.utilities.RGB;

import static org.junit.Assert.assertEquals;

public class AnimatorModelImplTest {
  AnimatorModelImpl model1;
  AnimatorModelImpl model2;


  @Before
  public void setUp() {
    model1 = new AnimatorModelImpl();
    model1.createShape("R", Shape.RECTANGLE, new RGB(1, 1, 1),
            50, 100, 200, 200, 1, 100);
    model1.createShape("O", Shape.OVAL, new RGB(21, 21, 21), 60,
            30, 500, 100, 6, 100);
    model1.createShape("T", Shape.TRIANGLE, new RGB(34, 0, 1),
            45.12, 30.54, 78, 234, 23, 75);
    model1.createShape("S", Shape.SQUARE, new RGB(1, 1, 1),
            15, 15, 125, 34, 30, 60);
    model1.createShape("RH", Shape.RHOMBUS, new RGB(2, 3, 4),
            20, 20, 45, 15, 98, 99);

    model2 = new AnimatorModelImpl();
    model2.createShape("P1", Shape.RECTANGLE, new RGB(1, 1, 1),
            50, 100, 200, 200, 1, 100);
    model2.move("P1", 150.0, 150.0, 1, 5);
    model2.scale("P1", 100, 200, 5, 10);
    model2.changeColor("P1", new RGB(10, 10, 10), 10, 15);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCreateShapeEmptyName() {
    model1.createShape("", Shape.CIRCLE, new RGB(1, 1, 1),
            12, 12, 100, 50, 6, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCreateShapeEmptyNameWithSpace() {
    model1.createShape(" ", Shape.CIRCLE, new RGB(1, 1, 1),
            12, 12, 100, 50, 6, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCreateShapeNullName() {
    model1.createShape(null, Shape.CIRCLE, new RGB(1, 1, 1),
            12, 12, 100, 50, 6, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCreateShapeNameAlreadyExists() {
    model1.createShape("R", Shape.CIRCLE, new RGB(1, 1, 1),
            12, 12, 100, 50, 6, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCreateShapeNullShape() {
    model1.createShape("B", null, new RGB(1, 1, 1),
            12, 12, 100, 50, 0, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCreateShapeNullRGB() {
    model1.createShape("D", Shape.CIRCLE, null,
            12, 12, 100, 50, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCreateShapeNegativeWidth() {
    model1.createShape("E", Shape.RECTANGLE, new RGB(51, 254, 46),
            -112, 12, 100, 50, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCreateShapeNegativeHeight() {
    model1.createShape("F", Shape.RECTANGLE, new RGB(51, 254, 46),
            212, -12, 100, 50, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCreateShapeNegativeStart() {
    model1.createShape("G", Shape.CIRCLE, new RGB(1, 1, 1),
            12, 12, 100, 50, -1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCreateShapeNegativeEndTime() {
    model1.createShape("H", Shape.CIRCLE, new RGB(1, 1, 1),
            12, 12, 100, 50, 1, -40);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCreateShapeStartBeforeEnd() {
    model1.createShape("I", Shape.CIRCLE, new RGB(1, 1, 1),
            12, 12, 100, 50, 6, 4);
  }

  @Test
  public void testCreateShape() {
    model1.createShape("C", Shape.CIRCLE, new RGB(1, 1, 1),
            12, 12, 100, 50, 1, 100);
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
            Appears at t=30
            Disappears at t=60

            Name: C
            Type: circle
            Center: (100.0,50.0), Radius: 12.0, Color: (1.0,1.0,1.0)
            Appears at t=1
            Disappears at t=100

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

            """, model1.toString());

    //create shape with starting and ending time of zero
    model1.createShape("X", Shape.OVAL, new RGB(0, 0, 0), 35, 47,
            234, 16, 0, 0);
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
            Appears at t=30
            Disappears at t=60

            Name: C
            Type: circle
            Center: (100.0,50.0), Radius: 12.0, Color: (1.0,1.0,1.0)
            Appears at t=1
            Disappears at t=100

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

            Name: X
            Type: oval
            Center: (234.0,16.0), X radius: 35.0, Y radius: 47.0, Color: (0.0,0.0,0.0)
            Appears at t=0
            Disappears at t=0

            Name: O
            Type: oval
            Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (21.0,21.0,21.0)
            Appears at t=6
            Disappears at t=100

            """, model1.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveNullName() {
    model1.move(null, 45, 125, 34, 36);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveEmptyName() {
    model1.move("", 45, 125, 34, 36);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveEmptySpaceName() {
    model1.move(" ", 45, 125, 34, 36);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveToSamePosition() {
    model1.move("S", 45, 125, 29, 36);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveToNegativeX() {
    model1.move("S", -12, 125, 34, 36);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveToNegativeY() {
    model1.move("S", 12, -125, 34, 36);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveNegativeStart() {
    model1.move("S", 45, 125, -34, 36);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveNegativeEndTime() {
    model1.move("S", 45, 125, 34, -36);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveStartBeforeEnd() {
    model1.move("S", 45, 125, 35, 34);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveBeforeTimeRange() {
    model1.move("S", 45, 125, 29, 36);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveAfterTimeRange() {
    model1.move("S", 45, 125, 29, 37);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveOverlappingMove() {
    model1.move("R", 350, 375, 10, 50);
    model1.move("R", 20, 14, 10, 50);
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
            Appears at t=30
            Disappears at t=60

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

            Shape R moves from (200.0, 200.0) to (350.0, 375.0) from time t=10 to t=50
            """, model1.toString());

    //move back to previous position
    model1.move("R", 200, 200, 51, 53);
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
            Appears at t=30
            Disappears at t=60

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

            Shape R moves from (200.0, 200.0) to (350.0, 375.0) from time t=10 to t=50
            Shape R moves from (350.0, 375.0) to (200.0, 200.0) from time t=51 to t=53
            """, model1.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorNullName() {
    model1.changeColor(null, new RGB(127, 39, 45), 37, 55);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorEmptyName() {
    model1.changeColor("", new RGB(127, 39, 45), 37, 55);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorEmptySpaceName() {
    model1.changeColor(" ", new RGB(127, 39, 45), 37, 55);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorNullColor() {
    model1.changeColor("R", null, 37, 55);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorNegativeRed() {
    model1.changeColor(" ", new RGB(-127, 39, 45), 37, 55);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorNegativeGreen() {
    model1.changeColor(" ", new RGB(127, -39, 45), 37, 55);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorNegativeBlue() {
    model1.changeColor(" ", new RGB(127, 39, -45), 37, 55);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorOutOfRangeRed() {
    model1.changeColor(" ", new RGB(255, 39, 17), 37, 55);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorOutOfRangeGreen() {
    model1.changeColor(" ", new RGB(55, 255, 40), 37, 55);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorOutOfRangeBlue() {
    model1.changeColor(" ", new RGB(55, 198, 255), 37, 55);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorRGBAllNegative() {
    model1.changeColor(" ", new RGB(-1, -1, -1), 37, 55);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorRGBAllOverRange() {
    model1.changeColor(" ", new RGB(255, 255, 255), 37, 55);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorStartTimeNegative() {
    model1.changeColor("R", new RGB(254.16, 35.44, 122),
            -33, 44);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorEndTimeNegative() {
    model1.changeColor("R", new RGB(254.16, 35.44, 122),
            33, -44);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorEndBeforeStart() {
    model1.changeColor("R", new RGB(254.16, 35.44, 122),
            44, 33);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorOverlappingColorChange() {
    model1.changeColor("R", new RGB(254.16, 35.44, 122),
            33, 44);
    model1.changeColor("R", new RGB(3, 4, 5),
            33, 44);
  }

  @Test
  public void testChangeColor() {
    model1.changeColor("R", new RGB(254.16, 35.44, 122),
            33, 44);
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
            Appears at t=30
            Disappears at t=60

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

            Shape R changes color from (1.0,1.0,1.0) to (254.2,35.4,122.0) from time t= 33 to t=44
            """, model1.toString());
  }

  @Test
  public void testScale() {
    model1.scale("T", 47, 51.75, 23, 25);
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
            Appears at t=30
            Disappears at t=60

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

            Shape T scales from Width: 45.12, Height: 30.54 to Width: 47.0, Height: 51.75 from time t=23 to t=25
            """, model1.toString());
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
    //System.out.println(model2.toString());
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
