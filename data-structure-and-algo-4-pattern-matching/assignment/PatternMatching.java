package assignment;

import java.util.*;
import java.util.ArrayList;

/**
 * Your implementations of the Boyer Moore string searching algorithm.
 */
public class PatternMatching {
//    public static void main(String[] args) {
//        String text = "ABABABABABABABABABABA";
//        String pattern = "ABABAB";
//        CharacterComparator comparator = new CharacterComparator();
//        System.out.println(Arrays.toString(boyerMoore(pattern, text, comparator).toArray()));
//        System.out.println("Compare count: " + comparator.getComparisonCount());
//    }

    /**
     * Boyer Moore algorithm that relies on a last occurrence table.
     * This algorithm Works better with large alphabets.
     * <p>
     * Make sure to implement the buildLastTable() method, which will be
     * used in this boyerMoore() method.
     * <p>
     * Note: You may find the getOrDefault() method from Java's Map class useful.
     * <p>
     * You may assume that the passed in pattern, text, and comparator will not be null.
     *
     * @param pattern    The pattern you are searching for in a body of text.
     * @param text       The body of text where you search for the pattern.
     * @param comparator You MUST use this to check if characters are equal.
     * @return List containing the starting index for each match found.
     */
    public static List<Integer> boyerMoore(CharSequence pattern, CharSequence text, CharacterComparator comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<Integer> indices = new ArrayList<>();
        int m = pattern.length();
        int n = text.length();
        if (m > n) {
            return indices;
        }

        Map<Character, Integer> lastTable = buildLastTable(pattern);

        int i = 0;
        while (i <= n - m) {
            int j = m - 1;
            while (j >= 0 && comparator.compare(pattern.charAt(j), text.charAt(i + j)) == 0) {
                j--;
            }
            if (j == -1) {
                indices.add(i);
                i++;
                continue;
            }
            int shift = lastTable.getOrDefault(text.charAt(i + j), -1);
            i += shift < j ? j - shift : 1;
        }
        return indices;
    }

    /**
     * Builds the last occurrence table that will be used to run the Boyer Moore algorithm.
     * <p>
     * Note that each char x will have an entry at table.get(x).
     * Each entry should be the last index of x where x is a particular
     * character in your pattern.
     * If x is not in the pattern, then the table will not contain the key x,
     * and you will have to check for that in your Boyer Moore implementation.
     * <p>
     * Ex. pattern = octocat
     * <p>
     * table.get(o) = 3
     * table.get(c) = 4
     * table.get(t) = 6
     * table.get(a) = 5
     * table.get(everything else) = null, which you will interpret in
     * Boyer-Moore as -1
     * <p>
     * If the pattern is empty, return an empty map.
     * You may assume that the passed in pattern will not be null.
     *
     * @param pattern A pattern you are building last table for.
     * @return A Map with keys of all of the characters in the pattern mapping
     * to their last occurrence in the pattern.
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            map.put(c, i);
        }
        return map;
    }
}