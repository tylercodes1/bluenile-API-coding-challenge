package com.bluenile.scrabble.Utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {
    // Loads the Dictionary as SortedSet from csv file
    public static SortedSet<String> getDictionary() throws IOException {
        Resource resource = new ClassPathResource("data.csv");
        InputStream inputStream = resource.getInputStream();

        byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
        String data = new String(bdata, StandardCharsets.UTF_8);
        return new TreeSet<String>(Arrays.asList(data.split(",")));
    }

    public static boolean containsExactOrFewerChars
            (String a, String b) {
        List<Character> aLetters = a.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> bLetters = b.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        while (aLetters.size() > 0) {
            if (Collections.disjoint(aLetters, bLetters))
                return false;

            Character letter = aLetters.get(0);
            if (!bLetters.contains(letter))
                return false;
            aLetters.remove(letter);
            bLetters.remove(letter);
        }

        return true;
    }
}
