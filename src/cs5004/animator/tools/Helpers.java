package cs5004.animator.tools;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Helpers {

  public static void createFile(String name, String format, String content) {

    String finalName = name + "." + format;

    try {
      Writer newFile = new FileWriter(finalName);
      newFile.write(content);
      newFile.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("An error occurred");
    }
  }

  public static String[] parseCommands(String[] args) {
    String[] commands = new String[4];

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-in")) {
        commands[0] = args[i + 1];
      } else {
        commands[0] = "";
      }

      if (args[i].equals("-view")) {
        commands[1] = args[i + 1];
      } else {
        commands[1] = "";
      }

      if (args[i].equals("-out")) {
        commands[2] = args[i + 1];
      } else {
        commands[2] = "";
      }

      if (args[i].equals("-speed")) {
        commands[3] = args[i + 1];
      } else {
        commands[3] = "";
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
