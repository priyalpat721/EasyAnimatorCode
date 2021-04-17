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
        case "exit":
          System.exit(0);
      }
    }
  }

}
