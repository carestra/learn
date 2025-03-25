package se.carestra.learn.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class CreateDirectory {
  public static void main(String[] args) {
    var homeDirectory = System.getProperty("user.home");
    var osTmpDir = System.getProperty("java.io.tmpdir");
    System.out.format("Home directory: %s%n", homeDirectory);
    System.out.format("OS tmp directory: %s%n", osTmpDir);

    Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxrwxrwx");
    try {
      // The name is set as:
      // String s = prefix + Long.toUnsignedString(n) + suffix;
      String prefix = "devjava_";
      Path devjavaDirPath = Paths.get(homeDirectory, prefix);
      /**
       * In unix like systems every process has a property called umask which is masked onto the permissions of
       * any file created and inherited by child processes. the default is 0002 or "turn off write for others".
       * So it's most likely that Java is setting the permission you seek and then it's being masked out.
       * You can explicitly set the permissions after creating the file or change the umask settings of the
       * java process before you start it.
       */
      FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);

      Path created = Files.createDirectory(devjavaDirPath, attr);
      // Update the permission since its umasked by the operating system
      Files.setPosixFilePermissions(created, perms);
      System.out.format("Directory created: %s%n", created);

      // As the operating system isn’t taking care of the cleanup
      created.toFile().deleteOnExit();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // create temp dir in OS temp dir
    try {
      String prefix = "devjava-tmp_";
      Path osTmpDirPath = Files.createTempDirectory(prefix);
      // Update the permission since its umasked by the operating system
      Files.setPosixFilePermissions(osTmpDirPath, perms);
      System.out.println("OS Temp directory created: " + osTmpDirPath);
      System.out.println("OS Temp dir absolut path: " + osTmpDirPath.toAbsolutePath());

      // As the operating system isn’t taking care of the cleanup
      osTmpDirPath.toFile().deleteOnExit();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // create temp dir in any localition
    try {
      String prefix = "devjava-tmp_";
      Path devjavaDirPath = Paths.get(homeDirectory);
      Path osTmpDirPath = Files.createTempDirectory(devjavaDirPath, prefix);
      // Update the permission since its umasked by the operating system
      Files.setPosixFilePermissions(osTmpDirPath, perms);
      System.out.println("OS Temp directory created: " + osTmpDirPath);
      System.out.println("OS Temp dir absolut path: " + osTmpDirPath.toAbsolutePath());

      // As the operating system isn’t taking care of the cleanup
      osTmpDirPath.toFile().deleteOnExit();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }
}
