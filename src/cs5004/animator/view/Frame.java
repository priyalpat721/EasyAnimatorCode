package cs5004.animator.view;

import java.awt.*;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.*;

import cs5004.animator.shape.IShape;

/**
 * Class that creates the frame of the animation. The class extends the JFrame class of Java Swing.
 */
public class Frame extends JFrame {
  ShapesPanel animation;
  JScrollPane scrollPane;
  JMenuBar menu;
  JMenu file;
  JMenuItem exit;
  int width;
  int height;

  /**
   * Constructs Canvas objects that displays the panel with the shapes.
   *

   * @param width  of the canvas window.
   * @param height of the canvas window.
   */
  public Frame(double width, double height,
               ShapesPanel animation, JScrollPane scrollPane) {
    super("Easy Animator");
    this.width = (int) width;
    this.height = (int) height;

    this.setSize(this.width, this.height);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.animation = animation;
    this.scrollPane = scrollPane;

    // adds menu option
    this.menu = new JMenuBar();
    this.file = new JMenu("File");
    this.exit = new JMenuItem("Exit");
    this.exit.setName("exit");
    this.file.add(exit);

    this.menu.add(file);
    this.setJMenuBar(menu);

    this.pack();
    this.setVisible(false);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(width + 1, height + 1);
  }

  public void currentView(List<IShape> currentShapes) {

    this.animation.setShapes(currentShapes);
    this.animation.repaint();
  }

  public void setCommandButtonListener(MouseListener mouseEvent) {
    this.exit.addMouseListener(mouseEvent);
  }

}
