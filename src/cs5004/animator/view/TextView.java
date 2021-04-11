package cs5004.animator.view;

import cs5004.animator.model.IAnimatorModel;

public class TextView implements IAnimatorView {
  String result;

  public TextView() {
    this.result = "";
  }

  @Override
  public void create(IAnimatorModel model, int speed) {
    result = model.toString(speed);
  }

  public String generate() {
    return this.result;
  }

}
