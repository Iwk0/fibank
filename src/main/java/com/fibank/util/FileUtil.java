package com.fibank.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtil {

  private static final ReadWriteLock LOCK = new ReentrantReadWriteLock();

  public static void writeToFile(String content, String path) {
    LOCK.writeLock().lock();

    try {
      Path tempPath = Paths.get(path);

      Files.writeString(
          tempPath,
          content + System.lineSeparator(),
          StandardOpenOption.CREATE,
          StandardOpenOption.APPEND);
    } catch (IOException e) {
      log.error("Error writing to file", e);
    } finally {
      LOCK.writeLock().unlock();
    }
  }

  public static List<String> readFile(String path) {
    LOCK.readLock().lock();

    try {
      Path tempPath = Paths.get(path);
      return Files.readAllLines(tempPath);
    } catch (IOException e) {
      log.error("Error reading file", e);
    } finally {
      LOCK.readLock().unlock();
    }
    return List.of();
  }
}
