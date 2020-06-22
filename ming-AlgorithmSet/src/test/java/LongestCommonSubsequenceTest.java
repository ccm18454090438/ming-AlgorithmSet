import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LongestCommonSubsequenceTest {
  @Test
  public void TestLongestCommonSubsequence(){
    assertEquals(0,LongestCommonSubsequence.getLong("123467",""));
    assertEquals(2,LongestCommonSubsequence.getLong("13458","18rr"));
    assertEquals(3,LongestCommonSubsequence.getLong("135676645344334","adfg434"));
  }
}
