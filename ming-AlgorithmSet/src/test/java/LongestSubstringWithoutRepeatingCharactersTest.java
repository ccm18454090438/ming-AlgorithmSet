import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class longestSubstringWithoutRepeatingCharactersTest {


  @Test
  public void TestLongestSubstringWithoutRepeatingCharactersTest () {


    assertEquals(3,longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("123"));
    assertEquals(7,longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("asdaghjk"));
    assertEquals(0,longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(""));
    assertEquals(1,longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("aaaa"));
  }
}
