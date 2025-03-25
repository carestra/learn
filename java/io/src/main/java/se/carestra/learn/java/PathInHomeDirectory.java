package se.carestra.learn.java;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathInHomeDirectory {

  public static void main(String[] args) {
    var homeDirectory = System.getProperty("user.home");
    Path javalearnPath = Paths.get(homeDirectory, "tmp", "javalearn.log");

    //Path debugPath = Path.of("tmp", "debug.log");

    System.out.format("toString: %s%n", javalearnPath);
    System.out.format("getFileName: %s%n", javalearnPath.getFileName());
    System.out.format("getName(0): %s%n", javalearnPath.getName(0));
    System.out.format("getNameCount: %d%n", javalearnPath.getNameCount());
    System.out.format("subpath(0,2): %s%n", javalearnPath.subpath(0,2));
    System.out.format("getParent: %s%n", javalearnPath.getParent());
    System.out.format("getRoot: %s%n", javalearnPath.getRoot());
    System.out.format("to URI: %s%n", javalearnPath.toUri());

    // Converts the input Path
    // to an absolute path.
    // Generally, this means prepending
    // the current working
    // directory.  If this example
    // were called like this:
    //     java FileTest foo
    // the getRoot and getParent methods
    // would return null
    // on the original "inputPath"
    // instance.  Invoking getRoot and
    // getParent on the "fullPath"
    // instance returns expected values.
    // The toAbsolutePath() method converts the user input and returns a Path that returns useful values when queried.
    // The file does not need to exist for this method to work.
    Path fullPath = javalearnPath.toAbsolutePath();
    System.out.format("fullPath getParent: %s%n", fullPath.getParent());
    System.out.format("fullPath getRoot: %s%n", fullPath.getRoot());

    try {
      Path fp = javalearnPath.toRealPath();
      System.out.format("toString: %s%n", fp);
    } catch (NoSuchFileException x) {
      System.err.format("%s: no such" + " file or directory%n", javalearnPath);
      // Logic for case when file doesn't exist.
    } catch (IOException x) {
      System.err.format("%s%n", x);
      // Logic for other sort of file error.
    }
  }
}
