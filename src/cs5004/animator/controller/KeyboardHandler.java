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
      case KeyEvent.VK_SPACE:
        System.out.println("KeyboardHandler -> pause");
        view.pause();
        break;
      case KeyEvent.VK_S:
        System.out.println("KeyboardHandler -> stop");
        view.stop();
        break;
      case KeyEvent.VK_R:
        System.out.println("KeyboardHandler -> restart");
        view.restart();
        break;
      case KeyEvent.VK_L:
        System.out.println("KeyboardHandler -> loop");
        view.loop();
        break;
      case KeyEvent.VK_PERIOD:
        System.out.println("KeyboardHandler -> increaseSpeed");
        view.increaseSpeed();
        break;
      case KeyEvent.VK_COMMA:
        System.out.println("KeyboardHandler -> decreaseSpeed");
        view.decreaseSpeed();
        break;
      case KeyEvent.VK_ESCAPE:
        System.out.println("KeyboardHandler -> pause");
        System.exit(0);
    }
  }

}
