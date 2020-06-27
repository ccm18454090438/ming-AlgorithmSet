import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class QuickSortTest {




  @Test
  public void TestQuickSort(){


    int[] a={3,1,4,2};
    assertEquals("1234", QuickSort.toSortString(a));
    int[] b={2,6,9,2,0,5,-1};
    assertEquals("-1022569", QuickSort.toSortString(b));
  }

}
