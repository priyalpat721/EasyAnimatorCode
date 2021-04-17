package cs5004.animator.controller;

import com.sun.jdi.connect.spi.TransportService;

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
        System.out.println("KeyboardHandler -> play");
        view.play();
        break;
      case KeyEvent.VK_BACK_SPACE:
        System.out.println("KeyboardHandler -> pause");
        view.pause();
        break;
    }
  }

}
