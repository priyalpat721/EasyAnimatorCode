import org.junit.Before;
import org.junit.Test;

import cs5004.animator.shape.Circle;
import cs5004.animator.shape.IShape;
import cs5004.animator.shape.Ellipse;
import cs5004.animator.shape.Rectangle;
import cs5004.animator.tools.RGB;
import cs5004.animator.shape.Rhombus;
import cs5004.animator.shape.Shape;
import cs5004.animator.shape.Square;
import cs5004.animator.shape.Triangle;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the IShape interface.
 */
public class IShapeTest {
  private IShape rectangle;
  private IShape ellipse;
  private IShape square;
  private IShape circle;
  private IShape triangle;
  private IShape rhombus;
  private IShape r2;
  private IShape e2;
  private IShape s2;
  private IShape c2;
  private IShape t2;
  private IShape rh2;

  @Before
  public void setUp() {
    rectangle = new Rectangle("R", new RGB(1, 0, 0), 50, 100,
            200, 200, 1, 100);

    ellipse = new Ellipse("C", new RGB(0, 0, 1), 60, 30,
            500, 100, 6, 100);

    square = new Square("S", new RGB(0, 1, 0), 40, 40,
            0, 0, 10, 100);

    circle = new Circle("I", new RGB(1, 1, 1), 10, 10,
            0, 0, 1, 100);

    triangle = new Triangle("T", new RGB(5, 5, 5), 20, 30,
            40, 50, 1, 50);

    rhombus = new Rhombus("H", new RGB(20, 20, 20), 50, 60,
            2, 2, 1, 80);
  }

