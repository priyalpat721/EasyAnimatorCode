package cs5004.animator.view;

import javax.swing.*;

public class Canvas extends JFrame{
  JPanel panel;
  JLabel label;
  JScrollPane scrollPane;
  int canvasWidth;
  int canvasHeight;

  public Canvas(double width, double height) {
    this.canvasWidth = (int) width;
    this.canvasHeight = (int) height;

    label = new JLabel();
    setTitle("Easy Animator");
    panel = new JPanel();
      setSize(canvasWidth, canvasHeight);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLocationRelativeTo(null);
      setVisible(true);

      panel.add(label);
      this.add(panel);

      scrollPane = new JScrollPane(); //parameters Jtext, JScrollPane.SCROLLBAR_AS_NEEDED wrap shapepanel in scrollpane
    }

    // buttons

  // setShapes -> pass through on shapepanel
  // repaint
}
