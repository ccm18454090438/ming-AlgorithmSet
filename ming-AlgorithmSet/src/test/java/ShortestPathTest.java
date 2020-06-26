import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ShortestPathTest {
  @Test
  public void TestShortestPathTest(){
    int[][] a={{1,3,10},{1,5,30},{1,6,100},{2,3,5},{3,4,50},{4,6,10},{5,6,60},{5,4,20}};
    int[] test={0,15,10,50,30,60};
    int[] to= ShortestPath.getDistan(6,a,1);
    int i;
    for (i=0;i<test.length;i++){
      assertEquals(test[i],to[i]);
    }
  }

}
