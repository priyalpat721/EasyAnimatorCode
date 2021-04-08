package cs5004.animator.utils;

import javax.swing.*;

public class VisualView extends JFrame {
  JPanel panel;
  JLabel label;


  public VisualView() {
    label = new JLabel();
    setTitle("Easy Animator");
    panel = new JPanel();

    setSize(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    panel.add(label);
    add(panel);

  }


  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new VisualView();
      }
    });
  }
}
