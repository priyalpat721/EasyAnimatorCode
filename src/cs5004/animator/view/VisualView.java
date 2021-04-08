package cs5004.animator.view;


import static cs5004.animator.utils.AnimationReader.parseFile;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import javax.swing.*;
import java.awt.*;

public class VisualView extends JFrame {
  JPanel panel;
  JLabel label;
  JScrollPane scrollPane;
  int panelWidth = 500;
  int panelHeight = 500;
  int ticksPerSecond;
  Timer timer;


  public VisualView() {
    // window setup
    label = new JLabel();
    setTitle("Easy Animator");
    panel = new JPanel();

    // panel setup
    setSize(panelWidth, panelHeight);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    setVisible(true);
    timer = new Timer(ticksPerSecond, null);
    panel.add(label);
    add(panel);

    scrollPane = new JScrollPane(); //parameters Jtext, JScrollPane.SCROLLBAR_AS_NEEDED
  }

  public void buildVisualView(IAnimatorModel model) {

  }

  public static void main(String args[]) throws FileNotFoundException {
    AnimationBuilder<IAnimatorModel> builder = new Builder();
//        var fileName = "src/cs5004/animator/demo.txt";
//        Readable in = new FileReader(fileName);
//        IAnimatorModel animation = parseFile(in, builder);


    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        VisualView visual = new VisualView();
//        visual.buildVisualView(animation);
      }
    });
  }
}
