package cs5004.animator.controller;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import cs5004.animator.model.IAnimatorModel;

/**
 * Class for adding motion a shape via the GUI. It implements the MotionStrategy interface.
 */
public class MoveStrategy implements MotionStrategy {
  private JFrame motionFrame;
  private JPanel shape;
  private String name;
  private IAnimatorModel model;

  /**
   * Constructor for MoveStrategy.
   * @param motionFrame JFrame for the motion.
   * @param shape to be moved.
   * @param name name of the object.
   * @param model that contains shapes.
   */
  public MoveStrategy(JFrame motionFrame, JPanel shape, String name, IAnimatorModel model) {
    this.motionFrame = motionFrame;
    this.shape = shape;
    this.name = name;
    this.model = model;
  }

  /**
   * Method that applies the new motion to shape based on user input via GUI.
   */
  public void makeMotion() {

    JLabel xLabel = new JLabel("X:");
    JTextField xText = new JTextField();
    JLabel yLabel = new JLabel("Y:");
    JTextField yText = new JTextField();
    JLabel t1Label = new JLabel("T1:");
    JTextField t1 = new JTextField();
    JLabel t2Label = new JLabel("T2:");
    JTextField t2 = new JTextField();
    shape.add(xLabel);
    shape.add(xText);
    shape.add(yLabel);
    shape.add(yText);
    shape.add(t1Label);
    shape.add(t1);
    shape.add(t2Label);
    shape.add(t2);
    motionFrame.add(shape, BorderLayout.CENTER);
    JPanel buttons = new JPanel();
    buttons.setLayout(new FlowLayout());
    buttons.setBackground(Color.WHITE);
    JButton ok = new JButton("OK");
    buttons.add(ok);
    motionFrame.add(buttons, BorderLayout.SOUTH);

    ok.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        motionFrame.dispose();
        try {
          model.move(name, Integer.parseInt(xText.getText()), Integer.parseInt(yText.getText()),
                  Integer.parseInt(t1.getText()), Integer.parseInt(t2.getText()));
        } catch (Exception err) {
          JOptionPane.showMessageDialog(null, "Move overlap", "Error",
                  JOptionPane.ERROR_MESSAGE);
        }
      }
    });
  }
}
