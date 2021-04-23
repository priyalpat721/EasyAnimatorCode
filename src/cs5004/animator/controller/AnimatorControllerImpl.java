package cs5004.animator.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.view.EditorView;


/**
 * This class represents a controller.
 * It takes input from the user and delegates to the model and the view.
 * It implements the interface IAnimatorController.
 */
public class AnimatorControllerImpl implements IAnimatorController {
  private final IAnimatorModel model;
  private final EditorView view;
  private final int speed;

  /**
   * Constructs an Animator Controller object.
   * @param model the model.
   * @param view the view.
   * @param speed the speed of the animation.
   */
  public AnimatorControllerImpl(IAnimatorModel model, EditorView view, int speed) {
    this.model = model;
    this.view = view;
    this.speed = speed;
  }

  @Override
  public void go() {
    view.create(model, speed);

    MouseHandler mouse = new MouseHandler();
    KeyboardHandler keyboard = new KeyboardHandler();
    view.setCommandButtonListener(mouse, keyboard);

    view.makeVisible();
    view.setFocus();
  }


  /**
   * This private class represents a mouse listener object.
   * It extends MouseAdapter.
   */
  private class MouseHandler extends MouseAdapter {

    @Override
    public void mouseReleased(MouseEvent e) {
      if (e.getButton() == MouseEvent.BUTTON1) {

        switch (e.getComponent().getName()) {
          case "play":
            view.play();
            break;
          case "pause":
            view.pause();
            break;
          case "resume":
            view.resume();
            break;
          case "restart":
            view.restart();
            break;
          case "loop":
            view.loop();
            break;
          case "increaseSpeed":
            view.increaseSpeed();
            break;
          case "decreaseSpeed":
            view.decreaseSpeed();
            break;
          case "exit":
            System.exit(0);
        }

        view.setFocus();
      }
    }
  }


  /**
   * This private class represents a keyboard listener object.
   * It extends KeyAdapter.
   */
  private class KeyboardHandler extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
      switch (e.getKeyCode()) {
        case KeyEvent.VK_ENTER:
          view.play();
          break;
        case KeyEvent.VK_SPACE:
          view.pause();
          break;
        case KeyEvent.VK_S:
          view.resume();
          break;
        case KeyEvent.VK_R:
          view.restart();
          break;
        case KeyEvent.VK_L:
          view.loop();
          break;
        case KeyEvent.VK_PERIOD:
          view.increaseSpeed();
          break;
        case KeyEvent.VK_COMMA:
          view.decreaseSpeed();
          break;
        case KeyEvent.VK_ESCAPE:
          System.exit(0);
      }
    }
  }

}


