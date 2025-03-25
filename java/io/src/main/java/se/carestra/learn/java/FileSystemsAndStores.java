package se.carestra.learn.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class FileSystemsAndStores {
  public static void main(String[] args) {
    FileSystem defaultFS = FileSystems.getDefault();

    System.out.println("Default file system: " + defaultFS);

    var separator = File.separator;
    var separatorDefault = defaultFS.getSeparator();
    System.out.println("Separator: " + separator);
    System.out.println("Separator default: " + separatorDefault);

    defaultFS.getFileStores().forEach(store -> System.out.println("Name: " + store + " Type: " + store.type()));

    defaultFS.getRootDirectories().forEach(dir -> {
      var readable = Files.isReadable(dir);
      System.out.println("Dir: " + dir + " isReadable: " + readable);
    });

    try {
      var homeDirectory = System.getProperty("user.home");
      Path javalearnPath = Paths.get(homeDirectory, "tmp", "javalearn.log");
      var type = Files.probeContentType(javalearnPath);
      if (type == null) {
        System.err.format("'%s' has an unknown filetype: %n", javalearnPath);
      } else if (!type.equals("text/plain")) {
        System.err.format("'%s' is not a text file: %n", javalearnPath);
      }
    } catch (IOException e) {
      System.err.println(e);
    }
  }
}
