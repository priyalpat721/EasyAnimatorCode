package cs5004.animator.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cs5004.animator.view.TestView;

public class MouseHandler extends MouseAdapter {
  private TestView view;

  public MouseHandler(TestView view) {
    this.view = view;
    this.view.setCommandButtonListener(this);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
      System.out.printf(">> click -> (%d, %d)%n", e.getX(), e.getY());

      switch (e.getComponent().getName()) {
        case "press":
          System.out.println("> pressed");
          view.setText("button pressed");
          break;
        case "quit":
          System.out.println("> quit");
          System.exit(0);
      }
    }
  }

//  @Override
//  public void mouseReleased(MouseEvent e) {
//    switch (e.getComponent().getName()) {
//      case "start":
//        break;
//      case "pause":
//        break;
//      case "resume":
//        break;
//      case "restart":
//        break;
//      case "loop":
//        break;
//      case "speed":
//        break;
//    }

}
