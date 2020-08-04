import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Test;

public class SM3Test {

  @Test
  public void testSM3() throws IOException {
    assertEquals("66C7F0F462EEEDD9D1F2D46BDC10E4E24167C4875CF2F7A2297DA02B8F4BA8E0",
        SM3.getSM3("abc"));
  }

}

