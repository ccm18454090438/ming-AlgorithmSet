import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HeapSortTest {


  @Test
  public void TestHeapSort(){


    int[] a={3,2,4,1};
    int[] b={7,6,3,0};
    assertEquals("1234",HeapSort.SortString(a));
    assertEquals("0367",HeapSort.SortString(b));



  }
}
