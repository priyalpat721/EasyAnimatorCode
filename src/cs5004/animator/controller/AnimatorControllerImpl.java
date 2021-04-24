package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Timer;
import javax.swing.JCheckBox;

import cs5004.animator.view.Canvas;
import cs5004.animator.view.Frame;
import cs5004.animator.view.IAnimatorView;
import cs5004.animator.view.PlayBack;
import cs5004.animator.action.IAction;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.shape.IShape;

import static cs5004.animator.tools.Helpers.createFile;
import static cs5004.animator.tools.Helpers.getSpeed;
import static cs5004.animator.tools.Helpers.parseCommands;
import static cs5004.animator.tools.Helpers.showMessage;

/**
 * Class that implements the IAnimatorController interface. It represents the operations managed by
 * EasyAnimator and enables user to control animations manually.
 */
public class AnimatorControllerImpl implements IAnimatorController {
  private List modelData;
  private final IAnimatorModel model;
  private IAnimatorView view;
  private PlayBack playback;
  private int speed;
  private String result;
  private int[] box;
  private int initialSpeed;
  private Frame frame;
  private Timer timer;
  private int count;
  private boolean loop;
  private int endTime;
  private JCheckBox checkLoop;

  private String output;
  private String viewType;

  /**
   * Constructor for the Animator controller.
   * @param args command-line arguments
   * @param model of the animation.
   * @param view type of view for the animation.
   */
  public AnimatorControllerImpl(String[] args, IAnimatorModel model, IAnimatorView view) {
    String[] commands = parseCommands(args);
    this.model = model;
    this.view = view;
    this.endTime = model.getTotalTime()[1];
    this.count = 0;
    this.viewType = commands[1];
    this.output = commands[2];
    this.speed = getSpeed(commands[3]);
    this.initialSpeed = speed;

//    this.viewType = "playback";
//    this.speed = 10;
//    this.initialSpeed = speed;
//    this.output = "";
  }

  @Override
  public String getTextView() {
    this.modelData = new LinkedList<>();
    this.result = model.toString(speed);
    this.modelData.add(result);
    view.create(modelData);
    return (String) view.generate();
  }

  @Override
  public String getSVGView() {
    modelData = new LinkedList<>();
    HashMap<String, List<IAction>> logOfAction = model.getLogOfActions();
    List<IShape> logOfShapes = model.getLogOfShapes();
    this.box = model.getBox();
    this.modelData.add(logOfAction);
    this.modelData.add(logOfShapes);
    this.modelData.add(box);
    this.modelData.add(speed);
    view.create(modelData);
    this.result = (String) view.generate();
    return result;
  }

