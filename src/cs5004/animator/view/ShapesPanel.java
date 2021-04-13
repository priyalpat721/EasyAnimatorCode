package cs5004.animator.view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import cs5004.animator.shape.IShape;
import cs5004.animator.shape.Shape;

/**
 * Class that creates the panel containing the animations.
 * This class extends the JComponent class of Java Swing.
 */
public class ShapesPanel extends JComponent {
  private List<IShape> currentModel;

  /**
   * Constructor that sets that creates the panel and sets its initial background to white.
   * @param model containing a list of shapes.
   */
  public ShapesPanel(List<IShape> model) {
    this.currentModel = model;
    setBackground(Color.WHITE);
  }

  /**
   * Constructor that creates the panel.
   * @param model containing a list of shapes.
   */
  public void setShapes(List<IShape> model) {
    this.currentModel = model;
  }


  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    if (currentModel == null) {
      return;
    }

    for (IShape shape : this.currentModel) {
      g2D.setColor(new Color((int) shape.getColor().getRed(), (int) shape.getColor().getGreen(),
              (int) shape.getColor().getBlue()));


      if (shape.getType() == Shape.RECTANGLE) {
        g2D.drawRect((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                (int) shape.getWidth(), (int) shape.getHeight());
        g2D.fillRect((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                (int) shape.getWidth(), (int) shape.getHeight());

      } else if (shape.getType() == Shape.ELLIPSE) {
        g2D.drawOval((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                (int) shape.getWidth(), (int) shape.getHeight());
        g2D.fillOval((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                (int) shape.getWidth(), (int) shape.getHeight());
      }
    }
  }

}

