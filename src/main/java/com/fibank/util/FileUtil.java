package com.fibank.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtil {

  private static final ReentrantLock FILE_LOCK = new ReentrantLock();

  public static void writeToFile(String line, String filePath) {
    FILE_LOCK.lock();

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
      writer.write(line);
      writer.newLine();
    } catch (IOException e) {
      log.error("Failed to write to file", e);
    } finally {
      FILE_LOCK.unlock();
    }
  }
}
