package cs5004.animator.view;

import java.util.List;

import javax.swing.*;

import cs5004.animator.shape.IShape;

public class Canvas extends JFrame {
  ShapesPanel panel;
  JScrollPane scrollPane;

  // displaying the panel with the shapes
  public Canvas(int x, int y, double width, double height, List<IShape> model) {
    super("Easy Animator");
    setSize((int) width * 2, (int) height * 2);
    setLocation(x, y);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.panel = new ShapesPanel(model);
    this.setVisible(true);
    this.add(this.panel);
    panel.setVisible(true);


    //parameters Jtext, JScrollPane.SCROLLBAR_AS_NEEDED wrap shape panel in scroll pane
    //scrollPane = new JScrollPane();
  }

  public void currentView(List<IShape> currentShapes) {
    // gets the shapes in correct position and color
    panel.setShapes(currentShapes);
    this.repaint();
  }
}
