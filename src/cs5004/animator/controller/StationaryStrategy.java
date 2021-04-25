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
 * Class for representing a static shape via the GUI. It implements the MotionStrategy interface.
 */
public class StationaryStrategy implements MotionStrategy {
  private JFrame motionFrame;
  private JPanel shape;
  private String name;
  private IAnimatorModel model;

  /**
   * Constructor for StationaryStrategy.
   * @param motionFrame JFrame for the motion.
   * @param shape to be kept stationary.
   * @param name of the shape.
   * @param model containing the shapes.
   */
  public StationaryStrategy(JFrame motionFrame, JPanel shape, String name, IAnimatorModel model) {
    this.motionFrame = motionFrame;
    this.shape = shape;
    this.name = name;
    this.model = model;
  }

  /**
   * Method that applies the new motion(maintaining a shape fixed) based on user input via GUI.
   */
  public void makeMotion() {
    JLabel t1Label = new JLabel("T1:");
    JTextField t1 = new JTextField();
    JLabel t2Label = new JLabel("T2:");
    JTextField t2 = new JTextField();

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
          model.stay(name, Integer.parseInt(String.valueOf(Integer.parseInt(t1.getText()))),
                  Integer.parseInt(t2.getText()));
        } catch (Exception err) {
          JOptionPane.showMessageDialog(null, "Stationary overlap", "Error",
                  JOptionPane.ERROR_MESSAGE);
        }
      }
    });
  }
}
