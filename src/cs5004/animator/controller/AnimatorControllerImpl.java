package cs5004.animator.controller;

import cs5004.animator.view.EditorView;


/**
 * Class that implements the IAnimator interface.
 * It represents the operations managed by EasyAnimator and enables user to control animations manually.
 */
public class AnimatorControllerImpl implements IAnimatorController {
  private EditorView view;

  public AnimatorControllerImpl(EditorView view) {
    this.view = view;
  }

  @Override
  public void go() {
    MouseHandler mouse = new MouseHandler(this.view);
    view.setCommandButtonListener(mouse);

    KeyboardHandler keyboard = new KeyboardHandler(this.view);
    view.setKeyListener(keyboard);

    view.makeVisible();
  }

}


