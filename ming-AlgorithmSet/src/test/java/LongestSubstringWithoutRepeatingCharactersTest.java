import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LongestSubstringWithoutRepeatingCharactersTest {


  @Test
  public void TestLongestSubstringWithoutRepeatingCharactersTest () {


    assertEquals(3,LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("123"));
    assertEquals(7,LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("asdaghjk"));
    assertEquals(0,LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(""));
    assertEquals(1,LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("aaaa"));
  }
}
