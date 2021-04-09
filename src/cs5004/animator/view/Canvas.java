package cs5004.animator.view;

import java.util.List;

import javax.swing.*;

import cs5004.animator.shape.IShape;

public class Canvas extends JFrame {
  ShapesPanel panel;
  JLabel label;
  JScrollPane scrollPane;

  // displaying the panel with the shapes
  public Canvas(int x, int y, double width, double height, List<IShape> model) {

    label = new JLabel();
    setTitle("Easy Animator");
    this.panel = new ShapesPanel(model);
    setSize((int) width * 2, (int) height * 2);
    setLocation(x, y);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
    panel.setVisible(true);
    this.add(this.panel);

    scrollPane = new JScrollPane(); //parameters Jtext, JScrollPane.SCROLLBAR_AS_NEEDED wrap shapepanel in scrollpane
  }

  public void currentView(List<IShape> currentShapes) {
    // gets the shapes in correct position and color
    panel.setShapes(currentShapes);
    this.repaint();
  }

  // buttons

  // setShapes -> pass through on shapepanel
  // repaint
}
