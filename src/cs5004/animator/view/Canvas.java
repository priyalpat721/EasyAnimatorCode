package cs5004.animator.view;

import java.util.List;

import javax.swing.*;

import cs5004.animator.shape.IShape;

public class Canvas extends JFrame{
  ShapesPanel panel;
  JLabel label;
  JScrollPane scrollPane;
  int canvasWidth;
  int canvasHeight;

  public Canvas(double width, double height) {
    this.canvasWidth = 1000;
    this.canvasHeight = 1000;

    label = new JLabel();
    setTitle("Easy Animator");
    panel = new ShapesPanel();
      setSize(canvasWidth, canvasHeight);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLocationRelativeTo(null);
      setVisible(true);

      panel.add(label);
      this.add(panel);

      scrollPane = new JScrollPane(); //parameters Jtext, JScrollPane.SCROLLBAR_AS_NEEDED wrap shapepanel in scrollpane
    }

    public void setShapes(List<IShape> currentShapes) {
      for (IShape shape: currentShapes) {
        panel.setShapes(shape);
      }
      this.add(panel);
      this.repaint();
    }
    // buttons

  // setShapes -> pass through on shapepanel
  // repaint
}
