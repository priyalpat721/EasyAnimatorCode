package cs5004.animator.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileCreator {

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

}
