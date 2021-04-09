package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.*;
import javax.swing.border.LineBorder;

import cs5004.animator.shape.IShape;
import cs5004.animator.shape.Shape;

public class ShapesPanel extends JPanel {
  private List<IShape> currentModel;

  // sole purpose is to make the shapes and color them
  public ShapesPanel() {
  }
  public void setShapes(List<IShape> model) {
    this.currentModel = model;
    setBackground(Color.WHITE);
    setLocation(0, 0);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;

    for (IShape shape : this.currentModel) {
      g2D.setColor(new Color((int) shape.getColor().getRed(), (int) shape.getColor().getBlue(),
              (int) shape.getColor().getGreen()));


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

