package cs5004.animator.controller;

import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.view.TestView;


public class AnimatorControllerImpl implements IAnimatorController {
  private IAnimatorModel model;
  private TestView view;
  private MouseHandler mouse;
  //private VisualView view;

  public AnimatorControllerImpl(IAnimatorModel model, TestView view) {
    this.model = model;
    this.view = view;
    this.mouse = new MouseHandler(this.view);
  }

  @Override
  public void go() {
    view.makeVisible();
  }

  public static void main(String[] args) {
    TestView view = new TestView();
    IAnimatorModel model = new AnimatorModelImpl();
    IAnimatorController controller = new AnimatorControllerImpl(model, view);
    controller.go();
  }

}
