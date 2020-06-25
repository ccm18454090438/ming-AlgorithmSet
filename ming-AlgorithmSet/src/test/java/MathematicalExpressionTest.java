import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class mathematicalExpressionTest {

  @Test
  public void TestMathematicalExpression(){
    assertEquals(true,mathematicalExpression.getResult("1+1*0-1")==0);
    assertEquals(true,
        mathematicalExpression.getResult("(1.5+1)+(1+1)*6/2-3+5*(4*5-20+6/3-2+3-1*1)+8*0")==15.5);

  }

}
