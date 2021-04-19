package cs5004.animator.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cs5004.animator.view.EditorView;

/**
 * Class that handles mouse events from user input. It extends the MouseAdapter abstract class.
 */
public class MouseHandler extends MouseAdapter {
  private final EditorView view;

  /**
   * MouseHandler class constructor.
   * @param view that contains the model and its playing speed.
   */
  public MouseHandler(EditorView view) {
    this.view = view;
  }

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

      view.getFrame().requestFocus();
    }
  }

}
