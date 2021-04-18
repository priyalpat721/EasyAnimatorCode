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
          System.out.println("MouseHandler -> play");
          view.play();
          break;
        case "pause":
          System.out.println("MouseHandler -> pause");
          view.pause();
          break;
        case "stop":
          System.out.println("MouseHandler -> stop");
          view.stop();
          break;
        case "restart":
          System.out.println("MouseHandler -> restart");
          view.restart();
          break;
        case "loop":
          System.out.println("MouseHandler -> loop");
          view.loop();
          break;
        case "increaseSpeed":
          view.increaseSpeed();
          System.out.println("MouseHandler -> speed+");
          break;
        case "decreaseSpeed":
          view.decreaseSpeed();
          System.out.println("MouseHandler -> speed-");
          break;
        case "exit":
          System.out.println("MouseHandler -> exit");
          System.exit(0);
      }

      view.getFrame().requestFocus();
    }
  }

}
