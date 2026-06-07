package problems1_10.TwoSum;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSumHashTable {
    public static int[] twoSum(int[] nums, int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])&&map.get(target - nums[i]) != i){
                return new int[]{i, map.get(target - nums[i])};
            }
        }
        return new int[]{};
    }
    public static void main(String[] args) {
        int[] nums = {3, 3};
        int target = 6;
        int[] result = twoSum(nums,target);
        System.out.println(Arrays.toString(result));
    }
}
