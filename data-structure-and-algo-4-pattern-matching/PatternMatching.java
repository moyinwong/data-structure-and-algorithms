import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


class HashAndPower {
    public double patternHash = 0;
    public double highestPowerOfBase;
}

public class PatternMatching {

    public static void main(String[] args) {
        String text = "somethingcadbcasomethicadbca";
        String pattern = "bca";
        System.out.println(Arrays.toString(rabinKarp(text, pattern)));
    }

    public static int[] rabinKarp(String text, String pattern) {
        final int BASE = 10;
        int m = pattern.length();
        int n = text.length();
        List<Integer> indices = new ArrayList<>();
        HashAndPower hashAndBase = getPatternHashAndHighestPowerOfBase(pattern, BASE);
        double patternHash = hashAndBase.patternHash;
        double baseHighest = hashAndBase.highestPowerOfBase;
        double currentHash = 0;

        int k = 0;
        for (int i = m - 1; i >= 0; i--) {
            int charVal = (int) text.charAt(i) - 97;
            currentHash += charVal * Math.pow(BASE, k);
            k++;
        }

        int curr = 0;
        if (patternHash == currentHash) {
            indices.add(curr);
        }
        curr++;

        while (curr <= n - m) {
            int newChar = (int) text.charAt(curr + m - 1) - 97;
            int oldChar = (int) text.charAt(curr - 1) - 97;

            currentHash = (currentHash - oldChar * baseHighest) * BASE + newChar;
            if (currentHash == patternHash) {
                int j = 0;
                while (j < m && pattern.charAt(j) == text.charAt(curr + j)) {
                    j++;
                }
                if (j == m) {
                    indices.add(curr);
                }
            }
            curr++;
        }
        return indices.stream().mapToInt(Integer::intValue).toArray();
    }

    private static HashAndPower getPatternHashAndHighestPowerOfBase(String pattern, int base) {
        HashAndPower hashAndBase = new HashAndPower();

        int m = pattern.length();
        int j = 0;
        for (int i = m - 1; i >= 0; i--) {
            int charVal = (int) pattern.charAt(i) - 97;
            double powerOfBase = Math.pow(base, j);
            double contribution = charVal * powerOfBase;
            hashAndBase.patternHash += contribution;

            if (i == 0) {
                hashAndBase.highestPowerOfBase = powerOfBase;
            }
            j++;
        }
        return hashAndBase;
    }


    public static int[] kruthMorrisPratt(String text, String pattern) {
        List<Integer> matchedIndexes = new ArrayList<Integer>();
        int[] failureTable = buildFailureTable(pattern);
        int n = text.length();
        int m = pattern.length();

        int i = 0;
        int j = 0;

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                if (j == m - 1) {
                    matchedIndexes.add(i - j);

                    // reset j to match prefix/suffix at j - 1
                    j = failureTable[j - 1];
                } else {
                    j++;
                }
                i++;
            } else if (text.charAt(i) != pattern.charAt(j) && j == 0) {
                i++;
            } else {
                // previously matched character, look up failure table and skip the length of prefix/suffix of character at j - 1
                j = failureTable[j - 1];
            }
        }
        return matchedIndexes.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int[] buildFailureTable(String pattern) {
        int m = pattern.length();
        int[] table = new int[m];
        table[0] = 0;

        int prefixIndex = 0;
        int curr = 1;
        while (curr < m) {
            if (pattern.charAt(prefixIndex) == pattern.charAt(curr)) {
                table[curr] = prefixIndex + 1;
                prefixIndex++;
                curr++;
            } else if (pattern.charAt(prefixIndex) != pattern.charAt(curr) && prefixIndex == 0) {
                table[curr] = 0;
                curr++;
            } else {
                prefixIndex = table[prefixIndex - 1];
            }
        }
        return table;
    }


    public static int[] boyerMoore(String text, String pattern) {
        List<Integer> matchIndexes = new ArrayList<>();

        var lastOccurrenceMap = buildLastOccurrenceMap(pattern);
        int n = text.length();
        int m = pattern.length();
        int i = 0;

        while (i <= n - m) {
            int j = m - 1;

            while (j >= 0 && text.charAt(i + j) == pattern.charAt(j)) {
                j--;
            }
            if (j == -1) {
                matchIndexes.add(i);
                i++;
                continue;
            }

            char unmatchedChar = text.charAt(i + j);
            int lastOccurrenceIndex = lastOccurrenceMap.getOrDefault(unmatchedChar, -1);

            i += lastOccurrenceIndex < j ? j - lastOccurrenceIndex : 1;
        }
        return matchIndexes.stream().mapToInt(Integer::intValue).toArray();
    }

    public static HashMap<Character, Integer> buildLastOccurrenceMap(String pattern) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            map.put(pattern.charAt(i), i);
        }
        return map;
    }
}
