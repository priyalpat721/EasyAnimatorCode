package cs5004.animator.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cs5004.animator.view.EditorView;

public class MouseHandler extends MouseAdapter {
  private final EditorView view;

  public MouseHandler(EditorView view) {
    this.view = view;
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
      System.out.printf(">> click -> (%d, %d)%n", e.getX(), e.getY());

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
        case "exit":
          System.out.println(">> quit");
          System.exit(0);
      }
    }
  }

}
