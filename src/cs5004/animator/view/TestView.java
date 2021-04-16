package cs5004.animator.view;

import java.awt.event.MouseListener;

import javax.swing.*;

// a view to test stuff
public class TestView extends JFrame {
  private JPanel panel;
  private JLabel label;
  private JButton button1;
  private JButton button2;

  public TestView() {
    super();
    setTitle("Test");
    setSize(300, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    panel = new JPanel();

    label = new JLabel("Empty label");

    button1 = new JButton("Press");
    // to identify a button
    button1.setName("press");

    button2 = new JButton("Quit");
    // to identify a button
    button2.setName("quit");

    panel.add(label);
    panel.add(button1);
    panel.add(button2);

    add(panel);
  }

  public void makeVisible() {
    setVisible(true);
  }

  public void setText(String text) {
    label.setText(text);
  }

  public void setCommandButtonListener(MouseListener mouseEvent) {
    addMouseListener(mouseEvent);
    button1.addMouseListener(mouseEvent);
    button2.addMouseListener(mouseEvent);
  }


  public static void main(String[] args) {
    TestView view = new TestView();
    view.makeVisible();
  }


}
