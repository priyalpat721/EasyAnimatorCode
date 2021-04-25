package cs5004.animator.controller;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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
import java.util.Objects;

import cs5004.animator.action.IAction;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.shape.IShape;
import cs5004.animator.shape.Shape;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;
import cs5004.animator.view.Canvas;
import cs5004.animator.view.Frame;
import cs5004.animator.view.IAnimatorView;
import cs5004.animator.view.PlayBack;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;

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
   * @throws NullPointerException if the args, model, or view are null.
   */
  public AnimatorControllerImpl(String[] args, IAnimatorModel model, IAnimatorView view) {
    Objects.requireNonNull(args, "Must have non-null args");
    Objects.requireNonNull(model, "Must have non-null model");
    Objects.requireNonNull(view, "Must have non-null view");

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
    count = 0;
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
   * Sets the speed to the initial speed.
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
      speed -= 1;
    }
    timer.setDelay(1000 / speed);
  }

  /**
   * Increases the speed.
   */
  public void increaseSpeed() {
    speed += 1;
    timer.setDelay(1000 / speed);
  }

  @Override
  public void createView() {
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


  /**
   * This class represents a mouse handler object. It extends MouseAdapter.
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
          case "open":
            try {
              getFile();
            } catch (Exception ex) {
              // no exception needs to be thrown.
            }
            break;
          case "help":
            help();
            break;
          case "shape":
            addShape();
            break;
          case "motion":
            addMotion();
            break;
          case "remove":
            removeShape();
            break;
          case "exit":
            System.exit(0);
            break;
          default:
            // no action is intended when no other case applies.
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
            // no exception needs to be thrown.
          }
          break;
        case KeyEvent.VK_A:
          addShape();
          break;
        case KeyEvent.VK_M:
          addMotion();
          break;
        case KeyEvent.VK_DELETE:
          removeShape();
          break;
        case KeyEvent.VK_ESCAPE:
          System.exit(0);
          break;
        default:
          // no action is intended when no other case applies.
      }
    }
  }

  /**
   * Private method to generate an output.
   * The default is System.out.
   * @param content the content of the svg or text view.
   * @throws NullPointerException if the content is null.
   */
  private void generateOutput(String content) {
    Objects.requireNonNull(content, "Must have non-null content");

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
    helpCenter.setLocationRelativeTo(frame);
    helpCenter.setVisible(true);
  }

  private void addShape() {
    JFrame frame = new JFrame();
    JPanel shape = new JPanel();
    shape.setBackground(Color.WHITE);
    shape.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    // adds name label
    JLabel question = new JLabel("What is the shape's name?");
    constraints.gridx = 150;
    constraints.gridy = 100;
    constraints.ipadx = 20;
    constraints.ipady = 20;
    shape.add(question, constraints);

    // adds text field to get name
    JTextField text = new JTextField();
    constraints.gridx = 150;
    constraints.gridy = 120;
    constraints.ipadx = 120;
    constraints.ipady = 10;
    shape.add(text, constraints);

    // adds shape type label
    JLabel questionType = new JLabel("What is the shape's type?");
    constraints.gridx = 150;
    constraints.gridy = 150;
    constraints.ipadx = 20;
    constraints.ipady = 20;
    shape.add(questionType, constraints);

    // adds the combo box for shape types
    String[] types = {"Rectangle", "Ellipse"};
    JComboBox<String> shapeType = new JComboBox(types);
    shapeType.setBackground(Color.WHITE);
    constraints.gridx = 150;
    constraints.gridy = 170;
    constraints.ipadx = 120;
    constraints.ipady = 20;
    shape.add(shapeType, constraints);

    frame.setSize(new Dimension(300, 230));
    frame.setLocationRelativeTo(frame);
    frame.setLayout(new BorderLayout());
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    frame.add(shape, BorderLayout.NORTH);
    frame.setVisible(true);

    String type = shapeType.getSelectedItem().toString();

    JButton addShape = new JButton("Add Shape");
    addShape.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          model.createShape(text.getText(), type);
          frame.dispose();
        }
        catch (Exception wrongShape) {
          JOptionPane.showMessageDialog(null, "Invalid Shape Name", "Error",
                  JOptionPane.ERROR_MESSAGE);
        }
      }
    });

    JButton cancel = new JButton("Cancel");
    cancel.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
      }
    });

    JPanel buttons = new JPanel();
    buttons.setLayout(new FlowLayout());
    buttons.setBackground(Color.WHITE);
    buttons.add(addShape);
    buttons.add(cancel);
    constraints.gridx = 150;
    constraints.gridy = 200;
    constraints.ipadx = 120;
    constraints.ipady = 20;
    shape.add(buttons, constraints);
  }

  private void addMotion() {
    // TODO
  }

  private void removeShape() {
    // populate the JList
    DefaultListModel listModel = new DefaultListModel();
    List<IShape> shapes = model.getLogOfShapes();
    int size = shapes.size();
    for (int i = 0; i < size; i++) {
      listModel.addElement(shapes.get(i).getName());
    }
    JList list = new JList(listModel);
    list.setVisible(true);

    // creates scroll bar and adds list to it
    JScrollPane listScroller = new JScrollPane(list);
    listScroller.setSize(300, 200);
    listScroller.setVerticalScrollBarPolicy(listScroller.VERTICAL_SCROLLBAR_ALWAYS);

    // create and add buttons
    JButton delete = new JButton("Remove");
    delete.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        int index = list.getSelectedIndex();
        String removedShape = (String) listModel.remove(index);

        int size = listModel.getSize();

        if (size == 0) {
          delete.setEnabled(false);
        }

        else {
          if (index == listModel.getSize()) {
            index --;
          }
          list.setSelectedIndex(index);
          list.ensureIndexIsVisible(index);
        }

        try {
          model.removeShape(removedShape);
        }
        catch (Exception notMotion) {
          JOptionPane.showMessageDialog(null, "Shape has no motions", "Error",
                  JOptionPane.ERROR_MESSAGE);
        }
      }
    });

    // creates the JFrame pop up and adds scroll bar to it
    JFrame listFrame = new JFrame();
    listFrame.setLayout(new BorderLayout());
    JLabel message = new JLabel("Which shape would you like to remove?");
    message.setFont(new Font("Helvetica", Font.BOLD, 14));
    listFrame.add(message, BorderLayout.NORTH);
    listFrame.setSize(new Dimension(300, 200));
    listFrame.setLocationRelativeTo(frame);
    listFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    listFrame.setVisible(true);
    listFrame.add(listScroller, BorderLayout.CENTER);

    JButton cancel = new JButton("Cancel");
    cancel.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        listFrame.dispose();
      }
    });

    // creates a button panel and adds to frame
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    buttonPanel.add(delete);
    buttonPanel.add(cancel);
    buttonPanel.setVisible(true);
    listFrame.add(buttonPanel, BorderLayout.SOUTH);
  }

  private void setShapeAttributes() {
    JFrame frame = new JFrame();
    JPanel shape = new JPanel();
    shape.setBackground(Color.WHITE);
    shape.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    // adds name label
    JLabel x = new JLabel("X:");
    constraints.gridx = 150;
    constraints.gridy = 100;
    constraints.ipadx = 20;
    constraints.ipady = 20;
    shape.add(x, constraints);

    // adds text field to get name
    JTextField text = new JTextField();
    constraints.gridx = 150;
    constraints.gridy = 120;
    constraints.ipadx = 20;
    constraints.ipady = 10;
    shape.add(text, constraints);
  }
}