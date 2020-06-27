//Evaluate complex expressions
import java.io.IOException;

public class MathematicalExpression {



//  public static void  main(String[] args){
//    System.out.println(getResult("1+1*0-1"));
//  }

  public static double getResult(String expression) {
    String one =expression;
    try {
      if (cont(one)==false){
        throw new IOException();
      }
      return RemoveBrackets(new StringBuffer(one));
    }
    catch (Exception e){
      System.out.println("Please check if the expression is valid if there is an error");
    }
    return -1;
  }

  private static boolean cont(String  a) {//Legality of judgment
    boolean b = true;
    char[] to = a.toCharArray();
    int i, j;
    for (i = 0; i < to.length - 1; i++) {
      if (to[i] == '+' || to[i] == '-' || to[i] == '*' || to[i] == '/') {
        if (to[i + 1] == '+' || to[i + 1] == '-' || to[i + 1] == '*' || to[i + 1] == '/') {
          return false;
        }
      }
    }
    return b;
  }


  private static double RemoveBrackets(StringBuffer a) {//Bracket removal method

    while (a.toString().indexOf('(') != -1) {
      int i = a.toString().indexOf('(');
      int j = GetBracketPosition(a.toString(), i + 1);
      String to = a.toString().substring(i + 1, j);
      a.replace(i, j + 1, String.valueOf(RemoveBrackets(new StringBuffer(to))));
    }
    return EliminatePlusSign(a.toString());//Calculate the formula other than parentheses
  }


  private static double EliminatePlusSign(String a) {//Eliminate the plus sign

    char[] b = a.toCharArray();
    for (int i = 0; i < a.length(); i++) {//Testing a plus sign
      if (b[i] == '+') {
        return EliminateMinusSign(a.substring(0, i)) + EliminatePlusSign(a.substring(i + 1));//Detection of minus
      }
    }

    return EliminateMinusSign(a);//The minus sign is detected directly without the plus sign
  }
  private static double EliminateMinusSign(String a){//Check for subtractions and subdivisions
    char[] b=a.toCharArray();
    if (b[0]=='-'){
      a="0"+a;
      b=a.toCharArray();
    }
    for (int i = 0; i < a.length(); i++) {//Detection of minus
      if (b[i] == '-' && (b[i-1]>='0') && b[i-1]<='9') {
        return EliminatePlusSign(a.substring(0, i)) - EliminatePlusSign(a.substring(i + 1));
      }
    }

    String[] sum1 = a.split("/");//Eliminate devide

    double too = Double.valueOf(CalculateMultiplication(sum1[0]));/*A multiplication sign is detected for each division expression*/
    for (int i = 1; i < sum1.length; i++) {
      too = too * 1.0 /Double.valueOf(CalculateMultiplication(sum1[i]));
    }
    return too;

  }

  //So the only way to multiply is to multiply
  private static double CalculateMultiplication(String aaa) {//Remove the multiplication sign
    double to = 1.0;
    if (aaa.indexOf('*') == -1) {//Without a multiplication sign
      return Double.valueOf(aaa);
    }
    String[] too = aaa.split("\\*");

    for (int i = 0; i < too.length; i++) {
      to = to * Double.valueOf(too[i]);
    }
    return to;
  }

  private static int GetBracketPosition(String a, int b) {//Find the corresponding close parenthesis position
    int count1 = 1;
    int count2 = 0;
    char[] aa = a.toCharArray();
    for (int i = b; i < a.length(); i++) {
      if (aa[i] == '(') {//Prevent double parenthesis
        count1++;
      }
      if (aa[i] == ')') {
        count2++;
        if (count2 == count1) {
          return i;
        }
      }
    }
    return 0;
  }
}
