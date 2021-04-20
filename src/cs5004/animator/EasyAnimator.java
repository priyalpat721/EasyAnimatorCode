package cs5004.animator;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;

import static cs5004.animator.tools.Helpers.checkInputFile;
import static cs5004.animator.tools.Helpers.generateView;
import static cs5004.animator.tools.Helpers.parseCommands;
import static cs5004.animator.utils.AnimationReader.parseFile;

/**
 * This class represents an Easy Animator.
 */
public final class EasyAnimator {

  /**
   * Main method for Easy Animator.
   * It manages events from Readable file and generates a view accordingly.
   * @param args default arguments for main method.
   */
  public static void main(String[] args) {
    String[] commands = parseCommands(args);
    String input = commands[0];
    String view = commands[1];
    String output = commands[2];
    String speed = commands[3];

    AnimationBuilder<IAnimatorModel> builder = new Builder();
    Readable in = checkInputFile(input);
    IAnimatorModel model = parseFile(in, builder);

    generateView(model, view, output, speed);
  }

}
