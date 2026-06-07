package problems11_20.binarySearch;

public class BinarySearch {
    public static int search3(int[] nums, int target) {
        if(nums == null)
            throw new IllegalArgumentException();
        int left = 0;
        int right = nums.length - 1;
        while(left<=right){
            int mid = left + (right - left) / 2;
            if (nums[mid]==target) return mid;
            else if (nums[mid]<target){
                left = mid+1;
            }
            else if (nums[mid]>target){
                right = mid-1;
            }
        }
        return -1;
    }

    // second solving solution, didn't handle because
    // assigned left,right = mid instead of left = mid+1, right = mid-1
    // initially tried while(left<right) that will be false for
    // nums with 1 element and target = nums[0] (nums{5}, target = 5 should be true)
    public static int search2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = (left+right)/2;
        while(left<=right){
            if(nums[mid]==target)
                return mid;
            else if(nums[mid]>target){
                right = mid-1;
                mid = (left+right)/2;
            }
            else if(nums[mid]<target){
                left = mid+1;
                mid = (left+right)/2;
            }
        }
        return -1;
    }
    // 3rd attempt, total success :)
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int mid = (start + end)/2;
        while(end>=start){
            if(nums[mid]> target){
                end = mid - 1;
                mid = (start+end)/2;
            }
            else if(nums[mid]<target){
                start = mid + 1;
                mid = (start+end)/2;
            }
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,3,5,9,12};
        int[] nums1 = {-1,0,3,5,9,12};
        int[] nums2 = {-1,0,3,5,9,12};
        System.out.println(search2(nums,9));
        System.out.println(search2(nums1,2));
        System.out.println(search3(nums2,12));
        System.out.println(search3(nums2,22));
    }
}
