package cs5004.animator;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs5004.animator.controller.AnimatorControllerImpl;
import cs5004.animator.controller.IAnimatorController;
import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;
import cs5004.animator.view.TestView;
import cs5004.animator.view.VisualView;

import static cs5004.animator.tools.Helpers.checkInputFile;
import static cs5004.animator.tools.Helpers.generateView;
import static cs5004.animator.tools.Helpers.parseCommands;
import static cs5004.animator.tools.Helpers.showMessage;
import static cs5004.animator.utils.AnimationReader.parseFile;

/**
 * This class represents an Easy Animator.
 */
public final class EasyAnimator {

  /**
   * Main method for Easy Animator.
   * @param args default arguments for main method.
   */
  public static void main(String[] args) {
    String[] commands = parseCommands(args);
    String inputFile = commands[0];
    String viewType = commands[1];
    String[] outputFile = commands[2].split("\\.");

    int speed = 1;
    if (!commands[3].equals("")) {
      speed = Integer.parseInt(commands[3]);
    }

    AnimationBuilder<IAnimatorModel> builder = new Builder();
    Readable in = checkInputFile(inputFile);
    IAnimatorModel animation = parseFile(in, builder);

    if (commands[4].equals("playback")) {
//      TestView view = new TestView();
//      IAnimatorController controller = new AnimatorControllerImpl(animation, view);
//      controller.go();
    } else {
      generateView(animation, viewType, outputFile, speed);
    }
  }

}
