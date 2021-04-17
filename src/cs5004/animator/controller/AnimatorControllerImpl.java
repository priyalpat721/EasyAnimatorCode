package cs5004.animator.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;
import cs5004.animator.view.EditorView;

import static cs5004.animator.utils.AnimationReader.parseFile;


public class AnimatorControllerImpl implements IAnimatorController {
  private EditorView view;

  public AnimatorControllerImpl(EditorView view) {
    this.view = view;
  }

  @Override
  public void go() {
    MouseHandler mouse = new MouseHandler(this.view);
    view.makeVisible();
  }

  public static void main(String[] args) throws FileNotFoundException {
    AnimationBuilder<IAnimatorModel> builder = new Builder();
    Readable in = new FileReader("test/smalldemo.txt");
    IAnimatorModel model = parseFile(in, builder);
    EditorView view = new EditorView(model, 2);

    IAnimatorController controller = new AnimatorControllerImpl(view);
    controller.go();
  }

}
