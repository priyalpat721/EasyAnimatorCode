package cs5004.animator.tools;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

import javax.swing.JOptionPane;

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
      showMessage("Output file",
              "An error occurred while creating the file", 2);
      System.exit(0);
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
        try {
          commands[0] = args[i + 1];
        } catch (IndexOutOfBoundsException e) {
          showMessage("Command", "Command -in without argument", 2);
          System.exit(0);
        }
      }

      if (args[i].equals("-view")) {
        try {
          commands[1] = args[i + 1];
        } catch (IndexOutOfBoundsException e) {
          showMessage("Command", "Command -view without argument", 2);
          System.exit(0);
        }
      }

      if (args[i].equals("-out")) {
        try {
          commands[2] = args[i + 1];
        } catch (IndexOutOfBoundsException e) {
          showMessage("Command",
                  "Command -out without argument\nSet to default", 2);
          commands[2] = "";
        }
      }

      if (args[i].equals("-speed")) {
        try {
          commands[3] = args[i + 1];
        } catch (IndexOutOfBoundsException e) {
          showMessage("Command",
                  "Command -speed without argument\nSet to default", 2);
          commands[3] = "1";
        }
      }
    }

    if (commands[0].equals("") && commands[1].equals("")) {
      showMessage("Input and view",
              "Input file and view type are mandatory", 2);
      System.exit(0);
    } else if (commands[0].equals("")) {
      showMessage("Input file", "Input file is mandatory", 2);
      System.exit(0);
    } else if (commands[1].equals("")) {
      showMessage("View type", "View type is mandatory", 2);
      System.exit(0);
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
        showMessage("View type", "Invalid view type", 2);
        System.exit(0);
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
          showMessage("Output file", String.format("%s created", fileName), 1);
        } else {
          System.out.print(content);
        }
      } else {
        fileName = createFile(outputFile[0], outputFile[1], content);
        showMessage("Output file", String.format("%s created", fileName), 1);
      }
    }
  }

  public static void showMessage(String title, String message, int iconNumber) {
    switch (iconNumber) {
      case 1 -> JOptionPane.showMessageDialog(null, message, title,
              JOptionPane.PLAIN_MESSAGE);
      case 2 -> JOptionPane.showMessageDialog(null, message, title,
              JOptionPane.ERROR_MESSAGE);
    }
  }

}
