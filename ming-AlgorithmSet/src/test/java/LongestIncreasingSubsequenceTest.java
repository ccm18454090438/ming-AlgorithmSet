import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class LongestIncreasingSubsequenceTest {

  @Test
  public void TestLongestIncreasingSubsequence() {
    int[] a = {1, 2, 3, 4, 5};
    int[] b = {1, 2, 1, 3, 5, 4, 6, 7};
    int[] c = {};
    int[] d = null;
    assertEquals(0, LongestIncreasingSubsequence.getNumber(c));
    assertEquals(0, LongestIncreasingSubsequence.getNumber(d));
    assertEquals(6, LongestIncreasingSubsequence.getNumber(b));
    assertEquals(5, LongestIncreasingSubsequence.getNumber(a));
  }

}
