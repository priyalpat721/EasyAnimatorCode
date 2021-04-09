package cs5004.animator.view;

import java.awt.*;

import javax.swing.*;

import cs5004.animator.shape.IShape;
import cs5004.animator.shape.Shape;

public class RectangleGraphic extends JPanel {
  int x = 0;
  int y = 0;
  Color color;
  int width;
  int height;
  int r;
  int g;
  int b;

  public RectangleGraphic() {
  }

  public  void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.setBackground(new Color(255, 255, 255));

    Graphics2D g2D = (Graphics2D) g;
    this.color = new Color(this.getR(), this.getG(), this.getB());
    g2D.setColor(color);
    g2D.drawRect(x, y, width, height);
    g2D.fillRect(x, y, width, height);
  }

  public void setShapes(IShape shape) {
    if (shape.getType() == Shape.RECTANGLE) {
      x = (int) shape.getPosition().getX();
      y = (int) shape.getPosition().getY();
      r = (int)shape.getColor().getRed();
      g = (int)shape.getColor().getGreen();
      b = (int)shape.getColor().getBlue();
      width = (int)shape.getWidth();
      height = (int)shape.getHeight();

      System.out.println("X: " + x + " Y: " + y);

    }
  }

  public int getR() {
    return r;
  }

  public int getG() {
    return g;
  }

  public int getB() {
    return b;
  }
}
