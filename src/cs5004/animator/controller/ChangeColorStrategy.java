package cs5004.animator.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.tools.RGB;

public class ChangeColorStrategy implements MotionStrategy {
  private JFrame motionFrame;
  private JPanel shape;
  private String name;
  private IAnimatorModel model;

  public ChangeColorStrategy(JFrame motionFrame, JPanel shape, String name, IAnimatorModel model) {
    this.motionFrame = motionFrame;
    this.shape = shape;
    this.name = name;
    this.model = model;
  }

  public void makeMotion() {
    JLabel redLabel = new JLabel("Red:");
    JTextField red = new JTextField();
    JLabel greenLabel = new JLabel("Green:");
    JTextField green = new JTextField();
    JLabel blueLabel = new JLabel("Blue:");
    JTextField blue = new JTextField();
    JLabel t1Label = new JLabel("T1:");
    JTextField t1 = new JTextField();
    JLabel t2Label = new JLabel("T2:");
    JTextField t2 = new JTextField();
    shape.add(redLabel);
    shape.add(red);
    shape.add(greenLabel);
    shape.add(green);
    shape.add(blueLabel);
    shape.add(blue);
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
          RGB color = new RGB(Integer.parseInt(red.getText()), Integer.parseInt(green.getText()),
                  Integer.parseInt(blue.getText()));
          model.changeColor(name, color, Integer.parseInt(t1.getText()), Integer.parseInt(t2.getText()));
        } catch (Exception err) {
          JOptionPane.showMessageDialog(null, "Color change overlap", "Error",
                  JOptionPane.ERROR_MESSAGE);
        }
      }
    });
  }
}
