import static org.junit.Assert.assertEquals;

import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.shape.Shape;
import cs5004.animator.tools.RGB;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for the IAnimatorModel interface.
 */
public class AnimatorModelImplTest {

  AnimatorModelImpl model1;
  AnimatorModelImpl model2;
  AnimatorModelImpl model3;
  AnimatorModelImpl modelEmpty;

  @Before
  public void setUp() {
    model1 = new AnimatorModelImpl();
    model1.createShape("R", Shape.RECTANGLE, new RGB(1, 1, 1),
        50, 100, 200, 200, 1, 100);
    model1.createShape("O", Shape.ELLIPSE, new RGB(21, 21, 21), 60,
        30, 500, 100, 6, 100);
    model1.createShape("T", Shape.TRIANGLE, new RGB(34, 0, 1),
        45.12, 30.54, 78, 234, 23, 75);
    model1.createShape("S", Shape.SQUARE, new RGB(1, 1, 1),
        15, 15, 125, 34, 30, 60);
    model1.createShape("RH", Shape.RHOMBUS, new RGB(2, 3, 4),
        20, 20, 45, 15, 98, 99);

    model2 = new AnimatorModelImpl();

    // P1 moves backwards. scales to a bigger size and changes color
    model2.createShape("P1", Shape.RECTANGLE, new RGB(1, 1, 1),
        50, 100, 200, 200, 1, 100);
    model2.move("P1", 150.0, 150.0, 1, 5);
    model2.scale("P1", 100, 200, 5, 10);
    model2.changeColor("P1", new RGB(10, 10, 10), 10, 15);

    model3 = new AnimatorModelImpl();
    modelEmpty = new AnimatorModelImpl();
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
  public void testIllegalCreateDuplicateCreate() {
    model1.createShape("R", Shape.RECTANGLE, new RGB(1, 1, 1),
        50, 100, 200, 200, 1, 100);
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

    //create shape with starting and ending time of zero
    model1.createShape("X", Shape.ELLIPSE, new RGB(0, 0, 0), 35, 47,
        234, 16, 0, 0);
    assertEquals("Shapes:\n"
                 + "Name: R\n"
                 + "Type: rectangle\n"
                 + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=1\nDisappears at t=100\n\n"
                 + "Name: O\n"
                 + "Type: ellipse\n"
                 + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, "
                 + "Color: (21.0,21.0,21.0)\n"
                 + "Appears at t=6\nDisappears at t=100\n\n"
                 + "Name: T\n"
                 + "Type: triangle\n"
                 + "Min corner: (78.0,234.0), Width: 45.1, Height: 30.5, Color: (34.0,0.0,1.0)\n"
                 + "Appears at t=23\nDisappears at t=75\n\n"
                 + "Name: S\n"
                 + "Type: square\n"
                 + "Min corner: (125.0,34.0), Length: 15.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=30\nDisappears at t=60\n\n"
                 + "Name: RH\n"
                 + "Type: rhombus\n"
                 + "Min corner: (45.0,15.0), Width: 20.0, Height: 20.0, Color: (2.0,3.0,4.0)\n"
                 + "Appears at t=98\nDisappears at t=99\n\n"
                 + "Name: C\n"
                 + "Type: circle\n"
                 + "Center: (100.0,50.0), Radius: 12.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=1\nDisappears at t=100\n\n"
                 + "Name: X\n"
                 + "Type: ellipse\n"
                 + "Center: (234.0,16.0), X radius: 35.0, Y radius: 47.0, Color: (0.0,0.0,0.0)\n"
                 + "Appears at t=0\nDisappears at t=0\n\n", model1.toString());
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

//  @Test(expected = IllegalArgumentException.class)
//  public void testIllegalMoveBeforeTimeRange() {
//    model1.move("S", 45, 125, 10, 25);
//  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveAfterTimeRange() {
    model1.move("S", 45, 125, 61, 37);
  }

//  @Test(expected = IllegalArgumentException.class)
//  public void testIllegalMoveOverlappingMoveSameStartAndEnd() {
//    model1.move("R", 335, 375, 10, 50);
//    model1.move("R", 20, 14, 10, 50);
//  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveOverlappingMoveWithinPreviousMove() {
    model1.move("R", 340, 375, 10, 50);
    model1.move("R", 20, 14, 35, 40);
  }