  @Test
  public void constructorTest() {
    rectangle = new Rectangle("R", new RGB(1, 0, 0), 50, 100,
            200, 200, 1, 100);
    assertEquals("Name: R\n" + "Type: rectangle\n"
                 + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n"
                 + "Appears at t=1\n" + "Disappears at t=100", rectangle.toString());

    ellipse = new Ellipse("C", new RGB(0, 0, 1), 60, 30,
            500, 100, 6, 100);
    assertEquals("Name: C\n" + "Type: ellipse\n"
                 + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n"
                 + "Appears at t=6\n" + "Disappears at t=100", ellipse.toString());

    square = new Square("S", new RGB(0, 1, 0), 40, 40,
            0, 0, 10, 100);
    assertEquals("Name: S\n" + "Type: square\n"
                 + "Min corner: (0.0,0.0), Length: 40.0, Color: (0.0,1.0,0.0)\n"
                 + "Appears at t=10\n" + "Disappears at t=100", square.toString());

    circle = new Circle("I", new RGB(1, 1, 1), 10, 10,
            0, 0, 1, 100);
    assertEquals("Name: I\n"
                 + "Type: circle\n" + "Center: (0.0,0.0), Radius: 10.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=1\n" + "Disappears at t=100", circle.toString());

    triangle = new Triangle("T", new RGB(5, 5, 5), 20, 30,
            40, 50, 1, 50);

    assertEquals("Name: T\n" + "Type: triangle\n"
                 + "Min corner: (40.0,50.0), Width: 20.0, Height: 30.0, Color: (5.0,5.0,5.0)\n"
                 + "Appears at t=1\n" + "Disappears at t=50", triangle.toString());

    rhombus = new Rhombus("H", new RGB(20, 20, 20), 50, 60,
            2, 2, 1, 80);
    assertEquals("Name: H\n" + "Type: rhombus\n"
                 + "Min corner: (2.0,2.0), Width: 50.0, Height: 60.0, Color: (20.0,20.0,20.0)\n"
                 + "Appears at t=1\n" + "Disappears at t=80", rhombus.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCircle() {
    IShape c1 = new Circle("I", new RGB(1, 1, 1), 20, 10,
            0, 0, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCircleNegativeRadius() {
    IShape c1 = new Circle("I", new RGB(1, 1, 1), -5, -5,
            0, 0, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSquare() {
    IShape s1 = new Square("I", new RGB(1, 1, 1), 30, 40,
            0, 0, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSquareNegativeLength() {
    IShape s1 = new Square("I", new RGB(1, 1, 1), -10, -10,
            0, 0, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalNameEmpty() {
    IShape r1 = new Rhombus("", new RGB(1, 0, 0), 50, 100,
            100, 100, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalNameSpaceCharacter() {
    IShape r1 = new Rectangle(" ", new RGB(1, 0, 0), 50, 100,
            200, 200, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalNameNull() {
    IShape r1 = new Rectangle(null, new RGB(1, 0, 0), 50, 100,
            200, 200, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalWidthNegative() {
    IShape r1 = new Rectangle("R", new RGB(1, 0, 0), -50, 100,
            200, 200, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalWidthZero() {
    IShape r1 = new Rectangle("R", new RGB(1, 0, 0), 0, 100,
            200, 200, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalHeightNegative() {
    IShape r1 = new Rectangle("R", new RGB(1, 0, 0), 50, -100,
            200, 200, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalHeightZero() {
    IShape r1 = new Rectangle("R", new RGB(1, 0, 0), 50, 0,
            200, 200, 1, 100);
  }

  @Test
  public void testGetName() {
    assertEquals("R", rectangle.getName());
    assertEquals("C", ellipse.getName());
    assertEquals("S", square.getName());
  }

  @Test
  public void testGetType() {
    assertEquals(Shape.RECTANGLE, rectangle.getType());
    assertEquals(Shape.ELLIPSE, ellipse.getType());
    assertEquals(Shape.SQUARE, square.getType());
    assertEquals(Shape.CIRCLE, circle.getType());
    assertEquals(Shape.TRIANGLE, triangle.getType());
    assertEquals(Shape.RHOMBUS, rhombus.getType());
  }

  @Test
  public void testGetTotalTime() {
    assertEquals(1, rectangle.getShowTime().getStartTime());
    assertEquals(100, rectangle.getShowTime().getEndTime());
    assertEquals(6, ellipse.getShowTime().getStartTime());
    assertEquals(100, ellipse.getShowTime().getEndTime());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalTimeStartGreaterThanEnding() {
    IShape r1 = new Rectangle("R", new RGB(1, 0, 0), 50, 100,
            200, 200, 20, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalTimeEndingLessThanStart() {
    IShape r1 = new Rectangle("R", new RGB(1, 0, 0), 50, 100,
            200, 200, 4, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalStartTimeNegative() {
    IShape r1 = new Rectangle("R", new RGB(1, 0, 0), 50, 100,
            200, 200, -1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalEndTime() {
    IShape r1 = new Rectangle("R", new RGB(1, 0, 0), 50, 100,
            200, 200, 1, -100);
  }

  @Test
  public void testGetPosition() {
    assertEquals(200, rectangle.getPosition().getX(), 0.01);
    assertEquals(200, rectangle.getPosition().getY(), 0.01);
    assertEquals(500, ellipse.getPosition().getX(), 0.01);
    assertEquals(100, ellipse.getPosition().getY(), 0.01);
  }

  @Test
  public void testSetPosition() {
    rectangle.setPosition(100, 100);
    assertEquals(100, rectangle.getPosition().getX(), 0.01);
    assertEquals(100, rectangle.getPosition().getY(), 0.01);

    //setting position with highly precise arguments
    circle.setPosition(0.9999999999999999, 99.9999999999999999);
    assertEquals(0.9999999999999999, circle.getPosition().getX(), 0.01);
    assertEquals(99.9999999999999999, circle.getPosition().getY(), 0.01);
  }

  @Test
  public void testGetColor() {
    assertEquals(1, rectangle.getColor().getRed(), 0.01);
    assertEquals(0, rectangle.getColor().getGreen(), 0.01);
    assertEquals(0, rectangle.getColor().getBlue(), 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalColorNull() {
    IShape r1 = new Ellipse("R", null, 50, 100,
            200, 200, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalColorR() {
    IShape r1 = new Rectangle("R", new RGB(-1, 0, 0), 50, 100,
            200, 200, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalColorG() {
    IShape r1 = new Rectangle("R", new RGB(1, -1, 0), 50, 100,
            200, 200, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalColorB() {
    IShape r1 = new Rectangle("R", new RGB(1, 0, 256), 50, 100,
            200, 200, 1, 100);
  }

  @Test
  public void testSetColor() {
    rectangle.setColor(new RGB(2, 2, 2));
    assertEquals(2, rectangle.getColor().getRed(), 0.01);
    assertEquals(2, rectangle.getColor().getGreen(), 0.01);
    assertEquals(2, rectangle.getColor().getBlue(), 0.01);

    //setting color with highly precise arguments
    circle.setColor(new RGB(254.9999999999999999, 254.9999999999999999,
            254.9999999999999999));
    assertEquals(254.9999999999999999, circle.getColor().getRed(), 0.01);
    assertEquals(254.9999999999999999, circle.getColor().getGreen(), 0.01);
    assertEquals(254.9999999999999999, circle.getColor().getBlue(), 0.01);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSetColorR() {
    rectangle.setColor(new RGB(-1, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSetColorG() {
    rectangle.setColor(new RGB(1, 256, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSetColorB() {
    rectangle.setColor(new RGB(1, 255, -1));
  }

  @Test
  public void testGetWidth() {
    assertEquals(50, rectangle.getWidth(), 0.01);
    assertEquals(60, ellipse.getWidth(), 0.01);
    assertEquals(20, triangle.getWidth(), 0.01);
    assertEquals(50, rhombus.getWidth(), 0.01);
  }

  @Test
  public void testGetHeight() {
    assertEquals(100, rectangle.getHeight(), 0.01);
    assertEquals(30, ellipse.getHeight(), 0.01);
    assertEquals(30, triangle.getHeight(), 0.01);
    assertEquals(60, rhombus.getHeight(), 0.01);
  }

  @Test
  public void testSetWidth() {
    rectangle.setWidth(20);
    ellipse.setWidth(30);
    assertEquals(20, rectangle.getWidth(), 0.01);
    assertEquals(30, ellipse.getWidth(), 0.01);

    //setting width with long decimal numbers
    rhombus.setWidth(30.823787428394928399);
    assertEquals(30.823787428394928399, rhombus.getWidth(), 0.01);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSetWidth() {
    rectangle.setWidth(0);
  }

  @Test
  public void testSetHeight() {
    rectangle.setHeight(40);
    ellipse.setHeight(50);
    assertEquals(40, rectangle.getHeight(), 0.01);
    assertEquals(50, ellipse.getHeight(), 0.01);

    //setting height to large decimal number
    rectangle.setHeight(60.2384782742874);
    ellipse.setHeight(50.9829348920293);
    assertEquals(60.2384782742874, rectangle.getHeight(), 0.01);
    assertEquals(50.9829348920293, ellipse.getHeight(), 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSetHeight() {
    rectangle.setHeight(-10);
  }

  @Test
  public void testGetRadius() {
    assertEquals(10, circle.getRadius(), 0.01);
  }

  @Test
  public void testSetRadius() {
    circle.setRadius(20);
    assertEquals(20, circle.getRadius(), 0.01);

    //setting radius to long decimal number
    circle.setRadius(77.82934829849239);
    assertEquals(77.82934829849239, circle.getRadius(), 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSetRadius() {
    circle.setRadius(-10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSetRadiusWithSetWidth() {
    circle.setWidth(5);
  }

  @Test
  public void testGetLength() {
    assertEquals(40, square.getLength(), 0.01);
  }

  @Test
  public void testSetLength() {
    square.setLength(30);
    assertEquals(30, square.getLength(), 0.01);

    //setting length to large decimal number
    square.setLength(82.9384920493029389424);
    assertEquals(82.9384920493029389424, square.getLength(), 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSetLength() {
    square.setLength(0);
  }

  @Test
  public void testCopy() {
    IShape newRectangle = rectangle.copy();

    newRectangle.setColor(new RGB(10, 10, 10));
    newRectangle.setWidth(70);
    newRectangle.setHeight(120);
    newRectangle.setPosition(60, 60);

    assertEquals(50, rectangle.getWidth(), 0.01);
    assertEquals(100, rectangle.getHeight(), 0.01);
    assertEquals(1, rectangle.getColor().getRed(), 0.01);
    assertEquals(0, rectangle.getColor().getGreen(), 0.01);
    assertEquals(0, rectangle.getColor().getBlue(), 0.01);
    assertEquals(200, rectangle.getPosition().getX(), 0.01);
    assertEquals(200, rectangle.getPosition().getY(), 0.01);

    assertEquals(70, newRectangle.getWidth(), 0.01);
    assertEquals(120, newRectangle.getHeight(), 0.01);
    assertEquals(10, newRectangle.getColor().getRed(), 0.01);
    assertEquals(10, newRectangle.getColor().getGreen(), 0.01);
    assertEquals(10, newRectangle.getColor().getBlue(), 0.01);
    assertEquals(60, newRectangle.getPosition().getX(), 0.01);
    assertEquals(60, newRectangle.getPosition().getY(), 0.01);
  }

  @Test
  public void testCreateLegalRectangle() {
    r2 = new Rectangle("R2", new RGB(2, 35, 250), 30, 75,
            150, 100, 5, 105);
    assertEquals("Name: R2\n"
                 + "Type: rectangle\n"
                 + "Min corner: (150.0,100.0), Width: 30.0, Height: 75.0, Color: (2.0,35.0,250.0)\n"
                 + "Appears at t=5\n"
                 + "Disappears at t=105", r2.toString());
  }

  @Test
  public void testCreateLegalOval() {
    e2 = new Ellipse("O2", new RGB(255, 255, 255), 55, 15,
            300, 200, 1, 96);
    assertEquals("Name: O2\n"
                 + "Type: ellipse\n"
                 + "Center: (300.0,200.0), X radius: 55.0, Y "
                 + "radius: 15.0, Color: (255.0,255.0,255.0)\n"
                 + "Appears at t=1\n"
                 + "Disappears at t=96", e2.toString());
  }

  @Test
  public void testCreateLegalSquare() {
    s2 = new Square("S2", new RGB(10, 10, 10), 55, 55,
            0, 0, 10, 100);
    assertEquals("Name: S2\n"
                 + "Type: square\n"
                 + "Min corner: (0.0,0.0), Length: 55.0, Color: (10.0,10.0,10.0)\n"
                 + "Appears at t=10\n"
                 + "Disappears at t=100", s2.toString());
  }

  @Test
  public void testCreateLegalCircle() {
    c2 = new Circle("C2", new RGB(40, 178, 222), 30, 30,
            167, 0, 45, 49);
    assertEquals("Name: C2\n" + "Type: circle\n"
                 + "Center: (167.0,0.0), Radius: 30.0, Color: (40.0,178.0,222.0)\n"
                 + "Appears at t=45\n" + "Disappears at t=49", c2.toString());
  }

  @Test
  public void testCreateLegalTriangle() {
    t2 = new Triangle("T2", new RGB(15, 50, 125), 40, 81,
            4, 11, 11, 22);
    assertEquals("Name: T2\n" + "Type: triangle\n"
                 + "Min corner: (4.0,11.0), Width: 40.0, Height: 81.0, Color: (15.0,50.0,125.0)\n"
                 + "Appears at t=11\n" + "Disappears at t=22", t2.toString());
  }

  @Test
  public void testCreateLegalRhombus() {
    rh2 = new Rhombus("RH2", new RGB(102, 51, 98), 21, 44,
            20, 230, 25, 52);
    assertEquals("Name: RH2\n" + "Type: rhombus\n"
                 + "Min corner: (20.0,230.0), Width: 21.0, Height: 44.0, "
                 + "Color: (102.0,51.0,98.0)\n" + "Appears at t=25\n"
                 + "Disappears at t=52", rh2.toString());
  }

  @Test
  public void testShapesWithLongNumbers() {
    //highest RGB numbers, large position, large dimensions
    r2 = new Rectangle("R2", new RGB(255, 255, 255), 8000, 6000,
            200, 200, 1, 100);
    assertEquals("Name: R2\n" + "Type: rectangle\n"
                 + "Min corner: (200.0,200.0), Width: 8000.0, Height: 6000.0, "
                 + "Color: (255.0,255.0,255.0)\n" + "Appears at t=1\n"
                 + "Disappears at t=100", r2.toString());

    t2 = new Triangle("T2", new RGB(255, 255, 255), 25000, 30000,
            4000, 5023, 1, 50);
    assertEquals("Name: T2\n" + "Type: triangle\n"
                 + "Min corner: (4000.0,5023.0), Width: 25000.0, Height: 30000.0, "
                 + "Color: (255.0,255.0,255.0)\n" + "Appears at t=1\n"
                 + "Disappears at t=50", t2.toString());

    s2 = new Square("S2", new RGB(255, 255, 255), 11999, 11999,
            20000, 30000, 10, 100);
    assertEquals("Name: S2\n" + "Type: square\n"
                 + "Min corner: (20000.0,30000.0), Length: 11999.0, Color: (255.0,255.0,255.0)\n"
                 + "Appears at t=10\n" + "Disappears at t=100", s2.toString());

    //highest RGB numbers, large position, large dimensions, long time
    e2 = new Ellipse("C2", new RGB(255, 255, 255), 6023, 3001,
            5000, 10000, 6, 10000);
    assertEquals("Name: C2\n" + "Type: ellipse\n"
                 + "Center: (5000.0,10000.0), X radius: 6023.0, Y radius: 3001.0, "
                 + "Color: (255.0,255.0,255.0)\n" + "Appears at t=6\n"
                 + "Disappears at t=10000",
            e2.toString());

    rh2 = new Rhombus("RH2", new RGB(20, 20, 20), 50, 60,
            2, 2, 1, 80000);
    assertEquals("Name: RH2\n" + "Type: rhombus\n"
                 + "Min corner: (2.0,2.0), Width: 50.0, Height: 60.0, Color: (20.0,20.0,20.0)\n"
                 + "Appears at t=1\n" + "Disappears at t=80000", rh2.toString());

    c2 = new Circle("C2", new RGB(255, 255, 255), 30000, 30000,
            2073, 3250, 1, 10023);
    assertEquals("Name: C2\n" + "Type: circle\n"
                 + "Center: (2073.0,3250.0), Radius: 30000.0, Color: (255.0,255.0,255.0)\n"
                 + "Appears at t=1\n" + "Disappears at t=10023", c2.toString());
  }

  @Test
  public void testHighlyPreciseArgs() {
    r2 = new Rectangle("R2",
            new RGB(1.8738278238233284, 0.293282738942, 0.98283748289232),
            50.29871267374283, 100.87982379336470,
            200.7632290839923472, 200.9292839298928327, 1, 100);
    assertEquals("Name: R2\n" + "Type: rectangle\n"
                 + "Min corner: (200.8,200.9), Width: 50.3, Height: 100.9, Color: (1.9,0.3,1.0)\n"
                 + "Appears at t=1\n" + "Disappears at t=100", r2.toString());

    e2 = new Ellipse("O2", new RGB(100.283748378, 200.842734820, 10.1728387172),
            60.92837817303429883984829483, 30.987238482783782834274,
            500.823748278432934892920, 100.93829480929483287428, 6, 100);
    assertEquals("Name: O2\n"
                 + "Type: ellipse\n" + "Center: (500.8,100.9), X radius: 60.9, Y radius: 31.0, "
                 + "Color: (100.3,200.8,10.2)\n" + "Appears at t=6\n"
                 + "Disappears at t=100", e2.toString());

    c2 = new Circle("C2",
            new RGB(254.9999999999999999999, 254.9999999999999999999,
                    254.9999999999999999999),
            19238938.2938489284232893494, 19238938.2938489284232893494,
            0.8129398848932894938949283498, 200.9284928578758278329890, 1, 100);
    assertEquals("Name: C2\n" + "Type: circle\n"
                 + "Center: (0.8,200.9), Radius: 19238938.3, Color: (255.0,255.0,255.0)\n"
                 + "Appears at t=1\n" + "Disappears at t=100", c2.toString());
  }

  @Test
  public void testToString() {
    assertEquals("Name: I\n"
                 + "Type: circle\n" + "Center: (0.0,0.0), Radius: 10.0, Color: (1.0,1.0,1.0)\n"
                 + "Appears at t=1\n" + "Disappears at t=100", circle.toString());
    assertEquals("Name: T\n" + "Type: triangle\n"
                 + "Min corner: (40.0,50.0), Width: 20.0, Height: 30.0, Color: (5.0,5.0,5.0)\n"
                 + "Appears at t=1\n" + "Disappears at t=50", triangle.toString());
    assertEquals("Name: S\n" + "Type: square\n"
                 + "Min corner: (0.0,0.0), Length: 40.0, Color: (0.0,1.0,0.0)\n"
                 + "Appears at t=10\n" + "Disappears at t=100", square.toString());
    assertEquals("Name: R\n" + "Type: rectangle\n"
                 + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n"
                 + "Appears at t=1\n" + "Disappears at t=100", rectangle.toString());
    assertEquals("Name: H\n" + "Type: rhombus\n"
                 + "Min corner: (2.0,2.0), Width: 50.0, Height: 60.0, Color: (20.0,20.0,20.0)\n"
                 + "Appears at t=1\n" + "Disappears at t=80", rhombus.toString());
    assertEquals("Name: C\n" + "Type: ellipse\n"
                 +  "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n"
                 + "Appears at t=6\n"
                 + "Disappears at t=100", ellipse.toString());
  }

}