import java.util.Scanner;

public class HeapSort {

  public static void swap(int[] a, int b, int c) {
    int d = a[b];
    a[b] = a[c];
    a[c] = d;
  }


  public static String SortString(int[] a) {
    int i;
    Sort(a);
    String s = "";
    for (i = 0; i < a.length; i++) {
      s += String.valueOf(a[i]);
    }
    return s;
  }



  public static  void Sort(int[] a){
    int i;
    for (i = a.length; i > 0; i--) {
      sortEnd(a, i);
      swap(a, 0, i - 1);
    }
  }

  private static void sortEnd(int[] a, int n) {
    int b = (int) (n / 2 - 1);
    int i;
    for (i = b; i >= 0; i--) {
      if (n >= 2 * i + 1 + 1 && a[2 * i + 1] > a[i]) {
        swap(a, i, 2 * i + 1);
        if ((n >= 2 * (2 * i + 1) + 1 + 1 && a[2 * (2 * i + 1) + 1] > a[2 * i + 1]) || (
            n >= 2 * (2 * i + 1) + 2 + 1 && a[2 * (2 * i + 1) + 2] > a[2 * i + 1])) {
          sortEnd(a, n);
        }
      }
      if (n > 2 * i + 2 && a[2 * i + 2] > a[i]) {
        swap(a, i, 2 * i + 2);
        if ((n >= 2 * (2 * i + 2) + 1 + 1 && a[2 * (2 * i + 2) + 1] > a[2 * i + 2]) || (
            n >= 2 * (2 * i + 2) + 2 + 1 && a[2 * (2 * i + 2) + 2] > a[2 * i + 2])) {
          sortEnd(a, n);
        }
      }
    }

  }

}
