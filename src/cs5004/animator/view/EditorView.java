package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.*;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;

import static cs5004.animator.utils.AnimationReader.parseFile;

public class EditorView implements ActionListener {
//public class EditorView {
  private IAnimatorModel model;
  private int speed;
  private int end;
  private Frame frame;
  private ShapesPanel animation;
  private JScrollPane scrollPane;
  private JButton play;
  private JButton pause;
  private JButton resume;
  private JButton restart;
  private JButton loop;
  private JButton increaseSpeed;
  private JButton decreaseSpeed;

  private Timer timer;

  /**
   * Class that creates an interactive window-based visual layout of the animation.
   * @param model of the animation.
   * @param speed intended speed for the animation.
   */

  public EditorView(IAnimatorModel model, int speed) {
    this.model = model;
    this.speed = speed;
    this.end = model.getTotalTime()[1];
    this.timer = new Timer(1000, this);
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

    this.frame = new Frame(960, 720, animation, scrollPane);
    //scrollPane.add(animation);
    //this.frame.add(scrollPane);
    // this is for the buttons
    this.frame.add(scrollPane);
    this.animation.setVisible(true);
    this.scrollPane.setVisible(true);
    this.frame.add(scrollPane, BorderLayout.CENTER);
    this.frame.setVisible(true);


    JPanel buttons = new JPanel();
    buttons.setLayout(new FlowLayout(FlowLayout.CENTER));

    this.play = new JButton("Play");
    this.play.setName("play");
    buttons.add(play);

    this.pause = new JButton("Pause");
    this.pause.setName("pause");
    buttons.add(pause);

    this.resume = new JButton("Resume");
    this.resume.setName("resume");
    buttons.add(resume);

    this.restart = new JButton("Restart");
    this.restart.setName("restart");
    buttons.add(restart);

    this.loop = new JButton("Loop");
    this.loop.setName("loop");
    buttons.add(loop);

    this.increaseSpeed = new JButton("Speed+");
    this.increaseSpeed.setName("increaseSpeed");
    buttons.add(increaseSpeed);

    this.decreaseSpeed = new JButton("Speed-");
    this.decreaseSpeed.setName("decreaseSpeed");
    buttons.add(decreaseSpeed);

    this.play.setActionCommand("play");
//    this.play.addActionListener(this);

    this.frame.add(buttons, BorderLayout.SOUTH);
  }


  public void play() {
    System.out.println("EditorView -> play");
    timer.start();
  }

  public void pause() {
    System.out.println("EditorView -> pause");
    timer.stop();
  }

  /**
   * Method that makes frame visible.
   */
  public void makeVisible() {
    frame.setVisible(true);
  }

  public void setCommandButtonListener(MouseListener mouseEvent) {
    frame.setCommandButtonListener(mouseEvent);
    play.addMouseListener(mouseEvent);
    pause.addMouseListener(mouseEvent);
    resume.addMouseListener(mouseEvent);
    restart.addMouseListener(mouseEvent);
    loop.addMouseListener(mouseEvent);
    increaseSpeed.addMouseListener(mouseEvent);
    decreaseSpeed.addMouseListener(mouseEvent);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
      double count = 0;

      while (count < end) {
        System.out.println("here " + (int) count);
        this.frame.currentView(model.getShapesAtTicks(count));
        count += 1;
        try {
          Thread.sleep(1000 / speed);
        } catch (InterruptedException t) {
          Thread.currentThread().interrupt();
        }
      }
  }

  /**
   * Main method for the EditorView. It coordinates between user input and model.
   * @param args default parameter for main method.
   * @throws FileNotFoundException if readable not found.
   */
  public static void main(String[] args) throws FileNotFoundException {
    AnimationBuilder<IAnimatorModel> builder = new Builder();
    Readable in = new FileReader("test/smalldemo.txt");
    IAnimatorModel model = parseFile(in, builder);
    EditorView view = new EditorView(model, 1);
    view.makeVisible();
//    view.setCommandButtonListener(new MouseHandler(view));
    view.play();

//    IAnimatorController controller = new AnimatorControllerImpl(view);
//    controller.go();
  }

}