  @Test
  public void testMove() {
    model1.move("R", 350, 375, 10, 50);
    assertEquals("Shapes:\n"
                 + "Name: R\n"
                 + "Type: rectangle\n"
                 + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=1\nDisappears at t=100\n\n"
                 + "Name: O\nType: ellipse\n"
                 + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, "
                 + "Color: (21.0,21.0,21.0)\n"
                 + "Appears at t=6\nDisappears at t=100\n\n"
                 + "Name: T\n"
                 + "Type: triangle\n"
                 + "Min corner: (78.0,234.0), Width: 45.1, Height: 30.5, Color: (34.0,0.0,1.0)\n"
                 + "Appears at t=23\nDisappears at t=75\n\n"
                 + "Name: S\n"
                 + "Type: square\n"
                 + "Min corner: (125.0,34.0), Length: 15.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=30\nDisappears at t=60\n\n"
                 + "Name: RH\n"
                 + "Type: rhombus\n"
                 + "Min corner: (45.0,15.0), Width: 20.0, Height: 20.0, Color: (2.0,3.0,4.0)\n"
                 + "Appears at t=98\nDisappears at t=99\n\n"
                 + "Shape R moves from (200.0, 200.0) to (350.0, 375.0) from time t=10 to t=50",
        model1.toString());

    //move back to previous position
    model1.move("R", 200, 200, 51, 53);
    assertEquals("Shapes:\n"
                 + "Name: R\n"
                 + "Type: rectangle\n"
                 + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=1\nDisappears at t=100\n\n"
                 + "Name: O\n"
                 + "Type: ellipse\n"
                 + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, "
                 + "Color: (21.0,21.0,21.0)\n"
                 + "Appears at t=6\nDisappears at t=100\n\n"
                 + "Name: T\n"
                 + "Type: triangle\n"
                 + "Min corner: (78.0,234.0), Width: 45.1, Height: 30.5, Color: (34.0,0.0,1.0)\n"
                 + "Appears at t=23\nDisappears at t=75\n\n"
                 + "Name: S\n"
                 + "Type: square\n"
                 + "Min corner: (125.0,34.0), Length: 15.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=30\nDisappears at t=60\n\n"
                 + "Name: RH\n"
                 + "Type: rhombus\n"
                 + "Min corner: (45.0,15.0), Width: 20.0, Height: 20.0, Color: (2.0,3.0,4.0)\n"
                 + "Appears at t=98\nDisappears at t=99\n\n"
                 + "Shape R moves from (200.0, 200.0) to (350.0, 375.0) from time t=10 to t=50\n"
                 + "Shape R moves from (350.0, 375.0) to (200.0, 200.0) from time t=51 to t=53",
        model1.toString());
  }

