package cs5004.animator.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.view.TestView;
import cs5004.animator.view.VisualView;


public class AnimatorControllerImpl extends MouseAdapter implements IAnimatorController {
  private IAnimatorModel model;
  private TestView view;
  //private VisualView view;

  public AnimatorControllerImpl(IAnimatorModel model, TestView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void go() {
    view.setCommandButtonListener(this);
    view.makeVisible();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    switch (e.getComponent().getName()) {
      case "press":
        view.setText("button pressed");
        break;
      case "quit":
        System.out.println("quit");
        System.exit(0);
    }
  }

//  @Override
//  public void mouseClicked(MouseEvent e) {
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

  public static void main(String[] args) {
    TestView view = new TestView();
    IAnimatorModel model = new AnimatorModelImpl();
    IAnimatorController controller = new AnimatorControllerImpl(model, view);
    controller.go();

  }

}
