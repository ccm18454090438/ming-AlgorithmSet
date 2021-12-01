//leecode  The third question

public class LongestSubstringWithoutRepeatingCharacters {


  public static int lengthOfLongestSubstring(String s) {
    char[] chars = s.toCharArray();//Converts to an array of characters
    int i;
    int Max = 1;//The final result
    int max1 = 1;//Ends with the last character
    if (chars.length == 0) {//Special case 1
      return 0;
    }
    if (chars.length == 1) {//Special case 2
      return 1;
    }
    for (i = 1; i < chars.length; i++) {//Dynamic programming
      int k = i - max1;
      for (int j = i - 1; j >= k; j--) {
        if (chars[i] == chars[j]) {//Find the same update pop up
          max1 = i - j;
          break;
        }
        if (j == i - max1) {//update
          max1++;
        }
      }
      if (max1 > Max) {//Whether or not to update
        Max = max1;
      }
    }

    return Max;
  }


}
