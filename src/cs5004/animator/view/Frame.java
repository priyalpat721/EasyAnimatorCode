package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.*;

import cs5004.animator.shape.IShape;

/**
 * Class that creates the frame of the animation. The class extends the JFrame class of Java Swing.
 */
public class Frame extends JFrame {
  ShapesPanel animation;
  JScrollPane scrollPane;
  JMenuBar menu;
  JMenu file;
  JMenuItem exit;
  int width;
  int height;
  int x;
  int y;

  /**
   * Constructs Canvas objects that displays the panel with the shapes.
   *
   * @param x      coordinate of the canvas on the screen.
   * @param y      coordinate of the canvas on the screen.
   * @param width  of the canvas window.
   * @param height of the canvas window.
   */
  public Frame(double x, double y, double width, double height,
               ShapesPanel animation, JScrollPane scrollPanel) {
    super("Easy Animator");
    this.width = (int) width;
    this.height = (int) height;
    this.x = (int) x;
    this.y = (int) y;
    this.setSize(this.width, this.height);
    this.setLocation((int) x, (int) y);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.animation = animation;
    this.scrollPane = scrollPanel;
    //this.view = view;

//    this.animation = new ShapesPanel(model);
//    this.animation.setLayout(new BorderLayout());
//    this.animation.setPreferredSize(new Dimension((int) width * 2, (int) height * 2));
//    scrollPane = new JScrollPane(animation);
//    scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//    scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//    this.setVisible(true);
//    this.add(scrollPane);
//    animation.setVisible(true);
    //this.add(scrollPane, BorderLayout.CENTER);


    // adds animation
//    this.animation = new ShapesPanel(model);
//    this.animation.setLayout(new BorderLayout());
//    this.animation.setPreferredSize(new Dimension(this.width * 2,
//            this.height * 2));
//    scrollPane = new JScrollPane();
//    scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//    scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//    this.add(scrollPane);

    // this is for the buttons
//    JPanel buttons = new JPanel();
//    buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
//
//    play = new JButton("Play");
//    play.addActionListener(this);
//    buttons.add(play);
//
//    JButton pause = new JButton("Pause");
//    pause.addActionListener(e -> System.out.println("pause"));
//    buttons.add(pause);
//
//    JButton stop = new JButton("Stop");
//    stop.addActionListener(e -> System.out.println("stop"));
//    buttons.add(stop);
//
//    JButton fast = new JButton("Speed up");
//    fast.addActionListener(e -> System.out.println("fast forward"));
//    buttons.add(fast);
//
//    JButton slow = new JButton("Slow down");
//    slow.addActionListener(e -> System.out.println("slow down"));
//    buttons.add(slow);
//    this.add(buttons, BorderLayout.SOUTH);


    // adds menu option
    this.menu = new JMenuBar();
    this.file = new JMenu("File");
    this.exit = new JMenuItem("Exit");
    this.exit.setName("exit");
    this.file.add(exit);

//    exit.addActionListener(this);
    this.menu.add(file);
    this.setJMenuBar(menu);

    this.pack();
    this.setVisible(false);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(width + 1, height + 1);
  }

  public void currentView(List<IShape> currentShapes) {
//    System.out.println("Here in frame");
//    this.animation.setShapes(currentShapes);
//    System.out.println("should be a repaint");
//    this.animation.repaint();
//    System.out.println("repaint should have happened");
//    this.scrollPane.add(animation);
//    this.add(scrollPane);
//    this.repaint();
    this.animation.setShapes(currentShapes);
    this.animation.repaint();
  }

  public void setComponentVisible(boolean visible) {
    this.animation.setVisible(visible);
    this.scrollPane.setVisible(visible);
  }

  public void setCommandButtonListener(MouseListener mouseEvent) {
    this.exit.addMouseListener(mouseEvent);
  }

}
