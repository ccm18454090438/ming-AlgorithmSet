import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MathematicalExpressionTest {

  @Test
  public void TestMathematicalExpression(){
    assertEquals(true, MathematicalExpression.getResult("1+1*0-1")==0);
    assertEquals(true,
        MathematicalExpression.getResult("(1.5+1)+(1+1)*6/2-3+5*(4*5-20+6/3-2+3-1*1)+8*0")==15.5);

  }

}
