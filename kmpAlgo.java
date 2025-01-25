import java.util.ArrayList;
import java.util.List;

public class kmpAlgo {
    public List<Integer> kmp(String s, String patten) {
        int n = s.length();
        int m = patten.length();
        int[] lps = new int[m];
        lpsMaking(patten, lps, m);

        List<Integer> res = new ArrayList<>();
        int i = 0;  // Pointer for the string s
        int j = 0;  // Pointer for the pattern

        while (i < n) {
            if (s.charAt(i) == patten.charAt(j)) {
                i++;
                j++;
            }

            if (j == m) {
                res.add(i - j);  // The match starts at i - j
                j = lps[j - 1];  // Reset j based on the LPS array
            } else if (s.charAt(i) != patten.charAt(j)) {
                if (j != 0) {
                    j = lps[j - 1];  // Shift j based on LPS
                } else {
                    i++;  // Move i forward when no match and j == 0
                }
            }
        }

        return res;
    }

    public void lpsMaking(String patten, int[] lps, int m) {
        int length = 0;  // Length of the previous longest prefix suffix
        lps[0] = 0;  // Base case, first character has no proper prefix-suffix
        int i = 1;  // Start from the second character

        while (i < m) {
            if (patten.charAt(i) == patten.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1];  // Use the previous LPS value
                } else {
                    lps[i] = 0;  // No prefix-suffix match, move forward
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        kmpAlgo kmp = new kmpAlgo();
        String text = "ababcababcabc";
        String pattern = "abc";
        List<Integer> result = kmp.kmp(text, pattern);
        System.out.println(result);  // Expected output: [2, 7, 10]
    }
}

