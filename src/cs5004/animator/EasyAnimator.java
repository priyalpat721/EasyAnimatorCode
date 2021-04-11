package cs5004.animator;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;

import static cs5004.animator.tools.Helpers.generateView;
import static cs5004.animator.tools.Helpers.parseCommands;
import static cs5004.animator.utils.AnimationReader.parseFile;
import static cs5004.animator.tools.Helpers.showMessage;


public final class EasyAnimator {

  // Entry point to our program
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
    Readable in = null;

    try {
      in = new FileReader(inputFile);
    } catch (FileNotFoundException e) {
      showMessage("Input file", "Input file not found", 2);
      System.exit(0);
    }

    IAnimatorModel animation = parseFile(in, builder);
    generateView(animation, viewType, outputFile, speed);
  }

}
