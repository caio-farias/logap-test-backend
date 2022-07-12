package com.projects.logapTest.VowelFinderAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("task-1")
public class VowelFinderController {
  private final VowelFinderService vowelFinderService;

  @Autowired
  public VowelFinderController(VowelFinderService vowelFinderService) {
    this.vowelFinderService = vowelFinderService;
  }

  @GetMapping(path = "{string}")
  public VowelFinderResponse getFirstNonRepeatedVowelAfterConsonantWithVowelBehind(
    @PathVariable("string") String string
  ) {
    return vowelFinderService.findFirstNonRepeatedVowelAfterConsonantWithVowelBehind(string);
  }
}
