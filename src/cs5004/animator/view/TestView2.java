package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.*;

import cs5004.animator.controller.MouseHandler;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;

import static cs5004.animator.utils.AnimationReader.parseFile;

// a view to test animation stuff
public class TestView2 extends MouseAdapter implements ActionListener {
  private IAnimatorModel model;
  private int speed;
  private int end;
  //private Canvas canvas;
  private MyFrame frame;
  private ShapesPanel animation;
  private JScrollPane scrollPane;
  private JButton play;

  public TestView2(IAnimatorModel model, int speed) {
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
            model.getBox()[3]  * 2));
    scrollPane = new JScrollPane(animation);
    scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    this.frame = new MyFrame(model.getBox()[0], model.getBox()[1],
            model.getBox()[2] + 100, model.getBox()[3] + 100, animation, scrollPane);
    //scrollPane.add(animation);
    //this.frame.add(scrollPane);
    // this is for the buttons
    this.frame.setVisible(true);
    this.frame.add(scrollPane);
    animation.setVisible(true);
    scrollPane.setVisible(true);
    this.frame.add(scrollPane, BorderLayout.CENTER);
    JPanel buttons = new JPanel();
    buttons.setLayout(new FlowLayout(FlowLayout.CENTER));


    //this.frame.currentView(model.getShapesAtTicks(10));
    play = new JButton("Play");
    buttons.add(play);

    JButton pause = new JButton("Pause");
    buttons.add(pause);

    JButton stop = new JButton("Stop");
    buttons.add(stop);

    JButton fast = new JButton("Speed up");
    buttons.add(fast);

    JButton slow = new JButton("Slow down");
    buttons.add(slow);

    this.frame.add(buttons, BorderLayout.SOUTH);

    play.addActionListener(this);
    pause.addActionListener(e -> System.out.println("pause"));
    stop.addActionListener(e -> System.out.println("stop"));
    fast.addActionListener(e -> System.out.println("speed up"));
    slow.addActionListener(e -> System.out.println("slow down"));

    play.addMouseListener(new MouseHandler(this));
    pause.addMouseListener(new MouseHandler(this));
    stop.addMouseListener(new MouseHandler(this));
    fast.addMouseListener(new MouseHandler(this));
    slow.addMouseListener(new MouseHandler(this));

//    this.canvas = new Canvas(model.getBox()[0], model.getBox()[1],
//            model.getBox()[2], model.getBox()[3], model.getShapesAtTicks(0));
//    this.canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    this.canvas.setVisible(false);
//
  }

  public void play() {

    double count = 0;

    while (count < end) {
     // this.canvas.currentView(model.getShapesAtTicks(count));
      this.frame.currentView(model.getShapesAtTicks(count));
      count += 1;
      //System.out.println("here");
      try {
        Thread.sleep(1000/this.speed);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
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
    this.frame.setCommandButtonListener(mouseEvent);
  }

//  @Override
//  public void mouseClicked(MouseEvent e) {
//    if (e.getSource().equals(play)) {
//      System.out.printf(">> click -> (%d, %d)%n", e.getX(), e.getY());
//    }
//  }

  @Override
  public void actionPerformed(ActionEvent e) {
  // This is where the timer would come into play
    // each unit from timer causes this to run
    if (e.getSource().equals(play)) {
      //System.out.println("play");
      double count = 0;

      while (count < end ) {
        // this.canvas.currentView(model.getShapesAtTicks(count));
        this.frame.currentView(model.getShapesAtTicks(count));
        System.out.println(model.getShapesAtTicks(count));
        count += 1;
        System.out.println("here");
        try {
          Thread.sleep(this.speed);
        } catch (InterruptedException t) {
          Thread.currentThread().interrupt();
        }
      }
      System.out.println("out");
    }
  }

  public static void main(String[] args) throws FileNotFoundException {
    AnimationBuilder<IAnimatorModel> builder = new Builder();

    Readable in = new FileReader("test/smalldemo.txt");

    IAnimatorModel model = parseFile(in, builder);

    TestView2 view2 = new TestView2(model, 1);

    view2.makeVisible();

    //view2.play();
  }


}
