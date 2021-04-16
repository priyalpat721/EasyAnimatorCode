package cs5004.animator.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.view.TestView;

public class AnimatorControllerImpl extends MouseAdapter implements IAnimatorController {
  private IAnimatorModel model;
  private TestView view;

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
    }

  }


  public static void main(String[] args) {
    TestView view = new TestView();
    IAnimatorModel model = new AnimatorModelImpl();
    AnimatorControllerImpl controller = new AnimatorControllerImpl(model, view);
    controller.go();
  }

}
