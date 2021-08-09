package com.bluenile.scrabble.controller;

import java.io.IOException;
import java.util.SortedSet;

import com.bluenile.scrabble.service.ScrabbleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ScrabbleController {

  @Autowired
  private ScrabbleService scrabbleService;

  @ResponseBody
  @RequestMapping(value = "/words/{letters}", produces = "application/json")
  public SortedSet<String> getWords(@PathVariable("letters") String letters) throws IOException {
    return scrabbleService.getWords(letters.toLowerCase()) ;
  }
}
