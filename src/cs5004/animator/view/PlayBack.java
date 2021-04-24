package cs5004.animator.view;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.EventListener;
import java.util.List;
import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JComponent;

import cs5004.animator.shape.IShape;

public class PlayBack implements IAnimatorView<Frame> {
  private Frame frame;
  private final JPanel buttons;
  private final JButton play;
  private final JButton pause;
  private final JButton resume;
  private final JButton restart;
  private final JButton loop;
  private final JButton increaseSpeed;
  private final JButton decreaseSpeed;
  private final JCheckBox checkLoop;

  /**
   * Class that creates an interactive window-based visual layout of the animation.
   */
  public PlayBack() {
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
  public void create(List modelData) {
    ShapesPanel animation = new ShapesPanel((List<IShape>) modelData.get(2));
    animation.setLayout(new BorderLayout());
    animation.setPreferredSize(new Dimension((int) (double) modelData.get(0),
            ((int) (double) modelData.get(1))));

    JScrollPane scrollPane = new JScrollPane(animation);
    scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    this.frame = new Frame(800, 600, animation, scrollPane);

    this.frame.add(scrollPane);
    animation.setVisible(true);
    scrollPane.setVisible(true);
    this.frame.add(scrollPane, BorderLayout.CENTER);
    this.frame.add(buttons, BorderLayout.SOUTH);
    this.frame.setResizable(false);
  }

  /**
   * Method that generates a JFrame frame containing the frames of the animation.
   * @return a Jframe object containing the animation.
   */
  @Override
  public Frame generate() {
    return frame;
  }

  public void makeVisible() {
    frame.setVisible(true);
  }

  public void setFocus() {
    frame.requestFocus();
  }

  private void removeMouseListener(JComponent event) {
    EventListener[] listeners = event.getListeners(MouseListener.class);
    for (EventListener listener : listeners)
      event.removeMouseListener((MouseListener) listener);
  }

  public JCheckBox getCheckLoop() {
    return checkLoop;
  }

  /**
   * Adds mouse and keyboard listeners to components.
   * @param mouseEvent a mouse event.
   * @param keyEvent a keyboard event.
   */
  public void setCommandButtonListener(MouseListener mouseEvent, KeyListener keyEvent) {
    Objects.requireNonNull(mouseEvent);
    Objects.requireNonNull(keyEvent);

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

}

