package com.projects.logapTest.VowelFinderAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Data
public class VowelFinderResponse {

  private String string;

  @JsonProperty("vogal")
  private String vowel;

  @JsonProperty("tempoTotal")
  private String timeSpent;

  public VowelFinderResponse(String string, String vowel) {
    setString(string);
    setVowel(vowel);
  }
}
