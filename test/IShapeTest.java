import org.junit.Before;
import org.junit.Test;

import cs5004.shape.Circle;
import cs5004.shape.IShape;
import cs5004.shape.Oval;
import cs5004.shape.Rectangle;
import cs5004.shape.RGB;
import cs5004.shape.Rhombus;
import cs5004.shape.Shape;
import cs5004.shape.Square;
import cs5004.shape.Triangle;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the IShape interface.
 */
public class IShapeTest {
  private IShape rectangle;
  private IShape oval;
  private IShape square;
  private IShape circle;
  private IShape triangle;
  private IShape rhombus;

  @Before
  public void setUp() {
    rectangle = new Rectangle("R", new RGB(1, 0, 0), 50, 100,
            200, 200, 1, 100);

    oval = new Oval("C", new RGB(0, 0, 1), 60, 30,
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

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCircle() {
    IShape c1 = new Circle("I", new RGB(1, 1, 1), 20, 10,
            0, 0, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSquare() {
    IShape s1 = new Square("I", new RGB(1, 1, 1), 30, 40,
            0, 0, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalNameEmpty() {
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
    assertEquals("C", oval.getName());
    assertEquals("S", square.getName());
  }

  @Test
  public void testGetType() {
    assertEquals(Shape.RECTANGLE, rectangle.getType());
    assertEquals(Shape.OVAL, oval.getType());
    assertEquals(Shape.SQUARE, square.getType());
    assertEquals(Shape.CIRCLE, circle.getType());
    assertEquals(Shape.TRIANGLE, triangle.getType());
    assertEquals(Shape.RHOMBUS, rhombus.getType());
  }

  @Test
  public void testGetTotalTime() {
    assertEquals(1, rectangle.getTotalTime().getStartTime());
    assertEquals(100, rectangle.getTotalTime().getEndTime());
    assertEquals(6, oval.getTotalTime().getStartTime());
    assertEquals(100, oval.getTotalTime().getEndTime());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalTime() {
    IShape r1 = new Rectangle("R", new RGB(1, 0, 0), 50, 100,
            200, 200, 20, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalStartTimeNegative() {
    IShape r1 = new Rectangle("R", new RGB(1, 0, 0), 50, 100,
            200, 200, -1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalStartTimeZero() {
    IShape r1 = new Rectangle("R", new RGB(1, 0, 0), 50, 100,
            200, 200, 0, 100);
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
    assertEquals(500, oval.getPosition().getX(), 0.01);
    assertEquals(100, oval.getPosition().getY(), 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalX() {
    IShape r1 = new Rectangle("R", new RGB(1, 0, 0), 50, 100,
            -200, 200, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalY() {
    IShape r1 = new Rectangle("R", new RGB(1, 0, 0), 50, 100,
            200, -200, 1, 100);
  }

  @Test
  public void testSetPosition() {
    rectangle.setPosition(100, 100);
    assertEquals(100, rectangle.getPosition().getX(), 0.01);
    assertEquals(100, rectangle.getPosition().getY(), 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSetPositionX() {
    rectangle.setPosition(-100, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSetPositionY() {
    rectangle.setPosition(100, -100);
  }

  @Test
  public void testGetColor() {
    assertEquals(1, rectangle.getColor().getRed(), 0.01);
    assertEquals(0, rectangle.getColor().getGreen(), 0.01);
    assertEquals(0, rectangle.getColor().getBlue(), 0.01);
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
    assertEquals(60, oval.getWidth(), 0.01);
    assertEquals(20, triangle.getWidth(), 0.01);
    assertEquals(50, rhombus.getWidth(), 0.01);
  }

  @Test
  public void testGetHeight() {
    assertEquals(100, rectangle.getHeight(), 0.01);
    assertEquals(30, oval.getHeight(), 0.01);
    assertEquals(30, triangle.getHeight(), 0.01);
    assertEquals(60, rhombus.getHeight(), 0.01);
  }

  @Test
  public void testSetWidth() {
    rectangle.setWidth(20);
    oval.setWidth(30);
    assertEquals(20, rectangle.getWidth(), 0.01);
    assertEquals(30, oval.getWidth(), 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSetWidth() {
    rectangle.setWidth(0);
  }

  @Test
  public void testSetHeight() {
    rectangle.setHeight(40);
    oval.setHeight(50);
    assertEquals(40, rectangle.getHeight(), 0.01);
    assertEquals(50, oval.getHeight(), 0.01);
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
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSetRadius() {
    circle.setRadius(-10);
  }

  @Test
  public void testGetLength() {
    assertEquals(40, square.getLength(), 0.01);
  }

  @Test
  public void testSetLength() {
    square.setLength(30);
    assertEquals(30, square.getLength(), 0.01);
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
  public void testToString() {
    assertEquals("""
       Name: I
       Type: Circle
       Center: (0.0,0.0), Radius: 10.0, Color: (1.0,1.0,1.0)
       Appears at t=1
       Disappears at t=100""", circle.toString());
    assertEquals("""
        Name: T
        Type: Triangle
        Min corner: (40.0,50.0), Width: 20.0, Height: 30.0, Color: (5.0,5.0,5.0)
        Appears at t=1
        Disappears at t=50""", triangle.toString());
    assertEquals("""
        Name: S
        Type: Square
        Min corner: (0.0,0.0), Length: 40.0, Color: (0.0,1.0,0.0)
        Appears at t=10
        Disappears at t=100""", square.toString());
    assertEquals("""
        Name: R
        Type: Rectangle
        Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)
        Appears at t=1
        Disappears at t=100""", rectangle.toString());
    assertEquals("""
        Name: H
        Type: Rhombus
        Min corner: (2.0,2.0), Width: 50.0, Height: 60.0, Color: (20.0,20.0,20.0)
        Appears at t=1
        Disappears at t=80""", rhombus.toString());
    assertEquals("""
        Name: C
        Type: Oval
        Min corner: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)
        Appears at t=6
        Disappears at t=100""", oval.toString());
  }

}