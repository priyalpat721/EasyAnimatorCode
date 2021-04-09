package cs5004.animator.view;

import java.util.List;

import javax.swing.*;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.shape.IShape;

public class Canvas extends JFrame {
  ShapesPanel panel;
  JLabel label;
  JScrollPane scrollPane;

  // displaying the panel with the shapes
  public Canvas(int x, int y, double width, double height, IAnimatorModel model) {

    label = new JLabel();
    setTitle("Easy Animator");
    panel = new ShapesPanel();
    setSize((int) width * 2, (int) height * 2);
    setLocation((int) x, (int) y);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setVisible(true);

    panel.add(label);

    scrollPane = new JScrollPane(); //parameters Jtext, JScrollPane.SCROLLBAR_AS_NEEDED wrap shapepanel in scrollpane
  }

  public void currentView(List<IShape> currentShapes) {
    try {
      Thread.sleep(5);
      this.remove(panel);
      // gets the shapes in correct position and color
      panel.setShapes(currentShapes);
      this.add(panel);
    }
    catch (InterruptedException e) {

    }



  }
  // buttons

  // setShapes -> pass through on shapepanel
  // repaint
}
