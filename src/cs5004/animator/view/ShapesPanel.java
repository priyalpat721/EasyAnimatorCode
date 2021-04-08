package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ShapesPanel extends JPanel  {
  int seconds = 0;
  int x = 0;
  int y = 0;
  int velocityX = 1;
  int velocityY = 1;

  public ShapesPanel() {

  }

  public  void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.setBackground(new Color(255, 255, 255));

    Graphics2D g2D = (Graphics2D) g;

    g2D.setColor(Color.getHSBColor(255, 0, 0));
    g2D.fillRect(200, 200, 50, 100);
  }


//    seconds++;
//    System.out.println(seconds + " second/s have passed.");

//    if (seconds == 10) {
//      timer.stop();
//      System.out.println("Time's up!");
//    }
    //y = y + velocityY;

}

