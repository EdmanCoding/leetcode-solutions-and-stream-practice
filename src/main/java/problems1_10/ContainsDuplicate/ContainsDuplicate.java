package problems1_10.ContainsDuplicate;

import java.util.HashSet;

public class ContainsDuplicate {
    public static boolean containsDuplicate(int[] nums){
        HashSet<Integer> numbers = new HashSet<Integer>();
        for(int num : nums){
            if(!numbers.add(num)){
                return true;
            }
        }
        return false;
    }

    public static boolean containsDuplicate2(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        boolean result;
        for(int i = 0; i>nums.length; i++){
            result = set.add(nums[i]);
            if(result == false){
                return true;
            }
        }
        return false;
    }
    // resolved by myself. O(n) time and space complexity.
    public static boolean containsDuplicate3(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("nums cannot be null");
        }
        //HashSet resizes internally, which costs time.
        HashSet<Integer> set = new HashSet<>(nums.length);
        for(int i = 0; i<nums.length; i++){
            if(!set.add(nums[i]))
                return true;
        }
        return false;
    }
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7,8,9};
        System.out.println(containsDuplicate2(nums));
        int[] nums2 = {1,2,3,1};
        System.out.println(containsDuplicate2(nums2));
        int[] nums3 = {1,1,1,3,3,4,3,2,4,2};
        System.out.println(containsDuplicate2(nums3));
    }
}
