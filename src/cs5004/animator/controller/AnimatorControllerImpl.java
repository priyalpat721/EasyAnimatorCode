package cs5004.animator.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

import cs5004.animator.action.IAction;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.shape.IShape;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;
import cs5004.animator.view.Canvas;
import cs5004.animator.view.Frame;
import cs5004.animator.view.IAnimatorView;
import cs5004.animator.view.PlayBack;

import static cs5004.animator.tools.Helpers.checkInputFile;
import static cs5004.animator.tools.Helpers.createFile;
import static cs5004.animator.tools.Helpers.getSpeed;
import static cs5004.animator.tools.Helpers.parseCommands;
import static cs5004.animator.tools.Helpers.showMessage;
import static cs5004.animator.utils.AnimationReader.parseFile;

/**
 * This class represents a controller. It takes input from the user and delegates to the model and
 * the view. It implements the IAnimatorController interface.
 */
public class AnimatorControllerImpl implements IAnimatorController {
  private List modelData;
  private IAnimatorModel model;
  private final IAnimatorView view;
  private PlayBack playback;
  private int speed;
  private String result;
  private int initialSpeed;
  private Frame frame;
  private Timer timer;
  private int count;
  private boolean loop;
  private final int endTime;
  private JCheckBox checkLoop;

  private final String output;
  private final String viewType;

  /**
   * Constructor for the Animator controller.
   *
   * @param args  command-line arguments
   * @param model of the animation.
   * @param view  type of view for the animation.
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
    int[] box = model.getBox();
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
    JFrame.setDefaultLookAndFeelDecorated(true);
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
    this.frame = playback.generate();

    this.timer = new Timer(1000 / speed, new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        frame.currentView(model.getShapesAtTicks(count));
        count++;
        if (loop) {
          if (count == endTime) {
            count = 0;
            timer.setDelay(1000 / speed);
          }
        }
      }
    });
  }

  /**
   * Starts the timer.
   */
  public void play() {
    count += 1;
    if (loop) {
      count = count % endTime;
    }
    frame.currentView(model.getShapesAtTicks(count));
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
    speed = initialSpeed;
    timer.setDelay(1000 / speed);
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
  }


  /**
   * This class represents a mouse handler object. It extends MouseAdapter.
   */
  public class MouseHandler extends MouseAdapter {

    @Override
    public void mouseReleased(MouseEvent e) {
      if (e.getButton() == MouseEvent.BUTTON1) {

        switch (e.getComponent().getName()) {
          case "play":
            timer.start();
            count = 0;
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
          case "open":
            try {
              getFile();
            } catch (Exception ex) {
            }
            break;
          case "help":
            help();
            break;
          case "exit":
            System.exit(0);
        }
        playback.setFocus();
      }
    }
  }

  /**
   * This class represents a keyboard handler object. It extends KeyAdapter.
   */
  public class KeyboardHandler extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
      switch (e.getKeyCode()) {
        case KeyEvent.VK_ENTER:
          timer.start();
          count = 0;
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
        case KeyEvent.VK_O:
          try {
            getFile();
          } catch (Exception ex) {
          }
          break;
        case KeyEvent.VK_ESCAPE:
          System.exit(0);
      }
    }
  }

  private void generateView() {
    if (model.getLogOfShapes().isEmpty() && !viewType.equals("playback")) {
      showMessage("Animation is empty", 2);
      System.exit(0);
    }

    String content = "";

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

    generateOutput(content);
  }

  private void generateOutput(String content) {
    String[] outputFile = output.split("\\.");
    String fileName = "";

    if (!(viewType.equals("visual") || viewType.equals("playback"))) {
      try {
        if (viewType.equals("text")) {
          fileName = createFile(outputFile[0], "txt", content);
        }
        if (viewType.equals("svg")) {
          fileName = createFile(outputFile[0], "svg", content);
        }
        showMessage(String.format("%s created", fileName), 1);
      } catch (Exception e) {
        System.out.print(content);
      }
    }
  }

  /**
   * A private method that allows the user to open a file of their choosing for the animator.
   */
  private void getFile() {
    JFileChooser file = new JFileChooser();
    file.showOpenDialog(null);
    File f = file.getSelectedFile();
    String filename = f.getAbsolutePath();
    AnimationBuilder<IAnimatorModel> builder = new Builder();
    Readable in = checkInputFile(filename);
    model = parseFile(in, builder);
    count = 0;
    play();
    pause();
  }

  /**
   * A private method that creates a help center for the animation's controls.
   */
  private void help() {
    JFrame.setDefaultLookAndFeelDecorated(true);
    JLabel welcome = new JLabel("    Welcome! Here are the controls\n\n");
    welcome.setFont(new Font("Serif", Font.BOLD, 18));
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(welcome, BorderLayout.NORTH, SwingConstants.CENTER);

    // makes the table for the functions
    JScrollPane help = new JScrollPane();
    String[] heading = {"Buttons", "KeyBoard Keys"};
    String[][] functions = {{"Play", "enter"}, {"Pause", "spacebar"}, {"Resume", "s"},
            {"Restart", "r"}, {"Loop", "l"}, {"Increase speed", "."}, {"Decrease speed", ","},
            {"Exit", "esc"}};
    JTable controls = new JTable(functions, heading);
    controls.setFont(new Font("Serif", Font.PLAIN, 16));
    help.setViewportView(controls);
    panel.add(help, BorderLayout.CENTER);
    JLabel note = new JLabel("Note: Click on loop to enable/diable");
    note.setFont(new Font("Serif", Font.BOLD, 14));
    panel.add(note, BorderLayout.SOUTH);
    JFrame helpCenter = new JFrame();
    helpCenter.add(panel);
    helpCenter.setSize(new Dimension(400, 227));
    helpCenter.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    helpCenter.setVisible(true);
  }

}