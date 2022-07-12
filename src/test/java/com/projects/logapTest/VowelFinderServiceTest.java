package com.projects.logapTest;

import com.projects.logapTest.VowelFinderAPI.VowelFinderResponse;
import com.projects.logapTest.VowelFinderAPI.VowelFinderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class VowelFinderServiceTest {
  private final VowelFinderService underTest = new VowelFinderService();

  @Test
  void itShouldFindFirstNonRepeatedVowelAfterConsonantWithVowelBehind() {
    final String testString = "aAbBABacafe";
    final VowelFinderResponse vowelFinderResponse = new VowelFinderResponse(
      testString,
      "e"
    );

    final VowelFinderResponse result =
      underTest.findFirstNonRepeatedVowelAfterConsonantWithVowelBehind(testString);

    assertThat(result.getString()).isEqualTo(
      vowelFinderResponse.getString()
    );
    assertThat(result.getVowel()).isEqualTo(
      vowelFinderResponse.getVowel()
    );

    System.out.println(result);
  }

}
