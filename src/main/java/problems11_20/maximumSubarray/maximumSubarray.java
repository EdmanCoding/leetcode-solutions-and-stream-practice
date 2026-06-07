package problems11_20.maximumSubarray;

public class maximumSubarray {
    // Kadane's algorithm
    public static int maxSubArray(int[] nums) {
        int currentSum = nums[0];
        int bestSoFar = nums[0];
        for(int i = 1; i<nums.length; i++){
            currentSum = Math.max(currentSum+nums[i], nums[i]);
            bestSoFar = Math.max(currentSum, bestSoFar);
        }
        return bestSoFar;
    }
    // second try with a lot of hints + didn't accept 1 time
    public int maxSubArray3(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] < 0) {
                if (sum + nums[i] > max)
                    max = sum + nums[i];
                sum = 0;
            } else {
                sum += nums[i];
                if (sum > max)
                    max = sum;
            }
        }
        return max;
    }
    // recursion solution
    public static int maxSubArray2(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private static int helper(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }

        int mid = left + (right - left) / 2;

        int leftMax = helper(nums, left, mid);
        int rightMax = helper(nums, mid + 1, right);
        int crossMax = crossSum(nums, left, mid, right);

        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }

    private static int crossSum(int[] nums, int left, int mid, int right) {
        int sum = 0;
        int leftBest = Integer.MIN_VALUE;

        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftBest = Math.max(leftBest, sum);
        }

        sum = 0;
        int rightBest = Integer.MIN_VALUE;

        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightBest = Math.max(rightBest, sum);
        }

        return leftBest + rightBest;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(maxSubArray2(new int[]{5,4,-1,7,8}));
    }
}
