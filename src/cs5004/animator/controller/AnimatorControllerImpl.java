package cs5004.animator.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;
import cs5004.animator.view.EditorView;

import static cs5004.animator.utils.AnimationReader.parseFile;

/**
 * Class that implements the IAnimator interface.
 * It represents the operations managed by EasyAnimator and enables user to control animations manually.
 */
public class AnimatorControllerImpl implements IAnimatorController {
  private EditorView view;

  /**
   * Constructor for the AnimatorControllerImpl class.
   * @param view data passed to the controller.
   */
  public AnimatorControllerImpl(EditorView view) {
    this.view = view;
  }

  @Override
  public void go() {
    MouseHandler mouse = new MouseHandler(this.view);
    view.setCommandButtonListener(mouse);
    view.makeVisible();
  }

  /**
   * Main method that handles input from the view and creates a new animation.
   * @param args default for the main method.
   * @throws FileNotFoundException when passed a null model into readable.
   */

  public static void main(String[] args) throws FileNotFoundException {
    AnimationBuilder<IAnimatorModel> builder = new Builder();
    Readable in = new FileReader("test/smalldemo.txt");
    IAnimatorModel model = parseFile(in, builder);
    EditorView view = new EditorView(model, 1);

    IAnimatorController controller = new AnimatorControllerImpl(view);
    controller.go();
  }

}


