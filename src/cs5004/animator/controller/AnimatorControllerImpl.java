package cs5004.animator.controller;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.view.EditorView;
import cs5004.animator.view.IAnimatorView;


/**
 * Class that implements the IAnimator interface.
 * It represents the operations managed by EasyAnimator and enables user to control animations manually.
 */
public class AnimatorControllerImpl implements IAnimatorController {
  private IAnimatorModel model;
  private EditorView view;
  int speed;

  public AnimatorControllerImpl(IAnimatorModel model, EditorView view, int speed) {
    this.model = model;
    this.view = view;
    this.speed = speed;
  }

  @Override
  public void go() {
    view.create(model, speed);

    MouseHandler mouse = new MouseHandler(this.view);
    KeyboardHandler keyboard = new KeyboardHandler(this.view);
    view.setCommandButtonListener(mouse, keyboard);

    view.makeVisible();
  }

}


