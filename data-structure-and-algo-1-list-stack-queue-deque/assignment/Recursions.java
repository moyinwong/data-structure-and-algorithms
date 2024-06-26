package assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recursions {
    public static void main(String[] args) {
        var nums = Arrays.asList(
                1, 7, 3, 5, 2, 8, 10, 24, -1, -5, 4
        );
        var res = numsOfIncreasingSubsequences(nums);
        System.out.println("nums of increasing subsequences for: " + nums.toString() + "is " + res);

        int n = 3;
        var res2 = numsOfTilings(4);
        System.out.printf("Given 4 blocks, nums of ways of tiling without 2 black stack on top of each other is for a 2 x [%d] grid: %d", n, res2);

        System.out.println("----- Playing Tower of Hanoi");
        towerOfHanoi(4, 'A', 'C', 'B');

        int[] nums2 = new int[]{1, 7, 3, 5, 2, 8, 10, 24, -1, -5, 4};
        int res3 = longestIncreasingSubsequence(new int[]{1, 7, 3, 5, 2, 8, 10, 24, -1, -5, 4});
        System.out.println("longest of increasing subsequences for: " + Arrays.toString(nums2) + "is " + res3);
    }

    private static void towerOfHanoi(int ringsOnTower, char fromPeg, char toPeg, char auxPeg) {
        if (ringsOnTower == 1) {
            System.out.printf("Moved ring %d from %s to %s \n", ringsOnTower, fromPeg, toPeg);
            return;
        }

        towerOfHanoi(ringsOnTower - 1, fromPeg, auxPeg, toPeg);
        System.out.printf("Moved ring %d from %s to %s \n", ringsOnTower, fromPeg, toPeg);
        towerOfHanoi(ringsOnTower - 1, auxPeg, toPeg, fromPeg);
    }

    private static int numsOfTilings(int n) {
        List<Integer> dp = new ArrayList<>(List.of(1, 3));

        for (int i = 2; i < n; i++) {
            dp.add(i, 3 * dp.get(i - 1) + 4 * dp.get(i - 2));
        }
        return dp.get(n - 1);
    }

    private static int numsOfIncreasingSubsequences(List<Integer> nums) {
        List<Integer> dp = new ArrayList<>();

        for (int i = 0; i < nums.size(); i++) {
            dp.add(1);

            for (int j = 0; j <= i - 1; j++) {
                if (nums.get(j) < nums.get(i)) {
                    dp.set(i, dp.get(j) + dp.get(i));
                }
            }
        }
        return dp.stream().reduce(0, Integer::sum);
    }

    private static int longestIncreasingSubsequence(int[] nums) {
        List<Integer> dp = new ArrayList<>();
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            dp.add(1);
            int currMax = 1;

            for (int j = 0; j <= i - 1; j++) {
                if (nums[j] < nums[i]) {
                    currMax = Math.max(currMax, dp.get(j) + 1);
                }
            }
            dp.set(i, currMax);
            max = Math.max(currMax, max);
        }

        return max;
    }
}