  @Test
  public void testMoveOverlappingActions() {
    model3.createShape("F1", Shape.TRIANGLE, new RGB(0, 0, 0),
        35, 50, 30, 60, 1, 80);
    model3.move("F1", 34, 65, 20, 33);
    model3.changeColor("F1", new RGB(13, 13, 43), 23, 30);
    model3.scale("F1", 108, 180, 23, 30);
    assertEquals("Shapes:\n"
                 + "Name: F1\n"
                 + "Type: triangle\n"
                 + "Min corner: (30.0,60.0), Width: 35.0, Height: 50.0, Color: (0.0,0.0,0.0)\n"
                 + "Appears at t=1\nDisappears at t=80\n\n"
                 + "Shape F1 moves from (30.0, 60.0) to (34.0, 65.0) from time t=20 to t=33\n"
                 + "Shape F1 changes color from (0.0,0.0,0.0) to (13.0,13.0,43.0) "
                 + "from time t=23 to t=30\n"
                 + "Shape F1 scales from Width: 35.0, Height: 50.0 to Width: 108.0, "
                 + "Height: 180.0 from time t=23 to t=30", model3.toString());

    //move overlap where end time of previous is same as start time of next move
    model3.move("F1",40, 75, 34, 40);
    model3.move("F1", 45, 80, 40, 45);
    assertEquals("Shapes:\n"
                 + "Name: F1\n"
                 + "Type: triangle\n"
                 + "Min corner: (30.0,60.0), Width: 35.0, Height: 50.0, Color: (0.0,0.0,0.0)\n"
                 + "Appears at t=1\nDisappears at t=80\n\n"
                 + "Shape F1 moves from (30.0, 60.0) to (34.0, 65.0) from time t=20 to t=33\n"
                 + "Shape F1 changes color from (0.0,0.0,0.0) to (13.0,13.0,43.0) "
                 + "from time t=23 to t=30\n"
                 + "Shape F1 scales from Width: 35.0, Height: 50.0 to Width: 108.0, "
                 + "Height: 180.0 from time t=23 to t=30\n"
                 + "Shape F1 moves from (34.0, 65.0) to (40.0, 75.0) from time t=34 to t=40\n"
                 + "Shape F1 moves from (40.0, 75.0) to (45.0, 80.0) "
                 + "from time t=40 to t=45", model3.toString());
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
    model1.changeColor("R",
        null, 37, 55);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorNegativeRed() {
    model1.changeColor("R", new RGB(-127, 39, 45), 37, 55);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorNegativeGreen() {
    model1.changeColor("R", new RGB(127, -39, 45), 37, 55);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorNegativeBlue() {
    model1.changeColor("R", new RGB(127, 39, -45), 37, 55);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorOutOfRangeRed() {
    model1.changeColor("R", new RGB(256, 39, 17), 37, 55);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorOutOfRangeGreen() {
    model1.changeColor("R", new RGB(55, 256, 40), 37, 55);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorOutOfRangeBlue() {
    model1.changeColor("R", new RGB(55, 198, 256), 37, 55);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorRGBAllNegative() {
    model1.changeColor("R", new RGB(-1, -1, -1), 37, 55);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorRGBAllOverRange() {
    model1.changeColor("R", new RGB(256, 256, 256), 37, 55);
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
  public void testIllegalChangeColorEndZeroTest() {
    model1.changeColor("R", new RGB(255, 255, 255),
        25, 0);
  }

//  @Test(expected = IllegalArgumentException.class)
//  public void testIllegalChangeColorOverlappingSameStartAndEnd() {
//    model1.changeColor("R", new RGB(220.16, 36, 120),
//        33, 44);
//    model1.changeColor("R", new RGB(3, 4, 5),
//        33, 44);
//  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorOverlappingWithinPreviousColorChange() {
    model1.changeColor("R", new RGB(230, 35.44, 121),
        33, 44);
    model1.changeColor("R", new RGB(3, 4, 5),
        40, 41);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColorOverlappingEndTimeAndStartOfNext() {
    model1.changeColor("R", new RGB(230, 35.44, 121),
        33, 44);
    model1.changeColor("R", new RGB(3, 4, 5),
        40, 41);
  }

  @Test
  public void testChangeColor() {
    model1.changeColor("R", new RGB(254.16, 35.44, 122),
        33, 44);

    assertEquals("Shapes:\n"
                 + "Name: R\n"
                 + "Type: rectangle\n"
                 + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=1\nDisappears at t=100\n\n"
                 + "Name: O\n"
                 + "Type: ellipse\n"
                 + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, "
                 + "Color: (21.0,21.0,21.0)\n"
                 + "Appears at t=6\nDisappears at t=100\n\n"
                 + "Name: T\n"
                 + "Type: triangle\n"
                 + "Min corner: (78.0,234.0), Width: 45.1, Height: 30.5, Color: (34.0,0.0,1.0)\n"
                 + "Appears at t=23\nDisappears at t=75\n\n"
                 + "Name: S\n"
                 + "Type: square\n"
                 + "Min corner: (125.0,34.0), Length: 15.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=30\nDisappears at t=60\n\n"
                 + "Name: RH\n"
                 + "Type: rhombus\n"
                 + "Min corner: (45.0,15.0), Width: 20.0, Height: 20.0, Color: (2.0,3.0,4.0)\n"
                 + "Appears at t=98\nDisappears at t=99\n\n"
                 + "Shape R changes color from (1.0,1.0,1.0) to (254.2,35.4,122.0) "
                 + "from time t=33 to t=44"
        , model1.toString());

     //changing color back to previous color
    model1.changeColor("R", new RGB(1, 1, 1),
        45, 50);
    assertEquals("Shapes:\n"
                 + "Name: R\n"
                 + "Type: rectangle\n"
                 + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=1\nDisappears at t=100\n\n"
                 + "Name: O\n"
                 + "Type: ellipse\n"
                 + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, "
                 + "Color: (21.0,21.0,21.0)\n"
                 + "Appears at t=6\nDisappears at t=100\n\n"
                 + "Name: T\n"
                 + "Type: triangle\n"
                 + "Min corner: (78.0,234.0), Width: 45.1, Height: 30.5, Color: (34.0,0.0,1.0)\n"
                 + "Appears at t=23\nDisappears at t=75\n\n"
                 + "Name: S\n"
                 + "Type: square\n"
                 + "Min corner: (125.0,34.0), Length: 15.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=30\nDisappears at t=60\n\n"
                 + "Name: RH\n"
                 + "Type: rhombus\n"
                 + "Min corner: (45.0,15.0), Width: 20.0, Height: 20.0, Color: (2.0,3.0,4.0)\n"
                 + "Appears at t=98\nDisappears at t=99\n\n"
                 + "Shape R changes color from (1.0,1.0,1.0) to (254.2,35.4,122.0) "
                 + "from time t=33 to t=44\n"
                 + "Shape R changes color from (254.2,35.4,122.0) to (1.0,1.0,1.0) "
                 + "from time t=45 to t=50"
        , model1.toString());
  }

  @Test
  public void testChangeColorBackToPrevious() {
    model1.changeColor("R", new RGB(254.16, 35.44, 122),
        33, 44);
    assertEquals("Shapes:\n"
                 + "Name: R\n"
                 + "Type: rectangle\n"
                 + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=1\nDisappears at t=100\n\n"
                 + "Name: O\n"
                 + "Type: ellipse\n"
                 + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (21.0,21.0,21.0)\n"
                 + "Appears at t=6\nDisappears at t=100\n\n"
                 + "Name: T\n"
                 + "Type: triangle\n"
                 + "Min corner: (78.0,234.0), Width: 45.1, Height: 30.5, Color: (34.0,0.0,1.0)\n"
                 + "Appears at t=23\nDisappears at t=75\n\n"
                 + "Name: S\n"
                 + "Type: square\n"
                 + "Min corner: (125.0,34.0), Length: 15.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=30\nDisappears at t=60\n\n"
                 + "Name: RH\n"
                 + "Type: rhombus\n"
                 + "Min corner: (45.0,15.0), Width: 20.0, Height: 20.0, Color: (2.0,3.0,4.0)\n"
                 + "Appears at t=98\nDisappears at t=99\n\n"
                 + "Shape R changes color from (1.0,1.0,1.0) to (254.2,35.4,122.0) "
                 + "from time t=33 to t=44",
        model1.toString());

    model1.changeColor("R", new RGB(1.0, 1.0, 1.0),
        45, 55);
    assertEquals("Shapes:\n"
                 + "Name: R\n"
                 + "Type: rectangle\n"
                 + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=1\nDisappears at t=100\n\n"
                 + "Name: O\n"
                 + "Type: ellipse\n"
                 + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, "
                 + "Color: (21.0,21.0,21.0)\n"
                 + "Appears at t=6\nDisappears at t=100\n\n"
                 + "Name: T\n"
                 + "Type: triangle\n"
                 + "Min corner: (78.0,234.0), Width: 45.1, Height: 30.5, Color: (34.0,0.0,1.0)\n"
                 + "Appears at t=23\nDisappears at t=75\n\n"
                 + "Name: S\n"
                 + "Type: square\n"
                 + "Min corner: (125.0,34.0), Length: 15.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=30\nDisappears at t=60\n\n"
                 + "Name: RH\n"
                 + "Type: rhombus\n"
                 + "Min corner: (45.0,15.0), Width: 20.0, Height: 20.0, Color: (2.0,3.0,4.0)\n"
                 + "Appears at t=98\nDisappears at t=99\n\n"
                 + "Shape R changes color from (1.0,1.0,1.0) to (254.2,35.4,122.0) "
                 + "from time t=33 to t=44\n"
                 + "Shape R changes color from (254.2,35.4,122.0) to (1.0,1.0,1.0) "
                 + "from time t=45 to t=55"
        , model1.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalScaleNullName() {
    model1.scale(null, 5.7, 2, 5, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalScaleEmptyName() {
    model1.scale("", 5.7, 2, 5, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalScaleEmptySpaceName() {
    model1.scale(" ", 5.7, 2, 5, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalScaleNegativeWidth() {
    model1.scale("R", -14.6, 2, 5, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalScaleZeroWidth() {
    model1.scale("R", 0, 2, 5, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalScaleNegativeHeight() {
    model1.scale("R", 4.25, -25.7, 5, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalScaleZeroHeight() {
    model1.scale("R", 4.25, 0, 5, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalScaleNegativeStartTime() {
    model1.scale("R", 4.25, 3.5, -15, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalScaleZeroEndTime() {
    model1.scale("R", 4.25, 3.5, 5, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalScaleNegativeEndTime() {
    model1.scale("R", 4.25, 3.5, 5, -20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalScaleStartTimeGreaterThanEndTime() {
    model1.scale("R", 4.25, 3.5, 15, 10);
  }

//  @Test(expected = IllegalArgumentException.class)
//  public void testIllegalScaleOverlappingTimesStartAndEnd() {
//    model1.scale("R", 4.25, 3.5, 0, 10);
//    model1.scale("R", 16, 34, 0, 10);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testIllegalScaleOverlappingWithinPreviousScale() {
//    model1.scale("R", 3.25, 7.5, 0, 10);
//    model1.scale("R", 4.25, 3.5, 10, 12);
//  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalScaleOverlappingOverlappingEndTimeAndStartOfNext() {
    model1.scale("R", 3.25, 7.5, 0, 10);
    model1.scale("R", 4.25, 3.5, 5, 8);
  }

  @Test
  public void testScale() {
    model1.scale("T", 47, 51.75, 23, 25);
    assertEquals("Shapes:\n"
                 + "Name: R\n"
                 + "Type: rectangle\n"
                 + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=1\nDisappears at t=100\n\n"
                 + "Name: O\n"
                 + "Type: ellipse\n"
                 + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, "
                 + "Color: (21.0,21.0,21.0)\n"
                 + "Appears at t=6\nDisappears at t=100\n\n"
                 + "Name: T\n"
                 + "Type: triangle\n"
                 + "Min corner: (78.0,234.0), Width: 45.1, Height: 30.5, Color: (34.0,0.0,1.0)\n"
                 + "Appears at t=23\nDisappears at t=75\n\n"
                 + "Name: S\n"
                 + "Type: square\n"
                 + "Min corner: (125.0,34.0), Length: 15.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=30\nDisappears at t=60\n\n"
                 + "Name: RH\n"
                 + "Type: rhombus\n"
                 + "Min corner: (45.0,15.0), Width: 20.0, Height: 20.0, Color: (2.0,3.0,4.0)\n"
                 + "Appears at t=98\nDisappears at t=99\n\n"
                 + "Shape T scales from Width: 45.12, Height: 30.54 to Width: 47.0, "
                 + "Height: 51.75 from time t=23 to t=25",
        model1.toString());
  }

  @Test
  public void testScaleToSameScale() {
    model1.scale("T", 45.12, 30.54, 23, 25);
    assertEquals("Shapes:\n"
                 + "Name: R\n"
                 + "Type: rectangle\n"
                 + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=1\nDisappears at t=100\n\n"
                 + "Name: O\n"
                 + "Type: ellipse\n"
                 + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, "
                 + "Color: (21.0,21.0,21.0)\n"
                 + "Appears at t=6\nDisappears at t=100\n\n"
                 + "Name: T\n"
                 + "Type: triangle\n"
                 + "Min corner: (78.0,234.0), Width: 45.1, Height: 30.5, Color: (34.0,0.0,1.0)\n"
                 + "Appears at t=23\nDisappears at t=75\n\n"
                 + "Name: S\n"
                 + "Type: square\n"
                 + "Min corner: (125.0,34.0), Length: 15.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=30\nDisappears at t=60\n\n"
                 + "Name: RH\n"
                 + "Type: rhombus\n"
                 + "Min corner: (45.0,15.0), Width: 20.0, Height: 20.0, Color: (2.0,3.0,4.0)\n"
                 + "Appears at t=98\nDisappears at t=99\n\n"
                 + "Shape T scales from Width: 45.12, Height: 30.54 to Width: 45.12, "
                 + "Height: 30.54 from time t=23 to t=25", model1.toString());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testIllegalGetShapesAtTickNegativeTick() {
    model1.getShapesAtTicks(-1);
  }

  @Test
  public void testGetShapesAtTicksZero() {
//    assertEquals("[Name: P1\n" +
//                 "Type: rectangle\n" +
//                 "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)\n" +
//                 "Appears at t=1\n" +
//                 "Disappears at t=100]", model2.getShapesAtTicks(0).toString());
//    assertEquals("[]", model3.getShapesAtTicks(0).toString());
  }

  @Test
  public void testGetShapesAtTicksTickOne() {
//    assertEquals("[Name: P1\n" +
//                 "Type: rectangle\n" +
//                 "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)\n" +
//                 "Appears at t=1\n" +
//                 "Disappears at t=100]", model2.getShapesAtTicks(1).toString());
  }

  @Test
  public void testGetShapesAtTickAfterFewActions() {
//    model2.createShape("O3", Shape.ELLIPSE, new RGB(3, 34, 16),
//        32, 16, 45, 67, 50, 100);
//    model2.move("O3", 50, 75, 51, 70);
//    model2.scale("O3", 40, 20, 52, 54);
//    model2.createShape("R3", Shape.RECTANGLE, new RGB(254, 45, 130),
//        18, 15, 0, 0, 80, 99);
//    model2.changeColor("R3", new RGB(3, 3, 3), 81, 99);
//    model2.createShape("T3", Shape.TRIANGLE, new RGB(20, 20, 20),
//        50, 50, 30, 12, 3, 50);
//
  }

  @Test
  public void testTestToString() {
    assertEquals("Shapes:\n"
                 + "Name: R\n"
                 + "Type: rectangle\n"
                 + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=1\nDisappears at t=100\n\nName: O\nType: ellipse\n"
                 + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, "
                 + "Color: (21.0,21.0,21.0)\n" + "Appears at t=6\nDisappears at t=100\n\n"
                 + "Name: T\n"
                 + "Type: triangle\n"
                 + "Min corner: (78.0,234.0), Width: 45.1, Height: 30.5, Color: (34.0,0.0,1.0)\n"
                 + "Appears at t=23\nDisappears at t=75\n\n"
                 + "Name: S\n"
                 + "Type: square\n"
                 + "Min corner: (125.0,34.0), Length: 15.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=30\nDisappears at t=60\n\n"
                 + "Name: RH\n"
                 + "Type: rhombus\n"
                 + "Min corner: (45.0,15.0), Width: 20.0, Height: 20.0, Color: (2.0,3.0,4.0)\n"
                 + "Appears at t=98\nDisappears at t=99\n\n", model1.toString());

    //test toString() for rectangle actions
    assertEquals("Shapes:\n" +
                 "Name: P1\n" +
                 "Type: rectangle\n" +
                 "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,1.0,1.0)\n" +
                 "Appears at t=1\nDisappears at t=100\n\n" +
                 "Shape P1 moves from (200.0, 200.0) to (150.0, 150.0) from time t=1 to t=5\n" +
                 "Shape P1 scales from Width: 50.0, Height: 100.0 to Width: 100.0, "
                 + "Height: 200.0 from time t=5 to t=10\n" +
                 "Shape P1 changes color from (1.0,1.0,1.0) to (10.0,10.0,10.0) "
                 + "from time t=10 to t=15"
        , model2.toString());
  }


  @Test
  public void testToStringCreateAndActionsRectangleAndEllipse() {
    AnimatorModelImpl model3 = new AnimatorModelImpl();
    model3.createShape("R", Shape.RECTANGLE, new RGB(1, 0, 0),
        50, 100, 200, 200, 1, 100);
    model3.createShape("C", Shape.ELLIPSE, new RGB(0, 0, 1), 60,
        30, 500, 100, 6, 100);
    model3.move("R", 300, 300, 10, 50);
    model3.move("C", 500, 400, 20, 70);
    model3.changeColor("C", new RGB(0, 1, 0), 50, 80);
    model3.scale("R", 25, 100, 51, 70);
    model3.move("R", 200, 200, 70, 100);

    assertEquals("Shapes:\n" +
                 "Name: R\n" +
                 "Type: rectangle\n" +
                 "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n" +
                 "Appears at t=1\n" +
                 "Disappears at t=100\n" +
                 "\n" +
                 "Name: C\n" +
                 "Type: ellipse\n" +
                 "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n" +
                 "Appears at t=6\n" +
                 "Disappears at t=100\n" +
                 "\n" +
                 "Shape R moves from (200.0, 200.0) to (300.0, 300.0) from time t=10 to t=50\n" +
                 "Shape C moves from (500.0, 100.0) to (500.0, 400.0) from time t=20 to t=70\n" +
                 "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) "
                 + "from time t=50 to t=80\n" +
                 "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, "
                 + "Height: 100.0 from time t=51 to t=70\n"
                 +
                 "Shape R moves from (300.0, 300.0) to (200.0, 200.0) from time t=70 to t=100"
        , model3.toString());
  }
}
