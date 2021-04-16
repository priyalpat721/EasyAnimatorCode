package cs5004.animator.view;

import java.awt.event.MouseListener;

import javax.swing.*;

// a view to test
public class TestView extends JFrame {
  private JFrame frame;
  private JPanel panel;
  private JLabel label;
  private JButton button;

  public TestView() {
    frame = new JFrame("Test");
    frame.setSize(300, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    panel = new JPanel();

    label = new JLabel("Empty label");
    button = new JButton("Press this");
    button.setName("press");

    panel.add(this.label);
    panel.add(this.button);

    frame.add(panel);
  }

  public void makeVisible() {
    frame.setVisible(true);
  }

  public void setText(String text) {
    label.setText(text);
  }

  public void setCommandButtonListener(MouseListener mouseEvent) {
    button.addMouseListener(mouseEvent);
  }

  public static void main(String[] args) {
    TestView view = new TestView();
    view.makeVisible();
  }


}
