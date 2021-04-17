package cs5004.animator.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;
import cs5004.animator.view.TestView;
import cs5004.animator.view.EditorView;

import static cs5004.animator.utils.AnimationReader.parseFile;


public class AnimatorControllerImpl implements IAnimatorController {
  private IAnimatorModel model;
  private TestView view;
  private EditorView view2;
  private MouseHandler mouse;

//  public AnimatorControllerImpl(IAnimatorModel model, TestView view) {
//    this.model = model;
//    this.view = view;
//    this.mouse = new MouseHandler(this.view);
//  }

  public AnimatorControllerImpl(IAnimatorModel model, EditorView view2) {
    this.model = model;
    this.view2 = view2;
    this.mouse = new MouseHandler(this.view2);
  }

//  @Override
//  public void go() {
//    view.makeVisible();
//  }

  @Override
  public void go() {
    view2.makeVisible();
  }

  public static void main(String[] args) throws FileNotFoundException {
//    TestView view = new TestView();
    AnimationBuilder<IAnimatorModel> builder = new Builder();
    Readable in = new FileReader("test/smalldemo.txt");
    IAnimatorModel model = parseFile(in, builder);
    EditorView view2 = new EditorView(model, 10);

    IAnimatorController controller = new AnimatorControllerImpl(model, view2);
    controller.go();
  }

}
