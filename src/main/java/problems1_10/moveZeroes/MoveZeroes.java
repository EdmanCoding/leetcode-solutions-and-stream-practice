package problems1_10.moveZeroes;

import java.util.Arrays;
//my initial solution, 44 ms
public class MoveZeroes {
    public static void moveZeroes(int[] nums) {
        for(int a = 0; a < nums.length; a++){
            if(nums[a]==0){
                for(int b= a+1; b<nums.length; b++){
                    if(nums[b]!=0){
                        int temp = nums[a];
                        nums[a] = nums[b];
                        nums[b] = temp;
                        break;
                    }
                }
            }
        }
    }
    // my second solution 4ms
    public static void moveZeroes2(int[] nums) {
        int zero = nums.length-1;
        for(int a = 0; a < nums.length; a++){
            if(nums[a]==0){
                if(a<zero)
                    zero = a;
            }
            if(nums[a]!=0 && zero<a){
                nums[zero] = nums[a];
                nums[a] = 0;
                for(int b = zero + 1; b<nums.length; b++){
                    if(nums[b]==0){
                        zero = b;
                        break;
                    }
                }
            }
        }
    }
    // most optimal, not my solution, 2ms
    // this is the canonical pattern 🔑
    // This is the pattern you were meant to learn.
    // Let’s name it explicitly (this matters for interviews):
    // Two-pointer compaction pattern
    // (also called slow/fast pointer or stable overwrite)

    // slow pointer = where to write
    // fast pointer = what to read

    public static void moveZeroes3(int[] nums) {
        if(nums==null)
            throw new IllegalArgumentException();
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                if (slow != fast) {
                    nums[slow] = nums[fast];
                    nums[fast] = 0;
                }
                slow++;
            } // [1, 0 , 3, 0, 11]    [0,1,0,3,12]
        }
    }
    // not mine, 1 ms solution
    public static void moveZeroes4(int[] nums) {
        int l = 0;
        int len = nums.length;
        int r = 0;
        while (r < len) {
            if (nums[r]!= 0) {
                nums[l] = nums[r];
                // nums[r] = 0;
                l++;
            }
            r++;
        }
        if(l < len){
            for(int i = l; i < len; i++){
                nums[i] = 0;
            }
        }
    }
    // snowball solution from the solutions (pretty cool), 2ms
    public static void moveZeroes5(int[] nums) {
        int snowBallSize = 0;
        int t =0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]==0){
                snowBallSize++;
            }
            else if (snowBallSize > 0) {
                t = nums[i];
                nums[i]=0;
                nums[i-snowBallSize]=t;
            }
        }
    }
    // resolved by myself. Second time. 2ms
    public static void moveZeroes6(int[] nums) {
        int slow = 0;
        int fast = 1;
        while(fast<nums.length){
            if (nums[slow]!=0){
                slow++;
                fast++;
                continue;
            }
            else if (nums[fast]!=0){
                nums[slow] = nums[fast];
                nums[fast] = 0;
                slow++;
                fast++;
                continue;
            }  // [1, 0 , 3, 0, 11]    [0,1,0,3,12]
            else
                fast++;
        }
    }
    // compact one
    public static void moveZeroes7(int[] nums) {
        int zeros = 0;
        for(int i = 0; i<nums.length; i++){
            if(nums[i]!=0){
                int temp = nums[i];
                nums[i]=0;
                nums[zeros++]=temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,3,12};
        moveZeroes7(nums);
        System.out.println(Arrays.toString(nums));
    }
}
