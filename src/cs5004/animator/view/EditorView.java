package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.*;

import cs5004.animator.controller.AnimatorControllerImpl;
import cs5004.animator.controller.IAnimatorController;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;

import static cs5004.animator.utils.AnimationReader.parseFile;

public class EditorView implements ActionListener {
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
  private double count = -1;

  /**
   * Class that creates an interactive window-based visual layout of the animation.
   *
   * @param model of the animation.
   * @param speed intended speed for the animation.
   */

  public EditorView(IAnimatorModel model, int speed) {
    this.model = model;
    this.speed = speed;
    this.end = model.getTotalTime()[1];
    this.timer = new Timer(1000 / speed, this);

    this.animation = new ShapesPanel(model.getShapesAtTicks(0));
    this.animation.setLayout(new BorderLayout());
    this.animation.setPreferredSize(new Dimension(model.getBox()[2] * 2,
            model.getBox()[3] * 2));

    this.scrollPane = new JScrollPane(animation);
    this.scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    this.frame = new Frame(960, 720, animation, scrollPane);

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

    this.resume = new JButton("Stop");
    this.resume.setName("stop");
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

  public void restart() {
    System.out.println("EditorView -> restart");
    timer.isRepeats();
    count = -1;
  }

  public void stop() {
    System.out.println("EditorView -> stop");
    timer.stop();
    timer.setDelay(0);
  }

  public void loop() {
    System.out.println("EditorView -> loop");
    count = -1;
  }

  public void increaseSpeed() {
    System.out.println("EditorView -> increased speed");
    this.speed = this.speed + 1;
    System.out.println(this.speed);
  }

  public void decreaseSpeed() {
    System.out.println("EditorView -> decreased speed");
    if (speed -1 != 0) {
      speed -=1;
    }
    speed = 1;
  }

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

  public void setKeyListener(KeyAdapter keyEvent) {
    frame.addKeyListener(keyEvent);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.count += 1;
    System.out.println("Tick count: " + (int) count);
    this.frame.currentView(model.getShapesAtTicks(count));
  }

  /**
   * Main method for the EditorView. It coordinates between user input and model.
   *
   * @param args default parameter for main method.
   * @throws FileNotFoundException if readable not found.
   */
  public static void main(String[] args) throws FileNotFoundException {
    AnimationBuilder<IAnimatorModel> builder = new Builder();
    Readable in = new FileReader("test/smalldemo.txt");
    IAnimatorModel model = parseFile(in, builder);
    EditorView view = new EditorView(model, 1);

    IAnimatorController controller = new AnimatorControllerImpl(view);
    controller.go();
  }

}
