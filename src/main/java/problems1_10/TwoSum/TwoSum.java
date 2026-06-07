package problems1_10.TwoSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int a = 0; a < nums.length; a++) {
            for (int b = a + 1; b < nums.length; b++) {
                if (nums[a] + nums[b] == target) {
                    result[0] = a;
                    result[1] = b;
                    return result;
                }
            }
        }
        return null;
    }
    // after the 20 problems. O(n^2) time, O(1) space
    public static int[] twoSum2(int[] nums, int target) {
        for(int i = 0; i<nums.length; i++ ){
            for(int j = i+1;j<nums.length; j++){
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return nums;
    }
    // solution from LeetCode. O(n) time, O(n) space
    public static int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        // In case there is no solution
        throw new IllegalArgumentException("No two sum solution");
    }
    // solution from LeetCode. One pass, most optimal. O(n) time, O(n) space
    public static int[] twoSum4(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        // Return an empty array if no solution is found
        return new int[] {};
    }
    public static void main(String[] args) {
        int[] nums = {2, 15, 11, 7};
        int target = 26;
        int[] result = twoSum3(nums,target);
        System.out.println(Arrays.toString(result));
    }
}
