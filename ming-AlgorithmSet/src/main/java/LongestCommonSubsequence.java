import java.util.Scanner;

public class LongestCommonSubsequence {

    static  int aa;
    static  int bb;

    private static int Max(char[] x, char[] y, int x1, int y1){

      int M=0;
      int n1=x.length-x1;
      int n2=y.length-y1;
      if (n1>0 && n2>0){
        if (x[x1]==y[y1]){
          M=1+Max(x,y,x1+1,y1+1);
        }else {
          if (n1>=1 && n2>=1){
            M=M+max(Max(x,y,x1+1,y1),Max(x,y,x1,y1+1));
          }

        }
      }
      return M;
    }

    private static int max(int a,int b){
      if (a>b){
        return a;
      }
      return b;
    }

    public static int getLong(String x,String y){
      aa=0;
      bb=0;
      return Max(x.toCharArray(),y.toCharArray(),0,0);

    }
  }


