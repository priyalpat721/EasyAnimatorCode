package cs5004.animator.tools;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

import cs5004.animator.model.IAnimatorModel;
import cs5004.animator.view.IAnimatorView;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;
import cs5004.animator.view.VisualView;

public class Helpers {

  public static String createFile(String name, String format, String content) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Invalid name");
    } else if (format == null || format.isBlank()) {
      throw new IllegalArgumentException("Invalid format");
    } else if (content == null || content.isBlank()) {
      throw new IllegalArgumentException("Invalid content");
    }

    String fileName = name + "." + format;

    try {
      Writer newFile = new FileWriter(fileName);
      newFile.write(content);
      newFile.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("An error occurred while creating the output file");
    }

    return fileName;
  }

  public static String[] parseCommands(String[] args) {
    Objects.requireNonNull(args, "Must have non-null args source");

    String[] commands = new String[4];
    for (int i = 0; i < 4; i++) {
      commands[i] = "";
    }

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-in")) {
        commands[0] = args[i + 1];
      }

      if (args[i].equals("-view")) {
        commands[1] = args[i + 1];
      }

      if (args[i].equals("-out")) {
        commands[2] = args[i + 1];
      }

      if (args[i].equals("-speed")) {
        commands[3] = args[i + 1];
      }
    }

    if (commands[0].equals("")) {
      throw new IllegalArgumentException("Input file is mandatory");
    } else if (commands[1].equals("")) {
      throw new IllegalArgumentException("View type is mandatory");
    }

    return commands;
  }

  public static void generateView(IAnimatorModel animation, String viewType,
                                  String[] outputFile, int speed) {
    String content = "";

    switch (viewType) {
      case "text" -> {
        TextView text = new TextView();
        text.create(animation, speed);
        content = text.generate();
      }
      case "svg" -> {
        SVGView svg = new SVGView();
        svg.create(animation, speed);
        content = svg.generate();
      }
      case "visual" -> {
        IAnimatorView view = new VisualView();
        view.create(animation, speed);
        view.generate();
      }
      default -> {
        throw new IllegalArgumentException("Invalid view type");
      }
    }

    String fileName = "";

    if (!viewType.equals("visual")) {
      if (outputFile.length == 1) {
        if (!outputFile[0].isBlank()) {
          if (viewType.equals("text")) {
            fileName = createFile(outputFile[0], "txt", content);
          }
          if (viewType.equals("svg")) {
            fileName = createFile(outputFile[0], "svg", content);
          }
          System.out.printf("%s created%n", fileName);
        } else {
          System.out.print(content);
        }
      } else {
        fileName = createFile(outputFile[0], outputFile[1], content);
        System.out.printf("%s created%n", fileName);
      }
    }
  }

}
