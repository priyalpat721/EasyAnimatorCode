package cs5004.animator.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

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
  private List<Object> modelData;
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
          if (count == model.getTotalTime()[1]) {
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
    timer.setDelay(1000 / speed);
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
   * Restarts the timer. Sets the speed to the initial speed.
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
  public void start() {
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
          case "save":
            try {
              save();
            } catch (IOException ioException) {
              ioException.printStackTrace();
            }
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
          play();
          break;
        case KeyEvent.VK_SPACE:
          pause();
          break;
        case KeyEvent.VK_K:
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
        case KeyEvent.VK_H:
          help();
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
        case KeyEvent.VK_S:
          try {
            save();
          } catch (IOException ioException) {
            ioException.printStackTrace();
          }
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
   * Private method to generate an output. The default is System.out.
   *
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
  public void getFile() {
    JFileChooser file = new JFileChooser();
    file.showOpenDialog(null);
    File f = file.getSelectedFile();
    String filename = f.getAbsolutePath();
    AnimationBuilder<IAnimatorModel> builder = new Builder();
    Readable in = checkInputFile(filename);
    model = parseFile(in, builder);
    count = 0;
    pause();
    play();
    pause();
  }

  /**
   * A private method that creates a help center for the animation's controls.
   */
  public void help() {
    //JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame helpCenter = makeFrame(400, 227);
    JLabel welcome = new JLabel("    Welcome! Here are the controls\n\n");
    welcome.setFont(new Font("Serif", Font.BOLD, 18));
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(welcome, BorderLayout.NORTH, SwingConstants.CENTER);

    // makes the table for the functions
    JScrollPane help = new JScrollPane();
    String[] heading = {"Buttons", "KeyBoard Keys"};
    String[][] functions = {{"o", "open"}, {"h", "help"},
        {"m", "add motion"},{"a", "add shape"},{"del", "delete shape"},{"s", "save to file"},
        {"Play", "enter"}, {"Pause", "spacebar"}, {"Resume", "k"}, {"Restart", "r"}, {"Loop", "l"},
        {"Increase speed", "."}, {"Decrease speed", ","}, {"Exit", "esc"}};
    JTable controls = new JTable(functions, heading);
    controls.setFont(new Font("Serif", Font.PLAIN, 16));
    help.setViewportView(controls);
    panel.add(help, BorderLayout.CENTER);
    JLabel note = new JLabel("Note: Click on loop to enable/disable");
    note.setFont(new Font("Serif", Font.BOLD, 14));
    panel.add(note, BorderLayout.SOUTH);
    helpCenter.add(panel);

  }

  /**
   * Method that adds the selected shape to the panel animation.
   */
  public void addShape() {
    JFrame frame = makeFrame(400, 230);
    JPanel shape = new JPanel();
    shape.setBackground(Color.WHITE);
    shape.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    // adds name label
    JLabel name = setConstraints("What is the shape's name?", constraints, 150, 100,
            20, 10);
    shape.add(name, constraints);

    // adds text field to get name
    JTextField text = new JTextField();
    constraints.gridx = 150;
    constraints.gridy = 120;
    constraints.ipadx = 120;
    constraints.ipady = 10;
    shape.add(text, constraints);

    // adds name label
    JLabel questionType = setConstraints("What is the shape's type?", constraints,
            150, 150, 20, 20);

    shape.add(questionType, constraints);

    // adds the combo box for shape types
    String[] types = {"Rectangle", "Ellipse"};
    JComboBox<String> shapeType = new JComboBox<>(types);
    shapeType.setBackground(Color.WHITE);
    constraints.gridx = 150;
    constraints.gridy = 170;
    constraints.ipadx = 120;
    constraints.ipady = 20;
    shape.add(shapeType, constraints);

    String type = shapeType.getSelectedItem().toString();

    JButton addShape = new JButton("Add Shape");
    addShape.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          model.createShape(text.getText(), type);
          frame.dispose();
        } catch (Exception wrongShape) {
          JOptionPane.showMessageDialog(null, "Invalid Shape Name", "Error",
                  JOptionPane.ERROR_MESSAGE);
        }
        setShapeAttributes(text.getText());
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
    frame.add(shape);
  }

  /**
   * Method that allows user to select a motion from a list and add it to the panel animation.
   */
  public void addMotion() {
    JFrame frame = makeFrame(300, 300);

    JPanel listPanel = new JPanel();
    listPanel.setLayout(new BorderLayout());
    JLabel shape = new JLabel("What shape do you want?");
    listPanel.setLayout(new BorderLayout());
    listPanel.add(shape, BorderLayout.CENTER);

    DefaultListModel<String> listModel = makeListModel();
    JList<String> list = new JList<>(listModel);
    list.setVisible(true);

    // creates scroll bar and adds list to it
    JScrollPane listScroller = makeScrollPane(list);
    listPanel.add(listScroller, BorderLayout.SOUTH);


    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    JLabel motion = new JLabel("What motion do you want?");
    String[] types = {"Move", "Scale", "Change Color", "Stationary"};
    JComboBox<String> motionType = new JComboBox<>(types);
    motionType.setBackground(Color.WHITE);
    panel.add(motion, BorderLayout.CENTER);
    panel.add(motionType, BorderLayout.SOUTH);

    frame.add(listPanel, BorderLayout.NORTH);
    frame.add(panel, BorderLayout.CENTER);
    panel.setVisible(true);

    JButton motionShape = new JButton("Add Motion");
    motionShape.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        JFrame motionFrame = makeFrame(250, 300);
        JPanel shape = new JPanel();

        motionFrame.add(shape);

        JLabel welcome = new JLabel("   Set the shape's motion!\n\n");
        welcome.setBackground(Color.WHITE);
        welcome.setFont(new Font("Serif", Font.BOLD, 18));
        motionFrame.add(welcome, BorderLayout.NORTH, SwingConstants.CENTER);

        shape.setVisible(true);
        shape.setBackground(Color.WHITE);
        shape.setLayout(new GridLayout(9, 1, 5, 5));
        shape.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        try {
          int index = list.getSelectedIndex();


          // getting shape's name
          String name = (String) listModel.get(index);
          String type = motionType.getSelectedItem().toString();

          MotionStrategy motionStrategy = new MoveStrategy(motionFrame, shape, name, model);

          if (type.equals("Move")) {
            frame.dispose();
            motionStrategy.makeMotion();
          } else if (type.equals("Scale")) {
            frame.dispose();
            motionStrategy = new ScaleStrategy(motionFrame, shape, name, model);
            motionStrategy.makeMotion();
          } else if (type.equals("Change Color")) {
            frame.dispose();
            motionStrategy = new ChangeColorStrategy(motionFrame, shape, name, model);
            motionStrategy.makeMotion();
          } else {
            frame.dispose();
            motionStrategy = new StationaryStrategy(motionFrame, shape, name, model);
            motionStrategy.makeMotion();
          }
        } catch (Exception notSelected) {
          motionFrame.dispose();
          JOptionPane.showMessageDialog(null, "A shape was not selected", "Error",
                  JOptionPane.ERROR_MESSAGE);
          frame.dispose();
        }
      }
    });

    JButton cancel = makeCancelButton(frame);


    JPanel buttons = new JPanel();
    buttons.setLayout(new FlowLayout());
    buttons.setBackground(Color.WHITE);
    buttons.add(motionShape);
    buttons.add(cancel);

    frame.add(buttons, BorderLayout.SOUTH);
  }


  /**
   * Method that removes a shape from the animation.
   */
  public void removeShape() {
    // populate the JList
    DefaultListModel<String> listModel = makeListModel();
    JList<String> list = new JList<String>(listModel);
    list.setVisible(true);

    // creates scroll bar and adds list to it
    JScrollPane listScroller = makeScrollPane(list);

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
        } else {
          if (index == listModel.getSize()) {
            index--;
          }
          list.setSelectedIndex(index);
          list.ensureIndexIsVisible(index);
        }

        try {
          model.removeShape(removedShape);
        } catch (Exception notMotion) {
          JOptionPane.showMessageDialog(null, "Shape had no motions", "Error",
                  JOptionPane.ERROR_MESSAGE);
        }
      }
    });

    // creates the JFrame pop up and adds scroll bar to it
    JFrame listFrame = makeFrame(300, 200);
    JLabel message = new JLabel("Which shape would you like to remove?");
    message.setFont(new Font("Helvetica", Font.BOLD, 14));
    listFrame.add(message, BorderLayout.NORTH);
    listFrame.add(listScroller, BorderLayout.CENTER);

    JButton cancel = makeCancelButton(listFrame);

    // creates a button panel and adds to frame
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    buttonPanel.add(delete);
    buttonPanel.add(cancel);
    buttonPanel.setVisible(true);
    listFrame.add(buttonPanel, BorderLayout.SOUTH);
  }

  /**
   * Method that allows a user to save an edited animation.
   * A user can save an animation in .txt or .svg file.
   */

  public void save() throws IOException {
    JTextField filename = new JTextField();
    JFileChooser fileChooser = new JFileChooser();
    if (fileChooser.showSaveDialog(filename) == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      String directory = file.getAbsolutePath();
      if (file == null) {
        JOptionPane.showMessageDialog(null, "", "Error",
                JOptionPane.ERROR_MESSAGE);
      }

      assert file != null;
      String file_name = file.getName();
      String[] fileComponents = file_name.split("\\.");
      String name = fileComponents[0];
      String extension = fileComponents[1];
      if (file_name.contains("txt")) {
        String text = saveFile(name, extension, model.toString(), directory);
        JOptionPane.showMessageDialog(null, text, "Success", JOptionPane.PLAIN_MESSAGE);
      } else if (file_name.contains("svg")) {

        String result = getSVGView();
        String svg = saveFile(name, extension, result, directory);
        JOptionPane.showMessageDialog(null, svg, "Success", JOptionPane.PLAIN_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(null, "File must be .txt or .svg", "Error",
                JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  /**
   * Method to set a generic Jlabel with GridBagLayout constraints.
   *
   * @param message label message.
   * @param constraints for the GridBagLayout Jlabel.
   * @param x x coordinate for GridBagLayout Jlabel.
   * @param y y coordinate for GridBagLayout Jlabel.
   * @param padX width of GridBagLayout Jlabel.
   * @param padY height of the GridBagLayout Jlabel.
   * @return a generic JLabel
   */
  private JLabel setConstraints(String message, GridBagConstraints constraints,
                                int x, int y, int padX, int padY) {
    JLabel genericLabel = new JLabel(message);
    constraints.gridx = x;
    constraints.gridy = y;
    constraints.ipadx = padX;
    constraints.ipady = padY;
    return genericLabel;
  }

  /**
   * Method that allows user to set shape attributes in GUI via mouse and keyboard events.
   *
   * @param name of the shape.
   */
  private void setShapeAttributes(String name) {
    JFrame frame = makeFrame(250, 300);
    JPanel shape = new JPanel();
    frame.add(shape);

    JLabel welcome = new JLabel("   Set the shape's attributes!\n\n");
    welcome.setBackground(Color.WHITE);
    welcome.setFont(new Font("Serif", Font.BOLD, 18));
    frame.add(welcome, BorderLayout.NORTH, SwingConstants.CENTER);

    shape.setVisible(true);
    shape.setBackground(Color.WHITE);
    shape.setLayout(new GridLayout(9, 1, 5, 5));
    shape.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

    // adds name label
    JLabel xLabel = new JLabel("X:");
    JTextField xText = new JTextField();

    JLabel yLabel = new JLabel("Y:");
    JTextField yText = new JTextField();

    JLabel wLabel = new JLabel("Width:");
    JTextField width = new JTextField();

    JLabel hLabel = new JLabel("Height:");
    JTextField height = new JTextField();

    JLabel rLabel = new JLabel("Red:");
    JTextField redText = new JTextField();

    JLabel gLabel = new JLabel("Green:");
    JTextField greenText = new JTextField();

    JLabel bLabel = new JLabel("Blue:");
    JTextField blueText = new JTextField();

    JLabel t1Label = new JLabel("T1:");
    JTextField t1 = new JTextField();

    JLabel t2Label = new JLabel("T2:");
    JTextField t2 = new JTextField();

    shape.add(xLabel);
    shape.add(xText);
    shape.add(yLabel);
    shape.add(yText);
    shape.add(wLabel);
    shape.add(width);
    shape.add(hLabel);
    shape.add(height);
    shape.add(rLabel);
    shape.add(redText);
    shape.add(gLabel);
    shape.add(greenText);
    shape.add(bLabel);
    shape.add(blueText);
    shape.add(t1Label);
    shape.add(t1);
    shape.add(t2Label);
    shape.add(t2);
    frame.add(shape, BorderLayout.CENTER);


    JButton addShape = new JButton("Add Shape");
    addShape.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          model.setAttributes(name, Integer.parseInt(xText.getText()),
                  Integer.parseInt(yText.getText()),
                  Integer.parseInt(width.getText()), Integer.parseInt(height.getText()),
                  Integer.parseInt(redText.getText()), Integer.parseInt(greenText.getText()),
                  Integer.parseInt(blueText.getText()), Integer.parseInt(t1.getText()),
                  Integer.parseInt(t2.getText()));
          frame.dispose();
        } catch (Exception wrongShape) {
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

    frame.add(buttons, BorderLayout.SOUTH);
  }

  /**
   * Method that creates a JFrame given width and height.
   *
   * @param width of the JFrame.
   * @param height of the JFrame.
   * @return new JFrame of white background.
   */
  private JFrame makeFrame(int width, int height) {
    JFrame frame = new JFrame();
    JFrame.setDefaultLookAndFeelDecorated(true);
    frame.setSize(new Dimension(width, height));
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    frame.setLocationRelativeTo(frame);
    frame.setBackground(Color.WHITE);
    frame.setLayout(new BorderLayout());
    frame.setVisible(true);
    return frame;
  }

  /**
   * Method that creates a JScrollPane of width 300 and height 200.
   *
   * @param list of elements in Panel
   * @return new JScrollpane.
   */
  private JScrollPane makeScrollPane(JList<String> list) {
    JScrollPane listScroller = new JScrollPane(list);
    listScroller.setSize(300, 200);
    listScroller.setVerticalScrollBarPolicy(listScroller.VERTICAL_SCROLLBAR_ALWAYS);
    return listScroller;
  }

  /**
   * Method that creates a DefaultListModel of type String and adds to it shapes from the model.
   *
   * @return DefaultListModel of shapes from model.
   */
  private DefaultListModel<String> makeListModel() {
    DefaultListModel<String> listModel = new DefaultListModel<String>();
    List<IShape> shapes = model.getLogOfShapes();
    int size = shapes.size();
    for (int i = 0; i < size; i++) {
      listModel.addElement(shapes.get(i).getName());
    }
    return listModel;
  }

  /**
   * Method that creates a cancel button.
   *
   * @param frame for the button.
   * @return cancel JButton.
   */
  private JButton makeCancelButton(JFrame frame) {
    JButton cancel = new JButton("Cancel");
    cancel.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
      }
    });

    return cancel;
  }

  /**
   * Creates a file.
   * @param name name of the file.
   * @param format format of the file.
   * @param content content of the file.
   * @return the file name.
   * @throws IllegalArgumentException if the name, format, or content are invalid.
   */
  private String saveFile(String name, String format, String content, String directory) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Invalid name");
    } else if (format == null || format.isBlank()) {
      throw new IllegalArgumentException("Invalid format");
    } else if (content == null || content.isBlank()) {
      throw new IllegalArgumentException("Invalid content");
    }

    String fileName = name + "." + format;

    try {
      File newfile = new File(fileName, directory);
      Writer newFile = new FileWriter(newfile);
      newFile.write(content);
      newFile.close();
    } catch (IOException e) {
      showMessage("An error occurred while saving the file", 2);
      System.exit(0);
    }

    return fileName;
  }

}
