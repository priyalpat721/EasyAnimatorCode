package cs5004.animator;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;
import cs5004.animator.view.SVGView;

import static cs5004.animator.tools.Helpers.createFile;
import static cs5004.animator.tools.Helpers.generateView;
import static cs5004.animator.tools.Helpers.parseCommands;
import static cs5004.animator.utils.AnimationReader.parseFile;

public final class EasyAnimator {

  // Entry point to our program
  public static void main(String[] args) throws FileNotFoundException {
    String[] commands = parseCommands(args);
    String inputFile = commands[0];
    String viewType = commands[1];
    String[] outputFile = commands[2].split("\\.");

    int speed = 1;
    if (!commands[3].equals("")) {
      speed = Integer.parseInt(commands[3]);
    }

    AnimationBuilder<IAnimatorModel> builder = new Builder();

    var fileName = "src/cs5004/animator/" + inputFile;

    // throws FileNotFoundException
    Readable in = new FileReader(fileName);

    IAnimatorModel animation = parseFile(in, builder);

    generateView(animation, viewType, outputFile, speed);

  }

}
