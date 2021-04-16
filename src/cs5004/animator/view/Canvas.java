package cs5004.animator.view;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import cs5004.animator.shape.IShape;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * Class that creates the frame of the animation.
 * The class extends the JFrame class of Java Swing.
 */
public class Canvas extends JFrame {
  ShapesPanel panel;
  JScrollPane scrollPane;
  int width;
  int height;


  /**
   * Constructs Canvas objects that displays the panel with the shapes.
   *
   * @param x coordinate of the canvas on the screen.
   * @param y coordinate of the canvas on the screen.
   * @param width of the canvas window.
   * @param height of the canvas window.
   * @param model containing the list of shapes to show in the window.
   */
  public Canvas(double x, double y, double width, double height, List<IShape> model) {
    super("Easy Animator");
    this.width = (int) width;
    this.height = (int) height;
    setSize(this.width, this.height);
    setLocation((int) x, (int) y);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.panel = new ShapesPanel(model);
    this.panel.setPreferredSize(new Dimension((int) width * 2, (int) height * 2));
    scrollPane = new JScrollPane(panel);
    scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    this.setVisible(true);
    this.add(scrollPane);
    panel.setVisible(true);

    this.pack();
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(width + 10, height + 10);
  }

  /**
   * Method that places the shapes in correct position and color.
   * @param currentShapes list that contains shapes to be shown.
   */
  public void currentView(List<IShape> currentShapes) {
    this.panel.setShapes(currentShapes);
    this.panel.repaint();
  }

  // add a mouse listener to the panel
  public void setCommandButtonListener(MouseListener mouseEvent) {
    this.panel.addMouseListener(mouseEvent);
  }

}
