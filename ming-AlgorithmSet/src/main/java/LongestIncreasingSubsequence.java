

public class longestIncreasingSubsequence {
  public static int getNumber(int[] a){
    int max=1;
    int m = 0;
    if (a==null || a.length==0){
      return 0;
    }
    int[] b=new int[a.length];
    int i,j;
    for (i=0;i<a.length;i++){
      b[i]=1;
      m=0;
      for (j=i-1;j>=0;j--){
        if (a[j]<a[i]){
          if (b[j]>m){
            m=b[j];
          }

        }
      }
      b[i]=m+1;
      if (b[i]>max){
        max=b[i];
      }
    }
    return max;
  }


}
