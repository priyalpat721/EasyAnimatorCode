package cs5004.animator.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cs5004.animator.view.TestView;
import cs5004.animator.view.EditorView;

public class MouseHandler extends MouseAdapter {
  private TestView view;
  private EditorView view2;

//  public MouseHandler(TestView view) {
//    this.view = view;
//    this.view.setCommandButtonListener(this);

  public MouseHandler(EditorView view) {
    this.view2 = view;
    this.view2.setCommandButtonListener(this);
  }

//  @Override
//  public void mouseReleased(MouseEvent e) {
//    if (e.getButton() == MouseEvent.BUTTON1) {
//      System.out.printf(">> click -> (%d, %d)%n", e.getX(), e.getY());
//
//      switch (e.getComponent().getName()) {
//        case "press":
//          System.out.println("> pressed");
//          view.setText("button pressed");
//          break;
//        case "quit":
//          System.out.println("> quit");
//          System.exit(0);
//      }
//    }
//  }

//  @Override
//  public void mouseReleased(MouseEvent e) {
//    if (e.getButton() == MouseEvent.BUTTON1) {
//      System.out.printf(">> click -> (%d, %d)%n", e.getX(), e.getY());
//      view2.play();
//    }
//  }


  @Override
  public void mouseClicked(MouseEvent e) {
    if(e.getButton() == MouseEvent.BUTTON1) {
      System.out.println("using mouse adapter");
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
