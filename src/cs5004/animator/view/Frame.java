package cs5004.animator.view;

import java.awt.*;
import java.awt.event.MouseListener;
import java.util.List;


import cs5004.animator.shape.IShape;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Class that creates the frame of the animation. The class extends the JFrame class of Java Swing.
 * This class is primarily used in the playback view.
 */
public class Frame extends JFrame {
  private final ShapesPanel animation;
  private JMenuItem exit;
  private JMenuItem open;

  private final int width;
  private final int height;
  private JMenuItem help;
  private JMenuItem addShape;
  private JMenuItem addMotion;
  private JMenuItem removeShape;
  private JMenuItem save;

  /**
   * Constructs Canvas objects that displays the panel with the shapes.
   *

   * @param width  of the canvas window.
   * @param height of the canvas window.
   */
  public Frame(double width, double height,
               ShapesPanel animation) {
    super("Easy Animator");
    JFrame.setDefaultLookAndFeelDecorated(true);
    this.width = (int) width;
    this.height = (int) height;

    this.setSize(this.width, this.height);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.animation = animation;

    makeMenu();

    this.pack();
    this.setVisible(false);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(width + 1, height + 1);
  }

  /**
   * Method that takes a list of current shapes and adds the shapes to the Shapes panel.
   * @param currentShapes current shapes to be added to panel.
   */
  public void currentView(List<IShape> currentShapes) {

    this.animation.setShapes(currentShapes);
    this.animation.repaint();
  }

  /**
   * Method that adds a mouse event to buttons exit, open and help.
   * @param mouseEvent for exit, open and help.
   */
  public void setCommandButtonListener(MouseListener mouseEvent) {
    this.exit.addMouseListener(mouseEvent);
    this.open.addMouseListener(mouseEvent);
    this.help.addMouseListener(mouseEvent);
    this.addShape.addMouseListener(mouseEvent);
    this.addMotion.addMouseListener(mouseEvent);
    this.removeShape.addMouseListener(mouseEvent);
    this.save.addMouseListener(mouseEvent);
  }

  /**
   * Method that adds JMenu tool bar functionality to PlayBackView.
   * It allows user to open animation from file, edit, and save animation to a file.
   * Buttons: File, Open, Edit, Add Shape/Motion, Add Shape, Add Motion, Remove Shape, Save, Exit.
   */
  private void makeMenu() {
    // adds menu option
    JMenuBar menu = new JMenuBar();
    JMenu file = new JMenu("File");

    // creates an open file option
    this.open = new JMenuItem("Open");
    this.open.setName("open");
    file.add(open);
    file.addSeparator();

    // creates an edit animation option
    JMenu edit = new JMenu("Edit");

    // adds shape or motion option
    JMenu add = new JMenu("Add Shape/Motion");
    this.addShape = new JMenuItem("Add Shape");
    this.addShape.setName("shape");
    add.add(addShape);
    this.addMotion = new JMenuItem("Add Motion");
    this.addMotion.setName("motion");
    add.add(addMotion);
    edit.add(add);

    // removes shape option
    this.removeShape = new JMenuItem("Remove Shape");
    this.removeShape.setName("remove");
    edit.add(removeShape);
    file.add(edit);
    file.addSeparator();

    // creates a help center for controls
    this.help = new JMenuItem("Help");
    this.help.setName("help");
    file.add(help);
    file.addSeparator();

    this.save = new JMenuItem("Save");
    this.save.setName("save");
    file.add(save);
    file.addSeparator();

    // allows user to exit out of program
    this.exit = new JMenuItem("Exit");
    this.exit.setName("exit");
    file.add(exit);

    menu.add(file);
    this.setJMenuBar(menu);
  }
}
