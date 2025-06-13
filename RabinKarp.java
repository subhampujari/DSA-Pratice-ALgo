public class RabinKarp {
    static final int d = 256; // Number of characters in the input alphabet
    static final int q = 101; // A prime number for modulo to reduce hash collisions

    public static void search(String pattern, String text) {
        int m = pattern.length();
        int n = text.length();
        int i, j;
        int p = 0; // hash value for pattern
        int t = 0; // hash value for text
        int h = 1;

        // The value of h would be "pow(d, m-1)%q"
        for (i = 0; i < m - 1; i++)
            h = (h * d) % q;

        // Calculate the hash value of pattern and first window of text
        for (i = 0; i < m; i++) {
            p = (d * p + pattern.charAt(i)) % q;
            t = (d * t + text.charAt(i)) % q;
        }

        // Slide the pattern over text one by one
        for (i = 0; i <= n - m; i++) {
            // Check the hash values of current window of text and pattern
            if (p == t) {
                // If the hash values match, check characters one by one
                for (j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j))
                        break;
                }

                if (j == m) {
                    System.out.println("Pattern found at index " + i);
                }
            }

            // Calculate hash value for next window of text: Remove leading digit,
            // add trailing digit
            if (i < n - m) {
                t = (d * (t - text.charAt(i) * h) + text.charAt(i + m)) % q;

                // We might get negative value of t, converting it to positive
                if (t < 0)
                    t = (t + q);
            }
        }
    }

    public static void main(String[] args) {
        String text = "ABCCDDAEFG";
        String pattern = "CDD";

        search(pattern, text);
    }
}
