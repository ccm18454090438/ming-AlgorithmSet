import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class KMPTest {

  @Test
  public void TestKMP(){
    assertEquals(0,KMP.getNumber("12"," "));
    assertEquals(2,KMP.getNumber("12","123412"));
    assertEquals(0,KMP.getNumber("12",""));
    assertEquals(0,KMP.getNumber("123","12"));
    assertEquals(1,KMP.getNumber("12","12341"));
  }

}
