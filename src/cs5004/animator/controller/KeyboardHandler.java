package cs5004.animator.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import cs5004.animator.view.EditorView;

public class KeyboardHandler extends KeyAdapter {
  private final EditorView view;

  public KeyboardHandler(EditorView view) {
    this.view = view;
  }

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
