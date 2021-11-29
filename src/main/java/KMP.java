
public class KMP {

  public static int count = 0;

  public static int getNumber(String child,String mather) {
    String A =mather;
    String a =child;
    A = a + A;
    int[] n = new int[A.length() + 1];
    Getnext(n, A.toCharArray());
    int sum = 0;
    int i;
    int cont = 1;
    if (mather.length()<a.length()|| mather==null){
      return 0;
    }
    for (i = a.length() - 1; i < A.length(); i++) {
      if (n[i + a.length()] - n[i + a.length()] == a.length()) {
        cont++;
        i += a.length() - 1;
      }
      if (i + a.length() >= A.length()) {
        break;
      }
    }
    for (i = a.length() - 1; i < A.length() - a.length() + 1; i++) {
      if (n[i] % a.length() == 0) {
        if (n[i + a.length()] - n[i] == a.length() && n[i + a.length()] <= cont * a.length()) {
          sum++;
        }
      }
    }
    return sum;
  }

  private static void Getnext(int next[], char[] t) {
    int j = 0, k = -1;
    next[0] = -1;
    while (j < t.length) {
      if ((k == -1) || (t[j] == t[k])) {
        j++;
        k++;
        next[j] = k;
      } else {
        k = next[k];
      }
    }
  }

}