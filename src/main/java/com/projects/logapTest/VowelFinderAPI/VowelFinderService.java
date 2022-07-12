package com.projects.logapTest.VowelFinderAPI;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class VowelFinderService {
  private static final List<Character>
    vowelsList
    = List.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
  private final List<Character> repeatedVowelsList = new ArrayList<>();

  private VowelFinderResponse searchFirstNonRepeatedVowelAfterConsonantWithVowelBehind(String string) {
    for (int i = 1; i < string.length() - 1; i++) {
      Character previousChar = string.charAt(i - 1);
      Character currentChar = string.charAt(i);
      Character nextChar = string.charAt(i + 1);

      boolean isPreviousCharVowel = vowelsList.contains(previousChar);
      boolean
        isPreviousCharRepeatedVowel
        = !repeatedVowelsList.contains(previousChar);
      if (isPreviousCharVowel && isPreviousCharRepeatedVowel) {
        repeatedVowelsList.add(previousChar);
      }

      boolean isCurrentCharConsonant = !vowelsList.contains(currentChar);
      boolean isNextCharVowel = vowelsList.contains(nextChar);
      boolean isNextCharRepeatedVowel = repeatedVowelsList.contains(nextChar);

      if (isPreviousCharVowel
          && isCurrentCharConsonant
          && isNextCharVowel
          && !isNextCharRepeatedVowel) {
        return new VowelFinderResponse(string, Character.toString(nextChar));
      }
    }
    return new VowelFinderResponse(string, "No vowel after consonant found.");
  }

  public VowelFinderResponse findFirstNonRepeatedVowelAfterConsonantWithVowelBehind(String string) {
    long startTime = System.currentTimeMillis();
    VowelFinderResponse
      vowelFinderResponse
      = searchFirstNonRepeatedVowelAfterConsonantWithVowelBehind(string);
    long endTime = System.currentTimeMillis();
    long timeSpent = endTime - startTime;
    vowelFinderResponse.setTimeSpent(timeSpent + " ms");
    return vowelFinderResponse;
  }
}
