

public class ShortestPath {


  public static int[] getDistan(int n,int[][] dis,int begin){
    int n1 = n;
    int n2 = dis.length;
    int[][] a1 = new int[n1][2];//Used to store the shortest distance found
    int[][] a3 = dis;
    int[][] a2 = new int[n1][2];//Used to store the known distance before and after the change，
    int[] to=new int[n1];
    //Whether the second use and store have been referenced
    int i;
    int l = begin;
    for (i = 0; i < n1; i++) {
      a2[i][0] = 1000000;
      a2[i][1] = 0;
    }
    int j;
    int min1;
    a1[0][0] = l;//Initialize, the starting distance from the starting point is 0
    a1[0][1] = 0;
    to[l-1]=0;
    a2[l - 1][1] = 1;
    for (i = 1; i < n1; i++) {
      for (j = 0; j < n2; j++) {/*The loop finds the distance to the point and modifies the corresponding value of the array*/
        if (a3[j][0] == l) {
          min1 = a3[j][2] + a1[i - 1][1];
          if (a2[a3[j][1] - 1][1] == 0) {
            if (a2[a3[j][1] - 1][0] > min1) {
              a2[a3[j][1] - 1][0] = min1;
            }
          }
        }
        if (a3[j][1] == l) {
          min1 = a3[j][2] + a1[i - 1][1];
          if (a2[a3[j][0] - 1][1] == 0) {
            if (a2[a3[j][0] - 1][0] > min1) {
              a2[a3[j][0] - 1][0] = min1;
            }
          }
        }
      }
      int min2 = 1000000000;//Used to store the minimum value found in the reconstructed array
      int min3 = -1;
      for (j = 0; j < n1; j++) {
        if (a2[j][1] == 0) {
          if (min2 > a2[j][0]) {
            min3 = j;
            min2 = a2[j][0];
          }
        }
      }
      a2[min3][1] = 1;//Modify the array that stores the distance after it is found
      a1[i][0] = min3 + 1;
      a1[i][1] = min2;
      l = min3 + 1;//Initializes the new starting point after the change
      to[min3]=min2;
    }
    return to;
  }

}
