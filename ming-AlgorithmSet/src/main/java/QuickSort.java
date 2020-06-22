public class QuickSort {

  public static void Sort(int[] n){
    sortTo(n,0,n.length-1);
  }

  private static void  sortTo(int[] n,int i,int j){
    if(i<j){
      int k = sort(n,i,j);
      sortTo(n,i,k-1);
      sortTo(n,k+1,j);
    }
  }


  private static int sort(int[] n,int be,int end){
    int i=be;
    int j=end+1;
    int k=n[be];

    while(true){
      while(i<end && n[++i]<k);
      while(n[--j]>k);
      if(i>=j) break;
      swap(n,i,j);
    }

    swap(n,be,j);
    return j;
  }

  private static void swap(int[] n,int i,int j){
    int a=n[i];
    n[i]=n[j];
    n[j]=a;
  }

  public static String toSortString(int[] n) {
    Sort(n);
    String a="";
    int i;
    for (i=0;i<n.length;i++){
      a+=String.valueOf(n[i]);
    }
    return a;

  }



}
