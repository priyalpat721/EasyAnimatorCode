package cs5004.animator.tools;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

public class Helpers {

  public static void createFile(String name, String format, String content) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Invalid name");
    } else if (format == null || format.isBlank()) {
      throw new IllegalArgumentException("Invalid format");
    } else if (content == null || content.isBlank()) {
      throw new IllegalArgumentException("Invalid content");
    }

    String finalName = name + "." + format;

    try {
      Writer newFile = new FileWriter(finalName);
      newFile.write(content);
      newFile.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("An error occurred while creating the output file");
    }
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

}
