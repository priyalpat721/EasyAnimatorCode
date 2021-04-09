package cs5004.animator.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {

  public static void createFile(String name, String format, String content) throws IOException {

    String finalName = name + "." + format;

    try {
      FileWriter newFile = new FileWriter(finalName);
      newFile.write(content);
      newFile.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("An error occurred");
    }
  }

}
