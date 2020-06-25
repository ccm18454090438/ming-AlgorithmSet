import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class redBlackTreeTest {
  @Test
  public void TestRedBlackTree(){

    redBlackTree tree=new redBlackTree();

    int[] n={50,20,60,10,30,70,40};
    int i;
    for (i=0;i<n.length;i++){
      tree.addNode(n[i]);
    }
    for (i=0;i<n.length;i++){
      assertEquals(n[i],tree.getNode(n[i]).getValue());
      tree.deletNode(n[i]);
      assertEquals(null,tree.getNode(n[i]));
    }


  }

}
