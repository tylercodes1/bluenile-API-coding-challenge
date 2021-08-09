package com.bluenile.scrabble;

import ch.qos.logback.classic.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This application starts a web service that returns Scrabble suggestions for
 * a given set of letters. The highest-scoring words are listed first. For
 * example, an HTTP GET request to http://local.bluenile.com:18080/words/hat
 * returns:
 *
 * <pre>
 * [
 *   "hat",
 *   "ah",
 *   "ha",
 *   "th",
 *   "at",
 *   "a"
 * ]
 * </pre>
 **/
@SpringBootApplication
public class ScrabbleMain {

  public static void main(String[] args) {
    SpringApplication.run(ScrabbleMain.class, args);
  }
}
