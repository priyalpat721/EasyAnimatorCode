package cs5004.animator.view;

import java.awt.*;
import javax.swing.*;

import cs5004.animator.shape.IShape;
import cs5004.animator.shape.Shape;


public class ShapesPanel extends JPanel  {
//  int seconds = 0;
  int x = 0;
  int y = 0;
  Color color;
  int width;
  int height;

  public ShapesPanel() {

  }

  public  void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.setBackground(new Color(255, 255, 255));

    Graphics2D g2D = (Graphics2D) g;

    g2D.setColor(color);
    g2D.fillRect(x, y, width, height);
  }

  public void setShapes(IShape shape) {
    if (shape.getType() == Shape.RECTANGLE) {
      x = (int) shape.getPosition().getX();
      y = (int) shape.getPosition().getY();
      color = Color.getHSBColor((float)shape.getColor().getRed(),
              (float)shape.getColor().getGreen(),
              (float)shape.getColor().getBlue());

      width = (int)shape.getWidth();
      height = (int)shape.getHeight();
    }
  }

//    seconds++;
//    System.out.println(seconds + " second/s have passed.");

//    if (seconds == 10) {
//      timer.stop();
//      System.out.println("Time's up!");
//    }
    //y = y + velocityY;

}

