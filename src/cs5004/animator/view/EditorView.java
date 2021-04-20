package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.EventListener;

import javax.swing.*;

import cs5004.animator.controller.AnimatorControllerImpl;
import cs5004.animator.controller.IAnimatorController;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;

import static cs5004.animator.utils.AnimationReader.parseFile;

public class EditorView implements IAnimatorView, ActionListener {
  private IAnimatorModel model;
  private int speed;
  private int initialSpeed;
  private int end;
  private Frame frame;
  private ShapesPanel animation;
  private JScrollPane scrollPane;
  private JPanel buttons;
  private JButton play;
  private JButton pause;
  private JButton resume;
  private JButton restart;
  private JButton loop;
  private JButton increaseSpeed;
  private JButton decreaseSpeed;
  private JCheckBox checkLoop;
  private Timer timer;
  private double count = -1;
  private boolean animate = false;

  /**
   * Class that creates an interactive window-based visual layout of the animation.
   */
  public EditorView() {
    buttons = new JPanel();
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

    this.checkLoop = new JCheckBox("Looping enabled", false);
    this.removeMouseListener(checkLoop);
    buttons.add(checkLoop);
  }

  @Override
  public void create(IAnimatorModel model, int speed) {
    this.model = model;
    this.speed = speed;
    this.initialSpeed = speed;
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
    this.frame.add(buttons, BorderLayout.SOUTH);
  }

  public void play() {
    speed = initialSpeed;
    timer.setDelay(1000 / speed);
    count = -1;
    timer.start();
    this.animate = false;
  }

  public void pause() {
    timer.stop();
  }

  public void restart() {
    timer.stop();
    speed = initialSpeed;
    timer.setDelay(1000 / speed);
    count = -1;
    timer.start();
    this.animate = false;
    this.checkLoop.setSelected(false);
  }

  public void resume() {
    timer.start();
    timer.setDelay(1000 / speed);
  }

  public void loop() {
    if (!this.animate) {
      timer.start();
      this.animate = true;
      this.checkLoop.setSelected(true);
    } else {
      timer.start();
      this.animate = false;
      timer.setDelay(1000 / speed);
      this.checkLoop.setSelected(false);
    }
  }

  public void increaseSpeed() {
    speed = speed + 1;
    timer.setDelay(1000 / speed);
  }

  public void decreaseSpeed() {
    if (speed - 1 <= 0) {
      speed = 1;
    } else {
      speed -= 1;
    }
    timer.setDelay(1000 / this.speed);
  }

  public void makeVisible() {
    frame.setVisible(true);
  }

  public void setCommandButtonListener(MouseListener mouseEvent, KeyListener keyEvent) {
    frame.setCommandButtonListener(mouseEvent);
    play.addMouseListener(mouseEvent);
    pause.addMouseListener(mouseEvent);
    resume.addMouseListener(mouseEvent);
    restart.addMouseListener(mouseEvent);
    loop.addMouseListener(mouseEvent);
    increaseSpeed.addMouseListener(mouseEvent);
    decreaseSpeed.addMouseListener(mouseEvent);
    frame.addKeyListener(keyEvent);
  }

  public void setFocus() {
    frame.requestFocus();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.count += 1;
    if (this.animate) {
      this.count = count % end;
    }
    System.out.println("Tick count: " + (int) count);
    this.frame.currentView(model.getShapesAtTicks(count));
  }

  private void removeMouseListener(JComponent event) {
    EventListener[] listeners = event.getListeners(MouseListener.class);
    for (EventListener listener : listeners)
      event.removeMouseListener((MouseListener) listener);
  }


//  public static void main(String[] args) throws FileNotFoundException {
//    AnimationBuilder<IAnimatorModel> builder = new Builder();
//    Readable in = new FileReader("test/smalldemo.txt");
//    IAnimatorModel model = parseFile(in, builder);
//    EditorView view = new EditorView();
//
//    IAnimatorController controller = new AnimatorControllerImpl(model, view, 1);
//    controller.go();
//  }
}
