package cs5004.animator;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.utils.AnimationBuilder;
import cs5004.animator.utils.Builder;

import static cs5004.animator.utils.AnimationReader.parseFile;

public final class EasyAnimator {

  // Entry point to our program
  public static void main(String[] args) throws FileNotFoundException {
    String inputFile = "";
    String outputFile = "";
    String viewType = "";
    int speed = 1;

    AnimationBuilder<IAnimatorModel> builder = new Builder();

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-in")) {
        inputFile = args[i + 1];
      }

      if (args[i].equals("-out")) {
        outputFile = args[i + 1];
      }

      if (args[i].equals("-view")) {
        viewType = args[i + 1];
      }

      if (args[i].equals("-speed")) {
        speed = Integer.parseInt(args[i + 1]);
      }
    }

    if (inputFile.equals("")) {
      throw new IllegalArgumentException("Input file is mandatory");
    } else if (viewType.equals("")) {
      throw new IllegalArgumentException("View type is mandatory");
    }

    // already throws FileNotFoundException
    Readable in = new FileReader(inputFile);

    IAnimatorModel animation = parseFile(in, builder);
    
    
    
  }

}
