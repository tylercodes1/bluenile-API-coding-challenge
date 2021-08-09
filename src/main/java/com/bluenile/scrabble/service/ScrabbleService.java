package com.bluenile.scrabble.service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import static com.bluenile.scrabble.Utils.Utils.*;

@Component
public class ScrabbleService {

  @Autowired
  ResourceLoader resourceLoader;

  /**
   * Returns a list of words that can be spelled from the given set of letters.
   * It is sorted by its Scrabble point value.
   *
   * @param letters The letters to form words from
   * @return A sorted set of words
   */
  public SortedSet<String> getWords(String letters) throws IOException {
    SortedSet<String> words = getDictionary();

    // Byproduct of reading and splitting from data source
    words.remove("");
    words.remove("\r\n");

    SortedSet<String> result = new TreeSet<>();
    Iterator<String> it = words.iterator();
    boolean highestValidCharReached = false;
    while (it.hasNext()) {
      String word = it.next();
      List<Character> wordList = word.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
      List<Character> letterList = letters.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
      while (it.hasNext() && Collections.disjoint(wordList, letterList)) {
        word = it.next();
        wordList = word.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
      }

      if (containsExactOrFewerChars(word, letters))
        result.add(word);
    }

    return result;
  }
}
