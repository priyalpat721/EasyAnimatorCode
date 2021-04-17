package cs5004.animator.view;

import java.awt.*;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.*;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;

import static cs5004.animator.utils.AnimationReader.parseFile;

// a view to test animation stuff
public class TestView2 {
  private IAnimatorModel model;
  private int speed;
  private int end;
  private Canvas canvas;
  private MyFrame frame;
  private ShapesPanel animation;
  private JScrollPane scrollPane;

  public TestView2(IAnimatorModel model, int speed) {
    this.model = model;
    this.model = model;

    // adds animation
    this.animation = new ShapesPanel(model.getShapesAtTicks(0));
    this.animation.setLayout(new BorderLayout());
    this.animation.setPreferredSize(new Dimension(model.getBox()[3] * 2,
            model.getBox()[3] * 2));
    scrollPane = new JScrollPane(this.animation);
    scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    this.frame = new MyFrame(model.getBox()[0], model.getBox()[1],
            model.getBox()[2] + 100, model.getBox()[3] + 100,
            model.getShapesAtTicks(0), animation, scrollPane);
    scrollPane.add(animation);
    this.frame.add(scrollPane);
    // this is for the buttons
    JPanel buttons = new JPanel();
    buttons.setLayout(new FlowLayout(FlowLayout.CENTER));

    JButton play = new JButton("Play");
    play.addActionListener(e -> this.play());
    buttons.add(play);

    JButton pause = new JButton("Pause");
    pause.addActionListener(e -> System.out.println("pause"));
    buttons.add(pause);

    JButton stop = new JButton("Stop");
    stop.addActionListener(e -> System.out.println("stop"));
    buttons.add(stop);

    JButton fast = new JButton("Speed up");
    fast.addActionListener(e -> System.out.println("fast forward"));
    buttons.add(fast);

    JButton slow = new JButton("Slow down");
    slow.addActionListener(e -> System.out.println("slow down"));
    buttons.add(slow);

    this.frame.add(buttons, BorderLayout.SOUTH);


//    this.canvas = new Canvas(model.getBox()[0], model.getBox()[1],
//            model.getBox()[2], model.getBox()[3], model.getShapesAtTicks(0));
//    this.canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    this.canvas.setVisible(false);
//
    this.speed = speed;
    this.end = model.getTotalTime()[1];
  }

  public void play() {
    System.out.println("play");
    this.frame.setComponentVisible(true);

    double count = 0;

    while (count < end) {
     // this.canvas.currentView(model.getShapesAtTicks(count));
      //this.frame.currentView(model.getShapesAtTicks(count));
      count += 1;
      //System.out.println("here");
//      try {
//        Thread.sleep(1000/this.speed);
//      } catch (InterruptedException e) {
//        Thread.currentThread().interrupt();
//      }
    }

    System.out.println("out");
  }

  public void makeVisible() {
    //this.canvas.setVisible(true);
    this.frame.setVisible(true);
  }

  // start the mouse listener in the canvas
  public void setCommandButtonListener(MouseListener mouseEvent) {
    //this.canvas.setCommandButtonListener(mouseEvent);
  }

  public static void main(String[] args) throws FileNotFoundException {
    AnimationBuilder<IAnimatorModel> builder = new Builder();

    Readable in = new FileReader("test/smalldemo.txt");

    IAnimatorModel model = parseFile(in, builder);

    TestView2 view2 = new TestView2(model, 5);

    view2.makeVisible();

    //view2.play();
  }

}
