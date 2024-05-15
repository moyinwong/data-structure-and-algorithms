import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LCS {

    public static void main(String[] args) {
        String str1 = "ABC";
        String str2 = "GFS";
        System.out.println(lcs(str1, str2));
    }

    public static int lcs(String str1, String str2) {
        int n = str1.length() + 1;
        int m = str2.length() + 1;
        int[][] dp = new int[n][m];

        // prefill with 0s for easier traverse of algorithm
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }

        for (int j = 0; j < m; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    int top = dp[i - 1][j];
                    int left = dp[i][j - 1];
                    if (top == left) {
                        dp[i][j] = left;
                    } else {
                        dp[i][j] = Math.max(top, left);
                    }
                }
            }
        }

        int row = n - 1;
        int col = m - 1;
        int curr = dp[row][col];
        StringBuilder lcs = new StringBuilder();
        System.out.println("curr: " + curr);
        while (curr != 0) {
            int top = dp[row - 1][col];
            int left = dp[row][col - 1];
            int diagonal = dp[row - 1][col - 1];

            if (top == left && left == diagonal && curr != diagonal) {
                // values comes from diagonal calculation
                // we have an extra row & column, so need row - 1/col - 1 to get correct character at either str
                lcs.append(str1.charAt(row - 1));
                row--;
                col--;
            } else if (top == left && left == diagonal || top < left) {
                // top and left the same, comes from left
                col--;
            } else if (top > left) {
                // comes from top
                row--;
            }

            curr = dp[row][col];
        }

        // lcs string
        // lcs.reverse().toString()
        return dp[n - 1][m - 1];
    }
}
