import org.junit.Before;
import org.junit.Test;

import cs5004.animator.Circle;
import cs5004.animator.IShape;
import cs5004.animator.Oval;
import cs5004.animator.Rectangle;
import cs5004.animator.RGB;
import cs5004.animator.Rhombus;
import cs5004.animator.Shape;
import cs5004.animator.Square;
import cs5004.animator.Triangle;

import static org.junit.Assert.*;

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

  @Test
  public void testGetPosition() {
    assertEquals(200, rectangle.getPosition().getX(), 0.01);
    assertEquals(200, rectangle.getPosition().getY(), 0.01);
    assertEquals(500, oval.getPosition().getX(), 0.01);
    assertEquals(100, oval.getPosition().getY(), 0.01);
  }

  @Test
  public void testSetPosition() {
    rectangle.setPosition(100, 100);
    assertEquals(100, rectangle.getPosition().getX(), 0.01);
    assertEquals(100, rectangle.getPosition().getY(), 0.01);
  }

  @Test
  public void testGetColor() {
    assertEquals(1, rectangle.getColor().getRed(), 0.01);
    assertEquals(0, rectangle.getColor().getGreen(), 0.01);
    assertEquals(0, rectangle.getColor().getBlue(), 0.01);
  }

  @Test
  public void testSetColor() {
    rectangle.setColor(new RGB(2, 2, 2));
    assertEquals(2, rectangle.getColor().getRed(), 0.01);
    assertEquals(2, rectangle.getColor().getGreen(), 0.01);
    assertEquals(2, rectangle.getColor().getBlue(), 0.01);
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

  @Test
  public void testSetHeight() {
    rectangle.setHeight(40);
    oval.setHeight(50);
    assertEquals(40, rectangle.getHeight(), 0.01);
    assertEquals(50, oval.getHeight(), 0.01);
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

  @Test
  public void testGetLength() {
    assertEquals(40, square.getLength(), 0.01);
  }

  @Test
  public void testSetLength() {
    square.setLength(30);
    assertEquals(30, square.getLength(), 0.01);
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




}