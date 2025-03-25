package se.carestra.learn.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class CreateFile {
  public static void main(String[] args) {
    Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxrwxrwx");
    FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);

    var homeDirectory = System.getProperty("user.home");
    Path emptyFilePath = Paths.get(homeDirectory, "emptyFile.txt");
    try {
      Path created = Files.createFile(emptyFilePath, attr);

      System.out.format("File created: %s%n", created);
      created.toFile().deleteOnExit();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // create temp file
    try {
      Path created = Files.createTempFile("emptyFile_", ".txt");

      System.out.format("File created: %s%n", created);
      created.toFile().deleteOnExit();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }


  }
}
