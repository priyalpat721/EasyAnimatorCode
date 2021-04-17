package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.*;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;

import static cs5004.animator.utils.AnimationReader.parseFile;

public class EditorView {
  private IAnimatorModel model;
  private int speed;
  private int end;
  private MyFrame frame;
  private ShapesPanel animation;
  private JScrollPane scrollPane;
  private JButton play;
  private JButton pause;
  private JButton resume;
  private JButton restart;
  private JButton loop;
  private JButton increaseSpeed;
  private JButton decreaseSpeed;

  public EditorView(IAnimatorModel model, int speed) {
    this.model = model;
    this.speed = speed;
    this.end = model.getTotalTime()[1];

    // adds animation
//    this.animation = new ShapesPanel(model.getShapesAtTicks(0));
//    this.animation.setLayout(new BorderLayout());
//    this.animation.setPreferredSize(new Dimension(model.getBox()[3] * 2,
//            model.getBox()[3] * 2));
//    scrollPane = new JScrollPane(this.animation);
//    scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//    scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    this.animation = new ShapesPanel(model.getShapesAtTicks(0));
    this.animation.setLayout(new BorderLayout());
    this.animation.setPreferredSize(new Dimension( model.getBox()[2] * 2,
            model.getBox()[3] * 2));

    this.scrollPane = new JScrollPane(animation);
    this.scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    this.frame = new MyFrame(model.getBox()[0], model.getBox()[1],
            960, 720, animation, scrollPane);
    //scrollPane.add(animation);
    //this.frame.add(scrollPane);
    // this is for the buttons
    this.frame.setVisible(false);
    this.frame.add(scrollPane);
    this.animation.setVisible(true);
    this.scrollPane.setVisible(true);
    this.frame.add(scrollPane, BorderLayout.CENTER);

    JPanel buttons = new JPanel();
    buttons.setLayout(new FlowLayout(FlowLayout.CENTER));

    //this.frame.currentView(model.getShapesAtTicks(10));
    this.play = new JButton("Play");
    this.play.setName("play");
    buttons.add(play);

    this.frame.add(buttons, BorderLayout.SOUTH);
  }

  public void play() {
    double count = 0;

    while (count < end ) {
      frame.currentView(model.getShapesAtTicks(count));
      count += 1;
      System.out.println("here");
      try {
        Thread.sleep(100/speed);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    System.out.println("out");
  }

  public void makeVisible() {
    frame.setVisible(true);
  }

  public void setCommandButtonListener(MouseListener mouseEvent) {
    frame.setCommandButtonListener(mouseEvent);
    play.addMouseListener(mouseEvent);
  }


  public static void main(String[] args) throws FileNotFoundException {
    AnimationBuilder<IAnimatorModel> builder = new Builder();

    Readable in = new FileReader("test/smalldemo.txt");

    IAnimatorModel model = parseFile(in, builder);

    EditorView view = new EditorView(model, 1);

    view.makeVisible();

    view.play();
  }


  //  @Override
//  public void actionPerformed(ActionEvent e) {
//  // This is where the timer would come into play
//    // each unit from timer causes this to run
//    if (e.getSource().equals(play)) {
//      //System.out.println("play");
//      double count = 0;
//
//      while (count < end ) {
//        // this.canvas.currentView(model.getShapesAtTicks(count));
//        this.frame.currentView(model.getShapesAtTicks(count));
//        System.out.println(model.getShapesAtTicks(count));
//        count += 1;
//        System.out.println("here");
//        try {
//          Thread.sleep(this.speed);
//        } catch (InterruptedException t) {
//          Thread.currentThread().interrupt();
//        }
//      }
//      System.out.println("out");
//    }
//  }

}