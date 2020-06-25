import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class longestCommonSubsequenceTest {
  @Test
  public void TestLongestCommonSubsequence(){
    assertEquals(0,longestCommonSubsequence.getLong("123467",""));
    assertEquals(2,longestCommonSubsequence.getLong("13458","18rr"));
    assertEquals(3,longestCommonSubsequence.getLong("135676645344334","adfg434"));
  }
}
