package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

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
  public void mouseClicked(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
      System.out.printf(">> click -> (%d, %d)%n", e.getX(), e.getY());
      view.makeVisible();

      switch (e.getComponent().getName()) {
        case "play":
          view.play();
          break;
        case "pause":
          System.out.println(">> pause");
          break;
        case "resume":
          System.out.println(">> resume");
          break;
        case "restart":
          System.out.println(">> restart");
          break;
        case "loop":
          System.out.println(">> loop");
          break;
        case "increaseSpeed":
          System.out.println(">> speed+");
          break;
        case "decreaseSpeed":
          System.out.println(">> speed-");
          break;
      }
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
      switch (e.getComponent().getName()) {
        case "exit":
          System.out.println(">> exit");
          System.exit(0);
      }
    }
  }

}