  @Override
  public void getVisualView() {
    modelData = new LinkedList<>();
    double x = model.getBox()[0];
    double y = model.getBox()[1];
    double width = model.getBox()[2];
    double height = model.getBox()[3];
    List<IShape> listOfShapes = model.getShapesAtTicks(0);
    modelData.add(x);
    modelData.add(y);
    modelData.add(width);
    modelData.add(height);
    modelData.add(listOfShapes);
    view.create(modelData);
    Canvas canvas = (Canvas) view.generate();

    this.timer = new Timer(1000 / speed, new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        canvas.currentView(model.getShapesAtTicks(count));
        count++;
      }
    });

    timer.start();
  }

  @Override
  public void getPlayBackView() {
    playback = (PlayBack) view;
    modelData = new LinkedList<>();
    double width = model.getBox()[2];
    double height = model.getBox()[3];
    List<IShape> listOfShapes = model.getShapesAtTicks(0);
    modelData.add(width);
    modelData.add(height);
    modelData.add(listOfShapes);
    this.loop = false;
    this.initialSpeed = speed;
    this.checkLoop = playback.getCheckLoop();
    playback.create(modelData);
    this.frame = (Frame) playback.generate();

    this.timer = new Timer(1000 / speed, new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        frame.currentView(model.getShapesAtTicks(count));
        count++;
        if (loop) {
          if (count == endTime) {
            count = 0;
            timer.setDelay(1000 / initialSpeed);
          }
        }
        System.out.println("Count: " + count);
      }
    });
  }

  /**
   * Starts the timer.
   */
  public void play() {
    timer.start();
  }

  /**
   * Stops the timer.
   */
  public void pause() {
    timer.stop();
  }

  /**
   * Resumes the timer.
   */
  public void resume() {
    timer.start();
  }

  /**
   * Restarts the timer.
   */
  public void restart() {
    timer.stop();
    timer.setDelay(1000 / initialSpeed);
    count = 0;
    timer.start();
    loop = false;
    checkLoop.setSelected(false);
  }

  /**
   * Activates or deactivates the loop.
   */
  public void loop() {
    if (!loop) {
      loop = true;
      checkLoop.setSelected(true);
    } else {
      loop = false;
      checkLoop.setSelected(false);
    }
  }

  /**
   * Decreases the speed.
   */
  public void decreaseSpeed() {
    if (speed - 1 <= 0) {
      speed = 1;
    } else {
      speed = speed - 1;
    }
    timer.setDelay(1000 / speed);
  }

  /**
   * Increases the speed.
   */
  public void increaseSpeed() {
    speed = speed + 1;
    timer.setDelay(1000 / speed);
  }

  @Override
  public void go() {
    generateView();
  }

  /**
   * This class represents a mouse handler object.
   * It extends MouseAdapter.
   */
  public class MouseHandler extends MouseAdapter {

    @Override
    public void mouseReleased(MouseEvent e) {
      if (e.getButton() == MouseEvent.BUTTON1) {

        switch (e.getComponent().getName()) {
          case "play":
            play();
            break;
          case "pause":
            pause();
            break;
          case "resume":
            resume();
            break;
          case "restart":
            restart();
            break;
          case "loop":
            loop();
            break;
          case "increaseSpeed":
            increaseSpeed();
            break;
          case "decreaseSpeed":
            decreaseSpeed();
            break;
          case "exit":
            System.exit(0);
        }

        playback.setFocus();
      }
    }
  }

  /**
   * This class represents a keyboard handler object.
   * It extends KeyAdapter.
   */
  public class KeyboardHandler extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
      switch (e.getKeyCode()) {
        case KeyEvent.VK_ENTER:
          play();
          break;
        case KeyEvent.VK_SPACE:
          pause();
          break;
        case KeyEvent.VK_S:
          resume();
          break;
        case KeyEvent.VK_R:
          restart();
          break;
        case KeyEvent.VK_L:
          loop();
          break;
        case KeyEvent.VK_PERIOD:
          increaseSpeed();
          break;
        case KeyEvent.VK_COMMA:
          decreaseSpeed();
          break;
        case KeyEvent.VK_ESCAPE:
          System.exit(0);
      }
    }
  }

  @Override
  public void generateView() {
    if (model.getLogOfShapes().isEmpty() && !viewType.equals("playback")) {
      showMessage("Animation is empty", 2);
      System.exit(0);
    }

    String content = "";
    String[] outputFile = output.split("\\.");

    switch (viewType) {
      case "text":
        content = getTextView();
        break;
      case "svg":
        content = getSVGView();
        break;
      case "visual":
        getVisualView();
        break;
      case "playback":
        getPlayBackView();
        MouseHandler mouse = new MouseHandler();
        KeyboardHandler keyboard = new KeyboardHandler();
        playback.setCommandButtonListener(mouse, keyboard);
        playback.makeVisible();
        playback.setFocus();
        break;
      default:
        showMessage("Invalid view type", 2);
        System.exit(0);
    }

    String fileName = "";
    if (!(viewType.equals("visual") || viewType.equals("playback"))) {
      try{
        if (viewType.equals("text")) {
          fileName = createFile(outputFile[0], "txt", content);
        }
        if (viewType.equals("svg")) {
          fileName = createFile(outputFile[0], "svg", content);
        }
        showMessage(String.format("%s created", fileName), 1);
      }
      catch (Exception e){
        System.out.print(content);
      }
    }
  }

}